package com.github.anopensaucedev.fasterrandom;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

import javax.swing.*;
import java.util.random.RandomGeneratorFactory;

public class FasterRandomPreLaunch implements PreLaunchEntrypoint {
	@Override
	public void onPreLaunch() {
		if (!checkRandomGeneratorFactory() || !RandomGeneratorFactory.all().map(RandomGeneratorFactory::name).toList().contains("L64X128MixRandom")) {
			JOptionPane.showMessageDialog(null,
					"Faster Random has crashed!\n" +
							"ERROR: Your Java Virtual Machine (JVM) does not implement RandomGeneratorFactory and/or L64X128MixRandom, which Faster Random requires to run.\n" +
							"Please use a supported JVM such as Azul Zulu (https://www.azul.com/downloads/#zulu) or GraalVM (https://www.graalvm.org/downloads/).\n" +
							"Your JVM, "+System.getProperty("java.vm.name")+" version "+System.getProperty("java.vm.version")+", is not suitable for Faster Random.",
					"Faster Random", JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException("[Faster Random] This JVM does not implement RandomGeneratorFactory and/or L64X128MixRandom!");
		}
	}
	
	private boolean checkRandomGeneratorFactory() {
		try {
			Class.forName("java.util.random.RandomGeneratorFactory");
			return true;
		} catch (ClassNotFoundException ignored) {
			return false;
		}
	}
}
