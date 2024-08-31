package com.knarfy.dumbpotions.init;

import com.knarfy.dumbpotions.client.renderer.InvisibleRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class ModEntityRenderers {
    public static void init() {
        EntityRendererRegistry.register(ModEntities.INVISIBLE, InvisibleRenderer::new);
    }
}
