package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.fish.WorldRestrictions;
import com.wdiscute.starcatcher.registry.fishrestrictions.DaytimeRestriction;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGSpawnFishes
{
    public static void bootstrap()
    {

        //
        // ,---.
        //'   .-'   ,---.   ,--,--. ,--.   ,--. ,--,--,
        //`.  `-.  | .-. | ' ,-.  | |  |.'.|  | |      \
        //.-'    | | '-' ' \ '-'  | |   .'.   | |  ||  |
        //`-----'  |  |-'   `--`--' '--'   '--' `--''--'
        //         `--'

        register(fish(new MaybeStack("spawn", "angler_fish"))
                .withBucketedFish(new MaybeStack("spawn", "angler_fish_bucket"))
                .withEntityToSpawn(U.holderEntity("spawn", "angler_fish"))
                .withSizeAndWeight(80, 40, 12000, 7000)
                .addRestriction(WorldRestrictions.OVERWORLD_DEEP_OCEAN)
                .withRarity(Rarity.RARE)
                .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                .withDifficulty(Difficulty.FOUR_AQUA)
                .withBaseChance(20)
        );

        register(fish(new MaybeStack("spawn", "tuna_egg_bucket"))
                .withBucketedFish(new MaybeStack("spawn", "tuna_egg_bucket"))
                .withEntityToSpawn(U.holderEntity("spawn", "tuna"))
                .withAlwaysSpawnEntity()
                .withSizeAndWeight(80, 40, 12000, 7000)
                .addRestriction(WorldRestrictions.OVERWORLD_ALL_OCEANS)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM_MOVING)
        );

        register(fish(new MaybeStack("spawn", "baby_sunfish_bucket"))
                .withBucketedFish(new MaybeStack("spawn", "baby_sunfish_bucket"))
                .withEntityToSpawn(U.holderEntity("spawn", "sunfish"))
                .withAlwaysSpawnEntity()
                .withSizeAndWeight(80, 40, 12000, 7000)
                .addRestriction(WorldRestrictions.OVERWORLD_WARM_OCEAN)
                .withRarity(Rarity.EPIC)
                .withDaytimeRestriction(DaytimeRestriction.NOON)
                .withBaseChance(20)
                .withDifficulty(Difficulty.TWO_THIN.vanishing())
        );

        register(fish(new MaybeStack("spawn", "captured_octopus"))
                .withEntityToSpawn(U.holderEntity("spawn", "octopus"))
                .withSizeAndWeight(80, 40, 12000, 7000)
                .addRestriction(WorldRestrictions.OVERWORLD_ALL_OCEANS)
                .withBaseChance(1)
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.MEDIUM_VANISHING_MOVING)
        );

        register(fish(new MaybeStack("spawn", "herring"))
                .withBucketedFish(new MaybeStack("spawn", "herring_bucket"))
                .withEntityToSpawn(U.holderEntity("spawn", "herring"))
                .withSizeAndWeight(80, 40, 12000, 7000)
                .addRestriction(WorldRestrictions.OVERWORLD_ALL_OCEANS)
                .withRarity(Rarity.COMMON)
                .withDifficulty(Difficulty.EASY_MOVING)
        );

    }
}
