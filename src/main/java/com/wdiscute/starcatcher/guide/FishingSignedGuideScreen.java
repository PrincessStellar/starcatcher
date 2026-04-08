package com.wdiscute.starcatcher.guide;

import com.wdiscute.starcatcher.registry.SignedGuide;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class FishingSignedGuideScreen extends FishingGuideScreen
{
    SignedGuide signedGuide;

    public FishingSignedGuideScreen(SignedGuide signedGuide)
    {
        super();
        this.signedGuide = signedGuide;
        MAX_HELP_PAGES = 0;
    }

    @Override
    protected void init()
    {
        super.init();
        this.fishCaughtCounterMap = signedGuide.fishesCaught();
        menu = -1;
        editBox.setEditable(false);
        editBox.setValue(signedGuide.signature());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        editBox.setFocused(false);
    }

    @Override
    public void renderCoverText(GuiGraphics guiGraphics, int mouseX, int mouseY)
    {
    }
}
