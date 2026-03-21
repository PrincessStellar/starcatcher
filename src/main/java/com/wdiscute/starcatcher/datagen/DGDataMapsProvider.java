package com.wdiscute.starcatcher.datagen;

import com.wdiscute.sellingbin.processors.QualityFoodsProcessor;
import com.wdiscute.sellingbin.registry.ModDataMaps;
import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.registry.SCDataMaps;
import com.wdiscute.starcatcher.registry.blocks.SCBlocks;
import com.wdiscute.starcatcher.registry.blocks.aquarium.AquariumBlock;
import com.wdiscute.starcatcher.sellingbin.FishProcessor;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.DataMapProvider;

import java.util.List;
import java.util.Map;
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
        var decor = this.builder(SCDataMaps.AQUARIUM_INTERACTION);
        var currencies = this.builder(com.wdiscute.sellingbin.registry.ModDataMaps.SELLING_BIN_CURRENCIES);
        var bin = this.builder(com.wdiscute.sellingbin.registry.ModDataMaps.SELLING_BIN_VALUE);

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
        decor.add(SCBlocks.CONCH.get().asItem().builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_CONCH, false);
        decor.add(SCBlocks.CLAM.get().asItem().builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_CLAM, false);

        decor.add(StarcatcherTags.STARCAUGHT_FISHES, AquariumBlock.Interaction.PLACE_FISH, false);

        //selling bin datagen
        //shouldn't be run as the JSONs are manually moved to a
        //built-in datapack instead of hard coded into the mod's resources
        if(false)
        {
            //selling bin currencies
            currencies.add(Items.EMERALD.builtInRegistryHolder(), 100, false);
            currencies.add(Items.EMERALD_BLOCK.builtInRegistryHolder(), 900, false);

            //selling bin fishes
            Map<ResourceLocation, Float> qualities = Map.of(
                    ResourceLocation.fromNamespaceAndPath("quality_food", "diamond"), 2f,
                    ResourceLocation.fromNamespaceAndPath("quality_food", "gold"), 1.5f,
                    ResourceLocation.fromNamespaceAndPath("quality_food", "iron"), 1.25f
            );

            bin.add(StarcatcherTags.COMMON_FISHES, new ModDataMaps.ItemValue(25, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            bin.add(StarcatcherTags.UNCOMMON_FISHES, new ModDataMaps.ItemValue(50, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            bin.add(StarcatcherTags.RARE_FISHES, new ModDataMaps.ItemValue(100, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            bin.add(StarcatcherTags.EPIC_FISHES, new ModDataMaps.ItemValue(150, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            bin.add(StarcatcherTags.LEGENDARY_FISHES, new ModDataMaps.ItemValue(200, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);
        }

    }
}
