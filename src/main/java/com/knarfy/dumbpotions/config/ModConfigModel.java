package com.knarfy.dumbpotions.config;

import com.knarfy.dumbpotions.DumbPotions;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.Nest;

@Modmenu(modId = DumbPotions.MOD_ID)
@Config(name = "dumbpotions", wrapperName = "ModConfig")
public class ModConfigModel {
    public boolean loudBreakingPotion = true;
    public boolean creatorMode = false;

    @Nest
    public TeleportConfigs teleportConfigs = new TeleportConfigs();

    public static class TeleportConfigs {
        public boolean cragdynaTp = true;
        public boolean gtcarrotTp = true;
        public boolean feritwolf95Tp = true;
        public boolean knarfyTp = true;
        public boolean cammaganzaTp = true;
        public boolean captoniumTp = true;
        public boolean c1ous3rTp = true;
        public boolean monoclemcTp = true;
        public boolean legundoTp = true;
        public boolean ashisrealTp = true;
        public boolean natureofgamingTp = true;
        public boolean theteebsTp = true;
        public boolean virtuositasTp = true;
        public boolean vileauTp = true;
        public boolean ajianajaTp = true;
        public boolean thevisionaryoneTp = true;
        public boolean coolmentTp = true;
        public boolean mistazelTp = true;
        public boolean surfboyTp = true;
        public boolean syszeeTp = true;
        public boolean mrssystemzeeTp = true;
        public boolean cringygullTp = true;
        public boolean farzyTp = true;
        public boolean jermsyboyTp = true;

        public static boolean getFor(String username) {
            String real = username.toLowerCase();

            return switch (real) {
                case "cragdyna" -> DumbPotions.CONFIG.teleportConfigs.cragdynaTp();
                case "gtcarrot" -> DumbPotions.CONFIG.teleportConfigs.gtcarrotTp();
                case "feritwolf95" -> DumbPotions.CONFIG.teleportConfigs.feritwolf95Tp();
                case "knarfy" -> DumbPotions.CONFIG.teleportConfigs.knarfyTp();
                case "cammaganza" -> DumbPotions.CONFIG.teleportConfigs.cammaganzaTp();
                case "captonium" -> DumbPotions.CONFIG.teleportConfigs.captoniumTp();
                case "c1ous3r" -> DumbPotions.CONFIG.teleportConfigs.c1ous3rTp();
                case "monoclemc" -> DumbPotions.CONFIG.teleportConfigs.monoclemcTp();
                case "legundo" -> DumbPotions.CONFIG.teleportConfigs.legundoTp();
                case "ashisreal" -> DumbPotions.CONFIG.teleportConfigs.ashisrealTp();
                case "natureofgaming" -> DumbPotions.CONFIG.teleportConfigs.natureofgamingTp();
                case "theteebs" -> DumbPotions.CONFIG.teleportConfigs.theteebsTp();
                case "virtuositas" -> DumbPotions.CONFIG.teleportConfigs.virtuositasTp();
                case "vileau" -> DumbPotions.CONFIG.teleportConfigs.vileauTp();
                case "ajianaja" -> DumbPotions.CONFIG.teleportConfigs.ajianajaTp();
                case "thevisionaryone" -> DumbPotions.CONFIG.teleportConfigs.thevisionaryoneTp();
                case "coolment" -> DumbPotions.CONFIG.teleportConfigs.coolmentTp();
                case "mistazel" -> DumbPotions.CONFIG.teleportConfigs.mistazelTp();
                case "surfboy" -> DumbPotions.CONFIG.teleportConfigs.surfboyTp();
                case "syszee" -> DumbPotions.CONFIG.teleportConfigs.syszeeTp();
                case "mrssystemzee" -> DumbPotions.CONFIG.teleportConfigs.mrssystemzeeTp();
                case "cringygull" -> DumbPotions.CONFIG.teleportConfigs.cringygullTp();
                case "farzy" -> DumbPotions.CONFIG.teleportConfigs.farzyTp();
                case "jermsyboy" -> DumbPotions.CONFIG.teleportConfigs.jermsyboyTp();
                default -> false;
            };
        }
    }
}
