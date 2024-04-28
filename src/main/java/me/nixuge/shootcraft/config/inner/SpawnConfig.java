package me.nixuge.shootcraft.config.inner;

import lombok.Getter;
import me.nixuge.configurator.ConfigPart;

@Getter
public class SpawnConfig extends ConfigPart {
    public SpawnConfig() {
        super("spawn");
        respawnDuration = getInt(rootConfig, "respawnDuration", 20);
        spawnProtectionDuration = getInt(rootConfig, "spawnProtectionDuration", 10);
    }
    
    private final int respawnDuration;
    private final int spawnProtectionDuration;
}
