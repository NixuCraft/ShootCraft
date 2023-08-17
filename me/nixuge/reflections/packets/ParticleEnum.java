package me.nixuge.reflections.packets;

public enum ParticleEnum {
    // Bukkit basically doesn't have a proper
    // particle enum that works on 1.8 - 1.12
    // ((it technically does but it's behind NMS & need reflections so no))
    // so making my own enum with only the ones I need.
    FIREWORKS_SPARK,
    BLOCK_CRACK,
    FLAME,
    ENCHANTMENT_TABLE
}
