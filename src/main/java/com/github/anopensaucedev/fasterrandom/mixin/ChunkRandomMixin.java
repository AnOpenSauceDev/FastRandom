package com.github.anopensaucedev.fasterrandom.mixin;

import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.ChunkRandom;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkRandom.class)
public class ChunkRandomMixin {

	@Mutable
	@Shadow
	@Final
	private Random baseRandom;

	@Inject(method = "<init>(Lnet/minecraft/util/math/random/Random;)V",at = @At("RETURN"))
	private void fasterrandom$fixBetterX(Random baseRandom, CallbackInfo ci){
		this.baseRandom = new CheckedRandom(0);
	}


}
