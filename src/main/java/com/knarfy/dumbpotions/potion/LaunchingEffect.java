package com.knarfy.dumbpotions.potion;

import com.knarfy.dumbpotions.init.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LaunchingEffect extends MobEffect {

    public LaunchingEffect() {
        super(MobEffectCategory.HARMFUL, -16759040);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect.dumbpotions.launching_effect";
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        Level world = entity.level();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        if (!world.isClientSide()) {
            world.playSound(null, BlockPos.containing(x, y, z), ModSounds.SLIDE_WHISTLE_UP, SoundSource.PLAYERS, 1.0F,
                    (float) Mth.nextDouble(RandomSource.create(), 0.7, 1.3));
        }
        else {
            world.playLocalSound(x, y, z, ModSounds.SLIDE_WHISTLE_UP, SoundSource.PLAYERS, 1.0F,
                    (float) Mth.nextDouble(RandomSource.create(), 0.7, 1.3), false);
        }

        if (!entity.level().isClientSide()) {
            entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 120, true, false));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

}
