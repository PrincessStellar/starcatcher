package com.wdiscute.starcatcher.registry;

import com.wdiscute.starcatcher.Starcatcher;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface SCStats
{
    DeferredRegister<ResourceLocation> CUSTOM_STATS =
            DeferredRegister.create(BuiltInRegistries.CUSTOM_STAT, Starcatcher.MOD_ID);

    DeferredHolder<ResourceLocation, ResourceLocation> STARCAUGHT_TREASURES = register("starcaught_treasures");
    DeferredHolder<ResourceLocation, ResourceLocation> STARCAUGHT_FISH = register("starcaught_fish");

    private static DeferredHolder<ResourceLocation, ResourceLocation> register(String key)
    {
        return CUSTOM_STATS.register(key, () -> Starcatcher.rl(key));
    }

    static void register(IEventBus eventBus)
    {
        CUSTOM_STATS.register(eventBus);
    }
}
