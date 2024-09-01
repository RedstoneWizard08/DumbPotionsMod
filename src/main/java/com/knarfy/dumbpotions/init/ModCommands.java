package com.knarfy.dumbpotions.init;

import com.knarfy.dumbpotions.command.DumbPotionsCommand;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ModCommands {
    public static void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, dedicated) -> {
            DumbPotionsCommand.register(dispatcher);
        });
    }
}
