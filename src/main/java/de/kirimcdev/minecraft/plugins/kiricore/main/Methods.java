package de.kirimcdev.minecraft.plugins.kiricore.main;

import com.earth2me.essentials.User;
import de.kirimcdev.minecraft.plugins.kiricore.DummyException;
import org.bukkit.Location;
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
            Vars.console.sendMessage("§cIf you didn't change them, please report this to the author of that plugin.");
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

    public static boolean getIsAfk(Player toCheck) {
        return Vars.afkList.contains(toCheck.getName());
    }

    public static boolean getIsVanish(Player toCheck) {
        if (Vars.essentialsExists) {

            User userToCheck = Vars.essentials.getUser(toCheck);
            return userToCheck.isVanished();


        } else { return false; }
    }

    public static void markAfk(Player toAfk) {
        Vars.afkList.add(toAfk.getName());

        if (getIsVanish(toAfk)) {
            String msg = Methods.genPrefix("kiriCore", toAfk, "a", "a");
            msg += Vars.afkMarkVanish;
            toAfk.sendMessage(msg);
        } else {
            String msg = Methods.genPrefix("kiriCore", Vars.console, "a", "a");
            msg += Vars.playerAfk(toAfk.getDisplayName(), "a");
            Vars.server.broadcastMessage(msg);
        }
    }

    public static void unMarkAfk(Player toAfk) {
        Vars.afkList.remove(toAfk.getName());

        if (Methods.getIsVanish(toAfk)) {
            String msg = Methods.genPrefix("kiriCore", toAfk, "a", "a");
            msg += Vars.backMarkVanish;
            toAfk.sendMessage(msg);
        } else {
            String msg = Methods.genPrefix("kiriCore", Vars.console, "a", "a");
            msg += Vars.playerBack(toAfk.getDisplayName(), "a");
            Vars.server.broadcastMessage(msg);
        }

    }

    public static void toggleAfk(Player player) {
        if (getIsAfk(player)) {
            unMarkAfk(player);
        } else {
            markAfk(player);
        }
    }

    public static int compareLocations(Location first, Location second) { // inspired by Essentials Mechanism
        if (first == second) { return 2; } // highest similarity
        else if (first.getBlockX() == second.getBlockX() && first.getBlockZ() == second.getBlockZ() && first.getBlockY() == second.getBlockY()) {
            return 1; // slightly similar, as Position is equal, but Yaw and Pitch can't be the same anymore.
        }

        else { return 0; } // no similarity
    }

}
