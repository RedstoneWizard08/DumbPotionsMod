package com.knarfy.dumbpotions.screen;

import com.knarfy.dumbpotions.DumbPotions;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class SubscribeHandler extends AbstractContainerMenu {

    public SubscribeHandler(int syncId, Inventory inv) {
        this(syncId, inv, ContainerLevelAccess.NULL);
    }

    public SubscribeHandler(int syncId, Inventory _inv, ContainerLevelAccess _ctx) {
        super(DumbPotions.SUBSCRIBE_HANDLER_MENU_TYPE, syncId);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        return new ItemStack(Items.AIR, 0);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

}
