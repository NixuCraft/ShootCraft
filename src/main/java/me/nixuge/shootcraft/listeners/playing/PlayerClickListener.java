package me.nixuge.shootcraft.listeners.playing;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.nixuge.shootcraft.player.Boost;
import me.nixuge.shootcraft.player.Gun;
import me.nixuge.shootcraft.player.ShootingPlayer;
import me.nixuge.shootcraft.PlayerManager;
import me.nixuge.shootcraft.ShootCraft;

public class PlayerClickListener implements Listener {
    private PlayerManager playerMgr = ShootCraft.getInstance().getPlayerMgr();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        ShootingPlayer p = playerMgr.getShootingPlayer(event.getPlayer());
        Action a = event.getAction();

        if (a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK)
            interactLeftClick(p);
        else if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK)
            interactRightClick(p);
    }

    public void interactRightClick(ShootingPlayer p) {
        Gun gun = p.getGun();
        // Logg
        if (gun.canFire())
            gun.fire();
    }

    public void interactLeftClick(ShootingPlayer p) {
        Boost boost = p.getBoost();
        if (boost.canBoost())
            boost.boost();
    }
}
