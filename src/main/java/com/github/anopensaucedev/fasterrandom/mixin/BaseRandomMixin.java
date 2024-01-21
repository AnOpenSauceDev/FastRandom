package com.github.anopensaucedev.fasterrandom.mixin;

import com.github.anopensaucedev.fasterrandom.FasterRandom;
import net.minecraft.util.math.random.BaseRandom;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BaseRandom.class)
public interface BaseRandomMixin {
	@Inject(method = "nextInt()I", at = @At(value = "HEAD"), cancellable = true)
	private void fastrandom$nextIntInject(@NotNull CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(FasterRandom.random.nextInt());
	}

	@Inject(method = "nextInt(I)I", at = @At(value = "HEAD"), cancellable = true)
	private void fastrandom$nextIntInject(int bound, @NotNull CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(FasterRandom.random.nextInt(bound));
	}

	@Inject(method = "nextLong", at = @At(value = "HEAD"), cancellable = true)
	private void fastrandom$nextLongInject(@NotNull CallbackInfoReturnable<Long> cir) {
		cir.setReturnValue(FasterRandom.random.nextLong());
	}

	@Inject(method = "nextBoolean", at = @At(value = "HEAD"), cancellable = true)
	private void fastrandom$nextBooleanInject(@NotNull CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(FasterRandom.random.nextBoolean());
	}

	@Inject(method = "nextFloat", at = @At(value = "HEAD"), cancellable = true)
	private void fastrandom$nextFloatInject(@NotNull CallbackInfoReturnable<Float> cir) {
		cir.setReturnValue(FasterRandom.random.nextFloat());
	}

	@Inject(method = "nextFloat", at = @At(value = "HEAD"), cancellable = true)
	private void fastrandom$nextDoubleInject(@NotNull CallbackInfoReturnable<Double> cir) {
		cir.setReturnValue(FasterRandom.random.nextDouble());
	}
}
