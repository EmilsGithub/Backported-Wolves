package net.emilsg.backported_wolves.world;

import net.emilsg.backported_wolves.BackportedWolves;
import net.emilsg.backported_wolves.config.BackportedWolvesConfig;
import net.emilsg.backported_wolves.tags.ModBiomeTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.SpawnSettings;

public class ModEntitySpawning {

    public static void addAndRemoveSpawns() {

        int spawnWeight = BackportedWolvesConfig.getInstance().getInteger(BackportedWolvesConfig.SPAWN_WEIGHT);

        SpawnSettings.SpawnEntry fourFour = new SpawnSettings.SpawnEntry(EntityType.WOLF, spawnWeight, 4, 4);
        SpawnSettings.SpawnEntry twoFour = new SpawnSettings.SpawnEntry(EntityType.WOLF, spawnWeight, 2, 4);
        SpawnSettings.SpawnEntry fourEight = new SpawnSettings.SpawnEntry(EntityType.WOLF, spawnWeight, 4, 8);
        SpawnSettings.SpawnEntry one = new SpawnSettings.SpawnEntry(EntityType.WOLF, spawnWeight, 1, 1);

        BiomeModifications
                .create(new Identifier(BackportedWolves.MOD_ID, "change_wolves"))
                .add(ModificationPhase.REMOVALS, biomeSelectionContext -> true,
                        (biomeSelectionContext, biomeModificationContext) -> biomeModificationContext.getSpawnSettings().removeSpawnsOfEntityType(EntityType.WOLF)
                )
                .add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> biomeSelectionContext.hasTag(ModBiomeTags.SPAWNS_PALE_WOLF),
                        (biomeSelectionContext, biomeModificationContext) -> biomeModificationContext.getSpawnSettings()
                                .addSpawn(SpawnGroup.CREATURE, fourFour))
                .add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> biomeSelectionContext.hasTag(ModBiomeTags.SPAWNS_WOODS_WOLF),
                        (biomeSelectionContext, biomeModificationContext) -> biomeModificationContext.getSpawnSettings()
                                .addSpawn(SpawnGroup.CREATURE, fourFour))
                .add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> biomeSelectionContext.hasTag(ModBiomeTags.SPAWNS_ASHEN_WOLF),
                        (biomeSelectionContext, biomeModificationContext) -> biomeModificationContext.getSpawnSettings()
                                .addSpawn(SpawnGroup.CREATURE, fourFour))
                .add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> biomeSelectionContext.hasTag(ModBiomeTags.SPAWNS_BLACK_WOLF),
                        (biomeSelectionContext, biomeModificationContext) -> biomeModificationContext.getSpawnSettings()
                                .addSpawn(SpawnGroup.CREATURE, twoFour))
                .add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> biomeSelectionContext.hasTag(ModBiomeTags.SPAWNS_CHESTNUT_WOLF),
                        (biomeSelectionContext, biomeModificationContext) -> biomeModificationContext.getSpawnSettings()
                                .addSpawn(SpawnGroup.CREATURE, twoFour))
                .add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> biomeSelectionContext.hasTag(ModBiomeTags.SPAWNS_RUSTY_WOLF),
                        (biomeSelectionContext, biomeModificationContext) -> biomeModificationContext.getSpawnSettings()
                                .addSpawn(SpawnGroup.CREATURE, twoFour))
                .add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> biomeSelectionContext.hasTag(ModBiomeTags.SPAWNS_SPOTTED_WOLF),
                        (biomeSelectionContext, biomeModificationContext) -> biomeModificationContext.getSpawnSettings()
                                .addSpawn(SpawnGroup.CREATURE, fourEight))
                .add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> biomeSelectionContext.hasTag(ModBiomeTags.SPAWNS_STRIPED_WOLF),
                        (biomeSelectionContext, biomeModificationContext) -> biomeModificationContext.getSpawnSettings()
                                .addSpawn(SpawnGroup.CREATURE, fourEight))
                .add(ModificationPhase.REPLACEMENTS, biomeSelectionContext -> biomeSelectionContext.hasTag(ModBiomeTags.SPAWNS_SNOWY_WOLF),
                        (biomeSelectionContext, biomeModificationContext) -> biomeModificationContext.getSpawnSettings()
                                .addSpawn(SpawnGroup.CREATURE, one)
                );

        BackportedWolves.LOGGER.info(BackportedWolves.MOD_ID + " registered wolf spawns successfully.");
    }
}
