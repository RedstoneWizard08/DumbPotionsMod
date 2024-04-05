package com.knarfy.dumbpotions.potion;

import com.knarfy.dumbpotions.procedures.SubscribeEffectEffectStartedAppliedProcedure;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SubscribeEffectMobEffect extends MobEffect {
    public SubscribeEffectMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -3407872);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect.dumbpotions.subscribe_effect";
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        Level world = entity.level();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        SubscribeEffectEffectStartedAppliedProcedure.execute(world, x, y, z, entity);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
