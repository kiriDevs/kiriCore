package de.kiridevs.kiricore.main;

import de.kiridevs.kiricore.Prefix;
import de.kiridevs.kiricore.commands.CMDafk;
import de.kiridevs.kiricore.commands.CMDafkList;
import de.kiridevs.kiricore.commands.CMDisAfk;
import de.kiridevs.kiricore.commands.CMDrename;
import de.kiridevs.kiricore.listeners.LISTonAsyncPlayerChatEvent;
import de.kiridevs.kiricore.listeners.LISTonPlayerChangeAfkStatus;
import de.kiridevs.kiricore.listeners.LISTonPlayerLeave;
import de.kiridevs.kiricore.listeners.LISTonPlayerMoveEvent;
import de.kiridevs.kiricore.managers.MessageService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused") // Class is used by Spigot
public class Main extends JavaPlugin {
    private static Main plugin;

    Prefix successPrefix = new Prefix("kiriCore", "2", "a");
    Prefix errorPrefix = new Prefix("kiriCore", "2", "c");
    Prefix infoPrefix = new Prefix("kiriCore", "2", "b");
    Map<String, Prefix> prefixMap = new HashMap<>();
    Map<String, String> messagePresets = new HashMap<>();
    MessageService messageService;

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onEnable() {
        plugin = this;

        // MessageService creation
        prefixMap.put("success", successPrefix);
        prefixMap.put("error", errorPrefix);
        prefixMap.put("info", infoPrefix);

        messagePresets.put("noperm", "Sorry, you don't have permission to do that! ({0})");
        messagePresets.put("playersonly", "Sorry, only a player can use that command!");
        messagePresets.put("badsyntax", "Sorry, please use the command like this: §e{0}");
        messagePresets.put("playernotonline", "Sorry, the player §r§e{0}§r§c is not online at the moment!");
        messagePresets.put("nowafk", "§r§3{0}§r§b is now AFK!");
        messagePresets.put("nolongerafk", "§r§3{0}§r§b is no longer AFK!");

        messageService = new MessageService(prefixMap, messagePresets);

        // Command registration
        getCommand("afk").setExecutor(new CMDafk(messageService));
        getCommand("isafk").setExecutor(new CMDisAfk(messageService));
        getCommand("rename").setExecutor(new CMDrename(messageService));
        getCommand("afklist").setExecutor(new CMDafkList(messageService));

        // Listener registration
        Bukkit.getPluginManager().registerEvents(new LISTonPlayerLeave(), this);
        Bukkit.getPluginManager().registerEvents(new LISTonPlayerMoveEvent(), this);
        Bukkit.getPluginManager().registerEvents(new LISTonAsyncPlayerChatEvent(), this);
        Bukkit.getPluginManager().registerEvents(new LISTonPlayerChangeAfkStatus(messageService), this);
    }

    public static Main getPlugin() { return plugin; }
}
