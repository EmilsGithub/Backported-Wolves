package net.emilsg.backported_wolves.mixin;

import net.emilsg.backported_wolves.BackportedWolves;
import net.emilsg.backported_wolves.variant.WolfEntityVariant;
import net.minecraft.client.render.entity.WolfEntityRenderer;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WolfEntityRenderer.class)
public class WolfEntityRendererMixin {
    private static final Identifier WILD_PALE_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf.png");
    private static final Identifier TAMED_PALE_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_tame.png");
    private static final Identifier ANGRY_PALE_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_angry.png");

    private static final Identifier WILD_ASHEN_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_ashen.png");
    private static final Identifier TAMED_ASHEN_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_ashen_tame.png");
    private static final Identifier ANGRY_ASHEN_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_ashen_angry.png");

    private static final Identifier WILD_BLACK_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_black.png");
    private static final Identifier TAMED_BLACK_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_black_tame.png");
    private static final Identifier ANGRY_BLACK_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_black_angry.png");

    private static final Identifier WILD_CHESTNUT_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_chestnut.png");
    private static final Identifier TAMED_CHESTNUT_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_chestnut_tame.png");
    private static final Identifier ANGRY_CHESTNUT_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_chestnut_angry.png");

    private static final Identifier WILD_RUSTY_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_rusty.png");
    private static final Identifier TAMED_RUSTY_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_rusty_tame.png");
    private static final Identifier ANGRY_RUSTY_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_rusty_angry.png");

    private static final Identifier WILD_SNOWY_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_snowy.png");
    private static final Identifier TAMED_SNOWY_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_snowy_tame.png");
    private static final Identifier ANGRY_SNOWY_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_snowy_angry.png");

    private static final Identifier WILD_SPOTTED_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_spotted.png");
    private static final Identifier TAMED_SPOTTED_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_spotted_tame.png");
    private static final Identifier ANGRY_SPOTTED_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_spotted_angry.png");

    private static final Identifier WILD_STRIPED_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_striped.png");
    private static final Identifier TAMED_STRIPED_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_striped_tame.png");
    private static final Identifier ANGRY_STRIPED_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_striped_angry.png");

    private static final Identifier WILD_WOODS_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_woods.png");
    private static final Identifier TAMED_WOODS_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_woods_tame.png");
    private static final Identifier ANGRY_WOODS_TEXTURE = new Identifier(BackportedWolves.MOD_ID, "textures/entity/wolf/wolf_woods_angry.png");


    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    public void getWolfTexture (WolfEntity wolfEntity, CallbackInfoReturnable<Identifier> cir) {
        NbtCompound compound = new NbtCompound();
        wolfEntity.writeNbt(compound);

        if (compound.contains("Variant")) {
            int wolfVariant = compound.getInt("Variant");
            Identifier customTexture = getCustomTextureForVariant(wolfVariant, wolfEntity);
            cir.setReturnValue(customTexture);
        }
    }


    private Identifier getCustomTextureForVariant(int variant, WolfEntity wolfEntity) {
        Identifier texture;

        if(wolfEntity.isTamed()) {
            texture = switch (variant) {
                default -> TAMED_PALE_TEXTURE;
                case 1 -> TAMED_WOODS_TEXTURE;
                case 2 -> TAMED_ASHEN_TEXTURE;
                case 3 -> TAMED_BLACK_TEXTURE;
                case 4 -> TAMED_CHESTNUT_TEXTURE;
                case 5 -> TAMED_RUSTY_TEXTURE;
                case 6 -> TAMED_SPOTTED_TEXTURE;
                case 7 -> TAMED_STRIPED_TEXTURE;
                case 8 -> TAMED_SNOWY_TEXTURE;
            };
        } else {
            if(wolfEntity.hasAngerTime()) {
                texture = switch (variant) {
                    default -> ANGRY_PALE_TEXTURE;
                    case 1 -> ANGRY_WOODS_TEXTURE;
                    case 2 -> ANGRY_ASHEN_TEXTURE;
                    case 3 -> ANGRY_BLACK_TEXTURE;
                    case 4 -> ANGRY_CHESTNUT_TEXTURE;
                    case 5 -> ANGRY_RUSTY_TEXTURE;
                    case 6 -> ANGRY_SPOTTED_TEXTURE;
                    case 7 -> ANGRY_STRIPED_TEXTURE;
                    case 8 -> ANGRY_SNOWY_TEXTURE;
                };
            } else {
                texture = switch (variant) {
                    default -> WILD_PALE_TEXTURE;
                    case 1 -> WILD_WOODS_TEXTURE;
                    case 2 -> WILD_ASHEN_TEXTURE;
                    case 3 -> WILD_BLACK_TEXTURE;
                    case 4 -> WILD_CHESTNUT_TEXTURE;
                    case 5 -> WILD_RUSTY_TEXTURE;
                    case 6 -> WILD_SPOTTED_TEXTURE;
                    case 7 -> WILD_STRIPED_TEXTURE;
                    case 8 -> WILD_SNOWY_TEXTURE;
                };
            }
        }

        return texture;
    }
}
