package net.emilsg.backported_wolves.mixin;

import net.emilsg.backported_wolves.variant.WolfVariantHolder;
import net.emilsg.backported_wolves.tags.ModBiomeTags;
import net.emilsg.backported_wolves.variant.WolfEntityVariant;
import net.emilsg.backported_wolves.variant.WolfSoundVariant;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;


@Mixin(Wolf.class)
public abstract class WolfEntityMixin extends MobEntityMixin implements WolfVariantHolder {

    @Unique
    private static final EntityDataAccessor<String> VARIANT =
            SynchedEntityData.defineId(Wolf.class, EntityDataSerializers.STRING);

    @Unique
    private static final EntityDataAccessor<String> SOUND_VARIANT =
            SynchedEntityData.defineId(Wolf.class, EntityDataSerializers.STRING);

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void defineSynchedData(CallbackInfo ci) {
        Wolf wolf = (Wolf) (Object) this;
        wolf.getEntityData().define(VARIANT, WolfEntityVariant.PALE_WOLF.getId());
        wolf.getEntityData().define(SOUND_VARIANT, WolfSoundVariant.CLASSIC.getId());
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void writeNBT(CompoundTag tag, CallbackInfo ci) {
        tag.putString("variant", getVariantId());
        tag.putString("sound_variant", getSoundVariantId());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readNBT(CompoundTag tag, CallbackInfo ci) {
        Wolf wolf = (Wolf) (Object) this;

        String variantId = WolfEntityVariant.PALE_WOLF.getId();

        if (tag.contains("Variant")) {
            variantId = migrateOldVariantToNewId(tag.getInt("Variant"));
        } else if (tag.contains("variant")) {
            variantId = tag.getString("variant");
        }

        wolf.getEntityData().set(VARIANT, variantId);

        if (tag.contains("sound_variant")) {
            wolf.getEntityData().set(SOUND_VARIANT, tag.getString("sound_variant"));
        } else {
            wolf.getEntityData().set(SOUND_VARIANT, WolfSoundVariant.CLASSIC.getId());
        }
    }

    @Override
    protected void onInitialize(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, SpawnGroupData pSpawnData, CompoundTag pDataTag, CallbackInfoReturnable<SpawnGroupData> cir) {
        Wolf wolf = (Wolf) (Object) this;
        Holder<Biome> biome = pLevel.getBiome(wolf.blockPosition());

        List<WolfEntityVariant> matches = new ArrayList<>();

        if (biome.is(ModBiomeTags.SPAWNS_WOODS_WOLF)) matches.add(WolfEntityVariant.WOODS_WOLF);
        if (biome.is(ModBiomeTags.SPAWNS_ASHEN_WOLF)) matches.add(WolfEntityVariant.ASHEN_WOLF);
        if (biome.is(ModBiomeTags.SPAWNS_BLACK_WOLF)) matches.add(WolfEntityVariant.BLACK_WOLF);
        if (biome.is(ModBiomeTags.SPAWNS_CHESTNUT_WOLF)) matches.add(WolfEntityVariant.CHESTNUT_WOLF);
        if (biome.is(ModBiomeTags.SPAWNS_PALE_WOLF)) matches.add(WolfEntityVariant.PALE_WOLF);
        if (biome.is(ModBiomeTags.SPAWNS_RUSTY_WOLF)) matches.add(WolfEntityVariant.RUSTY_WOLF);
        if (biome.is(ModBiomeTags.SPAWNS_SPOTTED_WOLF)) matches.add(WolfEntityVariant.SPOTTED_WOLF);
        if (biome.is(ModBiomeTags.SPAWNS_STRIPED_WOLF)) matches.add(WolfEntityVariant.STRIPED_WOLF);
        if (biome.is(ModBiomeTags.SPAWNS_SNOWY_WOLF)) matches.add(WolfEntityVariant.SNOWY_WOLF);

        WolfEntityVariant variant = matches.isEmpty()
                ? WolfEntityVariant.PALE_WOLF
                : matches.get(wolf.getRandom().nextInt(matches.size()));

        setVariant(variant);
        setSoundVariant(WolfSoundVariant.getRandom());
    }

    @Inject(
            method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/Wolf;",
            at = @At("RETURN")
    )
    private void onCreateChild(ServerLevel serverLevel, AgeableMob otherParent, CallbackInfoReturnable<Wolf> cir) {
        Wolf child = cir.getReturnValue();
        if (child == null) return;

        Wolf self = (Wolf) (Object) this;
        WolfVariantHolder selfData = (WolfVariantHolder) self;
        WolfVariantHolder otherData = (WolfVariantHolder) otherParent;
        WolfVariantHolder childData = (WolfVariantHolder) child;

        String variant = self.getRandom().nextBoolean()
                ? selfData.getVariantId()
                : otherData.getVariantId();

        childData.setVariant(WolfEntityVariant.valueOf(
                switch (variant) {
                    case "minecraft:woods" -> "WOODS_WOLF";
                    case "minecraft:ashen" -> "ASHEN_WOLF";
                    case "minecraft:black" -> "BLACK_WOLF";
                    case "minecraft:chestnut" -> "CHESTNUT_WOLF";
                    case "minecraft:rusty" -> "RUSTY_WOLF";
                    case "minecraft:spotted" -> "SPOTTED_WOLF";
                    case "minecraft:striped" -> "STRIPED_WOLF";
                    case "minecraft:snowy" -> "SNOWY_WOLF";
                    default -> "PALE_WOLF";
                }
        ));
        childData.setSoundVariant(WolfSoundVariant.getRandom());
    }

    @Inject(method = "getAmbientSound", at = @At("RETURN"), cancellable = true)
    private void getAmbientSound(CallbackInfoReturnable<SoundEvent> cir) {
        Wolf wolf = (Wolf) (Object) this;
        WolfSoundVariant soundVariant = WolfSoundVariant.fromId(getSoundVariantId());

        if (wolf.isAngry()) {
            cir.setReturnValue(soundVariant.getGrowlSound());
        } else if (wolf.getRandom().nextInt(3) != 0) {
            cir.setReturnValue(soundVariant.getBarkSound());
        } else {
            cir.setReturnValue(wolf.isTame() && wolf.getHealth() < 10.0F
                    ? soundVariant.getWhineSound()
                    : soundVariant.getPantingSound());
        }
    }

    @Inject(method = "getDeathSound", at = @At("RETURN"), cancellable = true)
    private void getDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        cir.setReturnValue(WolfSoundVariant.fromId(getSoundVariantId()).getDeathSound());
    }

    @Inject(method = "getHurtSound", at = @At("RETURN"), cancellable = true)
    private void getHurtSound(CallbackInfoReturnable<SoundEvent> cir) {
        cir.setReturnValue(WolfSoundVariant.fromId(getSoundVariantId()).getHurtSound());
    }

    @Override
    public String getVariantId() {
        return ((Wolf) (Object) this).getEntityData().get(VARIANT);
    }

    @Override
    public void setVariant(WolfEntityVariant variant) {
        ((Wolf) (Object) this).getEntityData().set(VARIANT, variant.getId());
    }

    @Override
    public String getSoundVariantId() {
        return ((Wolf) (Object) this).getEntityData().get(SOUND_VARIANT);
    }

    @Override
    public void setSoundVariant(WolfSoundVariant variant) {
        ((Wolf) (Object) this).getEntityData().set(SOUND_VARIANT, variant.getId());
    }

    @Unique
    private static String migrateOldVariantToNewId(int id) {
        return switch (id & 255) {
            case 0 -> "minecraft:pale";
            case 1 -> "minecraft:woods";
            case 2 -> "minecraft:ashen";
            case 3 -> "minecraft:black";
            case 4 -> "minecraft:chestnut";
            case 5 -> "minecraft:rusty";
            case 6 -> "minecraft:spotted";
            case 7 -> "minecraft:striped";
            case 8 -> "minecraft:snowy";
            default -> WolfEntityVariant.PALE_WOLF.getId();
        };
    }
}
