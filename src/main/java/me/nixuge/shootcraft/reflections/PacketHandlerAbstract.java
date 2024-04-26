package me.nixuge.shootcraft.reflections;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.nixuge.shootcraft.reflections.send.HandleSendPacket;

public abstract class PacketHandlerAbstract extends ReflectionUtilsAbstract {
        protected abstract Object getPacket();

    public void sendPacketAllPlayers() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            sendPacketPlayer(p);
        }
    }
    
    public void sendPacketPlayer(Player p) {
        Object playerHandle = HandleUtils.getHandleField(p, "playerConnection");
        
        HandleSendPacket.send(playerHandle, getPacket());
    }
}
