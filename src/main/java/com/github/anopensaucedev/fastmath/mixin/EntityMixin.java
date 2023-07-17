package com.github.anopensaucedev.fastmath.mixin;


import com.github.anopensaucedev.fastmath.Util.FastRandom;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;


@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Shadow protected abstract SoundEvent getSwimSound();

    @Redirect(method = "setOnFireFromLava",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random1(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "playExtinguishSound",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random2(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "playAmethystChimeSound",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random3(Random instance){
        return FastRandom.FastRandomFloat();
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void playSwimSound(float volume) {
        playSound(getSwimSound(), volume, 1.0f + (FastRandom.FastRandomFloat() - FastRandom.FastRandomFloat()) * 0.4f);
    }

    @Redirect(method = "onSwimmingStart",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextDouble()D"))
    public double random5(Random instance){
        return FastRandom.FastRandomDouble();
    }



    @Redirect(method = "spawnSprintingParticles",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextDouble()D"))
    public double random6(Random instance){
        return FastRandom.FastRandomDouble();
    }

    @Redirect(method = "pushOutOfBlocks",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random7(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "getRandomBodyY",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextDouble()D"))
    public double random8(Random instance){
        return FastRandom.FastRandomDouble();
    }
    @Redirect(method = "getParticleX",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextDouble()D"))
    public double random9(Random instance){
        return FastRandom.FastRandomDouble();
    }
    @Redirect(method = "getParticleZ",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextDouble()D"))
    public double random10(Random instance){
        return FastRandom.FastRandomDouble();
    }




}
