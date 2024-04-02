package com.github.anopensaucedev.fasterrandom;

import com.google.common.annotations.VisibleForTesting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.random.RandomSplitter;
import net.minecraft.util.thread.LockHelper;

import java.util.random.RandomGenerator;

import static com.github.anopensaucedev.fasterrandom.FasterRandom.RANDOM_GENERATOR_FACTORY;

public class RandomGeneratorCheckedRandom extends CheckedRandom {

	private static final int INT_BITS = 48;
	private static final long SEED_MASK = 0xFFFFFFFFFFFFL;
	private static final long MULTIPLIER = 25214903917L;
	private static final long INCREMENT = 11L;

	private long seed;
	private RandomGenerator.SplittableGenerator randomGenerator;

	public RandomGeneratorCheckedRandom(long seed) {
		super(seed);
	}

	@Override
	public Random split() {
		return new RandomGeneratorCheckedRandom(this.nextLong());
	}

	@Override
	public RandomSplitter nextSplitter() {
		return new RandomGeneratorCheckedRandom.Splitter(this.nextLong());
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
	public double nextGaussian() {
		return randomGenerator.nextGaussian();
	}

	public static class Splitter implements RandomSplitter {
		private final long seed;

		public Splitter(long seed) {
			this.seed = seed;
		}

		public Random split(int x, int y, int z) {
			long l = MathHelper.hashCode(x, y, z);
			long m = l ^ this.seed;
			return new RandomGeneratorCheckedRandom(m);
		}

		public Random split(String seed) {
			int i = seed.hashCode();
			return new RandomGeneratorCheckedRandom((long)i ^ this.seed);
		}

		@VisibleForTesting
		public void addDebugInfo(StringBuilder info) {
			info.append("LegacyPositionalRandomFactory{").append(this.seed).append("}");
		}
	}
}


