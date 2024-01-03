package com.github.anopensaucedev.fasterrandom.mixin;

import com.github.anopensaucedev.fasterrandom.util.FastThreadLocalRandom;
import net.minecraft.util.math.random.CheckedRandom;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CheckedRandom.class)
public class CheckedRandomMixin {
	@Inject(method = "nextGaussian", at = @At(value = "HEAD"), cancellable = true)
	private void fastrandom$nextGaussianInject(@NotNull CallbackInfoReturnable<Double> cir) {
		cir.setReturnValue(FastThreadLocalRandom.nextGaussian());
	}
}
