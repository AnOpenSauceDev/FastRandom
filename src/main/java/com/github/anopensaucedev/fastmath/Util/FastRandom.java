package com.github.anopensaucedev.fastmath.Util;

import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class FastRandom {

    //uses ThreadLocalRandom for much better random speed
    public static double FastRandomDouble(){
        return ThreadLocalRandom.current().nextDouble(); // MUCH faster than Math.random
    }

    public static float FastRandomFloat(){
        return ThreadLocalRandom.current().nextFloat();
    }

    public static float FastRandomInt(){
        return ThreadLocalRandom.current().nextInt();
    }



    public static float FastRandomInt(int var){
        return ThreadLocalRandom.current().nextInt(var);
    }

    public static float FastRandomFloat(float var){
        return ThreadLocalRandom.current().nextFloat(var);
    }

    public static double FastRandomDouble(double var){
        return ThreadLocalRandom.current().nextDouble(var); // MUCH faster than Math.random
    }

    public static long FastRandomLong(long var){
        return ThreadLocalRandom.current().nextLong(var); // MUCH faster than Math.random
    }

    public static long FastRandomLong(){
        return ThreadLocalRandom.current().nextLong(); // MUCH faster than Math.random
    }


    public static double FastRandomGaussian(){
        return ThreadLocalRandom.current().nextGaussian(); // MUCH faster than Math.random
    }

}
