package com.wdiscute.starcatcher.registry.sweetspotbehaviour;

import com.wdiscute.starcatcher.Starcatcher;

public class GlowingSweetSpotBehaviour extends NormalSweetSpotBehaviour
{
    @Override
    public void onHit()
    {
        super.onHit();
        Object data = instance.modifierData.computeIfAbsent(Starcatcher.rl("deep_dark"), o -> 0);

        if (data instanceof Integer i)
            instance.modifierData.put(Starcatcher.rl("deep_dark"), Math.clamp(i - 10, 0, 255));
    }
}
