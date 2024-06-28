package com.github.anopensaucedev.fasterrandomtest.benchmark;

import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.RandomSeed;
import net.minecraft.util.math.random.Xoroshiro128PlusPlusRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGeneratorFactory;

/**
 * A benchmarking tool for various random number generators.
 */
public class RandomNumberGenerationBenchmark {
	private static final Logger BENCHMARK_LOGGER = LoggerFactory.getLogger("Faster Random RandomNumberGenerationBenchmark");
	/**
	 * Minecraft's vanilla random number generator.
	 */
	private static final CheckedRandom VANILLA_RANDOM = new CheckedRandom(RandomSeed.getSeed());
	/**
	 * Legacy {@link Random} from {@link java.util}.
	 */
	private static final Random OLD_RANDOM = new Random(RandomSeed.getSeed());
	/**
	 * An ordered list of random number generator names.
	 */
	private static final String[] GENERATOR_NAMES = {"vanilla", "java.util.random", "ThreadLocalRandom", "LXM","Xoroshiro128++Random"};
	/**
	 * The amount of iterations the benchmark runs.
	 */
	private static final int ITERATIONS = 100_000_000; // Stress testing! Lower when actual development needs to happen
	/**
	 * The timing values of the tested random number generators.
	 */
	private static final ArrayList<Double> TIMING_VALUES = new ArrayList<>();

	enum ANSI_COLOURS{
		RESET("\033[0m"),
		BOLD_YELLOW("\033[1;33m");

		public final String value;

		private ANSI_COLOURS(String data){
			this.value = data;
		}

	}


	/**
	 * Runs a benchmark of various random number generators.
	 * Can be slow (and thus impact loading times if ran on game start).
	 */
	public static void runBenchmark() {
		BENCHMARK_LOGGER.info("Starting Random benchmark!");

		// Vanilla random
		BENCHMARK_LOGGER.info("Vanilla benchmark");
		var vanillaStart = System.nanoTime();
		for (int i = 0; i < ITERATIONS; i++) {
			VANILLA_RANDOM.nextFloat();
			VANILLA_RANDOM.nextInt();
			VANILLA_RANDOM.nextDouble();
			VANILLA_RANDOM.nextGaussian();
			VANILLA_RANDOM.nextLong();
			VANILLA_RANDOM.nextBoolean();
		}
		var vanillaFinish = toSeconds(System.nanoTime() - vanillaStart);
		BENCHMARK_LOGGER.info("Vanilla benchmark time: {}{}s{}, mean: {}{}s{}", ANSI_COLOURS.BOLD_YELLOW.value , vanillaFinish, ANSI_COLOURS.RESET.value, ANSI_COLOURS.BOLD_YELLOW.value,  (float) (vanillaFinish / ITERATIONS), ANSI_COLOURS.RESET.value);
		TIMING_VALUES.add(vanillaFinish); //0

		// Java.util.random
		BENCHMARK_LOGGER.info("java.util.random");
		var oldRandomStart = System.nanoTime();
		for (int i = 0; i < ITERATIONS; i++) {
			OLD_RANDOM.nextFloat();
			OLD_RANDOM.nextInt();
			OLD_RANDOM.nextDouble();
			OLD_RANDOM.nextGaussian();
			OLD_RANDOM.nextLong();
			OLD_RANDOM.nextBoolean();
		}
		var oldRandomFinish = toSeconds(System.nanoTime() - oldRandomStart);
		BENCHMARK_LOGGER.info("java.util.random time: {}{}s{}, mean: {}{}s{}", ANSI_COLOURS.BOLD_YELLOW.value, oldRandomFinish, ANSI_COLOURS.RESET.value, ANSI_COLOURS.BOLD_YELLOW.value , (float) (oldRandomFinish / ITERATIONS), ANSI_COLOURS.RESET.value);
		TIMING_VALUES.add(oldRandomFinish); //1

		// ThreadLocalRandom ("100X faster" than the old CheckedRandom)
		// TODO: Add old CheckedRandom implementation as a benchmark alongside the existing benchmarks
		BENCHMARK_LOGGER.info("ThreadLocalRandom");
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
		BENCHMARK_LOGGER.info("ThreadLocalRandom time: {}{}s{}, mean: {}{}s{}", ANSI_COLOURS.BOLD_YELLOW.value, threadLocalFinish, ANSI_COLOURS.RESET.value, ANSI_COLOURS.BOLD_YELLOW.value, (float) (threadLocalFinish / ITERATIONS), ANSI_COLOURS.RESET.value);
		TIMING_VALUES.add(threadLocalFinish); // 2

		// LXM random
		BENCHMARK_LOGGER.info("LXM");
		var generator = RandomGeneratorFactory.of("L64X128MixRandom").create();
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
		BENCHMARK_LOGGER.info("LXM time: {}{}s{}, mean: {}{}s{}", ANSI_COLOURS.BOLD_YELLOW.value, lxmFinish, ANSI_COLOURS.RESET.value,ANSI_COLOURS.BOLD_YELLOW.value, (float) (lxmFinish / ITERATIONS),ANSI_COLOURS.RESET.value);
		TIMING_VALUES.add(lxmFinish); // 3

		BENCHMARK_LOGGER.info("Xoroshiro128++Random");
		Xoroshiro128PlusPlusRandom xoroshirogenerator = new Xoroshiro128PlusPlusRandom(0);
		var xoroStart = System.nanoTime();
		for (int i = 0; i < ITERATIONS; i++){
			xoroshirogenerator.nextFloat();
			xoroshirogenerator.nextInt();
			xoroshirogenerator.nextDouble();
			xoroshirogenerator.nextGaussian();
			xoroshirogenerator.nextLong();
			xoroshirogenerator.nextBoolean();
		}
		var xoroFinish = toSeconds(System.nanoTime() - xoroStart);
		BENCHMARK_LOGGER.info("Xoroshiro128++ time: {}{}s{}, mean: {}{}s{}", ANSI_COLOURS.BOLD_YELLOW.value, xoroFinish, ANSI_COLOURS.RESET.value, ANSI_COLOURS.BOLD_YELLOW.value, (float) (xoroFinish / ITERATIONS), ANSI_COLOURS.RESET.value);
		TIMING_VALUES.add(xoroFinish); // 4

		BENCHMARK_LOGGER.info("Overall winner: {}{}s{}, from random generator: {}{}{}", ANSI_COLOURS.BOLD_YELLOW.value, Collections.min(TIMING_VALUES), ANSI_COLOURS.RESET.value,ANSI_COLOURS.BOLD_YELLOW.value,
				GENERATOR_NAMES[TIMING_VALUES.indexOf(Collections.min(TIMING_VALUES))], ANSI_COLOURS.RESET.value
		);
	}

	/**
	 * Runs a legacy benchmark of various random number generators.
	 * Can be slow (and thus impact loading times if ran on game start).
	 * This is an older benchmark from Methane, where {@link CheckedRandom} is 100x slower on average than {@link ThreadLocalRandom}.
	 */
	public static void runLegacyBenchmark() {
		int iterations = 1000000000;
		ThreadLocalRandom random = ThreadLocalRandom.current();
		long time = System.nanoTime();
		for (int x = 0; x < iterations; x++) {
			random.nextFloat();
		}

		long finalTime = System.nanoTime() - time;
		BENCHMARK_LOGGER.info(
				"Legacy Random Number Generation Benchmark (ThreadLocal): {}", RandomNumberGenerationBenchmark.toSeconds(finalTime));

		// Seeded or not, this is always slower
		net.minecraft.util.math.random.Random random2 = net.minecraft.util.math.random.Random.create(RandomSeed.getSeed());
		long time2 = System.nanoTime();
		for (int x = 0; x < iterations; x++) {
			random2.nextFloat();
		}

		long finalTime2 = System.nanoTime() - time2;
		BENCHMARK_LOGGER.info(
				"Legacy Random Number Generation Benchmark (Random): {}", RandomNumberGenerationBenchmark.toSeconds(finalTime2));

		if (finalTime < finalTime2) {
			BENCHMARK_LOGGER.info("ThreadLocal was faster!");
		}
	}

	private static double toSeconds(double value) {
		return (value / 1e9);
	}
}
