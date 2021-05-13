package de.kiridevs.kiricore.listeners;

import de.kiridevs.kiricore.events.ChangeAllowPvpStatusEvent;
import de.kiridevs.kiricore.managers.MessageService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LISTonChangePvpStatusEvent implements Listener {
    MessageService messageService;
    public LISTonChangePvpStatusEvent(MessageService messageService) {
        this.messageService = messageService;
    }

    @EventHandler
    public void onChangeAllowPvpStatusEvent(ChangeAllowPvpStatusEvent event) {
        boolean newStatus = event.getNewPvpState();

        if (newStatus) {
            messageService.broadcastMessage("PVP is now enabled!");
        } else {
            messageService.broadcastMessage("PVP is now disabled!");
        }
    }
}
