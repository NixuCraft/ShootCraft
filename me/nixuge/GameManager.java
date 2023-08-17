package me.nixuge;

import me.nixuge.enums.GameState;

public class GameManager {
    public GameManager() {
        shootCraft = ShootCraft.getInstance();
        GameState.setGameState(GameState.IDLING);
    }

    private ShootCraft shootCraft;
}
