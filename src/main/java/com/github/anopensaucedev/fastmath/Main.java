package com.github.anopensaucedev.nixium;


;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Nixium implements ModInitializer {

    public static boolean LightProcess = false;

    public static final String NIXIUM_MOD_ID = "nixium";

    public static Logger NixiumMainLogger = LoggerFactory.getLogger(NIXIUM_MOD_ID);

    @Override
    public void onInitialize() {
        NixiumMainLogger.info("Nixium has loaded, pay warning that this mod may cause instability.");
    }
}
