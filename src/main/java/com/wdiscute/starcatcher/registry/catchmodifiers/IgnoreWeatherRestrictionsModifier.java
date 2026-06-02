package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.registry.fishrestrictions.WeatherRestriction;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;

public class IgnoreWeatherRestrictionsModifier extends AbstractCatchModifier implements WeatherRestriction.SkipsWeatherRestriction
{
    float chance;

    public static final MapCodec<IgnoreWeatherRestrictionsModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.FLOAT.fieldOf("chance").forGetter(o -> o.chance),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, IgnoreWeatherRestrictionsModifier::new));

    public IgnoreWeatherRestrictionsModifier(float chance, String translationOverride)
    {
        super(translationOverride);
        this.chance = chance;
    }

    @Override
    public boolean shouldSkipWeather(Level level)
    {
        return level.getRandom().nextFloat() < chance;
    }

    @Override
    public DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolder()
    {
        return SCCatchModifiers.IGNORE_DAYTIME_RESTRICTION;
    }

    @Override
    public MapCodec<? extends AbstractCatchModifier> codec()
    {
        return CODEC;
    }
}
