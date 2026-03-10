package com.wdiscute.starcatcher.registry.blocks.sellingbin;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.custom.sellingbinprocessor.ModSellingBinProcessors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
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


        //sell / sell all
        if (x > 80 && x < 121 && y > 12 && y < 23)
        {
            if(!menu.be.getItem(0).isEmpty())
                Minecraft.getInstance().player.playSound(SoundEvents.NOTE_BLOCK_BELL.value(), 0.7f, U.r.nextFloat() / 8 + 1f);
            if (Screen.hasShiftDown())
                //sell all
                Minecraft.getInstance().gameMode.handleInventoryButtonClick(this.menu.containerId, 68);
            else
                //sell
                Minecraft.getInstance().gameMode.handleInventoryButtonClick(this.menu.containerId, 67);
        }

        //toggle insta-sell
        if (x > 58 && x < 69 && y > 12 && y < 23)
        {
            Minecraft.getInstance().gameMode.handleInventoryButtonClick(this.menu.containerId, 69);
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

        double x = mouseX - uiX;
        double y = mouseY - uiY;


        //arrow tooltip
        int progressAvailable = menu.be.getProgressAvailable();

        if (x > 79 && x < 96 && y > 37 && y < 54)
        {
            guiGraphics.renderTooltip(this.font, Component.literal(ModSellingBinProcessors.getStringFromValue(progressAvailable)), mouseX, mouseY);
        }

        //render arrow
        //scales [0, SELLING_BIN_LOWEST_VALUE]   ->   [0, 16]
        int arrow = (int) ((((float) progressAvailable) / ((float) menu.be.currencies.getFirst().getB())) * 16);
        guiGraphics.blit(TEXTURE, uiX + 80, uiY + 37, 192, 16, Math.clamp(arrow, 0, 16), 16, 256, 256);

        //sell / sell all text
        if (Screen.hasShiftDown())
            U.renderString(guiGraphics, this.font, Component.translatable("gui.starcatcher.selling_bin.sell_all"), uiX + 84, uiY + 14, 0x87583a);
        else
            U.renderString(guiGraphics, this.font, Component.translatable("gui.starcatcher.selling_bin.sell"), uiX + 84, uiY + 14, 0x87583a);

        //sell outline when hovering
        if (x > 80 && x < 121 && y > 12 && y < 23)
        {
            guiGraphics.blit(TEXTURE, uiX + 79, uiY + 10, 192, 80, 48, 16, 256, 256);
            if (Screen.hasShiftDown())
                U.renderFatString(guiGraphics, this.font, Component.translatable("gui.starcatcher.selling_bin.sell_all"), uiX + 84, uiY + 14, 0x87583a);
            else
                U.renderFatString(guiGraphics, this.font, Component.translatable("gui.starcatcher.selling_bin.sell"), uiX + 84, uiY + 14, 0x87583a);
        }

        //insta sell outline when hovering
        if (x > 58 && x < 69 && y > 12 && y < 23)
            guiGraphics.blit(TEXTURE, uiX + 56, uiY + 10, 192, 96, 16, 16, 256, 256);


        //insta sell checkmark
        if (menu.be.instaSell)
            guiGraphics.blit(TEXTURE, uiX + 56, uiY + 9, 208, 16, 16, 16, 256, 256);


        //auto sell tooltip
        if (x > 58 && x < 69 && y > 12 && y < 23)
        {
            if (menu.be.instaSell)
                guiGraphics.blit(TEXTURE, uiX + 55, uiY + 10, 192, 112, 18, 16, 256, 256);

            guiGraphics.renderTooltip(this.font, Component.translatable("gui.starcatcher.selling_bin.auto_sell"), mouseX, mouseY);
        }
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
