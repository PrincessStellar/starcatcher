package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.registry.fishrestrictions.DaytimeRestriction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.text.DecimalFormat;
import java.util.List;

public class IgnoreDaytimeRestrictionsModifier extends AbstractCatchModifier implements DaytimeRestriction.SkipsDaytimeRestriction
{
    float chance;

    public static final MapCodec<IgnoreDaytimeRestrictionsModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.FLOAT.fieldOf("chance").forGetter(o -> o.chance),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, IgnoreDaytimeRestrictionsModifier::new));

    public IgnoreDaytimeRestrictionsModifier(float chance, String translationOverride)
    {
        super(translationOverride);
        this.chance = chance;
    }

    @Override
    public List<Component> getNonOverriddenDescription(boolean shift)
    {
        if(shift)
            return List.of(Component.translatable("tooltip.modifier.starcatcher.ignore_daytime_restrictions.shift", new DecimalFormat("#.##").format(chance * 100)));

        if(chance >= 1)
            return List.of(Component.translatable("tooltip.modifier.starcatcher.ignore_daytime_restrictions"));
        else
            return List.of(Component.translatable("tooltip.modifier.starcatcher.ignore_daytime_restrictions.maybe"));
    }

    @Override
    public boolean shouldSkipDaytime(Level level)
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
