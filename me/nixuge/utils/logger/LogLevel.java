package me.nixuge.utils.logger;

import java.util.logging.Level;

public enum LogLevel {
    DEBUG(Level.INFO, "§7", "\u001B[90m", false), // See if will change or not
    INFO(Level.INFO, "§b", "\u001B[36m"),
    WARNING(Level.WARNING, "§e", "\u001B[33m"),
    ERROR(Level.SEVERE, "§c", "\u001B[31m", false);

    public static final String ANSI_RESET = "\u001B[0m";
    private final Level level;
    private final String chatColor;
    private final String terminalColor;
    private final boolean colorReset;

    private LogLevel(Level level, String chatColor, String terminalColor) {
        this(level, chatColor, terminalColor, true);
    }

    private LogLevel(Level level, String chatColor, String terminalColor, boolean colorReset) {
        this.level = level;
        this.chatColor = chatColor;
        this.terminalColor = terminalColor;
        this.colorReset = colorReset;
    }

    public Level getLevel() {
        return level;
    }

    public String getColorMessageGame(String message) {
        return getColorPrefixGame() + message + "§r";
    }

    public String getColorMessageTerm(String message) {
        return getColorPrefixTerm() + message + ANSI_RESET;
    }

    private String getColorPrefixGame() {
        String baseString = chatColor + "[ShootCraft] ";
        if (colorReset)
            baseString += "§r";

        return baseString;
    }

    private String getColorPrefixTerm() {
        String baseString = terminalColor + "[ShootCraft] ";
        if (colorReset)
            baseString += ANSI_RESET;

        return baseString;
    }

}
