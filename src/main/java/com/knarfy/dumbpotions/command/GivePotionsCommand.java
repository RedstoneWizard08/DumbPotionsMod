package com.knarfy.dumbpotions.command;

import com.knarfy.dumbpotions.init.ModPotions;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;

import java.util.Objects;

public class GivePotionsCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("givepotions").executes(arguments -> {
            var player = Objects.requireNonNull(arguments.getSource().getPlayer());

            var box = namedItem(new ItemStack(Items.PURPLE_SHULKER_BOX, 1),
                    Component.literal("Dumb Potion Ideas").withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.BOLD));

            var meta = new ShulkerBoxBlockEntity(DyeColor.PURPLE, BlockPos.ZERO,
                    Blocks.PURPLE_SHULKER_BOX.defaultBlockState());

            meta.setItem(0, namedItem(new ItemStack(Items.PAPER, 1),
                    Component.literal("Level 1").withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD)));

            meta.setItem(2, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.SHRIEKING));
            meta.setItem(3, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.SYSTEM_ZEE_POTION));
            meta.setItem(4, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.SHAPESHIFTING_POTION));
            meta.setItem(5, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.THEME_SONG_POTION));
            meta.setItem(6, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.SUBSCRIBE_POTION));

            meta.setItem(9, namedItem(new ItemStack(Items.PAPER, 1),
                    Component.literal("Level 2").withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD)));

            meta.setItem(11, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.CORRUPTION_POTION));
            meta.setItem(12, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.REVEALING_POTION));
            meta.setItem(13, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.DESPAWN_POTION));
            meta.setItem(14, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.FURIOUS_COCKTAIL));
            meta.setItem(15, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.XVII_POTION));

            meta.setItem(18, namedItem(new ItemStack(Items.PAPER, 1),
                    Component.literal("Level 3").withStyle(ChatFormatting.RED, ChatFormatting.BOLD)));

            meta.setItem(21, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.LAUNCHING_POTION));
            meta.setItem(22, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.TITAN_POTION));
            meta.setItem(23,
                    PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.TWENTY_FIVE_PERCENT_POTION));

            meta.saveToItem(box);
            player.addItem(box);

            return 0;
        }));
    }

    private static ItemStack namedItem(ItemStack item, Component name) {
        var nameTag = new CompoundTag();

        nameTag.putString("Name", Component.Serializer.toJson(name));
        item.addTagElement("display", nameTag);

        return item;
    }

}
