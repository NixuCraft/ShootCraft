package me.nixuge.shootcraft.config.inner;

import org.bukkit.configuration.ConfigurationSection;

import lombok.Getter;
import me.nixuge.shootcraft.config.ConfigPart;

@Getter
public class GunConfig extends ConfigPart {
    public GunConfig(ConfigurationSection conf) {
        super(conf);
        gunDelayDuration = getInt("gunDelayDuration", 50);
    }
    
    private final int gunDelayDuration;
}
