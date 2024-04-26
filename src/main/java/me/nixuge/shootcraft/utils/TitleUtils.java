package me.nixuge.shootcraft.utils;

import org.bukkit.entity.Player;

import me.nixuge.shootcraft.reflections.packets.HandleActionBarTitle;

public class TitleUtils {
    public static void sendActionBar(Player p, String text) {
        new HandleActionBarTitle(text).sendPacketPlayer(p);
    }
}
