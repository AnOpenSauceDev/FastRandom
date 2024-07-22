package com.github.anopensaucedev.fasterrandom.util.jvm;

import org.jetbrains.annotations.Nullable;

public class JvmUtil {
	private static @Nullable Boolean isRandomGeneratorFactorySupported;

	/**
	 * Checks if the JVM supports {@link java.util.random.RandomGeneratorFactory}, caching the result during the first invocation.
	 *
	 * @return If the JVM supports {@link java.util.random.RandomGeneratorFactory}.
	 */
	public static boolean isRandomGeneratorFactorySupported() {
		if (isRandomGeneratorFactorySupported == null) {
			try {
				Class.forName("java.util.random.RandomGeneratorFactory");
				isRandomGeneratorFactorySupported = true;
			} catch (ClassNotFoundException ignored) {
				isRandomGeneratorFactorySupported = false;
			}
		}

		return isRandomGeneratorFactorySupported;
	}
}
