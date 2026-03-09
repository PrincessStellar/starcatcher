package com.wdiscute.starcatcher.datagen;

import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.registry.ModDataMaps;
import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.registry.blocks.ModBlocks;
import com.wdiscute.starcatcher.registry.blocks.aquarium.AquariumBlock;
import com.wdiscute.starcatcher.registry.custom.sellingbinprocessor.AbstractSellingBinProcessor;
import com.wdiscute.starcatcher.registry.custom.sellingbinprocessor.FishSellingBinProcessor;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.DataMapProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DGDataMapsProvider extends DataMapProvider
{
    protected DGDataMapsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider)
    {
        var decor = this.builder(ModDataMaps.AQUARIUM_INTERACTION);
        var bin = this.builder(ModDataMaps.SELLING_BIN_VALUE);

        //ground
        decor.add(Items.GRAVEL.builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_GRAVEL, false);
        decor.add(Items.SAND.builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_SAND, false);
        decor.add(Items.RED_SAND.builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_RED_SAND, false);
        decor.add(Items.STONE.builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_STONE, false);

        //decorations
        decor.add(ItemTags.SHOVELS, AquariumBlock.Interaction.BUILD_CASTLE, false);
        decor.add(ItemTags.PICKAXES, AquariumBlock.Interaction.BUILD_CAVE, false);
        decor.add(Items.KELP.builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_KELP, false);
        decor.add(Items.SEAGRASS.builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_SEAGRASS, false);
        decor.add(ModBlocks.CONCH.get().asItem().builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_CONCH, false);
        decor.add(ModBlocks.CLAM.get().asItem().builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_CLAM, false);

        decor.add(StarcatcherTags.STARCAUGHT_FISHES, AquariumBlock.Interaction.PLACE_FISH, false);


        bin.add(StarcatcherTags.COMMON_FISHES, new FishSellingBinProcessor().create(20), false);
        bin.add(StarcatcherTags.UNCOMMON_FISHES, new FishSellingBinProcessor().create(50), false);
        bin.add(StarcatcherTags.RARE_FISHES, new FishSellingBinProcessor().create(100), false);
        bin.add(StarcatcherTags.EPIC_FISHES, new FishSellingBinProcessor().create(200), false);
        bin.add(StarcatcherTags.LEGENDARY_FISHES, new FishSellingBinProcessor().create(500), false);

        bin.add(ModItems.FISH_BONES, AbstractSellingBinProcessor.Instance.empty(20), false);
    }
}
