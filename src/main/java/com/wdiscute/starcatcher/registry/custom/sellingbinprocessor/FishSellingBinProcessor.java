package com.wdiscute.starcatcher.registry.custom.sellingbinprocessor;

import com.mojang.serialization.MapCodec;
import com.wdiscute.starcatcher.io.CaughtFishInfo;
import com.wdiscute.starcatcher.io.ModDataComponents;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

public class FishSellingBinProcessor extends AbstractSellingBinProcessor
{
    public FishSellingBinProcessor() {}

    public static final MapCodec<FishSellingBinProcessor> CODEC = MapCodec.unit(FishSellingBinProcessor::new);

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
    public int calculateValue(int baseValue, int currentValue, ItemStack itemStack)
    {
        if(ModDataComponents.has(itemStack, ModDataComponents.CAUGHT_FISH_INFO))
        {
            CaughtFishInfo caughtFishInfo = ModDataComponents.get(itemStack, ModDataComponents.CAUGHT_FISH_INFO);

            assert caughtFishInfo != null;
            if(caughtFishInfo.golden()) return baseValue * 9;

            //😂😂😂😂

            System.out.println("----");
            System.out.println("base: " + baseValue);
            System.out.println("perc: " + caughtFishInfo.percentile());

            //double result = baseValue * (1 + 0.25 * (1 - 2 * caughtFishInfo.percentile() / 100.0));

            double result = caughtFishInfo.percentile() * (1 + ((baseValue - 50) / 50.0) * 0.25);

            System.out.println(result);


            return (int) result;
        }
        return 0;
    }
}
