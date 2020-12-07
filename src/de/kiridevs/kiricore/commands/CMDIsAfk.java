package de.kiridevs.kiricore.commands;

import de.kiridevs.kiricore.main.Main;
import de.kiridevs.kiricore.main.Messages;
import de.kiridevs.kiricore.managers.AfkManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class CMDIsAfk implements CommandExecutor {
    @Override
    public boolean onCommand(@Nonnull CommandSender cmdSender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        // No permission
        if (!(cmdSender.hasPermission("kiri.core.afk.check"))) {
            cmdSender.sendMessage(Messages.noPerm(cmdSender, "kiri.core.afk.check"));
            return true;
        }

        // Wrong usage
        if (!(args.length == 1)) {
            cmdSender.sendMessage(Messages.badSyntax(cmdSender, "/isafk <Playername>"));
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);

        // Player does not exist or is not online
        if ((player == null) || (!(player.isOnline()))) {
            cmdSender.sendMessage(Messages.playerNotOnline(cmdSender, args[0]));
            return true;
        }

        // cmdSender DOES have permission
        // command WAS used correctly
        // affected player IS online
        String msg;

        if (cmdSender instanceof Player) { msg = Main.successPrefix.player; }
        else { msg = Main.successPrefix.console; }

        msg += "Player §r§3" + player.getName() + "§r";

        if (AfkManager.isAfk(player)) { msg += "§2 IS §r§a"; }
        else { msg += "§4 IS NOT §r§a"; }

        msg += "AFK right now!";

        cmdSender.sendMessage(msg);
        return true;
    }
}
