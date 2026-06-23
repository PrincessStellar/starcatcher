package com.wdiscute.starcatcher.registry.sweetspotbehaviour;

import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;

public class TntSweetSpotBehaviour extends AbstractSweetSpotBehaviour
{
    @Override
    public void onAdd(FishingMinigameScreen instance, ActiveSweetSpot ass)
    {
        super.onAdd(instance, ass);
        ass.shouldSudokuOnVanish = true;
    }

    @Override
    public void tick(FishingMinigameScreen instance, ActiveSweetSpot ass)
    {
        super.tick(instance, ass);
        if(ass.ticksActive > 40) ass.removed = true;
    }

    @Override
    public void onHit(FishingMinigameScreen instance, ActiveSweetSpot ass)
    {
        super.onHit(instance, ass);
        ass.removed = true;
        instance.progress -= ass.reward;
        Minecraft.getInstance().player.playSound(SoundEvents.GENERIC_EXPLODE.value(), 0.2f, 1f);
    }
}
