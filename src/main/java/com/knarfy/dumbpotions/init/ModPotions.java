package com.knarfy.dumbpotions.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

import java.util.List;

public class ModPotions {
    public static Potion CORRUPTION_POTION;
    public static Potion REVEALING_POTION;
    public static Potion TWENTY_FIVE_PERCENT_POTION;
    public static Potion FURIOUS_COCKTAIL;
    public static Potion SHRIEKING;
    public static Potion SHAPESHIFTING_POTION;
    public static Potion THEME_SONG_POTION;
    public static Potion XVII_POTION;
    public static Potion LAUNCHING_POTION;
    public static Potion SYSTEM_ZEE_POTION;
    public static Potion SUBSCRIBE_POTION;
    public static Potion TITAN_POTION;
    public static Potion DESPAWN_POTION;

    public static List<Potion> ALL;

    public static void load() {
        CORRUPTION_POTION = Registry.register(
                BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "corruption_potion"),
                new Potion(new MobEffectInstance(ModEffects.CORRUPTION_EFFECT, 3600, 0, false, true))
        );

        REVEALING_POTION = Registry.register(
                BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "revealing_potion"),
                new Potion(new MobEffectInstance(ModEffects.REVEALING, 3600, 0, false, true))
        );

        TWENTY_FIVE_PERCENT_POTION = Registry.register(
                BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "twenty_five_percent_potion"),
                new Potion(new MobEffectInstance(ModEffects.TWENTY_FIVE_PERCENT_EFFECT, 3600, 0, false, true))
        );

        FURIOUS_COCKTAIL = Registry.register(
                BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "furious_cocktail"),
                new Potion(new MobEffectInstance(ModEffects.FURIOUS_COCTAIL_EFFECT, 3600, 0, false, true))
        );

        SHRIEKING = Registry.register(
                BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "shrieking"),
                new Potion(new MobEffectInstance(ModEffects.SHRIEKING_EFFECT, 240, 0, false, true))
        );

        SHAPESHIFTING_POTION = Registry.register(
                BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "shapeshifting_potion"),
                new Potion(new MobEffectInstance(ModEffects.SHAPESHIFTING_EFFECT, 3600, 0, false, true))
        );

        THEME_SONG_POTION = Registry.register(
                BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "theme_song_potion"),
                new Potion(new MobEffectInstance(ModEffects.THEME_SONG_EFFECT, 4800, 0, false, true))
        );

        XVII_POTION = Registry.register(
                BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "xvii_potion"),
                new Potion(new MobEffectInstance(ModEffects.XVII_EFFECT, 360, 0, false, true))
        );

        LAUNCHING_POTION = Registry.register(
                BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "launching_potion"),
                new Potion(new MobEffectInstance(ModEffects.LAUNCHING_EFFECT, 100, 0, false, true))
        );

        SYSTEM_ZEE_POTION = Registry.register(
                BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "system_zee_potion"),
                new Potion(new MobEffectInstance(ModEffects.SYSTEM_ZEE_POTION_EFFECT, 1200, 0, false, true))
        );

        SUBSCRIBE_POTION = Registry.register(
                BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "subscribe_potion"),
                new Potion(new MobEffectInstance(ModEffects.SUBSCRIBE_EFFECT, 200, 0, false, true))
        );

        TITAN_POTION = Registry.register(
                BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "titan_potion"),
                new Potion(new MobEffectInstance(ModEffects.TITAN_EFFECT, 3600, 0, false, true))
        );

        DESPAWN_POTION = Registry.register(
                BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "despawn_potion"),
                new Potion(new MobEffectInstance(ModEffects.DESPAWN, 600, 0, false, true))
        );

        ALL = List.of(
                CORRUPTION_POTION,
                REVEALING_POTION,
                TWENTY_FIVE_PERCENT_POTION,
                FURIOUS_COCKTAIL,
                SHRIEKING,
                SHAPESHIFTING_POTION,
                THEME_SONG_POTION,
                XVII_POTION,
                LAUNCHING_POTION,
                SYSTEM_ZEE_POTION,
                SUBSCRIBE_POTION,
                TITAN_POTION,
                DESPAWN_POTION
        );
    }
}
