package de.kiridevs.kiricore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.Nonnull;

public class PlayerChangeAfkStatusEvent extends Event {
    private final Player player;
    private final boolean isAfkNow;

    private static final HandlerList handlerList = new HandlerList();

    public PlayerChangeAfkStatusEvent(Player player, boolean newAfkStatus) {
        this.player = player;
        this.isAfkNow = newAfkStatus;
    }

    public Player getPlayer() {
        return player;
    }
    public boolean getNewAfkState() {
        return isAfkNow;
    }



    @Override
    public @Nonnull HandlerList getHandlers() { return handlerList; }

    @SuppressWarnings("unused")
    public static HandlerList getHandlerList() { return handlerList; }
}
