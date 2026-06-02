package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.neoforged.neoforge.registries.DeferredHolder;

public class TriggersSkipMinigameModifier extends AbstractCatchModifier implements SkipMinigameIfTriggerFoundModifier.SkipsMinigame
{
    public static final MapCodec<TriggersSkipMinigameModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, TriggersSkipMinigameModifier::new));

    public TriggersSkipMinigameModifier(String translationOverride)
    {
        super(translationOverride);
    }

    @Override
    public DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolder()
    {
        return SCCatchModifiers.TRIGGER_SKIP_MINIGAME;
    }

    @Override
    public MapCodec<? extends AbstractCatchModifier> codec()
    {
        return CODEC;
    }
}
