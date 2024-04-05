package com.knarfy.dumbpotions.init;

import com.knarfy.dumbpotions.potion.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

public class ModEffects {
    public static MobEffect CORRUPTION_EFFECT;
    public static MobEffect REVEALING;
    public static MobEffect TWENTY_FIVE_PERCENT_EFFECT;
    public static MobEffect FURIOUS_COCTAIL_EFFECT;
    public static MobEffect SHRIEKING_EFFECT;
    public static MobEffect SHAPESHIFTING_EFFECT;
    public static MobEffect THEME_SONG_EFFECT;
    public static MobEffect XVII_EFFECT;
    public static MobEffect LAUNCHING_EFFECT;
    public static MobEffect SYSTEM_ZEE_POTION_EFFECT;
    public static MobEffect SUBSCRIBE_EFFECT;
    public static MobEffect TITAN_EFFECT;
    public static MobEffect DESPAWN;

    public static void load() {
        CORRUPTION_EFFECT = Registry.register(
                BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "corruption_effect"), new CorruptionEffectMobEffect()
        );
        REVEALING = Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "revealing"), new RevealingMobEffect());
        TWENTY_FIVE_PERCENT_EFFECT = Registry.register(
                BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "twenty_five_percent_effect"), new TwentyFivePercentEffectMobEffect()
        );
        FURIOUS_COCTAIL_EFFECT = Registry.register(
                BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "furious_coctail_effect"), new FuriousCoctailEffectMobEffect()
        );
        SHRIEKING_EFFECT = Registry.register(
                BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "shrieking_effect"), new ShriekingEffectMobEffect()
        );
        SHAPESHIFTING_EFFECT = Registry.register(
                BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "shapeshifting_effect"), new ShapeshiftingEffectMobEffect()
        );
        THEME_SONG_EFFECT = Registry.register(
                BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "theme_song_effect"), new ThemeSongEffectMobEffect()
        );
        XVII_EFFECT = Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "xvii_effect"), new XVIIEffectMobEffect());
        LAUNCHING_EFFECT = Registry.register(
                BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "launching_effect"), new LaunchingEffectMobEffect()
        );
        SYSTEM_ZEE_POTION_EFFECT = Registry.register(
                BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "system_zee_potion_effect"), new SystemZeePotionEffectMobEffect()
        );
        SUBSCRIBE_EFFECT = Registry.register(
                BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "subscribe_effect"), new SubscribeEffectMobEffect()
        );
        TITAN_EFFECT = Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "titan_effect"), new TitanEffectMobEffect());
        DESPAWN = Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "despawn"), new DespawnMobEffect());
    }
}
