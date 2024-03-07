package net.emilsg.backported_wolves.world;

import net.emilsg.backported_wolves.BackportedWolves;
import net.emilsg.backported_wolves.tags.ModBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawning {

    public static void addSpawns() {

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SAVANNA_PLATEAU), SpawnGroup.CREATURE, EntityType.WOLF, 8, 4, 8);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.WOODED_BADLANDS), SpawnGroup.CREATURE, EntityType.WOLF, 8, 4, 8);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SPARSE_JUNGLE), SpawnGroup.CREATURE, EntityType.WOLF, 8, 2, 4);

        BackportedWolves.LOGGER.info(BackportedWolves.MOD_ID + " registered wolf spawns successfully.");
    }
}
