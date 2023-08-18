package me.nixuge.config;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import me.nixuge.ShootCraft;
import me.nixuge.config.inner.DelayConfig;
import me.nixuge.config.inner.GameConfig;
import me.nixuge.config.inner.LivesConfig;
import me.nixuge.config.inner.MapConfig;

public class Config {

    private static ShootCraft plugin = ShootCraft.getInstance();
    private static FileConfiguration fileConf;
    public static DelayConfig delay;
    public static GameConfig game;
    public static LivesConfig lives;
    public static MapConfig map;

    public static void init(FileConfiguration conf) {
        Config.fileConf = conf;
        delay = new DelayConfig(getFileConfigBlock("delays"));
        game = new GameConfig(getFileConfigBlock("game"));
        lives = new LivesConfig(getFileConfigBlock("lives"));
        map = new MapConfig(getFileConfigBlock("map"));
    }

    public static void saveConfig() {
        fileConf.options().copyDefaults(true);
        plugin.saveConfig();
    }

    public static ConfigurationSection getFileConfigBlock(String str) {
        return fileConf.getConfigurationSection(str);
    }
}
