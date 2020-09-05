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
            if (args.length == 0) {
                String whoCleared;

                if (cmdSender instanceof Player) {
                    whoCleared = "§r" + ((Player) cmdSender).getDisplayName();
                } else {
                    whoCleared = "§r§4CONSOLE";
                }

                for (int i = 0; i < 100; i++) {
                    Vars.server.broadcastMessage("");
                }
                String msg = Methods.genPrefix("kiriCore", Vars.console, "a", "a");
                msg += Vars.chatCleared(whoCleared, "a");
                Vars.server.broadcastMessage(msg);
            } else {
                String syntaxMsg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
                syntaxMsg += Vars.wrongSyntax + "clearchat";
                cmdSender.sendMessage(syntaxMsg);
            }
        } else {
            String noPermMsg = Methods.genPrefix("kiriCore", cmdSender, "a", "c");
            noPermMsg += Vars.noPermission;
            cmdSender.sendMessage(noPermMsg);
        }
        return true;
    }
}
