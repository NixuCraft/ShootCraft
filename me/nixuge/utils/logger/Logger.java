package me.nixuge.utils.logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.nixuge.utils.TextUtils;

public class Logger {
    private static java.util.logging.Logger logger = Bukkit.getLogger();

    /**
     * Log a message to the console using the INFO LogLevel
     * @param message the message to log
     */
    public static void log(String message) {
        log(LogLevel.INFO, message);
    }

    /**
     * Log a message to the console & to players in-game using the INFO LogLevel
     * @param message the message to log
     */
    public static void logIG(String message) {
        logIG(LogLevel.INFO, message);
    }

    /**
     * Log a message to the console & broadcast to all players using the INFO LogLevel
     * @param message the message to log
     */
    public static void logBC(String message) {
        logBC(LogLevel.INFO, message);
    }

    /**
     * Log a message to the console
     * @param level level to log the message to (eg: Info, Warning, ...)
     * @param message the message to log
     */
    public static void log(LogLevel level, String message) {
        logger.log(level.getLevel(), level.getColorMessageTerm(message));
    }

    /**
     * Log a message to the console & to players in-game
     * @param level level to log the message to (eg: Info, Warning, ...)
     * @param message the message to log
     */
    public static void logIG(LogLevel level, String message) {
        logger.log(level.getLevel(), level.getColorMessageTerm(message));
        TextUtils.broadcastGame(level.getColorMessageGame(message));
    }

    /**
     * Log a message to the console & broadcast to all players
     * @param level level to log the message to (eg: Info, Warning, ...)
     * @param message the message to log
     */
    public static void logBC(LogLevel level, String message) {
        logger.log(level.getLevel(), level.getColorMessageTerm(message));
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(level.getColorMessageGame(message));
        }
    }
}
