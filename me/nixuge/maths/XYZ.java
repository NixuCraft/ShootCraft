package me.nixuge.maths;

import org.bukkit.Location;
import org.bukkit.World;

public class XYZ {
    private final int x, y, z;

    public XYZ(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public XYZ(Location loc) {
        this.x = loc.getBlockX();
        this.y = loc.getBlockY();
        this.z = loc.getBlockZ();
    }

    public Location asLocation(World world) {
        return new Location(world, x, y, z);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getZ() {
        return z;
    }
}
