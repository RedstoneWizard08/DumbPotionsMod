package com.knarfy.dumbpotions.init;

import com.knarfy.dumbpotions.potion.*;
import com.knarfy.dumbpotions.util.EmptyWorldPeople;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import java.util.HashMap;

public class ModEffects {
    public static MobEffect CORRUPTION;
    public static MobEffect REVEALING;
    public static MobEffect TWENTY_FIVE_PERCENT;
    public static MobEffect FURIOUS_COCKTAIL;
    public static MobEffect SHRIEKING;
    public static MobEffect SHAPESHIFTING;
    public static MobEffect THEME_SONG;
    public static MobEffect XVII;
    public static MobEffect LAUNCHING;
    public static MobEffect SUBSCRIBE;
    public static MobEffect TITAN;
    public static MobEffect DESPAWN;
    public static MobEffect BREAKING;

    public static HashMap<String, MobEffect> PLAYER_EFFECTS = new HashMap<>();

    public static void init() {
        CORRUPTION = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "corruption_effect"), new CorruptionEffect());

        REVEALING = Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "revealing_effect"),
                new RevealingEffect());

        TWENTY_FIVE_PERCENT = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "twenty_five_percent_effect"), new TwentyFivePercentEffect());

        FURIOUS_COCKTAIL = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "furious_cocktail_effect"), new FuriousCocktailEffect());

        SHRIEKING = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "shrieking_effect"), new ShriekingEffect());

        SHAPESHIFTING = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "shapeshifting_effect"), new ShapeshiftingEffect());

        THEME_SONG = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "theme_song_effect"), new ThemeSongEffect());

        XVII = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "xvii_effect"), new XVIIEffect());

        LAUNCHING = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "launching_effect"), new LaunchingEffect());

        SUBSCRIBE = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "subscribe_effect"), new SubscribeEffect());

        TITAN = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "titan_effect"), new TitanEffect());

        DESPAWN = Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "despawn_effect"),
                new DespawnEffect());

        BREAKING = Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "breaking_effect"),
                new BreakingEffect());

        PLAYER_EFFECTS = new HashMap<>();

        for (EmptyWorldPeople person : EmptyWorldPeople.values()) {
            PLAYER_EFFECTS.put(person.getUsername().toLowerCase(), Registry.register(BuiltInRegistries.MOB_EFFECT,
                    new ResourceLocation("dumbpotions", person.getUsername().toLowerCase() + "_potion_effect"), new PlayerEffect(person)));
        }
    }
}
