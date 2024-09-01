package com.knarfy.dumbpotions.potion;

import com.knarfy.dumbpotions.DumbPotions;
import com.knarfy.dumbpotions.init.ModSounds;
import io.wispforest.owo.ui.core.Color;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.phys.Vec3;

public class BreakingEffect extends MobEffect {
    public BreakingEffect() {
        super(MobEffectCategory.NEUTRAL, Color.ofRgb(0xFF0000).argb());
    }

    @Override
    public void addAttributeModifiers(LivingEntity livingEntity, AttributeMap attributeMap, int amplifier) {
        var pos = livingEntity.position();

        var nx = pos.add(-1, 0, 0);
        var px = pos.add(1, 0, 0);
        var nz = pos.add(0, 0, -1);
        var pz = pos.add(0, 0, 1);

        var positions = new Vec3[]{nx, px, nz, pz, pos};

        if (livingEntity.level() instanceof ServerLevel level) {
            for (var position : positions) {
                var tnt = new PrimedTnt(EntityType.TNT, level);

                tnt.setPos(position.x(), position.y(), position.z());
                level.addFreshEntity(tnt);
            }

            level.playSound(null, BlockPos.containing(pos), ModSounds.BREAKING, SoundSource.PLAYERS, DumbPotions.CONFIG.loudBreakingPotion() ? 1.0f : 0.5f, 1.0f);
        }
    }
}
