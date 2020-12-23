package de.kiridevs.kiricore.listeners;

import de.kiridevs.kiricore.managers.AfkManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LISTonPlayerLeave implements Listener {
    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        AfkManager.markBack(player);
    }
}
