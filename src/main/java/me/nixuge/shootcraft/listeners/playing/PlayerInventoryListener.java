package me.nixuge.shootcraft.listeners.playing;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class PlayerInventoryListener implements Listener {
    @EventHandler
    public void onInventoryChange(InventoryInteractEvent event) {
        event.setCancelled(true);
    }
}
