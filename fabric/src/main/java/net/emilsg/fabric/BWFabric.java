package net.emilsg.fabric;

import net.emilsg.fabric.spawn.ModEntitySpawns;
import net.fabricmc.api.ModInitializer;

import net.emilsg.backported_wolves.BackportedWolvesCommon;

public final class BWFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BackportedWolvesCommon.init();
        ModEntitySpawns.registerSpawns();
    }
}
