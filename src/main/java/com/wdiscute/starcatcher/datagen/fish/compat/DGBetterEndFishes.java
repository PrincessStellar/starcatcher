package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;

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

        register(endOuterIslandsFish(new MaybeStack("betterend", "end_fish_raw"))
                .withBucketedFish(new MaybeStack("betterend", "bucket_end_fish"))
                .withEntityToSpawn(U.holderEntity("betterend", "end_fish"))
                .withSizeAndWeight(80, 40, 2000, 400)
                .withRarity(Rarity.RARE)
        );

        register(endOuterIslandsFish(new MaybeStack("betterend", "gelatine"))
                .withBucketedFish(new MaybeStack("betterend", "bucket_cubozoa"))
                .withAlwaysSpawnEntity()
                .withEntityToSpawn(U.holderEntity("betterend", "cubozoa"))
                .withSizeAndWeight(70, 20, 200, 60)
                .withRarity(Rarity.EPIC)
        );
    }
}
