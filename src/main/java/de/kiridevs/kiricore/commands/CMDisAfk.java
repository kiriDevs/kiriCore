package de.kiridevs.kiricore.commands;

import de.kiridevs.kiricore.managers.AfkManager;
import de.kiridevs.kiricore.managers.MessageService;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CMDisAfk implements CommandExecutor {
    MessageService messageService;
    public CMDisAfk(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender cmdSender,
            @NotNull Command cmd,
            @NotNull String label,
            @NotNull String[] args
    ) {
        // No permission
        if (!(cmdSender.hasPermission("kiri.core.afk.check"))) {
            ArrayList<String> completion = new ArrayList<>();
            completion.add("kiri.core.afk.check");

            messageService.sendErrorMessage(cmdSender, "noperm", completion);
            return true;
        }

        // Wrong usage
        if (!(args.length == 1)) {
            ArrayList<String> completion = new ArrayList<>();
            completion.add("/isafk <playername>");

            messageService.sendErrorMessage(cmdSender, "badsyntax", completion);
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);

        // Player does not exist or is not online
        if ((player == null) || (!(player.isOnline()))) {
            ArrayList<String> completion = new ArrayList<>();
            completion.add(args[0]);

            messageService.sendErrorMessage(cmdSender,
                                           "playernotonline",
                                           completion);
            return true;
        }

        // Everything is ok, proceed
        String msg = "Player §r§3" + player.getName() + "§r";

        if (AfkManager.isAfk(player)) { msg += "§2 IS §r§a"; }
        else { msg += "§4 IS NOT §r§a"; }

        msg += "AFK right now!";

        messageService.sendSuccessMessage(cmdSender, msg);
        return true;
    }
}
