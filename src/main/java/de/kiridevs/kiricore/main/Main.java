package de.kiridevs.kiricore.main;

import de.kiridevs.kiricore.Prefix;
import de.kiridevs.kiricore.commands.*;
import de.kiridevs.kiricore.listeners.*;
import de.kiridevs.kiricore.managers.MessageService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {
    private static Main plugin;
    public  static Main getKiriCore() { return plugin; }

    // MessageService creation
    private final Map<String, Prefix> PREFIX_MAP = new HashMap<>() {
        @Serial
        private static final long serialVersionUID = 1200105127832529178L; {
        put("success",  new Prefix("kiriCore", "2", "a")); // light green
        put("error",    new Prefix("kiriCore", "2", "c")); // light red
        put("info",     new Prefix("kiriCore", "2", "b")); // light aqua
    }};

    private final Map<String, String> MESSAGE_PRESETS = new HashMap<>() {
        @Serial
        private static final long serialVersionUID = -3174899576421267075L; {
        put("noperm"         , "Sorry, you don't have permission " +
                               "to do that! ({0})");
        put("playersonly"    , "Sorry, only a player can use that command!");
        put("badsyntax"      , "Sorry, please use the command like this: " +
                               "§e{0}");
        put("playernotonline", "Sorry, the player §r§e{0}§r§c is " +
                               "not online at the moment!");
        put("nowafkinfo"     , "§r§3{0}§r§b is now AFK!");
        put("nolongerafkinfo", "§r§3{0}§r§b is no longer AFK!");
    }};
    private final MessageService MSG_SER = new MessageService(
            PREFIX_MAP,
            MESSAGE_PRESETS);



    @SuppressWarnings("ConstantConditions")
    @Override
    public void onEnable() {
        plugin = this; // Initializing the plugin singleton

        // Command registration
        getCommand("afk"    ).setExecutor(new CMDafk    (MSG_SER));
        getCommand("pvp"    ).setExecutor(new CMDpvp    (MSG_SER));
        getCommand("isafk"  ).setExecutor(new CMDisAfk  (MSG_SER));
        getCommand("rename" ).setExecutor(new CMDrename (MSG_SER));
        getCommand("afklist").setExecutor(new CMDafkList(MSG_SER));

        // Listener registration
        Bukkit.getPluginManager().registerEvents(
                new LISTonPlayerLeave(),
                this
        );

        Bukkit.getPluginManager().registerEvents(
                new LISTonPlayerMoveEvent(),
                this
        );

        Bukkit.getPluginManager().registerEvents(
                new LISTonAsyncPlayerChatEvent(),
                this
        );

        Bukkit.getPluginManager().registerEvents(
                new LISTonPvp(MSG_SER),
                this
        );

        // Register listeners to own events
        Bukkit.getPluginManager().registerEvents(
                new LISTonPlayerChangeAfkStatus(MSG_SER),
                this
        );

        Bukkit.getPluginManager().registerEvents(
                new LISTonChangePvpStatusEvent(MSG_SER),
                this
        );
    }
}
