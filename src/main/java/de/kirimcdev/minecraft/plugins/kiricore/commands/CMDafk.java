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
            String nowAfk = "You are now marked as AFK.";
            String nowAfkPu = " is now AFK.";
            String noLongerAfk = "You are no longer marked as AFK.";
            String noLongerAfkPu = " is no longer AFK.";
            if (args.length == 0) {
                if (cmdSender instanceof Player) {
                    Player toAfk = (Player) cmdSender;
                    if (!Vars.afkList.contains(toAfk.getName())) {
                        Vars.afkList.add(toAfk.getName());

                        String publicMessage = Methods.genPrefix("kiriCore", Vars.console, "a", "a");
                        publicMessage += "§r" + toAfk.getDisplayName() + "§r§a" + nowAfkPu;
                        Vars.server.broadcastMessage(publicMessage);

                        String privateMessage = Methods.genPrefix("kiriCore", toAfk, "a", "a");
                        privateMessage += nowAfk;
                        toAfk.sendMessage(privateMessage);
                    } else {
                        Vars.afkList.remove(toAfk.getName());

                        String publicMessage = Methods.genPrefix("kiriCore", Vars.console, "a", "a");
                        publicMessage += "§r" + toAfk.getDisplayName() + "§r§a" + noLongerAfkPu;
                        Vars.server.broadcastMessage(publicMessage);

                        String privateMessage = Methods.genPrefix("kiriCore", toAfk, "a", "a");
                        privateMessage += noLongerAfk;
                        toAfk.sendMessage(privateMessage);
                    }
                } else {
                    String msg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
                    msg += Vars.playersOnly;
                    cmdSender.sendMessage(msg);
                }
            } else if (args.length == 1) {
                if (cmdSender.hasPermission("kiri.core.afk.others")) {
                    Player toAfk = Bukkit.getPlayer(args[0]);
                    if (!(toAfk == null)) {
                        if (!Vars.afkList.contains(toAfk.getName())) {
                            Vars.afkList.add(toAfk.getName());

                            String publicMessage = Methods.genPrefix("kiriCore", Vars.console, "a", "a");
                            publicMessage += "§r" + toAfk.getDisplayName() + "§r§a" + nowAfkPu;
                            Vars.server.broadcastMessage(publicMessage);

                            String privateMessage = Methods.genPrefix("kiriCore", toAfk, "a", "a");
                            privateMessage += nowAfk;
                            toAfk.sendMessage(privateMessage);
                        } else {
                            Vars.afkList.remove(toAfk.getName());

                            String publicMessage = Methods.genPrefix("kiriCore", Vars.console, "a", "a");
                            publicMessage += "§r" + toAfk.getDisplayName() + "§r§a" + noLongerAfkPu;
                            Vars.server.broadcastMessage(publicMessage);

                            String privateMessage = Methods.genPrefix("kiriCore", toAfk, "a", "a");
                            privateMessage += noLongerAfk;
                            toAfk.sendMessage(privateMessage);
                        }
                    } else {
                        String msg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
                        msg += "The player §e" + args[0] + "§c is not on this server at the moment.";
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