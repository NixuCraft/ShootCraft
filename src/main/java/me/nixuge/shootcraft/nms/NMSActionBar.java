package me.nixuge.shootcraft.nms;

import org.bukkit.entity.Player;

import lombok.val;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class NMSActionBar {
    public static void sendActionBar(Player p, String text) {
        val packet = new PacketPlayOutChat(new ChatComponentText(text), (byte)2);
        ANMSBase.sendPacket(packet, p);
    }
    public static void sendActionBarAll(String text) {
        val packet = new PacketPlayOutChat(new ChatComponentText(text), (byte)2);
        ANMSBase.sendPacketAllPlayers(packet);
    }
}
