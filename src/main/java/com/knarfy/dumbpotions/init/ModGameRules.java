package com.knarfy.dumbpotions.init;

import com.knarfy.dumbpotions.util.EmptyWorldPeople;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules.BooleanValue;
import net.minecraft.world.level.GameRules.Category;
import net.minecraft.world.level.GameRules.IntegerValue;
import net.minecraft.world.level.GameRules.Key;

import java.util.HashMap;

public class ModGameRules {
    public static Key<IntegerValue> SHRIEK_COUNTER;

    public static HashMap<String, Key<BooleanValue>> PLAYER_KEYS;

    public static void init() {
        SHRIEK_COUNTER = GameRuleRegistry.register("shriek_counter", Category.SPAWNING,
                GameRuleFactory.createIntRule(0));

        PLAYER_KEYS = new HashMap<>();

        for (EmptyWorldPeople person : EmptyWorldPeople.values()) {
            PLAYER_KEYS.put(person.getUsername().toLowerCase(), GameRuleRegistry.register(person.getUsername().toLowerCase() + "_tp", Category.PLAYER,
                    GameRuleFactory.createBooleanRule(true)));
        }
    }

}
