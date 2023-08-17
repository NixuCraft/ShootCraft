package me.nixuge;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.nixuge.enums.GameState;

public class GameManager {
    private ShootCraft shootCraft;
    private PlayerManager playerMgr;

    public GameManager() {
        shootCraft = ShootCraft.getInstance();
        playerMgr = shootCraft.getPlayerMgr();
        GameState.setGameState(GameState.IDLING);
    }

    public void startGame() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            playerMgr.addPlayer(player);
        }


        GameState.setGameState(GameState.PLAYING);
    }

    public void stopGame() {
        GameState.setGameState(GameState.PLAYING);
    }
}
