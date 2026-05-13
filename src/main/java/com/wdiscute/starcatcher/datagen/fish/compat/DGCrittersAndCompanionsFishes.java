package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.FishProperties;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGCrittersAndCompanionsFishes
{


    public static void bootstrap()
    {

        //
        // ,-----.         ,--.   ,--.     ,--.                                                    ,--.      ,-----.                                              ,--.
        //'  .--./ ,--.--. `--' ,-'  '-. ,-'  '-.  ,---.  ,--.--.  ,---.       ,--,--. ,--,--,   ,-|  |     '  .--./  ,---.  ,--,--,--.  ,---.   ,--,--. ,--,--,  `--'  ,---.  ,--,--,   ,---.
        //|  |     |  .--' ,--. '-.  .-' '-.  .-' | .-. : |  .--' (  .-'      ' ,-.  | |      \ ' .-. |     |  |     | .-. | |        | | .-. | ' ,-.  | |      \ ,--. | .-. | |      \ (  .-'
        //'  '--'\ |  |    |  |   |  |     |  |   \   --. |  |    .-'  `)     \ '-'  | |  ||  | \ `-' |     '  '--'\ ' '-' ' |  |  |  | | '-' ' \ '-'  | |  ||  | |  | ' '-' ' |  ||  | .-'  `)
        // `-----' `--'    `--'   `--'     `--'    `----' `--'    `----'       `--`--' `--''--'  `---'       `-----'  `---'  `--`--`--' |  |-'   `--`--' `--''--' `--'  `---'  `--''--' `----'
        //                                                                                                                              `--'


        register(overworldBeachFish(U.holderItem("crittersandcompanions", "clam"))
                .withSkipMinigame(true)
        );

        register(overworldRiverFish(U.holderItem("crittersandcompanions", "koi_fish"))
                .withBucketedFish(U.holderItem("crittersandcompanions", "koi_fish_bucket"))
                .withEntityToSpawn(U.holderEntity("crittersandcompanions", "koi_fish"))
                .withSizeAndWeight(FishProperties.sizeWeight(60, 20, 3000, 2000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withRarity(FishProperties.Rarity.COMMON)
        );

        register(overworldDeepOceanFish(U.holderItem("crittersandcompanions", "dumbo_octopus_bucket"))
                .withAlwaysSpawnEntity(true)
                .withBucketedFish(U.holderItem("crittersandcompanions", "dumbo_octopus_bucket"))
                .withEntityToSpawn(U.holderEntity("crittersandcompanions", "dumbo_octopus"))
                .withSizeAndWeight(FishProperties.sizeWeight(30, 10, 1000, 300))
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        register(overworldDeepOceanFish(U.holderItem("crittersandcompanions", "sea_bunny_bucket"))
                .withAlwaysSpawnEntity(true)
                .withBucketedFish(U.holderItem("crittersandcompanions", "sea_bunny_bucket"))
                .withEntityToSpawn(U.holderEntity("crittersandcompanions", "sea_bunny"))
                .withSizeAndWeight(FishProperties.sizeWeight(40, 10, 200, 60))
                .withDifficulty(FishProperties.Difficulty.HARD)
                .withRarity(FishProperties.Rarity.RARE)
        );

    }
}
