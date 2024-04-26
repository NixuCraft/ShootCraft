package me.nixuge.shootcraft.reflections.send;

import java.lang.reflect.Method;

import me.nixuge.shootcraft.reflections.ReflectionUtilsAbstract;
import me.nixuge.shootcraft.utils.logger.LogLevel;
import me.nixuge.shootcraft.utils.logger.Logger;

public class HandleSendPacket extends ReflectionUtilsAbstract {
    public static Class<?> playerConnectionClass = getNMSClass("PlayerConnection");
    public static Method method = getMethodFromNameArgcount(playerConnectionClass, "sendPacket", 1);

    public static Object send(Object handle, Object packet) {
        try {
            method.invoke(handle, packet);

        } catch (Exception e) {
            Logger.log(LogLevel.ERROR, "Failed to send SendPacket");
            e.printStackTrace();
        }

        return null;
    }
}
