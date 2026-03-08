package com.wdiscute.starcatcher.registry.blocks.sellingbin;

import com.wdiscute.starcatcher.Starcatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class SellingBinScreen extends AbstractContainerScreen<SellingBinMenu>
{
    private static final ResourceLocation TEXTURE = Starcatcher.rl("textures/gui/selling_bin/selling_bin_background.png");

    private int uiX = 0;
    private int uiY = 0;

    @Override
    protected void init()
    {
        super.init();
        uiX = (this.width - this.imageWidth) / 2;
        uiY = (this.height - this.imageHeight) / 2;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {

        double x = mouseX - uiX;
        double y = mouseY - uiY;

        //System.out.println("clicked relative x: " + x);
        //System.out.println("clicked relative y: " + y);

        if(x > 69 && x < 106 && y > 32 && y < 45)
        {
            Minecraft.getInstance().gameMode.handleInventoryButtonClick(this.menu.containerId, 67);
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY)
    {
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY)
    {
        guiGraphics.blit(TEXTURE, uiX, uiY, 0, 0, this.imageWidth, this.imageHeight);
    }

    public SellingBinScreen(SellingBinMenu menu, Inventory playerInventory, Component title)
    {
        super(menu, playerInventory, title);
        ++this.imageHeight;
    }
}
