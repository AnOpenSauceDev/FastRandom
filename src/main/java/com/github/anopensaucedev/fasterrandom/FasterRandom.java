package com.github.anopensaucedev.fasterrandom;

import com.github.anopensaucedev.fasterrandom.benchmark.RandomNumberGenerationBenchmark;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class FasterRandom implements ModInitializer {
	public static final String MOD_ID = "faster-random";
	@SuppressWarnings("OptionalGetWithoutIsPresent")
	public static final String MOD_VERSION = FabricLoader.getInstance().getModContainer(
			MOD_ID).get().getMetadata().getVersion().getFriendlyString();
	public static final String MOD_NAME = "Faster Random";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
	public static final RandomGeneratorFactory<RandomGenerator.SplittableGenerator> RANDOM_GENERATOR_FACTORY = RandomGeneratorFactory.of(
			"L64X128MixRandom");

	@Override
	public void onInitialize() {
		LOGGER.info("{} v{} has loaded! \\0_0/", MOD_NAME, MOD_VERSION);
		LOGGER.info(
				"If you have any terrain-related problems, open an issue on the issue tracker. Make sure to mention me (AnOpenSauceDev) in the issue just to be sure!");
		LOGGER.info("Faster Random issue tracker: https://github.com/AnOpenSauceDev/FastRandom/issues");

		// Run random benchmarks if the mod is running in a development environment
		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			RandomNumberGenerationBenchmark.runLegacyBenchmark();
			RandomNumberGenerationBenchmark.runBenchmark();
		}
	}
}
