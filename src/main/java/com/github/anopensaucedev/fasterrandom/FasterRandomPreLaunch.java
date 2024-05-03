package com.github.anopensaucedev.fasterrandom;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

import java.util.random.RandomGeneratorFactory;

public class FasterRandomPreLaunch implements PreLaunchEntrypoint {
	@Override
	public void onPreLaunch() {
		if (!RandomGeneratorFactory.all().map(RandomGeneratorFactory::name).toList().contains("L64X128MixRandom")) {
			throw new RuntimeException("[Faster Random] This JVM does not implement L64X128MixRandom!");
		}
	}
}
