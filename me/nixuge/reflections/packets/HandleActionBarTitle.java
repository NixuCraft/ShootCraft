package me.nixuge.reflections.packets;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import me.nixuge.reflections.PacketHandlerAbstract;
import me.nixuge.utils.logger.LogLevel;
import me.nixuge.utils.logger.Logger;

public class HandleActionBarTitle extends PacketHandlerAbstract {
    private static Class<?> packetClass = getNMSClass("PacketPlayOutChat");
    private static Class<?> chatBaseComponentClass = getNMSClass("IChatBaseComponent");
    private static Class<?> chatSerializerClass = getNMSClass("IChatBaseComponent$ChatSerializer");

    private static Constructor<?> packetConstructor = getPacketConstructor();
    private static Method chatSerializerA = getChatSerializerA();

    private static Constructor<?> getPacketConstructor() {
        try {
            return packetClass.getConstructor(chatBaseComponentClass, byte.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static Method getChatSerializerA() {
        try {
            return chatSerializerClass.getMethod("a", String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String text;
    private byte type = 2; //2 = actionbar

    public HandleActionBarTitle(String text) {
        this.text = text;
    }

    public Object getPacket() {
        try {
            Object chatBaseComponent = chatSerializerA.invoke(null, "{\"text\": \"" + this.text + "\"}");
            Object packet = packetConstructor.newInstance(chatBaseComponent, this.type);
            return packet;
        } catch (Exception e) {
            Logger.log(LogLevel.ERROR, "Failed to create new PacketPlayOutChat");
            e.printStackTrace();
        }
        return null;
    }

}
