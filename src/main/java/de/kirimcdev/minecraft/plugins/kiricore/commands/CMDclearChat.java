package de.kirimcdev.minecraft.plugins.kiricore.commands;

import de.kirimcdev.minecraft.plugins.kiricore.main.Methods;
import de.kirimcdev.minecraft.plugins.kiricore.main.Vars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDclearChat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cmdSender, Command cmd, String label, String[] args) {
        if (cmdSender.hasPermission("kiri.core.clearchat")) {
            for (int i = 0; i < 100; i++) {
                Vars.server.broadcastMessage("");
            }
            String closingMsg = Methods.genPrefix("kiriCore", Vars.console, "a", "a");
            closingMsg += "The chat was cleared by §r";
            if (cmdSender instanceof Player) {
                Player player = (Player) cmdSender;
                closingMsg += player.getDisplayName() + "§r§a.";
            } else {
                closingMsg += "§4CONSOLE" + "§a.";
            }
            Vars.server.broadcastMessage(closingMsg);
        } else {
            String msg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
            msg += Vars.noPermission;
            cmdSender.sendMessage(msg);
        }
        return true;
    }
}
