package me.nixuge.utils;

import org.bukkit.entity.Player;

import me.nixuge.reflections.packets.HandleActionBarTitle;

public class TitleUtils {
    public static void sendActionBar(Player p, String text) {
        new HandleActionBarTitle(text).sendPacketPlayer(p);
    }
}
