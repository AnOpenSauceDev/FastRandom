package com.github.anopensaucedev.fastmath.Util;

import com.github.anopensaucedev.fastmath.mixin.BetterMaths;
import net.minecraft.util.math.random.BaseRandom;
import net.minecraft.util.math.random.CheckedRandom;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class FastRandom {

    /**
     * <h1>Basically all of Faster Random's documentation.</h1>
     *  <strong>Now,</strong> if you're looking at this, you're probably wondering why <strong>{@code ThreadLocalRandom}</strong> is being used here.
     *  The reason is that while Java's standard random is "good enough", its thread safety (among a handful of other things) slows it down.
     *  Faster Random instead uses the much faster (but also way less safe) <strong>{@code ThreadLocalRandom}</strong>.
     *  Benchmarking this method, I personally see up to 100X faster generation of random floating-point numbers.
     *  That being said, these methods only really get called for a handful of events, and there are tonnes of things to optimize (of which I'm too lazy to do). <br></br>
     *  <br></br>
     *  If this text looks weird, make sure to use the "rendered view" button in your IDE.
     */

    public static final ThreadLocalRandom sharedRandom = ThreadLocalRandom.current();


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
