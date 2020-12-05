package de.kiridevs.kiricore.commands;

import de.kiridevs.kiricore.main.Messages;
import de.kiridevs.kiricore.managers.AfkManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class CMDAfk implements CommandExecutor {
    @Override
    public boolean onCommand(@Nonnull CommandSender cmdSender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        // Input validation
        if (!cmdSender.hasPermission("kiri.core.afk")) {
            cmdSender.sendMessage(Messages.noPerm(cmdSender, "kiri.core.afk"));
            return true;
        }

        if (args.length != 0) {
            cmdSender.sendMessage(Messages.badSyntax(cmdSender, "/afk"));
            return true;
        }

        if (!(cmdSender instanceof Player)) {
            cmdSender.sendMessage(Messages.playersOnly());
            return true;
        }

        // Command execution
        Player player = (Player) cmdSender;
        AfkManager.toggleAfk(player);
        return true;
    }
}
