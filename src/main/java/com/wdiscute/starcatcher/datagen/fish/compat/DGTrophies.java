package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.SCBlocks;
import com.wdiscute.starcatcher.registry.fishrestrictions.*;
import com.wdiscute.starcatcher.fish.FishProperties;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;

import java.util.List;
import java.util.Map;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGTrophies
{
    public static void bootstrap()
    {

        register(fish(new MaybeStack(SCBlocks.TROPHY_COPPER))
                .withMaxLimit(1)
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false)
                .withRarity(Rarity.COMMON)
                .addRarityRestriction(
                        new RarityCountRestriction.RarityCount(Rarity.COMMON, 25, RarityCountRestriction.RarityCount.CountType.TOTAL)
                )
                .trophy()
        );

        register(fish(new MaybeStack(SCBlocks.TROPHY_IRON))
                .withMaxLimit(1)
                .withDifficulty(Difficulty.TRASH)
                .withRarity(Rarity.UNCOMMON)
                .withHasGuideEntry(false)
                .addRarityRestriction(
                        new RarityCountRestriction.RarityCount(Rarity.NONE, 35, RarityCountRestriction.RarityCount.CountType.UNIQUE)
                )
                .trophy()
        );

        register(fish(new MaybeStack(SCBlocks.TROPHY_GOLD))
                .withMaxLimit(1)
                .withDifficulty(Difficulty.TRASH)
                .withRarity(Rarity.RARE)
                .withHasGuideEntry(false)
                .addRarityRestriction(
                        new RarityCountRestriction.RarityCount(Rarity.NONE, 100, RarityCountRestriction.RarityCount.CountType.TOTAL),
                        new RarityCountRestriction.RarityCount(Rarity.LEGENDARY, 2, RarityCountRestriction.RarityCount.CountType.UNIQUE),
                        new RarityCountRestriction.RarityCount(Rarity.GOLDEN, 2, RarityCountRestriction.RarityCount.CountType.UNIQUE)
                )
                .trophy()
        );

        register(fish(new MaybeStack(SCBlocks.TROPHY_GOLD))
                .withMaxLimit(1)
                .withDifficulty(Difficulty.TRASH)
                .withRarity(Rarity.EPIC)
                .withHasGuideEntry(false)
                .addRarityRestriction(
                        new RarityCountRestriction.RarityCount(Rarity.NONE, 200, RarityCountRestriction.RarityCount.CountType.TOTAL),
                        new RarityCountRestriction.RarityCount(Rarity.LEGENDARY, 10, RarityCountRestriction.RarityCount.CountType.UNIQUE)
                )
                .trophy()
        );

        register(fish(new MaybeStack(SCBlocks.TROPHY_DIAMOND))
                .withMaxLimit(1)
                .withDifficulty(Difficulty.TRASH)
                .withRarity(Rarity.LEGENDARY)
                .withHasGuideEntry(false)
                .addRarityRestriction(
                        new RarityCountRestriction.RarityCount(Rarity.NONE, 500, RarityCountRestriction.RarityCount.CountType.TOTAL),
                        new RarityCountRestriction.RarityCount(Rarity.NONE, 0, RarityCountRestriction.RarityCount.CountType.ALL)
                )
                .trophy()
        );

        register(fish(new MaybeStack(SCBlocks.TROPHY_OF_THE_OLDER_ANGLER))
                .withMaxLimit(1)
                .withDifficulty(Difficulty.TRASH)
                .withRarity(Rarity.LEGENDARY)
                .withHasGuideEntry(false)
                .addRarityRestriction(
                        new RarityCountRestriction.RarityCount(Rarity.NONE, 0, RarityCountRestriction.RarityCount.CountType.ALL),
                        new RarityCountRestriction.RarityCount(Rarity.GOLDEN, 0, RarityCountRestriction.RarityCount.CountType.ALL)
                )
                .trophy()
        );


        //                                         ,--.
        // ,---.   ,---.   ,---. ,--.--.  ,---.  ,-'  '-.  ,---.
        //(  .-'  | .-. : | .--' |  .--' | .-. : '-.  .-' (  .-'
        //.-'  `) \   --. \ `--. |  |    \   --.   |  |   .-'  `)
        //`----'   `----'  `---' `--'     `----'   `--'   `----'
        //

        register(overworldSurfaceFish(new MaybeStack(SCItems.DRIFTING_WATERLOGGED_BOTTLE))
                .withMaxLimit(1)
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false)
                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.NONE, 10, RarityCountRestriction.RarityCount.CountType.TOTAL))
                .secret()
        );

        register(overworldSurfaceLava(new MaybeStack(SCItems.SCALDING_BOTTLE))
                .withMaxLimit(1)
                .withDifficulty(Difficulty.TRASH)
                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.LEGENDARY, 1, RarityCountRestriction.RarityCount.CountType.TOTAL))
                .withHasGuideEntry(false)
                .secret()
        );

        register(overworldSurfaceLava(new MaybeStack(SCItems.BURNING_BOTTLE))
                .withMaxLimit(1)
                .withDifficulty(Difficulty.TRASH)
                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.LEGENDARY, 2, RarityCountRestriction.RarityCount.CountType.TOTAL))
                .withHasGuideEntry(false)
                .secret()
        );

        register(overworldSurfaceFish(new MaybeStack(SCItems.HOPEFUL_BOTTLE))
                .withMaxLimit(1)
                .withDifficulty(Difficulty.TRASH)
                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.RARE, 10, RarityCountRestriction.RarityCount.CountType.TOTAL))
                .withHasGuideEntry(false)
                .secret()
        );

        register(overworldSurfaceFish(new MaybeStack(SCItems.HOPELESS_BOTTLE))
                .withMaxLimit(1)
                .withDifficulty(Difficulty.TRASH)
                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.RARE, 15, RarityCountRestriction.RarityCount.CountType.TOTAL))
                .withHasGuideEntry(false)
                .secret()
        );

        register(overworldSurfaceFish(new MaybeStack(SCItems.TRUE_BLUE_BOTTLE))
                .withMaxLimit(1)
                .withDifficulty(Difficulty.TRASH)
                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.EPIC, 10, RarityCountRestriction.RarityCount.CountType.TOTAL))
                .withHasGuideEntry(false)
                .secret()
        );

        register(fish(new MaybeStack(SCItems.WITHERED_BOTTLE))
                .withMaxLimit(1)
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false)
                .secret()
                .withBaseChance(0)
                .addRestrictions(new BaitRestriction(Map.of(U.rl("wither_skeleton_skull"), 200), "200"))
        );

        //
        //          ,--.   ,--.
        // ,---.  ,-'  '-. |  ,---.   ,---.  ,--.--.  ,---.
        //| .-. | '-.  .-' |  .-.  | | .-. : |  .--' (  .-'
        //' '-' '   |  |   |  | |  | \   --. |  |    .-'  `)
        // `---'    `--'   `--' `--'  `----' `--'    `----'
        //

        register(overworldDeepslateFish(new MaybeStack(SCItems.AMETHYST_HOOK))
                .withMaxLimit(1)
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false)
                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.EPIC, 1, RarityCountRestriction.RarityCount.CountType.TOTAL))
                .extra()
        );

        register(overworldDeepslateFish(new MaybeStack(Items.DIAMOND))
                .withMaxLimit(1)
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false)
                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.GOLDEN, 1, RarityCountRestriction.RarityCount.CountType.TOTAL))
                .extra()
        );

        register(netherLavaFish(new MaybeStack(Items.NETHERITE_SCRAP))
                .withMaxLimit(3)
                .withDifficulty(Difficulty.TRASH)
                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.GOLDEN, 1, RarityCountRestriction.RarityCount.CountType.TOTAL))
                .withHasGuideEntry(false)
                .extra()
        );

        register(netherLavaFish(new MaybeStack(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .withMaxLimit(1)
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false)
                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.GOLDEN, 1, RarityCountRestriction.RarityCount.CountType.TOTAL))
                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.LEGENDARY, 5, RarityCountRestriction.RarityCount.CountType.TOTAL))
                .extra()
        );

        //frog
        register(overworldSwampFish(new MaybeStack(SCItems.FROG_SMITHING_TEMPLATE))
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false)
                .withPercentageChance(0.01f)
                .extra()
        );

        //naturalist
        register(overworldForestFish(new MaybeStack(SCItems.NATURALIST_SKIN_SMITHING_TEMPLATE))
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false)
                .withPercentageChance(0.01f)
                .extra()
        );

        //magma forged
        register(netherLavaBasaltDeltasFish(new MaybeStack(SCItems.MAGMAFORGED_SKIN_SMITHING_TEMPLATE))
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false)
                .withPercentageChance(0.01f)
                .extra()
        );

        //magma forged
        register(overworldSwampFish(new MaybeStack(SCItems.SLIMED_SKIN_SMITHING_TEMPLATE))
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false)
                .withPercentageChance(0.01f)
                .extra()
        );

        //bamboo rod
        register(overworldBambooJungleFish(new MaybeStack(SCItems.BAMBOO_SKIN_SMITHING_TEMPLATE))
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false)
                .withPercentageChance(0.01f)
                .extra()
        );

        //obsidian rod
        register(overworldSurfaceLava(new MaybeStack(SCItems.OBSIDIAN_SKIN_SMITHING_TEMPLATE))
                .withWeather(WeatherRestriction.RAIN)
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false)
                .withPercentageChance(0.01f)
                .extra()
        );

        //boner rod
        register(netherLavaSoulSandValleyFish(new MaybeStack(SCItems.BONER_SKIN_SMITHING_TEMPLATE))
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false)
                .withPercentageChance(0.01f)
                .extra()
        );

        //sky rod
        register(overworldFish(new MaybeStack(SCItems.SKY_SKIN_SMITHING_TEMPLATE))
                .addRestrictions(
                        DimensionRestriction.OVERWORLD,
                        ElevationRestriction.ABOVE_TWO_HUNDRED)
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false)
                .withPercentageChance(0.01f)
                .extra()
        );

        //lush glowberry
        register(overworldLushCavesFish(new MaybeStack(SCItems.LUSH_GLOWBERRY_SKIN_SMITHING_TEMPLATE))
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false)
                .withPercentageChance(0.01f)
                .extra()
        );

        //humble rod
        register(overworldRiverFish(new MaybeStack(SCItems.HUMBLE_SKIN_SMITHING_TEMPLATE))
                .withDifficulty(Difficulty.TRASH)
                .addRestriction(new StructureRestriction(
                        List.of(
                                BuiltinStructures.VILLAGE_DESERT.location(),
                                BuiltinStructures.VILLAGE_PLAINS.location(),
                                BuiltinStructures.VILLAGE_SAVANNA.location(),
                                BuiltinStructures.VILLAGE_SNOWY.location(),
                                BuiltinStructures.VILLAGE_TAIGA.location()
                        ), ""))
                .withHasGuideEntry(false)
                .withPercentageChance(0.01f)
                .extra()
        );

        //shark rod
        register(overworldRiverFish(new MaybeStack(SCItems.SHARKTOOTH_SKIN_SMITHING_TEMPLATE))
                .withDifficulty(Difficulty.TRASH)
                .addRestriction(new StructureRestriction(
                        List.of(
                                BuiltinStructures.SHIPWRECK.location(),
                                BuiltinStructures.SHIPWRECK_BEACHED.location()
                        ), ""))
                .withHasGuideEntry(false)
                .withPercentageChance(0.01f)
                .extra()
        );


    }
}
