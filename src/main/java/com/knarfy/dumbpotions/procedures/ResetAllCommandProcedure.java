package com.knarfy.dumbpotions.procedures;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

public class ResetAllCommandProcedure {
    public static void execute(Entity entity) {
        if (entity != null) {
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
                                "effect clear @s"
                        );
            }

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
                                "scale reset @s"
                        );
            }

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
                                "identity unequip @s"
                        );
            }

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
                                "kill @e[type=dumbpotions:invisible]"
                        );
            }

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
                                "stopsound @a player dumbpotions:themesongs"
                        );
            }
        }
    }
}
