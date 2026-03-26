package com.wdiscute.starcatcher.datagen;

import com.wdiscute.sellingbin.processors.AbstractProcessor;
import com.wdiscute.sellingbin.processors.QualityFoodsProcessor;
import com.wdiscute.sellingbin.registry.ModDataMaps;
import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.registry.SCDataMaps;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.blocks.SCBlocks;
import com.wdiscute.starcatcher.blocks.aquarium.AquariumBlock;
import com.wdiscute.starcatcher.sellingbin.FishProcessor;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class DGSCDataMapsProvider extends DataMapProvider
{
    protected DGSCDataMapsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider)
    {
        var decor = this.builder(SCDataMaps.AQUARIUM_INTERACTION);
        var currencies = this.builder(ModDataMaps.SELLING_BIN_CURRENCIES);
        var sellable = this.builder(ModDataMaps.SELLING_BIN_VALUE);
        var compostable = this.builder(NeoForgeDataMaps.COMPOSTABLES);

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

        //compostable
        compostable.add(StarcatcherTags.WORMS, new Compostable(0.65F, false), false);

        //selling sellable datagen
        //shouldn't be run as the JSONs are manually moved to a
        //built-in datapack instead of hard coded into the mod's resources
        if(false)
        {
            //selling sellable currencies
            currencies.add(Items.EMERALD.builtInRegistryHolder(), 100, false);
            currencies.add(Items.EMERALD_BLOCK.builtInRegistryHolder(), 900, false);

            //selling sellable fishes
            Map<ResourceLocation, Float> qualities = Map.of(
                    ResourceLocation.fromNamespaceAndPath("quality_food", "diamond"), 2f,
                    ResourceLocation.fromNamespaceAndPath("quality_food", "gold"), 1.5f,
                    ResourceLocation.fromNamespaceAndPath("quality_food", "iron"), 1.25f
            );

            sellable.add(StarcatcherTags.COMMON_FISHES, new ModDataMaps.ItemValue(25, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            sellable.add(StarcatcherTags.UNCOMMON_FISHES, new ModDataMaps.ItemValue(50, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            sellable.add(StarcatcherTags.RARE_FISHES, new ModDataMaps.ItemValue(100, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            sellable.add(StarcatcherTags.EPIC_FISHES, new ModDataMaps.ItemValue(150, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            sellable.add(StarcatcherTags.LEGENDARY_FISHES, new ModDataMaps.ItemValue(200, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            sellable.add(SCItems.PEARL.get().asItem().builtInRegistryHolder(), AbstractProcessor.createEmpty(50), false);
        }

    }
}
