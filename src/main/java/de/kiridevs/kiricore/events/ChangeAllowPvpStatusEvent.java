package de.kiridevs.kiricore.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ChangeAllowPvpStatusEvent extends Event {
    private final boolean newState;

    private static final HandlerList handlerList = new HandlerList();

    public ChangeAllowPvpStatusEvent(boolean newState) {
        this.newState = newState;
    }

    public boolean getNewPvpState() { return newState; }

    @Override
    public @NotNull HandlerList getHandlers() { return handlerList; }

    @SuppressWarnings("unused")
    public static HandlerList getHandlerList() { return handlerList; }
}
