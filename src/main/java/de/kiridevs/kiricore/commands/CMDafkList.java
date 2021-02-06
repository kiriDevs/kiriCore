package de.kiridevs.kiricore.commands;

import de.kiridevs.kiricore.managers.AfkManager;
import de.kiridevs.kiricore.managers.MessageService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CMDafkList implements CommandExecutor {
    MessageService messageService;
    public CMDafkList(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender cmdSender,
            @NotNull Command cmd,
            @NotNull String label,
            @NotNull String[] args
    ) {
        if (!(cmdSender.hasPermission("kiri.core.afk.list"))) {
            ArrayList<String> completion = new ArrayList<>();
            completion.add("kiri.core.afk.list");
            messageService.sendErrorMessage(cmdSender, "noperm", completion);
            return true;
        }

        if (args.length != 0) {
            ArrayList<String> completion = new ArrayList<>();
            completion.add("/afklist");
            messageService.sendErrorMessage(cmdSender, "badsyntax", completion);
            return true;
        }

        if (AfkManager.getAfkList().size() == 0) {
            messageService.sendErrorMessage(cmdSender,
                    "No one is marked as AFK at the moment!");
            return true;
        }

        StringBuilder listBuilder = new StringBuilder();
        boolean isFirstRun = true;
        for (String playerName : AfkManager.getAfkList()) {
            if (!isFirstRun) {
                listBuilder.append(", ");
            } else { isFirstRun = false; }

            listBuilder.append("§r§3");
            listBuilder.append(playerName);
            listBuilder.append("§r§b");
        }


        String listString = listBuilder.toString();
        messageService.sendInfoMessage(cmdSender,
                "These players are marked as AFK right now:");
        messageService.sendInfoMessage(cmdSender, listString);

        return true;
    }
}
