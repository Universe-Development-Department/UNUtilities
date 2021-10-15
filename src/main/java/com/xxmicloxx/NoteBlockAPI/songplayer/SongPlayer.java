package com.xxmicloxx.NoteBlockAPI.songplayer;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import com.xxmicloxx.NoteBlockAPI.event.*;
import com.xxmicloxx.NoteBlockAPI.model.*;
import com.xxmicloxx.NoteBlockAPI.model.playmode.ChannelMode;
import com.xxmicloxx.NoteBlockAPI.model.playmode.MonoMode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.bukkit.Bukkit.getLogger;

/**
 * Plays a Song for a list of Players
 */
public abstract class SongPlayer {
    protected NoteBlockAPI plugin;
    protected Song song;
    protected Playlist playlist;
    protected int actualSong = 0;
    protected short tick = -1;
    protected boolean playing = false;
    protected boolean fading = false;
    protected Map<UUID, Boolean> playerList = new ConcurrentHashMap<>();
    protected Map<Song, Boolean> songQueue = new ConcurrentHashMap<>(); //True if already played
    protected byte volume = 100;
    protected Fade fadeIn;
    protected Fade fadeOut;
    protected Fade fadeTemp = null;
    protected SoundCategory soundCategory;
    protected RepeatMode repeat = RepeatMode.NO;
    protected ChannelMode channelMode = new MonoMode();
    protected boolean random;
    protected boolean enable10Octave = false;
    protected boolean autoStop = false;
    final Lock lock = new ReentrantLock();
    final Random rng = new Random();
    BukkitTask backgroundTask = null;

    public SongPlayer(Song song) {
        this(new Playlist(song), SoundCategory.MASTER);
    }

    public SongPlayer(Song song, SoundCategory soundCategory) {
        this(new Playlist(song), soundCategory);
    }

    public SongPlayer(Song song, SoundCategory soundCategory, boolean random) {
        this(new Playlist(song), soundCategory, random);
    }

    public SongPlayer(Playlist playlist) {
        this(playlist, SoundCategory.MASTER);
    }

    public SongPlayer(Playlist playlist, SoundCategory soundCategory) {
        this(playlist, soundCategory, false);
    }

    public SongPlayer(Playlist playlist, SoundCategory soundCategory, boolean random) {
        this.playlist = playlist;
        this.random = random;
        this.soundCategory = soundCategory;
        plugin = NoteBlockAPI.getAPI();
        fadeIn = new Fade(FadeType.NONE, 60);
        fadeIn.setFadeStart((byte) 0);
        fadeIn.setFadeTarget(volume);
        fadeOut = new Fade(FadeType.NONE, 60);
        fadeOut.setFadeStart(volume);
        fadeOut.setFadeTarget((byte) 0);
        if (random) {
            checkPlaylistQueue();
            actualSong = rng.nextInt(playlist.getCount());
        }
        this.song = playlist.get(actualSong);
    }

    /**
     * Check if 6 octave range is enabled
     *
     * @return true if enabled, false otherwise
     */
    public boolean isEnable10Octave() {
        return enable10Octave;
    }

    /**
     * Enable or disable 10 octave range
     * <p>
     * If not enabled, notes will be transposed to 2 octave range
     *
     * @param enable10Octave true if enabled, false otherwise
     */
    public void setEnable10Octave(boolean enable10Octave) {
        this.enable10Octave = enable10Octave;
    }

    /**
     * Starts playback of this SongPlayer
     */
    void run() {
        if (backgroundTask != null && Bukkit.getScheduler().isCurrentlyRunning(backgroundTask.getTaskId()) && Bukkit.getScheduler().isQueued(backgroundTask.getTaskId()))
            return;
        backgroundTask = plugin.doAsync(() -> {
            while (playing || fading) {
                long startTime = System.currentTimeMillis();
                if (NoteBlockAPI.getAPI().isDisabling()) break;
                lock.lock();
                try {
                    if (fadeTemp != null) {
                        if (fadeTemp.isDone()) {
                            fadeTemp = null;
                            fading = false;
                            if (!playing) {
                                SongStoppedEvent event = new SongStoppedEvent(this);
                                plugin.doSync(() -> Bukkit.getPluginManager().callEvent(event));
                                volume = fadeIn.getFadeTarget();
                                continue;
                            }
                        } else {
                            int fade = fadeTemp.calculateFade();
                            if (fade != -1) volume = (byte) fade;
                        }
                    } else if (tick < fadeIn.getFadeDuration()) {
                        int fade = fadeIn.calculateFade();
                        if (fade != -1) volume = (byte) fade;
                    } else if (tick >= song.getLength() - fadeOut.getFadeDuration()) {
                        int fade = fadeOut.calculateFade();
                        if (fade != -1) volume = (byte) fade;
                    }
                    tick++;
                    if (tick > song.getLength()) {
                        tick = -1;
                        fadeIn.setFadeDone(0);
                        fadeOut.setFadeDone(0);
                        volume = fadeIn.getFadeTarget();
                        if (repeat == RepeatMode.ONE) {
                            SongLoopEvent event = new SongLoopEvent(this);
                            plugin.doSync(() -> Bukkit.getPluginManager().callEvent(event));
                            if (!event.isCancelled()) continue;
                        } else {
                            if (random) {
                                songQueue.put(song, true);
                                checkPlaylistQueue();
                                ArrayList<Song> left = new ArrayList<>();
                                for (Song s : songQueue.keySet()) if (!songQueue.get(s)) left.add(s);
                                if (left.size() == 0) {
                                    left.addAll(songQueue.keySet());
                                    for (Song s : songQueue.keySet()) songQueue.put(s, false);
                                    song = left.get(rng.nextInt(left.size()));
                                    actualSong = playlist.getIndex(song);
                                    if (repeat == RepeatMode.ALL) {
                                        SongLoopEvent event = new SongLoopEvent(this);
                                        plugin.doSync(() -> Bukkit.getPluginManager().callEvent(event));
                                        if (!event.isCancelled()) continue;
                                    }
                                } else {
                                    song = left.get(rng.nextInt(left.size()));
                                    actualSong = playlist.getIndex(song);
                                    SongNextEvent event = new SongNextEvent(this);
                                    plugin.doSync(() -> Bukkit.getPluginManager().callEvent(event));
                                    continue;
                                }
                            } else {
                                if (playlist.hasNext(actualSong)) {
                                    actualSong++;
                                    song = playlist.get(actualSong);
                                    SongNextEvent event = new SongNextEvent(this);
                                    plugin.doSync(() -> Bukkit.getPluginManager().callEvent(event));
                                    continue;
                                } else {
                                    actualSong = 0;
                                    song = playlist.get(actualSong);
                                    if (repeat == RepeatMode.ALL) {
                                        SongLoopEvent event = new SongLoopEvent(this);
                                        plugin.doSync(() -> Bukkit.getPluginManager().callEvent(event));
                                        if (!event.isCancelled()) continue;
                                    }
                                }
                            }
                        }
                        playing = false;
                        SongEndEvent event = new SongEndEvent(this);
                        plugin.doSync(() -> Bukkit.getPluginManager().callEvent(event));
                        continue;
                    }

                    for (UUID uuid : playerList.keySet()) {
                        Player player = Bukkit.getPlayer(uuid);
                        // offline...
                        if (player == null) continue;
                        playTick(player, tick);
                    }
                } catch (Exception e) {
                    getLogger().severe("An error occurred during the playback of song " + (song != null ? song.getPath() + " (" + song.getAuthor() + " - " + song.getTitle() + ")" : "null"));
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                long duration = System.currentTimeMillis() - startTime;
                float delayMillis = song.getDelay() * 50;
                if (duration < delayMillis) {
                    try {
                        Thread.sleep((long) (delayMillis - duration));
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        });
    }

    void checkPlaylistQueue() {
        for (Song s : songQueue.keySet()) if (!playlist.contains(s)) songQueue.remove(s);
        for (Song s : playlist.getSongList()) if (!songQueue.containsKey(s)) songQueue.put(s, false);
    }

    /**
     * Returns {@link Fade} for Fade in effect
     *
     * @return Fade
     */
    public Fade getFadeIn() {
        return fadeIn;
    }

    /**
     * Returns {@link Fade} for Fade out effect
     *
     * @return Fade
     */
    public Fade getFadeOut() {
        return fadeOut;
    }

    /**
     * Gets list of current Player UUIDs listening to this SongPlayer
     *
     * @return list of Player UUIDs
     */
    public Set<UUID> getPlayerUUIDs() {
        Set<UUID> uuids = new HashSet<>(playerList.keySet());
        return Collections.unmodifiableSet(uuids);
    }

    /**
     * Adds a Player to the list of Players listening to this SongPlayer
     */
    public void addPlayer(Player player) {
        addPlayer(player.getUniqueId());
    }

    /**
     * Adds a Player to the list of Players listening to this SongPlayer
     *
     * @param player's uuid
     */
    public void addPlayer(UUID player) {
        lock.lock();
        try {
            if (!playerList.containsKey(player)) {
                playerList.put(player, false);
                ArrayList<SongPlayer> songs = NoteBlockAPI.getSongPlayersByPlayer(player);
                if (songs == null) songs = new ArrayList<>();
                songs.add(this);
                NoteBlockAPI.setSongPlayersByPlayer(player, songs);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * @return {@link #getAutoStop}
     * @deprecated SongPlayer is not being destroyed anymore. This method now does the same as {@link #getAutoStop}
     */
    public boolean getAutoDestroy() {
        return autoStop;
    }

    /**
     * @param autoDestroy {@link #setAutoStop}
     * @deprecated SongPlayer is not being destroyed anymore. This method now does the same as {@link #setAutoStop}
     */
    public void setAutoDestroy(boolean autoDestroy) {
        setAutoStop(autoDestroy);
    }

    /**
     * Returns whether the SongPlayer is going to stop playback when no one is listening
     *
     * @return if autoDestroy is enabled (default false)
     */
    public boolean getAutoStop() {
        return autoStop;
    }

    /**
     * Sets whether the SongPlayer is going to stop playback when no one is listening
     *
     * @param autoStop if autoStop is enabled
     */
    public void setAutoStop(boolean autoStop) {
        lock.lock();
        try {
            this.autoStop = autoStop;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Plays the Song for the specific player
     *
     * @param player to play this SongPlayer for
     * @param tick   to play at
     */
    public abstract void playTick(Player player, int tick);

    /**
     * SongPlayer will destroy itself
     *
     * @deprecated Stop playback and set tick to -1. It doesn't really destroy anything anymore.
     */
    public void destroy() {
        SongDestroyingEvent event = new SongDestroyingEvent(this);
        plugin.doSync(() -> Bukkit.getPluginManager().callEvent(event));
        if (event.isCancelled()) return;
        playing = false;
        setTick((short) -1);
    }

    /**
     * Returns whether the SongPlayer is actively playing
     *
     * @return is playing
     */
    public boolean isPlaying() {
        return playing;
    }

    /**
     * Sets whether the SongPlayer is playing
     */
    public void setPlaying(boolean playing) {
        setPlaying(playing, null);
    }

    /**
     * Sets whether the SongPlayer is playing and whether it should fade if previous value was different
     */
    public void setPlaying(boolean playing, boolean fade) {
        setPlaying(playing, fade ? (playing ? fadeIn : fadeOut) : null);
    }

    public void setPlaying(boolean playing, Fade fade) {
        if (this.playing == playing) return;
        this.playing = playing;
        if (fade != null && fade.getType() != FadeType.NONE) {
            fadeTemp = new Fade(fade.getType(), fade.getFadeDuration());
            fadeTemp.setFadeStart(playing ? 0 : volume);
            fadeTemp.setFadeTarget(playing ? volume : 0);
            fading = true;
        } else {
            fading = false;
            fadeTemp = null;
            volume = fadeIn.getFadeTarget();
            if (!playing) {
                SongStoppedEvent event = new SongStoppedEvent(this);
                plugin.doSync(() -> Bukkit.getPluginManager().callEvent(event));
            }
        }
        if (playing) run();
    }

    /**
     * Gets the current tick of this SongPlayer
     */
    public short getTick() {
        return tick;
    }

    /**
     * Sets the current tick of this SongPlayer
     */
    public void setTick(short tick) {
        this.tick = tick;
    }

    /**
     * Removes a player from this SongPlayer
     *
     * @param player to remove
     */
    public void removePlayer(Player player) {
        removePlayer(player.getUniqueId());
    }

    /**
     * Removes a player from this SongPlayer
     *
     * @param uuid of player to remove
     */
    public void removePlayer(UUID uuid) {
        lock.lock();
        try {
            playerList.remove(uuid);
            if (NoteBlockAPI.getSongPlayersByPlayer(uuid) == null) return;
            ArrayList<SongPlayer> songs = new ArrayList<>(NoteBlockAPI.getSongPlayersByPlayer(uuid));
            songs.remove(this);
            NoteBlockAPI.setSongPlayersByPlayer(uuid, songs);
            if (playerList.isEmpty() && autoStop) {
                SongEndEvent event = new SongEndEvent(this);
                plugin.doSync(() -> Bukkit.getPluginManager().callEvent(event));
                setPlaying(false);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Gets the current volume of this SongPlayer
     *
     * @return volume (0-100)
     */
    public byte getVolume() {
        return volume;
    }

    /**
     * Sets the current volume of this SongPlayer
     *
     * @param volume (0-100)
     */
    public void setVolume(byte volume) {
        if (volume > 100) volume = 100;
        else if (volume < 0) volume = 0;
        this.volume = volume;
        fadeIn.setFadeTarget(volume);
        fadeOut.setFadeStart(volume);
        if (fadeTemp != null) {
            if (playing) fadeTemp.setFadeTarget(volume);
            else fadeTemp.setFadeStart(volume);
        }
    }

    /**
     * Gets the Song being played by this SongPlayer
     */
    public Song getSong() {
        return song;
    }

    /**
     * Gets the Playlist being played by this SongPlayer
     */
    public Playlist getPlaylist() {
        return playlist;
    }

    /**
     * Sets the Playlist being played by this SongPlayer. Will affect next Song
     */
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    /**
     * Get index of actually played {@link Song} in {@link Playlist}
     */
    public int getPlayedSongIndex() {
        return actualSong;
    }

    /**
     * Start playing {@link Song} at specified index in {@link Playlist}
     * If there is no {@link Song} at this index, {@link SongPlayer} will continue playing current song
     */
    public void playSong(int index) {
        lock.lock();
        try {
            if (playlist.exist(index)) {
                song = playlist.get(index);
                actualSong = index;
                tick = -1;
                fadeIn.setFadeDone(0);
                fadeOut.setFadeDone(0);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Start playing {@link Song} that is next in {@link Playlist} or random {@link Song} from {@link Playlist}
     */
    public void playNextSong() {
        lock.lock();
        try {
            tick = song.getLength();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Gets the SoundCategory of this SongPlayer
     *
     * @return SoundCategory of this SongPlayer
     * @see SoundCategory
     */
    public SoundCategory getCategory() {
        return soundCategory;
    }

    /**
     * Sets the SoundCategory for this SongPlayer
     */
    public void setCategory(SoundCategory soundCategory) {
        this.soundCategory = soundCategory;
    }

    /**
     * Sets SongPlayer's {@link RepeatMode}
     */
    public void setRepeatMode(RepeatMode repeatMode) {
        this.repeat = repeatMode;
    }

    /**
     * Gets SongPlayer's {@link RepeatMode}
     */
    public RepeatMode getRepeatMode() {
        return repeat;
    }

    /**
     * Sets whether the SongPlayer will choose next song from player randomly
     */
    public void setRandom(boolean random) {
        this.random = random;
    }

    /**
     * Gets whether the SongPlayer will choose next song from player randomly
     *
     * @return is random
     */
    public boolean isRandom() {
        return random;
    }

    public ChannelMode getChannelMode() {
        return channelMode;
    }
}