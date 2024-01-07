package com.github.anopensaucedev.fasterrandom.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <h1>Basically all of Faster Random's documentation.</h1>
 * <strong>Now,</strong> if you're looking at this, you're probably wondering why <strong>{@code ThreadLocalRandom}</strong> is being used here.
 * The reason is that while Java's standard random is "good enough", its thread safety (among a handful of other things) slows it down.
 * Faster Random instead uses the much faster (but also way less safe) <strong>{@code ThreadLocalRandom}</strong>.
 * Benchmarking this method, I personally see up to 100X faster generation of random floating-point numbers.
 * That being said, these methods only really get called for a handful of events, and there are tonnes of things to optimize (of which I'm too lazy to do). <br></br>
 * <br></br>
 * If this text looks weird, make sure to use the "rendered view" button in your IDE.
 */
public class FastThreadLocalRandom {
	public static double nextDouble() {
		return ThreadLocalRandom.current().nextDouble();
	}

	public double nextDouble(double bound) {
		return ThreadLocalRandom.current().nextDouble(bound);
	}

	public static float nextFloat() {
		return ThreadLocalRandom.current().nextFloat();
	}

	public static float nextFloat(float bound) {
		return ThreadLocalRandom.current().nextFloat(bound);
	}

	public static int nextInt() {
		return ThreadLocalRandom.current().nextInt();
	}

	public static int nextInt(int bound) {
		return ThreadLocalRandom.current().nextInt(bound);
	}

	public static long nextLong(long bound) {
		return ThreadLocalRandom.current().nextLong(bound);
	}

	public static long nextLong() {
		return ThreadLocalRandom.current().nextLong();
	}

	public static double nextGaussian() {
		return ThreadLocalRandom.current().nextGaussian();
	}

	public static boolean nextBoolean() {
		return ThreadLocalRandom.current().nextBoolean();
	}
}
