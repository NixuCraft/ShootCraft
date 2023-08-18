package me.nixuge.player;

import org.bukkit.scheduler.BukkitRunnable;

import lombok.Getter;
import me.nixuge.ShootCraft;
import me.nixuge.config.Config;
import me.nixuge.utils.TextUtils;
import me.nixuge.utils.TitleUtils;
import me.nixuge.utils.logger.Logger;

public class Boost {
    private ShootCraft instance;
    private ShootingPlayer player;

    @Getter
    private int delay;

    public Boost(ShootingPlayer player) {
        this.instance = ShootCraft.getInstance();
        this.player = player;
        this.delay = 0;
    }

    public void setDelayRespawn() {
        setDelay(Config.delay.getRespawnDuration());
    }

    public void setDelay(int newDelay) {
        this.delay = newDelay;
        new BukkitRunnable() {
            @Override
            public void run() {
                delay--;
                TitleUtils.sendActionBar(player.getBukkitPlayer(), "§eBoost time: " + TextUtils.ticksToSeconds(newDelay) + "s");
                Logger.logBC("lowered timer ! (boost) " + delay);
                if (delay == 0)
                    this.cancel();
            }
        }.runTaskTimerAsynchronously(instance, 0, 1);
    }

    public boolean canBoost() {
        return false;
    }

    public void boost() {
    }
}
