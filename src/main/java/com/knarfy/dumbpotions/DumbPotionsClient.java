package com.knarfy.dumbpotions;

import com.knarfy.dumbpotions.init.ModEntityRenderers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class DumbPotionsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModEntityRenderers.load();
    }
}
