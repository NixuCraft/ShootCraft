package me.nixuge.player;

import org.bukkit.entity.Player;

import lombok.Getter;
import me.nixuge.GameManager;
import me.nixuge.ShootCraft;
import me.nixuge.config.Config;

public class ShootingPlayer {
    private GameManager gameMgr;

    @Getter
    private Gun gun;
    @Getter
    private Boost boost;

    private int currentLives;
    private int killStreak;

    @Getter
    private Player bukkitPlayer;
    private boolean isOnline;

    public ShootingPlayer(Player bukkitPlayer) {
        this.gameMgr = ShootCraft.getInstance().getGameMgr();
        this.bukkitPlayer = bukkitPlayer;
        this.isOnline = true;
        this.gun = new Gun(this);
        this.boost = new Boost(this);
        // Not sure if that should be in the constructor
        setMaxHealth();
        setHealth(true);
    }

    // Bit verbose but yeah idk this should be fine
    private void setMaxHealth() {
        bukkitPlayer.setMaxHealth(Config.lives.getMaxLives() * 2);
    }
    private void setHealth(boolean initialSpawn) {
        int newHealth = (initialSpawn) ?
            Config.lives.getRespawnLives() * 2 :
            Config.lives.getStartingLives() * 2;
        
        bukkitPlayer.setHealth(newHealth);
    }



    public void rejoin(Player player) {
        this.bukkitPlayer = player;
        this.isOnline = true;
        // May need to be delayed 1 tick
        setMaxHealth();
        setHealth(false);
    }

    public void leave() {
        this.bukkitPlayer = null;
        this.isOnline = false;
    }

    public void incrementKillStreak() {
        this.killStreak++;
        if (Config.lives.getLiveOnKillStreak().contains(killStreak))
            gameMgr.broadcastGame("§c[ShootCraft]§r " + bukkitPlayer.getCustomName() + " got a killstreak of " + killStreak + ". He gained a life.");
    }

    public void hit() {
        if (this.currentLives == 1)
            kill();
        else {
            bukkitPlayer.setHealth(currentLives * 2);
            this.currentLives--;
        }
    }

    private void kill() {
        gun.setDelay(Config.delay.getRespawnDuration());
        this.currentLives = Config.lives.getStartingLives();
        this.killStreak = 0;
        setHealth(false);

        // TODO: finish (respawn etc)
    }
}
