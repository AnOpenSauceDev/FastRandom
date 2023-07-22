package com.github.anopensaucedev.fastmath.Util;

import java.util.concurrent.ThreadLocalRandom;

public class FastRandom {


    public static final ThreadLocalRandom sharedRandom = ThreadLocalRandom.current();


    //uses ThreadLocalRandom for much better random speed
    public static double FastRandomDouble(){
        return sharedRandom.nextDouble(); // MUCH faster than Math.random
    }

    public static boolean FastRandomBoolean(){
        return sharedRandom.nextBoolean();
    }

    public static float FastRandomFloat(){
        return ThreadLocalRandom.current().nextFloat();
    }

    public static int FastRandomInt(){
        return sharedRandom.nextInt();
    }

    public static double FastNextTriangular(double mode, double deviation) {
        return mode + deviation * (FastRandomDouble() - FastRandomDouble());
    }

    public static int FastRandomInt(int var){
        return sharedRandom.nextInt(var);
    }

    public static float FastRandomFloat(float var){
        return sharedRandom.nextFloat(var);
    }

    public static double FastRandomDouble(double var){
        return sharedRandom.nextDouble(var); // MUCH faster than Math.random
    }

    public static long FastRandomLong(long var){
        return sharedRandom.nextLong(var); // MUCH faster than Math.random
    }

    public static long FastRandomLong(){
        return sharedRandom.nextLong(); // MUCH faster than Math.random
    }


    public static double FastRandomGaussian(){
        return sharedRandom.nextGaussian(); // MUCH faster than Math.random
    }

}
