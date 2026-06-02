package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.fish.FishProperties;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

public class OverrideFishPropertiesModifier extends AbstractCatchModifier
{
    FishProperties fp;

    public static final MapCodec<OverrideFishPropertiesModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    FishProperties.CODEC.fieldOf("fish_properties").forGetter(o -> o.fp),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, OverrideFishPropertiesModifier::new));

    public OverrideFishPropertiesModifier(FishProperties fp, String translationOverride)
    {
        super(translationOverride);
        this.fp = fp;
    }

    @Override
    public DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolder()
    {
        return SCCatchModifiers.EMPTY;
    }

    @Override
    public MapCodec<? extends AbstractCatchModifier> codec()
    {
        return CODEC;
    }

    @Override
    public void afterChoosingTheCatch(List<FishProperties> immutableAvailable)
    {
        instance.fpToFish = fp;
    }
}
