package com.knarfy.dumbpotions.client.renderer;

import com.knarfy.dumbpotions.entity.InvisibleEntity;
import com.knarfy.dumbpotions.init.ModEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class InvisibleRenderer extends HumanoidMobRenderer<InvisibleEntity, HumanoidModel<InvisibleEntity>> {

    public InvisibleRenderer(Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5F);

        this.addLayer(new HumanoidArmorLayer<>(this,
                new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(InvisibleEntity entity) {
        return new ResourceLocation("dumbpotions:textures/entities/invisible.png");
    }

    @Override
    public boolean shouldRender(InvisibleEntity livingEntity, Frustum camera, double camX, double camY, double camZ) {
        Player player = Minecraft.getInstance().player;

        if (player == null) {
            return super.shouldRender(livingEntity, camera, camX, camY, camZ);
        }

        if (player.getEffect(ModEffects.REVEALING) != null) {
            return super.shouldRender(livingEntity, camera, camX, camY, camZ);
        }

        return false;
    }
}
