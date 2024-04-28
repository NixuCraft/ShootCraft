package me.nixuge.shootcraft.config;

import me.nixuge.shootcraft.config.inner.BoostConfig;
import me.nixuge.shootcraft.config.inner.GameConfig;
import me.nixuge.shootcraft.config.inner.GunConfig;
import me.nixuge.shootcraft.config.inner.LivesConfig;
import me.nixuge.shootcraft.config.inner.MapConfig;
import me.nixuge.shootcraft.config.inner.SpawnConfig;
import me.nixuge.configurator.Configurator;

public class Config extends Configurator {
    public static BoostConfig boost;
    public static GameConfig game;
    public static GunConfig gun;
    public static LivesConfig lives;
    public static MapConfig map;
    public static SpawnConfig spawn;

    @Override
    protected void initConfigParts() {
        boost = new BoostConfig();
        game = new GameConfig();
        gun = new GunConfig();
        lives = new LivesConfig();
        map = new MapConfig();
        spawn = new SpawnConfig();
    }
}
