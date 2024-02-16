package com.github.anopensaucedev.fasterrandom;

import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.RandomSeed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// random number generation benchmarking
public class RandBench {

	private static Logger benchmarkLogger = LoggerFactory.getLogger("Faster Random RandBench");

	private static CheckedRandom vanillaRandom = new CheckedRandom(RandomSeed.getSeed());
	private static Random oldRandom = new Random(RandomSeed.getSeed());

	private static final int ITERATIONS = 100_000_000; // stress testing! Lower when actual development needs to happen

	private static ArrayList values = new ArrayList<>();
	private static final String[] generatorNames = {"vanilla","java.util.random","ThreadLocalRandom","LXM"}; // generator names in order

	public static void StartBenchmark(){
		benchmarkLogger.info("Starting Random benchmark!");

		// Vanilla random
		benchmarkLogger.info("Vanilla benchmark");
		var vanillaStart = System.nanoTime();
		for (int i = 0; i < ITERATIONS; i++) {
			vanillaRandom.nextFloat();
			vanillaRandom.nextInt();
			vanillaRandom.nextDouble();
			vanillaRandom.nextGaussian();
			vanillaRandom.nextLong();
			vanillaRandom.nextBoolean();
		}
		var vanillaFinish = toSeconds(System.nanoTime() - vanillaStart);
		benchmarkLogger.info("Vanilla benchmark time: {}s, mean: {}s",vanillaFinish, (float) (vanillaFinish/ITERATIONS));
		values.add(vanillaFinish); //0

		// Java.util.random
		benchmarkLogger.info("java.util.random");
		var oldRandomStart = System.nanoTime();
		for (int i = 0; i < ITERATIONS; i++) {
			oldRandom.nextFloat();
			oldRandom.nextInt();
			oldRandom.nextDouble();
			oldRandom.nextGaussian();
			oldRandom.nextLong();
			oldRandom.nextBoolean();
		}
		var oldRandomFinish = toSeconds(System.nanoTime() - oldRandomStart);
		benchmarkLogger.info("java.util.random time: {}s, mean: {}s",oldRandomFinish,(float) (oldRandomFinish/ITERATIONS));
		values.add(oldRandomFinish); //1

		// ThreadLocalRandom ("100X faster" than the old CheckedRandom)
		//TODO: add old CheckedRandom impl as a benchmark too.
		benchmarkLogger.info("ThreadLocalRandom");
		var threadLocalRandom = ThreadLocalRandom.current();
		var threadLocalStart = System.nanoTime();
		for (int i = 0; i < ITERATIONS; i++) {
			threadLocalRandom.nextFloat();
			threadLocalRandom.nextInt();
			threadLocalRandom.nextDouble();
			threadLocalRandom.nextGaussian();
			threadLocalRandom.nextLong();
			threadLocalRandom.nextBoolean();
		}
		var threadLocalFinish = toSeconds(System.nanoTime() - threadLocalStart);
		benchmarkLogger.info("ThreadLocalRandom time: {}s, mean: {}s",threadLocalFinish,(float) (threadLocalFinish/ITERATIONS));
		values.add(threadLocalFinish); // 2

		// LXM random
		benchmarkLogger.info("LXM");
		var generator = FasterRandom.RANDOM_GENERATOR_FACTORY.create();
		var lxmStart = System.nanoTime();
		for (int i = 0; i < ITERATIONS; i++) {
			generator.nextFloat();
			generator.nextInt();
			generator.nextDouble();
			generator.nextGaussian();
			generator.nextLong();
			generator.nextBoolean();
		}
		var lxmFinish = toSeconds(System.nanoTime() - lxmStart);
		benchmarkLogger.info("LXM Time: {}s, mean: {}s",lxmFinish,(float) (lxmFinish/ITERATIONS));
		values.add(lxmFinish); // 3

		benchmarkLogger.info("overall winner: {}s, from random generator: {}",Collections.min(values),generatorNames[values.indexOf(Collections.min(values))]);

	}

	public static double toSeconds(double value){
		return  (value / 1e9);
	}

}
