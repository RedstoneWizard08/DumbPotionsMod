package com.knarfy.dumbpotions.init;

import com.knarfy.dumbpotions.util.EmptyWorldPeople;
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
    public static Potion CORRUPTION;
    public static Potion REVEALING;
    public static Potion TWENTY_FIVE_PERCENT;
    public static Potion FURIOUS_COCKTAIL;
    public static Potion SHRIEKING;
    public static Potion SHAPESHIFTING;
    public static Potion THEME_SONG;
    public static Potion XVII;
    public static Potion LAUNCHING;
    public static Potion SUBSCRIBE;
    public static Potion TITAN;
    public static Potion DESPAWN;
    public static Potion BREAKING;

    public static List<Potion> ALL;
    public static HashMap<String, Potion> PLAYER_POTIONS = new HashMap<>();

    public static void init() {
        CORRUPTION = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "corruption"),
                new Potion(new MobEffectInstance(ModEffects.CORRUPTION, 3600, 0, false, true)));

        REVEALING = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "revealing"),
                new Potion(new MobEffectInstance(ModEffects.REVEALING, 3600, 0, false, true)));

        TWENTY_FIVE_PERCENT = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "twenty_five_percent"),
                new Potion(new MobEffectInstance(ModEffects.TWENTY_FIVE_PERCENT, 3600, 0, false, true)));

        FURIOUS_COCKTAIL = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "furious_cocktail"),
                new Potion(new MobEffectInstance(ModEffects.FURIOUS_COCKTAIL, 3600, 0, false, true)));

        SHRIEKING = Registry.register(BuiltInRegistries.POTION, new ResourceLocation("dumbpotions", "shrieking"),
                new Potion(new MobEffectInstance(ModEffects.SHRIEKING, 240, 0, false, true)));

        SHAPESHIFTING = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "shapeshifting"),
                new Potion(new MobEffectInstance(ModEffects.SHAPESHIFTING, 3600, 0, false, true)));

        THEME_SONG = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "theme_song"),
                new Potion(new MobEffectInstance(ModEffects.THEME_SONG, 4800, 0, false, true)));

        XVII = Registry.register(BuiltInRegistries.POTION, new ResourceLocation("dumbpotions", "xvii"),
                new Potion(new MobEffectInstance(ModEffects.XVII, 340, 0, false, true)));

        LAUNCHING = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "launching"),
                new Potion(new MobEffectInstance(ModEffects.LAUNCHING, 100, 0, false, true)));

        SUBSCRIBE = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "subscribe"),
                new Potion(new MobEffectInstance(ModEffects.SUBSCRIBE, 200, 0, false, true)));

        TITAN = Registry.register(BuiltInRegistries.POTION, new ResourceLocation("dumbpotions", "titan"),
                new Potion(new MobEffectInstance(ModEffects.TITAN, 3600, 0, false, true)));

        DESPAWN = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "despawn"),
                new Potion(new MobEffectInstance(ModEffects.DESPAWN, 600, 0, false, true)));

        BREAKING = Registry.register(BuiltInRegistries.POTION,
                new ResourceLocation("dumbpotions", "breaking"),
                new Potion(new MobEffectInstance(ModEffects.BREAKING, 100, 0, false, true)));

        ALL = new ArrayList<>(List.of(CORRUPTION, REVEALING, TWENTY_FIVE_PERCENT, FURIOUS_COCKTAIL, SHRIEKING,
                SHAPESHIFTING, THEME_SONG, XVII, LAUNCHING,
                SUBSCRIBE, TITAN, DESPAWN, BREAKING));

        for (EmptyWorldPeople person : EmptyWorldPeople.values()) {
            Potion item = Registry.register(BuiltInRegistries.POTION,
                    new ResourceLocation("dumbpotions", person.getUsername().toLowerCase() + "_potion"),
                    new Potion(new MobEffectInstance(ModEffects.PLAYER_EFFECTS.get(person.getUsername().toLowerCase()), 1200, 0, false, true)));

            ALL.add(item);
            PLAYER_POTIONS.put(person.getUsername(), item);
        }

        PotionBrewing.addMix(Potions.AWKWARD, Items.SCULK_CATALYST, SHRIEKING);
        PotionBrewing.addMix(Potions.AWKWARD, Items.MUTTON, SHAPESHIFTING);
        PotionBrewing.addMix(Potions.AWKWARD, Items.NOTE_BLOCK, THEME_SONG);
        PotionBrewing.addMix(Potions.AWKWARD, Items.IRON_INGOT, SUBSCRIBE);
        PotionBrewing.addMix(Potions.AWKWARD, Items.NETHER_STAR, CORRUPTION);
        PotionBrewing.addMix(Potions.AWKWARD, Items.ENDER_EYE, REVEALING);
        PotionBrewing.addMix(Potions.AWKWARD, Items.CHORUS_FRUIT, DESPAWN);
        PotionBrewing.addMix(Potions.AWKWARD, Items.SUSPICIOUS_STEW, FURIOUS_COCKTAIL);
        PotionBrewing.addMix(Potions.AWKWARD, Items.AXOLOTL_BUCKET, XVII);
        PotionBrewing.addMix(Potions.AWKWARD, Items.LADDER, LAUNCHING);
        PotionBrewing.addMix(Potions.AWKWARD, Items.ROTTEN_FLESH, TITAN);
        PotionBrewing.addMix(Potions.AWKWARD, Items.DRAGON_HEAD, TWENTY_FIVE_PERCENT);
        PotionBrewing.addMix(Potions.AWKWARD, Items.DRAGON_BREATH, BREAKING);

        for (EmptyWorldPeople person : EmptyWorldPeople.values()) {
            PotionBrewing.addMix(Potions.AWKWARD, person.getIngredient(), PLAYER_POTIONS.get(person.getUsername()));
        }
    }
}
