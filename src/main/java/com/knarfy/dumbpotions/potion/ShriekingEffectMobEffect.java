package com.knarfy.dumbpotions.potion;

import com.knarfy.dumbpotions.init.ModGameRules;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.NotNull;

public class ShriekingEffectMobEffect extends MobEffect {
    public ShriekingEffectMobEffect() {
        super(MobEffectCategory.HARMFUL, -16737895);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect.dumbpotions.shrieking_effect";
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        var world = entity.level();
        var x = entity.getX();
        var y = entity.getY();
        var z = entity.getZ();

        if (!world.isClientSide()) {
            world.playSound(
                    null,
                    BlockPos.containing(x, y, z),
                    BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.sculk_shrieker.shriek")),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );
        } else {
            world.playLocalSound(
                    x,
                    y,
                    z,
                    BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("block.sculk_shrieker.shriek")),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F,
                    false
            );
        }

        if (!entity.level().isClientSide()) {
            entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 240, 1));
        }

        if (world.getLevelData().getGameRules().getInt(ModGameRules.SHRIEK_COUNTER) == 0) {
            world.getLevelData().getGameRules().getRule(ModGameRules.SHRIEK_COUNTER).set(1, world.getServer());
        } else if (world.getLevelData().getGameRules().getInt(ModGameRules.SHRIEK_COUNTER) == 1) {
            world.getLevelData().getGameRules().getRule(ModGameRules.SHRIEK_COUNTER).set(2, world.getServer());
        } else if (world.getLevelData().getGameRules().getInt(ModGameRules.SHRIEK_COUNTER) >= 2) {
            world.getLevelData().getGameRules().getRule(ModGameRules.SHRIEK_COUNTER).set(0, world.getServer());

            (new Object() {
                private int ticks = 0;

                public void startDelay(LevelAccessor world) {
                    ServerTickEvents.END_SERVER_TICK
                            .register(
                                    server -> {
                                        ++this.ticks;
                                        if (this.ticks == 60) {
                                            if (world instanceof ServerLevel level) {
                                                var warden = new Warden(EntityType.WARDEN, level);
                                                var pos = entity.position();

                                                warden.getBrain().clearMemories();
                                                warden.getBrain().setMemoryWithExpiry(MemoryModuleType.DIG_COOLDOWN, Unit.INSTANCE, 1200L);
                                                warden.getBrain().setMemoryWithExpiry(MemoryModuleType.IS_EMERGING, Unit.INSTANCE, 64L);

                                                warden.moveTo(pos.x, pos.y, pos.z, 0.0F, 0.0F);
                                                warden.setYBodyRot(0.0F);
                                                warden.setYHeadRot(0.0F);
                                                warden.setDeltaMovement(0.0, 0.0, 0.0);
                                                warden.lookAt(entity, 100, 100);
                                                warden.finalizeSpawn(level, level.getCurrentDifficultyAt(warden.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);

                                                level.addFreshEntity(warden);
                                            }
                                        }
                                    }
                            );
                }
            })
                    .startDelay(world);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
