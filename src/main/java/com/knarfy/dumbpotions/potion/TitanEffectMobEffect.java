package com.knarfy.dumbpotions.potion;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

public class TitanEffectMobEffect extends MobEffect {
    public TitanEffectMobEffect() {
        super(MobEffectCategory.NEUTRAL, -10278615);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect.dumbpotions.titan_effect";
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        Level world = entity.level();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        if (!world.isClientSide()) {
            world.playSound(
                    null,
                    BlockPos.containing(x, y + 3.0, z),
                    BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.evoker.prepare_attack")),
                    SoundSource.PLAYERS,
                    2.0F,
                    1.0F
            );
        } else {
            world.playLocalSound(
                    x,
                    y + 3.0,
                    z,
                    BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.evoker.prepare_attack")),
                    SoundSource.PLAYERS,
                    2.0F,
                    1.0F,
                    false
            );
        }

        if (!entity.level().isClientSide() && entity.getServer() != null) {
            ScaleData data = ScaleData.Builder.create().type(ScaleTypes.BASE).entity(entity).build();

            data.setTargetScale(6.0f);
        }

        if (!entity.level().isClientSide() && entity.getServer() != null) {
            ScaleData data = ScaleData.Builder.create().type(ScaleTypes.MOTION).entity(entity).build();

            data.setTargetScale(0.2f);
        }

        if (!entity.level().isClientSide() && entity.getServer() != null) {
            ScaleData data = ScaleData.Builder.create().type(ScaleTypes.HEALTH).entity(entity).build();

            data.setTargetScale(4.0f);
        }

        if (!entity.level().isClientSide() && entity.getServer() != null) {
            ScaleData data = ScaleData.Builder.create().type(ScaleTypes.ATTACK).entity(entity).build();

            data.setTargetScale(30.0f);
        }

        if (!entity.level().isClientSide() && entity.getServer() != null) {
            ScaleData data = ScaleData.Builder.create().type(ScaleTypes.STEP_HEIGHT).entity(entity).build();

            data.setTargetScale(6.0f);
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);

        Level world = entity.level();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        if (!world.isClientSide()) {
            world.playSound(
                    null,
                    BlockPos.containing(x, y, z),
                    BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.evoker.prepare_summon")),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );
        } else {
            world.playLocalSound(
                    x,
                    y,
                    z,
                    BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.evoker.prepare_summon")),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F,
                    false
            );
        }

        if (!entity.level().isClientSide() && entity.getServer() != null) {
            ScaleData data = ScaleData.Builder.create().type(ScaleTypes.BASE).entity(entity).build();

            data.setTargetScale(1.0f);
        }

        if (!entity.level().isClientSide() && entity.getServer() != null) {
            ScaleData data = ScaleData.Builder.create().type(ScaleTypes.MOTION).entity(entity).build();

            data.setTargetScale(1.0f);
        }

        if (!entity.level().isClientSide() && entity.getServer() != null) {
            ScaleData data = ScaleData.Builder.create().type(ScaleTypes.HEALTH).entity(entity).build();

            data.setTargetScale(1.0f);
        }

        if (!entity.level().isClientSide() && entity.getServer() != null) {
            ScaleData data = ScaleData.Builder.create().type(ScaleTypes.ATTACK).entity(entity).build();

            data.setTargetScale(1.0f);
        }

        if (!entity.level().isClientSide() && entity.getServer() != null) {
            ScaleData data = ScaleData.Builder.create().type(ScaleTypes.STEP_HEIGHT).entity(entity).build();

            data.setTargetScale(1.0f);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
