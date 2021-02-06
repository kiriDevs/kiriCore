package de.kiridevs.kiricore.commands;

import de.kiridevs.kiricore.managers.AfkManager;
import de.kiridevs.kiricore.managers.MessageService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CMDafk implements CommandExecutor {
    MessageService messageService;
    public CMDafk(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender cmdSender,
            @NotNull Command cmd,
            @NotNull String label,
            @NotNull String[] args
    ) {
        // Input validation
        if (!cmdSender.hasPermission("kiri.core.afk")) {
            ArrayList<String> completion = new ArrayList<>();
            completion.add("kiri.core.afk");

            messageService.sendErrorMessage(cmdSender, "noperm", completion);
            return true;
        }

        if (args.length != 0) {
            ArrayList<String> completion = new ArrayList<>();
            completion.add("/afk");

            messageService.sendErrorMessage(cmdSender, "badsyntax", completion);
            return true;
        }

        if (!(cmdSender instanceof Player)) {
            messageService.sendErrorMessage(cmdSender, "playersonly", null);
            return true;
        }

        // Command execution
        Player player = (Player) cmdSender;
        AfkManager.toggleAfk(player);
        return true;
    }
}
