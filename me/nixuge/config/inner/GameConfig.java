package me.nixuge.config.inner;

import org.bukkit.configuration.ConfigurationSection;

import lombok.Getter;
import me.nixuge.config.ConfigPart;

@Getter
public class GameConfig extends ConfigPart {
    public GameConfig(ConfigurationSection conf) {
        super(conf);
        killsForWin = getInt("killsForWin", 0);
        kfwEnabled = killsForWin > 0;
        maxGameDuration = getInt("maxGameDuration", 180);
        maxPlayerCount = getInt("maxPlayerCount", 90);
        hungerLossOnBoost = getInt("halfHungerLossOnBoost", 10);
        broadcastPrefix = getString("broadcastPrefix", "§c[ShootCraft]§r ");
    }
    
    private final boolean kfwEnabled;
    private final int killsForWin;
    private final int maxGameDuration;
    private final int maxPlayerCount;
    private final int hungerLossOnBoost;
    private final String broadcastPrefix;
}
