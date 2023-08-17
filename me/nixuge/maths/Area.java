package me.nixuge.maths;

import org.bukkit.Location;

public class Area {
    private final XYZ corner1;
    private final XYZ corner2;

    public Area(Location corner1, Location corner2) {
        this.corner1 = new XYZ(Math.min(corner1.getBlockX(), corner2.getBlockX()), Math.min(corner1.getBlockY(), corner2.getBlockY()), Math.min(corner1.getBlockZ(), corner2.getBlockZ())); 
        this.corner2 = new XYZ(Math.max(corner1.getBlockX(), corner2.getBlockX()), Math.max(corner1.getBlockY(), corner2.getBlockY()), Math.max(corner1.getBlockZ(), corner2.getBlockZ())); 
    }
    public Area(XYZ corner1, XYZ corner2) {
        this.corner1 = new XYZ(Math.min(corner1.getX(), corner2.getX()), Math.min(corner1.getY(), corner2.getY()), Math.min(corner1.getZ(), corner2.getZ())); 
        this.corner2 = new XYZ(Math.max(corner1.getX(), corner2.getX()), Math.max(corner1.getY(), corner2.getY()), Math.max(corner1.getZ(), corner2.getZ())); 
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
}
