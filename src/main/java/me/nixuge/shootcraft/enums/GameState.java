package me.nixuge.shootcraft.enums;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import lombok.Getter;
import me.nixuge.shootcraft.listeners.playing.BreakBlockListener;
import me.nixuge.shootcraft.listeners.playing.HotbarItemHeldListener;
import me.nixuge.shootcraft.listeners.playing.HungerHealthChangeListener;
import me.nixuge.shootcraft.listeners.playing.PlayerClickListener;
import me.nixuge.shootcraft.listeners.playing.PlayerInventoryListener;
import me.nixuge.shootcraft.listeners.playing.PlayerJoinLeaveListener;
import me.nixuge.shootcraft.utils.logger.LogLevel;
import me.nixuge.shootcraft.utils.logger.Logger;
import me.nixuge.shootcraft.ShootCraft;

public enum GameState {
    IDLING(new Class<?>[] {

    }),
    PLAYING(new Class<?>[] {
        HotbarItemHeldListener.class,
        PlayerClickListener.class,
        PlayerJoinLeaveListener.class,
        PlayerInventoryListener.class,
        HungerHealthChangeListener.class,
        BreakBlockListener.class
    });

    private final Class<?>[] classes;
    private List<Listener> instances;

    private GameState(Class<?>[] classes) {
        this.classes = classes;
        this.instances = new ArrayList<>();
    }
    
    public Class<?>[] getListeners() {
        return classes;
    }
    public List<Listener> getInstances() {
        return instances;
    }
    public void addInstance(Listener listener) {
        instances.add(listener);
    }
    public void clearInstances() {
        this.instances = new ArrayList<>();
    }

    // Not sure if this is best in here or in a separate file (eg. GameManager)
    @Getter
    private static GameState currentState;
    public static void setGameState(GameState gameState) {
        Logger.log(LogLevel.DEBUG, "Setting gamestate to " + gameState.toString());

        // unregister previous listeners
        if (currentState != null) {
            for (Listener listener : currentState.getInstances()) {
                HandlerList.unregisterAll(listener);
            }
            currentState.clearInstances();
        }

        // set new state var
        currentState = gameState;
        
        // make new instances for the new ones from the classes
        ShootCraft instance = ShootCraft.getInstance();
        Class<?>[] classes = gameState.getListeners();
        try {
            for (Class<?> c : classes) {
                Constructor<?> cons = c.getConstructor();
                Listener listener = (Listener) cons.newInstance();
                currentState.addInstance(listener);
                instance.getPluginMgr().registerEvents(listener, instance);
                Logger.log(LogLevel.DEBUG, "Registered listener: " + c.getSimpleName());
            }
        } catch (Exception e) {
            Logger.log(LogLevel.ERROR, "Exception happened while registering listener !");
            e.printStackTrace();
        }
        Logger.log(LogLevel.DEBUG, "Done setting gamestate");
    }

}
