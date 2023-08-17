package me.nixuge.reflections;

import java.lang.reflect.Method;

import org.bukkit.Bukkit;

import me.nixuge.utils.logger.LogLevel;
import me.nixuge.utils.logger.Logger;

public abstract class ReflectionUtilsAbstract {
    private static String versionBase = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",")
            .split(",")[3];
    private static String version = versionBase + ".";
    
    protected static Class<?> getNMSClass(String nmsClassString) {
        String name = "net.minecraft.server." + version + nmsClassString;
        Class<?> nmsClass;
        try {
            nmsClass = Class.forName(name);
            return nmsClass;
        } catch (ClassNotFoundException e) {
            Logger.log(LogLevel.ERROR, "Error getting NMS class using reflections!");
            e.printStackTrace();
        }
        return null;
    }

    protected static Method getMethodFromNameArgcount(Class<?> clazz, String methodName, int argCount) {
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (method.getName().equals(methodName) && method.getParameterCount() == argCount) {
                return method;
            }
        }

        return null;
    }

    protected static Method getMethodFromNameArgs(Class<?> clazz, String methodName, Class<?>... args) {
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (!method.getName().equals(methodName))
                continue;

            Class<?>[] paramTypes = method.getParameterTypes();
            int argLength = paramTypes.length;

            if (argLength != args.length)
                continue;

            boolean isMatching = true;

            for (int i = 0; i < argLength; i++) {
                Class<?> currentArg = args[i];
                Class<?> currentParam = paramTypes[i];

                if (args[i] == null) // Just skip if null
                    continue;

                if (currentArg != currentParam) {
                    isMatching = false;
                    break;
                }
            }
            if (isMatching)
                return method;
        }

        return null;
    }
}
