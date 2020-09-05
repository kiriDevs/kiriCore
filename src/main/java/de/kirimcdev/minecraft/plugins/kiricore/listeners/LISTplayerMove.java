package de.kirimcdev.minecraft.plugins.kiricore.listeners;

import de.kirimcdev.minecraft.plugins.kiricore.main.Methods;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

@SuppressWarnings({"unused", "RedundantSuppression"}) // onPlayerMove was marked "unused", the suppression "redundant"
public class LISTplayerMove implements Listener {
    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent event) {

        if (Methods.compareLocations(event.getFrom(), event.getTo()) == 1) { // inspired by Essentials mechanism
            return;
        }

        Player player = event.getPlayer();
        if (Methods.getIsAfk(player)) {
            String privateMsg = Methods.genPrefix("kiriCore", player, "a", "a");
            privateMsg += "Your AFK status was removed due to movement!";

            player.sendMessage(privateMsg);
            Methods.unMarkAfk(player);
        }
    }
}
