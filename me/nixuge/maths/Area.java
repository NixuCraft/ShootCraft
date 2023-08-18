package me.nixuge.maths;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Area {
    private final Vector corner1, corner2;

    public Area(Location corner1, Location corner2) {
        this.corner1 = new Vector(Math.min(corner1.getBlockX(), corner2.getBlockX()), Math.min(corner1.getBlockY(), corner2.getBlockY()), Math.min(corner1.getBlockZ(), corner2.getBlockZ())); 
        this.corner2 = new Vector(Math.max(corner1.getBlockX(), corner2.getBlockX()), Math.max(corner1.getBlockY(), corner2.getBlockY()), Math.max(corner1.getBlockZ(), corner2.getBlockZ())); 
    }
    public Area(Vector corner1, Vector corner2) {
        this.corner1 = new Vector(Math.min(corner1.getX(), corner2.getX()), Math.min(corner1.getY(), corner2.getY()), Math.min(corner1.getZ(), corner2.getZ())); 
        this.corner2 = new Vector(Math.max(corner1.getX(), corner2.getX()), Math.max(corner1.getY(), corner2.getY()), Math.max(corner1.getZ(), corner2.getZ())); 
    }
    public Area(Player player) {
        this.corner1 = player.getLocation().toVector().add(new Vector(-0.3, 0, -0.3));
        this.corner2 = player.getLocation().toVector().add(new Vector(0.3, 1.8, 0.3));
    }

    public boolean containsBlock(Location loc) {
        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();
        return
            x >= corner1.getX() && x <= corner2.getX() && 
            y >= corner1.getY() && y <= corner2.getY() && 
            z >= corner1.getZ() && z <= corner2.getZ();
    }

    // public double collides(Ray ray, double tmin, double tmax) {
    //     for (int i = 0; i < 3; i++) {
    //         // invert the direction
    //         double direction = 1 / ray.direction(i);
            
    //         // get the directions?
    //         double t0 = (corner1(i) - ray.origin(i)) * direction;
    //         double t1 = (corner2(i) - ray.origin(i)) * direction;
    //         // Invert to get the minimals right minimals if < 0
    //         if (direction < 0) {
    //             double t = t0;
    //             t0 = t1;
    //             t1 = t;
    //         }

    //         tmin = (t0 > tmin) ? t0 : tmin;
    //         tmax = (t1 < tmax) ? t1 : tmax;
    //         if (tmax <= tmin) return -1;
    //     }
    //     return tmin;
    // }

    public double corner1(int i) {
        switch (i) {
            case 0:
                return corner1.getX();
            case 1:
                return corner1.getY();
            case 2:
                return corner1.getZ();
            default:
                return 0;
        }
    }
        
    public double corner2(int i) {
        switch (i) {
            case 0:
                return corner2.getX();
            case 1:
                return corner2.getY();
            case 2:
                return corner2.getZ();
            default:
                return 0;
        }
    }
}
