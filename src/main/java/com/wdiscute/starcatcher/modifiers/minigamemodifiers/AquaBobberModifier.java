package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.resources.ResourceLocation;

public class AquaBobberModifier extends AbstractTimedModifier
{
    public static final MapCodec<AquaBobberModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.optionalFieldOf("length", -1).forGetter(AbstractTimedModifier::getLength),
                    Codec.INT.optionalFieldOf("amount", 1).forGetter(mod -> mod.numberOfSweetSpotsToAdd),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, AquaBobberModifier::new));

    int numberOfSweetSpotsToAdd;

    public AquaBobberModifier(int length, int numberOfSweetSpotsToAdd, String translationOverride)
    {
        super(length, translationOverride);
        this.numberOfSweetSpotsToAdd = numberOfSweetSpotsToAdd;
    }

    @Override
    public void onAdd(FishingMinigameScreen instance)
    {
        super.onAdd(instance);

        for (int i = 0; i < numberOfSweetSpotsToAdd; i++)
        {
            instance.addSweetSpot(new ActiveSweetSpot(instance, Difficulty.SweetSpot.AQUA));
        }
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("add_aqua_sweet_spot");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }
}
