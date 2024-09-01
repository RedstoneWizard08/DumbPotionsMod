package com.knarfy.dumbpotions.command;

import com.knarfy.dumbpotions.entity.InvisibleEntity;
import com.knarfy.dumbpotions.init.ModPotions;
import com.knarfy.dumbpotions.init.ModSounds;
import com.knarfy.dumbpotions.screen.SubscribeHandler;
import com.knarfy.dumbpotions.util.EmptyWorldPeople;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import org.jetbrains.annotations.NotNull;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

import java.util.Objects;

import static com.knarfy.dumbpotions.potion.ShapeshiftingEffect.unequip;
import static net.minecraft.commands.Commands.literal;

public class DumbPotionsCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                literal("dumbpotions").requires(it -> it.hasPermission(2))
                        .then(literal("menu")
                                .then(literal("subscribe").executes(DumbPotionsCommand::openSubscribeMenu)))
                        .then(literal("reset")
                                .then(literal("all").executes(DumbPotionsCommand::resetAll)))
                        .then(literal("potions")
                                .then(literal("mod").executes(DumbPotionsCommand::giveModPotions))
                                .then(literal("all").executes(DumbPotionsCommand::giveAllPotions))
                                .then(literal("emptyworld").executes(DumbPotionsCommand::giveEmptyWorldPotions)))
                        .then(literal("effects")
                                .then(literal("mod").executes(DumbPotionsCommand::giveModEffects))
                                .then(literal("all").executes(DumbPotionsCommand::giveAllEffects))
                                .then(literal("emptyworld").executes(DumbPotionsCommand::giveEmptyWorldEffects)))
                        .then(literal("shulkerbox").executes(DumbPotionsCommand::givePotionsShulkerBox))
        );
    }

    public static int giveModEffects(CommandContext<CommandSourceStack> ctx) {
        var player = Objects.requireNonNull(ctx.getSource().getPlayer());

        for (Potion potion : ModPotions.ALL) {
            player.addEffect(potion.getEffects().get(0));
        }

        return 0;
    }

    public static int giveModPotions(CommandContext<CommandSourceStack> ctx) {
        var player = Objects.requireNonNull(ctx.getSource().getPlayer());

        for (Potion potion : ModPotions.ALL) {
            player.addItem(PotionUtils.setPotion(new ItemStack(Items.POTION, 1), potion));
        }

        return 0;
    }

    public static int giveAllEffects(CommandContext<CommandSourceStack> ctx) {
        if (ctx.getSource().getLevel().isClientSide) return 1;

        var player = Objects.requireNonNull(ctx.getSource().getPlayer());
        Registry<Potion> potions = ctx.getSource().registryAccess().registryOrThrow(Registries.POTION);

        for (Potion potion : potions) {
            player.addEffect(potion.getEffects().get(0));
        }

        return 0;
    }

    public static int giveAllPotions(CommandContext<CommandSourceStack> ctx) {
        if (ctx.getSource().getLevel().isClientSide) return 1;

        var player = Objects.requireNonNull(ctx.getSource().getPlayer());
        Registry<Potion> potions = ctx.getSource().registryAccess().registryOrThrow(Registries.POTION);

        for (Potion potion : potions) {
            player.addItem(PotionUtils.setPotion(new ItemStack(Items.POTION, 1), potion));
        }

        return 0;
    }

    public static int giveEmptyWorldEffects(CommandContext<CommandSourceStack> ctx) {
        var player = Objects.requireNonNull(ctx.getSource().getPlayer());

        for (EmptyWorldPeople person : EmptyWorldPeople.values()) {
            player.addEffect(ModPotions.PLAYER_POTIONS.get(person.getUsername()).getEffects().get(0));
        }

        return 0;
    }

    public static int giveEmptyWorldPotions(CommandContext<CommandSourceStack> ctx) {
        var player = Objects.requireNonNull(ctx.getSource().getPlayer());

        for (EmptyWorldPeople person : EmptyWorldPeople.values()) {
            player.addItem(PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.PLAYER_POTIONS.get(person.getUsername())));
        }

        return 0;
    }

    public static int givePotionsShulkerBox(CommandContext<CommandSourceStack> ctx) {
        Objects.requireNonNull(ctx.getSource().getPlayer()).addItem(createShulkerBox());

        return 0;
    }

    public static int openSubscribeMenu(CommandContext<CommandSourceStack> ctx) {
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
    }

    public static int resetAll(CommandContext<CommandSourceStack> ctx) {
        var player = Objects.requireNonNull(ctx.getSource().getPlayer());
        var level = player.serverLevel();

        player.removeAllEffects();

        ScaleData data = ScaleData.Builder.create().type(ScaleTypes.BASE).entity(player).build();
        data.setTargetScale(1.0f);

        data = ScaleData.Builder.create().type(ScaleTypes.MOTION).entity(player).build();
        data.setTargetScale(1.0f);

        data = ScaleData.Builder.create().type(ScaleTypes.HEALTH).entity(player).build();
        data.setTargetScale(1.0f);

        data = ScaleData.Builder.create().type(ScaleTypes.ATTACK).entity(player).build();
        data.setTargetScale(1.0f);

        data = ScaleData.Builder.create().type(ScaleTypes.STEP_HEIGHT).entity(player).build();
        data.setTargetScale(1.0f);

        unequip(player);

        for (Entity ent : level.getAllEntities()) {
            if (ent instanceof InvisibleEntity) {
                ent.kill();
            }
        }

        var packet = new ClientboundStopSoundPacket(ModSounds.THEME_SONGS.getLocation(), SoundSource.PLAYERS);

        player.connection.send(packet);

        return 0;
    }

    private static ItemStack createShulkerBox() {
        var box = namedItem(new ItemStack(Items.PURPLE_SHULKER_BOX, 1),
                Component.literal("Dumb Potion Ideas").withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.BOLD));

        var meta = new ShulkerBoxBlockEntity(DyeColor.PURPLE, BlockPos.ZERO,
                Blocks.PURPLE_SHULKER_BOX.defaultBlockState());

        meta.setItem(0, namedItem(new ItemStack(Items.PAPER, 1),
                Component.literal("Level 1").withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD)));

        meta.setItem(2, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.SHRIEKING));
        meta.setItem(3, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.PLAYER_POTIONS.get("syszee"))); // try to keep it consistent with the original mod
        meta.setItem(4, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.SHAPESHIFTING));
        meta.setItem(5, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.THEME_SONG));
        meta.setItem(6, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.SUBSCRIBE));

        meta.setItem(9, namedItem(new ItemStack(Items.PAPER, 1),
                Component.literal("Level 2").withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD)));

        meta.setItem(11, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.CORRUPTION));
        meta.setItem(12, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.REVEALING));
        meta.setItem(13, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.DESPAWN));
        meta.setItem(14, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.FURIOUS_COCKTAIL));
        meta.setItem(15, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.XVII));

        meta.setItem(18, namedItem(new ItemStack(Items.PAPER, 1),
                Component.literal("Level 3").withStyle(ChatFormatting.RED, ChatFormatting.BOLD)));

        meta.setItem(21, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.LAUNCHING));
        meta.setItem(22, PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.TITAN));
        meta.setItem(23,
                PotionUtils.setPotion(new ItemStack(Items.POTION, 1), ModPotions.TWENTY_FIVE_PERCENT));

        meta.saveToItem(box);

        return box;
    }

    private static ItemStack namedItem(ItemStack item, Component name) {
        var nameTag = new CompoundTag();

        nameTag.putString("Name", Component.Serializer.toJson(name));
        item.addTagElement("display", nameTag);

        return item;
    }
}
