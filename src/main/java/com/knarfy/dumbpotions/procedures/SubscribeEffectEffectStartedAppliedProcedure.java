package com.knarfy.dumbpotions.procedures;

import com.knarfy.dumbpotions.init.ModSounds;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class SubscribeEffectEffectStartedAppliedProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity != null) {
            // TODO: GUI :D
            if (!entity.level().isClientSide() && entity.getServer() != null) {
                entity.getServer()
                        .getCommands()
                        .performPrefixedCommand(
                                new CommandSourceStack(
                                        CommandSource.NULL,
                                        entity.position(),
                                        entity.getRotationVector(),
                                        entity.level() instanceof ServerLevel ? (ServerLevel) entity.level() : null,
                                        4,
                                        entity.getName().getString(),
                                        entity.getDisplayName(),
                                        entity.level().getServer(),
                                        entity
                                ),
                                "title @s title {\"text\":\"Subscribe!\",\"color\":\"red\",\"bold\":true}"
                        );
            }

            if (world instanceof Level _level) {
                if (!_level.isClientSide()) {
                    _level.playSound(null, BlockPos.containing(x, y, z), ModSounds.YAY, SoundSource.PLAYERS, 1.0F, 1.0F);
                } else {
                    _level.playLocalSound(x, y, z, ModSounds.YAY, SoundSource.PLAYERS, 1.0F, 1.0F, false);
                }
            }
        }
    }
}
