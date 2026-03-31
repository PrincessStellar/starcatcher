package com.wdiscute.starcatcher.registry.fishing.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.blocks.SCBlocks;
import com.wdiscute.starcatcher.registry.fishrestrictions.BaitRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.DimensionRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.ElevationRestriction;
import com.wdiscute.starcatcher.registry.FishProperties;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;

import java.util.Map;

import static com.wdiscute.starcatcher.registry.fishing.FishingPropertiesRegistry.*;

public class DGTrophies
{
    public static void bootstrap()
    {


//        registerTrophy(TrophyProperties.builder()
//                .setFish(SCBlocks.TROPHY_BRONZE.asItem().builtInRegistryHolder())
//                .hideUntilCaught()
//                .setTrophyType(TrophyProperties.TrophyType.TROPHY)
//                .setAllProgress(new TrophyProperties.RarityProgress(50, 20))
//        );
//
//        registerTrophy(TrophyProperties.builder()
//                .setFish(SCBlocks.TROPHY_SILVER.asItem().builtInRegistryHolder())
//                .hideUntilCaught()
//                .setTrophyType(TrophyProperties.TrophyType.TROPHY)
//                .setAllProgress(new TrophyProperties.RarityProgress(100, 50))
//        );
//
//        registerTrophy(TrophyProperties.builder()
//                .setFish(SCBlocks.TROPHY_GOLD.asItem().builtInRegistryHolder())
//                .hideUntilCaught()
//                .setTrophyType(TrophyProperties.TrophyType.TROPHY)
//                .setAllProgress(new TrophyProperties.RarityProgress(200, 0))
//                .withProgress(FishProperties.Rarity.COMMON, new TrophyProperties.RarityProgress(0, 44))
//                .withProgress(FishProperties.Rarity.UNCOMMON, new TrophyProperties.RarityProgress(0, 23))
//                .withProgress(FishProperties.Rarity.RARE, new TrophyProperties.RarityProgress(0, 15))
//                .withProgress(FishProperties.Rarity.EPIC, new TrophyProperties.RarityProgress(0, 16))
//                .withProgress(FishProperties.Rarity.LEGENDARY, new TrophyProperties.RarityProgress(0, 10))
//        );
//        //                                         ,--.
//        // ,---.   ,---.   ,---. ,--.--.  ,---.  ,-'  '-.  ,---.
//        //(  .-'  | .-. : | .--' |  .--' | .-. : '-.  .-' (  .-'
//        //.-'  `) \   --. \ `--. |  |    \   --.   |  |   .-'  `)
//        //`----'   `----'  `---' `--'     `----'   `--'   `----'
//        //
//
//        registerTrophy(TrophyProperties.builder()
//                .withFP(overworldFish(SCItems.DRIFTING_WATERLOGGED_BOTTLE))
//                .setTrophyType(TrophyProperties.TrophyType.SECRET)
//                .setAllProgress(new TrophyProperties.RarityProgress(6, 15))
//        );
//
//        registerTrophy(TrophyProperties.builder()
//                .withFP(overworldSurfaceLava(SCItems.SCALDING_BOTTLE))
//                .setTrophyType(TrophyProperties.TrophyType.SECRET)
//                .setAllProgress(new TrophyProperties.RarityProgress(0, 27))
//                .withChanceToCatch(33)
//        );
//
//        registerTrophy(TrophyProperties.builder()
//                .withFP(overworldSurfaceLava(SCItems.BURNING_BOTTLE))
//                .setTrophyType(TrophyProperties.TrophyType.SECRET)
//                .setAllProgress(new TrophyProperties.RarityProgress(0, 42))
//                .withChanceToCatch(33)
//        );
//
//        registerTrophy(TrophyProperties.builder()
//                .withFP(overworldDeepOceanFish(SCItems.HOPEFUL_BOTTLE))
//                .setTrophyType(TrophyProperties.TrophyType.SECRET)
//                .withProgress(FishProperties.Rarity.EPIC, new TrophyProperties.RarityProgress(5, 0))
//                .withChanceToCatch(33)
//        );
//
//        registerTrophy(TrophyProperties.builder()
//                .withFP(overworldDeepOceanFish(SCItems.HOPELESS_BOTTLE))
//                .setTrophyType(TrophyProperties.TrophyType.SECRET)
//                .withProgress(FishProperties.Rarity.EPIC, new TrophyProperties.RarityProgress(5, 0))
//                .withChanceToCatch(33)
//        );
//
//        registerTrophy(TrophyProperties.builder()
//                .withFP(overworldRiverFish(SCItems.TRUE_BLUE_BOTTLE))
//                .setTrophyType(TrophyProperties.TrophyType.SECRET)
//                .withProgress(FishProperties.Rarity.LEGENDARY, new TrophyProperties.RarityProgress(1, 0))
//                .withChanceToCatch(1)
//        );
//
//        registerTrophy(TrophyProperties.builder()
//                .withFP(
//                        fish(SCItems.WITHERED_BOTTLE)
//                                .withBaseChance(0)
//                                .addRestrictions(new BaitRestriction(Map.of(U.rl("wither_skeleton_skull"), 200), "200")))
//                .setTrophyType(TrophyProperties.TrophyType.SECRET)
//        );
//
//        registerTrophy(TrophyProperties.builder()
//                .withFP(overworldDeepslateFish(SCItems.CRYSTAL_HOOK))
//                .setTrophyType(TrophyProperties.TrophyType.SECRET)
//                .withProgress(FishProperties.Rarity.EPIC, new TrophyProperties.RarityProgress(1, 0))
//        );
//
//        //
//        //          ,--.   ,--.
//        // ,---.  ,-'  '-. |  ,---.   ,---.  ,--.--.  ,---.
//        //| .-. | '-.  .-' |  .-.  | | .-. : |  .--' (  .-'
//        //' '-' '   |  |   |  | |  | \   --. |  |    .-'  `)
//        // `---'    `--'   `--' `--'  `----' `--'    `----'
//        //
//
//        registerTrophy(TrophyProperties.builder().withFP(
//                        overworldDeepslateFish(BuiltInRegistries.ITEM.wrapAsHolder(Items.DIAMOND)))
//                .setTrophyType(TrophyProperties.TrophyType.EXTRA)
//                .withProgress(FishProperties.Rarity.RARE, new TrophyProperties.RarityProgress(1, 4))
//        );
//
//        registerTrophy(TrophyProperties.builder().withFP(
//                        netherLavaFish(BuiltInRegistries.ITEM.wrapAsHolder(Items.GOLD_BLOCK)))
//                .setTrophyType(TrophyProperties.TrophyType.EXTRA)
//                .withProgress(FishProperties.Rarity.LEGENDARY, new TrophyProperties.RarityProgress(3, 0))
//                .withChanceToCatch(33)
//        );
//
//        registerTrophy(TrophyProperties.builder().withFP(
//                        netherLavaFish(BuiltInRegistries.ITEM.wrapAsHolder(Items.NETHERITE_SCRAP)))
//                .setTrophyType(TrophyProperties.TrophyType.EXTRA)
//                .withProgress(FishProperties.Rarity.LEGENDARY, new TrophyProperties.RarityProgress(0, 10))
//                .withChanceToCatch(5)
//                .withRepeatable(true)
//        );
//
//        registerTrophy(TrophyProperties.builder().withFP(
//                        netherLavaFish(BuiltInRegistries.ITEM.wrapAsHolder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)))
//                .setTrophyType(TrophyProperties.TrophyType.EXTRA)
//                .withChanceToCatch(1)
//                .withRepeatable(true)
//        );
//
//
//        //naturalist
//        registerTrophy(TrophyProperties.builder().withFP(
//                        overworldForestFish(SCItems.NATURALIST_ROD))
//                .setTrophyType(TrophyProperties.TrophyType.EXTRA)
//                .withChanceToCatch(1)
//                .withRepeatable(true)
//        );
//
//        //magma forged
//        registerTrophy(TrophyProperties.builder().withFP(
//                        netherLavaBasaltDeltasFish(SCItems.MAGMAFORGED_ROD))
//                .setTrophyType(TrophyProperties.TrophyType.EXTRA)
//                .withChanceToCatch(1)
//                .withRepeatable(true)
//        );
//
//        //bamboo rod
//        registerTrophy(TrophyProperties.builder().withFP(
//                        overworldFish(SCItems.BAMBOO_ROD)
//                                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_BAMBOO_JUNGLE))
//                .setTrophyType(TrophyProperties.TrophyType.EXTRA)
//                .withChanceToCatch(1)
//                .withRepeatable(true)
//        );
//
//        //bamboo rod
//        registerTrophy(TrophyProperties.builder().withFP(
//                        overworldSurfaceLava(SCItems.OBSIDIAN_ROD))
//                .setTrophyType(TrophyProperties.TrophyType.EXTRA)
//                .withChanceToCatch(1)
//                .withRepeatable(true)
//        );
//
//        //boner rod
//        registerTrophy(TrophyProperties.builder().withFP(
//                        netherLavaSoulSandValleyFish(SCItems.BONER_ROD))
//                .setTrophyType(TrophyProperties.TrophyType.EXTRA)
//                .withChanceToCatch(1)
//                .withRepeatable(true)
//        );
//
//        //sky rod
//        registerTrophy(TrophyProperties.builder().withFP(
//                        overworldFish(SCItems.SKY_ROD)
//                                .addRestrictions(
//                                        DimensionRestriction.OVERWORLD,
//                                        ElevationRestriction.ABOVE_TWO_HUNDRED))
//                .setTrophyType(TrophyProperties.TrophyType.EXTRA)
//                .withChanceToCatch(1)
//                .withRepeatable(true)
//        );
//
//        //lush glowberry
//        registerTrophy(TrophyProperties.builder().withFP(
//                        overworldLushCavesFish(SCItems.LUSH_GLOWBERRY_ROD))
//                .setTrophyType(TrophyProperties.TrophyType.EXTRA)
//                .withChanceToCatch(1)
//                .withRepeatable(true)
//        );
//
//        //humble rod
//        registerTrophy(TrophyProperties.builder().withFP(
//                        overworldRiverFish(SCItems.HUMBLE_ROD))
//                .setTrophyType(TrophyProperties.TrophyType.EXTRA)
//                .withChanceToCatch(1)
//                .withRepeatable(true)
//        );
//
//        //neptunium ingot
//        registerTrophy(TrophyProperties.builder().withFP(
//                        overworldOceanFish(U.holderItem("aquaculture", "neptunium_ingot")))
//                .setTrophyType(TrophyProperties.TrophyType.EXTRA)
//                .withChanceToCatch(2)
//                .withRepeatable(true)
//        );
//
//
    }
}
