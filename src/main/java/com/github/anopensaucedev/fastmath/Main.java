package com.github.anopensaucedev.fastmath;



import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Main implements ModInitializer {


    public static final String MOD_ID = "fastrandom";

    public static Logger MainLogger = LoggerFactory.getLogger("Faster Random Logger");

    @Override
    public void onInitialize() {
        MainLogger.info("Faster Random has loaded!");
        MainLogger.warn("While terrain generation will \"function\" properly, cave generation will have a different seed, which may or may not be noticeable. Keep this in mind.");
        MainLogger.warn("If you have any terrain-related issues, make sure to mention me (AnOpenSauceDev) in an issue just to be sure!");
    }
}
