package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.fishrestrictions.BaitRestriction;

import java.util.Map;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGTerraFirmaCraftFishes
{
    public static void bootstrap()
    {

        //
        //   ,--.
        // ,-'  '-.  ,---.  ,--.--. ,--.--.  ,--,--.
        // '-.  .-' | .-. : |  .--' |  .--' ' ,-.  |
        //   |  |   \   --. |  |    |  |    \ '-'  |
        //   `--'    `----' `--'    `--'     `--`--'
        //  ,---. ,--.
        // /  .-' `--' ,--.--. ,--,--,--.  ,--,--.
        // |  `-, ,--. |  .--' |        | ' ,-.  |
        // |  .-' |  | |  |    |  |  |  | \ '-'  |
        // `--'   `--' `--'    `--`--`--'  `--`--'
        //                          ,---.   ,--.
        //  ,---. ,--.--.  ,--,--. /  .-' ,-'  '-.
        // | .--' |  .--' ' ,-.  | |  `-, '-.  .-'
        // \ `--. |  |    \ '-'  | |  .-'   |  |
        //  `---' `--'     `--`--' `--'     `--'
        //


        register(overworldLakeFish(new MaybeStack("tfc", "food/bluegill"))
                .withBucketedFish(new MaybeStack("tfc", "bucket/bluegill"))
                .withEntityToSpawn(U.holderEntity("tfc", "bluegill"))
                .withSizeAndWeight(15, 5, 200, 20)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM)
        );

        register(overworldRiverFish(new MaybeStack("tfc", "food/lake_trout"))
                .withBucketedFish(new MaybeStack("tfc", "bucket/lake_trout"))
                .withEntityToSpawn(U.holderEntity("tfc", "lake_trout"))
                .withSizeAndWeight(15, 5, 200, 20)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM)
        );

        register(overworldLakeFish(new MaybeStack("tfc", "food/largemouth_bass"))
                .withBucketedFish(new MaybeStack("tfc", "bucket/bluegill"))
                .withEntityToSpawn(U.holderEntity("tfc", "bluegill"))
                .withSizeAndWeight(15, 5, 200, 20)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM)
        );

        register(overworldRiverFish(new MaybeStack("tfc", "food/rainbow_trout"))
                .withBucketedFish(new MaybeStack("tfc", "bucket/rainbow_trout"))
                .withEntityToSpawn(U.holderEntity("tfc", "rainbow_trout"))
                .withSizeAndWeight(15, 5, 200, 20)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM)
        );

        register(overworldLakeFish(new MaybeStack("tfc", "food/smallmouth_bass"))
                .withBucketedFish(new MaybeStack("tfc", "bucket/smallmouth_bass"))
                .withEntityToSpawn(U.holderEntity("tfc", "smallmouth_bass"))
                .withSizeAndWeight(15, 5, 200, 20)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM)
        );

        register(overworldLakeFish(new MaybeStack("tfc", "food/salmon"))
                .withBucketedFish(new MaybeStack("tfc", "bucket/salmon"))
                .withEntityToSpawn(U.holderEntity("tfc", "salmon"))
                .withSizeAndWeight(15, 5, 200, 20)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM)
        );

        register(overworldOceanFish(new MaybeStack("tfc", "food/cod"))
                .withBucketedFish(new MaybeStack("tfc", "bucket/cod"))
                .withEntityToSpawn(U.holderEntity("tfc", "cod"))
                .withSizeAndWeight(15, 5, 200, 20)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM)
        );


        //dolphin
        //todo change baits to use tag #tfc:large_fishing_bait and #tfc:small_fishing_bait
        register(overworldOceanFish(new MaybeStack("tfc", "blubber"))
                .withEntityToSpawn(U.holderEntity("tfc", "dolphin"))
                .withBaseChance(0)
                .withAlwaysSpawnEntity()
                .withSkipsMinigame()
                .addRestrictions(new BaitRestriction(Map.of(
                        U.rl("tfc", "food/bluegill"), 50,
                        U.rl("tfc", "food/cod"), 50,
                        U.rl("tfc", "food/salmon"), 50,
                        U.rl("tfc", "food/tropical_fish"), 50
                ), ""))
                .withSizeAndWeight(15, 5, 200, 20)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM)
                .withHasGuideEntry(false)
        );


        //trash
        register(overworldOceanFish(new MaybeStack("tfc", "food/fresh_seaweed"))
                .withBaseChance(1)
                .withSkipsMinigame()
                .withHasGuideEntry(false)
        );

        register(overworldRiverFish(new MaybeStack("tfc", "food/shellfish"))
                .withEntityToSpawn(U.holderEntity("tfc", "crayfish"))
                .withBaseChance(1)
                .withSkipsMinigame()
                .withHasGuideEntry(false)
        );

        register(overworldOceanFish(new MaybeStack("tfc", "groundcover/sea_urchin"))
                .withBaseChance(1)
                .withSkipsMinigame()
                .withHasGuideEntry(false)
        );

        register(overworldBeachFish(new MaybeStack("tfc", "groundcover/clam"))
                .withBaseChance(1)
                .withSkipsMinigame()
                .withHasGuideEntry(false)
        );

        register(overworldFish(new MaybeStack("tfc", "groundcover/driftwood"))
                .withBaseChance(1)
                .withSkipsMinigame()
                .withHasGuideEntry(false)
        );


    }
}
