package com.knarfy.dumbpotions.gui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.data.Insets;

public class SubscribeGui extends LightweightGuiDescription {
    public SubscribeGui() {
        var root = new WGridPanel();

        setRootPanel(root);

        root.setSize(600, 400);
        root.setInsets(Insets.ROOT_PANEL);
        root.setBackgroundPainter(BackgroundPainter.createColorful(0x591390));

        root.validate(this);
    }
}
