package me.nixuge.shootcraft.config.inner;

import org.bukkit.configuration.ConfigurationSection;

import lombok.Getter;
import me.nixuge.shootcraft.config.ConfigPart;

@Getter
public class SpawnConfig extends ConfigPart {
    public SpawnConfig(ConfigurationSection conf) {
        super(conf);
        respawnDuration = getInt("respawnDuration", 20);
        spawnProtectionDuration = getInt("spawnProtectionDuration", 10);
    }
    
    private final int respawnDuration;
    private final int spawnProtectionDuration;
}
