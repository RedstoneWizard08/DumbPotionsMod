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

    public static SoundEvent BREAKING = SoundEvent.createVariableRangeEvent(new ResourceLocation("dumbpotions", "breaking"));

    public static void init() {
        Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation("dumbpotions", "themesongs"),
                THEME_SONGS);
        Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation("dumbpotions", "slidewhistleup"),
                SLIDE_WHISTLE_UP);
        Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation("dumbpotions", "yay"), YAY);
        Registry.register(BuiltInRegistries.SOUND_EVENT, new ResourceLocation("dumbpotions", "breaking"), BREAKING);
    }

}
