package me.nixuge.utils;

public class TextUtils {
    public static String secondsToMMSS(int seconds) {
        return String.format("%02d:%02d", (seconds / 60) % 60, seconds % 60);
    }

    public static String ticksToSeconds(int ticks) {
        return String.format("%02d.%02d", (ticks / 20) % 20, ticks % 20);
    }
}
