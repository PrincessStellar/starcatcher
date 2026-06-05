package com.wdiscute.starcatcher.datagen;

import com.wdiscute.sellingbin.processors.AbstractProcessor;
import com.wdiscute.sellingbin.processors.QualityFoodsProcessor;
import com.wdiscute.sellingbin.registry.SBDataMaps;
import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.registry.SCDataMaps;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.SCBlocks;
import com.wdiscute.starcatcher.blocks.aquarium.AquariumBlock;
import com.wdiscute.starcatcher.registry.Treasure;
import com.wdiscute.starcatcher.modifiers.catchmodifiers.*;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.*;
import com.wdiscute.starcatcher.registry.tackleskin.SCTackleSkins;
import com.wdiscute.starcatcher.sellingbin.FishProcessor;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
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
        var aquarium = this.builder(SCDataMaps.AQUARIUM_INTERACTION);
        var currencies = this.builder(SBDataMaps.SELLING_BIN_CURRENCIES);
        var sellable = this.builder(SBDataMaps.SELLING_BIN_VALUE);
        var compostable = this.builder(NeoForgeDataMaps.COMPOSTABLES);
        var modifiers = this.builder(SCDataMaps.ITEM_MODIFIERS);
        var tackleSkin = this.builder(SCDataMaps.TACKLE_SKIN);

        //ground
        aquarium.add(Items.GRAVEL.builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_GRAVEL, false);
        aquarium.add(Items.SAND.builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_SAND, false);
        aquarium.add(Items.RED_SAND.builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_RED_SAND, false);
        aquarium.add(Items.STONE.builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_STONE, false);

        //decorations
        aquarium.add(ItemTags.SHOVELS, AquariumBlock.Interaction.BUILD_CASTLE, false);
        aquarium.add(ItemTags.PICKAXES, AquariumBlock.Interaction.BUILD_CAVE, false);
        aquarium.add(Items.KELP.builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_KELP, false);
        aquarium.add(Items.SEAGRASS.builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_SEAGRASS, false);
        aquarium.add(SCBlocks.CONCH.get().asItem().builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_CONCH, false);
        aquarium.add(SCBlocks.CLAM.get().asItem().builtInRegistryHolder(), AquariumBlock.Interaction.PLACE_CLAM, false);

        aquarium.add(SCItems.STARCAUGHT_BUCKET, AquariumBlock.Interaction.PLACE_FISH, false);
        aquarium.add(SCTags.BUCKETABLE_FISHES, AquariumBlock.Interaction.PLACE_FISH_CREATIVE, false);
        aquarium.add(Tags.Items.BUCKETS, AquariumBlock.Interaction.RETRIEVE_FISH, false);

        //compostable
        compostable.add(SCTags.WORMS, new Compostable(0.65F, false), false);
        compostable.add(SCTags.BUCKETABLE_FISHES, new Compostable(0.9F, false), false);

        //selling sellable datagen
        //shouldn't be run as the JSONs are manually moved to a
        //built-in datapack instead of hard coded into the mod's resources
        if (false)
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

            sellable.add(SCTags.COMMON_FISHES, new SBDataMaps.ItemValue(25, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            sellable.add(SCTags.UNCOMMON_FISHES, new SBDataMaps.ItemValue(50, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            sellable.add(SCTags.RARE_FISHES, new SBDataMaps.ItemValue(100, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            sellable.add(SCTags.EPIC_FISHES, new SBDataMaps.ItemValue(150, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            sellable.add(SCTags.LEGENDARY_FISHES, new SBDataMaps.ItemValue(200, List.of(
                    new FishProcessor(2, 10f),
                    new QualityFoodsProcessor(qualities)
            )), false);

            sellable.add(SCItems.PEARL.get().asItem().builtInRegistryHolder(), AbstractProcessor.createEmpty(50), false);
        }

        //minigame modifiers
//        modifiers.add(SCItems.FROZEN_HOOK, List.of(SCMinigameModifiers.PREVENT_FROZEN.getId()), false);
//        modifiers.add(SCItems.SHINY_HOOK, List.of(SCMinigameModifiers.SPAWN_TREASURE_ON_THREE_HITS.getId()), false);
//        modifiers.add(SCItems.MOSSY_HOOK, List.of(SCMinigameModifiers.HARDER_WITH_TREASURE_ON_PERFECT.getId()), false);
//        modifiers.add(SCItems.STONE_HOOK, List.of(SCMinigameModifiers.STOP_DECAY_ON_HIT.getId()), false);
//        modifiers.add(SCItems.HEAVY_HOOK, List.of(SCMinigameModifiers.SLOWER_MOVING_SWEET_SPOTS.getId()), false);
//        modifiers.add(SCItems.STEADY_BOBBER, List.of(SCMinigameModifiers.BIGGER_GREEN_SWEET_SPOTS.getId()), false);
//        modifiers.add(SCItems.CLEAR_BOBBER, List.of(SCMinigameModifiers.SLOWER_VANISHING.getId()), false);
//        modifiers.add(SCItems.AQUA_BOBBER, List.of(SCMinigameModifiers.ADD_AQUA_SWEET_SPOT.getId()), false);
//        modifiers.add(SCItems.COPPER_HOOK, List.of(SCMinigameModifiers.FASTER_POINTER_SPEED.getId()), false);
//        modifiers.add(SCItems.EXPOSED_COPPER_HOOK, List.of(SCMinigameModifiers.SLIGHTLY_FASTER_POINTER_SPEED.getId()), false);
//        modifiers.add(SCItems.WEATHERED_COPPER_HOOK, List.of(SCMinigameModifiers.SLIGHTLY_SLOWER_POINTER_SPEED.getId()), false);
//        modifiers.add(SCItems.OXIDISED_COPPER_HOOK, List.of(SCMinigameModifiers.SLOWER_POINTER_SPEED.getId()), false);
//        modifiers.add(SCItems.LEAF_BOBBER, List.of(SCMinigameModifiers.ADD_LEAVES.getId()), false);
//        modifiers.add(SCItems.SLIMEY_BOBBER, List.of(SCMinigameModifiers.BOUNCE_BACK.getId()), false);
//        modifiers.add(SCItems.DEV_WORM, List.of(SCMinigameModifiers.NEVER_LOSE.getId()), false);

        modifiers.add(SCItems.FROZEN_HOOK, List.of(
                new PreventFrozenModifier("")
        ), false);

        modifiers.add(SCItems.FROZEN_HOOK, List.of(
                new PreventFrozenModifier("")
        ), false);

        modifiers.add(SCItems.SHINY_HOOK, List.of(
                new HideCatchModifier(1, "")
        ), false);

        modifiers.add(SCItems.ECHOING_HOOK, List.of(
                new NewCatchIncreaseModifier(5, "")
        ), false);

        modifiers.add(SCItems.AMETHYST_HOOK, List.of(
                new SurvivesLavaModifier("")
        ), false);

        modifiers.add(SCItems.GOLD_HOOK, List.of(
                new ExtraGoldenRiskModifier(0.05f, false, "")
        ), false);

        modifiers.add(SCItems.STONE_HOOK, List.of(
                new AdjustLureTimeModifier(1.2f, 1.6f, 1f, "")
        ), false);

        modifiers.add(SCItems.SPLIT_HOOK, List.of(
                new ExtraBaseCatchModifier(1, true, "")
        ), false);

        modifiers.add(SCItems.VANILLA_BOBBER, List.of(
                new AddLootTableToFishedItemsModifier(ResourceLocation.withDefaultNamespace("gameplay/fishing"),
                        "tooltip.modifier.starcatcher.vanilla_bobber"),
                new RemoveBaseFishedItemModifier("hide"),
                new ModifyAwardFishRlModifier(Starcatcher.rl("missingno"), "hide"),
                new OverrideFishPropertiesModifier(FishProperties.empty().withItemToOverrideWith(new MaybeStack(SCItems.UNKNOWN_FISH)), "hide"),
                new TriggersSkipMinigameModifier("hide")
        ), false);

        modifiers.add(SCItems.VANILLA_HOOK, List.of(
                new SkipMinigameIfTriggerFoundModifier("")
        ), false);

        modifiers.add(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE.builtInRegistryHolder(), List.of(
                new SurvivesLavaModifier(""),
                new ExtraGoldenRiskModifier(0.1f, true, "")
        ), false);


        //worms
        modifiers.add(SCItems.WORM, List.of(
                new AdjustLureTimeModifier(0.7f, 0.8f, 1.3f, "")
        ), false);

        modifiers.add(SCItems.ALMIGHTY_WORM, List.of(
                new AdjustLureTimeModifier(0.7f, 0.8f, 1.3f, ""),
                new ForceFishEntityModifier(1, "")
        ), false);

        modifiers.add(SCItems.SEEKING_WORM, List.of(
                new AdjustLureTimeModifier(0.7f, 0.8f, 1.3f, ""),
                new NewCatchIncreaseModifier(2, "")
        ), false);

        modifiers.add(SCItems.DEV_WORM, List.of(
                new AdjustLureTimeModifier(6f, 6f, 6f, "")
        ), false);

        //baits
        modifiers.add(SCItems.GUNPOWDER_BAIT, List.of(
                new AdjustLureTimeModifier(0.7f, 0.8f, 1.3f, "")
        ), false);

        modifiers.add(SCItems.CHERRY_BAIT, List.of(
                new AdjustLureTimeModifier(0.7f, 0.8f, 1.3f, "")
        ), false);

        modifiers.add(SCItems.LUSH_BAIT, List.of(
                new AdjustLureTimeModifier(0.7f, 0.8f, 1.3f, "")
        ), false);

        modifiers.add(SCItems.SCULK_BAIT, List.of(
                new AdjustLureTimeModifier(0.7f, 0.8f, 1.3f, "")
        ), false);

        modifiers.add(SCItems.DRIPSTONE_BAIT, List.of(
                new AdjustLureTimeModifier(0.7f, 0.8f, 1.3f, "")
        ), false);

        modifiers.add(SCItems.MURKWATER_BAIT, List.of(
                new AdjustLureTimeModifier(0.7f, 0.8f, 1.3f, "")
        ), false);

        modifiers.add(SCItems.LEGENDARY_BAIT, List.of(
                new AdjustLureTimeModifier(0.5f, 0.6f, 1.5f, "")
        ), false);

        modifiers.add(SCItems.METEOROLOGICAL_BAIT, List.of(
                new AdjustLureTimeModifier(0.7f, 0.8f, 1.3f, ""),
                new IgnoreDaytimeRestrictionsModifier(1f, ""),
                new IgnoreWeatherRestrictionsModifier(1f, "")
        ), false);


        //angler's hat (artifacts / reliquified artifacts compat)
        modifiers.add(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("artifacts", "anglers_hat")),
                List.of(
                        new AnglersHatModifier("")
                ), false);


        //hats
        modifiers.add(SCBlocks.FISHERMAN_HAT_BLACK.asItem().builtInRegistryHolder(),
                List.of(
                        new ExtraBaseCatchModifier(1, false, ""),
                        new HideCatchModifier(1, "")
                ), false);


        modifiers.add(SCBlocks.FISHERMAN_HAT_GRAY.asItem().builtInRegistryHolder(),
                List.of(
                        new ExtraBaseCatchModifier(3, true, ""),
                        new HideCatchModifier(1, "")
                ), false);

        modifiers.add(SCBlocks.FISHERMAN_HAT_LIGHT_GRAY.asItem().builtInRegistryHolder(),
                List.of(
                        new ExtraBaseCatchModifier(3, false, ""),
                        new CancelGoldenModifier(""),
                        new HideCatchModifier(1, "")
                ), false);


        modifiers.add(SCBlocks.FISHERMAN_HAT_GREEN.asItem().builtInRegistryHolder(),
                List.of(
                        new ExtraExpBasedOnPerformanceModifier("")
                ), false);


        modifiers.add(SCBlocks.FISHERMAN_HAT_LIME.asItem().builtInRegistryHolder(),
                List.of(
                        new AddToAvailablePoolModifier(
                                FishProperties.empty()
                                        .withEntityToSpawn(EntityType.CREEPER.builtInRegistryHolder())
                                        .withAlwaysSpawnEntity()
                                        .withDifficulty(Difficulty.CREEPER)
                                        .withSkipsMinigame()
                                , Starcatcher.rl("missingno"), 5, ""),

                        new AdjustLureTimeModifier(0.5f, 0.5f, 1, "")
                ), false);


        modifiers.add(SCBlocks.FISHERMAN_HAT_YELLOW.asItem().builtInRegistryHolder(),
                List.of(
                        new ExtraGoldenRiskModifier(0.05f, false, ""),
                        new AdjustLureTimeModifier(1.4f, 1.4f, 1.4f, "")
                ), false);


        modifiers.add(SCBlocks.FISHERMAN_HAT_RED.asItem().builtInRegistryHolder(),
                List.of(
                        new IgnoreDaytimeRestrictionsModifier(0.5f, ""),
                        new FlipEveryHitModifier(10, "")
                ), false);

        modifiers.add(SCBlocks.FISHERMAN_HAT_BLUE.asItem().builtInRegistryHolder(),
                List.of(
                        new IgnoreWeatherRestrictionsModifier(0.5f, ""),
                        new FlipEveryHitModifier(10, ""),
                        new ModifyBasePointerSpeedModifier(0.75f, "")
                ), false);

        modifiers.add(SCBlocks.FISHERMAN_HAT_LIGHT_BLUE.asItem().builtInRegistryHolder(),
                List.of(
                        new AdjustLureTimeModifier(1.4f, 1.4f, 1.4f, ""),
                        new AquaBobberModifier(-1, 1, ""),
                        new FreezeOnMissModifier(-1, "")
                ), false);

        modifiers.add(SCBlocks.FISHERMAN_HAT_ORANGE.asItem().builtInRegistryHolder(),
                List.of(
                        new FreezeOnMissModifier(-1, ""),
                        new ModifyBasePointerSpeedModifier(1.25f, "")
                ), false);


        //tackle skins
        tackleSkin.add(SCItems.PEARL_SMITHING_TEMPLATE, SCTackleSkins.PEARL_TACKLE_SKIN, false);
        tackleSkin.add(SCItems.KING_SMITHING_TEMPLATE, SCTackleSkins.KING_TACKLE_SKIN, false);
        tackleSkin.add(SCItems.COLORFUL_SMITHING_TEMPLATE, SCTackleSkins.COLORFUL_TACKLE_SKIN, false);
        tackleSkin.add(SCItems.CLEAR_SMITHING_TEMPLATE, SCTackleSkins.CLEAR_TACKLE_SKIN, false);
        tackleSkin.add(SCItems.FROG_SMITHING_TEMPLATE, SCTackleSkins.FROG_TACKLE_SKIN, false);
        tackleSkin.add(SCItems.KIMBE_SMITHING_TEMPLATE, SCTackleSkins.KIMBE_TACKLE_SKIN, false);
        tackleSkin.add(SCItems.KIMBE_SMITHING_TEMPLATE, SCTackleSkins.KIMBE_TACKLE_SKIN, false);

        Builder<Treasure.TreasureInstance, FishProperties> treasures = this.builder(SCDataMaps.TREASURE);

        //todo fix tags datagen to use tags instead of hard coding every entry
        //treasures.add(SCTags.COMMON_FISHES_FP, new Treasure.ItemStackListTreasureInstance(SCItems.AGAVE_BREAM.value().getDefaultInstance()), false);
        //treasures.add(SCTags.COMMON_FISHES_FP, new Treasure.ItemStackListTreasureInstance(SCItems.AGAVE_BREAM.value().getDefaultInstance()), false);
        //treasures.add(SCTags.COMMON_FISHES_FP, new Treasure.ItemStackListTreasureInstance(SCItems.AGAVE_BREAM.value().getDefaultInstance()), false);

        DGSCFishProperties.PROPERTIES.forEach((k, v) ->
        {
            String namespace = k.location().getNamespace();

            //This keeps it from spamming the log
            if (namespace.equals(Starcatcher.MOD_ID) || namespace.equals(ResourceLocation.DEFAULT_NAMESPACE))
                treasures.add(k, Treasure.VANILLA_FISHING_LOOT_TABLE, false);
            else
                treasures.add(k, Treasure.VANILLA_FISHING_LOOT_TABLE, false, new ModLoadedCondition(namespace));
        });

        treasures.add(Starcatcher.rl("azure_crystalback_minnow"), Treasure.AZURE_CRYSTAL_SKIN_SMITHING_TEMPLATE, false);
        treasures.add(Starcatcher.rl("willish"), Treasure.KIMBE_SMITHING_TEMPLATE, false);
        treasures.add(Starcatcher.rl("vesani"), Treasure.COLORFUL_SMITHING_TEMPLATE, false);
        treasures.add(Starcatcher.rl("boreal"), Treasure.CLEAR_SMITHING_TEMPLATE, false);
        treasures.add(Starcatcher.rl("cerberay"), Treasure.KING_SMITHING_TEMPLATE, false);
        treasures.add(Starcatcher.rl("aurora"), Treasure.ICEBORN_SKIN_SMITHING_TEMPLATE, false);


    }

    public static TagKey<FishProperties> fp(String s)
    {
        return TagKey.create(Starcatcher.FISH_REGISTRY_KEY, Starcatcher.rl(s));
    }
}
