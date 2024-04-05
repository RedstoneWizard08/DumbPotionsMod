package com.knarfy.dumbpotions.command;

import com.knarfy.dumbpotions.procedures.ResetAllCommandProcedure;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.Entity;

public class ResetAllCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("resetall").executes(arguments -> {
            Entity entity = arguments.getSource().getEntity();
            ResetAllCommandProcedure.execute(entity);
            return 0;
        }));
    }
}
