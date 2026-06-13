package com.wdiscute.starcatcher.registry.sweetspotbehaviour;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;

public class CloudSweetSpotBehaviour extends NormalSweetSpotBehaviour
{

    @Override
    public void onHit()
    {
        float pos = ass.pos;
        super.onHit();
        ass.pos = pos;
        Minecraft.getInstance().player.playSound(SoundEvents.BREEZE_IDLE_AIR, 0.8f, 0.6f);
    }

    @Override
    public void onMiss()
    {
        super.onMiss();
        Minecraft.getInstance().player.playSound(SoundEvents.BREEZE_LAND, 0.4f, 1f);
    }
}
