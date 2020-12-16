package de.kiridevs.kiricore.managers;

import de.kiridevs.kiricore.events.PlayerChangeAfkStatusEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class AfkManager {
    @SuppressWarnings("FieldMayBeFinal")
    private static ArrayList<String> afkList = new ArrayList<>();


    // Methods for checking status
    @SuppressWarnings("unused")
    public static boolean isAfk(String playername) { return afkList.contains(playername); }
    public static boolean isAfk(Player player) { return afkList.contains(player.getName()); }


    // Methods for changing status
    @SuppressWarnings("UnusedReturnValue")
    public static boolean markAfk(Player player) {
        if (!isAfk(player)) {
            // Calling an event
            PlayerChangeAfkStatusEvent event = new PlayerChangeAfkStatusEvent(player, true);
            Bukkit.getPluginManager().callEvent(event);

            afkList.add(player.getName()); // Adding Player to the AFKList
            return true; // Action completed
        } else {
            return false; // Nothing changed - Player already AFK
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public static boolean markBack(Player player) {
        if (isAfk(player)) {
            // Calling an event
            PlayerChangeAfkStatusEvent event = new PlayerChangeAfkStatusEvent(player, false);
            Bukkit.getPluginManager().callEvent(event);

            afkList.remove(player.getName()); // Removing Player from the AFKList
            return true; // Action completed
        } else {
            return false; // Nothing changed - Player wasn't AFK
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public static boolean toggleAfk(Player player) {
        if (isAfk(player)) {
            markBack(player);
            return false; // New AFK status: false
        } else {
            markAfk(player);
            return true; // New AFK status: true
        }
    }

    public static ArrayList<String> getAfkList() { return afkList; }
}
