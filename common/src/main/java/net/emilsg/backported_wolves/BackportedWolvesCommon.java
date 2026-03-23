package net.emilsg.backported_wolves;

import net.emilsg.backported_wolves.sound.ModSoundEvents;
import net.emilsg.backported_wolves.tags.ModBiomeTags;

public final class BackportedWolvesCommon {
    public static final String MOD_ID = "backported_wolves";

    public static void init() {
        ModSoundEvents.register();
        ModBiomeTags.register();
    }
}
