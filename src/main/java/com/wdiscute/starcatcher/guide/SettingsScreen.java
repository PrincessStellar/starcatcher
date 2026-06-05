package com.wdiscute.starcatcher.guide;

import com.wdiscute.starcatcher.SCConfig;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.SizeAndWeight;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.AbstractMinigameModifier;
import com.wdiscute.starcatcher.fish.FishProperties;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class SettingsScreen extends FishingMinigameScreen
{
    public static final ResourceLocation SETTINGS = Starcatcher.rl("textures/gui/minigame/settings.png");
    public static final ResourceLocation GUI_SCALE = Starcatcher.rl("textures/gui/minigame/gui_scale.png");

    SizeAndWeight.Units unitSelected;

    public SettingsScreen(FishProperties fp, ItemStack rod)
    {
        super(fp, ItemStack.EMPTY, rod);
    }

    @Override
    protected void init()
    {
        super.init();

        hitDelay = (SCConfig.HIT_DELAY.get().floatValue());
        unitSelected = SCConfig.UNIT.get();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        super.render(guiGraphics, mouseX, mouseY, partialTick);



    }

    @Override
    public boolean isSettingsScreen()
    {
        return true;
    }

    @Override
    public void tick()
    {
        super.tick();
        if (progress > 100) progress = 100;
        if (progress < 0) progress = 0;
    }

    @Override
    public void onClose()
    {
        //round it to 2 decimal points
        SCConfig.HIT_DELAY.set(Math.round(hitDelay * 10) / 10d);
        SCConfig.HIT_DELAY.save();


        SCConfig.MINIGAME_RENDER_SCALE.set((double) renderScale);
        SCConfig.MINIGAME_RENDER_SCALE.save();

        SCConfig.MINIGAME_X_OFFSET.set(xOffset);
        SCConfig.MINIGAME_Y_OFFSET.set(yOffset);
        SCConfig.MINIGAME_X_OFFSET.save();
        SCConfig.MINIGAME_Y_OFFSET.save();


        SCConfig.UNIT.set(unitSelected);
        SCConfig.UNIT.save();

        modifiers.forEach(AbstractMinigameModifier::onRemove);

        this.minecraft.popGuiLayer();
    }
}
