package me.nixuge.player;

import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import lombok.Getter;
import lombok.Setter;
import me.nixuge.GameManager;
import me.nixuge.PlayerManager;
import me.nixuge.ShootCraft;
import me.nixuge.config.Config;
import me.nixuge.reflections.packets.HandleParticleSend;
import me.nixuge.reflections.packets.ParticleEnum;
import me.nixuge.utils.logger.Logger;

public class Gun {
    @Getter
    @Setter
    private int delay;

    private World world;
    private ShootingPlayer player;
    private GameManager gameMgr;
    private PlayerManager playerMgr;

    public Gun(ShootingPlayer player) {
        this.world = Config.map.getWorld();
        ShootCraft instance = ShootCraft.getInstance();
        this.playerMgr = instance.getPlayerMgr();
        this.gameMgr = instance.getGameMgr();
        this.player = player;
    }

    public boolean canFire() {
        return delay <= 0; // Could be == 0 but just in case
    }

    // public Set<ShootingPlayer> fire(GameManager gameMgr, Player p) {
    public void fire() {
        // Set<Entity> hitPlayers = new HashSet<>();
        int hitCount = 0;

        // values straight from the player
        Player p = player.getBukkitPlayer();
        Location loc = p.getLocation();
        double yaw = loc.getYaw();
        double pitch = loc.getPitch();
        double x = loc.getX();
        double y = loc.getY() + 1.5; //getY = base of feet, +1.5 = head
        double z = loc.getZ();

        // values computed
        double yMinus = Math.sin(Math.toRadians(pitch));
        double yMultiply = getYMultiplyOffset(yMinus);
        double xMinus = Math.sin(Math.toRadians(yaw)) * yMultiply;
        double zPlus = Math.cos(Math.toRadians(yaw)) * yMultiply;

        for (int i = 0; i < 40; i++) {
            x -= xMinus;
            z += zPlus;
            y -= yMinus;

            new HandleParticleSend(ParticleEnum.FIREWORKS_SPARK, x, y, z, 0, 0, 0, 10, null)
                    .sendPacketAllPlayers();

            loc = new Location(world, x, y, z);
            if (loc.getBlock().getType() != Material.AIR) {
                // If hits wall (may be bypassable if through corners)
                continue;
            }

            Collection<Entity> nearbyEntities = world.getNearbyEntities(loc, .3, .3, .3); // VALUES TO TWEAK
            if (nearbyEntities == null) {
                Logger.logBC("GOT NULL! (to remove in prod)");
                continue;
            }
                
            for (Entity e : nearbyEntities) {
                if (e instanceof Player) {
                    playerMgr.getShootingPlayer((Player)e).hit(player.getBukkitPlayer().getCustomName());
                    hitCount++;
                }
                    
            }
        }
        if (hitCount > 1) {
            gameMgr.broadcastGamePrefix(p.getCustomName() + " killed " + hitCount + " people in a single shot !");
            if (Config.lives.isLiveOnDoubleKill()) {
                player.addLife();
            }
        }
    }
    
    /**
     * This function is used to calculate the multiply offset for X/Z
     * based on how up/down the player is looking.
     * Without those, the most players can shoot at is diagonal.
     * With those, all orientations are fine
     * 
     * @param yMinus determines how many blocks far down the next particle will be
     */
    private static double getYMultiplyOffset(double yMinus) {
        // get the absolute value of yMinus
        double yMultiply = yMinus > 0 ? yMinus : -yMinus;

        // function sqrt(r² - x²)
        // makes a nice quarter cycle
        return Math.sqrt(1 - (yMultiply * yMultiply));
    }
}
