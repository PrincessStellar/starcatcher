package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.fishrestrictions.*;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGTideFishes
{
    public static void bootstrap()
    {
        //
        //  ,--.   ,--.    ,--.
        //,-'  '-. `--'  ,-|  |  ,---.
        //'-.  .-' ,--. ' .-. | | .-. :
        //  |  |   |  | \ `-' | \   --.
        //  `--'   `--'  `---'   `----'
        //

        //freshwater
        register(
                overworldRiverFish(new MaybeStack("tide", "yellow_perch"))
                        .withBucketedFish(new MaybeStack("tide", "yellow_perch_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "yellow_perch"))
                        .withSizeAndWeight(27, 11, 500, 352)
                        .withDifficulty(Difficulty.EASY_MOVING)
                        .withRarity(Rarity.COMMON)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
        );

        register(
                overworldColdLakeFish(new MaybeStack("tide", "rainbow_trout"))
                        .withBucketedFish(new MaybeStack("tide", "rainbow_trout_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "rainbow_trout"))
                        .withSizeAndWeight(35, 8, 1600, 1200)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldRiverFish(new MaybeStack("tide", "largemouth_bass"))
                        .withBucketedFish(new MaybeStack("tide", "largemouth_bass_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "largemouth_bass"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldRiverFish(new MaybeStack("tide", "brook_trout"))
                        .withBucketedFish(new MaybeStack("tide", "brook_trout_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "brook_trout"))
                        .withSizeAndWeight(35, 8, 1600, 1200)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldRiverFish(new MaybeStack("tide", "white_crappie"))
                        .withBucketedFish(new MaybeStack("tide", "white_crappie_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "white_crappie"))
                        .withSizeAndWeight(35, 8, 1600, 1200)
                        .withDifficulty(Difficulty.EASY_VANISHING)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldRiverFish(new MaybeStack("tide", "black_crappie"))
                        .withBucketedFish(new MaybeStack("tide", "black_crappie_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "black_crappie"))
                        .withSizeAndWeight(35, 8, 1600, 1200)
                        .withDifficulty(Difficulty.EASY_VANISHING)
                        .withRarity(Rarity.COMMON)
        );


        register(
                overworldRiverFish(new MaybeStack("tide", "carp"))
                        .withBucketedFish(new MaybeStack("tide", "carp_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "carp"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.COMMON)
        );


        register(
                overworldColdRiverFish(new MaybeStack("tide", "bluegill"))
                        .withBucketedFish(new MaybeStack("tide", "bluegill_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "bluegill"))
                        .withSizeAndWeight(20, 5, 400, 100)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON)
        );


        register(
                overworldWarmLakeFish(new MaybeStack("tide", "guppy"))
                        .withBucketedFish(new MaybeStack("tide", "guppy_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "guppy"))
                        .withSizeAndWeight(20, 5, 400, 100)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.UNCOMMON)
        );


        register(
                overworldWarmLakeFish(new MaybeStack("tide", "walleye"))
                        .withBucketedFish(new MaybeStack("tide", "walleye_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "walleye"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.UNCOMMON)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
        );


        register(
                overworldWarmLakeFish(new MaybeStack("tide", "catfish"))
                        .withBucketedFish(new MaybeStack("tide", "catfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "catfish"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.UNCOMMON)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
        );


        register(
                overworldCherryGroveFish(new MaybeStack("tide", "blossom_bass"))
                        .withBucketedFish(new MaybeStack("tide", "blossom_bass_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "blossom_bass"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.TWO_AQUA_ONE_THIN)
                        .withRarity(Rarity.RARE)
        );


        register(
                overworldJungleFish(new MaybeStack("tide", "arapaima"))
                        .withBucketedFish(new MaybeStack("tide", "arapaima_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "arapaima"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.SINGLE_AQUA)
                        .withRarity(Rarity.RARE)
        );


        register(
                overworldWarmLakeFish(new MaybeStack("tide", "mirage_catfish"))
                        .withBucketedFish(new MaybeStack("tide", "mirage_catfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "mirage_catfish"))
                        .withSizeAndWeight(100, 50, 10000, 3000)
                        .withDifficulty(Difficulty.SINGLE_AQUA)
                        .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                        .withRarity(Rarity.RARE)
        );

        register(
                overworldColdLakeFish(new MaybeStack("tide", "frostbite_flounder"))
                        .withBucketedFish(new MaybeStack("tide", "frostbite_flounder_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "frostbite_flounder"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.HARD_MOVING)
                        .withRarity(Rarity.RARE)
        );

        register(
                overworldWarmLakeFish(new MaybeStack("tide", "sand_tiger_shark"))
                        .withBucketedFish(new MaybeStack("tide", "sand_tiger_shark_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "sand_tiger_shark"))
                        .withSizeAndWeight(400, 120, 16000, 11000)
                        .withDifficulty(Difficulty.TWO_AQUA_ONE_THIN)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                        .withRarity(Rarity.RARE)
        );


        register(
                overworldColdRiverFish(new MaybeStack("tide", "sturgeon"))
                        .withBucketedFish(new MaybeStack("tide", "sturgeon_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "sturgeon"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.TWO_THIN_NO_DECAY)
                        .withRarity(Rarity.RARE)
        );


        register(
                overworldSwampFish(new MaybeStack("tide", "slimy_salmon"))
                        .withBucketedFish(new MaybeStack("tide", "slimy_salmon_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "slimy_salmon"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.TWO_THIN)
                        .withRarity(Rarity.RARE)
        );


        register(
                overworldLakeFish(new MaybeStack("tide", "mooneye"))
                        .withBucketedFish(new MaybeStack("tide", "mooneye_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "mooneye"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.SINGLE_THIN_FAST)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                        .withRarity(Rarity.EPIC)
        );


        register(
                overworldRiverFish(new MaybeStack("tide", "bull_shark"))
                        .withBucketedFish(new MaybeStack("tide", "bull_shark_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "bull_shark"))
                        .withSizeAndWeight(400, 120, 16000, 11000)
                        .withDifficulty(Difficulty.SINGLE_THIN_FAST)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                        .withRarity(Rarity.EPIC)
        );


        //oceans
        register(
                overworldOceanFish(new MaybeStack("tide", "mackerel"))
                        .withBucketedFish(new MaybeStack("tide", "mackerel_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "mackerel"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.EASY)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldOceanFish(new MaybeStack("tide", "tuna"))
                        .withBucketedFish(new MaybeStack("tide", "tuna_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "tuna"))
                        .withSizeAndWeight(150, 50, 120000, 60000)
                        .withDifficulty(Difficulty.EASY)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldOceanFish(new MaybeStack("tide", "red_snapper"))
                        .withBucketedFish(new MaybeStack("tide", "red_snapper_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "red_snapper"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldOceanFish(new MaybeStack("tide", "snook"))
                        .withBucketedFish(new MaybeStack("tide", "snook_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "snook"))
                        .withSizeAndWeight(27.0f, 11, 500, 352)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldOceanFish(new MaybeStack("tide", "anchovy"))
                        .withBucketedFish(new MaybeStack("tide", "anchovy_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "anchovy"))
                        .withSizeAndWeight(27.0f, 11, 500, 352)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldOceanFish(new MaybeStack("tide", "flounder"))
                        .withBucketedFish(new MaybeStack("tide", "flounder_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "flounder"))
                        .withSizeAndWeight(27.0f, 11, 500, 352)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldWarmOceanFish(new MaybeStack("tide", "angelfish"))
                        .withBucketedFish(new MaybeStack("tide", "angelfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "angelfish"))
                        .withSizeAndWeight(27.0f, 11, 500, 352)
                        .withDifficulty(Difficulty.EASY_FAST_FISH)
                        .withRarity(Rarity.UNCOMMON)
        );

        register(
                overworldMushroomFieldsFish(new MaybeStack("tide", "spore_stalker"))
                        .withBucketedFish(new MaybeStack("tide", "spore_stalker_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "spore_stalker"))
                        .withSizeAndWeight(70, 50, 4000, 2000)
                        .withDifficulty(Difficulty.HARD_MOVING)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldWarmOceanFish(new MaybeStack("tide", "mahi_mahi"))
                        .withBucketedFish(new MaybeStack("tide", "mahi_mahi_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "mahi_mahi"))
                        .withSizeAndWeight(70, 50, 4000, 2000)
                        .withDifficulty(Difficulty.FOUR_BIG_VANISHING)
                        .withRarity(Rarity.RARE)
        );

        register(
                overworldWarmOceanFish(new MaybeStack("tide", "sailfish"))
                        .withBucketedFish(new MaybeStack("tide", "sailfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "sailfish"))
                        .withSizeAndWeight(70, 50, 4000, 2000)
                        .withDifficulty(Difficulty.FOUR_THIN)
                        .withRarity(Rarity.RARE)
        );

        register(
                overworldWarmOceanFish(new MaybeStack("tide", "swordfish"))
                        .withBucketedFish(new MaybeStack("tide", "swordfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "swordfish"))
                        .withSizeAndWeight(70, 50, 4000, 2000)
                        .withDifficulty(Difficulty.TWO_THIN_NO_DECAY)
                        .withRarity(Rarity.RARE)
        );

        register(
                overworldOceanFish(new MaybeStack("tide", "manta_ray"))
                        .withBucketedFish(new MaybeStack("tide", "manta_ray_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "manta_ray"))
                        .withSizeAndWeight(70, 50, 4000, 2000)
                        .withDifficulty(Difficulty.TWO_THIN_NO_DECAY)
                        .withRarity(Rarity.RARE)
        );


        //todo ocean monument structure restriction
        register(
                overworldColdOceanFish(new MaybeStack("tide", "aquathorn"))
                        .withBucketedFish(new MaybeStack("tide", "aquathorn_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "aquathorn"))
                        .withSizeAndWeight(70, 50, 4000, 2000)
                        .withDifficulty(Difficulty.SINGLE_AQUA)
                        .withRarity(Rarity.EPIC)
                        .withBaseChance(2)
        );


        register(
                overworldOceanFish(new MaybeStack("tide", "neptune_koi"))
                        .withBucketedFish(new MaybeStack("tide", "neptune_koi_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "neptune_koi"))
                        .withSizeAndWeight(60, 13, 3500, 731)
                        .withDifficulty(Difficulty.SINGLE_AQUA)
                        .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                        .withRarity(Rarity.EPIC)
        );


        register(
                overworldOceanFish(new MaybeStack("tide", "pluto_snail"))
                        .withBucketedFish(new MaybeStack("tide", "pluto_snail_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "pluto_snail"))
                        .withSizeAndWeight(60, 13, 3500, 731)
                        .withDifficulty(Difficulty.TWO_AQUA)
                        .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                        .withRarity(Rarity.EPIC)
        );

        register(
                overworldOceanFish(new MaybeStack("tide", "sun_emblem"))
                        .withBucketedFish(new MaybeStack("tide", "sun_emblem_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "sun_emblem"))
                        .withSizeAndWeight(10, 3, 40, 10)
                        .withDifficulty(Difficulty.TWO_THIN)
                        .withDaytimeRestriction(DaytimeRestriction.NOON)
                        .withRarity(Rarity.EPIC)
        );


        register(
                overworldOceanFish(new MaybeStack("tide", "saturn_cuttlefish"))
                        .withBucketedFish(new MaybeStack("tide", "saturn_cuttlefish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "saturn_cuttlefish"))
                        .withSizeAndWeight(60, 13, 3500, 731)
                        .withDifficulty(Difficulty.TWO_THIN)
                        .withDaytimeRestriction(DaytimeRestriction.NOON)
                        .withRarity(Rarity.EPIC)
        );


        register(
                overworldOceanFish(new MaybeStack("tide", "marstilus"))
                        .withBucketedFish(new MaybeStack("tide", "marstilus_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "marstilus"))
                        .withSizeAndWeight(60, 13, 3500, 731)
                        .withDifficulty(Difficulty.TWO_THIN)
                        .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                        .withRarity(Rarity.EPIC)
        );


        register(
                overworldOceanFish(new MaybeStack("tide", "uranias_pisces"))
                        .withBucketedFish(new MaybeStack("tide", "uranias_pisces_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "uranias_pisces"))
                        .withSizeAndWeight(60, 13, 3500, 731)
                        .withDifficulty(Difficulty.TWO_THIN)
                        .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                        .withRarity(Rarity.EPIC)
        );

        register(
                overworldOceanFish(new MaybeStack("tide", "great_white_shark"))
                        .withBucketedFish(new MaybeStack("tide", "great_white_shark_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "great_white_shark"))
                        .withSizeAndWeight(400, 120, 16000, 11000)
                        .withDifficulty(Difficulty.HARD)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
                        .withRarity(Rarity.EPIC)
        );

        register(
                overworldDeepOceanFish(new MaybeStack("tide", "shooting_starfish"))
                        .withBucketedFish(new MaybeStack("tide", "shooting_starfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "shooting_starfish"))
                        .withSizeAndWeight(400, 120, 16000, 11000)
                        .withDifficulty(Difficulty.TWO_THIN)
                        .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                        .withBaseChance(1)
                        .addRestrictions(BaitRestriction.LEGENDARY_BAIT)
                        .withRarity(Rarity.EPIC)
        );

        register(
                overworldDeepOceanFish(new MaybeStack("tide", "coelacanth"))
                        .withBucketedFish(new MaybeStack("tide", "coelacanth_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "coelacanth"))
                        .withSizeAndWeight(400, 120, 16000, 11000)
                        .withDifficulty(Difficulty.SINGLE_AQUA_MOVING)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                        .withWeather(WeatherRestriction.RAIN)
                        .addRestrictions(BaitRestriction.LEGENDARY_BAIT)
                        .withRarity(Rarity.LEGENDARY)
        );


        //underground
        register(
                overworldUndergroundFish(new MaybeStack("tide", "cave_eel"))
                        .withBucketedFish(new MaybeStack("tide", "cave_eel_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "cave_eel"))
                        .withSizeAndWeight(500, 150, 10000, 2000)
                        .withDifficulty(Difficulty.EASY)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldUndergroundFish(new MaybeStack("tide", "deep_grouper"))
                        .withBucketedFish(new MaybeStack("tide", "deep_grouper_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "deep_grouper"))
                        .withSizeAndWeight(50, 15, 1000, 200)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING_MOVING)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldUndergroundFish(new MaybeStack("tide", "cave_crawler"))
                        .withBucketedFish(new MaybeStack("tide", "cave_crawler_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "cave_crawler"))
                        .withSizeAndWeight(50, 15, 1000, 200)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldDeepslateFish(new MaybeStack("tide", "shadow_snapper"))
                        .withBucketedFish(new MaybeStack("tide", "shadow_snapper_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "shadow_snapper"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldUndergroundFish(new MaybeStack("tide", "glowfish"))
                        .withBucketedFish(new MaybeStack("tide", "glowfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "glowfish"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.UNCOMMON)
        );

        register(
                overworldCavesFish(new MaybeStack("tide", "anglerfish"))
                        .withBucketedFish(new MaybeStack("tide", "anglerfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "anglerfish"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.UNCOMMON)
        );

        register(
                overworldDeepslateFish(new MaybeStack("tide", "abyss_angler"))
                        .withBucketedFish(new MaybeStack("tide", "abyss_angler_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "abyss_angler"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.UNCOMMON)
        );

        register(
                overworldUndergroundFish(new MaybeStack("tide", "iron_tetra"))
                        .withBucketedFish(new MaybeStack("tide", "iron_tetra_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "iron_tetra"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.EIGHT_STONE_SPOTS)
                        .withRarity(Rarity.UNCOMMON)
        );

        register(
                overworldUndergroundFish(new MaybeStack("tide", "dripstone_darter"))
                        .withBucketedFish(new MaybeStack("tide", "dripstone_darter_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "dripstone_darter"))
                        .withSizeAndWeight(6, 2, 7, 6)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.UNCOMMON)
        );

        register(
                overworldDeepslateFish(new MaybeStack("tide", "lapis_lanternfish"))
                        .withBucketedFish(new MaybeStack("tide", "lapis_lanternfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "lapis_lanternfish"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.UNCOMMON)
        );

        register(
                overworldDeepslateFish(new MaybeStack("tide", "crystal_shrimp"))
                        .withBucketedFish(new MaybeStack("tide", "crystal_shrimp_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "crystal_shrimp"))
                        .withSizeAndWeight(10, 3, 20, 10)
                        .withDifficulty(Difficulty.EASY_FAST_FISH)
                        .withRarity(Rarity.UNCOMMON)
        );

        register(
                overworldDeepslateFish(new MaybeStack("tide", "crystalline_carp"))
                        .withBucketedFish(new MaybeStack("tide", "crystalline_carp_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "crystalline_carp"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDifficulty(Difficulty.TWO_AQUA)
                        .withRarity(Rarity.RARE)
        );

        register(
                overworldDeepslateFish(new MaybeStack("tide", "luminescent_jellyfish"))
                        .withBucketedFish(new MaybeStack("tide", "crystalline_carp_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "crystalline_carp"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.RARE)
        );

        register(
                overworldCavesFish(new MaybeStack("tide", "gilded_minnow"))
                        .withBucketedFish(new MaybeStack("tide", "gilded_minnow_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "gilded_minnow"))
                        .withSizeAndWeight(6, 4, 5, 3)
                        .withDifficulty(Difficulty.FOUR_THIN)
                        .withRarity(Rarity.RARE)
        );

        register(
                overworldCavesFish(new MaybeStack("tide", "bedrock_tetra"))
                        .withBucketedFish(new MaybeStack("tide", "bedrock_tetra_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "bedrock_tetra"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.FOUR_STONE_SPOTS)
                        .withRarity(Rarity.RARE)
        );

        register(
                overworldCavesFish(new MaybeStack("tide", "windbass"))
                        .withBucketedFish(new MaybeStack("tide", "windbass_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "windbass"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.FOUR_BIG_MOVING)
                        .withBaseChance(2)
                        .withRarity(Rarity.EPIC)
        );

        register(
                overworldDeepDarkFish(new MaybeStack("tide", "echo_snapper"))
                        .withBucketedFish(new MaybeStack("tide", "echo_snapper_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "echo_snapper"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.FOUR_BIG_MOVING)
                        .withRarity(Rarity.EPIC)
        );

        register(
                overworldDeepDarkFish(new MaybeStack("tide", "chasm_eel"))
                        .withBucketedFish(new MaybeStack("tide", "chasm_eel_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "chasm_eel"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.TWO_THIN)
                        .withRarity(Rarity.EPIC)
        );

        register(
                overworldDeepslateFish(new MaybeStack("tide", "midas_fish"))
                        .withBucketedFish(new MaybeStack("tide", "echo_snapper_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "echo_snapper"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withRarity(Rarity.LEGENDARY)
                        .withDifficulty(Difficulty.SINGLE_THIN_FAST)
                        .withBaseChance(1)
                        .addRestrictions(BaitRestriction.LEGENDARY_BAIT)
        );

        register(
                overworldCavesFish(new MaybeStack("tide", "devils_hole_pupfish"))
                        .withBucketedFish(new MaybeStack("tide", "devils_hole_pupfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "devils_hole_pupfish"))
                        .withSizeAndWeight(35, 25, 1000, 700)
                        .withRarity(Rarity.LEGENDARY)
                        .withDifficulty(Difficulty.SINGLE_THIN_FAST)
                        .addRestrictions(BaitRestriction.LEGENDARY_BAIT)
                        .addRestrictions(DimensionRestriction.OVERWORLD,
                                new ElevationRestriction(20, 30, "")
                        )
        );


        //lava
        register(
                overworldSurfaceLava(new MaybeStack("tide", "magma_mackerel"))
                        .withBucketedFish(new MaybeStack("tide", "magma_mackerel_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "magma_mackerel"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldSurfaceLava(new MaybeStack("tide", "ember_koi"))
                        .withBucketedFish(new MaybeStack("tide", "ember_koi_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "ember_koi"))
                        .withSizeAndWeight(60, 13, 3500, 731)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldSurfaceLava(new MaybeStack("tide", "ash_perch"))
                        .withBucketedFish(new MaybeStack("tide", "ash_perch_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "ash_perch"))
                        .withSizeAndWeight(27.0f, 11, 500, 352)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldUndergroundLava(new MaybeStack("tide", "obsidian_pike"))
                        .withBucketedFish(new MaybeStack("tide", "obsidian_pike_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "obsidian_pike"))
                        .withSizeAndWeight(75, 20, 5000, 3000)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.COMMON)
        );

        register(
                overworldUndergroundLava(new MaybeStack("tide", "volcano_tuna"))
                        .withBucketedFish(new MaybeStack("tide", "volcano_tuna_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "volcano_tuna"))
                        .withSizeAndWeight(150, 50, 120000, 60000)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.UNCOMMON)
        );

        register(
                netherLavaCrimsonForestFish(new MaybeStack("tide", "crimson_fangjaw"))
                        .withBucketedFish(new MaybeStack("tide", "crimson_fangjaw_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "crimson_fangjaw"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING_MOVING)
                        .withRarity(Rarity.RARE)
        );

        register(
                netherLavaWarpedForestFish(new MaybeStack("tide", "warped_guppy"))
                        .withBucketedFish(new MaybeStack("tide", "warped_guppy_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "warped_guppy"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.HARD_VANISHING)
                        .withRarity(Rarity.RARE)
        );


        register(
                netherLavaSoulSandValleyFish(new MaybeStack("tide", "soulscale"))
                        .withBucketedFish(new MaybeStack("tide", "soulscale_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "soulscale"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.HARD_VANISHING)
                        .withRarity(Rarity.RARE)
        );

        register(
                netherLavaFish(new MaybeStack("tide", "witherfin"))
                        .withBucketedFish(new MaybeStack("tide", "witherfin_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "witherfin"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.RARE)
        );

        register(
                netherLavaFish(new MaybeStack("tide", "inferno_guppy"))
                        .withBucketedFish(new MaybeStack("tide", "inferno_guppy_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "inferno_guppy"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.RARE)
        );

        register(
                netherLavaFish(new MaybeStack("tide", "blazing_swordfish"))
                        .withBucketedFish(new MaybeStack("tide", "blazing_swordfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "blazing_swordfish"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.EPIC)
        );


        //void
        register(
                endVoidFishing(new MaybeStack("tide", "amber_rockfish"))
                        .withBucketedFish(new MaybeStack("tide", "amber_rockfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "amber_rockfish"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.TWO_STONE_SPOTS_EASY)
                        .withRarity(Rarity.COMMON)
                        .withBaseChance(20)
        );

        register(
                endVoidFishing(new MaybeStack("tide", "pale_clubfish"))
                        .withBucketedFish(new MaybeStack("tide", "pale_clubfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "pale_clubfish"))
                        .withSizeAndWeight(300, 150, 26000, 7000)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING)
                        .withRarity(Rarity.COMMON)
                        .withBaseChance(20)
        );

        register(
                endVoidFishing(new MaybeStack("tide", "enderfin"))
                        .withBucketedFish(new MaybeStack("tide", "enderfin_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "enderfin"))
                        .withSizeAndWeight(300, 150, 16000, 7000)
                        .withDifficulty(Difficulty.TWO_AQUA)
                        .withRarity(Rarity.COMMON)
                        .withBaseChance(20)
        );

        register(
                overworldVoidFishing(new MaybeStack("tide", "incandescent_larva"))
                        .withBucketedFish(new MaybeStack("tide", "enderfin_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "enderfin"))
                        .withSizeAndWeight(6, 4, 5, 3)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING)
                        .withRarity(Rarity.COMMON)
                        .withBaseChance(20)
        );

        register(
                overworldVoidFishing(new MaybeStack("tide", "bedrock_bug"))
                        .withBucketedFish(new MaybeStack("tide", "bedrock_bug_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "bedrock_bug"))
                        .withSizeAndWeight(300, 150, 26000, 7000)
                        .withDifficulty(Difficulty.TWO_STONE_SPOTS_EASY)
                        .withRarity(Rarity.COMMON)
                        .withBaseChance(20)
        );

        register(
                overworldVoidFishing(new MaybeStack("tide", "sleepy_carp"))
                        .withBucketedFish(new MaybeStack("tide", "sleepy_carp_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "sleepy_carp"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON)
                        .withBaseChance(20)
        );

        register(
                endVoidFishing(new MaybeStack("tide", "chorus_cod"))
                        .withBucketedFish(new MaybeStack("tide", "chorus_cod_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "chorus_cod"))
                        .withSizeAndWeight(80, 40, 12000, 7000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.UNCOMMON)
                        .withBaseChance(15)
        );

        register(
                endVoidFishing(new MaybeStack("tide", "ender_glider"))
                        .withBucketedFish(new MaybeStack("tide", "ender_glider_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "ender_glider"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.UNCOMMON)
                        .withBaseChance(15)
        );

        register(
                endVoidFishing(new MaybeStack("tide", "endergazer"))
                        .withBucketedFish(new MaybeStack("tide", "endergazer_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "endergazer"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDifficulty(Difficulty.HARD_MOVING)
                        .withRarity(Rarity.UNCOMMON)
                        .withBaseChance(15)
        );

        register(
                overworldVoidFishing(new MaybeStack("tide", "blue_neonfish"))
                        .withBucketedFish(new MaybeStack("tide", "blue_neonfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "blue_neonfish"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING)
                        .withRarity(Rarity.UNCOMMON)
                        .withBaseChance(15)
        );

        register(
                overworldVoidFishing(new MaybeStack("tide", "judgment_fish"))
                        .withBucketedFish(new MaybeStack("tide", "judgment_fish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "judgment_fish"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING)
                        .withRarity(Rarity.UNCOMMON)
                        .withBaseChance(15)
        );

        register(
                overworldVoidFishing(new MaybeStack("tide", "deep_blue"))
                        .withBucketedFish(new MaybeStack("tide", "deep_blue_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "deep_blue"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.TWO_AQUA)
                        .withRarity(Rarity.UNCOMMON)
                        .withBaseChance(15)
        );

        register(
                endVoidFishing(new MaybeStack("tide", "violet_carp"))
                        .withBucketedFish(new MaybeStack("tide", "violet_carp_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "violet_carp"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDifficulty(Difficulty.FOUR_BIG_VANISHING)
                        .withRarity(Rarity.UNCOMMON)
                        .withBaseChance(15)
        );

        register(
                overworldVoidFishing(new MaybeStack("tide", "nephrosilu"))
                        .withBucketedFish(new MaybeStack("tide", "nephrosilu_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "nephrosilu"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.FOUR_THIN)
                        .withRarity(Rarity.UNCOMMON)
                        .withBaseChance(15)
        );

        register(
                endVoidFishing(new MaybeStack("tide", "red_40"))
                        .withBucketedFish(new MaybeStack("tide", "red_40_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "red_40"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.FOUR_THIN)
                        .withRarity(Rarity.RARE)
                        .withBaseChance(10)
        );

        register(
                endVoidFishing(new MaybeStack("tide", "dutchman_sock"))
                        .withBucketedFish(new MaybeStack("tide", "dutchman_sock_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "dutchman_sock"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.FOUR_THIN_MOVING)
                        .withRarity(Rarity.RARE)
                        .withBaseChance(10)
        );

        register(
                overworldVoidFishing(new MaybeStack("tide", "vengeance"))
                        .withBucketedFish(new MaybeStack("tide", "vengeance_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "vengeance"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.THREE_BIG_TWO_THIN_VANISHING)
                        .withRarity(Rarity.RARE)
                        .withBaseChance(10)
        );

        register(
                overworldVoidFishing(new MaybeStack("tide", "pentapus"))
                        .withBucketedFish(new MaybeStack("tide", "pentapus_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "pentapus"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.FOUR_THIN_VANISHING)
                        .withRarity(Rarity.RARE)
                        .withBaseChance(10)
        );

        register(
                endVoidFishing(new MaybeStack("tide", "elytrout"))
                        .withBucketedFish(new MaybeStack("tide", "elytrout_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "elytrout"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.SINGLE_BIG_FAST)
                        .withRarity(Rarity.RARE)
                        .withBaseChance(10)
        );

        register(
                endVoidFishing(new MaybeStack("tide", "mantyvern"))
                        .withBucketedFish(new MaybeStack("tide", "mantyvern_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "mantyvern"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.SINGLE_BIG_FAST_VANISHING)
                        .withRarity(Rarity.EPIC)
                        .withBaseChance(10)
        );

        register(
                endVoidFishing(new MaybeStack("tide", "snatcher_squid"))
                        .withBucketedFish(new MaybeStack("tide", "snatcher_squid_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "snatcher_squid"))
                        .withSizeAndWeight(40, 20, 1300, 700)
                        .withDifficulty(Difficulty.TWO_AQUA_ONE_THIN)
                        .withRarity(Rarity.EPIC)
                        .withBaseChance(10)
        );

        register(
                overworldVoidFishing(new MaybeStack("tide", "darkness_eater"))
                        .withBucketedFish(new MaybeStack("tide", "darkness_eater_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "darkness_eater"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.HEAVY_EIGHT_AQUA)
                        .withRarity(Rarity.EPIC)
                        .withBaseChance(10)
        );

        register(
                overworldVoidFishing(new MaybeStack("tide", "shadow_shark"))
                        .withBucketedFish(new MaybeStack("tide", "shadow_shark_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "shadow_shark"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.TWO_AQUA_ONE_THIN)
                        .withRarity(Rarity.EPIC)
                        .withBaseChance(10)
        );

        register(
                endVoidFishing(new MaybeStack("tide", "voidseeker"))
                        .withBucketedFish(new MaybeStack("tide", "voidseeker_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "voidseeker"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.NON_STOP_ACTION_AQUA)
                        .withRarity(Rarity.LEGENDARY)
                        .addRestrictions(BaitRestriction.LEGENDARY_BAIT)
                        .withBaseChance(3)
        );

        register(
                overworldVoidFishing(new MaybeStack("tide", "alpha_fish"))
                        .withBucketedFish(new MaybeStack("tide", "alpha_fish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "alpha_fish"))
                        .withSizeAndWeight(80, 40, 12000, 7000)
                        .withSkipsMinigame()
                        .withRarity(Rarity.LEGENDARY)
                        .addRestrictions(BaitRestriction.LEGENDARY_BAIT)
                        .withBaseChance(3)
        );

        register(
                endVoidFishing(new MaybeStack("tide", "dragon_fish"))
                        .withBucketedFish(new MaybeStack("tide", "dragon_fish_bucket"))
                        .withEntityToSpawn(U.holderEntity("tide", "dragon_fish"))
                        .withSizeAndWeight(500, 150, 700000, 130000)
                        .withDifficulty(Difficulty.WITHER)
                        .withRarity(Rarity.LEGENDARY)
                        .addRestrictions(BaitRestriction.LEGENDARY_BAIT)
                        .withBaseChance(3)
        );

    }
}
