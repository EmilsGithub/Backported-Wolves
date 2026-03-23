package net.emilsg.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;

import net.emilsg.backported_wolves.BackportedWolvesCommon;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BackportedWolvesCommon.MOD_ID)
public final class BWForge {
    public BWForge() {
        EventBuses.registerModEventBus(
                BackportedWolvesCommon.MOD_ID,
                FMLJavaModLoadingContext.get().getModEventBus()
        );

        BackportedWolvesCommon.init();
    }
}
