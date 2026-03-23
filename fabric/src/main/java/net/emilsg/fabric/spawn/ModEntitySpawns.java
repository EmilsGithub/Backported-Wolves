package net.emilsg.fabric.spawn;

import net.emilsg.backported_wolves.tags.ModBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntitySpawns {

    public static void registerSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.tag(ModBiomeTags.SPAWNS_ASHEN_WOLF), MobCategory.CREATURE, EntityType.WOLF, 16, 4, 4);
        BiomeModifications.addSpawn(BiomeSelectors.tag(ModBiomeTags.SPAWNS_BLACK_WOLF), MobCategory.CREATURE, EntityType.WOLF, 16, 2, 4);
        BiomeModifications.addSpawn(BiomeSelectors.tag(ModBiomeTags.SPAWNS_CHESTNUT_WOLF), MobCategory.CREATURE, EntityType.WOLF, 16, 2, 4);
        BiomeModifications.addSpawn(BiomeSelectors.tag(ModBiomeTags.SPAWNS_PALE_WOLF), MobCategory.CREATURE, EntityType.WOLF, 16, 2, 4);
        BiomeModifications.addSpawn(BiomeSelectors.tag(ModBiomeTags.SPAWNS_RUSTY_WOLF), MobCategory.CREATURE, EntityType.WOLF, 16, 2, 4);
        BiomeModifications.addSpawn(BiomeSelectors.tag(ModBiomeTags.SPAWNS_SNOWY_WOLF), MobCategory.CREATURE, EntityType.WOLF, 16, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.tag(ModBiomeTags.SPAWNS_SPOTTED_WOLF), MobCategory.CREATURE, EntityType.WOLF, 16, 4, 8);
        BiomeModifications.addSpawn(BiomeSelectors.tag(ModBiomeTags.SPAWNS_STRIPED_WOLF), MobCategory.CREATURE, EntityType.WOLF, 16, 4, 8);
        BiomeModifications.addSpawn(BiomeSelectors.tag(ModBiomeTags.SPAWNS_WOODS_WOLF), MobCategory.CREATURE, EntityType.WOLF, 16, 4, 4);
    }

}
