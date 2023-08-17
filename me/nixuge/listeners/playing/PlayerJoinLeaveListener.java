package me.nixuge.listeners.playing;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.nixuge.PlayerManager;
import me.nixuge.ShootCraft;
import me.nixuge.player.ShootingPlayer;

public class PlayerJoinLeaveListener implements Listener {
    private PlayerManager playerMgr = ShootCraft.getInstance().getPlayerMgr();
    
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        ShootingPlayer shootingP = playerMgr.getOfflineShootingPlayer(p);
        if (shootingP == null) {
            playerMgr.addPlayer(p);
        } else {
            playerMgr.rejoinPlayer(p);
        }
    }

    public void onPlayerLeave(PlayerQuitEvent event) {
        playerMgr.logoutPlayer(event.getPlayer());
    }
}
