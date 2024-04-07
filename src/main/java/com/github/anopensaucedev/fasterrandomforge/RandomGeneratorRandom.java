package com.github.anopensaucedev.fasterrandomforge;

import net.minecraft.world.level.levelgen.BitRandomSource;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;

import java.util.random.RandomGenerator;

import static com.github.anopensaucedev.fasterrandomforge.FasterRandomForge.RANDOM_GENERATOR_FACTORY;

public class RandomGeneratorRandom implements BitRandomSource {
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
	public BitRandomSource fork() {
		return new RandomGeneratorRandom(seed, randomGenerator.split());
	}

	@Override
	public PositionalRandomFactory forkPositional() {
		// TODO: Should this use this.seed instead of nextLong()?
		return new LegacyRandomSource.LegacyPositionalRandomFactory(nextLong());
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
