package de.kirimcdev.minecraft.plugins.kiricore.main;

import de.kirimcdev.minecraft.plugins.kiricore.DummyException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Methods {

    @SuppressWarnings("WeakerAccess")
    public static void logError(String service, String phase, Exception excep) {
        if (phase.contains("config") && excep instanceof DummyException) {
            Vars.console.sendMessage("");
            Vars.console.sendMessage("§c=====kiriCore ErrorReport================");
            Vars.console.sendMessage("§e" + service + "§c asked me to tell you that something is wrong with it's configs.");
            Vars.console.sendMessage("§cPlease check any changes on the config files again.");
            Vars.console.sendMessage("§cIf you they weren't changed, please report this to the author of that plugin.");
            Vars.console.sendMessage("§c=====End of kiriCore ErrorReport=========");
            Vars.console.sendMessage("");
        } else {
            Vars.console.sendMessage("");
            Vars.console.sendMessage("§c=====kiriCore ErrorReport================");
            Vars.console.sendMessage("§e" + service + "§c failed to complete the following task: §e" + phase );
            Vars.console.sendMessage("excep.stacktrace:");
            Vars.console.sendMessage("");
            excep.printStackTrace();
            Vars.console.sendMessage("");
            Vars.console.sendMessage("§c The plugin §e" + service + "§c probably won't load properly.");
            Vars.console.sendMessage("§cPlease try restarting your server.");
            Vars.console.sendMessage("§cIf the error persists, please report it to the plugin developer!");
            Vars.console.sendMessage("§cPlease include this whole section in your error report.");
            Vars.console.sendMessage("§c=====End of kiriCore ErrorReport=========");
            Vars.console.sendMessage("");
        }
    }

    public static String genPrefix(String service, CommandSender recipient, String prefixColor, String messageColor) {
        String prefix = "§8[";
        if (recipient instanceof Player) {
            prefix += "§l";
        }
        prefix += "§" + prefixColor;
        prefix += service;
        prefix += "§r§8]";
        prefix += "§" + messageColor + " ";
        return prefix;
    }
}
