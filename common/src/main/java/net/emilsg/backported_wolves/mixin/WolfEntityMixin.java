package net.emilsg.backported_wolves.mixin;

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
public abstract class WolfEntityMixin extends MobEntityMixin {

    @Unique
    private static final EntityDataAccessor<String> VARIANT = SynchedEntityData.defineId(Wolf.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<String> SOUND_VARIANT = SynchedEntityData.defineId(Wolf.class, EntityDataSerializers.STRING);

    @Inject(method = "defineSynchedData", at = @At("HEAD"))
    public void initTracker (CallbackInfo ci) {
        Wolf wolfEntity = (Wolf) (Object) this;
        wolfEntity.getEntityData().define(VARIANT, WolfEntityVariant.PALE_WOLF.getId());
        wolfEntity.getEntityData().define(SOUND_VARIANT, WolfSoundVariant.CLASSIC.getId());
    }

    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
    public void writeNBTData (CompoundTag pCompound, CallbackInfo ci) {
        pCompound.putString("variant", getTypeVariant());
        pCompound.putString("sound_variant", getSoundTypeVariant());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    public void readNBTData(CompoundTag pCompound, CallbackInfo ci) {
        Wolf wolfEntity = (Wolf) (Object) this;

        String variantId = WolfEntityVariant.PALE_WOLF.getId();

        if (pCompound.contains("Variant")) {
            variantId = migrateOldVariantToNewId(pCompound.getInt("Variant"));
            pCompound.remove("Variant");
            pCompound.putString("variant", variantId);
        } else {
            variantId = pCompound.getString("variant");
        }

        wolfEntity.getEntityData().set(VARIANT, variantId);

        if (pCompound.contains("sound_variant")) wolfEntity.getEntityData().set(SOUND_VARIANT, pCompound.getString("sound_variant"));
        else wolfEntity.getEntityData().set(SOUND_VARIANT, WolfSoundVariant.CLASSIC.getId());
    }

    @Inject(method = "getAmbientSound", at = @At("RETURN"), cancellable = true)
    public void newAmbientSounds (CallbackInfoReturnable<SoundEvent> cir) {
        Wolf wolfEntity = (Wolf) (Object) this;
        WolfSoundVariant soundVariant = WolfSoundVariant.fromId(this.getSoundTypeVariant());

        if (wolfEntity.isAngry()) {
            cir.setReturnValue(soundVariant.getGrowlSound());
        } else if (wolfEntity.getRandom().nextInt(3) != 0) {
            cir.setReturnValue(soundVariant.getBarkSound());
        } else {
            cir.setReturnValue(wolfEntity.isTame() && wolfEntity.getHealth() < 10.0F ? soundVariant.getWhineSound() : soundVariant.getPantingSound());
        }
    }

    @Inject(method = "getDeathSound", at = @At("RETURN"), cancellable = true)
    public void newDeathSounds (CallbackInfoReturnable<SoundEvent> cir) {
        WolfSoundVariant soundVariant = WolfSoundVariant.fromId(this.getSoundTypeVariant());
        cir.setReturnValue(soundVariant.getDeathSound());
    }

    @Inject(method = "getHurtSound", at = @At("RETURN"), cancellable = true)
    public void newHurtSounds (CallbackInfoReturnable<SoundEvent> cir) {
        WolfSoundVariant soundVariant = WolfSoundVariant.fromId(this.getSoundTypeVariant());
        cir.setReturnValue(soundVariant.getHurtSound());
    }

    @Override
    protected void onInitialize(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, SpawnGroupData pSpawnData, CompoundTag pDataTag, CallbackInfoReturnable<SpawnGroupData> cir) {
        Wolf wolfEntity = (Wolf) (Object) this;
        Holder<Biome> registryEntry = pLevel.getBiome(wolfEntity.getOnPos());

        List<WolfEntityVariant> matches = new ArrayList<>();

        if (registryEntry.is(ModBiomeTags.SPAWNS_WOODS_WOLF)) matches.add(WolfEntityVariant.WOODS_WOLF);
        if (registryEntry.is(ModBiomeTags.SPAWNS_ASHEN_WOLF)) matches.add(WolfEntityVariant.ASHEN_WOLF);
        if (registryEntry.is(ModBiomeTags.SPAWNS_BLACK_WOLF)) matches.add(WolfEntityVariant.BLACK_WOLF);
        if (registryEntry.is(ModBiomeTags.SPAWNS_CHESTNUT_WOLF)) matches.add(WolfEntityVariant.CHESTNUT_WOLF);
        if (registryEntry.is(ModBiomeTags.SPAWNS_PALE_WOLF)) matches.add(WolfEntityVariant.PALE_WOLF);
        if (registryEntry.is(ModBiomeTags.SPAWNS_RUSTY_WOLF)) matches.add(WolfEntityVariant.RUSTY_WOLF);
        if (registryEntry.is(ModBiomeTags.SPAWNS_SPOTTED_WOLF)) matches.add(WolfEntityVariant.SPOTTED_WOLF);
        if (registryEntry.is(ModBiomeTags.SPAWNS_STRIPED_WOLF)) matches.add(WolfEntityVariant.STRIPED_WOLF);
        if (registryEntry.is(ModBiomeTags.SPAWNS_SNOWY_WOLF)) matches.add(WolfEntityVariant.SNOWY_WOLF);

        WolfEntityVariant variant = matches.isEmpty()
                ? WolfEntityVariant.PALE_WOLF
                : matches.get(wolfEntity.getRandom().nextInt(matches.size()));

        this.setVariant(variant);
        this.setSoundVariant(WolfSoundVariant.getRandom());
    }

    @Inject(
            method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/Wolf;",
            at = @At("RETURN")
    )
    private void onCreateChild(ServerLevel pLevel, AgeableMob pOtherParent, CallbackInfoReturnable<Wolf> cir) {
        Wolf child = cir.getReturnValue();
        if (child == null) return;

        Wolf wolfEntity = (Wolf) (Object) this;

        CompoundTag nbtParent = new CompoundTag();
        wolfEntity.addAdditionalSaveData(nbtParent);

        CompoundTag nbtOtherParent = new CompoundTag();
        pOtherParent.addAdditionalSaveData(nbtOtherParent);

        String variant = wolfEntity.getRandom().nextBoolean() ? nbtParent.getString("variant") : nbtOtherParent.getString("variant");

        WolfSoundVariant soundVariant = WolfSoundVariant.getRandom();

        child.getEntityData().set(VARIANT, variant);
        child.getEntityData().set(SOUND_VARIANT, soundVariant.getId());

        CompoundTag childNbt = new CompoundTag();
        child.addAdditionalSaveData(childNbt);
        childNbt.putString("variant", variant);
        childNbt.putString("sound_variant", soundVariant.getId());
        child.readAdditionalSaveData(childNbt);
    }

    @Unique
    public String getTypeVariant() {
        Wolf wolfEntity = (Wolf) (Object) this;
        return wolfEntity.getEntityData().get(VARIANT);
    }

    @Unique
    public void setVariant(WolfEntityVariant variant) {
        Wolf wolfEntity = (Wolf) (Object) this;
        wolfEntity.getEntityData().set(VARIANT, variant.getId());
    }

    @Unique
    public String getSoundTypeVariant() {
        Wolf wolfEntity = (Wolf) (Object) this;
        return wolfEntity.getEntityData().get(SOUND_VARIANT);
    }

    @Unique
    public void setSoundVariant(WolfSoundVariant variant) {
        Wolf wolfEntity = (Wolf) (Object) this;
        wolfEntity.getEntityData().set(SOUND_VARIANT, variant.getId());
    }

    //Fix old stuff:
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
