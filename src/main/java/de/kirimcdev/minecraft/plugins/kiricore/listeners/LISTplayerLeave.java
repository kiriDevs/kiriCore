package de.kirimcdev.minecraft.plugins.kiricore.listeners;

import de.kirimcdev.minecraft.plugins.kiricore.main.Vars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@SuppressWarnings("unused")
public class LISTplayerLeave implements Listener {
    @EventHandler
    public static void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (Vars.afkList.contains(player.getName())) {
            Vars.afkList.remove(player.getName());
        }
    }
}
