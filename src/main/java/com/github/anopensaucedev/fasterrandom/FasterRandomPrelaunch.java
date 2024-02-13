package com.github.anopensaucedev.fasterrandom;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FasterRandomPrelaunch implements PreLaunchEntrypoint {

	public static Logger logger = LoggerFactory.getLogger("Faster Random Prelaunch");

	public static final String SYSTEM_PROPERTY_JVM_STRING = "java.vm.name";

	@Override
	public void onPreLaunch() {

		logger.info("preparing to launch Faster Random!");
		logger.info("Checking if JVM \"{}\" is officially supported by Faster Random...",System.getProperty(SYSTEM_PROPERTY_JVM_STRING));
		if(isJVMsupported(System.getProperty(SYSTEM_PROPERTY_JVM_STRING))){
			logger.info("JVM supported! Faster Random should behave correctly.");
		}else {
			logger.error("### WARNING! JVM Not supported! ### \n If minecraft crashes after this message, your JVM does not support the minimum requirements for Faster Random.");
		}

	}

	public boolean isJVMsupported(String vm){

		if(vm.contains("OpenJDK")){ // if our vendor is OpenJDK (seems to work)
			return true;
		}

		if(vm.contains("GraalVM")){ // if our vendor is GraalVM (works)
			return true;
		}

		return false;
	}

}
