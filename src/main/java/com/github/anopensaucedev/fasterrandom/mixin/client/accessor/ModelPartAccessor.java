package com.github.anopensaucedev.fasterrandom.mixin.client.accessor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(ModelPart.class)
public interface ModelPartAccessor {
	@Accessor
	List<ModelPart.Cuboid> getCuboids();
}
