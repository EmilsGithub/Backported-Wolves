package net.emilsg.backported_wolves;

import net.emilsg.backported_wolves.world.ModEntitySpawning;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackportedWolves implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("backported_wolves");
	public static final String MOD_ID = "backported_wolves";

	@Override
	public void onInitialize() {
		ModEntitySpawning.addSpawns();
	}
}