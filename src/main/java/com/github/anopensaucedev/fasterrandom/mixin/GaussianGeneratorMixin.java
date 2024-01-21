package com.github.anopensaucedev.fasterrandom.mixin;

import com.github.anopensaucedev.fasterrandom.FasterRandom;
import net.minecraft.util.math.random.GaussianGenerator;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GaussianGenerator.class)
public class GaussianGeneratorMixin {
	@Inject(method = "next", at = @At(value = "HEAD"), cancellable = true)
	private void fasterrandom$nextInject(@NotNull CallbackInfoReturnable<Double> cir) {
		cir.setReturnValue(FasterRandom.random.nextGaussian());
	}
}
