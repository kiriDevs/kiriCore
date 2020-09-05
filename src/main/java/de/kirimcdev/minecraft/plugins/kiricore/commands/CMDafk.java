package de.kirimcdev.minecraft.plugins.kiricore.commands;

import de.kirimcdev.minecraft.plugins.kiricore.main.Methods;
import de.kirimcdev.minecraft.plugins.kiricore.main.Vars;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDafk implements CommandExecutor {

    public boolean onCommand(CommandSender cmdSender, Command cmd, String label, String[] args) {
        if (cmdSender.hasPermission("kiri.core.afk")) {
            if (args.length == 0) {
                if (cmdSender instanceof Player) {
                    Player player = (Player) cmdSender;
                    Methods.toggleAfk(player);
                } else {
                    String msg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
                    msg += Vars.playersOnly;
                    cmdSender.sendMessage(msg);
                }
            } else if (args.length == 1) {
                if (cmdSender.hasPermission("kiri.core.afk.others")) {
                    Player toAfk = Bukkit.getPlayer(args[0]);
                    if (toAfk != null && !Methods.getIsVanish(toAfk)) {
                        Methods.toggleAfk(toAfk);
                    } else if (toAfk != null && cmdSender.hasPermission("kiri.core.afk.others.vanish")) {
                        Methods.toggleAfk(toAfk);
                    } else {
                        String msg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
                        msg += Vars.notOnline(args[0], "a");
                        cmdSender.sendMessage(msg);
                    }
                } else {
                    String msg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
                    msg += Vars.noPermission;
                    cmdSender.sendMessage(msg);
                }
            } else {
                String msg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
                msg += Vars.wrongSyntax + "afk [Player]";
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