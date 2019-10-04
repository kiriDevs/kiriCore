package de.kirimcdev.minecraft.plugins.kiricore.listeners;

import de.kirimcdev.minecraft.plugins.kiricore.main.Methods;
import de.kirimcdev.minecraft.plugins.kiricore.main.Vars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

@SuppressWarnings("unused")
public class LISTplayerMove implements Listener {
    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (Vars.afkList.contains(player.getName())) {
            Vars.afkList.remove(player.getName());

            String publicMsg = Methods.genPrefix("kiriCore", player, "a", "a");
            publicMsg += "§r" + player.getDisplayName() + "§r§a";
            publicMsg += " is no longer AFK.";
            Vars.server.broadcastMessage(publicMsg);

            String privateMsg = Methods.genPrefix("kiriCore", player, "a", "a");
            privateMsg += "You are no longer marked as AFK.";
            player.sendMessage(privateMsg);
        }
    }
}
