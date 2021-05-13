package de.kiridevs.kiricore.managers;

import de.kiridevs.kiricore.annotations.PublicAPI;
import de.kiridevs.kiricore.events.ChangeAllowPvpStatusEvent;
import org.bukkit.Bukkit;

import java.util.ArrayList;

/**
 * A static class used to manage the current state of PVP
 * (enabled or disabled)
 */
public class PvpManager {
    /**
     * A list including the strings that are recognized
     * as arguments for /pvp to allow PVP
     */
    public static final ArrayList<String> enableStrings = new ArrayList<>()
    {{ add("y"); add("on"); add("yes"); add("true"); }};

    /**
     * A list including the strings that are recognized
     * as arguments for /pvp to disallow PVP
     */
    public static final ArrayList<String> disableStrings = new ArrayList<>()
    {{ add("n"); add("no"); add("off"); add("false"); }};

    private static boolean doAllowPvp = true;

    /**
     * Allows checking for the current state of PVP
     *
     * @return The current state of whether PVP is allowed or not.
     */
    @PublicAPI public static boolean getAllowPvp() {
        return doAllowPvp;
    }

    /**
     * Allows changing of the current PVP status.
     * Also fires a ChangeAllowPvpStatusEvent
     *
     * @param doAllowPvp The new state to set PVP to
     */
    @PublicAPI public static void setAllowPvp(boolean doAllowPvp) {
        // Calling an event
        ChangeAllowPvpStatusEvent event = new ChangeAllowPvpStatusEvent
                                          (doAllowPvp);
        Bukkit.getPluginManager().callEvent(event);

        // Changing the local variable
        PvpManager.doAllowPvp = doAllowPvp;
    }
}
