package me.nixuge.shootcraft;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.nixuge.shootcraft.commands.ForceStartCommand;
import me.nixuge.shootcraft.config.Config;

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
        new Config().init(this);

        pluginMgr = getServer().getPluginManager();

        playerMgr = new PlayerManager();
        gameMgr = new GameManager();

        getCommand("force_start").setExecutor(new ForceStartCommand());

        gameMgr.broadcastGamePrefix("Plugin enabled successfully.");
    }
}