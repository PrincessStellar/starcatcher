package com.wdiscute.starcatcher.registry.custom.sellingbinprocessor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

public class FishSellingBinProcessor extends AbstractSellingBinProcessor
{
    private final int base_value;

    public FishSellingBinProcessor()
    {
        this.base_value = 0;
    }

    public FishSellingBinProcessor(int baseValue)
    {
        this.base_value = baseValue;
    }

    public static final MapCodec<FishSellingBinProcessor> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.fieldOf("base_value").forGetter(FishSellingBinProcessor::getLength)
            ).apply(instance, FishSellingBinProcessor::new));

    private Integer getLength()
    {
        return base_value;
    }


    @Override
    public MapCodec<? extends AbstractSellingBinProcessor> codec()
    {
        return CODEC;
    }

    @Override
    public DeferredHolder<AbstractSellingBinProcessor, AbstractSellingBinProcessor> getRegistryHolder()
    {
        return ModSellingBinProcessors.FISH;
    }

    @Override
    public int calculateValue(ItemStack itemStack)
    {
        return 0;
    }
}
