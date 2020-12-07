package de.kiridevs.kiricore.main;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Messages {
    private static final String noPermString = "Sorry, you don't have permission to do that!";
    private static final String playersOnlyString = "Sorry, only a player can use that command!";
    private static final String badSyntaxString = "Sorry, please use the command like this: §e";
    private static final String playerNotOnlineString = "Sorry, the player §r§e{name}§r§c is not online at the moment!";

    public static String noPerm(CommandSender recipient, String neededPerm) {
        String message;

        if (recipient instanceof Player) { message = Main.errorPrefix.player; }
        else { message = Main.errorPrefix.console; }

        message += noPermString + " " + "(" + neededPerm + ")";
        return message;
    }

    public static String playersOnly() { return Main.errorPrefix.console + playersOnlyString; }
    public static String badSyntax(CommandSender recipient, String correctSyntax) {
        String message;

        if (recipient instanceof Player) { message = Main.errorPrefix.player; }
        else { message = Main.errorPrefix.console; }

        message += badSyntaxString + correctSyntax;
        return message;
    }

    public static String playerNotOnline(CommandSender recipient, String playerName) {
        String message;

        if (recipient instanceof Player) { message = Main.errorPrefix.player; }
        else { message = Main.errorPrefix.console; }

        String customPlayersOnlyString = playerNotOnlineString.replace("{name}", playerName);
        message += customPlayersOnlyString;
        return message;
    }
}
