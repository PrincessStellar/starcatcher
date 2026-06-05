package com.wdiscute.starcatcher.registry.sweetspotbehaviour;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;

public class DeepOceanSweetSpotBehaviour extends NormalSweetSpotBehaviour
{
    @Override
    public void onMiss()
    {
        super.onMiss();
        ass.removed = true;
        Minecraft.getInstance().player.playSound(SoundEvents.FISHING_BOBBER_SPLASH, 0.1f, 1.6f);
    }
}
