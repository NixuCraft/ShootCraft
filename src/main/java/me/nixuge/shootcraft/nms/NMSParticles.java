package me.nixuge.shootcraft.nms;

import lombok.val;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class NMSParticles {
    public static void makeParticle(ParticleEnum particle,
            double x, double y, double z,
            double xPlus, double yPlus, double zPlus,
            int speed, int count, int[] data) {
        
        EnumParticle enumParticle = EnumParticle.valueOf(particle.toString());

        val packet = new PacketPlayOutWorldParticles(
            enumParticle, true,
            (float)x, (float)y, (float)z, 
            (float)xPlus, (float)yPlus, (float)zPlus,
            speed, count, data
        );

        ANMSBase.sendPacketAllPlayers(packet);

    }
}
