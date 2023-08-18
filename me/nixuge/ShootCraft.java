package me.nixuge;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.nixuge.commands.ForceStartCommand;
import me.nixuge.config.Config;
import me.nixuge.enums.GameState;

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
        
        GameState.setGameState(GameState.IDLING);

        // gameManager = new GameManager();

        getCommand("force_start").setExecutor(new ForceStartCommand());

        // pm.registerEvents(new InventoryListener(), this);

        Bukkit.broadcastMessage("PLugin enablezd");
    }

    // LEFT TODO FROM CONFIG:
    // - maxGameDuration
    // - maxPlayerCount
    // - halfHungerLossOnBoost
    //
    // - ticksPerHalfHungerRegain
    // - spawnProtectionDuration
    // - respawnDuration (not fully)
    //
    // - spawns
}