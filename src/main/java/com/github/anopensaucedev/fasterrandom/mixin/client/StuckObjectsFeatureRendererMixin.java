package com.github.anopensaucedev.fasterrandom.mixin.client;

import com.github.anopensaucedev.fasterrandom.mixin.client.accessor.ModelPartAccessor;
import com.github.anopensaucedev.fasterrandom.mixin.client.accessor.PlayerEntityModelAccessor;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.StuckObjectsFeatureRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Environment(EnvType.CLIENT)
@Mixin(StuckObjectsFeatureRenderer.class)
public class StuckObjectsFeatureRendererMixin<T extends LivingEntity> {
	@Unique
	private Random fasterrandom$random;

	@Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V", at = @At(value = "HEAD"))
	private void fasterrandom$renderSetRandom(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
		fasterrandom$random = new Random(livingEntity.getId());
	}

	@WrapOperation(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/PlayerEntityModel;getRandomPart(Lnet/minecraft/util/math/random/Random;)Lnet/minecraft/client/model/ModelPart;"))
	private ModelPart fasterrandom$renderMakeRandomModelPartConsistent(PlayerEntityModel<T> instance, net.minecraft.util.math.random.Random random, Operation<ModelPart.Cuboid> original) {
		var parts = ((PlayerEntityModelAccessor) instance).getParts();
		return parts.get(fasterrandom$random.nextInt(parts.size()));
	}

	@WrapOperation(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelPart;getRandomCuboid(Lnet/minecraft/util/math/random/Random;)Lnet/minecraft/client/model/ModelPart$Cuboid;"))
	private ModelPart.Cuboid fasterrandom$renderMakeRandomCuboidConsistent(ModelPart instance, net.minecraft.util.math.random.Random random, Operation<ModelPart.Cuboid> original) {
		var cuboids = ((ModelPartAccessor) (Object) instance).getCuboids();
		return cuboids.get(fasterrandom$random.nextInt(cuboids.size()));
	}

	@Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/util/math/MathHelper;lerp(FFF)F", ordinal = 0, shift = At.Shift.BEFORE))
	private void fasterrandom$renderMakeRandomDirectionsConsistent(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l, CallbackInfo ci, @Local(ordinal = 6) LocalFloatRef o, @Local(ordinal = 7) LocalFloatRef p, @Local(ordinal = 8) LocalFloatRef q) {
		o.set(fasterrandom$random.nextFloat());
		p.set(fasterrandom$random.nextFloat());
		q.set(fasterrandom$random.nextFloat());
	}
}
