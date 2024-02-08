package com.github.anopensaucedev.fasterrandom.mixin;

import com.github.anopensaucedev.fasterrandom.extension.BaseRandomExtension;
import net.minecraft.util.math.random.BaseRandom;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BaseRandom.class)
public interface BaseRandomMixin extends Random, BaseRandomExtension {
	@Override
	default double nextGaussian() {
		return fasterrandom$getRandomGenerator().nextGaussian();
	}

	@Inject(method = "nextInt()I", at = @At(value = "HEAD"), cancellable = true)
	private void fasterrandom$nextIntInject(@NotNull CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(fasterrandom$getRandomGenerator().nextInt());
	}

	@Inject(method = "nextInt(I)I", at = @At(value = "HEAD"), cancellable = true)
	private void fasterrandom$nextIntInject(int bound, @NotNull CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(fasterrandom$getRandomGenerator().nextInt(bound));
	}

	@Inject(method = "nextLong", at = @At(value = "HEAD"), cancellable = true)
	private void fasterrandom$nextLongInject(@NotNull CallbackInfoReturnable<Long> cir) {
		cir.setReturnValue(fasterrandom$getRandomGenerator().nextLong());
	}

	@Inject(method = "nextBoolean", at = @At(value = "HEAD"), cancellable = true)
	private void fasterrandom$nextBooleanInject(@NotNull CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(fasterrandom$getRandomGenerator().nextBoolean());
	}

	@Inject(method = "nextFloat", at = @At(value = "HEAD"), cancellable = true)
	private void fasterrandom$nextFloatInject(@NotNull CallbackInfoReturnable<Float> cir) {
		cir.setReturnValue(fasterrandom$getRandomGenerator().nextFloat());
	}

	@Inject(method = "nextFloat", at = @At(value = "HEAD"), cancellable = true)
	private void fasterrandom$nextDoubleInject(@NotNull CallbackInfoReturnable<Double> cir) {
		cir.setReturnValue(fasterrandom$getRandomGenerator().nextDouble());
	}
}
