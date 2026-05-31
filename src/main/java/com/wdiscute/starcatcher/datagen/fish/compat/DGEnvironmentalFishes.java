package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.fishrestrictions.BiomeRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.DimensionRestriction;

import java.util.List;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGEnvironmentalFishes
{
    public static void bootstrap()
    {

        //
        //,------.                     ,--.                                                        ,--.            ,--.
        //|  .---' ,--,--,  ,--.  ,--. `--' ,--.--.  ,---.  ,--,--,  ,--,--,--.  ,---.  ,--,--,  ,-'  '-.  ,--,--. |  |
        //|  `--,  |      \  \  `'  /  ,--. |  .--' | .-. | |      \ |        | | .-. : |      \ '-.  .-' ' ,-.  | |  |
        //|  `---. |  ||  |   \    /   |  | |  |    ' '-' ' |  ||  | |  |  |  | \   --. |  ||  |   |  |   \ '-'  | |  |
        //`------' `--''--'    `--'    `--' `--'     `---'  `--''--' `--`--`--'  `----' `--''--'   `--'    `--`--' `--'
        //


        register(fish(new MaybeStack("environmental", "koi"))
                .withBucketedFish(new MaybeStack("environmental", "koi_bucket"))
                .withEntityToSpawn(U.holderEntity("environmental", "koi"))
                .withSizeAndWeight(60, 20, 3000, 2000)
                .withDifficulty(Difficulty.EASY_FAST_FISH)
                .withRarity(Rarity.UNCOMMON)
                .addRestrictions(
                        DimensionRestriction.OVERWORLD,
                        new BiomeRestriction(List.of(U.rl("environmental", "blossom_woods"), U.rl("environmental", "blossom_valleys")),
                                List.of(), List.of(), List.of(), "")
                )
        );

        register(fish(new MaybeStack("environmental", "slabfish_bucket"))
                .withAlwaysSpawnEntity()
                .withBucketedFish(new MaybeStack("environmental", "slabfish_bucket"))
                .withEntityToSpawn(U.holderEntity("environmental", "slabfish"))
                .withSizeAndWeight(120, 40, 20000, 10000)
                .withDifficulty(Difficulty.HARD)
                .withRarity(Rarity.UNCOMMON)
                .addRestrictions(
                        DimensionRestriction.OVERWORLD,
                        new BiomeRestriction(List.of(U.rl("environmental", "marsh")),
                                List.of(), List.of(), List.of(), "")
                )
        );

    }
}
