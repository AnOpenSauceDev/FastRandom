package com.github.anopensaucedev.fasterrandom;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FasterRandomPreLaunch implements PreLaunchEntrypoint {
	public static final Logger LOGGER = LoggerFactory.getLogger("Faster Random PreLaunch");

	@Override
	public void onPreLaunch() {
		LOGGER.info("Preparing to launch Faster Random!");
		LOGGER.info("If Minecraft crashes past this point, your JVM is not supported.");
		FasterRandom.RANDOM_GENERATOR_FACTORY.create();
		LOGGER.info("Your JVM seems to be supported!");
	}
}
