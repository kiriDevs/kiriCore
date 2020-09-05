package de.kirimcdev.minecraft.plugins.kiricore.commands;

import de.kirimcdev.minecraft.plugins.kiricore.main.Methods;
import de.kirimcdev.minecraft.plugins.kiricore.main.Vars;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CMDafkList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender cmdSender, Command cmd, String label, String[] args) {
        if (cmdSender.hasPermission("kiri.core.afk.list")) {
            if (args.length == 0) {
                if (!Vars.afkList.isEmpty()) {
                    String listString;
                    if (cmdSender.hasPermission("kiri.core.afk.list.vanish")) {

                        StringBuilder listBuilder = new StringBuilder();
                        for (String afkName : Vars.afkList) {
                            if (!(Vars.afkList.indexOf(afkName) == 0)) {
                                listBuilder.append(", ");
                            }
                            Player player = Bukkit.getPlayer(afkName);
                            String displayName = player.getDisplayName();
                            listBuilder.append(displayName);
                        }
                        listString = listBuilder.toString();

                    } else {

                        ArrayList<String> visibleAfks = new ArrayList<>();
                        for (String afkPlayerName : Vars.afkList) {
                            Player afkPlayer = Bukkit.getPlayer(afkPlayerName);
                            if (!Methods.getIsVanish(afkPlayer)) {
                                visibleAfks.add(afkPlayerName);
                            }
                        }

                        if (!visibleAfks.isEmpty()) {
                            StringBuilder listBuilder = new StringBuilder();
                            for (String afkName : visibleAfks) {
                                if (!(visibleAfks.indexOf(afkName) == 0)) {
                                    listBuilder.append(", ");
                                }
                                Player player = Bukkit.getPlayer(afkName);
                                String displayName = player.getDisplayName();
                                listBuilder.append(displayName);
                            }
                            listString = listBuilder.toString();
                        } else {
                            String fakeNoneAfkMsg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
                            fakeNoneAfkMsg += Vars.noOneAfk;
                            cmdSender.sendMessage(fakeNoneAfkMsg);
                            return true;
                        }

                    }

                    String opening = Methods.genPrefix("kiriCore", cmdSender, "a", "a");
                    opening += Vars.theseAreAfk;

                    cmdSender.sendMessage("");
                    cmdSender.sendMessage(opening);
                    cmdSender.sendMessage(listString);
                    cmdSender.sendMessage("");

                } else {
                    String noOneAfkMsg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
                    noOneAfkMsg += Vars.noOneAfk;
                    cmdSender.sendMessage(noOneAfkMsg);
                }
            } else {
                String usageMsg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
                usageMsg += Vars.wrongSyntax;
                usageMsg += "afklist";
                cmdSender.sendMessage(usageMsg);
            }
        } else {
            String permMsg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
            permMsg += Vars.noPermission;
            cmdSender.sendMessage(permMsg);
        }
        return true;
    }
}

// kiri.core.afk.list
// kiri.core.afklist.listvanish

/*
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

                    StringBuilder playerList = new StringBuilder("§e");
                    for (String playerName : Vars.afkList) {
                        Player player = Bukkit.getPlayer(playerName);
                        if (Methods.getIsVanish(player)) {
                            if (cmdSender.hasPermission("kiri.core.afklist.listvanish")) {
                                playerList.append(player.getDisplayName());
                                if (!(Vars.afkList.indexOf(playerName) == Vars.afkList.size() - 1)) {
                                    playerList.append("§r§a, §r");
                                }
                            }
                            continue;
                        }

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
*/