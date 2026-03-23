package net.emilsg.backported_wolves.sound;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.emilsg.backported_wolves.BackportedWolvesCommon;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class ModSoundEvents {
    private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BackportedWolvesCommon.MOD_ID, Registries.SOUND_EVENT);

    public static final Supplier<SoundEvent> ANGRY_PANTING = registerSoundEvents("angry_panting");
    public static final Supplier<SoundEvent> BIG_PANTING = registerSoundEvents("big_panting");
    public static final Supplier<SoundEvent> CLASSIC_PANTING = registerSoundEvents("classic_panting");
    public static final Supplier<SoundEvent> CUTE_PANTING = registerSoundEvents("cute_panting");
    public static final Supplier<SoundEvent> GRUMPY_PANTING = registerSoundEvents("grumpy_panting");
    public static final Supplier<SoundEvent> PUGLIN_PANTING = registerSoundEvents("puglin_panting");
    public static final Supplier<SoundEvent> SAD_PANTING = registerSoundEvents("sad_panting");

    public static final Supplier<SoundEvent> ANGRY_BARK = registerSoundEvents("angry_bark");
    public static final Supplier<SoundEvent> BIG_BARK = registerSoundEvents("big_bark");
    public static final Supplier<SoundEvent> CLASSIC_BARK = registerSoundEvents("classic_bark");
    public static final Supplier<SoundEvent> CUTE_BARK = registerSoundEvents("cute_bark");
    public static final Supplier<SoundEvent> GRUMPY_BARK = registerSoundEvents("grumpy_bark");
    public static final Supplier<SoundEvent> PUGLIN_BARK = registerSoundEvents("puglin_bark");
    public static final Supplier<SoundEvent> SAD_BARK = registerSoundEvents("sad_bark");

    public static final Supplier<SoundEvent> ANGRY_DEATH = registerSoundEvents("angry_death");
    public static final Supplier<SoundEvent> BIG_DEATH = registerSoundEvents("big_death");
    public static final Supplier<SoundEvent> CLASSIC_DEATH = registerSoundEvents("classic_death");
    public static final Supplier<SoundEvent> CUTE_DEATH = registerSoundEvents("cute_death");
    public static final Supplier<SoundEvent> GRUMPY_DEATH = registerSoundEvents("grumpy_death");
    public static final Supplier<SoundEvent> PUGLIN_DEATH = registerSoundEvents("puglin_death");
    public static final Supplier<SoundEvent> SAD_DEATH = registerSoundEvents("sad_death");

    public static final Supplier<SoundEvent> ANGRY_HURT = registerSoundEvents("angry_hurt");
    public static final Supplier<SoundEvent> BIG_HURT = registerSoundEvents("big_hurt");
    public static final Supplier<SoundEvent> CLASSIC_HURT = registerSoundEvents("classic_hurt");
    public static final Supplier<SoundEvent> CUTE_HURT = registerSoundEvents("cute_hurt");
    public static final Supplier<SoundEvent> GRUMPY_HURT = registerSoundEvents("grumpy_hurt");
    public static final Supplier<SoundEvent> PUGLIN_HURT = registerSoundEvents("puglin_hurt");
    public static final Supplier<SoundEvent> SAD_HURT = registerSoundEvents("sad_hurt");

    public static final Supplier<SoundEvent> ANGRY_GROWL = registerSoundEvents("angry_growl");
    public static final Supplier<SoundEvent> BIG_GROWL = registerSoundEvents("big_growl");
    public static final Supplier<SoundEvent> CLASSIC_GROWL = registerSoundEvents("classic_growl");
    public static final Supplier<SoundEvent> CUTE_GROWL = registerSoundEvents("cute_growl");
    public static final Supplier<SoundEvent> GRUMPY_GROWL = registerSoundEvents("grumpy_growl");
    public static final Supplier<SoundEvent> PUGLIN_GROWL = registerSoundEvents("puglin_growl");
    public static final Supplier<SoundEvent> SAD_GROWL = registerSoundEvents("sad_growl");

    public static final Supplier<SoundEvent> ANGRY_WHINE = registerSoundEvents("angry_whine");
    public static final Supplier<SoundEvent> BIG_WHINE = registerSoundEvents("big_whine");
    public static final Supplier<SoundEvent> CLASSIC_WHINE = registerSoundEvents("classic_whine");
    public static final Supplier<SoundEvent> CUTE_WHINE = registerSoundEvents("cute_whine");
    public static final Supplier<SoundEvent> GRUMPY_WHINE = registerSoundEvents("grumpy_whine");
    public static final Supplier<SoundEvent> PUGLIN_WHINE = registerSoundEvents("puglin_whine");
    public static final Supplier<SoundEvent> SAD_WHINE = registerSoundEvents("sad_whine");

    public static RegistrySupplier<SoundEvent> registerSoundEvents(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BackportedWolvesCommon.MOD_ID, name)));
    }

    public static void register() {
        SOUNDS.register();
    }
}
