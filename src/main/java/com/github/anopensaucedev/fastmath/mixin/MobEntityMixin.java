package com.github.anopensaucedev.fastmath.mixin;

import com.github.anopensaucedev.fastmath.Util.FastRandom;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MobEntity.class)
public class MobEntityMixin {

    @Redirect(method = "baseTick",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I"))
    public int random1(Random instance, int i){
        return FastRandom.FastRandomInt();
    }

    @Redirect(method = "getXpToDrop",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I"))
    public int random2(Random instance, int i){
        return FastRandom.FastRandomInt();
    }

    @Redirect(method = "playSpawnEffects",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextGaussian()D"))
    public double random3(Random instance){
        return FastRandom.FastRandomGaussian();
    }

    @Redirect(method = "tryEquip",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random4(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "checkDespawn",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I"))
    public int random5(Random instance, int i){
        return FastRandom.FastRandomInt();
    }

    @Redirect(method = "dropEquipment",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I"))
    public int random6(Random instance, int i){
        return FastRandom.FastRandomInt();
    }

    @Redirect(method = "dropEquipment",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random7(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "initEquipment",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random8(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "initEquipment",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I"))
    public int random9(Random instance, int i){
        return FastRandom.FastRandomInt();
    }

    @Redirect(method = "enchantMainHandItem",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I"))
    public int random10(Random instance, int i){
        return FastRandom.FastRandomInt(i);
    }

    @Redirect(method = "enchantMainHandItem",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random11(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "enchantEquipment",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I"))
    public int random12(Random instance, int i){
        return FastRandom.FastRandomInt(i);
    }

    @Redirect(method = "enchantEquipment",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random13(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "initialize",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random14(Random instance){
        return FastRandom.FastRandomFloat();
    }
    @Redirect(method = "initialize",at= @At(value = "INVOKE",target = "Lnet/minecraft/world/ServerWorldAccess;getRandom()Lnet/minecraft/util/math/random/Random;"))
    public Random random15(ServerWorldAccess instance){
        return null;
    }

    @Redirect(method = "initialize",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextTriangular(DD)D"))
    public double random16(Random instance, double mode, double deviation){
        return FastRandom.FastNextTriangular(mode,deviation);
    }

    @Redirect(method = "disablePlayerShield",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random17(Random instance){
        return FastRandom.FastRandomFloat();
    }

    @Redirect(method = "isAffectedByDaylight",at= @At(value = "INVOKE",target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
    public float random18(Random instance){
        return FastRandom.FastRandomFloat();
    }

}
