package com.wdiscute.starcatcher.registry.minigamemodifiers;

import com.mojang.serialization.MapCodec;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.fish.FishProperties;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

public class ShinyHookModifier extends AbstractMinigameModifier
{
    public static final MapCodec<ShinyHookModifier> CODEC = MapCodec.unit(ShinyHookModifier::new);

    private int hits = 0;

    @Override
    public void onMiss()
    {
        super.onMiss();
        hits = 0;
    }

    @Override
    public MapCodec<? extends AbstractMinigameModifier> codec() {
        return CODEC;
    }

    @Override
    public DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> getRegistryHolder() {
        return SCMinigameModifiers.SPAWN_TREASURE_ON_THREE_HITS;
    }

    @Override
    public boolean onHit(ActiveSweetSpot ass)
    {
        hits++;

        if(hits == 3 && !instance.treasureActive && instance.treasureProgress == 0)
        {
            instance.addSweetSpot(new ActiveSweetSpot(instance, Difficulty.SweetSpot.TREASURE));
            instance.addSweetSpot(new ActiveSweetSpot(instance, Difficulty.SweetSpot.TREASURE));
            removed = true;
        }

        return super.onHit(ass);
    }
}
