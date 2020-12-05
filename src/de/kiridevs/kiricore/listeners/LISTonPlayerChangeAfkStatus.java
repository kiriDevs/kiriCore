package de.kiridevs.kiricore.listeners;

import de.kiridevs.kiricore.events.PlayerChangeAfkStatusEvent;
import de.kiridevs.kiricore.main.Main;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LISTonPlayerChangeAfkStatus implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    public void onPlayerChangeAfkStatus(PlayerChangeAfkStatusEvent event) {
        Server server = Main.server;

        String playerName = event.getPlayer().getDisplayName();
        boolean isAfkNow = event.getNewAfkState();

        String broadcast;
        if (isAfkNow) { broadcast = Main.broadcastPrefix.console + "§r§3" + playerName + "§r§b " + "is now AFK!"; }
        else { broadcast = Main.broadcastPrefix.console + "§r§3" + playerName + "§r§b " + "is no longer AFK!"; }
        Main.server.broadcastMessage(broadcast);

    }

}
