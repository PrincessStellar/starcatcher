package com.wdiscute.starcatcher.datagen;

import com.wdiscute.starcatcher.registry.ModDataMaps;
import com.wdiscute.starcatcher.registry.blocks.AquariumBlock;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.DataMapProvider;

import java.util.concurrent.CompletableFuture;

public class DGDataMapsProvider extends DataMapProvider {
    protected DGDataMapsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        var decor = this.builder(ModDataMaps.AQUARIUM_DECOR);

        decor.add(Items.KELP.builtInRegistryHolder(), AquariumBlock.Decoration.KELP, false);
        decor.add(ItemTags.SAND, AquariumBlock.Decoration.CASTLE_RED_SAND, false);
    }
}
