package com.wdiscute.starcatcher.registry.blocks.sellingbin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class SellingBinScreen extends AbstractContainerScreen<SellingBinMenu>
{
    public SellingBinScreen(SellingBinMenu menu, Inventory playerInventory, Component title)
    {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1)
    {

    }
}
