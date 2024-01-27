package com.github.anopensaucedev.fasterrandom.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Random;

@Environment(EnvType.CLIENT)
@Mixin(ItemEntityRenderer.class)
public class ItemEntityRendererMixin {
	@Unique
	private static final Random fasterrandom$random = new Random();

	@Inject(method = "render(Lnet/minecraft/entity/ItemEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;setSeed(J)V"))
	private void fasterrandom$renderSetRandomSeedInject(ItemEntity itemEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci, @Local(ordinal = 1) int j) {
		fasterrandom$random.setSeed(j);
	}

	@ModifyArgs(method = "render(Lnet/minecraft/entity/ItemEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V", ordinal = 2))
	private void fasterrandom$renderMakeRandomTranslationWithDepthConsistent(Args args) {
		args.set(0, (fasterrandom$random.nextFloat() * 2.0f - 1.0f) * 0.15f);
		args.set(1, (fasterrandom$random.nextFloat() * 2.0f - 1.0f) * 0.15f);
		args.set(2, (fasterrandom$random.nextFloat() * 2.0f - 1.0f) * 0.15f);
	}

	@ModifyArgs(method = "render(Lnet/minecraft/entity/ItemEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V", ordinal = 3))
	private void fasterrandom$renderMakeRandomTranslationConsistent(Args args) {
		args.set(0, (fasterrandom$random.nextFloat() * 2.0f - 1.0f) * 0.15f);
		args.set(1, (fasterrandom$random.nextFloat() * 2.0f - 1.0f) * 0.15f);
		args.set(2, (fasterrandom$random.nextFloat() * 2.0f - 1.0f) * 0.15f);
	}
}
