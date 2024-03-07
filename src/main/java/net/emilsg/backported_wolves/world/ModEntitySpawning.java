package net.emilsg.backported_wolves.world;

import net.emilsg.backported_wolves.BackportedWolves;
import net.emilsg.backported_wolves.tags.ModBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class ModEntitySpawning {

    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.tag(ModBiomeTags.SPAWNS_RUSTY_WOLF), SpawnGroup.CREATURE, EntityType.WOLF, 8, 2, 4);
        BiomeModifications.addSpawn(BiomeSelectors.tag(ModBiomeTags.SPAWNS_SPOTTED_WOLF), SpawnGroup.CREATURE, EntityType.WOLF, 8, 4, 8);
        BiomeModifications.addSpawn(BiomeSelectors.tag(ModBiomeTags.SPAWNS_STRIPED_WOLF), SpawnGroup.CREATURE, EntityType.WOLF, 8, 4, 8);

        BackportedWolves.LOGGER.info(BackportedWolves.MOD_ID + " registered wolf spawns successfully.");
    }
}
