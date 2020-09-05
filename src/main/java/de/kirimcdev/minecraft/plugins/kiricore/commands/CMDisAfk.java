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
                if (toCheck != null) {
                    if (!Methods.getIsVanish(toCheck) || Methods.getIsVanish(toCheck) && cmdSender.hasPermission("kiri.core.afk.check.vanish")) {
                        if (Vars.afkList.contains(toCheck.getName())) {
                            String msg = Methods.genPrefix("kiriCore", cmdSender, "a", "a");
                            msg += "The player §r";
                            msg += toCheck.getDisplayName();
                            msg += " §2IS §aAFK at the moment.";
                            cmdSender.sendMessage(msg);
                        } else {
                            String msg = Methods.genPrefix("kiriCore", cmdSender, "a", "a");
                            msg += "The player §r";
                            msg += toCheck.getDisplayName();
                            msg += " §4ISN'T §aAFK at the moment.";
                            cmdSender.sendMessage(msg);
                        }
                    } else {
                        String fakeNotOnline = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
                        fakeNotOnline += Vars.notOnline(args[0], "c");
                        cmdSender.sendMessage(fakeNotOnline);
                    }
                } else {
                    String notOnlineMsg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
                    notOnlineMsg += Vars.notOnline(args[0], "c");
                    cmdSender.sendMessage(notOnlineMsg);
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
