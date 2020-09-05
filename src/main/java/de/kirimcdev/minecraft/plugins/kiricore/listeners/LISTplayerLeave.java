package de.kirimcdev.minecraft.plugins.kiricore.listeners;

import de.kirimcdev.minecraft.plugins.kiricore.main.Methods;
import de.kirimcdev.minecraft.plugins.kiricore.main.Vars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@SuppressWarnings({"unused", "RedundantSuppression"}) // onPlayerLeave "unused", suppression for that "redundant"
public class LISTplayerLeave implements Listener {
    @EventHandler
    public static void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (Methods.getIsAfk(player)) {
            Vars.afkList.remove(player.getName());
        }
    }
}
