package com.knarfy.dumbpotions.init;

import com.knarfy.dumbpotions.potion.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import java.util.HashMap;

public class ModEffects {
    public static MobEffect CORRUPTION_EFFECT;
    public static MobEffect REVEALING;
    public static MobEffect TWENTY_FIVE_PERCENT_EFFECT;
    public static MobEffect FURIOUS_COCKTAIL_EFFECT;
    public static MobEffect SHRIEKING_EFFECT;
    public static MobEffect SHAPESHIFTING_EFFECT;
    public static MobEffect THEME_SONG_EFFECT;
    public static MobEffect XVII_EFFECT;
    public static MobEffect LAUNCHING_EFFECT;
    public static MobEffect SUBSCRIBE_EFFECT;
    public static MobEffect TITAN_EFFECT;
    public static MobEffect DESPAWN;

    public static HashMap<String, MobEffect> PLAYER_EFFECTS = new HashMap<>();

    public static void load() {
        CORRUPTION_EFFECT = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "corruption_effect"), new CorruptionEffect());

        REVEALING = Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "revealing_effect"),
                new RevealingEffect());

        TWENTY_FIVE_PERCENT_EFFECT = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "twenty_five_percent_effect"), new TwentyFivePercentEffect());

        FURIOUS_COCKTAIL_EFFECT = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "furious_cocktail_effect"), new FuriousCocktailEffect());

        SHRIEKING_EFFECT = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "shrieking_effect"), new ShriekingEffect());

        SHAPESHIFTING_EFFECT = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "shapeshifting_effect"), new ShapeshiftingEffect());

        THEME_SONG_EFFECT = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "theme_song_effect"), new ThemeSongEffect());

        XVII_EFFECT = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "xvii_effect"), new XVIIEffect());

        LAUNCHING_EFFECT = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "launching_effect"), new LaunchingEffect());

        SUBSCRIBE_EFFECT = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "subscribe_effect"), new SubscribeEffect());

        TITAN_EFFECT = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation("dumbpotions", "titan_effect"), new TitanEffect());

        DESPAWN = Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation("dumbpotions", "despawn_effect"),
                new DespawnEffect());

        PLAYER_EFFECTS = new HashMap<>();

        for (EmptyWorldPeople person : EmptyWorldPeople.values()) {
            PLAYER_EFFECTS.put(person.getUsername().toLowerCase(), Registry.register(BuiltInRegistries.MOB_EFFECT,
                    new ResourceLocation("dumbpotions", person.getUsername().toLowerCase() + "_potion_effect"), new PlayerEffect(person)));
        }
    }

}
