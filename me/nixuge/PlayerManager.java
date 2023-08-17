package me.nixuge;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import lombok.Getter;
import me.nixuge.player.ShootingPlayer;

public class PlayerManager {
    public PlayerManager() {
        this.onlinePlayers = new ArrayList<>();
        this.offlinePlayers = new ArrayList<>();
    }
    
    @Getter
    private List<ShootingPlayer> onlinePlayers;
    private List<ShootingPlayer> offlinePlayers;

    public void addPlayer(Player player) {
        this.onlinePlayers.add(new ShootingPlayer(player));
    }

    public void rejoinPlayer(Player bukkitPlayer) {
        ShootingPlayer player = getOfflineShootingPlayer(bukkitPlayer);
        this.offlinePlayers.remove(player);
        this.onlinePlayers.add(player);
        player.rejoin(bukkitPlayer);
    }
    public void logoutPlayer(Player bukkitPlayer) {
        ShootingPlayer player = getShootingPlayer(bukkitPlayer);
        this.onlinePlayers.remove(player);
        this.offlinePlayers.add(player);
        player.leave();
    }

    public ShootingPlayer getOfflineShootingPlayer(Player bukkitPlayer) {
        for (ShootingPlayer shootingPlayer : this.offlinePlayers) {
            if (shootingPlayer.getBukkitPlayer().getName() == bukkitPlayer.getName())
                return shootingPlayer;
        }
        return null;
    }

    public ShootingPlayer getShootingPlayer(Player bukkitPlayer) {
        for (ShootingPlayer shootingPlayer : this.onlinePlayers) {
            if (shootingPlayer.getBukkitPlayer() == bukkitPlayer)
                return shootingPlayer;
        }
        return null;
    }

    public void broadcastToAllPlayers(String text) {
        for (ShootingPlayer shootingPlayer : this.onlinePlayers)
            shootingPlayer.getBukkitPlayer().sendMessage(text);
    }
}
