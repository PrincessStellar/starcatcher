package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
                    Codec.FLOAT.fieldOf("chance_every_tick_multiplier").forGetter(o -> o.randomness),
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
    public List<Component> getNonOverriddenDescription(boolean shift)
    {
        if (shift)
        {
            List<Component> list = new ArrayList<>();

            var format = new DecimalFormat("#.##");

            if (minTicks != 1)
                list.add(Component.translatable("tooltip.modifier.starcatcher.adjust_lure_time.shift.min_ticks",
                        format.format(minTicks * 100)));
            if (maxTicks != 1)
                list.add(Component.translatable("tooltip.modifier.starcatcher.adjust_lure_time.shift.max_ticks",
                        format.format(maxTicks * 100)));
            if (randomness != 1)
                list.add(Component.translatable("tooltip.modifier.starcatcher.adjust_lure_time.shift.random",
                        format.format(randomness * 100)));

            return list;
        }
        else
        {
            float total = (minTicks + maxTicks) / 2;

            if (total > 2f)
                return List.of(Component.translatable("tooltip.modifier.starcatcher.adjust_lure_time.big_increase"));

            if (total > 1f)
                return List.of(Component.translatable("tooltip.modifier.starcatcher.adjust_lure_time.increase"));

            if (total < 0.75f)
                return List.of(Component.translatable("tooltip.modifier.starcatcher.adjust_lure_time.big_decrease"));

            return List.of(Component.translatable("tooltip.modifier.starcatcher.adjust_lure_time.decrease"));
        }
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
