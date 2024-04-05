package com.knarfy.dumbpotions.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.jetbrains.annotations.NotNull;

public class XVIIEffect extends MobEffect {

    public XVIIEffect() {
        super(MobEffectCategory.HARMFUL, -26368);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect.dumbpotions.xvii_effect";
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        var effects = new MobEffect[] { MobEffects.ABSORPTION, MobEffects.BAD_OMEN, MobEffects.BLINDNESS,
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
                // This is a 16 since it's a modifier. This effect is applied at level 17.
                entity.addEffect(new MobEffectInstance(effect, 340, 16));
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

}
