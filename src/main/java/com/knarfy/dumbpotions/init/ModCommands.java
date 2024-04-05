package com.knarfy.dumbpotions.init;

import com.knarfy.dumbpotions.command.GivePotionsCommand;
import com.knarfy.dumbpotions.command.OpenGuiCommand;
import com.knarfy.dumbpotions.command.ResetAllCommand;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class ModCommands {

    public static void load() {
        CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, dedicated) -> {
            GivePotionsCommand.register(dispatcher);
            ResetAllCommand.register(dispatcher);
            OpenGuiCommand.register(dispatcher);
        });
    }

}
