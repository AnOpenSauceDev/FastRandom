package com.github.anopensaucedev.fasterrandom.mixin;

import com.github.anopensaucedev.fasterrandom.util.FastThreadLocalRandom;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.UUID;

@Mixin(value = MathHelper.class, priority = 2000)
public class MathHelperMixin {
	/**
	 * @author AnOpenSauceDev
	 * @reason Optimise Gaussian using ThreadLocal.
	 */
	@Overwrite
	public static float nextGaussian(Random random, float mean, float deviation) {
		return mean + (float) FastThreadLocalRandom.nextGaussian() * deviation;
	}

	/**
	 * @author AnOpenSauceDev
	 * @reason Optimise using ThreadLocal.
	 */
	@Overwrite
	public static int nextBetween(Random random, int min, int max) {
		return FastThreadLocalRandom.nextInt(max - min + 1) + min;
	}

	/**
	 * @author AnOpenSauceDev
	 * @reason Optimise using ThreadLocal.
	 */
	@Overwrite
	public static float nextBetween(Random random, float min, float max) {
		return FastThreadLocalRandom.nextFloat() * (max - min) + min;
	}

	/**
	 * @author AnOpenSauceDev
	 * @reason Optimise using ThreadLocal.
	 */
	@Overwrite
	public static @NotNull UUID randomUuid(Random random) {
		long mostSigBits = FastThreadLocalRandom.nextLong() & 0xFFFFFFFFFFFF0FFFL | 0x4000L;
		long leastSigBits = FastThreadLocalRandom.nextLong() & 0x3FFFFFFFFFFFFFFFL | Long.MIN_VALUE;
		return new UUID(mostSigBits, leastSigBits);
	}

	/**
	 * @author AnOpenSauceDev
	 * @reason Optimise using ThreadLocal.
	 */
	@Overwrite
	public static int nextInt(Random random, int min, int max) {
		if (min >= max) {
			return min;
		}

		return FastThreadLocalRandom.nextInt(max - min + 1) + min;
	}

	/**
	 * @author AnOpenSauceDev
	 * @reason Optimise using ThreadLocal.
	 */
	@Overwrite
	public static float nextFloat(Random random, float min, float max) {
		if (min >= max) {
			return min;
		}

		return FastThreadLocalRandom.nextFloat() * (max - min) + min;
	}

	/**
	 * @author AnOpenSauceDev
	 * @reason Optimise using ThreadLocal.
	 */
	@Overwrite
	public static double nextDouble(Random random, double min, double max) {
		if (min >= max) {
			return min;
		}

		return FastThreadLocalRandom.nextDouble() * (max - min) + min;
	}
}
