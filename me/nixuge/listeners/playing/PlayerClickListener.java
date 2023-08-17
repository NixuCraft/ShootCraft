package me.nixuge.listeners.playing;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.nixuge.PlayerManager;
import me.nixuge.ShootCraft;
import me.nixuge.player.Boost;
import me.nixuge.player.Gun;
import me.nixuge.player.ShootingPlayer;

public class PlayerClickListener implements Listener {
    private PlayerManager playerMgr = ShootCraft.getInstance().getPlayerMgr();

    public void onInteract(PlayerInteractEvent event) {
        ShootingPlayer p = playerMgr.getShootingPlayer(event.getPlayer());
        Action a = event.getAction();

        if (a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK)
            interactLeftClick(p);
        else if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK)
            interactRightClick(p);
    }

    public void interactLeftClick(ShootingPlayer p) {
        Gun gun = p.getGun();
        if (gun.canFire())
            gun.fire();
    }

    public void interactRightClick(ShootingPlayer p) {
        Boost boost = p.getBoost();
        if (boost.canBoost())
            boost.boost();
    }
}
