package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.fishrestrictions.SeasonRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.WeatherRestriction;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGFishOfThievesFishes
{
    public static void bootstrap()
    {

        //
        //  ,---. ,--.         ,--.                   ,---.
        // /  .-' `--'  ,---.  |  ,---.       ,---.  /  .-'
        // |  `-, ,--. (  .-'  |  .-.  |     | .-. | |  `-,
        // |  .-' |  | .-'  `) |  | |  |     ' '-' ' |  .-'
        // `--'   `--' `----'  `--' `--'      `---'  `--'
        //   ,--.   ,--.      ,--.
        // ,-'  '-. |  ,---.  `--'  ,---.  ,--.  ,--.  ,---.   ,---.
        // '-.  .-' |  .-.  | ,--. | .-. :  \  `'  /  | .-. : (  .-'
        //   |  |   |  | |  | |  | \   --.   \    /   \   --. .-'  `)
        //   `--'   `--' `--' `--'  `----'    `--'     `----' `----'
        //

        register(overworldOceanFish(new MaybeStack("fishofthieves", "splashtail"))
                .withBucketedFish(new MaybeStack("fishofthieves", "splashtail_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "splashtail"))
                .withSeasons(SeasonRestriction.SUMMER_AUTUMN)
                .withSizeAndWeight(250, 70, 7600, 2000)
                .withDifficulty(Difficulty.EASY_MOVING)
        );

        register(overworldLakeFish(new MaybeStack("fishofthieves", "pondie"))
                .withBucketedFish(new MaybeStack("fishofthieves", "pondie_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "pondie"))
                .withSeasons(SeasonRestriction.SPRING_SUMMER)
                .withSizeAndWeight(190, 30, 9000, 3600)
                .withDifficulty(Difficulty.MEDIUM)
                .withRarity(Rarity.UNCOMMON)
        );

        register(overworldRiverFish(new MaybeStack("fishofthieves", "islehopper"))
                .withBucketedFish(new MaybeStack("fishofthieves", "islehopper_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "islehopper"))
                .withSizeAndWeight(300, 20, 23000, 3600)
                .withDifficulty(Difficulty.MEDIUM_VANISHING)
                .withRarity(Rarity.UNCOMMON)
        );

        register(overworldWarmOceanFish(new MaybeStack("fishofthieves", "ancientscale"))
                .withBucketedFish(new MaybeStack("fishofthieves", "ancientscale_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "ancientscale"))
                .withSeasons(SeasonRestriction.SPRING_WINTER)
                .withSizeAndWeight(70, 10, 4000, 2000)
                .withDifficulty(Difficulty.HARD_VANISHING)
                .withRarity(Rarity.RARE)
        );

        register(overworldWarmOceanFish(new MaybeStack("fishofthieves", "plentifin"))
                .withBucketedFish(new MaybeStack("fishofthieves", "plentifin_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "plentifin"))
                .withSizeAndWeight(90, 10, 4300, 2500)
                .withDifficulty(Difficulty.MEDIUM)
                .withRarity(Rarity.UNCOMMON)
        );

        register(overworldLushCavesFish(new MaybeStack("fishofthieves", "wildsplash"))
                .withBucketedFish(new MaybeStack("fishofthieves", "wildsplash_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "wildsplash"))
                .withSeasons(SeasonRestriction.SPRING_WINTER)
                .withSizeAndWeight(120, 30, 8000, 2200)
                .withDifficulty(Difficulty.MEDIUM_MOVING)
                .withRarity(Rarity.UNCOMMON)
        );

        register(overworldDeepslateFish(new MaybeStack("fishofthieves", "devilfish"))
                .withBucketedFish(new MaybeStack("fishofthieves", "devilfish_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "devilfish"))
                .withSizeAndWeight(180, 80, 20000, 2200)
                .withDifficulty(Difficulty.HARD)
                .withRarity(Rarity.RARE)
        );

        register(overworldColdOceanFish(new MaybeStack("fishofthieves", "battlegill"))
                .withBucketedFish(new MaybeStack("fishofthieves", "battlegill_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "battlegill"))
                .withSeasons(SeasonRestriction.SUMMER_AUTUMN)
                .withSizeAndWeight(100, 10, 19000, 4200)
                .withDifficulty(Difficulty.MEDIUM_VANISHING)
                .withRarity(Rarity.UNCOMMON)
        );

        register(endFish(new MaybeStack("fishofthieves", "wrecker"))
                .withBucketedFish(new MaybeStack("fishofthieves", "wrecker_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "wrecker"))
                .withSeasons(SeasonRestriction.SPRING_AUTUMN)
                .withSizeAndWeight(100, 10, 19000, 4200)
                .withDifficulty(Difficulty.MEDIUM_VANISHING)
                .withRarity(Rarity.EPIC)
        );

        register(overworldOceanFish(new MaybeStack("fishofthieves", "stormfish"))
                .withBucketedFish(new MaybeStack("fishofthieves", "stormfish_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "stormfish"))
                .withSeasons(SeasonRestriction.NOT_SPRING)
                .withSizeAndWeight(150, 30, 14000, 2000)
                .withDifficulty(Difficulty.MEDIUM_VANISHING)
                .withWeather(WeatherRestriction.THUNDER)
                .withRarity(Rarity.RARE)
        );
    }
}
