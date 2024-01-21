package com.github.anopensaucedev.fasterrandom.mixin;

import com.github.anopensaucedev.fasterrandom.FasterRandom;
import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.GaussianGenerator;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.random.RandomGeneratorFactory;

@Mixin(CheckedRandom.class)
public class CheckedRandomMixin {
	@Shadow @Final private GaussianGenerator gaussianGenerator;

	@Inject(method = "setSeed", at = @At(value = "HEAD"), cancellable = true)
	private void fastrandom$nextIntInject(long seed, @NotNull CallbackInfo ci) {
		FasterRandom.random = RandomGeneratorFactory.of("L64X128MixRandom").create(seed);
		gaussianGenerator.reset();

		ci.cancel();
	}
}
