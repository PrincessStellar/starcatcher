package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

public abstract class AbstractTimedModifier extends AbstractMinigameModifier
{
    int length;

    public AbstractTimedModifier(int length, String translationOverride)
    {
        super(translationOverride);
        this.length = length;
    }

    public int getLength()
    {
        return length;
    }

    @Override
    public void tick()
    {
        if (length > 0 && tickCount >= length)
        {
            removed = true;
        }
        super.tick();
    }
}
