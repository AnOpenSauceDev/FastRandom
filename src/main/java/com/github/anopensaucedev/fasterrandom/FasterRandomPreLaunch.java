package com.github.anopensaucedev.fasterrandom;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

import javax.swing.*;
import java.awt.*;
import java.util.random.RandomGeneratorFactory;

public class FasterRandomPreLaunch implements PreLaunchEntrypoint {
	@Override
	public void onPreLaunch() {
		if (!checkRandomGeneratorFactory() || !RandomGeneratorFactory.all().map(RandomGeneratorFactory::name).toList().contains("L64X128MixRandom")) {
			String errorMessage = String.format(
				"""
				Faster Random has crashed!
				ERROR: Your Java Virtual Machine (JVM) does not implement RandomGeneratorFactory and/or L64X128MixRandom, which Faster Random requires to run.
				Please use a supported JVM such as Azul Zulu (https://www.azul.com/downloads/#zulu) or GraalVM (https://www.graalvm.org/downloads/).
				Your JVM, %s version %s, is not suitable for Faster Random.
				""",
				System.getProperty("java.vm.name"),
				System.getProperty("java.vm.version")
			);

			try {
				JOptionPane.showMessageDialog(null, errorMessage, "Faster Random", JOptionPane.ERROR_MESSAGE);
			} catch (HeadlessException ignored) {}

			throw new RuntimeException(errorMessage);
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
