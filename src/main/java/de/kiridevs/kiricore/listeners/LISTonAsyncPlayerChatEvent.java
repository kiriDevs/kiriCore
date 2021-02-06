package de.kiridevs.kiricore.listeners;

import de.kiridevs.kiricore.main.Main;
import de.kiridevs.kiricore.managers.AfkManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class LISTonAsyncPlayerChatEvent implements Listener {
    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (!(AfkManager.isAfk(player))) {
            return;
        } // Skip all events where the player isn't AFK to enhance speed

        // Cancel event
        // "player is back" msg should be sent before the actual chat message
        event.setCancelled(true);

        if (event.isAsynchronous()) {
            Main plugin = Main.getKiriCore();
            // Asynchronous event (player spoke themselves)

            // Run sync method AfkManager.markBack(player) from async context
            // This is needed as another event is fired
            Bukkit.getScheduler().callSyncMethod(plugin, () -> {
                AfkManager.markBack(player);
                return null;
            });
        } else {
            // Synchronous event (other plugin forced player to speak)
            AfkManager.markBack(player);
        }
        // Un-cancel event so the players message is sent
        event.setCancelled(false);
    }
}
