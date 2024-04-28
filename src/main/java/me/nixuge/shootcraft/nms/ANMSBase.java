package me.nixuge.shootcraft.nms;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import lombok.val;
import net.minecraft.server.v1_8_R3.Packet;

public class ANMSBase {
    protected static void sendPacket(Packet<?> packet, Player p) {
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
    }
    protected static void sendPacketAllPlayers(Packet<?> packet) {
        for (val p : Bukkit.getOnlinePlayers()) {
            sendPacket(packet, p);
        }
    }
}
