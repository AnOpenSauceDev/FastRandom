package com.github.anopensaucedev.fastmath.Util;

import java.util.concurrent.ThreadLocalRandom;

public class FastRandom {


    public static final ThreadLocalRandom sharedRandom = ThreadLocalRandom.current();


    //uses ThreadLocalRandom for much better random speed
    public static double FastRandomDouble(){
        return ThreadLocalRandom.current().nextDouble(); // MUCH faster than Math.random
    }

    public static float FastRandomFloat(){
        return ThreadLocalRandom.current().nextFloat();
    }

    public static int FastRandomInt(){
        return ThreadLocalRandom.current().nextInt();
    }

    public static double FastNextTriangular(double mode, double deviation) {
        return mode + deviation * (FastRandomDouble() - FastRandomDouble());
    }

    public static int FastRandomInt(int var){
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
