package de.kiridevs.kiricore.commands;

import de.kiridevs.kiricore.managers.AfkManager;
import de.kiridevs.kiricore.managers.MessageService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class CMDafkList implements CommandExecutor {
    MessageService messageService;
    public CMDafkList(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender cmdSender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        if (!(cmdSender.hasPermission("kiri.core.afk.list"))) {
            ArrayList<String> completionList = new ArrayList<>();
            completionList.add("kiri.core.afk.list");
            messageService.sendErrorMessage(cmdSender, "noperm", completionList);
            return true;
        }

        if (args.length != 0) {
            ArrayList<String> completionList = new ArrayList<>();
            completionList.add("/afklist");
            messageService.sendErrorMessage(cmdSender, "badsyntax", completionList);
            return true;
        }

        if (AfkManager.getAfkList().size() == 0) {
            messageService.sendErrorMessage(cmdSender, "No one is marked as AFK at the moment!");
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
        messageService.sendInfoMessage(cmdSender, "These players are marked as AFK right now:");
        messageService.sendInfoMessage(cmdSender, listString);

        return true;
    }
}
