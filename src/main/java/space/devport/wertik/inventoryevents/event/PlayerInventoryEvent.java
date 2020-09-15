package space.devport.wertik.inventoryevents.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class PlayerInventoryEvent extends Event {

    @Getter
    private final Player player;

    @Getter
    private final Inventory inventory;

    private static final HandlerList handlerList = new HandlerList();

    public PlayerInventoryEvent(Player player, Inventory inventory) {
        this.player = player;
        this.inventory = inventory;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
