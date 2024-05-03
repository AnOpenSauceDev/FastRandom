package com.github.anopensaucedev.fasterrandomtest;

import com.github.anopensaucedev.fasterrandomtest.benchmark.RandomNumberGenerationBenchmark;
import net.fabricmc.api.ModInitializer;

public class FasterRandomTest implements ModInitializer {
	@Override
	public void onInitialize() {
		RandomNumberGenerationBenchmark.runLegacyBenchmark();
		RandomNumberGenerationBenchmark.runBenchmark();
	}
}
