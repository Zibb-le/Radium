package com.pepedevs.corelib.nms;

public enum EntityStatus {

    ENTITY_HONEY_BLOCK_SLIDE_PARTICLE(53),
    SNOW_BALL_PARTICLES(3),
    EGG_CRACK_PARTICLES(3),
    FISHING_HOOK_PULL(31),
    TIPPED_ARROW_PARTICLE_EFFECT(0),
    FIREWORK_ROCKET_EXPLOSION_TRIGGER(17),
    LIVING_ENTITY_HURT_ANIMATION_SOUND(2),
    LIVING_ENTITY_DEATH_ANIMATION_SOUND(3),
    LIVING_ENTITY_SHIELD_BLOCK_SOUND(29),
    LIVING_ENTITY_SHIELD_BREAK_SOUND(30),
    LIVING_ENTITY_THORNS_SOUND_W_HURT_ANIMATION_SOUND(33),
    LIVING_ENTITY_TOTEM_UNDYING_SOUND_ANIMATION(35),
    LIVING_ENTITY_HURT_DROWN_ANIMATION_SOUND(36),
    LIVING_ENTITY_HURT_BURN_ANIMATION_SOUND(37),
    LIVING_ENTITY_HURT_BUSH_ANIMATION_SOUND(44),
    LIVING_ENTITY_PORTAL_PARTICLES(46),
    LIVING_ENTITY_MAIN_HAND_ITEM_BREAK_SOUND_ANIMATION(47),
    LIVING_ENTITY_OFF_HAND_ITEM_BREAK_SOUND_ANIMATION(48),
    LIVING_ENTITY_MAIN_HEAD_ITEM_BREAK_SOUND_ANIMATION(49),
    LIVING_ENTITY_MAIN_CHEST_ITEM_BREAK_SOUND_ANIMATION(50),
    LIVING_ENTITY_MAIN_LEGGINGS_ITEM_BREAK_SOUND_ANIMATION(51),
    LIVING_ENTITY_MAIN_BOOTS_ITEM_BREAK_SOUND_ANIMATION(52),
    LIVING_ENTITY_HONEY_BLOCK_FALL_PARTICLE(54),
    LIVING_ENTITY_SWAP_HAND_ITEMS(55),
    LIVING_ENTITY_HURT_FREEZE_ANIMATION_SOUND(57),
    LIVING_ENTITY_DEATH_SMOKE_PARTICLE(60),
    PLAYER_MARK_ITEM_FINISHED(9),
    PLAYER_ENABLE_REDUCED_DEBUG_SCREEN(22),
    PLAYER_DISABLE_REDUCED_DEBUG_SCREEN(23),
    PLAYER_OP_PERMS_LEVEL_0(24),
    PLAYER_OP_PERMS_LEVEL_1(25),
    PLAYER_OP_PERMS_LEVEL_2(26),
    PLAYER_OP_PERMS_LEVEL_3(27),
    PLAYER_OP_PERMS_LEVEL_4(28),
    PLAYER_SPAWN_CLOUD_PARTICLES(43),
    ARMOR_STAND_HIT_SOUND_COOLDOWN_RESET(32),
    MOB_SPAWN_EXPLOSION_PARTICLE(20),
    SQUID_RESET_ROTATION_0_RADIAN(19),
    DOLPHIN_HAPPY_VILLAGER_PARTICLES(38),
    ANIMAL_LOVE_MODE_PARTICLES(18),
    ABSTRACT_HORSE_TAMING_FAILED_PARTICLES(6),
    ABSTRACT_HORSE_TAMING_SUCCESS_PARTICLES(7),
    OCELOT_TAMING_FAILED_PARTICLES(40),
    OCELOT_TAMING_SUCCESS_PARTICLES(41),
    RABBIT_ROTATED_JUMPING_ANIMATION(1),
    SHEEP_EAT_GRASS_ANIMATION(10),
    FOX_CHEWING_PARTICLES(45),
    GOAT_LOWER_HEAD(58),
    GOAR_STOP_LOWERING_HEAD(59),
    TAMABLE_ANIMAL_TAMING_FAILED_PARTICLES(6),
    TAMABLE_ANIMAL_TAMING_SUCCESS_PARTICLES(7),
    WOLF_PLAY_SHAKING_ANIMATION(8),
    WOLF_STOP_SHAKING_ANIMATION(20),
    VILLAGER_MATING_PARTICLES(12),
    VILLAGER_ANGRY_PARTICLES(13),
    VILLAGER_HAPPY_PARTICLES(14),
    VILLAGER_SPLASH_PARTICLES(42),
    IRON_GOLEM_ATTACK_ANIMATION_SOUND(4),
    IRON_GOLEM_SHOW_FLOWER(11),
    IRON_GOLEM_STOP_SHOWING_POPPY(34),
    EVOKER_FANGS_ATTACK_ANIMATION(4),
    WITCH_SPAWN_MAGIC_PARTICLES(15),
    RAVAGER_START_ATTACK_ANIMATION(4),
    RAVAGER_MARK_AS_STUNNED(39),
    ZOMBIE_VILLAGER_CURE_SOUND_EFFECT(16),
    GUARDIAN_ATTACK_SOUND(21),
    MINECART_TNT(10),
    MINECART_SPAWNER_RESET_DELAY(1),
    HOGLIN_PLAY_ATTACK_ANIMATION(4),
    ZOGLIN_PLAY_ATTACK_ANIMATION(4);

    EntityStatus(int statusNumber) {
        this.STATUS_NUMBER = (byte) statusNumber;
    }
    public final byte STATUS_NUMBER;

}