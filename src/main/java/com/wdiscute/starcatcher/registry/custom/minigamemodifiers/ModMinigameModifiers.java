package com.wdiscute.starcatcher.registry.custom.minigamemodifiers;

import com.wdiscute.starcatcher.Starcatcher;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface ModMinigameModifiers
{
    DeferredRegister<Supplier<AbstractMinigameModifier>> REGISTRY =
            DeferredRegister.create(Starcatcher.MINIGAME_MODIFIERS_REGISTRY, Starcatcher.MOD_ID);

    //ice fishes
    DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> FREEZE_ON_MISS = registerMinigameModifier("freeze_on_miss", FreezeOnMissModifier::new);

    //base modifier
    DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> LOW_CHANCE_TREASURE_SPAWN = registerMinigameModifier("low_chance_treasure_spawn", LowChanceTreasureSpawnModifier::new);

    //shiny hook
    DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> SPAWN_TREASURE_ON_THREE_HITS = registerMinigameModifier("spawn_treasure_on_three_hits", ShinyHookModifier::new);

    //heavy hook
    DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> SLOWER_MOVING_SWEET_SPOTS = registerMinigameModifier("slower_moving_sweet_spots", () -> new HeavyHookModifier(2));

    //stone hook
    DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> STOP_DECAY_ON_HIT = registerMinigameModifier("stop_decay_on_hit", StoneHookModifier::new);

    //mossy hook
    DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> HARDER_WITH_TREASURE_ON_PERFECT = registerMinigameModifier("harder_with_treasure_on_perfect", MossyHookModifier::new);

    //steady bobber
    DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> BIGGER_GREEN_SWEET_SPOTS = registerMinigameModifier("bigger_green_sweet_spots", SteadyBobberModifier::new);

    //clear bobber
    DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> SLOWER_VANISHING = registerMinigameModifier("slower_vanishing", ClearBobberModifier::new);

    //aqua bobber
    DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> ADD_AQUA_SWEET_SPOT = registerMinigameModifier("add_aqua_sweet_spot", () -> new AquaBobberModifier(-1, 1));

    //MultiLayer
    DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> NIKDO53_MODIFIER = registerMinigameModifier("nikdo53_modifier", Nikdo53Modifier::new);

    //base
    DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> BASE = registerMinigameModifier("base", BaseModifier::new);

    //flip on every hit
    DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> FLIP_EVERY_HIT = registerMinigameModifier("flip_every_hit", FlipEveryHitModifier::new);

    DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> FROZEN_POINTER = registerMinigameModifier("frozen_pointer", () -> new FrozenPointerWhileActiveModifier(20, 10));

    DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> SPAWN_SWEET_SPOTS = registerMinigameModifier("spawn_sweet_spots", () -> new SpawnSweetSpotsModifier(SpawnSweetSpotsModifier.legacyFreeze()));

    static DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> registerMinigameModifier(String name, Supplier<AbstractMinigameModifier> sup)
    {
        return REGISTRY.register(name, () -> sup);
    }

    static void register(IEventBus eventBus)
    {
        REGISTRY.register(eventBus);
    }
}
