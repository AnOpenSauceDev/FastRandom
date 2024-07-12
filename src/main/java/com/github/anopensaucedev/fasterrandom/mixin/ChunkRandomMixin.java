package com.github.anopensaucedev.fasterrandom.mixin;

import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.ChunkRandom;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ChunkRandom.class)
public class ChunkRandomMixin {

	/**
	 * @author AnOpenSauceDev
	 * @reason Don't optimize slime chunks, as it shuffles the placement.
	 */
	@Overwrite
	public static Random getSlimeRandom(int chunkX, int chunkZ, long worldSeed, long scrambler) {
		return new CheckedRandom(
				worldSeed + (long)(chunkX * chunkX * 4987142) + (long)(chunkX * 5947611) + (long)(chunkZ * chunkZ) * 4392871L + (long)(chunkZ * 389711) ^ scrambler
		);
	}

}
