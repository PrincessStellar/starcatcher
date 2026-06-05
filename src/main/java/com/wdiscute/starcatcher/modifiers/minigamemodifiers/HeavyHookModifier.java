package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.resources.ResourceLocation;

public class HeavyHookModifier extends AbstractTimedModifier
{
    float rate;

    public static final MapCodec<HeavyHookModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.optionalFieldOf("length", -1).forGetter(AbstractTimedModifier::getLength),
                    Codec.FLOAT.fieldOf("slowness").forGetter(mod -> mod.rate),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
                    ).apply(instance, HeavyHookModifier::new));


    public HeavyHookModifier(int length, float speedDividedByX, String translationOverride)
    {
        super(length, translationOverride);
        this.rate = speedDividedByX;
    }

    @Override
    public ActiveSweetSpot onSpotAdded(ActiveSweetSpot ass)
    {
        super.onSpotAdded(ass);
        ass.movingRate /= rate;
        return ass;
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("slower_moving_sweet_spots");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }
}
