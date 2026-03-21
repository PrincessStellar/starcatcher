package com.wdiscute.starcatcher.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.wdiscute.starcatcher.registry.blocks.SCBlocks.*;

public class DGSCBlockLootTableProvider extends BlockLootSubProvider
{
    protected DGSCBlockLootTableProvider(HolderLookup.Provider registries)
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate()
    {
        HATS.getEntries().forEach(o -> dropSelf(o.get()));
        TACKLE_BOXES.getEntries().forEach(o -> add(o.get(), createShulkerBoxDrop(o.get())));

        dropSelf(AQUARIUM.get());

        dropSelf(TROPHY_BRONZE.get());
        dropSelf(TROPHY_SILVER.get());
        dropSelf(TROPHY_GOLD.get());

        dropSelf(CLAM.get());
        dropSelf(CONCH.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        List<Block> list = new ArrayList<>();
        list.addAll(HATS.getEntries().stream().map(Holder::value).toList());
        list.addAll(TACKLE_BOXES.getEntries().stream().map(Holder::value).toList());

        list.add(TROPHY_BRONZE.get());
        list.add(TROPHY_SILVER.get());
        list.add(TROPHY_GOLD.get());

        list.add(AQUARIUM.get());

        list.add(CLAM.get());
        list.add(CONCH.get());
        return list::iterator;
    }
}
