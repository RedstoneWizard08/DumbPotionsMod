package com.knarfy.dumbpotions.potion;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TwentyFivePercentEffect extends MobEffect {

    public TwentyFivePercentEffect() {
        super(MobEffectCategory.HARMFUL, -6684775);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect.dumbpotions.twenty_five_percent_effect";
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }

    @Override
    public void applyInstantenousEffect(Entity source, Entity indirectSource, LivingEntity entity, int amplifier,
            double health) {
        var world = entity.level();
        var x = entity.getX();
        var y = entity.getY();
        var z = entity.getZ();

        var goodEffects = new MobEffect[] { MobEffects.ABSORPTION, MobEffects.CONDUIT_POWER, MobEffects.DOLPHINS_GRACE,
                MobEffects.FIRE_RESISTANCE, MobEffects.GLOWING, MobEffects.DIG_SPEED, MobEffects.HEALTH_BOOST,
                MobEffects.HERO_OF_THE_VILLAGE, MobEffects.HEAL, MobEffects.JUMP, MobEffects.LUCK,
                MobEffects.NIGHT_VISION, MobEffects.REGENERATION, MobEffects.DAMAGE_RESISTANCE, MobEffects.SATURATION,
                MobEffects.MOVEMENT_SPEED, MobEffects.DAMAGE_BOOST, MobEffects.WATER_BREATHING, };

        var badEffects = new MobEffect[] { MobEffects.BAD_OMEN, MobEffects.BLINDNESS, MobEffects.DARKNESS,
                MobEffects.HUNGER, MobEffects.CONFUSION, MobEffects.DIG_SLOWDOWN, MobEffects.MOVEMENT_SLOWDOWN,
                MobEffects.WEAKNESS, MobEffects.UNLUCK, };

        var percent = Mth.nextInt(RandomSource.create(), 1, 4);

        var positions = List.of(new Vec3(x + 8, y, z), new Vec3(x - 8, y, z), new Vec3(x, y, z + 8),
                new Vec3(x, y, z - 8), new Vec3(x + 6, y, z + 6), new Vec3(x + 6, y, z - 6), new Vec3(x - 6, y, z + 6),
                new Vec3(x - 6, y, z - 6));

        if (percent == 1) {
            if (!world.isClientSide()) {
                for (var pos : positions) {
                    world.explode(null, pos.x, pos.y, pos.z, 4.0F, Level.ExplosionInteraction.TNT);
                }
            }
        }
        else if (percent == 2) {
            if (!entity.level().isClientSide()) {
                for (MobEffect effect : goodEffects) {
                    entity.addEffect(new MobEffectInstance(effect, 600, 10, false, false));
                }
            }
        }
        else if (percent == 3) {
            if (!entity.level().isClientSide()) {
                for (MobEffect effect : badEffects) {
                    entity.addEffect(new MobEffectInstance(effect, 600, 10, false, false));
                }
            }
        }
        else if (percent == 4) {
            entity.hurt(new DamageSource(entity.level()
                .registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(DamageTypes.GENERIC)) {
                public @NotNull Component getLocalizedDeathMessage(LivingEntity _msgEntity) {
                    return Component.translatable("death.attack.25%");
                }
            }, 100.0F);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

}
