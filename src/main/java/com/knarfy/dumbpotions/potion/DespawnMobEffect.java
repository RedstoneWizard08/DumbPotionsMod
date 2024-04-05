package com.knarfy.dumbpotions.potion;

import com.knarfy.dumbpotions.DumbPotions;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class DespawnMobEffect extends MobEffect {
    public DespawnMobEffect() {
        super(MobEffectCategory.HARMFUL, -39169);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private static void showTitle(ServerPlayer target, Component title, Function<Component, Packet<?>> getter) {
        try {
            target.connection.send(getter.apply(updateForEntity(target, title, 0)));
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
    }

    public static MutableComponent updateForEntity(ServerPlayer player, Component component, int recursionDepth) throws CommandSyntaxException {
        CommandSourceStack stack = new CommandSourceStack(
                player,
                player.position(),
                player.getRotationVector(),
                player.serverLevel(),
                4,
                player.getName().getString(),
                player.getDisplayName(),
                player.server,
                player
        );

        if (recursionDepth > 100) {
            return component.copy();
        } else {
            MutableComponent mutableComponent = component.getContents().resolve(stack, player, recursionDepth + 1);

            for (Component component2 : component.getSiblings()) {
                mutableComponent.append(updateForEntity(player, component2, recursionDepth + 1));
            }

            return mutableComponent.withStyle(resolveStyle(player, component.getStyle(), recursionDepth));
        }
    }

    private static Style resolveStyle(ServerPlayer player, Style style, int recursionDepth) throws CommandSyntaxException {
        HoverEvent hoverEvent = style.getHoverEvent();

        if (hoverEvent != null) {
            Component component = hoverEvent.getValue(HoverEvent.Action.SHOW_TEXT);

            if (component != null) {
                HoverEvent hoverEvent2 = new HoverEvent(HoverEvent.Action.SHOW_TEXT, updateForEntity(player, component, recursionDepth + 1));
                return style.withHoverEvent(hoverEvent2);
            }
        }

        return style;
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect.dumbpotions.despawn";
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        if (!entity.level().isClientSide() && entity.getServer() != null) {
            entity.teleportTo(
                    entity.getServer().getLevel(
                            ResourceKey.create(
                                    Registries.DIMENSION,
                                    new ResourceLocation(DumbPotions.MOD_ID, "despawn_dimension")
                            )
                    ),
                    0.0,
                    5.0,
                    0.0,
                    null,
                    0.0f,
                    0.0f
            );
        }

        if (!entity.level().isClientSide() && entity.getServer() != null && entity instanceof ServerPlayer player) {
            showTitle(player, Component.literal("Have Fun :)").withStyle(ChatFormatting.DARK_PURPLE), ClientboundSetSubtitleTextPacket::new);
            showTitle(player, Component.literal("Despawned...").withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.BOLD), ClientboundSetTitleTextPacket::new);
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);

        if (!entity.level().isClientSide() && entity.getServer() != null) {
            // This makes all the items and XP despawn when the player dies.
            if (entity instanceof ServerPlayer player) {
                player.getInventory().clearContent();
                player.setExperienceLevels(0);
                player.setExperiencePoints(0);
            }

            entity.kill();
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
