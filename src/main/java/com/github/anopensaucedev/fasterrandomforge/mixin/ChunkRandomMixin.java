package com.github.anopensaucedev.fasterrandomforge.mixin;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(WorldgenRandom.class)
public class ChunkRandomMixin {

	@Mutable
	@Shadow
	@Final
	private RandomSource randomSource;

	@Inject(method = "<init>",at = @At("RETURN"))
	private void fasterrandom$fixBetterX(RandomSource p_224680_, CallbackInfo ci){
		this.randomSource = new LegacyRandomSource(0);
	}


}
