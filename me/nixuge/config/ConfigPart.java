package me.nixuge.config;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import me.nixuge.maths.XYZ;
import me.nixuge.utils.logger.LogLevel;
import me.nixuge.utils.logger.Logger;

public abstract class ConfigPart {
    // Bit of a verbose & inefficient class
    // but useful for easy access and only meant to be called 1x at startup anyways
    
    // NOTE: using a private/protected ConfigurationSection instead of having 
    // as a parameter isn't that good of an idea
    // -> need error checking at the start of every method to make sure its there
    // -> not good for like the "MapConfig" class where the used conf changes

    private final ConfigurationSection conf;

    public ConfigPart(ConfigurationSection conf) {
        this.conf = conf;
    }

    protected int getInt(String key, int defaultVal) {
        int value;
        try {
            value = conf.getInt(key);
        } catch (Exception e) {
            Logger.log(LogLevel.WARNING, "Invalid key \"" + key + "\" (int). Please check your config. Defaulted to \"" + defaultVal + "\"");
            value = defaultVal;
        }
        return value;
    }

    protected String getString(String key, String defaultVal) {
        String value;
        try {
            value = conf.getString(key);
        } catch (Exception e) {
            Logger.log(LogLevel.WARNING, "Invalid key \"" + key + "\" (String). Please check your config. Defaulted to \"" + defaultVal + "\"");
            value = defaultVal;
        }
        return value;
    }

    protected boolean getBoolean(String key, boolean defaultVal) {
        boolean value;
        try {
            value = conf.getBoolean(key);
        } catch (Exception e) {
            Logger.log(LogLevel.WARNING, "Invalid value for key \"" + key + "\" (boolean). Please check your config. Defaulted to \"" + defaultVal + "\"");
            value = defaultVal;
        }
        return value;
    }

    protected List<String> getStringList(String key, List<String> defaultVal) {
        List<String> value;
        try {
            value = conf.getStringList(key);
        } catch (Exception e) {
            Logger.log(LogLevel.WARNING, "Invalid key \"" + key + "\" (String list). Please check your config. Defaulted to \"" + defaultVal + "\"");
            value = defaultVal;
        }
        return value;
    }

    protected List<Integer> getIntList(String key, List<Integer> defaultVal) {
        List<Integer> value;
        try {
            value = conf.getIntegerList(key);
        } catch (Exception e) {
            Logger.log(LogLevel.WARNING, "Invalid key \"" + key + "\" (Integer list). Please check your config. Defaulted to \"" + defaultVal + "\"");
            value = defaultVal;
        }
        return value;
    }

    protected XYZ getXYZfromString(String str) {
        String[] parts = str.split(" ");
        if (parts.length < 3) {
            // Bukkit.broadcastMessage(Lang.get("errors.mapconfig.wrongparseXYZ1", parts.length, str));
            return new XYZ(0, 0, 0);
        }

        int[] xyz = new int[3];

        for (int i = 0; i < 3; i++) {
            String part = parts[i];
            try {
                xyz[i] = Integer.parseInt(part);
            } catch (NumberFormatException e) {
                // Bukkit.broadcastMessage(Lang.get("errors.mapconfig.wrongparseXYZ2", part, str));
                xyz[i] = 0;
            }
        }

        return new XYZ(xyz[0], xyz[1], xyz[2]);
    }

    protected Location getLocationFromString(String str, World world) {
        String[] xyz_yp = str.split(", ");
        if (xyz_yp.length != 2) {
            // Bukkit.broadcastMessage(Lang.get("errors.mapconfig.wrongparselocation1", xyz_yp.length, str));
            return new Location(world, 0, 0, 0);
        } 
        XYZ coords = getXYZfromString(xyz_yp[0]);


        //raw_yp[0] = yaw, raw_yp[1] = pitch
        String[] raw_yp = xyz_yp[1].split(" ");

        int[] yp = new int[2];

        for (int i = 0; i < 2; i++) {
            String part = raw_yp[i];
            try {
                yp[i] = Integer.parseInt(part);
            } catch (NumberFormatException e) {
                // Bukkit.broadcastMessage(Lang.get("errors.mapconfig.wrongparselocation2", part, str));
                yp[i] = 0;
            }
        }

        Location finalLoc = coords.asLocation(world);
        finalLoc.setYaw(yp[0]);
        finalLoc.setPitch(yp[1]);
        return finalLoc;
    }
}
