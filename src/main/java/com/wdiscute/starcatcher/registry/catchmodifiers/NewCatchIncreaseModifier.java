package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.io.FishCaughtCounter;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.registry.SCDataAttachments;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewCatchIncreaseModifier extends AbstractCatchModifier
{
    int increase;

    public static final MapCodec<NewCatchIncreaseModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.fieldOf("increase").forGetter(o -> o.increase),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, NewCatchIncreaseModifier::new));

    public NewCatchIncreaseModifier(int increase, String translationOverride)
    {
        super(translationOverride);
        this.increase = increase;
    }

    @Override
    public List<Component> getNonOverriddenDescription(boolean shift)
    {
        if (shift)
            return List.of(Component.translatable("tooltip.modifier.starcatcher.new_catch_increase.shift", increase));

        return List.of(Component.translatable("tooltip.modifier.starcatcher.new_catch_increase"));
    }

    @Override
    public DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolder()
    {
        return SCCatchModifiers.NEW_CATCH_INCREASE;
    }

    @Override
    public MapCodec<? extends AbstractCatchModifier> codec()
    {
        return CODEC;
    }

    @Override
    public List<FishProperties> modifyAvailablePool(List<FishProperties> available)
    {
        List<FishProperties> list = new ArrayList<>(available);

        Map<ResourceLocation, FishCaughtCounter> fishesCaught = instance.player.getData(SCDataAttachments.FISHING_GUIDE).fishesCaught;

        for (FishProperties fp : available)
        {
            ResourceLocation key = instance.level().registryAccess().registryOrThrow(Starcatcher.FISH_REGISTRY_KEY).getKey(fp);

            if (key == null) continue;

            if (!fishesCaught.containsKey(key))
            {
                for (int i = 0; i < increase; i++)
                {
                    list.add(fp);
                }
            }
        }

        return list;
    }
}
