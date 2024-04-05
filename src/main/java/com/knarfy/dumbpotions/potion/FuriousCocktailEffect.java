package com.knarfy.dumbpotions.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class FuriousCocktailEffect extends MobEffect {

    public FuriousCocktailEffect() {
        super(MobEffectCategory.BENEFICIAL, -10092493);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect.dumbpotions.furious_cocktail_effect";
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }

    @Override
    public void applyInstantenousEffect(Entity source, Entity indirectSource, LivingEntity entity, int amplifier,
            double health) {
        MobEffect[] effects = new MobEffect[] { MobEffects.ABSORPTION, MobEffects.BAD_OMEN, MobEffects.BLINDNESS,
                MobEffects.CONDUIT_POWER, MobEffects.DARKNESS, MobEffects.DOLPHINS_GRACE, MobEffects.FIRE_RESISTANCE,
                MobEffects.GLOWING, MobEffects.DIG_SPEED, MobEffects.HEALTH_BOOST, MobEffects.HERO_OF_THE_VILLAGE,
                MobEffects.HUNGER, MobEffects.HARM, MobEffects.HEAL, MobEffects.INVISIBILITY, MobEffects.JUMP,
                MobEffects.LEVITATION, MobEffects.LUCK, MobEffects.DIG_SLOWDOWN, MobEffects.CONFUSION,
                MobEffects.NIGHT_VISION, MobEffects.POISON, MobEffects.REGENERATION, MobEffects.DAMAGE_RESISTANCE,
                MobEffects.SATURATION, MobEffects.SLOW_FALLING, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.MOVEMENT_SPEED,
                MobEffects.DAMAGE_BOOST, MobEffects.UNLUCK, MobEffects.WATER_BREATHING, MobEffects.WEAKNESS,
                MobEffects.WITHER, };

        if (!entity.level().isClientSide()) {
            for (MobEffect effect : effects) {
                entity.addEffect(new MobEffectInstance(effect, 60, 1));
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

}
