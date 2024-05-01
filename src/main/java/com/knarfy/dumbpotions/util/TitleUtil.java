package com.knarfy.dumbpotions.util;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Function;

public class TitleUtil {

    @SuppressWarnings("CallToPrintStackTrace")
    public static void showTitle(ServerPlayer target, Component title, Function<Component, Packet<?>> getter) {
        try {
            target.connection.send(getter.apply(updateForEntity(target, title, 0)));
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
    }

    public static MutableComponent updateForEntity(ServerPlayer player, Component component, int recursionDepth) throws CommandSyntaxException {
        CommandSourceStack stack = new CommandSourceStack(player, player.position(), player.getRotationVector(),
                player.serverLevel(), 4, player.getName().getString(), player.getDisplayName(), player.server, player);

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

    public static Style resolveStyle(ServerPlayer player, Style style, int recursionDepth) throws CommandSyntaxException {
        HoverEvent hoverEvent = style.getHoverEvent();

        if (hoverEvent != null) {
            Component component = hoverEvent.getValue(HoverEvent.Action.SHOW_TEXT);

            if (component != null) {
                HoverEvent hoverEvent2 = new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        updateForEntity(player, component, recursionDepth + 1));

                return style.withHoverEvent(hoverEvent2);
            }
        }

        return style;
    }

}
