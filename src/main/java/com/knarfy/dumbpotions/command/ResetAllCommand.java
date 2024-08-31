package com.knarfy.dumbpotions.command;

import com.knarfy.dumbpotions.entity.InvisibleEntity;
import com.knarfy.dumbpotions.init.ModSounds;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleTypes;

import java.util.Objects;

import static com.knarfy.dumbpotions.potion.ShapeshiftingEffect.unequip;

public class ResetAllCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("resetall").executes(ctx -> {
            var player = Objects.requireNonNull(ctx.getSource().getPlayer());
            var level = player.serverLevel();

            player.removeAllEffects();

            ScaleData data = ScaleData.Builder.create().type(ScaleTypes.BASE).entity(player).build();
            data.setTargetScale(1.0f);

            data = ScaleData.Builder.create().type(ScaleTypes.MOTION).entity(player).build();
            data.setTargetScale(1.0f);

            data = ScaleData.Builder.create().type(ScaleTypes.HEALTH).entity(player).build();
            data.setTargetScale(1.0f);

            data = ScaleData.Builder.create().type(ScaleTypes.ATTACK).entity(player).build();
            data.setTargetScale(1.0f);

            data = ScaleData.Builder.create().type(ScaleTypes.STEP_HEIGHT).entity(player).build();
            data.setTargetScale(1.0f);

            unequip(player);

            for (Entity ent : level.getAllEntities()) {
                if (ent instanceof InvisibleEntity) {
                    ent.kill();
                }
            }

            var packet = new ClientboundStopSoundPacket(ModSounds.THEME_SONGS.getLocation(), SoundSource.PLAYERS);

            player.connection.send(packet);

            return 0;
        }));
    }

}
