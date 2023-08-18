package me.nixuge.player;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.nixuge.ShootCraft;
import me.nixuge.config.Config;

public class RespawnManager {
    private ShootCraft instance;

    private ShootingPlayer player;
    private int tempTicks;

    public RespawnManager(ShootingPlayer player) {
        this.instance = ShootCraft.getInstance();
        this.player = player;
    }

    public void initialSpawn() {
        Player p = player.getBukkitPlayer();
        p.getInventory().clear();
        p.getInventory().setItem(4, Gun.getItemEnabled());

        setHealthRespawn(true);

        commonInitialSpawnRespawn();

        removeSpawnProtAfterDelay();
    }

    public void respawnAfterKill() {
        setHealthRespawn(false);

        commonInitialSpawnRespawn();

        waitForRespawnDurationAndRemoveSpawnProt();
    }

    private void commonInitialSpawnRespawn() {
        player.setProtected(true);
        player.getBoost().removeAndRegainBoostHunger();
        
        tpToRespawn();
    }

    private void setHealthRespawn(boolean initialSpawn) {
        int newHealth = (initialSpawn) ?
            Config.lives.getRespawnLives() * 2 :
            Config.lives.getStartingLives() * 2;
        
        player.getBukkitPlayer().setHealth(newHealth);
    }

    private void tpToRespawn() {
        player.getBukkitPlayer().teleport(Config.map.getRandomSpawn());
    }

    private void waitForRespawnDurationAndRemoveSpawnProt() {
        tempTicks = Config.spawn.getRespawnDuration();
        setRespawnEffect(tempTicks);
        new BukkitRunnable() {
            @Override
            public void run() {
                tempTicks--;
                if (tempTicks == 0) {
                    removeSpawnProtAfterDelay();
                    this.cancel();
                }
            }
        }.runTaskTimerAsynchronously(instance, 0, 1);
    }

    private void removeSpawnProtAfterDelay() {
        tempTicks = Config.spawn.getSpawnProtectionDuration();
        new BukkitRunnable() {
            @Override
            public void run() {
                tempTicks--;
                if (tempTicks == 0) {
                    player.setProtected(false);
                    this.cancel();
                }
            }
        }.runTaskTimer(instance, 0, 1);
    }

    private void setRespawnEffect(int ticks) {
        Player p = player.getBukkitPlayer();
        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, ticks, 255, false, false));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, ticks, 255, false, false));
    }
}
