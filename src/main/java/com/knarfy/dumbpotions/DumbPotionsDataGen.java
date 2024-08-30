package com.knarfy.dumbpotions;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import com.knarfy.dumbpotions.init.EmptyWorldPeople;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class DumbPotionsDataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator gen) {
        FabricDataGenerator.Pack pack = gen.createPack();

        pack.addProvider(TextureProvider::new);
        pack.addProvider(LangGenerator::new);
    }

    private static class LangGenerator extends FabricLanguageProvider {
        protected LangGenerator(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generateTranslations(TranslationBuilder b) {
            for (EmptyWorldPeople person : EmptyWorldPeople.values()) {
                b.add("item.minecraft.tipped_arrow.effect." + person.getUsername().toLowerCase() + "_potion", "Arrow of " + person.getName());
                b.add("item.minecraft.splash_potion.effect." + person.getUsername().toLowerCase() + "_potion", "Splash Potion of " + person.getName());
                b.add("item.minecraft.lingering_potion.effect." + person.getUsername().toLowerCase() + "_potion", "Lingering Potion of " + person.getName());
                b.add("item.minecraft.potion.effect." + person.getUsername().toLowerCase() + "_potion", "Potion of " + person.getName());
                b.add("effect.dumbpotions." + person.getUsername().toLowerCase() + "_potion_effect", person.getName() + " Effect");
                b.add("gamerule." + person.getUsername().toLowerCase() + "_tp.description", "Does the " + person.getName() + " Potion Teleport " + person.getName() + " to you?");
                b.add("gamerule." + person.getUsername().toLowerCase() + "_tp", person.getName() + " Teleport Potion Works");
            }

            HashMap<String, String> potions = getPotions();

            for (String potion : potions.keySet()) {
                b.add("item.minecraft.tipped_arrow.effect." + potion, "Arrow of " + potions.get(potion));
                b.add("item.minecraft.splash_potion.effect." + potion, "Splash Potion of " + potions.get(potion));
                b.add("item.minecraft.lingering_potion.effect." + potion, "Lingering Potion of " + potions.get(potion));
                b.add("item.minecraft.potion.effect." + potion, "Potion of " + potions.get(potion));
                b.add("effect.dumbpotions." + potion + "_effect", potions.get(potion) + " Effect");
            }

            b.add("subtitles.yay", "YAAAAAAYYYYYYY!!");
            b.add("subtitles.themesongs", "Theme Song Plays");
            b.add("subtitles.slidewhistleup", "Entity Launches");
            b.add("gamerule.shriek_counter", "Shriek Counter");
            b.add("gamerule.shriek_counter.description", "How many potions have been consumed (2+ spawns a warden)_");
            b.add("item.dumbpotions.invisible_spawn_egg", "Invisible Spawn Egg");
            b.add("death.attack.25%", "You found the 25% Chance of Death");
            b.add("text.kick.corruption", "Your game has been corrupted.");
        }

        private HashMap<String, String> getPotions() {
            HashMap<String, String> potions = new HashMap<>();

            potions.put("launching", "The Launch Thousands of Blocks Into the Air");
            potions.put("theme_song", "Theme Song");
            potions.put("titan", "Titan");
            potions.put("despawn", "Despawn");
            potions.put("corruption", "Corruption");
            potions.put("shrieking", "Shrieking");
            potions.put("xvii", "XVII");
            potions.put("revealing", "Revealing");
            potions.put("furious_cocktail", "Furious Cocktail");
            potions.put("subscribe", "Subscribe!");
            potions.put("shapeshifting", "Shapeshifting");
            potions.put("twenty_five_percent", "25%");

            return potions;
        }
    }

    @SuppressWarnings("NullableProblems")
    private static class TextureProvider implements DataProvider {
        private final FabricDataOutput packOutput;

        public TextureProvider(FabricDataOutput output) {
            this.packOutput = output;
        }

        @Override
        public CompletableFuture<?> run(CachedOutput output) {
            Path dir = packOutput.getOutputFolder(PackOutput.Target.RESOURCE_PACK);

            for (EmptyWorldPeople person : EmptyWorldPeople.values()) {
                try {
                    Path file = dir.resolve(DumbPotions.MOD_ID + "/textures/mob_effect/" + person.getUsername().toLowerCase() + "_potion_effect.png");
                    Path file2 = dir.resolve(DumbPotions.MOD_ID + "/textures/mob_effect/" + person.getUsername().toLowerCase() + "potionfx.png");
                    Path parent = file.getParent();
                    URL url = new URL("https://minotar.net/helm/" + person.getUsername() + "/600.png");

                    DumbPotions.LOGGER.info("Downloading icon for {}", person.getUsername());
                    InputStream stream = url.openStream();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(stream);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    HashingOutputStream hashingOutputStream = new HashingOutputStream(Hashing.sha1(), byteArrayOutputStream);

                    int read;

                    while ((read = bufferedInputStream.read()) != -1) {
                        hashingOutputStream.write(read);
                    }

                    bufferedInputStream.close();

                    byte[] arr = byteArrayOutputStream.toByteArray();
                    HashCode hash = hashingOutputStream.hash();

                    output.writeIfNeeded(file, arr, hash);
                    output.writeIfNeeded(file2, arr, hash);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return CompletableFuture.completedFuture(null);
        }

        @Override
        public String getName() {
            return "textures";
        }
    }
}
