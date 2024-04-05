package com.knarfy.dumbpotions.command;

import com.knarfy.dumbpotions.procedures.GivePotionsCommandProcedure;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.Entity;

public class GivePotionsCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("givepotions").executes(arguments -> {
            Entity entity = arguments.getSource().getEntity();
            GivePotionsCommandProcedure.execute(entity);
            return 0;
        }));
    }
}
