package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.fish.FishProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

public class ModifyAwardFishRlModifier extends AbstractCatchModifier
{
    ResourceLocation rlToAward;

    public static final MapCodec<ModifyAwardFishRlModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    ResourceLocation.CODEC.fieldOf("resource_location_to_award").forGetter(o -> o.rlToAward),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, ModifyAwardFishRlModifier::new));

    public ModifyAwardFishRlModifier(ResourceLocation rl, String translationOverride)
    {
        super(translationOverride);
        this.rlToAward = rl;
    }

    @Override
    public DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolder()
    {
        return SCCatchModifiers.MODIFY_AWARD_FISH;
    }

    @Override
    public MapCodec<? extends AbstractCatchModifier> codec()
    {
        return CODEC;
    }

    @Override
    public void afterChoosingTheCatch(List<FishProperties> immutableAvailable)
    {
        this.instance.rlToAwardUponFishingComplete = rlToAward;
    }
}
