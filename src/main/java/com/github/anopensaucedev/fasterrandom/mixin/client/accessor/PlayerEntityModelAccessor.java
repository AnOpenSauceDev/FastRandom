package com.github.anopensaucedev.fasterrandom.mixin.client.accessor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntityModel.class)
public interface PlayerEntityModelAccessor {
	@Accessor
	List<ModelPart> getParts();
}
