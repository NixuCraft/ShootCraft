package me.nixuge.shootcraft.reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import me.nixuge.shootcraft.utils.logger.LogLevel;
import me.nixuge.shootcraft.utils.logger.Logger;

public class HandleUtils {
    public static Object getHandle(Object object) {
        try {
            Method getHandle = object.getClass().getMethod("getHandle");
            return getHandle.invoke(object);

        } catch (Exception e) {
            Logger.log(LogLevel.ERROR, "Failed to get handle!");
            e.printStackTrace();
        }
        return null;
    }

    public static Object getHandleField(Object object, String field) {
        try {
            Object handle = getHandle(object);

            Field conField = handle.getClass().getField(field);
            return conField.get(handle);

        } catch (IllegalAccessException | IllegalArgumentException | SecurityException | NoSuchFieldException e) {
            Logger.log(LogLevel.ERROR, "Failed to get handle field!");
            e.printStackTrace();
        }
        return null;
    }

    public static Object callMethodFromHandle(Object handle, String methodName, Object... parameters) {
        try {
            Method method = handle.getClass().getMethod(methodName);
            Object result = method.invoke(method, parameters);
            return result;

        } catch (IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException
                | NoSuchMethodException e) {
            Logger.log(LogLevel.ERROR, "Failed to call handle method");
            e.printStackTrace();
        }

        return null;
    }
}
