# InventoryEventAPI
Replacement for InventoryClose&amp;OpenEvents.

## Description

Spigot recently changed InventoryCloseEvent's behaviour. (No longer fires when a player closes his own inventory, [related issue](https://github.com/PaperMC/Paper/issues/3733))
This library provides the PlayerInventoryCloseEvent and PlayerInventoryOpenEvent, which are both fired using packets.
*Yes, I call this a library and API at the same time, so confused about this one ok?!*

## Usage

Initialize using ``InventoryEventAPI#registerListeners(JavaPlugin plugin)`` and then listen for `PlayerInventoryCloseEvent` and/or `PlayerInventoryOpenEvent`.

## Maven

```xml
<repository>
    <id>devport-public</id>
    <url>http://play.pvpcraft.cz:8081/repository/devport-public/</url>
</repository>

<dependency>
    <groupId>space.devport.wertik.inventoryevents</groupId>
    <artifactId>InventoryEventsAPI</artifactId>
    <version>1.1.1</version>
    <scope>compile</scope>
</dependency>
```
