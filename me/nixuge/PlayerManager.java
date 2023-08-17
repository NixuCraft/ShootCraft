package me.nixuge;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.nixuge.players.ShootingPlayer;
import me.nixuge.utils.logger.Logger;

public class PlayerManager {
    public PlayerManager() {
        this.players = new ArrayList<>();
    }
    
    private List<ShootingPlayer> players;

    public void addPlayer(Player player) {
        this.players.add(new ShootingPlayer(player));
    }

    public ShootingPlayer getShootingPlayer(Player bukkitPlayer) {
        for (ShootingPlayer shootingPlayer : this.players) {
            if (shootingPlayer.getBukkitPlayer() == bukkitPlayer)
                return shootingPlayer;
        }
        return null;
    }

    // public boolean hasPlayer(Player bukkitPlayer) {
    //     return (getPlayerFromBukkitPlayer(bukkitPlayer) == null) ? false : true;
    // }

    public void removePlayer(Player player) {
        ShootingPlayer p = getShootingPlayer(player);
        this.players.remove(p);
        Logger.logBC("p removed: " + p.getBukkitPlayer().getName() + "( DEBUG )");
    }
}
