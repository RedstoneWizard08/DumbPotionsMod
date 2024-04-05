package com.knarfy.dumbpotions.gui;

import io.wispforest.owo.ui.base.BaseOwoHandledScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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

        rootComponent.child(Containers.verticalFlow(Sizing.fixed(600), Sizing.fixed(400))
            .child(Components.label(Component.literal("Chat Window")).positioning(Positioning.absolute(16, 13)))
            .child(Components.box(Sizing.fixed(568), Sizing.fixed(1))
                .color(Color.WHITE)
                .positioning(Positioning.absolute(16, 42)))
            .padding(Insets.of(10))
            .surface(Surface.DARK_PANEL)
            .alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER));

        this.font.wordWrapHeight("Chat Window", 10000);
    }

}
