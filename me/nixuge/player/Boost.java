package me.nixuge.player;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.Getter;
import me.nixuge.ShootCraft;
import me.nixuge.config.Config;

// Could make an interface for that + Gun.java but meh 
public class Boost {
    private ShootCraft instance;
    private ShootingPlayer player;

    @Getter
    private boolean canBoost;

    public Boost(ShootingPlayer player) {
        this.instance = ShootCraft.getInstance();
        this.player = player;
        this.canBoost = true;
    }

    public void removeAndRegainBoostHunger() {
        removeBoostHunger();
        regainBoostHunger();
    }

    private void removeBoostHunger() {
        player.getBukkitPlayer().setFoodLevel(20 - Config.boost.getHungerLossOnBoost());
        canBoost = false;
    }

    // Have to have those declated outside of regainBoostHunger
    int ranTicks;
    int hungerRegainAdd;
    private void regainBoostHunger() {
        int ticksPerHunger = Config.boost.getTicksPerHungerRegain();
        int hungerLossOnBoost = Config.boost.getHungerLossOnBoost();
        ranTicks = 1;
        hungerRegainAdd = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                ranTicks++;
                if (ranTicks % ticksPerHunger == 0) {
                    hungerRegainAdd++;
                    player.getBukkitPlayer().setFoodLevel(20 - hungerLossOnBoost + hungerRegainAdd);
                }
                if (hungerRegainAdd == hungerLossOnBoost) {
                    canBoost = true;
                    this.cancel();
                }
            }
        }.runTaskTimer(instance, 1, 1);
    }
    
    int ranBoostTicks;
    private void giveBoostEffectThenRevert() {
        giveSpeed(true);
        ranBoostTicks = 0;
        new BukkitRunnable() {
            @Override
            public void run() {
                ranBoostTicks++;
                if (ranBoostTicks == Config.boost.getBoostTime()) {
                    giveSpeed(false);
                    this.cancel();
                }
            }
        }.runTaskTimer(instance, 0, 1);
    }
 
    private void giveSpeed(boolean boost) {
        player.getBukkitPlayer().removePotionEffect(PotionEffectType.SPEED);
        player.getBukkitPlayer().addPotionEffect(new PotionEffect(
            PotionEffectType.SPEED, 9999999, boost ? 9 : 1, false, false)
        );
    }

    public boolean canBoost() {
        return canBoost;
    }

    public void boost() {
        giveBoostEffectThenRevert();
        removeAndRegainBoostHunger();
    }
}
