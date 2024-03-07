package net.emilsg.backported_wolves.tags;

import net.emilsg.backported_wolves.BackportedWolves;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class ModBiomeTags {
    //public static final Tag.Identified<Biome> SPAWNS_PALE_WOLF = create(BackportedWolves.MOD_ID, "spawns_pale_wolf");
    public static final Tag.Identified<Biome> SPAWNS_WOODS_WOLF = create(BackportedWolves.MOD_ID, "spawns_woods_wolf");
    public static final Tag.Identified<Biome> SPAWNS_ASHEN_WOLF = create(BackportedWolves.MOD_ID, "spawns_ashen_wolf");
    public static final Tag.Identified<Biome> SPAWNS_BLACK_WOLF = create(BackportedWolves.MOD_ID, "spawns_black_wolf");
    public static final Tag.Identified<Biome> SPAWNS_CHESTNUT_WOLF = create(BackportedWolves.MOD_ID, "spawns_chestnut_wolf");
    public static final Tag.Identified<Biome> SPAWNS_RUSTY_WOLF = create(BackportedWolves.MOD_ID, "spawns_rusty_wolf");
    public static final Tag.Identified<Biome> SPAWNS_SPOTTED_WOLF = create(BackportedWolves.MOD_ID, "spawns_spotted_wolf");
    public static final Tag.Identified<Biome> SPAWNS_STRIPED_WOLF = create(BackportedWolves.MOD_ID, "spawns_striped_wolf");
    public static final Tag.Identified<Biome> SPAWNS_SNOWY_WOLF = create(BackportedWolves.MOD_ID, "spawns_snowy_wolf");


    private static Tag.Identified<Biome> create(String nameSpace, String path) {
        return TagFactory.BIOME.create(new Identifier(nameSpace, path));
    }
}
