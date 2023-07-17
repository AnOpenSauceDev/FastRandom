package com.github.anopensaucedev.fastmath.mixin;

import com.github.anopensaucedev.fastmath.Util.FastRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.util.UUID;

@Mixin(MathHelper.class)
public class BetterMaths {




    @Shadow
    private static final float[] SINE_TABLE = Util.make(new float[65536], sineTable -> {
        for (int i = 0; i < (sineTable).length; ++i) {
            sineTable[i] = (float)Math.sin((double)i * Math.PI * 2.0 / 65536.0);
        }
    });


    /**
     * @author AnOpenSauceDev
     * @reason Optimize Gaussian to use ThreadLocal
     */
    @Overwrite
    public static float nextGaussian(Random random, float mean, float deviation) {
        return mean + (float)FastRandom.FastRandomGaussian() * deviation;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static int nextBetween(Random random, int min, int max) {
        return (int) (FastRandom.FastRandomInt(max - min + 1) + min);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static float nextBetween(Random random, float min, float max) {
        return FastRandom.FastRandomFloat() * (max - min) + min;
    }



    /**
     * @author
     * @reason
     */
    @Overwrite
    public static UUID randomUuid(Random random) {
        long l = FastRandom.FastRandomLong() & 0xFFFFFFFFFFFF0FFFL | 0x4000L;
        long m = FastRandom.FastRandomLong() & 0x3FFFFFFFFFFFFFFFL | Long.MIN_VALUE;
        return new UUID(l, m);
    }

    /**
     * @author AnOpenSauceDev
     * @reason Speed up math
     */
    @Overwrite
    public static int nextInt(Random random, int min, int max) {
        if (min >= max) {
            return min;
        }
        return (int) FastRandom.FastRandomInt(max - min + 1) + min; // unnecessarily long?
        //return random.nextInt(max - min + 1) + min;
    }

    /**
     * @author AnOpenSauceDev
     * @reason Speed up math
     */
    @Overwrite
    public static float nextFloat(Random random, float min, float max) {
        if (min >= max) {
            return min;
        }
        return FastRandom.FastRandomFloat() * (max - min) + min; // what is this!?
        //return random.nextFloat() * (max - min) + min;
    }

    /**
     * @author AnOpenSauceDev
     * @reason Speed up math
     */
    @Overwrite
    public static double nextDouble(Random random, double min, double max) {
        if (min >= max) {
            return min;
        }
        return FastRandom.FastRandomDouble() * (max - min) + min; // what is this!?
    }


}
