package com.knarfy.dumbpotions.util;

import io.wispforest.owo.ui.core.Color;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public enum EmptyWorldPeople {
    CRAGDYNA("CragDyna", "CragDyna", 0x9C0D0D, Items.TNT),
    GARRETTTHECARROT("GarrettTheCarrot", "GTCarrot", 0xFF9D48, Items.CARROT),
    FERITWOLF95("FeritWolf95", "FeritWolf95", 0xCA00F4, Items.ENDER_PEARL),
    KNARFY("Knarfy", "Knarfy", 0x7523A9, Items.PURPLE_WOOL),
    CAMMAGANZA("Cammaganza", "Cammaganza", 0x874B3A, Items.BROWN_WOOL),
    CAPTONIUM("Captonium", "Captonium", 0x6AECC2, Items.EMERALD),
    CLOUSER("Clouser", "C1OUS3R", 0xFB6D49, Items.PUMPKIN),
    MONOCLEMC("MonocleMC", "MonocleMC", 0x132553, Items.GOLD_NUGGET),
    LEGUNDO("Legundo", "Legundo", 0x1B285D, Items.WITHER_SKELETON_SKULL),
    ASHISREAL("AshIsReal", "AshIsReal", 0xF53274, Items.COAL),
    NATUREOFGAMING("NatureOfGaming", "NatureOfGaming", 0x408D0C, Items.AZALEA_LEAVES),
    THETEEBS("TheTeebs", "TheTeebs", 0x56B0C8, Items.GRAY_WOOL),
    VIRTUOSITAS("Virtuositas", "Virtuositas", 0x333333, Items.REDSTONE_TORCH),
    VILEAU("Vileau", "Vileau", 0xCD0C0D, Items.END_CRYSTAL),
    AJIANAJA("AjianAja", "AjianAja", 0xFF4260, Items.CHERRY_SAPLING),
    THEVISIONARYONE("TheVisionaryOne", "TheVisionaryOne", 0x4EBBFE, Items.DIAMOND),
    COOLMENT("Coolment", "Coolment", 0x76CBBB, Items.CYAN_WOOL),
    MISTAZEL("Mistazel", "Mistazel", 0xFFD143, Items.CARVED_PUMPKIN),
    SURFBOY("SurfBoy", "Surfboy", 0xED6E31, Items.ORANGE_WOOL),
    SYSTEMZEE("SystemZee", "syszee", 0xB33B3C, Items.GOLDEN_APPLE),
    MRSSYSTEMZEE("MrsSystemZee", "MrsSystemZee", 0xC80629, Items.RED_WOOL),
    CRINGYGULL("CringyGull", "CringyGull", 0x52B4FF, Items.FEATHER),
    FARZY("Farzy", "Farzy", 0x00A7A0, Items.TOTEM_OF_UNDYING),
    JERMSYBOY("JermsyBoy", "JermsyBoy", 0xFAED61, Items.WOODEN_AXE);

    private final String name;
    private final String username;
    private final int color;
    private final Item ingredient;

    EmptyWorldPeople(String name, String username, int color, Item ingredient) {
        this.name = name;
        this.color = color;
        this.username = username;
        this.ingredient = ingredient;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public int getColor() {
        return Color.ofRgb(color).argb();
    }

    public Item getIngredient() {
        return ingredient;
    }
}
