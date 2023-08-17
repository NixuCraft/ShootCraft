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
    private int totalKills;

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
        setHealthRespawn(true);
    }

    // Bit verbose but yeah idk this should be fine
    private void setMaxHealth() {
        bukkitPlayer.setMaxHealth(Config.lives.getMaxLives() * 2);
    }
    private void setHealthRespawn(boolean initialSpawn) {
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
        setHealthRespawn(false);
    }

    public void leave() {
        this.bukkitPlayer = null;
        this.isOnline = false;
    }

    public void addKill() {
        this.totalKills++;
        this.killStreak++;
        if (Config.lives.getLiveOnKillStreak().contains(killStreak))
            gameMgr.broadcastGamePrefix(bukkitPlayer.getCustomName() + " got a killstreak of " + killStreak + ". He gained a life.");

        if (Config.game.isKfwEnabled() && Config.game.getKillsForWin() >= totalKills) {
            gameMgr.broadcastGamePrefix("§l§6GAME ENDED !");
            // TODO: end game here
        }
    }

    public void addLife() {
        if (this.currentLives >= Config.lives.getMaxLives())
            return;
        this.currentLives++;
        bukkitPlayer.setHealth(currentLives * 2);
        // TODO: add chestplate
    }

    public void hit(String killer) {
        if (this.currentLives == 1) {
            kill();
            gameMgr.broadcastGame(bukkitPlayer.getCustomName() + " §cgot killed by " + killer);
        } else {
            this.currentLives--;
            bukkitPlayer.setHealth(currentLives * 2);
        }
    }

    private void kill() {
        gun.setDelay(Config.delay.getRespawnDuration());
        this.currentLives = Config.lives.getStartingLives();
        this.killStreak = 0;
        setHealthRespawn(false);

        // TODO: finish (respawn etc)
    }
}
