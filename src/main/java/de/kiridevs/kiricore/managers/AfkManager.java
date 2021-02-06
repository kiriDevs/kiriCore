package de.kiridevs.kiricore.managers;

import de.kiridevs.kiricore.annotations.PublicAPI;
import de.kiridevs.kiricore.events.PlayerChangeAfkStatusEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * A class with only static methods used to manage the AFK status of players
 * using kiriCore's AFK system
 */
@SuppressWarnings("UnusedReturnValue")
public class AfkManager {
    @SuppressWarnings("FieldMayBeFinal")
    private static ArrayList<String> afkList = new ArrayList<>();

    // Methods for checking status
    /** Allows quick checking of the status of a player using the player's name
     * @param playername The name (not display name) of the player
     *        to check the status of
     * @return true when the player IS marked as AFK right now
     *         false when the player is NOT marked as AFK right now
     */
    @PublicAPI public static boolean isAfk(String playername) {
      return afkList.contains(playername);
    }

    /**
     * Allows quick checking of the status of a player using the player's
     * Player object
     *
     * @param player The Player element of the player to check the status of
     * @return true when the player IS marked as AFK right now
     *         false when the player is NOT marked as AFK right now
     */
    @PublicAPI public static boolean isAfk(Player player) {
      return afkList.contains(player.getName());
    }


    // Methods for changing status

    /**
     * Marks a player as AFK
     *
     * @param player The player to mark as AFK
     * @return If the action was completed -
     *         true: Action completed
     *         false: Nothing changed - player was already AFK
     */
    public static boolean markAfk(Player player) {
        if (!isAfk(player)) {
            // Calling an event
            PlayerChangeAfkStatusEvent event = new PlayerChangeAfkStatusEvent
                                               (player, true);
            Bukkit.getPluginManager().callEvent(event);

            afkList.add(player.getName()); // Adding Player to the AFKList
            return true; // Action completed
        } else {
            return false; // Nothing changed - Player already AFK
        }
    }

    /**
     * Marks a player as back (no longer AFK)
     *
     * @param player The player to mark as back
     * @return If the action was completed -
     *         true: Action completed
     *         false: Nothing changed - player was not AFK
     */
    public static boolean markBack(Player player) {
        if (isAfk(player)) {
            // Calling an event
            PlayerChangeAfkStatusEvent event = new PlayerChangeAfkStatusEvent
                                               (player, false);
            Bukkit.getPluginManager().callEvent(event);

            afkList.remove(player.getName()); // Removing Player from AFKList
            return true; // Action completed
        } else {
            return false; // Nothing changed - Player wasn't AFK
        }
    }

    /**
     * Possibility to toggle the status of a Player
     * without knowing it in advance
     *
     * @param player The player to change the status of
     * @return The new AFK status -
     *         false: Player is not AFK,
     *         true: Player is AFK
     */
    public static boolean toggleAfk(Player player) {
        if (isAfk(player)) {
            markBack(player);
            return false; // New AFK status: false
        } else {
            markAfk(player);
            return true; // New AFK status: true
        }
    }

    /**
     * Getter for the full AFK list
     *
     * @return The current afkList including player names (not display names)
     *         of all players marked AFK at the moment
     */
    public static ArrayList<String> getAfkList() { return afkList; }
}
