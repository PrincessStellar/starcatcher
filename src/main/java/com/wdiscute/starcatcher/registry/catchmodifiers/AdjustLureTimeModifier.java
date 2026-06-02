package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

public class AdjustLureTimeModifier extends AbstractCatchModifier
{
    final float minTicks;
    final float maxTicks;
    final float randomness;

    public static final MapCodec<AdjustLureTimeModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.FLOAT.fieldOf("min_ticks_multiplier").forGetter(o -> o.minTicks),
                    Codec.FLOAT.fieldOf("max_ticks_multiplier").forGetter(o -> o.maxTicks),
                    Codec.FLOAT.fieldOf("change_every_tick_multiplier").forGetter(o -> o.randomness),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, AdjustLureTimeModifier::new));

    public AdjustLureTimeModifier(float minTicks, float maxTicks, float randomness, String translationOverride)
    {
        super(translationOverride);
        this.minTicks = minTicks;
        this.maxTicks = maxTicks;
        this.randomness = randomness;
    }

    @Override
    public List<Component> getNonOverriddenDescription()
    {
        return List.of();
    }

    @Override
    public DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolder()
    {
        return SCCatchModifiers.ADJUST_LURE_TIME;
    }

    @Override
    public MapCodec<? extends AbstractCatchModifier> codec()
    {
        return CODEC;
    }

    @Override
    public int adjustMinTicksToFish(int minTicksToFish)
    {
        return (int) (minTicksToFish * minTicks);
    }

    @Override
    public int adjustMaxTicksToFish(int maxTicksToFish)
    {
        return (int) (maxTicksToFish * maxTicks);
    }

    @Override
    public float adjustChanceToFishEachTick(float chanceToFishEachTick)
    {
        return chanceToFishEachTick * randomness;
    }
}
