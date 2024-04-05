package com.knarfy.dumbpotions.potion;

import com.knarfy.dumbpotions.DumbPotions;
import com.knarfy.dumbpotions.util.TitleUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.jetbrains.annotations.NotNull;

public class DespawnEffect extends MobEffect {

    public DespawnEffect() {
        super(MobEffectCategory.HARMFUL, -39169);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect.dumbpotions.despawn";
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        if (!entity.level().isClientSide() && entity.getServer() != null) {
            entity.teleportTo(
                    entity.getServer()
                        .getLevel(ResourceKey.create(Registries.DIMENSION,
                                new ResourceLocation(DumbPotions.MOD_ID, "despawn_dimension"))),
                    0.0, 5.0, 0.0, null, 0.0f, 0.0f);
        }

        if (!entity.level().isClientSide() && entity.getServer() != null && entity instanceof ServerPlayer player) {
            TitleUtil.showTitle(player, Component.literal("Have Fun :)").withStyle(ChatFormatting.DARK_PURPLE),
                    ClientboundSetSubtitleTextPacket::new);
            TitleUtil.showTitle(player,
                    Component.literal("Despawned...").withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.BOLD),
                    ClientboundSetTitleTextPacket::new);
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        super.removeAttributeModifiers(entity, attributeMap, amplifier);

        if (!entity.level().isClientSide() && entity.getServer() != null) {
            // This makes all the items and XP despawn when the player dies.
            if (entity instanceof ServerPlayer player) {
                player.getInventory().clearContent();
                player.setExperienceLevels(0);
                player.setExperiencePoints(0);
            }

            entity.kill();
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

}
