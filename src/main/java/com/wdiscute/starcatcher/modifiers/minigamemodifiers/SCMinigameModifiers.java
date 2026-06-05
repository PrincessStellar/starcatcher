package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.modifiers.Modifier;

public interface SCMinigameModifiers
{
    static void register()
    {
        //minigame modifier
        Modifier.MODIFIERS.put(Starcatcher.rl("freeze_on_miss"), FreezeOnMissModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("burn_on_miss"), BurnOnMissModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("teleport"), TeleportModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("spawn_treasure_on_three_hits"), ShinyHookModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("slower_moving_sweet_spots"), HeavyHookModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("stop_decay_on_hit"), StoneHookModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("harder_with_treasure_on_perfect"), MossyHookModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("bigger_green_sweet_spots"), SteadyBobberModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("slower_vanishing"), ClearBobberModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("add_aqua_sweet_spot"), AquaBobberModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("nikdo53_modifier"), Nikdo53Modifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("base"), BaseMinigameModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("flip_every_hit"), FlipEveryHitModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("frozen_pointer"), FrozenPointerWhileActiveModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("spawn_sweet_spots"), SpawnSweetSpotsModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("faster_handle_speed"), ModifyBasePointerSpeedModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("add_leaves_spots"), AddLeavesSweetspotsModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("bounce_back"), BounceBackModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("never_lose"), NeverLoseModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("prevent_frozen"), PreventFrozenModifier.CODEC);
    }
}
