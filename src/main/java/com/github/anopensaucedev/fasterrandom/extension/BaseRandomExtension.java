package com.github.anopensaucedev.fasterrandom.extension;

import org.spongepowered.asm.mixin.Unique;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public interface BaseRandomExtension {
	@Unique
	RandomGenerator fasterrandom$randomGenerator = RandomGeneratorFactory.of("L64X128MixRandom").create();

	default RandomGenerator fasterrandom$getRandomGenerator() {
		return fasterrandom$randomGenerator;
	}
}
