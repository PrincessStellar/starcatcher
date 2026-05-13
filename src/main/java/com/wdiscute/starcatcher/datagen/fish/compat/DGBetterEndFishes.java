package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.FishProperties;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGBetterEndFishes
{
    public static void bootstrap()
    {

        //
        //,--.              ,--.     ,--.                                           ,--.
        //|  |-.   ,---.  ,-'  '-. ,-'  '-.  ,---.  ,--.--.      ,---.  ,--,--,   ,-|  |
        //| .-. ' | .-. : '-.  .-' '-.  .-' | .-. : |  .--'     | .-. : |      \ ' .-. |
        //| `-' | \   --.   |  |     |  |   \   --. |  |        \   --. |  ||  | \ `-' |
        // `---'   `----'   `--'     `--'    `----' `--'         `----' `--''--'  `---'
        //

        register(endOuterIslandsFish(U.holderItem("betterend", "end_fish_raw"))
                .withBucketedFish(U.holderItem("betterend", "bucket_end_fish"))
                .withEntityToSpawn(U.holderEntity("betterend", "end_fish"))
                .withSizeAndWeight(FishProperties.sizeWeight(80, 40, 2000, 400))
                .withRarity(FishProperties.Rarity.RARE)
        );

        register(endOuterIslandsFish(U.holderItem("betterend", "gelatine"))
                .withBucketedFish(U.holderItem("betterend", "bucket_cubozoa"))
                .withAlwaysSpawnEntity(true)
                .withEntityToSpawn(U.holderEntity("betterend", "cubozoa"))
                .withSizeAndWeight(FishProperties.sizeWeight(70, 20, 200, 60))
                .withRarity(FishProperties.Rarity.EPIC)
        );
    }
}
