package me.nixuge.shootcraft.config.inner;

import lombok.Getter;
import me.nixuge.configurator.ConfigPart;

@Getter
public class GunConfig extends ConfigPart {
    public GunConfig() {
        super("gun");
        gunDelayDuration = getInt(rootConfig, "gunDelayDuration", 50);
    }
    
    private final int gunDelayDuration;
}
