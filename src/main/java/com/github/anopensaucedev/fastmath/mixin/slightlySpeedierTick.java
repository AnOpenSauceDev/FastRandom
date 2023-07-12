package com.github.anopensaucedev.fastmath.mixin;

import com.github.anopensaucedev.fastmath.Util.FastRandom;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LightmapTextureManager.class)
public class slightlySpeedierTick {

    @Redirect(method = "tick",at=@At(value = "INVOKE",target = "Ljava/lang/Math;random()D")) // replace all random calls with our much faster implementation
    public double callFastRandom(){
        return FastRandom.FastRandomDouble();
    }

}
