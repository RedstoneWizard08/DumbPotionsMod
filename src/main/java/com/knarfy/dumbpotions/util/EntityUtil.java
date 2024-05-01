package com.knarfy.dumbpotions.util;

import draylar.identity.api.variant.IdentityType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class EntityUtil {
    public static List<EntityType<?>> getEntityTypes(Level level) {
        List<EntityType<?>> types = new ArrayList<>();

        for (EntityType<?> ty : BuiltInRegistries.ENTITY_TYPE) {
            var entity = ty.create(level);

            if (entity instanceof LivingEntity) {
                types.add(ty);
            }
        }

        return types;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List<IdentityType<?>> getIdentityTypes(Level level) {
        List<IdentityType<?>> types = new ArrayList<>();

        for (var type : getEntityTypes(level)) {
            types.add(new IdentityType(type));
        }

        return types;
    }
}
