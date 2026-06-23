package com.wdiscute.starcatcher.registry.sweetspotbehaviour;

import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;

public class DeepOceanSweetSpotBehaviour extends NormalSweetSpotBehaviour
{
    @Override
    public void onMiss(FishingMinigameScreen instance, ActiveSweetSpot ass)
    {
        super.onMiss(instance, ass);
        ass.removed = true;
        Minecraft.getInstance().player.playSound(SoundEvents.FISHING_BOBBER_SPLASH, 0.1f, 1.6f);
    }
}
