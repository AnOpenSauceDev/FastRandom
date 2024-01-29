package com.github.anopensaucedev.fasterrandom.mixin;

import com.github.anopensaucedev.fasterrandom.FasterRandom;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import static com.github.anopensaucedev.fasterrandom.FasterRandom.LOGGER;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {



	@Redirect(method = "calculateRequiredExperienceLevel",at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I"))
	private static int fasterrandom$calculateRequiredExperienceLevel(Random instance, int i){
		return instance.nextInt(i);
	}

	@Debug
	@Inject(method = "generateEnchantments",at = @At("TAIL"))
	private static void validateenchantments(Random random, ItemStack stack, int level, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir){
		var values = cir.getReturnValue();
		for (int i = 0; i < values.size(); i++) {
			var ench = values.get(i);
			LOGGER.info("generated enchantment {}, level {}",ench.enchantment.getTranslationKey(),ench.level);
		}
	}

	@Redirect(method = "generateEnchantments",at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I"))
	private static int fasterrandom$generateEnchantmentsNextInt(Random instance, int i){
		return instance.nextInt(i);
	}

	@Redirect(method = "generateEnchantments",at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
	private static float fasterrandom$generateEnchantmentsNextFloat(Random instance){
		return instance.nextFloat();
	}

}
