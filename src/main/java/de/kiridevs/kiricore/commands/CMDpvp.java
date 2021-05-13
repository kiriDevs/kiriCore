package de.kiridevs.kiricore.commands;

import de.kiridevs.kiricore.managers.MessageService;
import de.kiridevs.kiricore.managers.PvpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CMDpvp implements CommandExecutor {
    MessageService messageService;
    public CMDpvp(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public boolean onCommand(
        @NotNull CommandSender cmdSender,
        @NotNull Command cmd,
        @NotNull String label,
        @NotNull String[] args
    ) {
        if (args.length == 0) {
            // No arguments: Query current PVP status
            if (!(cmdSender.hasPermission("kiri.core.pvp.query"))) {
                // No permission
                ArrayList<String> completion = new ArrayList<>();
                completion.add("kiri.core.pvp.query");
                messageService.sendErrorMessage(
                    cmdSender,
                    "noperm",
                    completion
                );
                return true; // End execution
            }

            // Send message about the current PVP status
            if (PvpManager.getAllowPvp()) {
                messageService.sendInfoMessage(cmdSender,
                    "PVP is currently enabled."
                );
            } else {
                messageService.sendInfoMessage(cmdSender,
                    "PVP is currently disabled."
                );
            }

            return true;
        } else if (args.length == 1) {
            // 1 argument: Change the current PVP status
            if (!(cmdSender.hasPermission("kiri.core.pvp.modify"))) {
                // No permission
                ArrayList<String> completion = new ArrayList<>();
                completion.add("kiri.core.pvp.modify");
                messageService.sendErrorMessage(
                    cmdSender,
                    "noperm",
                    completion
                );
                return true; // End execution
            }

            // Change the PVP status
            if (PvpManager.enableStrings.contains(args[0])) {
                PvpManager.setAllowPvp(true); // Enable PVP
                return true;
            } else if (PvpManager.disableStrings.contains(args[0])) {
                PvpManager.setAllowPvp(false); // Disable PVP
                return true;
            } // If none apply: Fall through to 'Handle bad syntax' below
        }

        // Handle bad syntax
        ArrayList<String> completion = new ArrayList<>();
        String correctSyntax = "/pvp";
        completion.add("/pvp [(y/yes/on/true) / (n/no/off/false)]");
        messageService.sendErrorMessage(cmdSender, "badsyntax", completion);

        return true;
    }
}
