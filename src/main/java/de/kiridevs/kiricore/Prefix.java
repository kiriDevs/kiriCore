package de.kiridevs.kiricore;

public class Prefix {
    public final String console;
    public final String player;

    public Prefix(String service, String serviceColor, String textColor) {
        console = "§8[§r§" + serviceColor + service + "§r§8]§r§" + textColor + " ";
        player = "§8[§r§l§" + serviceColor + service + "§r§8]§r§" + textColor + " ";
    }
}
