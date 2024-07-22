package com.github.anopensaucedev.fasterrandom.util.math.random;

import org.jetbrains.annotations.NotNull;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class RandomGeneratorFactoryUtil {
	public static final @NotNull String RANDOM_GENERATOR_NAME = "L64X128MixRandom";

	public static @NotNull RandomGeneratorFactory<RandomGenerator.SplittableGenerator> getRandomGeneratorFactory() {
		return RandomGeneratorFactory.of(RANDOM_GENERATOR_NAME);
	}
}
