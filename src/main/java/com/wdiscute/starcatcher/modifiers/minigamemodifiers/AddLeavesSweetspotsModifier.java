package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.resources.ResourceLocation;

public class AddLeavesSweetspotsModifier extends AbstractMinigameModifier
{
    public float chancePerTick;

    public static final MapCodec<AddLeavesSweetspotsModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.FLOAT.optionalFieldOf("chance_per_tick", 0.025f).forGetter(o -> o.chancePerTick),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
                    ).apply(instance, AddLeavesSweetspotsModifier::new));

    public AddLeavesSweetspotsModifier(float chancePerTick, String overrideOverride)
    {
        super(overrideOverride);
        this.chancePerTick = chancePerTick;
    }

    @Override
    public void tick()
    {
        super.tick();

        if(U.r.nextFloat() < chancePerTick)
        {
            ActiveSweetSpot activeSweetSpot = new ActiveSweetSpot(instance, Difficulty.SweetSpot.LEAF);
            instance.addSweetSpot(activeSweetSpot);
        }
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("add_leaves_spots");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }
}
