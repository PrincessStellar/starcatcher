package com.wdiscute.starcatcher.registry.fishing.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.blocks.SCBlocks;
import com.wdiscute.starcatcher.registry.fishrestrictions.BaitRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.DimensionRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.ElevationRestriction;
import com.wdiscute.starcatcher.registry.FishProperties;
import com.wdiscute.starcatcher.registry.fishrestrictions.RarityCountRestriction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;

import java.util.Map;

import static com.wdiscute.starcatcher.registry.fishing.FishingPropertiesRegistry.*;

public class DGTrophies
{
    public static void bootstrap()
    {
        register(fish(SCBlocks.TROPHY_COPPER.asItem().builtInRegistryHolder())
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .addRarityRestriction(
                        new RarityCountRestriction.RarityCount(FishProperties.Rarity.COMMON, 25, RarityCountRestriction.RarityCount.CountType.UNIQUE)
                )
                .trophy()
        );

        register(fish(SCBlocks.TROPHY_IRON.asItem().builtInRegistryHolder())
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .addRarityRestriction(
                        new RarityCountRestriction.RarityCount(FishProperties.Rarity.RARE, 67, RarityCountRestriction.RarityCount.CountType.TOTAL)
                )
                .trophy()
        );

        register(fish(SCBlocks.TROPHY_GOLD.asItem().builtInRegistryHolder())
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .addRarityRestriction(
                        new RarityCountRestriction.RarityCount(FishProperties.Rarity.NONE, 25, RarityCountRestriction.RarityCount.CountType.TOTAL)
                )
                .trophy()
        );

        register(fish(SCBlocks.TROPHY_EMERALD.asItem().builtInRegistryHolder())
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .addRarityRestriction(
                        new RarityCountRestriction.RarityCount(FishProperties.Rarity.NONE, 25, RarityCountRestriction.RarityCount.CountType.TOTAL)
                )
                .trophy()
        );

        register(fish(SCBlocks.TROPHY_DIAMOND.asItem().builtInRegistryHolder())
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .addRarityRestriction(
                        new RarityCountRestriction.RarityCount(FishProperties.Rarity.NONE, 25, RarityCountRestriction.RarityCount.CountType.TOTAL)
                )
                .trophy()
        );


        //                                         ,--.
        // ,---.   ,---.   ,---. ,--.--.  ,---.  ,-'  '-.  ,---.
        //(  .-'  | .-. : | .--' |  .--' | .-. : '-.  .-' (  .-'
        //.-'  `) \   --. \ `--. |  |    \   --.   |  |   .-'  `)
        //`----'   `----'  `---' `--'     `----'   `--'   `----'
        //

        register(overworldSurfaceFish(SCItems.DRIFTING_WATERLOGGED_BOTTLE)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .secret()
        );

        register(overworldSurfaceLava(SCItems.SCALDING_BOTTLE)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .secret()
        );

        register(overworldSurfaceLava(SCItems.BURNING_BOTTLE)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .secret()
        );

        register(overworldSurfaceFish(SCItems.HOPEFUL_BOTTLE)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .secret()
        );

        register(overworldSurfaceFish(SCItems.HOPELESS_BOTTLE)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .secret()
        );

        register(overworldSurfaceFish(SCItems.TRUE_BLUE_BOTTLE)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .secret()
        );

        register(fish(SCItems.WITHERED_BOTTLE)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .secret()
                .withBaseChance(0)
                .addRestrictions(new BaitRestriction(Map.of(U.rl("wither_skeleton_skull"), 200), "200"))
        );

        register(overworldDeepslateFish(SCItems.CRYSTAL_HOOK)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .secret()
        );

        //
        //          ,--.   ,--.
        // ,---.  ,-'  '-. |  ,---.   ,---.  ,--.--.  ,---.
        //| .-. | '-.  .-' |  .-.  | | .-. : |  .--' (  .-'
        //' '-' '   |  |   |  | |  | \   --. |  |    .-'  `)
        // `---'    `--'   `--' `--'  `----' `--'    `----'
        //

        register(overworldDeepslateFish(BuiltInRegistries.ITEM.wrapAsHolder(Items.DIAMOND))
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .extra()
        );

        register(netherLavaFish(BuiltInRegistries.ITEM.wrapAsHolder(Items.NETHERITE_SCRAP))
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .extra()
        );

        register(netherLavaFish(BuiltInRegistries.ITEM.wrapAsHolder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .extra()
        );

        //neptunium ingot
        register(overworldOceanFish(U.holderItem("aquaculture", "neptunium_ingot"))
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .extra()
        );

        //naturalist
        register(overworldForestFish(SCItems.NATURALIST_SKIN_SMITHING_TEMPLATE)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .extra()
        );

        //magma forged
        register(netherLavaBasaltDeltasFish(SCItems.MAGMAFORGED_SKIN_SMITHING_TEMPLATE)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .extra()
        );

        //bamboo rod
        register(overworldBambooJungleFish(SCItems.BAMBOO_SKIN_SMITHING_TEMPLATE)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .extra()
        );

        //obsidian rod
        register(overworldSurfaceLava(SCItems.OBSIDIAN_SKIN_SMITHING_TEMPLATE)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .extra()
        );

        //boner rod
        register(netherLavaSoulSandValleyFish(SCItems.BONER_SKIN_SMITHING_TEMPLATE)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .extra()
        );

        //sky rod
        register(overworldFish(SCItems.SKY_ROD)
                .addRestrictions(
                        DimensionRestriction.OVERWORLD,
                        ElevationRestriction.ABOVE_TWO_HUNDRED)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .extra()
        );

        //lush glowberry
        register(overworldLushCavesFish(SCItems.LUSH_GLOWBERRY_SKIN_SMITHING_TEMPLATE)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .extra()
        );

        //humble rod
        register(overworldRiverFish(SCItems.HUMBLE_SKIN_SMITHING_TEMPLATE)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .extra()
        );

        //shark rod
        register(overworldRiverFish(SCItems.SHARKTOOTH_SKIN_SMITHING_TEMPLATE)
                .withMaxLimit(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false)
                .extra()
        );


    }
}
