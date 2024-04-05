package com.knarfy.dumbpotions.init;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules.BooleanValue;
import net.minecraft.world.level.GameRules.Category;
import net.minecraft.world.level.GameRules.IntegerValue;
import net.minecraft.world.level.GameRules.Key;

public class ModGameRules {

    public static Key<BooleanValue> SYSTEM_ZEE_TP;

    public static Key<IntegerValue> SHRIEK_COUNTER;

    public static void load() {
        SYSTEM_ZEE_TP = GameRuleRegistry.register("system_zee_tp", Category.PLAYER,
                GameRuleFactory.createBooleanRule(true));
        SHRIEK_COUNTER = GameRuleRegistry.register("shriek_counter", Category.SPAWNING,
                GameRuleFactory.createIntRule(0));
    }

}
