package com.wdiscute.starcatcher.compat.emi;

import com.mojang.blaze3d.platform.InputConstants;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.guide.FishingGuideScreen;
import com.wdiscute.starcatcher.io.FishCaughtCounter;
import com.wdiscute.starcatcher.registry.FishProperties;
import dev.emi.emi.api.EmiApi;
import dev.emi.emi.api.recipe.EmiRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class IsolatedEmiFPScreen extends Screen
{
    private static final ResourceLocation BACKGROUND = Starcatcher.rl("textures/gui/emi/emi_entry.png");

    private final FishProperties fp;
    private int uiX;
    private int uiY;
    private EmiRecipe emiRecipe;


    protected IsolatedEmiFPScreen(FishProperties fishProperties, EmiRecipe emiRecipe)
    {
        super(Component.empty());
        this.fp = fishProperties;
        this.emiRecipe = emiRecipe;
    }

    @Override
    protected void init()
    {
        super.init();
        uiX = (width - 200) / 2;
        uiY = (height - 200) / 2;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
        InputConstants.Key key = InputConstants.getKey(keyCode, scanCode);
        if (this.minecraft.options.keyInventory.isActiveAndMatches(key))
        {
            this.onClose();
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void onClose()
    {
        super.onClose();
        EmiApi.displayRecipe(emiRecipe);
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        guiGraphics.blit(BACKGROUND, uiX, uiY, 0, 0, 200, 200, 200, 200);


        FishingGuideScreen.renderFishEntryPage(
                guiGraphics,
                fp,
                new ItemStack(fp.catchInfo().fish().value()),
                FishCaughtCounter.get(Minecraft.getInstance().player, fp),
                uiX + 31,
                uiY - 25,
                mouseX,
                mouseY
        );
    }
}
