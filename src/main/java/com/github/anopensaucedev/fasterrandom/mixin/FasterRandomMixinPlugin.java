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

import static com.github.anopensaucedev.fasterrandom.FasterRandom.MOD_NAME;

public class FasterRandomMixinPlugin implements IMixinConfigPlugin {
	private static final @NotNull Supplier<Boolean> TRUE = () -> true;
	private static final @NotNull Map<String, Supplier<Boolean>> CONDITIONS = ImmutableMap.of();

	private static boolean hasLoggedLoadingMessage = false;

	@Override
	public boolean shouldApplyMixin(@NotNull String targetClassName, @NotNull String mixinClassName) {
		if (!JvmUtil.isRandomGeneratorFactorySupported()) {
			if (hasLoggedLoadingMessage) {
				return false;
			}

			@NotNull String warningMessage = String.format(
					"""
							Faster Random was unable to load!
							WARNING: Your Java Virtual Machine (JVM) does not implement RandomGeneratorFactory and/or L64X128MixRandom, which Faster Random requires to run.
							Please use a supported JVM such as Azul Zulu (https://www.azul.com/downloads/#zulu) or GraalVM (https://www.graalvm.org/downloads/).
							Your JVM, %s version %s, is not suitable for Faster Random. Faster Random will disable itself.
							""",
					System.getProperty("java.vm.name"),
					System.getProperty("java.vm.version")
			);
			FasterRandom.LOGGER.warn(warningMessage);
			hasLoggedLoadingMessage = true;
			return false;
		}

		if (hasLoggedLoadingMessage) {
			return CONDITIONS.getOrDefault(mixinClassName, TRUE).get();
		}

		FasterRandom.LOGGER.info("Loading {}.", MOD_NAME);
		hasLoggedLoadingMessage = true;
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
