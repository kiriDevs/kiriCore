package de.kirimcdev.minecraft.plugins.kiricore.commands;

import de.kirimcdev.minecraft.plugins.kiricore.main.Methods;
import de.kirimcdev.minecraft.plugins.kiricore.main.Vars;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDisAfk implements CommandExecutor {
    public boolean onCommand(CommandSender cmdSender, Command cmd, String label, String[] args) {
        if (cmdSender.hasPermission("kiri.core.afk.check")) {
            if (args.length == 1) {
                Player toCheck = Bukkit.getPlayer(args[0]);
                if (!(toCheck == null)) {
                    String checkResult = Methods.genPrefix("kiriCore", cmdSender, "a", "a");
                    checkResult += "The player §e" + toCheck.getDisplayName();
                    if (Vars.afkList.contains(toCheck.getName())) {
                        checkResult += "§2 is";
                    } else {
                        checkResult += "§4 isn't";
                    }
                    checkResult += "§a marked as AFK at the moment.";
                    cmdSender.sendMessage(checkResult);
                } else {
                    String msg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
                    msg += "The player §e" + args[0] + "§c is not on this server at the moment.";
                    cmdSender.sendMessage(msg);
                }
            } else {
                String msg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
                msg += Vars.wrongSyntax + "isafk <Player>";
                cmdSender.sendMessage(msg);
            }
        } else {
            String msg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
            msg += Vars.noPermission;
            cmdSender.sendMessage(msg);
        }
        return true;
    }
}
