package com.wdiscute.starcatcher.datagen;

import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.registry.blocks.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Set;

import static com.wdiscute.starcatcher.registry.blocks.ModBlocks.*;

public class ModBlockLootTableProvider extends BlockLootSubProvider
{
    protected ModBlockLootTableProvider(HolderLookup.Provider registries)
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate()
    {
        HATS.getEntries().forEach(o -> dropSelf(o.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.HATS.getEntries().stream().map(Holder::value)::iterator;
    }
}
