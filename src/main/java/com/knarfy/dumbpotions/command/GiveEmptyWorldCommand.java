package com.knarfy.dumbpotions.command;

import com.knarfy.dumbpotions.init.ModPotions;
import com.knarfy.dumbpotions.util.EmptyWorldPeople;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;

import java.util.Objects;

public class GiveEmptyWorldCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("ewpotions").requires(it -> it.hasPermission(2)).executes(ctx -> {
            var player = Objects.requireNonNull(ctx.getSource().getPlayer());

            for (EmptyWorldPeople person : EmptyWorldPeople.values()) {
                player.addItem(PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.PLAYER_POTIONS.get(person.getUsername())));
            }

            return 0;
        }));

        dispatcher.register(Commands.literal("eweffects").requires(it -> it.hasPermission(2)).executes(ctx -> {
            var player = Objects.requireNonNull(ctx.getSource().getPlayer());

            for (EmptyWorldPeople person : EmptyWorldPeople.values()) {
                player.addEffect(ModPotions.PLAYER_POTIONS.get(person.getUsername()).getEffects().get(0));
            }

            return 0;
        }));
    }
}
