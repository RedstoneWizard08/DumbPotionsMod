package com.knarfy.dumbpotions.potion;

import carpet.patches.EntityPlayerMPFake;
import com.knarfy.dumbpotions.init.ModGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class SystemZeePotionEffectMobEffect extends MobEffect {
    public SystemZeePotionEffectMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -13382401);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect.dumbpotions.system_zee_potion_effect";
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        Level world = entity.level();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        if (world.getLevelData().getGameRules().getBoolean(ModGameRules.SYSTEM_ZEE_TP)) {
            if (!entity.level().isClientSide() && entity.getServer() != null) {
                if (Arrays.stream(entity.getServer().getPlayerNames()).map(String::toLowerCase).toList().contains("syszee")) {
                    var pos = entity.position();

                    Objects.requireNonNull(entity.getServer().getPlayerList().getPlayerByName("syszee")).teleportTo(pos.x, pos.y, pos.z);
                } else {
                    EntityPlayerMPFake.createFake("syszee", entity.getServer(), entity.position(), 0, 0, entity.level().dimension(), GameType.SURVIVAL, false);
                }
            }

            if (!world.isClientSide()) {
                world.playSound(
                        null,
                        BlockPos.containing(x, y, z),
                        BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.enderman.teleport")),
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                );
            } else {
                world.playLocalSound(
                        x,
                        y,
                        z,
                        BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.enderman.teleport")),
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F,
                        false
                );
            }

            if (world instanceof ServerLevel _level) {
                _level.sendParticles(ParticleTypes.PORTAL, x, y, z, 100, 0.5, 1.0, 0.5, 0.1);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
