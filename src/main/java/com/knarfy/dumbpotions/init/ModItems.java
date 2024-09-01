package com.knarfy.dumbpotions.init;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.SpawnEggItem;

public class ModItems {
    public static Item INVISIBLE_SPAWN_EGG;

    public static void init() {
        INVISIBLE_SPAWN_EGG = Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation("dumbpotions", "invisible_spawn_egg"),
                new SpawnEggItem(ModEntities.INVISIBLE, -1, -6710887, new Properties()));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS)
                .register(content -> content.accept(INVISIBLE_SPAWN_EGG));
    }
}
