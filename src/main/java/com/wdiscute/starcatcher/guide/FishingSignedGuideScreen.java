package com.wdiscute.starcatcher.guide;

import com.wdiscute.starcatcher.registry.SignedGuide;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class FishingSignedGuideScreen extends Screen
{
    SignedGuide signedGuide;

    protected FishingSignedGuideScreen(SignedGuide signedGuide)
    {
        super(Component.empty());
        this.signedGuide = signedGuide;
    }




}
