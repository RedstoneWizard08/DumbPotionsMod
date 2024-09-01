package com.knarfy.dumbpotions.mixin;

import com.knarfy.dumbpotions.DumbPotions;
import com.knarfy.dumbpotions.init.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(ClientPacketListener.class)
public class PacketHandlerMixin {
    @Final
    @Shadow
    private Minecraft minecraft;

    /**
     * @author RedstoneWizard08
     * @reason Config enforcement 🚨
     */
    @SuppressWarnings("DataFlowIssue")
    @Overwrite
    public void handleSoundEvent(ClientboundSoundPacket packet) {
        PacketUtils.ensureRunningOnSameThread(packet, (ClientPacketListener) (Object) this, this.minecraft);
        Optional<ResourceKey<SoundEvent>> key = packet.getSound().unwrapKey();

        System.out.println("SoundEvent: " + key.get().location());

        if (key.isPresent() && key.get().location() == ModSounds.THEME_SONGS.getLocation() && DumbPotions.CONFIG.creatorMode()) {
            return;
        }

        this.minecraft.level.playSeededSound(this.minecraft.player, packet.getX(), packet.getY(), packet.getZ(), packet.getSound(), packet.getSource(), packet.getVolume(), packet.getPitch(), packet.getSeed());
    }
}
