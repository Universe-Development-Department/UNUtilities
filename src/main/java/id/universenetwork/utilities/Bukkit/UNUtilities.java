package id.universenetwork.utilities.Bukkit;

import id.universenetwork.utilities.Bukkit.Enums.Features.MaxPlayerChangerCommand;
import id.universenetwork.utilities.Bukkit.Manager.Commands;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import id.universenetwork.utilities.Bukkit.Manager.Event;
import id.universenetwork.utilities.Bukkit.Manager.Hooks;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

import static id.universenetwork.utilities.Bukkit.Manager.API.ActionBarAPISetup;

public final class UNUtilities extends JavaPlugin {
    public static UNUtilities plugin;
    public static String prefix;
    public static Boolean aweHook = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        Config.setup();
        ActionBarAPISetup();
        Event.register();
        Commands.register();
        Hooks.AsyncWorldEditBossBarDisplay("enabling");
        Hooks.ShopGUIPlusSilkSpawnersConnector();
        Bukkit.getLogger().info("\n\n\n" +
                "§b██╗░░░██╗§e███╗░░██╗§9██╗░░░██╗████████╗██╗██╗░░░░░██╗████████╗██╗███████╗░██████╗\n" +
                "§b██║░░░██║§e████╗░██║§9██║░░░██║╚══██╔══╝██║██║░░░░░██║╚══██╔══╝██║██╔════╝██╔════╝\n" +
                "§b██║░░░██║§e██╔██╗██║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║█████╗░░╚█████╗░\n" +
                "§b██║░░░██║§e██║╚████║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║██╔══╝░░░╚═══██╗\n" +
                "§b╚██████╔╝§e██║░╚███║§9╚██████╔╝░░░██║░░░██║███████╗██║░░░██║░░░██║███████╗██████╔╝\n" +
                "§b░╚═════╝░§e╚═╝░░╚══╝§9░╚═════╝░░░░╚═╝░░░╚═╝╚══════╝╚═╝░░░╚═╝░░░╚═╝╚══════╝╚═════╝░\n\n" +
                "§d         █░█ ▄▀█ █▀   █▄▄ █▀▀ █▀▀ █▄░█   █▀▀ █▄░█ ▄▀█ █▄▄ █░░ █▀▀ █▀▄\n" +
                "§d         █▀█ █▀█ ▄█   █▄█ ██▄ ██▄ █░▀█   ██▄ █░▀█ █▀█ █▄█ █▄▄ ██▄ █▄▀\n\n\n");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (Config.MPCCSettings(MaxPlayerChangerCommand.SOR) && Config.MPCCSettings(MaxPlayerChangerCommand.ENABLED))
            updateServerProperties();
        Hooks.AsyncWorldEditBossBarDisplay("disabling");
        Bukkit.getLogger().info("\n\n\n" +
                "§b██╗░░░██╗§e███╗░░██╗§9██╗░░░██╗████████╗██╗██╗░░░░░██╗████████╗██╗███████╗░██████╗\n" +
                "§b██║░░░██║§e████╗░██║§9██║░░░██║╚══██╔══╝██║██║░░░░░██║╚══██╔══╝██║██╔════╝██╔════╝\n" +
                "§b██║░░░██║§e██╔██╗██║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║█████╗░░╚█████╗░\n" +
                "§b██║░░░██║§e██║╚████║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║██╔══╝░░░╚═══██╗\n" +
                "§b╚██████╔╝§e██║░╚███║§9╚██████╔╝░░░██║░░░██║███████╗██║░░░██║░░░██║███████╗██████╔╝\n" +
                "§b░╚═════╝░§e╚═╝░░╚══╝§9░╚═════╝░░░░╚═╝░░░╚═╝╚══════╝╚═╝░░░╚═╝░░░╚═╝╚══════╝╚═════╝░\n\n" +
                "§c        █░█ ▄▀█ █▀   █▄▄ █▀▀ █▀▀ █▄░█   █▀▄ █ █▀ ▄▀█ █▄▄ █░░ █▀▀ █▀▄\n" +
                "§c        █▀█ █▀█ ▄█   █▄█ ██▄ ██▄ █░▀█   █▄▀ █ ▄█ █▀█ █▄█ █▄▄ ██▄ █▄▀\n\n\n");
    }

    private void updateServerProperties() {
        Properties properties = new Properties();
        File propertiesFile = new File("server.properties");
        try {
            FileInputStream is = new FileInputStream(propertiesFile);
            try {
                properties.load(is);
            } catch (Throwable v10) {
                try {
                    is.close();
                } catch (Throwable v8) {
                    v10.addSuppressed(v8);
                }
                throw v10;
            }
            is.close();
            String maxPlayers = Integer.toString(getServer().getMaxPlayers());
            if (properties.getProperty("max-players").equals(maxPlayers)) return;
            System.out.println(prefix + " §6Saving max players to server.properties...");
            properties.setProperty("max-players", maxPlayers);
            FileOutputStream os = new FileOutputStream(propertiesFile);
            try {
                properties.store(os, "Minecraft server properties");
            } catch (Throwable v9) {
                try {
                    os.close();
                } catch (Throwable v7) {
                    v9.addSuppressed(v7);
                }
                throw v9;
            }
            os.close();
        } catch (IOException v11) {
            getLogger().log(Level.SEVERE, "Error while saving max players in server properties", v11);
        }
    }
}