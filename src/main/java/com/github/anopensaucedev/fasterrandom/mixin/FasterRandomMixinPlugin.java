package com.github.anopensaucedev.fasterrandom.mixin;

import com.github.anopensaucedev.fasterrandom.FasterRandom;
import com.github.anopensaucedev.fasterrandom.util.jvm.JvmUtil;
import com.google.common.collect.ImmutableMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class FasterRandomMixinPlugin implements IMixinConfigPlugin {
	private static final @NotNull Supplier<Boolean> TRUE = () -> true;
	private static final @NotNull Map<String, Supplier<Boolean>> CONDITIONS = ImmutableMap.of();

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		if (!JvmUtil.isRandomGeneratorFactorySupported()) {
			@NotNull String errorMessage = String.format(
					"""
					Faster Random was unable to load!
					ERROR: Your Java Virtual Machine (JVM) does not implement RandomGeneratorFactory and/or L64X128MixRandom, which Faster Random requires to run.
					Please use a supported JVM such as Azul Zulu (https://www.azul.com/downloads/#zulu) or GraalVM (https://www.graalvm.org/downloads/).
					Your JVM, %s version %s, is not suitable for Faster Random. Faster Random will disable itself.
					""",
					System.getProperty("java.vm.name"),
					System.getProperty("java.vm.version")
			);
			FasterRandom.LOGGER.warn(errorMessage);
			return false;
		}

		return CONDITIONS.getOrDefault(mixinClassName, TRUE).get();
	}

	@Override
	public void onLoad(String mixinPackage) {}

	@Override
	public @Nullable String getRefMapperConfig() {
		return null;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

	@Override
	public @Nullable List<String> getMixins() {
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
