# InventoryEventsAPI
Replacement for InventoryClose&amp;OpenEvents.

## Description

Spigot recently changed InventoryCloseEvent's behaviour. (No longer fires when a player closes his own inventory, [related issue](https://github.com/PaperMC/Paper/issues/3733))
This library provides the PlayerInventoryCloseEvent and PlayerInventoryOpenEvent, which are both fired using packets.

## Usage

Initialize using ``InventoryEventsAPI#registerListeners(JavaPlugin plugin)`` and then listen for `PlayerInventoryCloseEvent` and/or `PlayerInventoryOpenEvent`.
