package me.nixuge.config.inner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import lombok.Getter;
import me.nixuge.config.ConfigPart;

@Getter
public class MapConfig extends ConfigPart {
    public MapConfig(ConfigurationSection conf) {
        super(conf);
        this.rand = new Random();
        world = Bukkit.getWorld(getString("world", "world"));
        spawns = new ArrayList<>();
        for (String coords : getStringList("spawns", null)) { // crash when not set 
            spawns.add(getXYZfromString(coords).asLocation(world));
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
