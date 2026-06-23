package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

public class BounceBackModifier extends AbstractMinigameModifier
{
    public static final MapCodec<BounceBackModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, BounceBackModifier::new));

    private int bounceBackTime = 0;
    private boolean bounced = false;

    public BounceBackModifier(String translationOverride)
    {
        super(translationOverride);
    }

    @Override
    public void onMiss(FishingMinigameScreen instance)
    {
        if (bounceBackTime > 0)
        {
            instance.progress += instance.penalty;
        }

        if (instance.progress <= instance.penalty && !bounced)
        {
            instance.progress += instance.penalty;
            Minecraft.getInstance().player.playSound(SoundEvents.SLIME_HURT);
            bounceBackTime = instance.hp / 5;
            bounced = true;
        }

    }

    @Override
    public void tick(FishingMinigameScreen instance)
    {
        //no idea wtf is going on here anymore
        super.tick(instance);

        if (bounced)
            bounceBackTime--;

        if (bounceBackTime > 0)
        {
            instance.progress += 1;
            if (instance.progressSmooth < 5) instance.progressSmooth = 5;
        }

        if (bounceBackTime < 0) removed = true;

        if (instance.progressSmooth < 2 && !bounced)
        {
            bounced = true;
            Minecraft.getInstance().player.playSound(SoundEvents.SLIME_HURT);
            bounceBackTime = instance.hp / 5;
        }
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("bounce_back");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }
}
