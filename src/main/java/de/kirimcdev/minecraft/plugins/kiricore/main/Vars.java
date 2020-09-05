package de.kirimcdev.minecraft.plugins.kiricore.main;

import com.earth2me.essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;

public class Vars {

    // Server stuff
    public static Server server = Bukkit.getServer();
    public static ConsoleCommandSender console = server.getConsoleSender();
    public static PluginManager pluginMngr = Bukkit.getPluginManager();

    // Essentials Hook
    public static Essentials essentials = (Essentials) pluginMngr.getPlugin("Essentials");
    public static boolean essentialsExists = essentials != null;

    // AFK function
    public static ArrayList<String> afkList = new ArrayList<>();

    // Strings
    public static final String playersOnly = "This command can only be used by players.";
    public static final String noPermission = "You don't have permission to use this command.";
    public static final String wrongSyntax = "Please use the command as following: §e/";
    public static final String noOneAfk = "No players are marked as AFK at the moment.";
    public static final String theseAreAfk = "These players are marked as AFK at the moment:";

    public static final String afkMarkVanish = "You were marked as AFK, but since you are vanished, I won't tell anyone.";
    public static final String backMarkVanish = "You were marked as back, but since you are vanished, I won't tell anyone.";

    public static String playerAfk(String name, String msgColor) { return "§r" + name + "§" + msgColor + " is now AFK!"; }
    public static String playerBack(String name, String msgColor) { return "§r" + name + "§" + msgColor + " is no longer AFK!"; }

    public static String chatCleared(String by, String msgColor) { return "The chat was cleared by §r" + by + "§" + msgColor + "."; }

    public static String notOnline(String name, String msgColor) { return "The player §e" + name + "§" + msgColor + " is not on this server at the moment."; }
}
