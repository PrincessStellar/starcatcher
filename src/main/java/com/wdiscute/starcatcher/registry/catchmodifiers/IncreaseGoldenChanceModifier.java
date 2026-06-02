package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.io.FishCaughtCounter;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.registries.DeferredHolder;

public class IncreaseGoldenChanceModifier extends AbstractCatchModifier
{
    final float chance;

    public static final MapCodec<IncreaseGoldenChanceModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.FLOAT.fieldOf("chance_increase").forGetter(o -> o.chance),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, IncreaseGoldenChanceModifier::new));

    public IncreaseGoldenChanceModifier(float chance, String translationOverride)
    {
        super(translationOverride);
        this.chance = chance;
    }

    @Override
    public DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolder()
    {
        return SCCatchModifiers.INCREASE_GOLDEN_CHANCE;
    }

    @Override
    public MapCodec<? extends AbstractCatchModifier> codec()
    {
        return CODEC;
    }

    @Override
    public boolean shouldBeGolden()
    {
        return FishCaughtCounter.canCatchGolden(instance.fpToFish, (ServerPlayer) instance.player) && instance.level().getRandom().nextFloat() < chance;
    }
}
