package com.knarfy.dumbpotions.command;

import com.knarfy.dumbpotions.gui.SubscribeHandler;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class OpenGuiCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("subscribe").executes(ctx -> {
            Objects.requireNonNull(ctx.getSource().getPlayer()).openMenu(new MenuProvider() {
                @Override
                public @NotNull Component getDisplayName() {
                    return Component.literal("Subscribe!");
                }

                @Override
                public @NotNull AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
                    return new SubscribeHandler(i, inventory,
                            ContainerLevelAccess.create(player.getCommandSenderWorld(), player.blockPosition()));
                }
            });

            return 0;
        }));
    }

}
