package me.nixuge.shootcraft.config.inner;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import me.nixuge.configurator.ConfigPart;


@Getter
public class LivesConfig extends ConfigPart {
    public LivesConfig() {
        super("lives");
        startingLives = getInt(rootConfig, "startingLives", 1);
        respawnLives = getInt(rootConfig, "respawnLives", 1);
        maxLives = getInt(rootConfig, "maxLives", 3);
        liveOnDoubleKill = getBoolean(rootConfig, "liveOnDoubleKill", true);
        liveOnKillStreak = getIntegerList(rootConfig, "liveOnKillStreak", new ArrayList<>(0));
    }
    
    private final int startingLives;
    private final int respawnLives;
    private final int maxLives;
    private final boolean liveOnDoubleKill;
    private final List<Integer> liveOnKillStreak;
}
