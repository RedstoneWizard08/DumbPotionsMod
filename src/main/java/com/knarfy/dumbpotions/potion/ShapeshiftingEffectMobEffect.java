package com.knarfy.dumbpotions.potion;

import draylar.identity.api.PlayerIdentity;
import draylar.identity.api.variant.IdentityType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShapeshiftingEffectMobEffect extends MobEffect {
    public ShapeshiftingEffectMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -6750004);
    }

    private static void equip(ServerPlayer player, IdentityType<?> entity) {
        Entity created = entity.create(player.level());

        assert !(created instanceof LivingEntity living) || PlayerIdentity.updateIdentity(player, entity, living);
    }

    private static void unequip(ServerPlayer player) {
        assert PlayerIdentity.updateIdentity(player, null, null);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect.dumbpotions.shapeshifting_effect";
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        var world = entity.level();
        var x = entity.getX();
        var y = entity.getY();
        var z = entity.getZ();

        if (world instanceof ServerLevel _level) {
            _level.sendParticles(ParticleTypes.CLOUD, x, y + 1.0, z, 100, 0.5, 0.5, 0.5, 0.1);
        }

        if (!world.isClientSide()) {
            world.playSound(
                    null,
                    BlockPos.containing(x, y, z),
                    BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.generic.explode")),
                    SoundSource.PLAYERS,
                    0.7F,
                    2.0F
            );
        } else {
            world.playLocalSound(
                    x,
                    y,
                    z,
                    BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.generic.explode")),
                    SoundSource.PLAYERS,
                    0.7F,
                    2.0F,
                    false
            );
        }

        List<IdentityType<?>> types = IdentityType.getAllTypes(world);
        IdentityType<?> value = types.get(Mth.nextInt(RandomSource.create(), 1, types.size()));

        if (!world.isClientSide() && value != null && entity instanceof ServerPlayer) {
            equip((ServerPlayer) entity, value);
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);

        var world = entity.level();
        var x = entity.getX();
        var y = entity.getY();
        var z = entity.getZ();

        if (world instanceof ServerLevel _level) {
            _level.sendParticles(ParticleTypes.CLOUD, x, y + 1.0, z, 100, 0.5, 0.5, 0.5, 0.1);
        }

        if (!world.isClientSide()) {
            world.playSound(
                    null,
                    BlockPos.containing(x, y, z),
                    BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.generic.explode")),
                    SoundSource.PLAYERS,
                    0.7F,
                    2.0F
            );
        } else {
            world.playLocalSound(
                    x,
                    y,
                    z,
                    BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.generic.explode")),
                    SoundSource.PLAYERS,
                    0.7F,
                    2.0F,
                    false
            );
        }

        if (!entity.level().isClientSide() && entity.getServer() != null && entity instanceof ServerPlayer) {
            unequip((ServerPlayer) entity);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
