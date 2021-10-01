package dev.ainterr.minecraft.blockshuffle

import org.bukkit.Material
import org.bukkit.Material.*

val UnobtainableItems = setOf(
    BARRIER,
    BEDROCK,
    CAVE_AIR,
    CHAIN_COMMAND_BLOCK,
    COMMAND_BLOCK,
    COMMAND_BLOCK_MINECART,
    FROSTED_ICE,
    JIGSAW,
    MELON_STEM,
    MOVING_PISTON,
    PLAYER_HEAD,
    PLAYER_WALL_HEAD,
    REPEATING_COMMAND_BLOCK,
    SPAWNER,
    STRUCTURE_BLOCK
)

val EndItems = setOf(
    BLACK_SHULKER_BOX,
    BLUE_SHULKER_BOX,
    BROWN_SHULKER_BOX,
    CHORUS_FLOWER,
    CHORUS_FRUIT,
    CHORUS_PLANT,
    CYAN_SHULKER_BOX,
    DRAGON_HEAD,
    DRAGON_WALL_HEAD,
    END_ROD,
    END_STONE,
    END_STONE_BRICKS,
    END_STONE_BRICK_SLAB,
    END_STONE_BRICK_STAIRS,
    END_STONE_BRICK_WALL,
    GRAY_SHULKER_BOX,
    GREEN_SHULKER_BOX,
    LIGHT_BLUE_SHULKER_BOX,
    LIGHT_GRAY_SHULKER_BOX,
    LIME_SHULKER_BOX,
    MAGENTA_SHULKER_BOX,
    ORANGE_SHULKER_BOX,
    PINK_SHULKER_BOX,
    PURPLE_SHULKER_BOX,
    PURPUR_BLOCK,
    PURPUR_PILLAR,
    PURPUR_SLAB,
    PURPUR_STAIRS,
    RED_SHULKER_BOX,
    SHULKER_BOX,
    WHITE_SHULKER_BOX,
    YELLOW_SHULKER_BOX
)

val UnrealisticItems = setOf(
    BEACON,
    BLACK_GLAZED_TERRACOTTA,
    BLACK_TERRACOTTA,
    BLUE_GLAZED_TERRACOTTA,
    BLUE_TERRACOTTA,
    BRAIN_CORAL,
    BRAIN_CORAL_BLOCK,
    BRAIN_CORAL_FAN,
    BRAIN_CORAL_WALL_FAN,
    BROWN_GLAZED_TERRACOTTA,
    BROWN_TERRACOTTA,
    BUBBLE_CORAL,
    BUBBLE_CORAL_BLOCK,
    BUBBLE_CORAL_FAN,
    BUBBLE_CORAL_WALL_FAN,
    COCOA,
    CONDUIT,
    CREEPER_HEAD,
    CREEPER_WALL_HEAD,
    CYAN_GLAZED_TERRACOTTA,
    CYAN_TERRACOTTA,
    DARK_PRISMARINE,
    DARK_PRISMARINE_SLAB,
    DARK_PRISMARINE_STAIRS,
    DEAD_BRAIN_CORAL,
    DEAD_BRAIN_CORAL_BLOCK,
    DEAD_BRAIN_CORAL_FAN,
    DEAD_BRAIN_CORAL_WALL_FAN,
    DEAD_BUBBLE_CORAL,
    DEAD_BUBBLE_CORAL_BLOCK,
    DEAD_BUBBLE_CORAL_FAN,
    DEAD_BUBBLE_CORAL_WALL_FAN,
    DEAD_FIRE_CORAL,
    DEAD_FIRE_CORAL_BLOCK,
    DEAD_FIRE_CORAL_FAN,
    DEAD_FIRE_CORAL_WALL_FAN,
    DEAD_HORN_CORAL,
    DEAD_HORN_CORAL_BLOCK,
    DEAD_HORN_CORAL_FAN,
    DEAD_HORN_CORAL_WALL_FAN,
    DEAD_TUBE_CORAL,
    DEAD_TUBE_CORAL_BLOCK,
    DEAD_TUBE_CORAL_FAN,
    DEAD_TUBE_CORAL_WALL_FAN,
    DIAMOND_BLOCK,
    DRAGON_EGG,
    ENCHANTING_TABLE,
    ENDER_CHEST,
    END_CRYSTAL,
    END_PORTAL,
    END_PORTAL_FRAME,
    FIRE_CORAL,
    FIRE_CORAL_BLOCK,
    FIRE_CORAL_FAN,
    FIRE_CORAL_WALL_FAN,
    GRAY_GLAZED_TERRACOTTA,
    GRAY_TERRACOTTA,
    GREEN_GLAZED_TERRACOTTA,
    GREEN_TERRACOTTA,
    HONEY_BLOCK,
    HORN_CORAL,
    HORN_CORAL_BLOCK,
    HORN_CORAL_FAN,
    HORN_CORAL_WALL_FAN,
    INFESTED_CHISELED_STONE_BRICKS,
    INFESTED_COBBLESTONE,
    INFESTED_CRACKED_STONE_BRICKS,
    INFESTED_MOSSY_STONE_BRICKS,
    INFESTED_STONE,
    INFESTED_STONE_BRICKS,
    LIGHT_BLUE_GLAZED_TERRACOTTA,
    LIGHT_BLUE_TERRACOTTA,
    LIGHT_GRAY_GLAZED_TERRACOTTA,
    LIGHT_GRAY_TERRACOTTA,
    LIME_GLAZED_TERRACOTTA,
    LIME_TERRACOTTA,
    MAGENTA_GLAZED_TERRACOTTA,
    MAGENTA_TERRACOTTA,
    ORANGE_GLAZED_TERRACOTTA,
    ORANGE_TERRACOTTA,
    PINK_GLAZED_TERRACOTTA,
    PINK_TERRACOTTA,
    PODZOL,
    POTTED_WITHER_ROSE,
    PRISMARINE,
    PRISMARINE_BRICKS,
    PRISMARINE_BRICK_SLAB,
    PRISMARINE_BRICK_STAIRS,
    PRISMARINE_SLAB,
    PRISMARINE_STAIRS,
    PRISMARINE_WALL,
    PURPLE_GLAZED_TERRACOTTA,
    PURPLE_TERRACOTTA,
    RED_GLAZED_TERRACOTTA,
    RED_SAND,
    RED_TERRACOTTA,
    SKELETON_SKULL,
    SLIME_BLOCK,
    SPONGE,
    STICKY_PISTON,
    TERRACOTTA,
    TUBE_CORAL,
    TUBE_CORAL_BLOCK,
    TUBE_CORAL_FAN,
    TUBE_CORAL_WALL_FAN,
    TURTLE_EGG,
    WET_SPONGE,
    WHITE_GLAZED_TERRACOTTA,
    WHITE_TERRACOTTA,
    WITHER_ROSE,
    WITHER_SKELETON_SKULL,
    YELLOW_GLAZED_TERRACOTTA,
    YELLOW_TERRACOTTA,
    ZOMBIE_HEAD,
    ZOMBIE_WALL_HEAD
)

val DefaultBlacklist = UnobtainableItems union EndItems union UnrealisticItems
val AllItems = Material.values().toList()
val AllBlocks = AllItems.filter(Material::isBlock)

val DefaultTargetBlocks = AllBlocks.minus(DefaultBlacklist)
