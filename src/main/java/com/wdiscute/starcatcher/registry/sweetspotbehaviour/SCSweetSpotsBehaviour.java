package com.wdiscute.starcatcher.registry.sweetspotbehaviour;

import com.wdiscute.starcatcher.Starcatcher;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface SCSweetSpotsBehaviour
{
    DeferredRegister<Supplier<? extends AbstractSweetSpotBehaviour>> REGISTRY =
            DeferredRegister.create(Starcatcher.SWEETSPOT_BEHAVIOUR_REGISTRY, Starcatcher.MOD_ID);

    ResourceLocation NORMAL = registerSweetspot("normal", () -> NormalSweetSpotBehaviour::new);
    ResourceLocation FROZEN = registerSweetspot("freeze", () -> FreezeSweetSpotBehaviour::new);
    ResourceLocation TREASURE = registerSweetspot("treasure", () -> TreasureSweetSpotBehaviour::new);
    ResourceLocation TNT = registerSweetspot("tnt", () -> TntSweetSpotBehaviour::new);
    ResourceLocation AQUA = registerSweetspot("aqua", () -> AquaSweetSpotBehaviour::new);
    ResourceLocation LEAF = registerSweetspot("leaf", () -> LeafSweetSpotBehaviour::new);
    ResourceLocation DEEP_OCEAN = registerSweetspot("deep_ocean", () -> DeepOceanSweetSpotBehaviour::new);
    ResourceLocation CLOUD = registerSweetspot("cloud", () -> CloudSweetSpotBehaviour::new);
    ResourceLocation GLOWING_SWEETSPOT = registerSweetspot("glowing", () -> GlowingSweetSpotBehaviour::new);
    ResourceLocation MIRAGE = registerSweetspot("mirage", () -> MirageSweetspotBehaviour::new);


    static ResourceLocation registerSweetspot(String name, Supplier<Supplier<? extends AbstractSweetSpotBehaviour>> supplier)
    {
        REGISTRY.register(name, supplier);
        return Starcatcher.rl(name);
    }


    static void register(IEventBus eventBus)
    {
        REGISTRY.register(eventBus);
    }
}
