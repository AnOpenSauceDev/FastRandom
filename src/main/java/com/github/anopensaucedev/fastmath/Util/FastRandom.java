package com.github.anopensaucedev.nixium.Util;

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


}
