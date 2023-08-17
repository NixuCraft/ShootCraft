package me.nixuge.config.inner;

import java.util.ArrayList;
import java.util.List;

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
        world = Bukkit.getWorld(getString("world", "world"));
        spawns = new ArrayList<>();
        for (String coords : getStringList("spawns", null)) { // crash when not set 
            spawns.add(getXYZfromString(coords).asLocation(world));
        } 
    }

    private final World world;
    private final List<Location> spawns;
}
