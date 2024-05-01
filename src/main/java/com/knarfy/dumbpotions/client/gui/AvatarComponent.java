package com.knarfy.dumbpotions.client.gui;

import com.knarfy.dumbpotions.DumbPotions;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import io.wispforest.owo.ui.base.BaseComponent;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.parsing.UIModel;
import io.wispforest.owo.ui.parsing.UIParsing;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
@SuppressWarnings("UnusedReturnValue")
public class AvatarComponent extends BaseComponent {
    protected final ResourceLocation tex;
    protected final int u, v;
    protected final int regionWidth, regionHeight;
    protected final int textureWidth, textureHeight;

    protected final AnimatableProperty<PositionedRectangle> visibleArea;
    protected boolean blend = false;

    private static final Map<String, ResourceLocation> cachedImages = new HashMap<>();

    public static void preloadImages(Minecraft client) {
        DumbPotions.LOGGER.info("Preloading avatar images...");

        try {
            getImage("Knarfy");
            getImage(client.getUser().getName());
        } catch (IOException ex) {
            DumbPotions.LOGGER.error("Could not preload avatar images: " + ex.getMessage());
        }

        DumbPotions.LOGGER.info("Preloaded avatar images!");
    }

    public static ResourceLocation getImage(String player) throws IOException {
        if (cachedImages.containsKey(player)) return cachedImages.get(player);

        var img = PlayerAvatar.getBytes(PlayerAvatar.getUrl(player));

        assert img != null;

        var tex = new DynamicTexture(NativeImage.read(img));
        var id = tex.getId();
        var loc = Minecraft.getInstance().getTextureManager().register("__avatar_dynamic_tex_" + id, tex);

        cachedImages.put(player, loc);

        return loc;
    }

    protected AvatarComponent(String player, int u, int v, int regionWidth, int regionHeight, int textureWidth, int textureHeight) throws IOException {
        this.tex = getImage(player);
        this.u = u;
        this.v = v;
        this.regionWidth = regionWidth;
        this.regionHeight = regionHeight;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.visibleArea = AnimatableProperty.of(PositionedRectangle.of(0, 0, this.regionWidth, this.regionHeight));
    }

    @Override
    protected int determineHorizontalContentSize(Sizing sizing) {
        return this.regionWidth;
    }

    @Override
    protected int determineVerticalContentSize(Sizing sizing) {
        return this.regionHeight;
    }

    @Override
    public void update(float delta, int mouseX, int mouseY) {
        super.update(delta, mouseX, mouseY);
        this.visibleArea.update(delta);
    }

    @Override
    public void draw(OwoUIDrawContext context, int mouseX, int mouseY, float partialTicks, float delta) {
        RenderSystem.enableDepthTest();

        if (this.blend) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
        }

        var matrices = context.pose();

        matrices.pushPose();
        matrices.translate(x, y, 0);
        matrices.scale(this.width / (float) this.regionWidth, this.height / (float) this.regionHeight, 0);

        var visibleArea = this.visibleArea.get();

        int bottomEdge = Math.min(visibleArea.y() + visibleArea.height(), regionHeight);
        int rightEdge = Math.min(visibleArea.x() + visibleArea.width(), regionWidth);

        context.blit(this.tex,
                visibleArea.x(),
                visibleArea.y(),
                rightEdge - visibleArea.x(),
                bottomEdge - visibleArea.y(),
                this.u + visibleArea.x(),
                this.v + visibleArea.y(),
                rightEdge - visibleArea.x(),
                bottomEdge - visibleArea.y(),
                this.textureWidth, this.textureHeight
        );

        if (this.blend) {
            RenderSystem.disableBlend();
        }

        matrices.popPose();
    }

    public AvatarComponent visibleArea(PositionedRectangle visibleArea) {
        this.visibleArea.set(visibleArea);
        return this;
    }

    public AvatarComponent resetVisibleArea() {
        this.visibleArea(PositionedRectangle.of(0, 0, this.regionWidth, this.regionHeight));
        return this;
    }

    public AnimatableProperty<PositionedRectangle> visibleArea() {
        return this.visibleArea;
    }

    public AvatarComponent blend(boolean blend) {
        this.blend = blend;
        return this;
    }

    public boolean blend() {
        return this.blend;
    }

    @Override
    public void parseProperties(UIModel model, Element element, Map<String, Element> children) {
        super.parseProperties(model, element, children);

        UIParsing.apply(children, "blend", UIParsing::parseBool, this::blend);

        if (children.containsKey("visible-area")) {
            var areaChildren = UIParsing.childElements(children.get("visible-area"));

            int x = 0, y = 0, width = this.regionWidth, height = this.regionHeight;
            if (areaChildren.containsKey("x")) {
                x = UIParsing.parseSignedInt(areaChildren.get("x"));
            }

            if (areaChildren.containsKey("y")) {
                y = UIParsing.parseSignedInt(areaChildren.get("y"));
            }

            if (areaChildren.containsKey("width")) {
                width = UIParsing.parseSignedInt(areaChildren.get("width"));
            }

            if (areaChildren.containsKey("height")) {
                height = UIParsing.parseSignedInt(areaChildren.get("height"));
            }

            this.visibleArea(PositionedRectangle.of(x, y, width, height));
        }
    }

    public static AvatarComponent parse(Element element) throws IOException {
        UIParsing.expectAttributes(element, "player");

        var player = element.getAttributeNode("player").getValue();
        int u = 0, v = 0, regionWidth = 0, regionHeight = 0, textureWidth = 256, textureHeight = 256;

        if (element.hasAttribute("u")) {
            u = UIParsing.parseSignedInt(element.getAttributeNode("u"));
        }

        if (element.hasAttribute("v")) {
            v = UIParsing.parseSignedInt(element.getAttributeNode("v"));
        }

        if (element.hasAttribute("region-width")) {
            regionWidth = UIParsing.parseSignedInt(element.getAttributeNode("region-width"));
        }

        if (element.hasAttribute("region-height")) {
            regionHeight = UIParsing.parseSignedInt(element.getAttributeNode("region-height"));
        }

        if (element.hasAttribute("texture-width")) {
            textureWidth = UIParsing.parseSignedInt(element.getAttributeNode("texture-width"));
        }

        if (element.hasAttribute("texture-height")) {
            textureHeight = UIParsing.parseSignedInt(element.getAttributeNode("texture-height"));
        }

        return new AvatarComponent(player, u, v, regionWidth, regionHeight, textureWidth, textureHeight);
    }
}
