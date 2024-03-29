package com.github.anopensaucedev.fasterrandom;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.random.RandomSeed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class FasterRandom implements ModInitializer {
	public static final String MOD_ID = "faster-random";


	public static final String MOD_VERSION = FabricLoader.getInstance().getModContainer(MOD_ID).get().getMetadata().getVersion().getFriendlyString();
	public static final String MOD_NAME = "Faster Random";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
	public static final RandomGeneratorFactory<RandomGenerator.SplittableGenerator> RANDOM_GENERATOR_FACTORY = RandomGeneratorFactory.of(
			"L64X128MixRandom");

	@Override
	public void onInitialize() {
		LOGGER.info("{} {} has loaded! \\0_0/", MOD_NAME,MOD_VERSION);
		LOGGER.warn(
				"While terrain generation will \"function\" properly, cave generation will have a different seed (in singleplayer / if this mod is on the server), which may or may not be noticeable. Keep this in mind.");
		LOGGER.warn("If you have any terrain-related issues, make sure to mention me (AnOpenSauceDev) in an issue just to be sure!");
		LOGGER.info("Faster Random Issue Tracker: https://github.com/AnOpenSauceDev/FastRandom/issues");


		//run random benchmarks
		if(FabricLoader.getInstance().isDevelopmentEnvironment()){
			//LegacyBenchmark(); // older benchmark from Methane, where CheckedRandom is 100x slower than Threadlocal?
			//RandBench.StartBenchmark();
		}

	}

	// I have absolutely no idea why it's slower in this case.
	public static void LegacyBenchmark(){

		int iterations = 1000000000;

		ThreadLocalRandom random = ThreadLocalRandom.current();

		long time = System.nanoTime();

		for(int x = 0; x < iterations; x++ ){
			random.nextFloat();
		}

		long finaltime = System.nanoTime() - time;

		LOGGER.info("Random Benchmark Results (ThreadLocal): " + RandBench.toSeconds(finaltime));

		Random random2 = Random.create(RandomSeed.getSeed()); //seeded or not, this is always slower

		long time2 = System.nanoTime();

		for(int x = 0; x < iterations; x++ ){
			random2.nextFloat();
		}


		long finaltime2 = System.nanoTime() - time2;

		LOGGER.info("Random Benchmark Results (Random): " + RandBench.toSeconds(finaltime2));

		if(finaltime < finaltime2){
			LOGGER.info("ThreadLocal was faster!");
		}

	}
}
