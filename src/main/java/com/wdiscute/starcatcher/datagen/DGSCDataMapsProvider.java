package com.wdiscute.starcatcher.datagen;

import com.wdiscute.sellingbin.processors.AbstractProcessor;
import com.wdiscute.sellingbin.processors.QualityFoodsProcessor;
import com.wdiscute.sellingbin.registry.ModDataMaps;
import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.registry.SCDataMaps;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.blocks.SCBlocks;
import com.wdiscute.starcatcher.blocks.aquarium.AquariumBlock;
import com.wdiscute.starcatcher.registry.catchmodifiers.SCCatchModifiers;
import com.wdiscute.starcatcher.registry.minigamemodifiers.SCMinigameModifiers;
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
        var catchModifiers = this.builder(SCDataMaps.CATCH_MODIFIERS);
        var minigameModifiers = this.builder(SCDataMaps.MINIGAME_MODIFIERS);

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

        decor.add(SCTags.STARCAUGHT_FISHES, AquariumBlock.Interaction.PLACE_FISH, false);

        //compostable
        compostable.add(SCTags.WORMS, new Compostable(0.65F, false), false);

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

            sellable.add(SCTags.COMMON_FISHES, new ModDataMaps.ItemValue(25, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            sellable.add(SCTags.UNCOMMON_FISHES, new ModDataMaps.ItemValue(50, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            sellable.add(SCTags.RARE_FISHES, new ModDataMaps.ItemValue(100, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            sellable.add(SCTags.EPIC_FISHES, new ModDataMaps.ItemValue(150, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            sellable.add(SCTags.LEGENDARY_FISHES, new ModDataMaps.ItemValue(200, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            sellable.add(SCItems.PEARL.get().asItem().builtInRegistryHolder(), AbstractProcessor.createEmpty(50), false);
        }


        //minigame modifiers
        minigameModifiers.add(SCItems.SHINY_HOOK, List.of(SCMinigameModifiers.SPAWN_TREASURE_ON_THREE_HITS.getId()), false);
        minigameModifiers.add(SCItems.MOSSY_HOOK, List.of(SCMinigameModifiers.HARDER_WITH_TREASURE_ON_PERFECT.getId()), false);
        minigameModifiers.add(SCItems.STONE_HOOK, List.of(SCMinigameModifiers.STOP_DECAY_ON_HIT.getId()), false);
        minigameModifiers.add(SCItems.HEAVY_HOOK, List.of(SCMinigameModifiers.SLOWER_MOVING_SWEET_SPOTS.getId()), false);
        minigameModifiers.add(SCItems.STEADY_BOBBER, List.of(SCMinigameModifiers.BIGGER_GREEN_SWEET_SPOTS.getId()), false);
        minigameModifiers.add(SCItems.CLEAR_BOBBER, List.of(SCMinigameModifiers.SLOWER_VANISHING.getId()), false);
        minigameModifiers.add(SCItems.AQUA_BOBBER, List.of(SCMinigameModifiers.ADD_AQUA_SWEET_SPOT.getId()), false);

        //catch modifiers
        catchModifiers.add(SCItems.CRYSTAL_HOOK, List.of(SCCatchModifiers.SURVIVES_LAVA.getFirst()), false);
        catchModifiers.add(SCItems.GOLD_HOOK, List.of(SCCatchModifiers.EXTRA_EXP_BASED_ON_PERFORMANCE.getFirst()), false);
        catchModifiers.add(SCItems.STONE_HOOK, List.of(SCCatchModifiers.INCREASE_LURE_TIME.getFirst()), false);
        catchModifiers.add(SCItems.SPLIT_HOOK, List.of(SCCatchModifiers.EXTRA_ITEM.getFirst()), false);
        catchModifiers.add(SCItems.VANILLA_BOBBER, List.of(SCCatchModifiers.VANILLA_LOOT.getFirst()), false);
        catchModifiers.add(SCItems.VANILLA_HOOK, List.of(SCCatchModifiers.SKIP_MINIGAME_IF_VANILLA_LOOT.getFirst()), false);

        catchModifiers.add(SCItems.WORM, List.of(SCCatchModifiers.DECREASES_LURE_TIME.getFirst()), false);
        catchModifiers.add(SCItems.ALMIGHTY_WORM, List.of(SCCatchModifiers.DECREASES_LURE_TIME.getFirst(), SCCatchModifiers.FISH_ENTITY.getFirst()), false);
        catchModifiers.add(SCItems.SEEKING_WORM, List.of(SCCatchModifiers.DECREASES_LURE_TIME.getFirst(), SCCatchModifiers.GUARANTEE_NEW_FISH_ALWAYS.getFirst()), false);

        catchModifiers.add(SCItems.GUNPOWDER_BAIT, List.of(SCCatchModifiers.DECREASES_LURE_TIME.getFirst()), false);
        catchModifiers.add(SCItems.CHERRY_BAIT, List.of(SCCatchModifiers.DECREASES_LURE_TIME.getFirst()), false);
        catchModifiers.add(SCItems.LUSH_BAIT, List.of(SCCatchModifiers.DECREASES_LURE_TIME.getFirst()), false);
        catchModifiers.add(SCItems.SCULK_BAIT, List.of(SCCatchModifiers.DECREASES_LURE_TIME.getFirst()), false);
        catchModifiers.add(SCItems.DRIPSTONE_BAIT, List.of(SCCatchModifiers.DECREASES_LURE_TIME.getFirst()), false);
        catchModifiers.add(SCItems.MURKWATER_BAIT, List.of(SCCatchModifiers.DECREASES_LURE_TIME.getFirst()), false);
        catchModifiers.add(SCItems.LEGENDARY_BAIT, List.of(SCCatchModifiers.DECREASES_LURE_TIME.getFirst()), false);
        catchModifiers.add(SCItems.METEOROLOGICAL_BAIT, List.of(SCCatchModifiers.DECREASES_LURE_TIME.getFirst(), SCCatchModifiers.IGNORE_DAYTIME_AND_WEATHER_RESTRICTIONS.getFirst()), false);


    }
}
