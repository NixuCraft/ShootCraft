package me.nixuge.shootcraft.listeners.playing;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class HotbarItemHeldListener implements Listener {
    @EventHandler
    public void onHeldItemChange(PlayerItemHeldEvent event) {
        event.setCancelled(true);
    }
}
