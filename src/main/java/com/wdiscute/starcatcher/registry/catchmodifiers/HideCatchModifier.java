package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.neoforged.neoforge.registries.DeferredHolder;

public class HideCatchModifier extends AbstractCatchModifier
{
    float chance;

    public static final MapCodec<HideCatchModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.FLOAT.fieldOf("chance").forGetter(o -> o.chance),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, HideCatchModifier::new));


    public HideCatchModifier(float chance, String translationOverride)
    {
        super(translationOverride);
        this.chance = chance;
    }

    @Override
    public DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolder()
    {
        return SCCatchModifiers.HIDE_CATCH;
    }

    @Override
    public MapCodec<? extends AbstractCatchModifier> codec()
    {
        return CODEC;
    }

    @Override
    public boolean shouldHideCatch()
    {
        return instance.level().getRandom().nextFloat() < chance;
    }
}
