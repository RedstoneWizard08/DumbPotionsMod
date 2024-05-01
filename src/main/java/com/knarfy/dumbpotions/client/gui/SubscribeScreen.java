package com.knarfy.dumbpotions.client.gui;

import com.knarfy.dumbpotions.DumbPotions;
import com.knarfy.dumbpotions.screen.SubscribeHandler;
import io.wispforest.owo.ui.base.BaseOwoHandledScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class SubscribeScreen extends BaseOwoHandledScreen<FlowLayout, SubscribeHandler> {
    public SubscribeScreen(SubscribeHandler handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.surface(Surface.VANILLA_TRANSLUCENT)
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .verticalAlignment(VerticalAlignment.CENTER);

        rootComponent.child(Containers.verticalFlow(Sizing.fill(60), Sizing.fill(60))
                .child(Components.label(Component.literal("Chat Window")).margins(Insets.of(2)))
                .child(Components.box(Sizing.fill(100), Sizing.fixed(1)).color(Color.WHITE))
                .child(new MessageComponent("Knarfy", "Go subscribe!", HorizontalAlignment.LEFT).get())
                .child(Components.button(Component.literal("Subscribe"), this::onSub).margins(Insets.of(2)))
                .child(Components.button(Component.literal("Do not subscribe"), this::onNoSub).margins(Insets.of(2)))
                .padding(Insets.of(10))
                .surface(Surface.DARK_PANEL)
                .alignment(HorizontalAlignment.LEFT, VerticalAlignment.TOP));

        this.font.wordWrapHeight("Chat Window", 10000);
    }

    private void onSub(ButtonComponent _button) {
        assert Minecraft.getInstance().player != null;

        var parent = (FlowLayout) _button.parent();
        var player = Minecraft.getInstance().getUser().getName();

        assert parent != null;

        parent.child(new MessageComponent(player, "Okay, I'll subscribe! <3", HorizontalAlignment.LEFT).get());
        parent.child(new MessageComponent("Knarfy", "Thank you so much!", HorizontalAlignment.LEFT).get());

        Util.getPlatform().openUri("https://www.youtube.com/@KNARFY");

        parent.child(Components.label(Component.literal("Press [ESC] to close this menu."))
                .color(Color.WHITE)
                .horizontalTextAlignment(HorizontalAlignment.LEFT)
                .verticalTextAlignment(VerticalAlignment.CENTER)
                .zIndex(2)
                .margins(Insets.of(2)));
    }

    private void onNoSub(ButtonComponent _button) {
        assert Minecraft.getInstance().player != null;

        var parent = (FlowLayout) _button.parent();
        var player = Minecraft.getInstance().getUser().getName();

        assert parent != null;

        parent.child(new MessageComponent(player, "No, I'm okay, thanks.", HorizontalAlignment.LEFT).get());
        parent.child(new MessageComponent("Knarfy", "I'm sorry to hear that.", HorizontalAlignment.LEFT).get());

        parent.child(Components.label(Component.literal("Press [ESC] to close this menu."))
                .color(Color.WHITE)
                .horizontalTextAlignment(HorizontalAlignment.LEFT)
                .verticalTextAlignment(VerticalAlignment.CENTER)
                .zIndex(2)
                .margins(Insets.of(2)));
    }
}
