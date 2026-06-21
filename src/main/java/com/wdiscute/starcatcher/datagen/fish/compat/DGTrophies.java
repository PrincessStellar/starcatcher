package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.datagen.fish.FishRegistration;
import com.wdiscute.starcatcher.datagen.fish.PresetRestrictions;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.SCBlocks;
import com.wdiscute.starcatcher.registry.fishrestrictions.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;

public class DGTrophies
{
    public static void bootstrap(BootstrapContext<FishProperties> context)
    {

        FishRegistration.register(
                context,
                FishProperties.empty()
                        .withFish(SCBlocks.TROPHY_COPPER)
                        .withMaxLimit(1)
                        .withDifficulty(Difficulty.TRASH)
                        .withHasGuideEntry(false)
                        .withRarity(Rarity.COMMON)
                        .addRarityRestriction(
                                new RarityCountRestriction.RarityCount(Rarity.COMMON, 25, RarityCountRestriction.RarityCount.CountType.TOTAL)
                        )
                        .trophy()
        );

        FishRegistration.register(
                context,
                FishProperties.empty()
                        .withFish(SCBlocks.TROPHY_IRON)
                        .withMaxLimit(1)
                        .withDifficulty(Difficulty.TRASH)
                        .withRarity(Rarity.UNCOMMON)
                        .withHasGuideEntry(false)
                        .addRarityRestriction(
                                new RarityCountRestriction.RarityCount(Rarity.NONE, 35, RarityCountRestriction.RarityCount.CountType.UNIQUE)
                        )
                        .trophy()
        );

        FishRegistration.register(
                context,
                FishProperties.empty()
                        .withFish(SCBlocks.TROPHY_GOLD)
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

        FishRegistration.register(
                context,
                FishProperties.empty()
                        .withFish(SCBlocks.TROPHY_EMERALD)
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

        FishRegistration.register(
                context,
                FishProperties.empty()
                        .withFish(SCBlocks.TROPHY_DIAMOND)
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

        FishRegistration.register(
                context,
                FishProperties.empty()
                        .withFish(SCBlocks.TROPHY_OF_THE_OLDER_ANGLER)
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

        FishRegistration.register(
                context,
                PresetRestrictions.deepslate(context)
                        .withFish(SCItems.AMETHYST_HOOK)
                        .withMaxLimit(1)
                        .withDifficulty(Difficulty.TRASH)
                        .withHasGuideEntry(false)
                        .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.EPIC, 1, RarityCountRestriction.RarityCount.CountType.TOTAL))
                        .extra()
        );

        FishRegistration.register(
                context,
                PresetRestrictions.deepslate(context)
                        .withFish(Items.DIAMOND)
                        .withMaxLimit(1)
                        .withDifficulty(Difficulty.TRASH)
                        .withHasGuideEntry(false)
                        .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.GOLDEN, 1, RarityCountRestriction.RarityCount.CountType.TOTAL))
                        .extra()
        );

        FishRegistration.register(
                context,
                PresetRestrictions.netherLava(context)
                        .withFish(Items.NETHERITE_SCRAP)
                        .withMaxLimit(3)
                        .withDifficulty(Difficulty.TRASH)
                        .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.GOLDEN, 1, RarityCountRestriction.RarityCount.CountType.TOTAL))
                        .withHasGuideEntry(false)
                        .extra()
        );

        FishRegistration.register(
                context,
                PresetRestrictions.netherLava(context)
                        .withFish(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                        .withMaxLimit(1)
                        .withDifficulty(Difficulty.TRASH)
                        .withHasGuideEntry(false)
                        .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.GOLDEN, 1, RarityCountRestriction.RarityCount.CountType.TOTAL))
                        .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.LEGENDARY, 5, RarityCountRestriction.RarityCount.CountType.TOTAL))
                        .extra()
        );

        FishRegistration.register(
                context,
                PresetRestrictions.swamp(context)
                        .withFish(SCItems.FROG_SMITHING_TEMPLATE)
                        .withDifficulty(Difficulty.TRASH)
                        .withHasGuideEntry(false)
                        .withPercentageChance(0.01f)
                        .extra()
        );

        FishRegistration.register(
                context,
                PresetRestrictions.forest(context)
                        .withFish(SCItems.NATURALIST_SKIN_SMITHING_TEMPLATE)
                        .withDifficulty(Difficulty.TRASH)
                        .withHasGuideEntry(false)
                        .withPercentageChance(0.01f)
                        .extra()
        );

        FishRegistration.register(
                context,
                PresetRestrictions.netherLavaBasaltDeltas(context)
                        .withFish(SCItems.MAGMAFORGED_SKIN_SMITHING_TEMPLATE)
                        .withDifficulty(Difficulty.TRASH)
                        .withHasGuideEntry(false)
                        .withPercentageChance(0.01f)
                        .extra()
        );

        FishRegistration.register(
                context,
                PresetRestrictions.swamp(context)
                        .withFish(SCItems.SLIMED_SKIN_SMITHING_TEMPLATE)
                        .withDifficulty(Difficulty.TRASH)
                        .withHasGuideEntry(false)
                        .withPercentageChance(0.01f)
                        .extra()
        );

        FishRegistration.register(
                context,
                PresetRestrictions.bambooJungle(context)
                        .withFish(SCItems.BAMBOO_SKIN_SMITHING_TEMPLATE)
                        .withDifficulty(Difficulty.TRASH)
                        .withHasGuideEntry(false)
                        .withPercentageChance(0.01f)
                        .extra()
        );

        FishRegistration.register(
                context,
                PresetRestrictions.surfaceLava(context)
                        .withFish(SCItems.OBSIDIAN_SKIN_SMITHING_TEMPLATE)
                        .withWeather(WeatherRestriction.RAIN)
                        .withDifficulty(Difficulty.TRASH)
                        .withHasGuideEntry(false)
                        .withPercentageChance(0.01f)
                        .extra()
        );

        FishRegistration.register(
                context,
                PresetRestrictions.soulSandValley(context)
                        .withFish(SCItems.BONER_SKIN_SMITHING_TEMPLATE)
                        .withDifficulty(Difficulty.TRASH)
                        .withHasGuideEntry(false)
                        .withPercentageChance(0.01f)
                        .extra()
        );

        //                                         ,--.
        // ,---.   ,---.   ,---. ,--.--.  ,---.  ,-'  '-.  ,---.
        //(  .-'  | .-. : | .--' |  .--' | .-. : '-.  .-' (  .-'
        //.-'  `) \   --. \ `--. |  |    \   --.   |  |   .-'  `)
        //`----'   `----'  `---' `--'     `----'   `--'   `----'
        //

//        register(overworldSurfaceempty().withFish(SCItems.DRIFTING_WATERLOGGED_BOTTLE))
//                .withMaxLimit(1)
//                .withDifficulty(Difficulty.TRASH)
//                .withHasGuideEntry(false)
//                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.NONE, 10, RarityCountRestriction.RarityCount.CountType.TOTAL))
//                .secret()
//        );
//
//        register(overworldSurfaceLava().withFish(SCItems.SCALDING_BOTTLE))
//                .withMaxLimit(1)
//                .withDifficulty(Difficulty.TRASH)
//                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.LEGENDARY, 1, RarityCountRestriction.RarityCount.CountType.TOTAL))
//                .withHasGuideEntry(false)
//                .secret()
//        );
//
//        register(overworldSurfaceLava().withFish(SCItems.BURNING_BOTTLE))
//                .withMaxLimit(1)
//                .withDifficulty(Difficulty.TRASH)
//                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.LEGENDARY, 2, RarityCountRestriction.RarityCount.CountType.TOTAL))
//                .withHasGuideEntry(false)
//                .secret()
//        );
//
//        register(overworldSurfaceempty().withFish(SCItems.HOPEFUL_BOTTLE))
//                .withMaxLimit(1)
//                .withDifficulty(Difficulty.TRASH)
//                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.RARE, 10, RarityCountRestriction.RarityCount.CountType.TOTAL))
//                .withHasGuideEntry(false)
//                .secret()
//        );
//
//        register(overworldSurfaceempty().withFish(SCItems.HOPELESS_BOTTLE))
//                .withMaxLimit(1)
//                .withDifficulty(Difficulty.TRASH)
//                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.RARE, 15, RarityCountRestriction.RarityCount.CountType.TOTAL))
//                .withHasGuideEntry(false)
//                .secret()
//        );
//
//        register(overworldSurfaceempty().withFish(SCItems.TRUE_BLUE_BOTTLE))
//                .withMaxLimit(1)
//                .withDifficulty(Difficulty.TRASH)
//                .addRarityRestriction(new RarityCountRestriction.RarityCount(Rarity.EPIC, 10, RarityCountRestriction.RarityCount.CountType.TOTAL))
//                .withHasGuideEntry(false)
//                .secret()
//        );
//
//        register(empty().withFish(SCItems.WITHERED_BOTTLE))
//                .withMaxLimit(1)
//                .withDifficulty(Difficulty.TRASH)
//                .withHasGuideEntry(false)
//                .secret()
//                .withBaseChance(0)
//                .addRestrictions(new BaitRestriction(Map.of(U.rl("wither_skeleton_skull"), 200), "200"))
//        );

        //
        //          ,--.   ,--.
        // ,---.  ,-'  '-. |  ,---.   ,---.  ,--.--.  ,---.
        //| .-. | '-.  .-' |  .-.  | | .-. : |  .--' (  .-'
        //' '-' '   |  |   |  | |  | \   --. |  |    .-'  `)
        // `---'    `--'   `--' `--'  `----' `--'    `----'
        //

    }
}
