package com.pepedevs.corelib.holograms.utils;

import com.google.common.collect.Sets;
import org.bukkit.entity.EntityType;

import java.util.*;
import java.util.stream.Collectors;

public final class HologramEntities {

    private static final Map<String, EntityType> ENTITY_TYPE_ALIASES = new HashMap<>();
    private static final Set<String> ENTITY_TYPE_BLACKLIST;

    static {
        for (EntityType entityType : EntityType.values()) {
            ENTITY_TYPE_ALIASES.put(entityType.name().replace("_", "").toLowerCase(), entityType);
        }

        ENTITY_TYPE_BLACKLIST =
                Sets.newHashSet(
                        "ENDER_DRAGON",
                        "ARMOR_STAND",
                        "PRIMED_TNT",
                        "AREA_EFFECT_CLOUD",
                        "FISHING_HOOK",
                        "GIANT",
                        "EVOKER_FANGS",
                        "EXPERIENCE_ORB",
                        "LEASH_HITCH",
                        "DROPPED_ITEM",
                        "ITEM_FRAME",
                        "GLOW_ITEM_FRAME",
                        "THROWN_EXP_BOTTLE",
                        "SPLASH_POTION",
                        "SPECTRAL_ARROW",
                        "LLAMA_SPIT",
                        "MARKER",
                        "LIGHTNING",
                        "TRIDENT",
                        "PAINTING",
                        "PLAYER",
                        "MINECART",
                        "MINECART_CHEST",
                        "MINECART_COMMAND",
                        "MINECART_FURNACE",
                        "MINECART_HOPPER",
                        "MINECART_MOB_SPAWNER",
                        "MINECART_TNT",
                        "COMPLEX_PART",
                        "WEATHER",
                        "TIPPED_ARROW",
                        "UNKNOWN");
    }

    public static List<EntityType> getAllowedEntityTypes() {
        return Arrays.stream(EntityType.values())
                .filter(entityType -> !ENTITY_TYPE_BLACKLIST.contains(entityType.name()))
                .collect(Collectors.toList());
    }

    public static List<String> getAllowedEntityTypeNames() {
        return Arrays.stream(EntityType.values())
                .map(EntityType::name)
                .filter(name -> !ENTITY_TYPE_BLACKLIST.contains(name))
                .collect(Collectors.toList());
    }

    public static boolean isAllowed(EntityType entityType) {
        return !ENTITY_TYPE_BLACKLIST.contains(entityType.name());
    }

    public static EntityType parseEntityType(String string) {
        EntityType entityType = ENTITY_TYPE_ALIASES.get(string.replace("_", "").toLowerCase());
        if (ENTITY_TYPE_BLACKLIST.contains(entityType.name())) {
            return null;
        }
        return entityType;
    }
}
