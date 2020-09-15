package space.devport.wertik.inventoryevents;

import io.netty.channel.*;
import net.minecraft.server.v1_16_R2.PacketPlayInCloseWindow;
import net.minecraft.server.v1_16_R2.PacketPlayOutOpenWindow;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import space.devport.wertik.inventoryevents.event.PlayerInventoryCloseEvent;
import space.devport.wertik.inventoryevents.event.PlayerInventoryOpenEvent;

public class InventoryEventAPI implements Listener {

    private static InventoryEventAPI instance;

    private JavaPlugin plugin;

    public static InventoryEventAPI getInstance() {
        if (instance == null)
            instance = new InventoryEventAPI();
        return instance;
    }

    private InventoryEventAPI() {
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.startListening(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        this.stopListening(event.getPlayer());
    }

    public void registerListeners(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        plugin.getServer().getOnlinePlayers().forEach(this::startListening);
    }

    public void stopListening(Player player) {
        Channel channel = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(player.getName());
            return null;
        });
    }

    public void startListening(Player player) {
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {

            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
                if (packet instanceof PacketPlayInCloseWindow) {
                    Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getPluginManager().callEvent(new PlayerInventoryCloseEvent(player, player.getInventory())));
                }
                super.channelRead(channelHandlerContext, packet);
            }

            @Override
            public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {
                if (packet instanceof PacketPlayOutOpenWindow) {
                    Bukkit.getScheduler().runTask(plugin, () -> Bukkit.getPluginManager().callEvent(new PlayerInventoryOpenEvent(player, player.getInventory())));
                }
                super.write(channelHandlerContext, packet, channelPromise);
            }
        };

        ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline();
        pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
    }
}