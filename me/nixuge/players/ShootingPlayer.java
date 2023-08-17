package me.nixuge.players;

import org.bukkit.entity.Player;

import lombok.Getter;
import lombok.Setter;
import me.nixuge.config.Config;

public class ShootingPlayer {
    @Getter
    private Gun gun;
    @Getter
    private Boost boost;
    @Getter
    @Setter // for relogs
    private Player bukkitPlayer;
    @Getter
    @Setter
    private boolean isOnline;

    public ShootingPlayer(Player bukkitPlayer) {
        this.bukkitPlayer = bukkitPlayer;
        this.isOnline = true;
        this.gun = new Gun(this);
        this.boost = new Boost(this);
    }

    public void setBukkitPlayer() {

    }

    public void kill() {
        gun.setDelay(Config.delay.getRespawnDuration());
        // boost.set
        // TODO
    }
}
