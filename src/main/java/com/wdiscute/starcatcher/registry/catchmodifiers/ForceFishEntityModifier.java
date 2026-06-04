package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ForceFishEntityModifier extends AbstractCatchModifier
{
    float chance;

    public static final MapCodec<ForceFishEntityModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.FLOAT.fieldOf("chance").forGetter(o -> o.chance),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, ForceFishEntityModifier::new));

    public ForceFishEntityModifier(float chance, String translationOverride)
    {
        super(translationOverride);
        this.chance = chance;
    }

    @Override
    public DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolder()
    {
        return SCCatchModifiers.FORCE_FISH_ENTITY;
    }

    @Override
    public MapCodec<? extends AbstractCatchModifier> codec()
    {
        return CODEC;
    }

    @Override
    public boolean forceSpawnEntity()
    {
        return instance.level().getRandom().nextFloat() < chance;
    }
}
