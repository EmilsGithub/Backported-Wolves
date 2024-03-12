package net.emilsg.backported_wolves.mixin;

import net.emilsg.backported_wolves.BackportedWolves;
import net.emilsg.backported_wolves.variant.WolfEntityVariant;
import net.minecraft.client.render.entity.WolfEntityRenderer;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WolfEntityRenderer.class)
public class WolfEntityRendererMixin {
    private static final String BASE_PATH = "textures/entity/wolf/wolf";

    @Unique
    private Identifier getCustomTextureForVariant(int variant, WolfEntity wolfEntity) {
        String texture = BASE_PATH + "_" + WolfEntityVariant.byId(variant).asString();

        if (wolfEntity.isTamed()){
            texture += "_tame";
        } else if (wolfEntity.hasAngerTime()){
            texture += "_angry";
        }

        return new Identifier(BackportedWolves.MOD_ID, texture + ".png");
    }

    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    public void getWolfTexture (WolfEntity wolfEntity, CallbackInfoReturnable<Identifier> cir) {
        NbtCompound compound = new NbtCompound();
        wolfEntity.writeNbt(compound);

        if (compound.contains("Variant")) {
            int wolfVariant = compound.getInt("Variant");
            if (wolfVariant == 0 || wolfVariant >= 9){
                return;
            }
            Identifier customTexture = getCustomTextureForVariant(wolfVariant, wolfEntity);
            cir.setReturnValue(customTexture);
        }
    }
}
