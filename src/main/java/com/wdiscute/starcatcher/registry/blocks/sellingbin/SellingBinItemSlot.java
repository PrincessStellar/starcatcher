package com.wdiscute.starcatcher.registry.blocks.sellingbin;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;

public class SellingBinItemSlot extends Slot
{
    SellingBinMenu menu;
    boolean isServer;

    public SellingBinItemSlot(SellingBinMenu menu, Container container, int slot, int x, int y, boolean isServer)
    {
        super(container, slot, x, y);
        this.menu = menu;
        this.isServer = isServer;
    }
}
