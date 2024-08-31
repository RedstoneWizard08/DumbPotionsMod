package com.knarfy.dumbpotions;

import com.knarfy.dumbpotions.client.gui.AvatarComponent;
import com.knarfy.dumbpotions.client.gui.SubscribeScreen;
import com.knarfy.dumbpotions.init.ModEntityRenderers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.gui.screens.MenuScreens;

@Environment(EnvType.CLIENT)
public class DumbPotionsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModEntityRenderers.init();

        MenuScreens.register(DumbPotions.SUBSCRIBE_HANDLER_MENU_TYPE, SubscribeScreen::new);
        ClientLifecycleEvents.CLIENT_STARTED.register(AvatarComponent::preloadImages);
    }
}
