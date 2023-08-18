package me.nixuge.config.inner;

import org.bukkit.configuration.ConfigurationSection;

import lombok.Getter;
import me.nixuge.config.ConfigPart;

@Getter
public class DelayConfig extends ConfigPart {
    public DelayConfig(ConfigurationSection conf) {
        super(conf);
        ticksPerHungerRegain = getInt("ticksPerHalfHungerRegain", 10);
        respawnDuration = getInt("respawnDuration", 20);
        gunDelayDuration = getInt("gunDelayDuration", 50);
        spawnProtectionDuration = getInt("spawnProtectionDuration", 10);
    }
    
    private final int ticksPerHungerRegain;
    private final int respawnDuration;
    private final int gunDelayDuration;
    private final int spawnProtectionDuration;
}
