package com.knarfy.dumbpotions.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class InvisibleEntity extends Monster {

    public InvisibleEntity(EntityType<InvisibleEntity> type, Level world) {
        super(type, world);
        this.setMaxUpStep(0.6F);
        this.xpReward = 0;
        this.setNoAi(false);
    }

    public static Builder createAttributes() {
        Builder builder = Mob.createMobAttributes();

        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.4);
        builder = builder.add(Attributes.MAX_HEALTH, 20.0);
        builder = builder.add(Attributes.ARMOR, 0.0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 10.0);
        builder = builder.add(Attributes.FOLLOW_RANGE, 32.0);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 2.0);

        return builder.add(Attributes.ATTACK_KNOCKBACK, 1.0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.6, false) {
            protected double getAttackReachSqr(LivingEntity entity) {
                return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
            }
        });

        this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new FloatGoal(this));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, ServerPlayer.class, false, false));
    }

    @Override
    public @NotNull MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public double getMyRidingOffset() {
        return -0.35;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.phantom.ambient"));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.phantom.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.phantom.death"));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getDirectEntity() instanceof ThrownPotion || source.getDirectEntity() instanceof AreaEffectCloud) {
            return false;
        } else if (source.is(DamageTypes.FALL)) {
            return false;
        } else if (source.is(DamageTypes.CACTUS)) {
            return false;
        } else if (source.is(DamageTypes.DROWN)) {
            return false;
        } else {
            return !source.is(DamageTypes.LIGHTNING_BOLT) && super.hurt(source, amount);
        }
    }

    @Override
    public void awardKillScore(Entity entity, int score, DamageSource damageSource) {
        super.awardKillScore(entity, score, damageSource);

        if (!this.level().isClientSide()) {
            this.discard();

            if (this.level() instanceof ServerLevel level) {
                for (Entity ent : level.getAllEntities()) {
                    if (ent instanceof InvisibleEntity) {
                        ent.kill();
                    }
                }
            }
        }
    }

    @Override
    public void baseTick() {
        super.baseTick();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Level world = this.level();

        Entity var10 = world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 3.0, 3.0, 3.0), e -> true)
                .stream()
                .min((new Object() {
                    Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                        return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
                    }
                }).compareDistOf(x, y, z))
                .orElse(null);
        if (var10 instanceof LivingEntity _ent) {
            setTarget(_ent);
        }
    }

}
