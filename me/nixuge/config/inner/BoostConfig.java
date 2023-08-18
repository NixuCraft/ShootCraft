package me.nixuge.config.inner;

import org.bukkit.configuration.ConfigurationSection;

import lombok.Getter;
import me.nixuge.config.ConfigPart;

@Getter
public class BoostConfig extends ConfigPart {
    public BoostConfig(ConfigurationSection conf) {
        super(conf);
        hungerLossOnBoost = getInt("halfHungerLossOnBoost", 10);
        ticksPerHungerRegain = getInt("ticksPerHalfHungerRegain", 10);
        boostTime = getInt("boostTime", 50);
    }

    private final int hungerLossOnBoost;
    private final int ticksPerHungerRegain;
    private final int boostTime;
}
