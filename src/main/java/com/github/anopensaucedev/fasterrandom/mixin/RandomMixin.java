package com.github.anopensaucedev.fasterrandom.mixin;

import com.github.anopensaucedev.fasterrandom.util.math.random.RandomGeneratorRandom;
import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.random.RandomSeed;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Random.class)
public interface RandomMixin {
	@Inject(method = "create(J)Lnet/minecraft/util/math/random/Random;", at = @At(value = "HEAD"), cancellable = true)
	private static void fasterrandom$createInject(long seed, @NotNull CallbackInfoReturnable<Random> cir) {
		cir.setReturnValue(new RandomGeneratorRandom(seed));
	}

	@Inject(method = "createLocal", at = @At(value = "HEAD"), cancellable = true)
	private static void fasterrandom$createLocalInject(@NotNull CallbackInfoReturnable<Random> cir) {
		cir.setReturnValue(new RandomGeneratorRandom(ThreadLocalRandom.current().nextLong()));
	}

	@Inject(method = "createThreadSafe", at = @At(value = "HEAD"), cancellable = true)
	private static void fasterrandom$createThreadSafeInject(@NotNull CallbackInfoReturnable<Random> cir) {
		cir.setReturnValue(new RandomGeneratorRandom(RandomSeed.getSeed()));
	}
}
