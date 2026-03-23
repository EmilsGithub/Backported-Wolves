package net.emilsg.backported_wolves.variant;

import net.emilsg.backported_wolves.sound.ModSoundEvents;
import net.emilsg.backported_wolves.sound.ModSoundEvents;
import net.minecraft.sounds.SoundEvent;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public enum WolfSoundVariant {
    ANGRY("minecraft:angry"),
    BIG("minecraft:big"),
    CLASSIC("minecraft:classic"),
    CUTE("minecraft:cute"),
    GRUMPY("minecraft:grumpy"),
    PUGLIN("minecraft:puglin"),
    SAD("minecraft:sad");

    private final String id;

    WolfSoundVariant(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static WolfSoundVariant fromId(String id) {
        for (WolfSoundVariant variant : values()) {
            if (variant.id.equals(id)) {
                return variant;
            }
        }
        return CLASSIC;
    }

    public static WolfSoundVariant getRandom() {
        return Arrays.stream(values()).skip(ThreadLocalRandom.current().nextInt(values().length)).findFirst().orElse(CLASSIC);
    }

    public SoundEvent getPantingSound() {
        return switch (this) {
            case ANGRY -> ModSoundEvents.ANGRY_PANTING.get();
            case BIG -> ModSoundEvents.BIG_PANTING.get();
            case CLASSIC -> ModSoundEvents.CLASSIC_PANTING.get();
            case CUTE -> ModSoundEvents.CUTE_PANTING.get();
            case GRUMPY -> ModSoundEvents.GRUMPY_PANTING.get();
            case PUGLIN -> ModSoundEvents.PUGLIN_PANTING.get();
            case SAD -> ModSoundEvents.SAD_PANTING.get();
        };
    }

    public SoundEvent getBarkSound() {
        return switch (this) {
            case ANGRY -> ModSoundEvents.ANGRY_BARK.get();
            case BIG -> ModSoundEvents.BIG_BARK.get();
            case CLASSIC -> ModSoundEvents.CLASSIC_BARK.get();
            case CUTE -> ModSoundEvents.CUTE_BARK.get();
            case GRUMPY -> ModSoundEvents.GRUMPY_BARK.get();
            case PUGLIN -> ModSoundEvents.PUGLIN_BARK.get();
            case SAD -> ModSoundEvents.SAD_BARK.get();
        };
    }

    public SoundEvent getDeathSound() {
        return switch (this) {
            case ANGRY -> ModSoundEvents.ANGRY_DEATH.get();
            case BIG -> ModSoundEvents.BIG_DEATH.get();
            case CLASSIC -> ModSoundEvents.CLASSIC_DEATH.get();
            case CUTE -> ModSoundEvents.CUTE_DEATH.get();
            case GRUMPY -> ModSoundEvents.GRUMPY_DEATH.get();
            case PUGLIN -> ModSoundEvents.PUGLIN_DEATH.get();
            case SAD -> ModSoundEvents.SAD_DEATH.get();
        };
    }

    public SoundEvent getHurtSound() {
        return switch (this) {
            case ANGRY -> ModSoundEvents.ANGRY_HURT.get();
            case BIG -> ModSoundEvents.BIG_HURT.get();
            case CLASSIC -> ModSoundEvents.CLASSIC_HURT.get();
            case CUTE -> ModSoundEvents.CUTE_HURT.get();
            case GRUMPY -> ModSoundEvents.GRUMPY_HURT.get();
            case PUGLIN -> ModSoundEvents.PUGLIN_HURT.get();
            case SAD -> ModSoundEvents.SAD_HURT.get();
        };
    }

    public SoundEvent getGrowlSound() {
        return switch (this) {
            case ANGRY -> ModSoundEvents.ANGRY_GROWL.get();
            case BIG -> ModSoundEvents.BIG_GROWL.get();
            case CLASSIC -> ModSoundEvents.CLASSIC_GROWL.get();
            case CUTE -> ModSoundEvents.CUTE_GROWL.get();
            case GRUMPY -> ModSoundEvents.GRUMPY_GROWL.get();
            case PUGLIN -> ModSoundEvents.PUGLIN_GROWL.get();
            case SAD -> ModSoundEvents.SAD_GROWL.get();
        };
    }

    public SoundEvent getWhineSound() {
        return switch (this) {
            case ANGRY -> ModSoundEvents.ANGRY_WHINE.get();
            case BIG -> ModSoundEvents.BIG_WHINE.get();
            case CLASSIC -> ModSoundEvents.CLASSIC_WHINE.get();
            case CUTE -> ModSoundEvents.CUTE_WHINE.get();
            case GRUMPY -> ModSoundEvents.GRUMPY_WHINE.get();
            case PUGLIN -> ModSoundEvents.PUGLIN_WHINE.get();
            case SAD -> ModSoundEvents.SAD_WHINE.get();
        };
    }
}
