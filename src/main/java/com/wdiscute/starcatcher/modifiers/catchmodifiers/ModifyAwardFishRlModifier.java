package com.wdiscute.starcatcher.modifiers.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.resources.ResourceLocation;

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
    public void afterChoosingTheCatch(List<FishProperties> immutableAvailable)
    {
        this.instance.rlToAwardUponFishingComplete = rlToAward;
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("modify_award_fish");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }
}
