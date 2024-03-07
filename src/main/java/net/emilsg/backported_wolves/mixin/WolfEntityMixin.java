package net.emilsg.backported_wolves.mixin;

import net.emilsg.backported_wolves.tags.ModBiomeTags;
import net.emilsg.backported_wolves.variant.WolfEntityVariant;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WolfEntity.class)
public abstract class WolfEntityMixin extends MobEntityMixin {

    @Unique
    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(WolfEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Inject(method = "initDataTracker", at = @At("HEAD"))
    public void initTracker (CallbackInfo ci) {
        WolfEntity wolfEntity = (WolfEntity) (Object) this;
        wolfEntity.getDataTracker().startTracking(VARIANT, 0);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeNBTData (NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("Variant", getTypeVariant());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readNBTData (NbtCompound nbt, CallbackInfo ci) {
        WolfEntity wolfEntity = (WolfEntity) (Object) this;
        wolfEntity.getDataTracker().set(VARIANT, nbt.getInt("Variant"));
    }

    @Override
    protected void onInitialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, NbtCompound entityNbt, CallbackInfoReturnable<EntityData> cir) {
        WolfEntity wolfEntity = (WolfEntity) (Object) this;
        RegistryEntry<Biome> registryEntry = world.getBiome(wolfEntity.getBlockPos());
        WolfEntityVariant variant = WolfEntityVariant.byId(WolfEntityVariant.PALE_WOLF.getId());

        if(registryEntry.isIn(ModBiomeTags.SPAWNS_WOODS_WOLF)) {
            variant = WolfEntityVariant.WOODS_WOLF;
        } else if(registryEntry.isIn(ModBiomeTags.SPAWNS_ASHEN_WOLF)) {
            variant = WolfEntityVariant.ASHEN_WOLF;
        } else if(registryEntry.isIn(ModBiomeTags.SPAWNS_BLACK_WOLF)) {
            variant = WolfEntityVariant.BLACK_WOLF;
        } else if(registryEntry.isIn(ModBiomeTags.SPAWNS_CHESTNUT_WOLF)) {
            variant = WolfEntityVariant.CHESTNUT_WOLF;
        } else if(registryEntry.isIn(ModBiomeTags.SPAWNS_RUSTY_WOLF)) {
            variant = WolfEntityVariant.RUSTY_WOLF;
        } else if(registryEntry.isIn(ModBiomeTags.SPAWNS_SPOTTED_WOLF)) {
            variant = WolfEntityVariant.SPOTTED_WOLF;
        } else if(registryEntry.isIn(ModBiomeTags.SPAWNS_STRIPED_WOLF)) {
            variant = WolfEntityVariant.STRIPED_WOLF;
        } else if(registryEntry.isIn(ModBiomeTags.SPAWNS_SNOWY_WOLF)) {
            variant = WolfEntityVariant.SNOWY_WOLF;
        }

        this.setVariant(variant);
    }

    @Inject(
            method = "createChild(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/PassiveEntity;)Lnet/minecraft/entity/passive/WolfEntity;",
            at = @At("RETURN")
    )
    private void onCreateChild(ServerWorld serverWorld, PassiveEntity passiveEntity, CallbackInfoReturnable<WolfEntity> cir) {
        WolfEntity child = cir.getReturnValue();
        WolfEntity wolfEntity = (WolfEntity) (Object) this;

        NbtCompound childNbt = new NbtCompound();
        child.writeNbt(childNbt);

        NbtCompound nbtParent = new NbtCompound();
        (wolfEntity).writeCustomDataToNbt(nbtParent);
        NbtCompound nbtOtherParent = new NbtCompound();
        passiveEntity.writeCustomDataToNbt(nbtOtherParent);

        int variant = wolfEntity.getRandom().nextBoolean() ? nbtParent.getInt("Variant") : nbtOtherParent.getInt("Variant");

        child.getDataTracker().set(VARIANT, variant & 255);

        childNbt.putInt("Variant", variant);

        child.readCustomDataFromNbt(childNbt);
    }

    public WolfEntityVariant getVariant() {
        return WolfEntityVariant.byId(getTypeVariant() & 255);
    }

    public int getTypeVariant() {
        WolfEntity wolfEntity = (WolfEntity) (Object) this;
        return wolfEntity.getDataTracker().get(VARIANT);
    }

    public void setVariant(WolfEntityVariant variant) {
        WolfEntity wolfEntity = (WolfEntity) (Object) this;
        wolfEntity.getDataTracker().set(VARIANT, variant.getId() & 255);
    }

}
