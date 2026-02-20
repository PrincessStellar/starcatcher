package com.wdiscute.starcatcher.registry.custom.sweetspotbehaviour;

import com.wdiscute.starcatcher.Config;
import com.wdiscute.starcatcher.registry.custom.minigamemodifiers.AbstractMinigameModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;

public class NormalSweetSpotBehaviour extends AbstractSweetSpotBehaviour
{
    @Override
    public void onHit()
    {
        super.onHit();
        if (instance.getModifiers().stream().noneMatch(AbstractMinigameModifier::skipHitParticles))
            instance.addParticles(ass.pos, 30, ass.particleColor);
        instance.progress += ass.reward;
        if (ass.isFlip) instance.currentRotation *= -1;
        ass.pos = instance.getRandomFreePosition(ass.thickness);
        if (Config.ENABLE_HIT_SOUND.get() && instance.getModifiers().stream().noneMatch(AbstractMinigameModifier::skipHitSound))
            Minecraft.getInstance().player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.3f, 1f);
        ass.alpha = 1;
    }
}
