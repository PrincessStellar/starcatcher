package com.wdiscute.starcatcher.registry.sweetspotbehaviour;

import com.wdiscute.starcatcher.modifiers.minigamemodifiers.FrozenPointerWhileActiveModifier;

public class FreezeSweetSpotBehaviour extends NormalSweetSpotBehaviour
{
    @Override
    public void onHit()
    {
        super.onHit();
        instance.addUniqueModifier(new FrozenPointerWhileActiveModifier(40, 10));
    }
}
