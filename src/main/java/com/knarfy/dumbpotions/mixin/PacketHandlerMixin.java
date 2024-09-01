package com.knarfy.dumbpotions.mixin;

import com.knarfy.dumbpotions.DumbPotions;
import com.knarfy.dumbpotions.init.ModSounds;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(ClientPacketListener.class)
public class PacketHandlerMixin {
    @Inject(method = "handleSoundEvent", at = @At("HEAD"), cancellable = true)
    public void injectHandler(ClientboundSoundPacket packet, CallbackInfo ci) {
        Optional<ResourceKey<SoundEvent>> key = packet.getSound().unwrapKey();

        if (key.isPresent() && key.get().location().toString().equals(ModSounds.THEME_SONGS.getLocation().toString()) && DumbPotions.CONFIG.creatorMode()) {
            ci.cancel();
        }
    }
}
