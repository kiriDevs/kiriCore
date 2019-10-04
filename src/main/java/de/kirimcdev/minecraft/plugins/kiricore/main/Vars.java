package de.kirimcdev.minecraft.plugins.kiricore.main;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;

public class Vars {
    public static Server server = Bukkit.getServer();
    public static ConsoleCommandSender console = server.getConsoleSender();

    public static ArrayList<String> afkList = new ArrayList<String>();

    static PluginManager pluginMngr = Bukkit.getPluginManager();

    public static String playersOnly = "This command can only be used by players.";
    public static String noPermission = "You don't have permission to use this command.";
    public static String wrongSyntax = "Please use the command as following: §e/";
}
