package com.github.anopensaucedev.fasterrandom;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FasterRandom implements ModInitializer {
	public static final String MOD_ID = "faster-random";


	public static final String MOD_VERSION = FabricLoader.getInstance().getModContainer(MOD_ID).get().getMetadata().getVersion().getFriendlyString();
	public static final String MOD_NAME = "Faster Random";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		LOGGER.info("{} {} has loaded! \\0_0/", MOD_NAME,MOD_VERSION);
		LOGGER.warn(
				"While terrain generation will \"function\" properly, cave generation will have a different seed (in singleplayer / if this mod is on the server), which may or may not be noticeable. Keep this in mind.");
		LOGGER.warn("If you have any terrain-related issues, make sure to mention me (AnOpenSauceDev) in an issue just to be sure!");
		LOGGER.info("Faster Random Issue Tracker: https://github.com/AnOpenSauceDev/FastRandom/issues");
	}
}
