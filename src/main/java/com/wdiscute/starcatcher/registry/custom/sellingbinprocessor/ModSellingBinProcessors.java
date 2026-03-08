package com.wdiscute.starcatcher.registry.custom.sellingbinprocessor;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.registry.ModDataMaps;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface ModSellingBinProcessors
{
    DeferredRegister<AbstractSellingBinProcessor> REGISTRY =
            DeferredRegister.create(Starcatcher.SELLING_BIN_REGISTRY, Starcatcher.MOD_ID);

    DeferredHolder<AbstractSellingBinProcessor, AbstractSellingBinProcessor> FISH = registerCatchModifier("fish_processor", FishSellingBinProcessor::new);


    static int calculateFromStack(ItemStack is)
    {
        var instance = ModDataMaps.getOrDefault(is, ModDataMaps.SELLING_BIN_VALUE, AbstractSellingBinProcessor.Instance.empty());

        int value = instance.baseValue();

        for (var p : instance.processors())
        {
            value += p.calculateValue(value, instance.baseValue(), is);
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
