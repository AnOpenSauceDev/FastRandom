package com.github.anopensaucedev.fasterrandom;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FasterRandom implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Faster Random");

	@Override
	public void onInitialize() {
		LOGGER.info("Faster Random has loaded! \\0_0/");
		LOGGER.info("While terrain generation will \"function\" properly, cave generation will have a different seed (in singleplayer / if this mod is on the server), which may or may not be noticeable. Keep this in mind.");
		LOGGER.info("If you have any terrain-related issues, make sure to mention me (AnOpenSauceDev) in an issue just to be sure!");
		LOGGER.info("Faster Random issue tracker: https://github.com/AnOpenSauceDev/FastRandom/issues");
	}
}
