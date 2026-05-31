package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;

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


        register(
                overworldBeachFish(new MaybeStack("crittersandcompanions", "clam"))
                .withSkipsMinigame()
        );

        register(overworldRiverFish(new MaybeStack("crittersandcompanions", "koi_fish"))
                .withBucketedFish(new MaybeStack("crittersandcompanions", "koi_fish_bucket"))
                .withEntityToSpawn(U.holderEntity("crittersandcompanions", "koi_fish"))
                .withSizeAndWeight(60, 20, 3000, 2000)
                .withDifficulty(Difficulty.MEDIUM)
                .withRarity(Rarity.COMMON)
        );

        register(overworldDeepOceanFish(new MaybeStack("crittersandcompanions", "dumbo_octopus_bucket"))
                .withAlwaysSpawnEntity()
                .withBucketedFish(new MaybeStack("crittersandcompanions", "dumbo_octopus_bucket"))
                .withEntityToSpawn(U.holderEntity("crittersandcompanions", "dumbo_octopus"))
                .withSizeAndWeight(30, 10, 1000, 300)
                .withDifficulty(Difficulty.MEDIUM_VANISHING)
                .withRarity(Rarity.UNCOMMON)
        );

        register(overworldDeepOceanFish(new MaybeStack("crittersandcompanions", "sea_bunny_bucket"))
                .withAlwaysSpawnEntity()
                .withBucketedFish(new MaybeStack("crittersandcompanions", "sea_bunny_bucket"))
                .withEntityToSpawn(U.holderEntity("crittersandcompanions", "sea_bunny"))
                .withSizeAndWeight(40, 10, 200, 60)
                .withDifficulty(Difficulty.HARD)
                .withRarity(Rarity.RARE)
        );

    }
}
