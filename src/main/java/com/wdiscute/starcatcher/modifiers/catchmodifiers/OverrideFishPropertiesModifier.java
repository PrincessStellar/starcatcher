package com.wdiscute.starcatcher.modifiers.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.resources.ResourceLocation;

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
    public void afterChoosingTheCatch(List<FishProperties> immutableAvailable)
    {
        instance.fpToFish = fp;
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("override_fish_caught");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }
}
