package de.kirimcdev.minecraft.plugins.kiricore.listeners;

import de.kirimcdev.minecraft.plugins.kiricore.main.Methods;
import de.kirimcdev.minecraft.plugins.kiricore.main.Vars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@SuppressWarnings("unused")
public class LISTplayerChat implements Listener {
    @EventHandler
    public static void onPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (Vars.afkList.contains(player.getName())) {
            event.setCancelled(true);
            Vars.afkList.remove(player.getName());

            String publicMsg = Methods.genPrefix("kiriCore", player, "a", "a");
            publicMsg += "§r" + player.getDisplayName() + "§a is no longer AFK!";
            Vars.server.broadcastMessage(publicMsg);

            String playerMsg = Methods.genPrefix("kiriCore", player, "a", "a");
            playerMsg += "Your AFK-Status was removed due to chat activity!";
            player.sendMessage(playerMsg);

            event.setCancelled(false);
        }
    }
}
