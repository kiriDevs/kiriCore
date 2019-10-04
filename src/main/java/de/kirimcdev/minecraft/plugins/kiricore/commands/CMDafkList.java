package de.kirimcdev.minecraft.plugins.kiricore.commands;

import de.kirimcdev.minecraft.plugins.kiricore.main.Methods;
import de.kirimcdev.minecraft.plugins.kiricore.main.Vars;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDafkList implements CommandExecutor {
    public boolean onCommand(CommandSender cmdSender, Command cmd, String label, String[] args) {
        if (cmdSender.hasPermission("kiri.core.afk.list")) {
            if (args.length == 0) {
                if (!(Vars.afkList.size() == 0)) {
                    String openingMessage = Methods.genPrefix("kiriCore", cmdSender, "a", "a");
                    openingMessage += "These players are marked as AFK at the moment:";
                    cmdSender.sendMessage(openingMessage);

                    StringBuilder playerList = new StringBuilder("§e");
                    for (String playerName : Vars.afkList) {
                        Player player = Bukkit.getPlayer(playerName);
                        playerList.append(player.getDisplayName());
                        if (!(Vars.afkList.indexOf(playerName) == Vars.afkList.size() - 1)) {
                            playerList.append("§r§a, §r");
                        }
                    }
                    cmdSender.sendMessage(playerList.toString());
                } else {
                    String msg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
                    msg += "Nobody is marked as AFK at the moment.";
                    cmdSender.sendMessage(msg);
                }
            } else {
                String msg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
                msg += Vars.wrongSyntax + "afklist";
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
