package com.knarfy.dumbpotions.command;

import com.knarfy.dumbpotions.init.ModPotions;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;

import java.util.Objects;

public class EffectCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("modeffects").requires(it -> it.hasPermission(2)).executes(ctx -> {
            var player = Objects.requireNonNull(ctx.getSource().getPlayer());

            for (Potion potion : ModPotions.ALL) {
                player.addEffect(potion.getEffects().get(0));
            }

            return 0;
        }));

        dispatcher.register(Commands.literal("modpotions").requires(it -> it.hasPermission(2)).executes(ctx -> {
            var player = Objects.requireNonNull(ctx.getSource().getPlayer());

            for (Potion potion : ModPotions.ALL) {
                player.addItem(PotionUtils.setPotion(new ItemStack(Items.POTION, 1), potion));
            }

            return 0;
        }));

        dispatcher.register(Commands.literal("alleffects").requires(it -> it.hasPermission(2)).executes(ctx -> {
            if (ctx.getSource().getLevel().isClientSide) return 1;

            var player = Objects.requireNonNull(ctx.getSource().getPlayer());
            Registry<Potion> potions = ctx.getSource().registryAccess().registryOrThrow(Registries.POTION);

            for (Potion potion : potions) {
                player.addEffect(potion.getEffects().get(0));
            }

            return 0;
        }));

        dispatcher.register(Commands.literal("allpotions").requires(it -> it.hasPermission(2)).executes(ctx -> {
            if (ctx.getSource().getLevel().isClientSide) return 1;

            var player = Objects.requireNonNull(ctx.getSource().getPlayer());
            Registry<Potion> potions = ctx.getSource().registryAccess().registryOrThrow(Registries.POTION);

            for (Potion potion : potions) {
                player.addItem(PotionUtils.setPotion(new ItemStack(Items.POTION, 1), potion));
            }

            return 0;
        }));
    }
}
