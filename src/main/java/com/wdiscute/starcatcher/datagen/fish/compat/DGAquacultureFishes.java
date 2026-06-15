package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.fishrestrictions.*;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGAquacultureFishes
{
    public static void bootstrap()
    {

        //
        //                                                   ,--.   ,--.                                 ,---.
        // ,--,--.  ,---.  ,--.,--.  ,--,--.  ,---. ,--.,--. |  | ,-'  '-. ,--.,--. ,--.--.  ,---.      '.-.  \
        //' ,-.  | | .-. | |  ||  | ' ,-.  | | .--' |  ||  | |  | '-.  .-' |  ||  | |  .--' | .-. :      .-' .'
        //\ '-'  | ' '-' | '  ''  ' \ '-'  | \ `--. '  ''  ' |  |   |  |   '  ''  ' |  |    \   --.     /   '-.
        // `--`--'  `-|  |  `----'   `--`--'  `---'  `----'  `--'   `--'    `----'  `--'     `----'     '-----'
        //            `--'

        //freshwater
        register(overworldRiverFish(new MaybeStack("aquaculture", "smallmouth_bass"))
                .withBucketedFish(new MaybeStack("aquaculture", "smallmouth_bass_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "smallmouth_bass"))
                .withSeasons(SeasonRestriction.NOT_WINTER)
                .withSizeAndWeight(30, 10, 1500, 500)
                .withDifficulty(Difficulty.MEDIUM)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
                .withRarity(Rarity.UNCOMMON)
        );

        register(overworldRiverFish(new MaybeStack("aquaculture", "bluegill"))
                .withBucketedFish(new MaybeStack("aquaculture", "smallmouth_bass_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "smallmouth_bass"))
                .withSeasons(SeasonRestriction.SUMMER_AUTUMN)
                .withSizeAndWeight(15, 3, 300, 200)
        );

        register(overworldRiverFish(new MaybeStack("aquaculture", "brown_trout"))
                .withBucketedFish(new MaybeStack("aquaculture", "brown_trout_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "brown_trout"))
                .withSeasons(SeasonRestriction.NOT_SPRING)
                .withSizeAndWeight(45, 15, 3000, 2000)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                .withWeather(WeatherRestriction.CLEAR)
        );

        register(overworldRiverFish(new MaybeStack("aquaculture", "carp"))
                .withBucketedFish(new MaybeStack("aquaculture", "carp_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "carp"))
                .withSeasons(SeasonRestriction.SPRING_WINTER)
                .withSizeAndWeight(60, 20, 10000, 4000)
                .withDifficulty(Difficulty.HARD)
                .withRarity(Rarity.RARE)
                .withWeather(WeatherRestriction.RAIN)
        );

        register(overworldMountainFish(new MaybeStack("aquaculture", "catfish"))
                .withBucketedFish(new MaybeStack("aquaculture", "catfish_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "catfish"))
                .withSizeAndWeight(150, 40, 100000, 25000)
                .withDifficulty(Difficulty.THIN_NO_DECAY_NOT_FORGIVING)
                .withRarity(Rarity.EPIC)
                .withWeather(WeatherRestriction.RAIN)
        );

        register(overworldMountainFish(new MaybeStack("aquaculture", "gar"))
                .withBucketedFish(new MaybeStack("aquaculture", "gar_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "gar"))
                .withSeasons(SeasonRestriction.SPRING_WINTER)
                .withSizeAndWeight(160, 30, 160000, 20000)
        );

        register(overworldLakeFish(new MaybeStack("aquaculture", "minnow"))
                .withBucketedFish(new MaybeStack("aquaculture", "minnow_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "minnow"))
                .withSizeAndWeight(6, 4, 10, 4)
        );

        register(overworldLakeFish(new MaybeStack("aquaculture", "muskellunge"))
                .withBucketedFish(new MaybeStack("aquaculture", "muskellunge_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "muskellunge"))
                .withSizeAndWeight(100, 10, 7000, 3000)
                .withRarity(Rarity.RARE)
                .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
        );

        register(overworldLakeFish(new MaybeStack("aquaculture", "perch"))
                .withBucketedFish(new MaybeStack("aquaculture", "perch_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "perch"))
                .withSeasons(SeasonRestriction.SUMMER_AUTUMN)
                .withSizeAndWeight(20, 5, 500, 200)
        );

        //arid
        register(overworldWarmMountainFish(new MaybeStack("aquaculture", "bayad"))
                .withBucketedFish(new MaybeStack("aquaculture", "bayad_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "bayad"))
                .withSeasons(SeasonRestriction.SUMMER_AUTUMN)
                .withSizeAndWeight(170, 30, 150000, 20000)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
        );

        register(overworldWarmLakeFish(new MaybeStack("aquaculture", "boulti"))
                .withBucketedFish(new MaybeStack("aquaculture", "boulti_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "boulti"))
                .withSeasons(SeasonRestriction.SUMMER)
                .withSizeAndWeight(40, 10, 4000, 300)
                .withRarity(Rarity.RARE)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
                .withDifficulty(Difficulty.HARD)
        );

        register(overworldWarmMountainFish(new MaybeStack("aquaculture", "capitaine"))
                .withBucketedFish(new MaybeStack("aquaculture", "capitaine_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "capitaine"))
                .withSeasons(SeasonRestriction.SPRING_SUMMER)
                .withSizeAndWeight(130, 50, 12000, 3000)
        );

        register(overworldWarmMountainFish(new MaybeStack("aquaculture", "synodontis"))
                .withBucketedFish(new MaybeStack("aquaculture", "synodontis_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "synodontis"))
                .withSizeAndWeight(35, 15, 1000, 300)
                .withDifficulty(Difficulty.HARD_MOVING)
                .withRarity(Rarity.EPIC)
        );

        //arctic ocean
        register(overworldColdOceanFish(new MaybeStack("aquaculture", "atlantic_cod"))
                .withBucketedFish(new MaybeStack("aquaculture", "atlantic_cod_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "atlantic_cod"))
                .withSeasons(SeasonRestriction.WINTER)
                .withSizeAndWeight(100, 50, 15000, 10000)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
        );

        register(overworldColdOceanFish(new MaybeStack("aquaculture", "blackfish"))
                .withBucketedFish(new MaybeStack("aquaculture", "blackfish_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "blackfish"))
                .withSeasons(SeasonRestriction.WINTER)
                .withSizeAndWeight(50, 20, 5000, 3000)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                .withRarity(Rarity.UNCOMMON)
        );

        register(overworldColdOceanFish(new MaybeStack("aquaculture", "pacific_halibut"))
                .withBucketedFish(new MaybeStack("aquaculture", "pacific_halibut_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "pacific_halibut"))
                .withSeasons(SeasonRestriction.NOT_SUMMER)
                .withSizeAndWeight(150, 50, 80000, 5000)
        );

        register(overworldColdOceanFish(new MaybeStack("aquaculture", "atlantic_halibut"))
                .withBucketedFish(new MaybeStack("aquaculture", "atlantic_halibut_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "atlantic_halibut"))
                .withSizeAndWeight(200, 80, 150000, 10000)
                .withDifficulty(Difficulty.MEDIUM)
                .withWeather(WeatherRestriction.RAIN)
        );

        register(overworldColdOceanFish(new MaybeStack("aquaculture", "atlantic_herring"))
                .withBucketedFish(new MaybeStack("aquaculture", "atlantic_herring_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "atlantic_herring"))
                .withSeasons(SeasonRestriction.SPRING_WINTER)
                .withSizeAndWeight(25, 5, 200, 100)
                .withDifficulty(Difficulty.HARD_MOVING)
                .withRarity(Rarity.RARE)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
        );

        register(overworldColdOceanFish(new MaybeStack("aquaculture", "pink_salmon"))
                .withBucketedFish(new MaybeStack("aquaculture", "pink_salmon_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "pink_salmon"))
                .withSeasons(SeasonRestriction.SPRING_WINTER)
                .withSizeAndWeight(50, 10, 2000, 1000)
                .withRarity(Rarity.EPIC)
                .withWeather(WeatherRestriction.THUNDER)
                .withDifficulty(Difficulty.HARD)
        );

        register(overworldColdOceanFish(new MaybeStack("aquaculture", "pollock"))
                .withBucketedFish(new MaybeStack("aquaculture", "pollock_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "pollock"))
                .withSizeAndWeight(70, 30, 5000, 4000)
        );

        register(overworldColdOceanFish(new MaybeStack("aquaculture", "rainbow_trout"))
                .withBucketedFish(new MaybeStack("aquaculture", "rainbow_trout_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "rainbow_trout"))
                .withSeasons(SeasonRestriction.WINTER)
                .withSizeAndWeight(60, 20, 2000, 1500)
                .withRarity(Rarity.UNCOMMON)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
        );

        //saltwater
        register(overworldOceanFish(new MaybeStack("aquaculture", "jellyfish"))
                .withBucketedFish(new MaybeStack("aquaculture", "jellyfish_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "jellyfish"))
                .withSeasons(SeasonRestriction.SUMMER)
                .withSizeAndWeight(100, 70, 50000, 40000)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.HARD)
                .withBaseChance(3)
        );

        register(overworldOceanFish(new MaybeStack("aquaculture", "red_grouper"))
                .withBucketedFish(new MaybeStack("aquaculture", "red_grouper_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "red_grouper"))
                .withSeasons(SeasonRestriction.AUTUMN)
                .withSizeAndWeight(100, 50, 15000, 10000)
        );

        register(overworldOceanFish(new MaybeStack("aquaculture", "tuna"))
                .withBucketedFish(new MaybeStack("aquaculture", "tuna_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "tuna"))
                .withSizeAndWeight(200, 100, 200000, 150000)
        );

        //jungle
        register(overworldJungleFish(new MaybeStack("aquaculture", "arapaima"))
                .withBucketedFish(new MaybeStack("aquaculture", "arapaima_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "arapaima"))
                .withSeasons(SeasonRestriction.AUTUMN_WINTER)
                .withSizeAndWeight(250, 50, 50000, 150000)
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.HARD)
                .withWeather(WeatherRestriction.RAIN)
        );

        register(overworldJungleFish(new MaybeStack("aquaculture", "arrau_turtle"))
                //no bucketed version
                .withEntityToSpawn(U.holderEntity("aquaculture", "arrau_turtle"))
                .withSizeAndWeight(100, 30, 80000, 150000)
                .withDifficulty(Difficulty.MEDIUM)
        );


        register(overworldJungleFish(new MaybeStack("aquaculture", "piranha"))
                .withBucketedFish(new MaybeStack("aquaculture", "piranha_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "piranha"))
                .withSeasons(SeasonRestriction.NOT_WINTER)
                .withSizeAndWeight(30, 10, 500, 300)
                .addBait(BaitRestriction.LEGENDARY_BAIT)
                .withRarity(Rarity.LEGENDARY)
                .withDifficulty(Difficulty.FOUR_BIG)
                .withDaytimeRestriction(DaytimeRestriction.NOON)
        );

        register(overworldJungleFish(new MaybeStack("aquaculture", "tambaqui"))
                .withBucketedFish(new MaybeStack("aquaculture", "tambaqui_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "tambaqui"))
                .withSizeAndWeight(100, 30, 150000, 10000)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM)
        );

        //swamp
        register(overworldSwampFish(new MaybeStack("aquaculture", "leech"))
                //no bucketed version
                //no entity
                .withSeasons(SeasonRestriction.AUTUMN)
                .withSizeAndWeight(10, 5, 5, 3)
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.HARD)
        );

        register(overworldSwampFish(new MaybeStack("aquaculture", "box_turtle"))
                //no bucketed version
                .withEntityToSpawn(U.holderEntity("aquaculture", "box_turtle"))
                .withSizeAndWeight(20, 5, 1000, 500)
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.HARD)
                .withWeather(WeatherRestriction.RAIN)
        );

        //mushroom island
        register(overworldMushroomFieldsFish(new MaybeStack("aquaculture", "brown_shrooma"))
                .withBucketedFish(new MaybeStack("aquaculture", "brown_shrooma_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "brown_shrooma"))
                .withSizeAndWeight(100, 20, 3000, 500)
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.FOUR_BIG)
        );

        register(overworldMushroomFieldsFish(new MaybeStack("aquaculture", "red_shrooma"))
                .withBucketedFish(new MaybeStack("aquaculture", "brown_shrooma_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "brown_shrooma"))
                .withSizeAndWeight(100, 20, 3000, 500)
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.FOUR_BIG)
        );

        //anywhere
        register(overworldOceanFish(new MaybeStack("aquaculture", "goldfish"))
                //no bucketed version
                //no entity
                .withSeasons(SeasonRestriction.SUMMER)
                .withSizeAndWeight(15, 5, 100, 5)
                .withBaseChance(0)
                //0.5% weight
                .withPercentageChance(0.005f)
        );



        //extras
        //neptunium ingot
        register(overworldOceanFish(new MaybeStack("aquaculture", "neptunium_ingot"))
                .withMaxLimit(5)
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false)
                .addRarityRestriction(new RarityCountRestriction.RarityCount(
                        Rarity.GOLDEN, 1, RarityCountRestriction.RarityCount.CountType.TOTAL))
                .withPercentageChance(0.05f)
                .extra()
        );

    }
}
