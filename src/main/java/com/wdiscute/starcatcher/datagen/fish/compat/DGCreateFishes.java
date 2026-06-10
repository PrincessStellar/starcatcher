package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.compat.CreateCompat;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.fish.SizeAndWeight;
import com.wdiscute.starcatcher.registry.fishrestrictions.DimensionRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.FluidRestriction;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGCreateFishes
{
    public static void bootstrap()
    {

        //
        //                                   ,--.
        //  ,---. ,--.--.  ,---.   ,--,--. ,-'  '-.  ,---.
        // | .--' |  .--' | .-. : ' ,-.  | '-.  .-' | .-. :
        // \ `--. |  |    \   --. \ '-'  |   |  |   \   --.
        //  `---' `--'     `----'  `--`--'   `--'    `----'
        //


        register(overworldWarmLakeFish(new MaybeStack(CreateCompat.COGGILL))
                .withSizeAndWeight(80, 40, 12000, 7000)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM),
                "create"
        );

        register(overworldBeachFish(new MaybeStack(CreateCompat.MECHANICAL_SNAIL))
                .withSizeAndWeight(3, 1, 400, 100)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.EASY),
                "create"
        );

        register(overworldBeachFish(new MaybeStack(CreateCompat.MECHANICAL_BRASS_SNAIL))
                .withSizeAndWeight(7, 1, 1400, 800)
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.HARD),
                "create"
        );

        register(overworldOceanFish(new MaybeStack(CreateCompat.PHILLIPSFISH))
                .withSizeAndWeight(7, 1, 100, 60)
                .withRarity(Rarity.COMMON)
                .withDifficulty(Difficulty.EASY),
                "create"
        );

        register(overworldRiverFish(new MaybeStack(CreateCompat.VALVE))
                .withSizeAndWeight(7, 1, 300, 100)
                .withRarity(Rarity.COMMON)
                .withDifficulty(Difficulty.EASY_MOVING),
                "create"
        );

        register(overworldOceanFish(new MaybeStack(CreateCompat.PIPEHEAD))
                .withSizeAndWeight(7, 1, 2400, 400)
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.MEDIUM_MOVING),
                "create"
        );

        register(overworldDeepOceanFish(new MaybeStack(CreateCompat.COGTOPUS))
                .withSizeAndWeight(7, 1, 7400, 800)
                .withRarity(Rarity.RARE)
                .withBaseChance(3)
                .withDifficulty(Difficulty.MEDIUM_MOVING),
                "create"
        );

        register(overworldDeepslateFish(new MaybeStack(CreateCompat.EEL_DYNAMO))
                .withSizeAndWeight(7, 1, 7400, 800)
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.HARD_VANISHING),
                "create"
        );

        register(overworldRiverFish(new MaybeStack(CreateCompat.DRIVE_PIKE))
                .withSizeAndWeight(7, 1, 4400, 1800)
                .withRarity(Rarity.COMMON)
                .withDifficulty(Difficulty.HARD),
                "create"
        );

        register(netherLavaFish(new MaybeStack(CreateCompat.DRIVE_PIKE))
                .withSizeAndWeight(7, 1, 7400, 800)
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.HARD),
                "create"
        );

        register(netherLavaFish(new MaybeStack(CreateCompat.BRASSGILL))
                .withSizeAndWeight(7, 1, 17400, 800)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.HARD),
                "create"
        );

        register(overworldWarmLakeFish(new MaybeStack(CreateCompat.MEKA_AGAVE_BREAM))
                .withSizeAndWeight(new SizeAndWeight(36, 12, 2000, 1000))
                .withBaseChance(8)
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.HARD),
                "create"
        );
    }
}
