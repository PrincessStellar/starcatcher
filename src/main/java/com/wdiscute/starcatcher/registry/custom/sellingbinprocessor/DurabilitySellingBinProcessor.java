package com.wdiscute.starcatcher.registry.custom.sellingbinprocessor;

import com.mojang.serialization.MapCodec;
import com.wdiscute.starcatcher.io.CaughtFishInfo;
import com.wdiscute.starcatcher.io.ModDataComponents;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DurabilitySellingBinProcessor extends AbstractSellingBinProcessor
{
    public DurabilitySellingBinProcessor() {}

    public static final MapCodec<DurabilitySellingBinProcessor> CODEC = MapCodec.unit(DurabilitySellingBinProcessor::new);

    @Override
    public MapCodec<? extends AbstractSellingBinProcessor> codec()
    {
        return CODEC;
    }

    @Override
    public DeferredHolder<AbstractSellingBinProcessor, AbstractSellingBinProcessor> getRegistryHolder()
    {
        return ModSellingBinProcessors.DURABILITY;
    }

    @Override
    public int addValue(int baseValue, int currentValue, ItemStack itemStack)
    {
        if (itemStack.getDamageValue() == 0) return 0;

        //reduces the current value by a percentage of remaining durability
        //100% = full untouched
        //50% = half value
        float durability = (float) (itemStack.getMaxDamage() - itemStack.getDamageValue()) / itemStack.getMaxDamage();

        return (int) (-currentValue * (1 - durability));
    }
}
