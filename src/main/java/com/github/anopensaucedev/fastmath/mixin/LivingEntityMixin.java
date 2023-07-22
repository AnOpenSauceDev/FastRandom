package com.github.anopensaucedev.fastmath.mixin;

import com.github.anopensaucedev.fastmath.Util.FastRandom;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Redirect(method = "baseTick",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextDouble()D"))
    public double random1(Random instance){
        return FastRandom.FastRandomDouble();
    }

    @Redirect(method = "displaySoulSpeedEffects",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextDouble()D"))
    public double random2(Random instance){
        return FastRandom.FastRandomDouble();
    }

    @Redirect(method = "displaySoulSpeedEffects",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random3(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "addSoulSpeedBoostIfNeeded",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random4(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "getNextAirUnderwater",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I"))
    public int random5(Random instance, int i){
        return FastRandom.FastRandomInt(i);
    }

    @Redirect(method = "tickStatusEffects",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I"))
    public int random6(Random instance, int i){
        return FastRandom.FastRandomInt(i);
    }

    @Redirect(method = "tickStatusEffects",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextBoolean()Z"))
    public boolean random7(Random instance){
        return FastRandom.FastRandomBoolean();

    }

    @Redirect(method = "damage",at= @At(value = "INVOKE",target = "Ljava/lang/Math;random()D"))
    public double random8(){
        return FastRandom.FastRandomDouble();
    }

    @Redirect(method = "playEquipmentBreakEffects",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random9(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "onDamaged",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random10(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "handleStatus",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextDouble()D"))
    public double random11(Random instance){
        return FastRandom.FastRandomDouble();
    }

    @Redirect(method = "handleStatus",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random12(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "addDeathParticles",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextGaussian()D"))
    public double random13(Random instance){
        return FastRandom.FastRandomGaussian();
    }


    @Redirect(method = "getSoundPitch",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random14(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "tickCramming",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I"))
    public int random15(Random instance, int i){
        return FastRandom.FastRandomInt(i);
    }

    @Redirect(method = "spawnConsumptionEffects",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random16(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "spawnConsumptionEffects",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I"))
    public int random17(Random instance, int i){
        return FastRandom.FastRandomInt(i);
    }

    @Redirect(method = "spawnItemParticles",at= @At(value = "INVOKE",target = "Ljava/lang/Math;random()D"))
    public double random18(){
        return FastRandom.FastRandomDouble();
    }

    @Redirect(method = "spawnItemParticles",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random19(Random instance){
        return FastRandom.FastRandomFloat();
    }


    @Redirect(method = "eatFood",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random20(Random instance){
        return FastRandom.FastRandomFloat();
    }


}
