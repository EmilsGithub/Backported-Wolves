package net.emilsg.backported_wolves.mixin;

import net.emilsg.backported_wolves.BackportedWolvesCommon;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(value = WolfRenderer.class)
public class WolfEntityRendererMixin {
    @Unique private static final ResourceLocation WILD_PALE_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf.png");
    @Unique private static final ResourceLocation TAMED_PALE_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_tame.png");
    @Unique private static final ResourceLocation ANGRY_PALE_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_angry.png");

    @Unique private static final ResourceLocation WILD_ASHEN_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_ashen.png");
    @Unique private static final ResourceLocation TAMED_ASHEN_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_ashen_tame.png");
    @Unique private static final ResourceLocation ANGRY_ASHEN_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_ashen_angry.png");

    @Unique private static final ResourceLocation WILD_BLACK_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_black.png");
    @Unique private static final ResourceLocation TAMED_BLACK_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_black_tame.png");
    @Unique private static final ResourceLocation ANGRY_BLACK_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_black_angry.png");

    @Unique private static final ResourceLocation WILD_CHESTNUT_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_chestnut.png");
    @Unique private static final ResourceLocation TAMED_CHESTNUT_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_chestnut_tame.png");
    @Unique private static final ResourceLocation ANGRY_CHESTNUT_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_chestnut_angry.png");

    @Unique private static final ResourceLocation WILD_RUSTY_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_rusty.png");
    @Unique private static final ResourceLocation TAMED_RUSTY_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_rusty_tame.png");
    @Unique private static final ResourceLocation ANGRY_RUSTY_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_rusty_angry.png");

    @Unique private static final ResourceLocation WILD_SNOWY_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_snowy.png");
    @Unique private static final ResourceLocation TAMED_SNOWY_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_snowy_tame.png");
    @Unique private static final ResourceLocation ANGRY_SNOWY_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_snowy_angry.png");

    @Unique private static final ResourceLocation WILD_SPOTTED_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_spotted.png");
    @Unique private static final ResourceLocation TAMED_SPOTTED_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_spotted_tame.png");
    @Unique private static final ResourceLocation ANGRY_SPOTTED_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_spotted_angry.png");

    @Unique private static final ResourceLocation WILD_STRIPED_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_striped.png");
    @Unique private static final ResourceLocation TAMED_STRIPED_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_striped_tame.png");
    @Unique private static final ResourceLocation ANGRY_STRIPED_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_striped_angry.png");

    @Unique private static final ResourceLocation WILD_WOODS_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_woods.png");
    @Unique private static final ResourceLocation TAMED_WOODS_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_woods_tame.png");
    @Unique private static final ResourceLocation ANGRY_WOODS_TEXTURE = new ResourceLocation(BackportedWolvesCommon.MOD_ID, "textures/entity/wolf/wolf_woods_angry.png");


    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/animal/Wolf;)Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"), cancellable = true)
    public void getWolfTexture (Wolf wolfEntity, CallbackInfoReturnable<ResourceLocation> cir) {
        CompoundTag compound = new CompoundTag();
        wolfEntity.addAdditionalSaveData(compound);

        if (compound.contains("variant")) {
            String  wolfVariant = compound.getString("variant");
            ResourceLocation customTexture = getCustomTextureForVariant(wolfVariant, wolfEntity);
            cir.setReturnValue(customTexture);
        }
    }


    @Unique
    private ResourceLocation getCustomTextureForVariant(String variant, Wolf wolfEntity) {
        ResourceLocation texture;

        if(wolfEntity.isTame()) {
            texture = switch (variant) {
                case "minecraft:woods" -> TAMED_WOODS_TEXTURE;
                case "minecraft:ashen" -> TAMED_ASHEN_TEXTURE;
                case "minecraft:black" -> TAMED_BLACK_TEXTURE;
                case "minecraft:chestnut" -> TAMED_CHESTNUT_TEXTURE;
                case "minecraft:rusty" -> TAMED_RUSTY_TEXTURE;
                case "minecraft:spotted" -> TAMED_SPOTTED_TEXTURE;
                case "minecraft:striped" -> TAMED_STRIPED_TEXTURE;
                case "minecraft:snowy" -> TAMED_SNOWY_TEXTURE;
                default -> TAMED_PALE_TEXTURE;
            };
        } else {
            if(wolfEntity.getRemainingPersistentAngerTime() > 0) {
                texture = switch (variant) {
                    case "minecraft:woods" -> ANGRY_WOODS_TEXTURE;
                    case "minecraft:ashen" -> ANGRY_ASHEN_TEXTURE;
                    case "minecraft:black" -> ANGRY_BLACK_TEXTURE;
                    case "minecraft:chestnut" -> ANGRY_CHESTNUT_TEXTURE;
                    case "minecraft:rusty" -> ANGRY_RUSTY_TEXTURE;
                    case "minecraft:spotted" -> ANGRY_SPOTTED_TEXTURE;
                    case "minecraft:striped" -> ANGRY_STRIPED_TEXTURE;
                    case "minecraft:snowy" -> ANGRY_SNOWY_TEXTURE;
                    default -> ANGRY_PALE_TEXTURE;
                };
            } else {
                texture = switch (variant) {
                    case "minecraft:woods" -> WILD_WOODS_TEXTURE;
                    case "minecraft:ashen" -> WILD_ASHEN_TEXTURE;
                    case "minecraft:black" -> WILD_BLACK_TEXTURE;
                    case "minecraft:chestnut" -> WILD_CHESTNUT_TEXTURE;
                    case "minecraft:rusty" -> WILD_RUSTY_TEXTURE;
                    case "minecraft:spotted" -> WILD_SPOTTED_TEXTURE;
                    case "minecraft:striped" -> WILD_STRIPED_TEXTURE;
                    case "minecraft:snowy" -> WILD_SNOWY_TEXTURE;
                    default -> WILD_PALE_TEXTURE;
                };
            }
        }

        return texture;
    }
}
