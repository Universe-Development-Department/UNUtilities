package com.xxmicloxx.NoteBlockAPI.songplayer;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import com.xxmicloxx.NoteBlockAPI.event.PlayerRangeStateChangeEvent;
import com.xxmicloxx.NoteBlockAPI.model.*;
import com.xxmicloxx.NoteBlockAPI.utils.CompatibilityUtils;
import com.xxmicloxx.NoteBlockAPI.utils.InstrumentUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * SongPlayer created at a specified NoteBlock
 */
public class NoteBlockSongPlayer extends RangeSongPlayer {
    Block noteBlock;

    public NoteBlockSongPlayer(Song song) {
        super(song);
    }

    public NoteBlockSongPlayer(Song song, SoundCategory soundCategory) {
        super(song, soundCategory);
    }

    public NoteBlockSongPlayer(Playlist playlist, SoundCategory soundCategory) {
        super(playlist, soundCategory);
    }

    public NoteBlockSongPlayer(Playlist playlist) {
        super(playlist);
    }

    /**
     * Get the Block this SongPlayer is played at
     *
     * @return Block representing a NoteBlock
     */
    public Block getNoteBlock() {
        return noteBlock;
    }

    /**
     * Set the Block this SongPlayer is played at
     */
    public void setNoteBlock(Block noteBlock) {
        this.noteBlock = noteBlock;
    }

    @Override
    public void playTick(Player player, int tick) {
        if (noteBlock.getType() != CompatibilityUtils.getNoteBlockMaterial()) return;
        // not in same world
        if (!player.getWorld().getName().equals(noteBlock.getWorld().getName())) return;
        byte playerVolume = NoteBlockAPI.getPlayerVolume(player);
        Location loc = noteBlock.getLocation();
        loc = new Location(loc.getWorld(), loc.getX() + 0.5f, loc.getY() - 0.5f, loc.getZ() + 0.5f);
        for (Layer layer : song.getLayerHashMap().values()) {
            Note note = layer.getNote(tick);
            if (note == null) continue;
            int key = note.getKey() - 33;
            while (key < 0) key += 12;
            while (key > 24) key -= 12;
            player.playNote(loc, InstrumentUtils.getBukkitInstrument(note.getInstrument()), new org.bukkit.Note(key));
            float volume = ((layer.getVolume() * (int) this.volume * (int) playerVolume * note.getVelocity()) / 100_00_00_00F) * ((1F / 16F) * getDistance());
            channelMode.play(player, loc, song, layer, note, soundCategory, volume, !enable10Octave);
            if (isInRange(player)) {
                if (!this.playerList.get(player.getUniqueId())) {
                    playerList.put(player.getUniqueId(), true);
                    plugin.doSync(() -> Bukkit.getPluginManager().callEvent(new PlayerRangeStateChangeEvent(this, player, true)));
                }
            } else {
                if (this.playerList.get(player.getUniqueId())) {
                    playerList.put(player.getUniqueId(), false);
                    plugin.doSync(() -> Bukkit.getPluginManager().callEvent(new PlayerRangeStateChangeEvent(this, player, false)));
                }
            }
        }
    }

    /**
     * Returns true if the Player is able to hear the current NoteBlockSongPlayer
     *
     * @param player in range
     * @return ability to hear the current NoteBlockSongPlayer
     */
    @Override
    public boolean isInRange(Player player) {
        Location loc = noteBlock.getLocation();
        loc = new Location(loc.getWorld(), loc.getX() + 0.5f, loc.getY() - 0.5f, loc.getZ() + 0.5f);
        return !(player.getLocation().distance(loc) > getDistance());
    }
}