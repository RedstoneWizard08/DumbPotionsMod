package com.knarfy.dumbpotions.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {

    public static SoundEvent THEME_SONGS = SoundEvent
        .createVariableRangeEvent(new ResourceLocation("dumbpotions", "themesongs"));

    public static SoundEvent SLIDE_WHISTLE_UP = SoundEvent
        .createVariableRangeEvent(new ResourceLocation("dumbpotions", "slidewhistleup"));

    public static SoundEvent YAY = SoundEvent.createVariableRangeEvent(new ResourceLocation("dumbpotions", "yay"));

    public static void load() {
        Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation("dumbpotions", "themesongs"),
                THEME_SONGS);
        Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation("dumbpotions", "slidewhistleup"),
                SLIDE_WHISTLE_UP);
        Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation("dumbpotions", "yay"), YAY);
    }

}
