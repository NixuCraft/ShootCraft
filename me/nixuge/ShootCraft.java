package me.nixuge;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.nixuge.commands.ForceStartCommand;
import me.nixuge.config.Config;

public class ShootCraft extends JavaPlugin {
    @Getter
    private static ShootCraft instance;
    @Getter
    private PluginManager pluginMgr;
    @Getter
    private GameManager gameMgr;
    @Getter
    private PlayerManager playerMgr;


    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        Config.init(getConfig());

        pluginMgr = getServer().getPluginManager();

        playerMgr = new PlayerManager();
        gameMgr = new GameManager();

        getCommand("force_start").setExecutor(new ForceStartCommand());

        gameMgr.broadcastGamePrefix("Plugin enabled successfully.");
    }

    // LEFT TODO FROM CONFIG:
    // - maxGameDuration
    // - maxPlayerCount
}