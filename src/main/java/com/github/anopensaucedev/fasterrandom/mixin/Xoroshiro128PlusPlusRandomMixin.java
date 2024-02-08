package com.github.anopensaucedev.fasterrandom.mixin;

import com.github.anopensaucedev.fasterrandom.extension.BaseRandomExtension;
import net.minecraft.util.math.random.BaseRandom;
import net.minecraft.util.math.random.Xoroshiro128PlusPlusRandom;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

@Mixin(Xoroshiro128PlusPlusRandom.class)
public abstract class Xoroshiro128PlusPlusRandomMixin implements BaseRandom, BaseRandomExtension {
	@Unique
	RandomGenerator fasterrandom$randomGenerator;

	@Inject(method = "<init>*", at = @At(value = "TAIL"))
	private void fasterrandom$constructorInject(long seed, CallbackInfo ci) {
		fasterrandom$randomGenerator = RandomGeneratorFactory.of("L64X128MixRandom").create(seed);
	}

	@Override
	public RandomGenerator fasterrandom$getRandomGenerator() {
		return fasterrandom$randomGenerator;
	}

	@Inject(method = "nextGaussian", at = @At(value = "HEAD"), cancellable = true)
	private void fasterrandom$nextGaussianInject(@NotNull CallbackInfoReturnable<Double> cir) {
		cir.setReturnValue(fasterrandom$getRandomGenerator().nextGaussian());
	}
}
