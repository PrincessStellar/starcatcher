package com.wdiscute.starcatcher.registry.sweetspotbehaviour;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;

public class LeafSweetSpotBehaviour extends NormalSweetSpotBehaviour
{
    @Override
    public void onHit()
    {
        super.onHit();
        ass.removed = true;
        Minecraft.getInstance().player.playSound(SoundEvents.BIG_DRIPLEAF_BREAK, 1f, 1.6f);
    }
}
