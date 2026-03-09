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
    public int addValue(int baseValue, int currentValue, ItemStack itemStack)
    {
        if(ModDataComponents.has(itemStack, ModDataComponents.CAUGHT_FISH_INFO))
        {
            CaughtFishInfo caughtFishInfo = ModDataComponents.get(itemStack, ModDataComponents.CAUGHT_FISH_INFO);

            assert caughtFishInfo != null;
            if(caughtFishInfo.golden()) return baseValue * 9;

            // -+ 25% of base vale based on percentile, with +0% at 50% percentile
            return (int) (caughtFishInfo.percentile() * (1 + ((baseValue - 50) / 50.0) * 0.25));
        }
        return 0;
    }
}
