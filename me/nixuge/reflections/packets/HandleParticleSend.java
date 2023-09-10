package me.nixuge.reflections.packets;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import me.nixuge.reflections.PacketHandlerAbstract;
import me.nixuge.utils.logger.LogLevel;
import me.nixuge.utils.logger.Logger;

public class HandleParticleSend extends PacketHandlerAbstract {
    private static Class<?> packetClass = getNMSClass("PacketPlayOutWorldParticles");
    private static Class<?> enumClass = getNMSClass("EnumParticle");

    private static Constructor<?> packetConstructor = getPacketConstructor();
    private static Method enumValueOf = getEnumValueOfMethod();

    private static Constructor<?> getPacketConstructor() {
        try {
            return packetClass.getConstructor(enumClass, boolean.class, float.class, float.class, float.class,
                    float.class, float.class, float.class, float.class, int.class, int[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Method getEnumValueOfMethod() {
        try {
            return enumClass.getMethod("valueOf", String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private ParticleEnum particle;
    private float x, y, z;
    private float xPlus, yPlus, zPlus;
    private int speed;
    private int count;
    private int[] data;

    public HandleParticleSend(ParticleEnum particle,
            double x, double y, double z,
            double xPlus, double yPlus, double zPlus,
            int speed, int count, int[] data) {
        this.particle = particle;
        this.x = (float) x;
        this.y = (float) y;
        this.z = (float) z;
        this.xPlus = (float) xPlus;
        this.yPlus = (float) yPlus;
        this.zPlus = (float) zPlus;
        this.speed = speed;
        this.count = count;
        this.data = data;
    }

    // enum_particle, true,
    // (float) x, (float) y, (float) z,
    // (float) xPlus, (float) yPlus, (float) zPlus,
    // 0, count, data);

    public Object getPacket() {
        try {
            Object enumParticle = enumValueOf.invoke(enumClass, particle.toString());
            Object packet = packetConstructor.newInstance(
                    enumParticle, true,
                    x, y, z, xPlus, yPlus, zPlus,
                    speed, count, data);
            return packet;
        } catch (Exception e) {
            Logger.log(LogLevel.ERROR, "Failed to create new PacketPlayOutWorldParticles");
            e.printStackTrace();
        }
        return null;
    }
}

