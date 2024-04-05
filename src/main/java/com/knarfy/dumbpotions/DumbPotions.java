package com.knarfy.dumbpotions;

import com.knarfy.dumbpotions.init.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DumbPotions implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "dumbpotions";

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Knarfy's Dumb Potions");

        ModGameRules.load();
        ModEffects.load();
        ModPotions.load();
        ModEntities.load();
        ModItems.load();
        ModCommands.load();
        ModSounds.load();
    }
}
