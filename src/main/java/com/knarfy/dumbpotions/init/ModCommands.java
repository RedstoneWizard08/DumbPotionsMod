package com.knarfy.dumbpotions.init;

import com.knarfy.dumbpotions.command.*;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ModCommands {
    public static void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, dedicated) -> {
            GivePotionsCommand.register(dispatcher);
            EffectCommands.register(dispatcher);
            GiveEmptyWorldCommand.register(dispatcher);
            ResetAllCommand.register(dispatcher);
            OpenGuiCommand.register(dispatcher);
        });
    }
}
