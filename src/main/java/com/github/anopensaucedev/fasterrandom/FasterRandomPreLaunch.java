package com.github.anopensaucedev.fasterrandom;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class FasterRandomPreLaunch implements PreLaunchEntrypoint {
	public static final Logger LOGGER = LoggerFactory.getLogger("Faster Random PreLaunch");

	@Override
	public void onPreLaunch() {
		LOGGER.info("Preparing to launch Faster Random!");
		LOGGER.info("If Minecraft crashes past this point, your JVM is not supported.");
		try{
			Class.forName("java.util.random.RandomGeneratorFactory"); // check for JVM support.
		}catch (ClassNotFoundException exception){
			LOGGER.error("Your JVM does not support Faster Random! Crashing game!");
			JOptionPane.showMessageDialog(null,"._. Faster Random has crashed... \nERROR: Your Java Virtual Machine (JVM) does not support the features that Faster Random absolutely requires to run. \nPlease get a supported JVM such as GraalVM (at https://www.graalvm.org/) or Azul JDK (at https://www.azul.com/downloads/#zulu). \nYour JVM, " + System.getProperty("java.vm.name") + " " + System.getProperty("java.vm.version")  + " Is either too old, or is not suitable for Faster Random. \nMinecraft will now crash.", "Faster Random Loader",JOptionPane.WARNING_MESSAGE);
			throw new RuntimeException();
		}
		LOGGER.info("Your JVM seems to be supported!");
	}
}
