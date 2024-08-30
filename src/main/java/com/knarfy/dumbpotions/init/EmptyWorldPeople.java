package com.knarfy.dumbpotions.init;

import io.wispforest.owo.ui.core.Color;

public enum EmptyWorldPeople {
    CRAGDYNA("CragDyna", "CragDyna", 0x9C0D0D),
    GARRETTTHECARROT("GarrettTheCarrot", "GTCarrot", 0xFF9D48),
    FERITWOLF95("FeritWolf95", "FeritWolf95", 0xCA00F4),
    KNARFY("Knarfy", "Knarfy", 0x7523A9),
    CAPTONIUM("Captonium", "Captonium", 0x6AECC2),
    CLOUSER("Clouser", "C1OUS3R", 0xFB6D49),
    MONOCLEMC("MonocleMC", "MonocleMC", 0x132553),
    LEGUNDO("Legundo", "Legundo", 0x1B285D),
    ASHISREAL("AshIsReal", "AshIsReal", 0xF53274),
    NATUREOFGAMING("NatureOfGaming", "NatureOfGaming", 0x408D0C),
    THETEEBS("TheTeebs", "TheTeebs", 0x56B0C8),
    VIRTUOSITAS("Virtuositas", "Virtuositas", 0x333333),
    VILEAU("Vileau", "Vileau", 0xCD0C0D),
    AJIANAJA("AjianAja", "AjianAja", 0xFF4260),
    THEVISIONARYONE("TheVisionaryOne", "TheVisionaryOne", 0x4EBBFE),
    COOLMENT("Coolment", "Coolment", 0x76CBBB),
    SURFBOY("SurfBoy", "Surfboy", 0xED6E31),
    SYSTEMZEE("SystemZee", "syszee", 0xB33B3C);

    private final String name;
    private final String username;
    private final int color;

    EmptyWorldPeople(String name, String username, int color) {
        this.name = name;
        this.color = color;
        this.username = username;
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
}
