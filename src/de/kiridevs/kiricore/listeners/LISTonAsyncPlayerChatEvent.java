package de.kiridevs.kiricore.listeners;

import de.kiridevs.kiricore.main.Main;
import de.kiridevs.kiricore.managers.AfkManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.concurrent.Callable;

public class LISTonAsyncPlayerChatEvent implements Listener {
    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (!(AfkManager.isAfk(player))) { return; } // Skip all events where the player isn't AFK to enhance speed

        event.setCancelled(true); // Cancel event so the "player is back" message is sent before the actual chat message is

        if (event.isAsynchronous()) {
            // Event was caused by an actual player -> async
            // Running sync method AfkManager.markBack(player) from async context: (Needed as another event is fired)
            Bukkit.getScheduler().callSyncMethod(Main.getPlugin(), new Callable<>() {
                @Override
                public Object call() {
                    AfkManager.markBack(player);
                    return null;
                }
            });
        } else {
            // Event was caused by another Plugin forcing a player to speak -> sync
            AfkManager.markBack(player);
        }

        event.setCancelled(false); // Un-cancel event so the players message is sent

    }
}
