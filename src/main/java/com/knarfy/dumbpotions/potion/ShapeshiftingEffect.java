package com.knarfy.dumbpotions.potion;

import com.knarfy.dumbpotions.DumbPotions;
import com.knarfy.dumbpotions.util.EntityUtil;
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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShapeshiftingEffect extends MobEffect {

    public ShapeshiftingEffect() {
        super(MobEffectCategory.BENEFICIAL, -6750004);
    }

    public static void equip(ServerPlayer player, EntityType<?> entity) {
        DumbPotions.LOGGER.debug("Trying to set player {}'s identity to {}", player.getName(), BuiltInRegistries.ENTITY_TYPE.getKey(entity));

        Entity created = entity.create(player.level());

        if (created instanceof LivingEntity living) {
            var defaultType = IdentityType.from(living);

            if (defaultType != null) PlayerIdentity.updateIdentity(player, defaultType, living);
        }
    }

    public static void unequip(ServerPlayer player) {
        DumbPotions.LOGGER.debug("Removing {}'s identity", player.getName());
        PlayerIdentity.updateIdentity(player, null, null);
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
            world.playSound(null, BlockPos.containing(x, y, z),
                    BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.generic.explode")),
                    SoundSource.PLAYERS, 0.7F, 2.0F);
        } else {
            world.playLocalSound(x, y, z,
                    BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.generic.explode")),
                    SoundSource.PLAYERS, 0.7F, 2.0F, false);
        }

        List<EntityType<?>> types = EntityUtil.getEntityTypes(world);
        EntityType<?> value = types.get(Mth.nextInt(RandomSource.create(), 1, types.size()));

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
            world.playSound(null, BlockPos.containing(x, y, z),
                    BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.generic.explode")),
                    SoundSource.PLAYERS, 0.7F, 2.0F);
        } else {
            world.playLocalSound(x, y, z,
                    BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.generic.explode")),
                    SoundSource.PLAYERS, 0.7F, 2.0F, false);
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
