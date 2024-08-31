package com.knarfy.dumbpotions.client.gui;

import com.knarfy.dumbpotions.DumbPotions;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.core.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;

import java.io.IOException;

@Environment(EnvType.CLIENT)
public class MessageComponent {
    public static int AVATAR_SIZE = 20;

    public HorizontalAlignment align;
    public AvatarComponent player;
    public String text;

    public MessageComponent(String player, String text, HorizontalAlignment align) {
        try {
            this.player = new AvatarComponent(
                    player,
                    1,
                    1,
                    AVATAR_SIZE,
                    AVATAR_SIZE,
                    AVATAR_SIZE,
                    AVATAR_SIZE
            );
        } catch (IOException ex) {
            DumbPotions.LOGGER.error("Unable to create a player avatar: {}", ex.getMessage());
        }

        this.text = text;
        this.align = align;
    }

    public ParentComponent get() {
        return Containers.horizontalFlow(Sizing.content(), Sizing.content())
                .child(player.zIndex(2))
                .child(
                        Components.label(Component.literal(text))
                                .color(Color.WHITE)
                                .horizontalTextAlignment(HorizontalAlignment.LEFT)
                                .verticalTextAlignment(VerticalAlignment.CENTER)
                                .zIndex(2)
                                .margins(Insets.of(2))
                )
                .margins(Insets.of(4))
                .alignment(align, VerticalAlignment.TOP);
    }
}
