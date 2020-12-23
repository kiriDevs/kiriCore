package de.kiridevs.kiricore.listeners;

import de.kiridevs.kiricore.events.PlayerChangeAfkStatusEvent;
import de.kiridevs.kiricore.managers.MessageService;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class LISTonPlayerChangeAfkStatus implements Listener {
    MessageService messageService;
    public LISTonPlayerChangeAfkStatus(MessageService messageService) {
        this.messageService = messageService;
    }

    @EventHandler
    public void onPlayerChangeAfkStatus(PlayerChangeAfkStatusEvent event) {
        Server server = Bukkit.getServer();

        String playerName = event.getPlayer().getDisplayName();
        boolean isAfkNow = event.getNewAfkState();

        ArrayList<String> completionList = new ArrayList<>();
        completionList.add(playerName);

        if (!(event.getPlayer().isOnline())) { return; } // Don't allow messages to be sent when player is no longer online (changed AFK status by leaving)

        if (isAfkNow) { messageService.broadcastMessage("nowafk", completionList); }
        else { messageService.broadcastMessage("nolongerafk", completionList); }
    }
}
