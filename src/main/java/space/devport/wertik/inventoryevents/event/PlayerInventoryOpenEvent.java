package space.devport.wertik.inventoryevents.event;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Fired when a player opens any other inventory than his.
 * Why not his: Couldn't figure out how to listen for Window Confirmation packets and ran out of time.
 */
public class PlayerInventoryOpenEvent extends PlayerInventoryEvent {
    public PlayerInventoryOpenEvent(Player player, Inventory inventory) {
        super(player, inventory);
    }
}