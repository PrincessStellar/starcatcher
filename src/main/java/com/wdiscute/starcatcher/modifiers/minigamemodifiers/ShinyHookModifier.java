package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.resources.ResourceLocation;

public class ShinyHookModifier extends AbstractMinigameModifier
{
    public static final MapCodec<ShinyHookModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, ShinyHookModifier::new));

    private int hits = 0;

    protected ShinyHookModifier(String translationOverride)
    {
        super(translationOverride);
    }

    @Override
    public void onMiss()
    {
        super.onMiss();
        hits = 0;
    }

    @Override
    public boolean onHit(ActiveSweetSpot ass)
    {
        hits++;

        if(hits == 3 && !instance.treasureActive && instance.treasureProgress == 0)
        {
            instance.addSweetSpot(new ActiveSweetSpot(instance, Difficulty.SweetSpot.TREASURE));
            instance.addSweetSpot(new ActiveSweetSpot(instance, Difficulty.SweetSpot.TREASURE));
            removed = true;
        }

        return super.onHit(ass);
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("spawn_treasure_on_three_hits");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }
}
