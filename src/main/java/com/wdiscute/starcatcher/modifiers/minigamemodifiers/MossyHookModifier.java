package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.resources.ResourceLocation;

public class MossyHookModifier extends AbstractMinigameModifier
{
    public static final MapCodec<MossyHookModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, MossyHookModifier::new));

    public MossyHookModifier(String translationOverride)
    {
        super(translationOverride);
    }

    @Override
    public void tick()
    {
        super.tick();
        if(tickCount == 1 && instance.rarity != Rarity.LEGENDARY && instance.rarity != Rarity.EPIC)
        {
            instance.removeAllSweetSpots();
            instance.pointerBaseSpeed = 12;
            instance.pointerSpeed = 12;
            instance.penalty = 25;

            instance.addSweetSpot(new ActiveSweetSpot(instance, Difficulty.SweetSpot.THIN_STEADY_MOSSY));
            instance.addSweetSpot(new ActiveSweetSpot(instance, Difficulty.SweetSpot.AQUA));
        }
    }

    //award treasure if perfect catch and mossy hook
    @Override
    public boolean forceAwardTreasure()
    {
        return instance.perfectCatch && instance.rarity != Rarity.LEGENDARY && instance.rarity != Rarity.EPIC;
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("harder_with_treasure_on_perfect");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }
}


