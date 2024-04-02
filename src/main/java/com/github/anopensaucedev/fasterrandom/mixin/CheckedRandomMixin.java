package com.github.anopensaucedev.fasterrandom.mixin;

import com.github.anopensaucedev.fasterrandom.RandomGeneratorCheckedRandom;
import com.google.common.annotations.VisibleForTesting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.random.RandomSplitter;
import net.minecraft.util.thread.LockHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CheckedRandom.class)
public class CheckedRandomMixin {

	@Unique
	RandomGeneratorCheckedRandom random;

	@Inject(method = "<init>",at = @At("RETURN"))
	public void guh(long seed, CallbackInfo ci){
	}

	@Overwrite
	public Random split() {
		return random.split();
	}


	@Overwrite
	public RandomSplitter nextSplitter() {
		return new RandomGeneratorCheckedRandom.Splitter(random.nextLong());
	}

	@Overwrite
	public void setSeed(long seed) {
		if(random == null) {random = new RandomGeneratorCheckedRandom(seed); return;}

		random.setSeed(seed);
	}

	@Overwrite
	public int next(int bits) {
		return random.next(bits);
	}

	@Overwrite
	public double nextGaussian() {
		return random.nextGaussian();
	}

	@Mixin(CheckedRandom.Splitter.class)
	public static class Splitter implements RandomSplitter {
		@Unique
		private final long seed;

		public Splitter(long seed) {
			this.seed = seed;
		}

		@Overwrite
		public Random split(int x, int y, int z) {
			long l = MathHelper.hashCode(x, y, z);
			long m = l ^ this.seed;
			return new RandomGeneratorCheckedRandom(m);
		}

		@Overwrite
		public Random split(String seed) {
			int i = seed.hashCode();
			return new RandomGeneratorCheckedRandom((long)i ^ this.seed);
		}

		@VisibleForTesting
		public void addDebugInfo(StringBuilder info) {
			info.append("LegacyPositionalRandomFactory{").append(this.seed).append("}");
		}
	}




}
