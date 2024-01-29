package com.github.anopensaucedev.fasterrandom.mixin;

import com.github.anopensaucedev.fasterrandom.FasterRandom;
import net.minecraft.screen.EnchantmentScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;
import java.util.random.RandomGenerator;

/// Usages of random in EnchantmentScreenHandler
// onContentChanged
// onButtonClick
// generateEnchantments
//
@Mixin(EnchantmentScreenHandler.class)
public class EnchantmentScreenHandlerMixin {

	@Unique
	private RandomGenerator fasterrandom$random;

	// To inject into a lambda, you have to get the function's name via a bytecode viewer.
	@Redirect(method = "method_17411", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;setSeed(J)V"))
	public void fasterrandom$setseed(net.minecraft.util.math.random.Random instance, long l) {
		fasterrandom$random = new Random(l);
	}

	@Redirect(method = "method_17411", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I"))
	public int fasterrandom$nextIntRedirect(net.minecraft.util.math.random.Random instance, int i) {
		return fasterrandom$random.nextInt(i);
	}

	@Redirect(method = "generateEnchantments",at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;setSeed(J)V"))
	public void fasterandom$setSeedGenerateEnchantments(net.minecraft.util.math.random.Random instance, long l) {
		FasterRandom.LOGGER.warn("rerolled enchantments!");
		fasterrandom$random = new Random(l);
	}

	@Redirect(method = "generateEnchantments",at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I"))
	public int fasterandom$generateEnchantmentsNextInt(net.minecraft.util.math.random.Random instance, int i) {
		return fasterrandom$random.nextInt(i);
	}

}
