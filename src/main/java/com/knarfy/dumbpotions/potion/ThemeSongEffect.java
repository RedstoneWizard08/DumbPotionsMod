package com.knarfy.dumbpotions.potion;

import com.knarfy.dumbpotions.init.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.jetbrains.annotations.NotNull;

public class ThemeSongEffect extends MobEffect {

    public ThemeSongEffect() {
        super(MobEffectCategory.BENEFICIAL, -154);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect.dumbpotions.theme_song_effect";
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        var world = entity.level();
        var x = entity.getX();
        var y = entity.getY();
        var z = entity.getZ();

        if (!world.isClientSide()) {
            world.playSound(null, BlockPos.containing(x, y, z), ModSounds.THEME_SONGS, SoundSource.PLAYERS, 0.8F, 1.0F);
        }
        else {
            world.playLocalSound(x, y, z, ModSounds.THEME_SONGS, SoundSource.PLAYERS, 0.8F, 1.0F, false);
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);

        if (entity.level() instanceof ServerLevel) {
            var player = (ServerPlayer) entity;
            var packet = new ClientboundStopSoundPacket(ModSounds.THEME_SONGS.getLocation(), SoundSource.PLAYERS);

            player.connection.send(packet);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

}
