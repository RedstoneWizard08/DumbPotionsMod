package com.knarfy.dumbpotions;

import com.knarfy.dumbpotions.gui.SubscribeScreen;
import com.knarfy.dumbpotions.init.ModEntityRenderers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;

@Environment(EnvType.CLIENT)
public class DumbPotionsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModEntityRenderers.load();

        MenuScreens.register(DumbPotions.SUBSCRIBE_HANDLER_MENU_TYPE, SubscribeScreen::new);
    }
}
