package com.wdiscute.starcatcher.registry.custom.sellingbinprocessor;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.registry.ModDataMaps;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public interface ModSellingBinProcessors
{
    DeferredRegister<AbstractSellingBinProcessor> REGISTRY =
            DeferredRegister.create(Starcatcher.SELLING_BIN_REGISTRY, Starcatcher.MOD_ID);


    DeferredHolder<AbstractSellingBinProcessor, AbstractSellingBinProcessor> SIMPLE_ITEM =
            registerCatchModifier("simple_item", SimpleItemSellingBinProcessor::new);

    DeferredHolder<AbstractSellingBinProcessor, AbstractSellingBinProcessor> FISH = registerCatchModifier("fish_processor", FishSellingBinProcessor::new);


    default int calculateFromStack(ItemStack is)
    {
        List<AbstractSellingBinProcessor> processors = ModDataMaps.getOrDefault(is, ModDataMaps.SELLING_BIN_VALUE, List.of());

        int value = 0;

        for (var p : processors)
        {
            value += p.calculateValue(is);
        }

        return value;
    }

    static DeferredHolder<AbstractSellingBinProcessor, AbstractSellingBinProcessor> registerCatchModifier(String name, Supplier<AbstractSellingBinProcessor> sup)
    {
        return REGISTRY.register(name, sup);
    }

    static void register(IEventBus eventBus)
    {
        REGISTRY.register(eventBus);
    }

}
