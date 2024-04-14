package com.github.anopensaucedev.fasterrandom;

import net.minecraft.util.math.random.*;

import java.util.random.RandomGenerator;

import static com.github.anopensaucedev.fasterrandom.FasterRandom.RANDOM_GENERATOR_FACTORY;

public class RandomGeneratorRandom implements BaseRandom {
	private static final int INT_BITS = 48;
	private static final long SEED_MASK = 0xFFFFFFFFFFFFL;
	private static final long MULTIPLIER = 25214903917L;
	private static final long INCREMENT = 11L;

	private long seed;
	private RandomGenerator.SplittableGenerator randomGenerator;

	public RandomGeneratorRandom(long seed) {
		this.seed = seed;
		this.randomGenerator = RANDOM_GENERATOR_FACTORY.create(seed);
	}

	public RandomGeneratorRandom(long seed, RandomGenerator.SplittableGenerator randomGenerator) {
		this.seed = seed;
		this.randomGenerator = randomGenerator;
	}

	@Override
	public Random split() {
		return new RandomGeneratorRandom(seed, randomGenerator.split());
	}

	@Override
	public RandomSplitter nextSplitter() {
		return new CheckedRandom.Splitter(this.seed);
	}

	@Override
	public void setSeed(long seed) {
		this.seed = seed;
		this.randomGenerator = RANDOM_GENERATOR_FACTORY.create(seed);
	}

	@Override
	public int next(int bits) {
		// >>> instead of Mojang's >> fixes MC-239059
		return (int) ((seed * MULTIPLIER + INCREMENT & SEED_MASK) >>> INT_BITS - bits);
	}

	@Override
	public int nextInt() {
		return randomGenerator.nextInt();
	}

	@Override
	public int nextInt(int bound) {
		return randomGenerator.nextInt(bound);
	}

	@Override
	public long nextLong() {
		return randomGenerator.nextInt();
	}

	@Override
	public boolean nextBoolean() {
		return randomGenerator.nextBoolean();
	}

	@Override
	public float nextFloat() {
		return randomGenerator.nextFloat();
	}

	@Override
	public double nextDouble() {
		return randomGenerator.nextDouble();
	}

	@Override
	public double nextGaussian() {
		return randomGenerator.nextGaussian();
	}
}
