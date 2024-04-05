package com.knarfy.dumbpotions.potion;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CorruptionEffect extends MobEffect {

    public CorruptionEffect() {
        super(MobEffectCategory.HARMFUL, -16776961);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect.dumbpotions.corruption_effect";
    }

    @Override
    public boolean isInstantenous() {
        return true;
    }

    @Override
    public void applyInstantenousEffect(Entity source, Entity indirectSource, LivingEntity entity, int amplifier,
            double health) {
        Level world = entity.level();

        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        if (entity instanceof Player) {
            if (!world.isClientSide()) {
                world.playSound(null, BlockPos.containing(x, y, z),
                        BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("item.shield.break")),
                        SoundSource.PLAYERS, 1.0F, 1.0F);
            }
            else {
                world.playLocalSound(x, y, z,
                        BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("item.shield.break")),
                        SoundSource.PLAYERS, 1.0F, 1.0F, false);
            }

            // TODO: Make some blocks not render properly (temporarily) while the effect
            // is active, then, after 10 seconds, kick.

            if (!entity.level().isClientSide() && entity.getServer() != null) {
                if (entity instanceof ServerPlayer player) {
                    player.connection.disconnect(Component.translatable("text.kick.corruption"));
                }
            }
        }
        else if (!entity.level().isClientSide()) {
            entity.discard();
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

}
