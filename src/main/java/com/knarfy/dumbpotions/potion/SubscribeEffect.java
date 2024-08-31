package com.knarfy.dumbpotions.potion;

import com.knarfy.dumbpotions.init.ModSounds;
import com.knarfy.dumbpotions.screen.SubscribeHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
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

        if (!entity.level().isClientSide() && entity.getServer() != null) {
//            TitleUtil.showTitle((ServerPlayer) entity,
//                    Component.literal("Subscribe!").withStyle(ChatFormatting.RED, ChatFormatting.BOLD),
//                    ClientboundSetTitleTextPacket::new);

            ((ServerPlayer) entity).openMenu(new MenuProvider() {
                @Override
                public @NotNull Component getDisplayName() {
                    return Component.literal("Subscribe!");
                }

                @Override
                public @NotNull AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
                    return new SubscribeHandler(i, inventory,
                            ContainerLevelAccess.create(player.getCommandSenderWorld(), player.blockPosition()));
                }
            });
        }

        if (!world.isClientSide()) {
            world.playSound(null, BlockPos.containing(x, y, z), ModSounds.YAY, SoundSource.PLAYERS, 1.0F, 1.0F);
        } else {
            world.playLocalSound(x, y, z, ModSounds.YAY, SoundSource.PLAYERS, 1.0F, 1.0F, false);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

}
