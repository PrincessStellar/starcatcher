package com.wdiscute.starcatcher.registry.blocks.sellingbin;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SellingBinScreen extends AbstractContainerScreen<SellingBinMenu>
{
    private static final ResourceLocation TEXTURE = Starcatcher.rl("textures/gui/selling_bin/selling_bin_background.png");

    private int uiX = 0;
    private int uiY = 0;

    private boolean mousePressed;

    @Override
    protected void init()
    {
        super.init();
        uiX = (this.width - this.imageWidth) / 2;
        uiY = (this.height - this.imageHeight) / 2;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button)
    {
        mousePressed = false;
        return super.mouseReleased(mouseX, mouseY, button);
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
            mousePressed = true;
            if (!menu.be.instaSell)
                if (menu.be.getItem(0).isEmpty())
                    Minecraft.getInstance().player.playSound(SoundEvents.DISPENSER_FAIL, 0.7f, U.r.nextFloat() / 8 + 1f);
                else
                    Minecraft.getInstance().player.playSound(SoundEvents.NOTE_BLOCK_BELL.value(), 0.7f, U.r.nextFloat() / 8 + 1f);

            if (Screen.hasShiftDown())
                //sell all
                Minecraft.getInstance().gameMode.handleInventoryButtonClick(this.menu.containerId, 68);
            else
                //sell
                Minecraft.getInstance().gameMode.handleInventoryButtonClick(this.menu.containerId, 67);
        }

        //toggle currency
        if (x > 126 && x < 137 && y > 40 && y < 51)
        {
            Minecraft.getInstance().player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 0.7f, 1f);
            Minecraft.getInstance().gameMode.handleInventoryButtonClick(this.menu.containerId, 70);
        }

        //toggle insta-sell
        if (x > 58 && x < 69 && y > 12 && y < 23)
        {
            Minecraft.getInstance().player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 0.7f, 1f);
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


        int progressAvailable = menu.be.getProgressAvailable();

        //arrow tooltip
        if (x > 79 && x < 96 && y > 37 && y < 54)
            guiGraphics.renderTooltip(this.font, Currency.getListOfCurrenciesFromValue(menu.be.currencies, progressAvailable), Optional.empty(), mouseX, mouseY);

        //render arrow
        //scales [0, SELLING_BIN_LOWEST_VALUE]   ->   [0, 16]
        Currency currency = menu.be.currencySelected;
        if(currency.isNone()) currency = menu.be.currencies.getFirst();
        int arrow = (int) ((((float) progressAvailable) / ((float) currency.value())) * 16);
        guiGraphics.blit(TEXTURE, uiX + 80, uiY + 37, 192, 16, Math.clamp(arrow, 0, 16), 16, 256, 256);

        //insta sell pressed
        if (menu.be.instaSell)
            guiGraphics.blit(TEXTURE, uiX + 80, uiY + 11, 192, 128, 42, 13, 256, 256);

        //sell button pressed down
        if (mousePressed)
            guiGraphics.blit(TEXTURE, uiX + 80, uiY + 11, 192, 128, 42, 13, 256, 256);

        //sell text
        U.renderString(guiGraphics, this.font, Component.translatable("gui.starcatcher.selling_bin.sell"), uiX + 84, uiY + 14, 0x87583a);

        //sell outline when hovering
        if (x > 80 && x < 121 && y > 12 && y < 23 && !menu.be.instaSell)
        {
            guiGraphics.blit(TEXTURE, uiX + 79, uiY + 10, 192, 80, 48, 16, 256, 256);
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

        //render currency selected
        ItemStack stack = new ItemStack(menu.be.currencySelected.item());
        if (stack.isEmpty())
            guiGraphics.blit(TEXTURE, uiX + 128, uiY + 42, 192, 144, 10, 10, 256, 256);
        else
            renderItem(stack, uiX + 124, uiY + 38, 0.5f);

        //currency selected tooltip
        if (x > 126 && x < 137 && y > 40 && y < 51)
        {
            List<Component> components = new ArrayList<>();
            components.add(Component.translatable("gui.starcatcher.selling_bin.currency_selected"));
            if (menu.be.currencySelected.isNone())
                components.add(Component.translatable("gui.starcatcher.selling_bin.highest"));
            else
            {
                MutableComponent mutableComponent = Component.empty();
                mutableComponent.append(menu.be.currencySelected.item().getDescription());
                if (Screen.hasShiftDown())
                    mutableComponent.append(" (" + menu.be.currencySelected.value() +")");

                components.add(mutableComponent);
            }
            guiGraphics.renderTooltip(this.font, components, Optional.empty(), mouseX, mouseY);
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

    private void renderItem(ItemStack stack, int x, int y, float scale)
    {

        Level level = Minecraft.getInstance().level;
        LivingEntity entity = Minecraft.getInstance().player;

        if (!stack.isEmpty())
        {
            BakedModel bakedmodel = this.minecraft.getItemRenderer().getModel(stack, level, entity, 234234);

            PoseStack pose = new PoseStack();

            pose.pushPose();
            pose.translate((float) (x + 8), (float) (y + 8), (float) (150));

            pose.scale(16F * scale, -16F * scale, 16F * scale);
            boolean usesBlockLight = !bakedmodel.usesBlockLight();
            if (usesBlockLight)
            {
                Lighting.setupForFlatItems();
            }

            this.minecraft.getItemRenderer().render(
                    stack, ItemDisplayContext.GUI, false, pose, Minecraft.getInstance().renderBuffers().bufferSource(),
                    15728880, OverlayTexture.NO_OVERLAY, bakedmodel);

            //flush()
            RenderSystem.disableDepthTest();
            Minecraft.getInstance().renderBuffers().bufferSource().endBatch();
            RenderSystem.enableDepthTest();

            if (usesBlockLight)
            {
                Lighting.setupFor3DItems();
            }

            pose.popPose();
        }
    }
}
