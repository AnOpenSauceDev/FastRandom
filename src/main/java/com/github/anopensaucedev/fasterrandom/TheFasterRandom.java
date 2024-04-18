package com.github.anopensaucedev.fasterrandom;

import com.google.common.annotations.VisibleForTesting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.BaseRandom;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.random.RandomSplitter;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class TheFasterRandom implements BaseRandom {
	private static final RandomGeneratorFactory<RandomGenerator.SplittableGenerator> RANDOM_GENERATOR_FACTORY = RandomGeneratorFactory.of("L64X128MixRandom");

	private static final int INT_BITS = 48;
	private static final long SEED_MASK = 0xFFFFFFFFFFFFL;
	private static final long MULTIPLIER = 25214903917L;
	private static final long INCREMENT = 11L;

	private long seed;
	private RandomGenerator.SplittableGenerator randomGenerator;

	public TheFasterRandom(long seed) {
		this.seed = seed;
		this.randomGenerator = RANDOM_GENERATOR_FACTORY.create(seed);
	}

	@Override
	public Random split() {
		return new TheFasterRandom(this.nextLong());
	}

	@Override
	public RandomSplitter nextSplitter() {
		return new TheFasterRandomSplitter(this.nextLong());
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
		return randomGenerator.nextLong();
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

	private record TheFasterRandomSplitter(long seed) implements RandomSplitter {
		@Override
		public Random split(int x, int y, int z) {
			return new TheFasterRandom(MathHelper.hashCode(x, y, z) ^ this.seed);
		}

		@Override
		public Random split(String seed) {
			return new TheFasterRandom((long) seed.hashCode() ^ this.seed);
		}

		@Override
		@VisibleForTesting
		public void addDebugInfo(StringBuilder info) {
			info.append("TheFasterRandomSplitter{").append(this.seed).append("}");
		}
	}
}
