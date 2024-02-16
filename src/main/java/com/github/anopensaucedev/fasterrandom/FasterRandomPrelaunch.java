package com.github.anopensaucedev.fasterrandom;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FasterRandomPrelaunch implements PreLaunchEntrypoint {

	public static Logger logger = LoggerFactory.getLogger("Faster Random Prelaunch");

	public static final String SYSTEM_PROPERTY_JVM_STRING = "java.vm.vendor";

	@Override
	public void onPreLaunch() {

		logger.info("preparing to launch Faster Random!");
		logger.warn("If Minecraft crashes past this point, your JVM is not supported.");
		FasterRandom.RANDOM_GENERATOR_FACTORY.create();
		logger.info("Your JVM seems to be supported!");

	}

}
