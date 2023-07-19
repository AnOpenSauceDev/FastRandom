package com.github.anopensaucedev.fastmath.mixin;


import com.github.anopensaucedev.fastmath.Util.FastRandom;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;


@Mixin(Entity.class)
public abstract class EntityMixin {



    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Shadow protected abstract SoundEvent getSwimSound();

    @Shadow private float lastChimeIntensity;

    @Shadow private int lastChimeAge;

    @Shadow public int age;


    @Shadow @Final protected Random random = null; // null out


    @Redirect(method = "setOnFireFromLava",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random1(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "playExtinguishSound",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random2(Random instance){
        return FastRandom.FastRandomFloat();
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void playStepSound(BlockPos pos, BlockState state) {
        lastChimeIntensity *= (float) Math.pow(0.997, this.age - this.lastChimeAge);
        this.lastChimeIntensity = Math.min(1.0f, this.lastChimeIntensity + 0.07f);
        float f = 0.5f + this.lastChimeIntensity * FastRandom.FastRandomFloat() * 1.2f;
        float g = 0.1f + this.lastChimeIntensity * 1.2f;
        this.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, g, f);
        lastChimeAge = age;
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
