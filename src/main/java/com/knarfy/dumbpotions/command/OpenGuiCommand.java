package com.knarfy.dumbpotions.command;

import com.knarfy.dumbpotions.gui.SubscribeGui;
import com.knarfy.dumbpotions.gui.SubscribeScreen;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;

public class OpenGuiCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("subscribe").executes(arguments -> {
            Minecraft.getInstance().setScreen(new SubscribeScreen(new SubscribeGui()));

            return 0;
        }));
    }
}
