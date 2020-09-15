package space.devport.wertik.inventoryevents.event;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Fired when a player closes his or any other inventory.
 */
public class PlayerInventoryCloseEvent extends PlayerInventoryEvent {
    public PlayerInventoryCloseEvent(Player player, Inventory inventory) {
        super(player, inventory);
    }
}