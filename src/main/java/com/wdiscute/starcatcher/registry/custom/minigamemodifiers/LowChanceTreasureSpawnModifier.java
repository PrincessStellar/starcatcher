package com.wdiscute.starcatcher.registry.custom.minigamemodifiers;

import com.mojang.serialization.MapCodec;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

public class LowChanceTreasureSpawnModifier extends AbstractMinigameModifier
{

    public static final MapCodec<LowChanceTreasureSpawnModifier> CODEC = MapCodec.unit(LowChanceTreasureSpawnModifier::new);

    @Override
    public MapCodec<? extends AbstractMinigameModifier> codec() {
        return CODEC;
    }

    @Override
    public DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> getRegistryHolder() {
        return SCMinigameModifiers.LOW_CHANCE_TREASURE_SPAWN;
    }

    @Override
    public boolean onHit(ActiveSweetSpot ass)
    {
        if (U.r.nextFloat() > 0.975 && instance.progress == 0)
        {
            removed = true;
            ActiveSweetSpot newTreasureSweetSpot = new ActiveSweetSpot(instance, FishProperties.SweetSpot.TREASURE);
            instance.addSweetSpot(newTreasureSweetSpot);
        }

        return super.onHit(ass);
    }
}
