package me.nixuge.player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.Getter;
import me.nixuge.GameManager;
import me.nixuge.PlayerManager;
import me.nixuge.ShootCraft;
import me.nixuge.config.Config;
import me.nixuge.reflections.packets.HandleParticleSend;
import me.nixuge.reflections.packets.ParticleEnum;
import me.nixuge.utils.ItemBuilder;

public class Gun {
    @Getter
    private static ItemStack itemEnabled = new ItemBuilder(Material.GOLD_HOE).itemName("§2[Enabled]§r ShootGun").build();
    @Getter
    private static ItemStack itemDisabled = new ItemBuilder(Material.STICK).itemName("§7[Reloading]§r ShootGun").build();

    @Getter
    private int delay;

    private World world;
    private ShootingPlayer player;
    private ShootCraft instance;
    private GameManager gameMgr;
    private PlayerManager playerMgr;

    public Gun(ShootingPlayer player) {
        this.world = Config.map.getWorld();
        this.instance = ShootCraft.getInstance();
        this.playerMgr = instance.getPlayerMgr();
        this.gameMgr = instance.getGameMgr();
        this.player = player;
    }

    public void onRespawn() {
        setDelay(Config.spawn.getRespawnDuration());
    }
 
    public void setDelay(int newDelay) {
        player.getBukkitPlayer().getInventory().setItem(4, Gun.getItemDisabled());
        this.delay = newDelay;
        new BukkitRunnable() {
            @Override
            public void run() {
                delay--;
                if (delay == 0) {
                    player.getBukkitPlayer().getInventory().setItem(4, Gun.getItemEnabled());
                    this.cancel();
                }   
            }
        }.runTaskTimerAsynchronously(instance, 0, 1);
    }

    public boolean canFire() {
        return delay <= 0; // Could be == 0 but just in case
    }

    // public Set<ShootingPlayer> fire(GameManager gameMgr, Player p) {
    public void fire() {
        setDelay(Config.gun.getGunDelayDuration());
        
        Set<Player> hitPlayers = new HashSet<>();
        String name = player.getBukkitPlayer().getDisplayName();
        // int hitCount = 0;

        // values straight from the player
        Player p = player.getBukkitPlayer();
        Location loc = p.getLocation();
        double yaw = loc.getYaw();
        double pitch = loc.getPitch();
        double x = loc.getX();
        double y = loc.getY() + 1.5; //getY = base of feet, +1.5 = head
        double z = loc.getZ();

        // values computed
        // TODO?: see if I can divide those by 2?
        // So I can have a more accurate particle trail
        // (currently doesn't work due to yMultiply)
        double yMinus = Math.sin(Math.toRadians(pitch));
        double yMultiply = getYMultiplyOffset(yMinus) ;
        double xMinus = Math.sin(Math.toRadians(yaw)) * yMultiply;
        double zPlus = Math.cos(Math.toRadians(yaw)) * yMultiply;

        for (int i = 0; i < 60; i++) {
            x -= xMinus;
            z += zPlus;
            y -= yMinus;

            new HandleParticleSend(ParticleEnum.FIREWORKS_SPARK, x, y, z, 0, 0, 0, 0, 1, null)
                    .sendPacketAllPlayers();

            loc = new Location(world, x, y, z);
            if (loc.getBlock().getType() != Material.AIR) {
                // If hits wall (may be bypassable if through corners)
                break;
            }


            // TODO: if possible, redo this detection in a better way
            // Basically before the particles loop, calculate
            // the players that have been hit.
            // https://www.spigotmc.org/threads/hitboxes-and-ray-tracing.174358/page-3
            // https://bukkit.org/threads/using-rays-to-quickly-and-accurately-detect-hitbox-collisions.441877/

            Collection<Entity> nearbyEntities = world.getNearbyEntities(loc, .2, .2, .2); // VALUES TO TWEAK
            
                
            for (Entity e : nearbyEntities) {
                if (!(e instanceof Player)) 
                    continue;

                Player hitPlayer = (Player)e;
                if (hitPlayers.contains(hitPlayer) || hitPlayer == p)
                    continue;
                
                if(playerMgr.getShootingPlayer(hitPlayer).hit(name))
                    hitPlayers.add(hitPlayer);  
            }
        }
        int hitCount = hitPlayers.size();
        if (hitCount > 1) {
            gameMgr.broadcastGamePrefix(p.getDisplayName() + " killed " + hitCount + " people in a single shot !");
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
