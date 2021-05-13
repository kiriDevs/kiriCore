package de.kiridevs.kiricore.listeners;

import de.kiridevs.kiricore.managers.MessageService;
import de.kiridevs.kiricore.managers.PvpManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class LISTonPvp implements Listener {
    MessageService messageService;

    public LISTonPvp(MessageService messageService) {
        this.messageService = messageService;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        // Check if PvP is even disallowed
        if (PvpManager.getAllowPvp()) { return; }

        // Make sure the damage is actually PvP
        if (!(event.getEntity() instanceof Player)) { return; }
        if (!(event.getDamager() instanceof Player)) { return; }

        // Cancel the PvP damage
        event.setCancelled(true);

        // Send a message to the 'offender'
        Player offender = (Player) event.getDamager();
        messageService.sendErrorMessage(offender, "PVP is currently turned off!");
    }
}
