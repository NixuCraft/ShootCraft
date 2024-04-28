package me.nixuge.shootcraft.config.inner;

import lombok.Getter;
import me.nixuge.configurator.ConfigPart;

@Getter
public class BoostConfig extends ConfigPart {
    public BoostConfig() {
        super("boost");
        hungerLossOnBoost = getInt(rootConfig, "halfHungerLossOnBoost", 10);
        ticksPerHungerRegain = getInt(rootConfig, "ticksPerHalfHungerRegain", 10);
        boostTime = getInt(rootConfig, "boostTime", 50);
    }

    private final int hungerLossOnBoost;
    private final int ticksPerHungerRegain;
    private final int boostTime;
}
