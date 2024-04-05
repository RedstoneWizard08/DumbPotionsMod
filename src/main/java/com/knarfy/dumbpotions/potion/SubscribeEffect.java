package com.knarfy.dumbpotions.potion;

import com.knarfy.dumbpotions.init.ModSounds;
import com.knarfy.dumbpotions.util.TitleUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.jetbrains.annotations.NotNull;

public class SubscribeEffect extends MobEffect {

    public SubscribeEffect() {
        super(MobEffectCategory.BENEFICIAL, -3407872);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect.dumbpotions.subscribe_effect";
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        var world = entity.level();
        var x = entity.getX();
        var y = entity.getY();
        var z = entity.getZ();

        // TODO: GUI :D
        if (!entity.level().isClientSide() && entity.getServer() != null) {
            TitleUtil.showTitle((ServerPlayer) entity,
                    Component.literal("Subscribe!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD),
                    ClientboundSetTitleTextPacket::new);
        }

        if (!world.isClientSide()) {
            world.playSound(null, BlockPos.containing(x, y, z), ModSounds.YAY, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
        else {
            world.playLocalSound(x, y, z, ModSounds.YAY, SoundSource.PLAYERS, 1.0F, 1.0F, false);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

}
