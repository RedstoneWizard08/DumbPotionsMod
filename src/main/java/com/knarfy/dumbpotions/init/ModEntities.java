package com.knarfy.dumbpotions.init;

import com.knarfy.dumbpotions.entity.InvisibleEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static EntityType<InvisibleEntity> INVISIBLE;

    public static void load() {
        INVISIBLE = Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                new ResourceLocation("dumbpotions", "invisible"),
                FabricEntityTypeBuilder.create(MobCategory.MONSTER, InvisibleEntity::new)
                        .dimensions(new EntityDimensions(0.6F, 1.8F, true))
                        .fireImmune()
                        .trackRangeBlocks(128)
                        .forceTrackedVelocityUpdates(true)
                        .trackedUpdateRate(3)
                        .build()
        );

        InvisibleEntity.init();
        FabricDefaultAttributeRegistry.register(INVISIBLE, InvisibleEntity.createAttributes());
    }
}
