package me.nixuge.shootcraft.config.inner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import lombok.Getter;
import me.nixuge.configurator.ConfigPart;

@Getter
public class MapConfig extends ConfigPart {
    public MapConfig() {
        super("map");
        this.rand = new Random();
        world = Bukkit.getWorld(getString(rootConfig, "world", "world"));
        spawns = new ArrayList<>();
        for (String coords : getStringList(rootConfig, "spawns", null)) { // crash when not set 
            spawns.add(getLocationFromString(coords, world));
        } 
    }

    // TODO: eventually move somewhere else
    // & add check to not spawn if player nearby
    public Location getRandomSpawn() {
        return spawns.get(rand.nextInt(spawns.size()));
    }

    private final Random rand;
    private final World world;
    private final List<Location> spawns;
}
