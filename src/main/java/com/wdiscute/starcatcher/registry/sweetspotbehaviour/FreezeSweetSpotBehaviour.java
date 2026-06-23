package com.wdiscute.starcatcher.registry.sweetspotbehaviour;

import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.FrozenPointerWhileActiveModifier;

public class FreezeSweetSpotBehaviour extends NormalSweetSpotBehaviour
{
    @Override
    public void onHit(FishingMinigameScreen instance, ActiveSweetSpot ass)
    {
        super.onHit(instance, ass);
        instance.addUniqueModifier(new FrozenPointerWhileActiveModifier(40, 10));
    }
}
