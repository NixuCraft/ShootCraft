package me.nixuge.config.inner;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

import lombok.Getter;
import me.nixuge.config.ConfigPart;

@Getter
public class LivesConfig extends ConfigPart {
    public LivesConfig(ConfigurationSection conf) {
        super(conf);
        startingLives = getInt("startingLives", 1);
        respawnLives = getInt("respawnLives", 1);
        maxLives = getInt("maxLives", 3);
        liveOnDoubleKill = getBoolean("liveOnDoubleKill", true);
        liveOnKillStreak = getIntList("liveOnKillStreak", new ArrayList<>(0));
    }
    
    private final int startingLives;
    private final int respawnLives;
    private final int maxLives;
    private final boolean liveOnDoubleKill;
    private final List<Integer> liveOnKillStreak;
}
