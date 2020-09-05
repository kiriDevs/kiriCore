package de.kirimcdev.minecraft.plugins.kiricore.listeners;

import de.kirimcdev.minecraft.plugins.kiricore.main.Methods;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@SuppressWarnings({"unused", "RedundantSuppression"}) // onPlayerChatEvent "unused", suppression for that "redundant"
public class LISTplayerChat implements Listener {
    @EventHandler
    public static void onPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (Methods.getIsAfk(player)) {
            event.setCancelled(true); // Cancelling chat event so it comes in after the public message

            Methods.unMarkAfk(player);

            String privateMsg = Methods.genPrefix("kiriCore", player, "a", "a");
            privateMsg += "Your AFK status was removed due to chat activity!";
            player.sendMessage(privateMsg);

            event.setCancelled(false);
        }
    }
}
