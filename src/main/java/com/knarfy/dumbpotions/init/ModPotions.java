package com.knarfy.dumbpotions.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;

import java.util.ArrayList;
import java.util.HashMap;
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
    public static Potion SUBSCRIBE_POTION;
    public static Potion TITAN_POTION;
    public static Potion DESPAWN_POTION;

    public static List<Potion> ALL;
    public static HashMap<String, Potion> PLAYER_POTIONS = new HashMap<>();

    public static void load() {
        CORRUPTION_POTION = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "corruption_potion"),
                new Potion(new MobEffectInstance(ModEffects.CORRUPTION_EFFECT, 3600, 0, false, true)));

        REVEALING_POTION = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "revealing_potion"),
                new Potion(new MobEffectInstance(ModEffects.REVEALING, 3600, 0, false, true)));

        TWENTY_FIVE_PERCENT_POTION = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "twenty_five_percent_potion"),
                new Potion(new MobEffectInstance(ModEffects.TWENTY_FIVE_PERCENT_EFFECT, 3600, 0, false, true)));

        FURIOUS_COCKTAIL = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "furious_cocktail"),
                new Potion(new MobEffectInstance(ModEffects.FURIOUS_COCKTAIL_EFFECT, 3600, 0, false, true)));

        SHRIEKING = Registry.register(BuiltInRegistries.POTION, new ResourceLocation("dumbpotions", "shrieking"),
                new Potion(new MobEffectInstance(ModEffects.SHRIEKING_EFFECT, 240, 0, false, true)));

        SHAPESHIFTING_POTION = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "shapeshifting_potion"),
                new Potion(new MobEffectInstance(ModEffects.SHAPESHIFTING_EFFECT, 3600, 0, false, true)));

        THEME_SONG_POTION = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "theme_song_potion"),
                new Potion(new MobEffectInstance(ModEffects.THEME_SONG_EFFECT, 4800, 0, false, true)));

        XVII_POTION = Registry.register(BuiltInRegistries.POTION, new ResourceLocation("dumbpotions", "xvii_potion"),
                new Potion(new MobEffectInstance(ModEffects.XVII_EFFECT, 340, 0, false, true)));

        LAUNCHING_POTION = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "launching_potion"),
                new Potion(new MobEffectInstance(ModEffects.LAUNCHING_EFFECT, 100, 0, false, true)));

        SUBSCRIBE_POTION = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "subscribe_potion"),
                new Potion(new MobEffectInstance(ModEffects.SUBSCRIBE_EFFECT, 200, 0, false, true)));

        TITAN_POTION = Registry.register(BuiltInRegistries.POTION, new ResourceLocation("dumbpotions", "titan_potion"),
                new Potion(new MobEffectInstance(ModEffects.TITAN_EFFECT, 3600, 0, false, true)));

        DESPAWN_POTION = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "despawn_potion"),
                new Potion(new MobEffectInstance(ModEffects.DESPAWN, 600, 0, false, true)));

        ALL = new ArrayList<>(List.of(CORRUPTION_POTION, REVEALING_POTION, TWENTY_FIVE_PERCENT_POTION, FURIOUS_COCKTAIL, SHRIEKING,
                SHAPESHIFTING_POTION, THEME_SONG_POTION, XVII_POTION, LAUNCHING_POTION,
                SUBSCRIBE_POTION, TITAN_POTION, DESPAWN_POTION));

        for (EmptyWorldPeople person : EmptyWorldPeople.values()) {
            Potion item = Registry.register(BuiltInRegistries.POTION,
                    new ResourceLocation("dumbpotions", person.getUsername().toLowerCase() + "_potion"),
                    new Potion(new MobEffectInstance(ModEffects.PLAYER_EFFECTS.get(person.getUsername().toLowerCase()), 1200, 0, false, true)));

            ALL.add(item);
            PLAYER_POTIONS.put(person.getUsername(), item);
        }

        PotionBrewing.addMix(Potions.AWKWARD, Items.SCULK_CATALYST, SHRIEKING);
        PotionBrewing.addMix(Potions.AWKWARD, Items.MUTTON, SHAPESHIFTING_POTION);
        PotionBrewing.addMix(Potions.AWKWARD, Items.NOTE_BLOCK, THEME_SONG_POTION);
        PotionBrewing.addMix(Potions.AWKWARD, Items.IRON_INGOT, SUBSCRIBE_POTION);
        PotionBrewing.addMix(Potions.AWKWARD, Items.NETHER_STAR, CORRUPTION_POTION);
        PotionBrewing.addMix(Potions.AWKWARD, Items.ENDER_EYE, REVEALING_POTION);
        PotionBrewing.addMix(Potions.AWKWARD, Items.CHORUS_FRUIT, DESPAWN_POTION);
        PotionBrewing.addMix(Potions.AWKWARD, Items.SUSPICIOUS_STEW, FURIOUS_COCKTAIL);
        PotionBrewing.addMix(Potions.AWKWARD, Items.AXOLOTL_BUCKET, XVII_POTION);
        PotionBrewing.addMix(Potions.AWKWARD, Items.TNT, LAUNCHING_POTION);
        PotionBrewing.addMix(Potions.AWKWARD, Items.ROTTEN_FLESH, TITAN_POTION);
        PotionBrewing.addMix(Potions.AWKWARD, Items.DRAGON_HEAD, TWENTY_FIVE_PERCENT_POTION);
    }
}
