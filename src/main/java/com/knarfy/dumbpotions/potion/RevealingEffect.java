package com.knarfy.dumbpotions.potion;

import com.knarfy.dumbpotions.entity.InvisibleEntity;
import com.knarfy.dumbpotions.init.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RevealingEffect extends MobEffect {

    public RevealingEffect() {
        super(MobEffectCategory.HARMFUL, -1);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect.dumbpotions.revealing";
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        var world = entity.level();
        var x = entity.getX();
        var y = entity.getY();
        var z = entity.getZ();

        if (!entity.level().isClientSide()) {
            entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 3200, 1, true, false));
        }

        var positions = List.of(new Vec3(x + 1, y, z), new Vec3(x, y, z + 1), new Vec3(x - 1, y, z),
                new Vec3(x, y, z - 1));

        if (world instanceof ServerLevel _level) {
            for (var pos : positions) {
                var invisible = new InvisibleEntity(ModEntities.INVISIBLE, _level);

                invisible.moveTo(pos.x, pos.y, pos.z, 0.0F, 0.0F);
                invisible.setYBodyRot(0.0F);
                invisible.setYHeadRot(0.0F);
                invisible.setDeltaMovement(0.0, 0.0, 0.0);
                invisible.lookAt(entity, 100, 100);
                invisible.finalizeSpawn(_level, _level.getCurrentDifficultyAt(invisible.blockPosition()),
                        MobSpawnType.MOB_SUMMONED, null, null);

                _level.addFreshEntity(invisible);
            }
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        if (!entity.level().isClientSide()) {
            entity.discard();

            if (entity.level() instanceof ServerLevel level) {
                for (Entity ent : level.getAllEntities()) {
                    if (ent instanceof InvisibleEntity) {
                        ent.kill();
                    }
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

}
