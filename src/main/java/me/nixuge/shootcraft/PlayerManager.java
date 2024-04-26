package me.nixuge.shootcraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

import lombok.Getter;
import me.nixuge.shootcraft.player.ShootingPlayer;

public class PlayerManager {
    public PlayerManager() {
        this.onlinePlayers = new ArrayList<>();
        this.offlinePlayers = new ArrayList<>();
        this.onlinePlayersMap = new HashMap<>();
    }
    
    @Getter
    private List<ShootingPlayer> onlinePlayers;
    private List<ShootingPlayer> offlinePlayers;

    // This should be much more optimized than a list for searching players.
    // Still didn't test performances tho lol.
    // Shouldn't even matter that much w only a few players,
    // but might as well since doing it frequently.
    private Map<Player, ShootingPlayer> onlinePlayersMap;

    public void addPlayer(Player player) {
        ShootingPlayer p = new ShootingPlayer(player);
        this.onlinePlayers.add(p);
        
        onlinePlayersMap.put(player, p);

        p.rejoin(player);
    }

    public void rejoinPlayer(Player bukkitPlayer) {
        ShootingPlayer player = getOfflineShootingPlayer(bukkitPlayer);

        onlinePlayersMap.put(bukkitPlayer, player);

        this.offlinePlayers.remove(player);
        this.onlinePlayers.add(player);

        player.rejoin(bukkitPlayer);
    }
    public void logoutPlayer(Player bukkitPlayer) {
        ShootingPlayer player = getShootingPlayer(bukkitPlayer);

        onlinePlayersMap.remove(bukkitPlayer);

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
        return onlinePlayersMap.get(bukkitPlayer);
    }

    public void broadcastToAllPlayers(String text) {
        for (ShootingPlayer shootingPlayer : this.onlinePlayers)
            shootingPlayer.getBukkitPlayer().sendMessage(text);
    }
}
