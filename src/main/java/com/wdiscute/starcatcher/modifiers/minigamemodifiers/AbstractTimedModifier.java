package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;

public abstract class AbstractTimedModifier extends AbstractInstancedMinigameModifier
{
    int length;

    public AbstractTimedModifier(int length)
    {
        this.length = length;
    }

    public int getLength()
    {
        return length;
    }

    @Override
    public void tick(FishingMinigameScreen instance)
    {
        if (length > 0 && instance.tickCount >= length)
        {
            removed = true;
        }
        super.tick(instance);
    }
}
