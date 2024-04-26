package me.nixuge.shootcraft.config.inner;

import org.bukkit.configuration.ConfigurationSection;

import lombok.Getter;
import me.nixuge.shootcraft.config.ConfigPart;

@Getter
public class GameConfig extends ConfigPart {
    public GameConfig(ConfigurationSection conf) {
        super(conf);
        killsForWin = getInt("killsForWin", 0);
        kfwEnabled = killsForWin > 0;
        maxGameDuration = getInt("maxGameDuration", 180);
        maxPlayerCount = getInt("maxPlayerCount", 90);
        broadcastPrefix = getString("broadcastPrefix", "§c[ShootCraft]§r ");
    }
    
    private final boolean kfwEnabled;
    private final int killsForWin;
    private final int maxGameDuration;
    private final int maxPlayerCount;
    private final String broadcastPrefix;
}
