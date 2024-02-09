package com.github.anopensaucedev.fasterrandom;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class FasterRandom implements ModInitializer {
	public static final String MOD_ID = "faster-random";
	public static final String MOD_NAME = "Faster Random";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
	public static final RandomGeneratorFactory<RandomGenerator.SplittableGenerator> RANDOM_GENERATOR_FACTORY = RandomGeneratorFactory.of(
			"L128X128MixRandom");

	@Override
	public void onInitialize() {
		LOGGER.info("Loading {} \\0_0/", MOD_NAME);
		LOGGER.warn(
				"While terrain generation will \"function\" properly, cave generation will have a different seed (in singleplayer / if this mod is on the server), which may or may not be noticeable. Keep this in mind.");
		LOGGER.warn("If you have any terrain-related issues, make sure to mention me (AnOpenSauceDev) in an issue just to be sure!");
	}
}
