package me.nixuge.shootcraft.config.inner;

import lombok.Getter;
import me.nixuge.configurator.ConfigPart;

@Getter
public class GameConfig extends ConfigPart {
    public GameConfig() {
        super("game");
        killsForWin = getInt(rootConfig, "killsForWin", 0);
        kfwEnabled = killsForWin > 0;
        maxGameDuration = getInt(rootConfig, "maxGameDuration", 180);
        maxPlayerCount = getInt(rootConfig, "maxPlayerCount", 90);
        broadcastPrefix = getString(rootConfig, "broadcastPrefix", "§c[ShootCraft]§r ");
    }
    
    private final boolean kfwEnabled;
    private final int killsForWin;
    private final int maxGameDuration;
    private final int maxPlayerCount;
    private final String broadcastPrefix;
}
