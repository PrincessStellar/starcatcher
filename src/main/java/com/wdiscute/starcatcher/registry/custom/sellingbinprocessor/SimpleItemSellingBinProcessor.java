package com.wdiscute.starcatcher.registry.custom.sellingbinprocessor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

public class SimpleItemSellingBinProcessor extends AbstractSellingBinProcessor
{
    private final int base_value;

    public SimpleItemSellingBinProcessor()
    {
        this.base_value = 0;
    }

    public SimpleItemSellingBinProcessor(int baseValue)
    {
        this.base_value = baseValue;
    }

    public static final MapCodec<SimpleItemSellingBinProcessor> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.fieldOf("base_value").forGetter(SimpleItemSellingBinProcessor::getLength)
            ).apply(instance, SimpleItemSellingBinProcessor::new));

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
        return ModSellingBinProcessors.SIMPLE_ITEM;
    }

    @Override
    public int calculateValue(ItemStack itemStack)
    {
        return base_value;
    }
}
