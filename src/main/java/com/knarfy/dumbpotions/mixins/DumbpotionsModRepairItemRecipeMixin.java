package com.knarfy.dumbpotions.mixins;

import com.google.common.collect.Lists;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RepairItemRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

// ??? Why does this exist?? It does nothing!
@Mixin(RepairItemRecipe.class)
public abstract class DumbpotionsModRepairItemRecipeMixin {
    @Inject(
            method = "assemble*",
            at = @At("HEAD")
    )
    public void assemble(CraftingContainer craftingContainer, RegistryAccess registryAccess, CallbackInfoReturnable<ItemStack> cir) {
        ArrayList<ItemStack> list = Lists.newArrayList();

        for (int i = 0; i < craftingContainer.getContainerSize(); ++i) {
            ItemStack itemStack = craftingContainer.getItem(i);

            if (!itemStack.isEmpty()) {
                list.add(itemStack);
            }
        }
    }
}
