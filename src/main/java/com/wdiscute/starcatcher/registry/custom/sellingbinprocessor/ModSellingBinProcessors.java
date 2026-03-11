package com.wdiscute.starcatcher.registry.custom.sellingbinprocessor;

import com.wdiscute.starcatcher.Starcatcher;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface ModSellingBinProcessors
{
    DeferredRegister<AbstractSellingBinProcessor> SELLING_BIN_PROCESSORS =
            DeferredRegister.create(Starcatcher.SELLING_BIN_REGISTRY, Starcatcher.MOD_ID);

    DeferredHolder<AbstractSellingBinProcessor, AbstractSellingBinProcessor> FISH = register("fish_processor", FishSellingBinProcessor::new);

    static DeferredHolder<AbstractSellingBinProcessor, AbstractSellingBinProcessor> register(String name, Supplier<AbstractSellingBinProcessor> sup)
    {
        return SELLING_BIN_PROCESSORS.register(name, sup);
    }

    static void register(IEventBus eventBus)
    {
        SELLING_BIN_PROCESSORS.register(eventBus);
    }

}
