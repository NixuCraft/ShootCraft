package me.nixuge.shootcraft.enums;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import lombok.Getter;
import me.nixuge.shootcraft.listeners.playing.BreakBlockListener;
import me.nixuge.shootcraft.listeners.playing.HotbarItemHeldListener;
import me.nixuge.shootcraft.listeners.playing.HungerHealthChangeListener;
import me.nixuge.shootcraft.listeners.playing.PlayerClickListener;
import me.nixuge.shootcraft.listeners.playing.PlayerInventoryListener;
import me.nixuge.shootcraft.listeners.playing.PlayerJoinLeaveListener;
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

    private static Logger logger = ShootCraft.getInstance().getLogger();

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
        logger.info("Setting gamestate to " + gameState.toString());

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
                logger.info("Registered listener: " + c.getSimpleName());
            }
        } catch (Exception e) {
            logger.severe("Exception happened while registering listener !");
            e.printStackTrace();
        }
        logger.info("Done setting gamestate");
    }

}
