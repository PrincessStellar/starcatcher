package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.datagen.fish.FishRegistration;
import com.wdiscute.starcatcher.datagen.fish.PresetRestrictions;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.fishrestrictions.*;
import net.minecraft.data.worldgen.BootstrapContext;
import org.jetbrains.annotations.Nullable;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;
import static com.wdiscute.starcatcher.datagen.fish.PresetRestrictions.*;

public class DGTideFishes
{
    public static void bootstrap(@Nullable BootstrapContext<FishProperties> context)
    {
        //
        //  ,--.   ,--.    ,--.
        //,-'  '-. `--'  ,-|  |  ,---.
        //'-.  .-' ,--. ' .-. | | .-. :
        //  |  |   |  | \ `-' | \   --.
        //  `--'   `--'  `---'   `----'
        //

        //freshwater
        FishRegistration.register(
                context,
                PresetRestrictions.river(context)
                        .withFish("tide", "yellow_perch")
                        .withBucketedFish("tide", "yellow_perch_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "yellow_perch"))
                        .withSizeAndWeight(27, 11, 500, 352)
                        .withDifficulty(Difficulty.EASY_MOVING)
                        .withRarity(Rarity.COMMON)
                        .withDaytimeRestriction(DaytimeRestriction.DAY),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.coldLake(context)
                        .withFish("tide", "rainbow_trout")
                        .withBucketedFish("tide", "rainbow_trout_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "rainbow_trout"))
                        .withSizeAndWeight(35, 8, 1600, 1200)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.river(context)
                        .withFish("tide", "largemouth_bass")
                        .withBucketedFish("tide", "largemouth_bass_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "largemouth_bass"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.river(context)
                        .withFish("tide", "brook_trout")
                        .withBucketedFish("tide", "brook_trout_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "brook_trout"))
                        .withSizeAndWeight(35, 8, 1600, 1200)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.river(context)
                        .withFish("tide", "white_crappie")
                        .withBucketedFish("tide", "white_crappie_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "white_crappie"))
                        .withSizeAndWeight(35, 8, 1600, 1200)
                        .withDifficulty(Difficulty.EASY_VANISHING)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.river(context)
                        .withFish("tide", "black_crappie")
                        .withBucketedFish("tide", "black_crappie_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "black_crappie"))
                        .withSizeAndWeight(35, 8, 1600, 1200)
                        .withDifficulty(Difficulty.EASY_VANISHING)
                        .withRarity(Rarity.COMMON),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.river(context)
                        .withFish("tide", "carp")
                        .withBucketedFish("tide", "carp_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "carp"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.COMMON),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.coldRiver(context)
                        .withFish("tide", "bluegill")
                        .withBucketedFish("tide", "bluegill_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "bluegill"))
                        .withSizeAndWeight(20, 5, 400, 100)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.warmLake(context)
                        .withFish("tide", "guppy")
                        .withBucketedFish("tide", "guppy_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "guppy"))
                        .withSizeAndWeight(20, 5, 400, 100)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.UNCOMMON),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.warmLake(context)
                        .withFish("tide", "walleye")
                        .withBucketedFish("tide", "walleye_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "walleye"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.UNCOMMON)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.warmLake(context)
                        .withFish("tide", "catfish")
                        .withBucketedFish("tide", "catfish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "catfish"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.UNCOMMON)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.cherryGrove(context)
                        .withFish("tide", "blossom_bass")
                        .withBucketedFish("tide", "blossom_bass_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "blossom_bass"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.TWO_AQUA_ONE_THIN)
                        .withRarity(Rarity.RARE),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.jungle(context)
                        .withFish("tide", "arapaima")
                        .withBucketedFish("tide", "arapaima_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "arapaima"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.SINGLE_AQUA)
                        .withRarity(Rarity.RARE),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.warmLake(context)
                        .withFish("tide", "mirage_catfish")
                        .withBucketedFish("tide", "mirage_catfish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "mirage_catfish"))
                        .withSizeAndWeight(100, 50, 10000, 3000)
                        .withDifficulty(Difficulty.SINGLE_AQUA)
                        .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                        .withRarity(Rarity.RARE),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.coldLake(context)
                        .withFish("tide", "frostbite_flounder")
                        .withBucketedFish("tide", "frostbite_flounder_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "frostbite_flounder"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.HARD_MOVING)
                        .withRarity(Rarity.RARE),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.warmLake(context)
                        .withFish("tide", "sand_tiger_shark")
                        .withBucketedFish("tide", "sand_tiger_shark_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "sand_tiger_shark"))
                        .withSizeAndWeight(400, 120, 16000, 11000)
                        .withDifficulty(Difficulty.TWO_AQUA_ONE_THIN)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                        .withRarity(Rarity.RARE),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.coldRiver(context)
                        .withFish("tide", "sturgeon")
                        .withBucketedFish("tide", "sturgeon_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "sturgeon"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.TWO_THIN_NO_DECAY)
                        .withRarity(Rarity.RARE),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.swamp(context)
                        .withFish("tide", "slimy_salmon")
                        .withBucketedFish("tide", "slimy_salmon_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "slimy_salmon"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.TWO_THIN)
                        .withRarity(Rarity.RARE),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.lake(context)
                        .withFish("tide", "mooneye")
                        .withBucketedFish("tide", "mooneye_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "mooneye"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.SINGLE_THIN_FAST)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                        .withRarity(Rarity.EPIC),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.river(context)
                        .withFish("tide", "bull_shark")
                        .withBucketedFish("tide", "bull_shark_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "bull_shark"))
                        .withSizeAndWeight(400, 120, 16000, 11000)
                        .withDifficulty(Difficulty.SINGLE_THIN_FAST)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                        .withRarity(Rarity.EPIC),
                "tide"
        );


        //oceans
        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("tide", "mackerel")
                        .withBucketedFish("tide", "mackerel_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "mackerel"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.EASY)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("tide", "tuna")
                        .withBucketedFish("tide", "tuna_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "tuna"))
                        .withSizeAndWeight(150, 50, 120000, 60000)
                        .withDifficulty(Difficulty.EASY)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("tide", "red_snapper")
                        .withBucketedFish("tide", "red_snapper_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "red_snapper"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("tide", "snook")
                        .withBucketedFish("tide", "snook_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "snook"))
                        .withSizeAndWeight(27.0f, 11, 500, 352)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("tide", "anchovy")
                        .withBucketedFish("tide", "anchovy_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "anchovy"))
                        .withSizeAndWeight(27.0f, 11, 500, 352)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("tide", "flounder")
                        .withBucketedFish("tide", "flounder_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "flounder"))
                        .withSizeAndWeight(27.0f, 11, 500, 352)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.warmLake(context)
                        .withFish("tide", "angelfish")
                        .withBucketedFish("tide", "angelfish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "angelfish"))
                        .withSizeAndWeight(27.0f, 11, 500, 352)
                        .withDifficulty(Difficulty.EASY_FAST_FISH)
                        .withRarity(Rarity.UNCOMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.mushroomFields(context)
                        .withFish("tide", "spore_stalker")
                        .withBucketedFish("tide", "spore_stalker_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "spore_stalker"))
                        .withSizeAndWeight(70, 50, 4000, 2000)
                        .withDifficulty(Difficulty.HARD_MOVING)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.warmLake(context)
                        .withFish("tide", "mahi_mahi")
                        .withBucketedFish("tide", "mahi_mahi_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "mahi_mahi"))
                        .withSizeAndWeight(70, 50, 4000, 2000)
                        .withDifficulty(Difficulty.FOUR_BIG_VANISHING)
                        .withRarity(Rarity.RARE),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.warmLake(context)
                        .withFish("tide", "sailfish")
                        .withBucketedFish("tide", "sailfish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "sailfish"))
                        .withSizeAndWeight(70, 50, 4000, 2000)
                        .withDifficulty(Difficulty.FOUR_THIN)
                        .withRarity(Rarity.RARE),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.warmLake(context)
                        .withFish("tide", "swordfish")
                        .withBucketedFish("tide", "swordfish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "swordfish"))
                        .withSizeAndWeight(70, 50, 4000, 2000)
                        .withDifficulty(Difficulty.TWO_THIN_NO_DECAY)
                        .withRarity(Rarity.RARE),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("tide", "manta_ray")
                        .withBucketedFish("tide", "manta_ray_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "manta_ray"))
                        .withSizeAndWeight(70, 50, 4000, 2000)
                        .withDifficulty(Difficulty.TWO_THIN_NO_DECAY)
                        .withRarity(Rarity.RARE),
                "tide"
        );


        //todo ocean monument structure restriction
        FishRegistration.register(
                context,
                PresetRestrictions.coldOcean(context)
                        .withFish("tide", "aquathorn")
                        .withBucketedFish("tide", "aquathorn_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "aquathorn"))
                        .withSizeAndWeight(70, 50, 4000, 2000)
                        .withDifficulty(Difficulty.SINGLE_AQUA)
                        .withRarity(Rarity.EPIC)
                        .withBaseChance(2),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("tide", "neptune_koi")
                        .withBucketedFish("tide", "neptune_koi_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "neptune_koi"))
                        .withSizeAndWeight(60, 13, 3500, 731)
                        .withDifficulty(Difficulty.SINGLE_AQUA)
                        .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                        .withRarity(Rarity.EPIC),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("tide", "pluto_snail")
                        .withBucketedFish("tide", "pluto_snail_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "pluto_snail"))
                        .withSizeAndWeight(60, 13, 3500, 731)
                        .withDifficulty(Difficulty.TWO_AQUA)
                        .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                        .withRarity(Rarity.EPIC),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("tide", "sun_emblem")
                        .withBucketedFish("tide", "sun_emblem_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "sun_emblem"))
                        .withSizeAndWeight(10, 3, 40, 10)
                        .withDifficulty(Difficulty.TWO_THIN)
                        .withDaytimeRestriction(DaytimeRestriction.NOON)
                        .withRarity(Rarity.EPIC),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("tide", "saturn_cuttlefish")
                        .withBucketedFish("tide", "saturn_cuttlefish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "saturn_cuttlefish"))
                        .withSizeAndWeight(60, 13, 3500, 731)
                        .withDifficulty(Difficulty.TWO_THIN)
                        .withDaytimeRestriction(DaytimeRestriction.NOON)
                        .withRarity(Rarity.EPIC),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("tide", "marstilus")
                        .withBucketedFish("tide", "marstilus_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "marstilus"))
                        .withSizeAndWeight(60, 13, 3500, 731)
                        .withDifficulty(Difficulty.TWO_THIN)
                        .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                        .withRarity(Rarity.EPIC),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("tide", "uranias_pisces")
                        .withBucketedFish("tide", "uranias_pisces_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "uranias_pisces"))
                        .withSizeAndWeight(60, 13, 3500, 731)
                        .withDifficulty(Difficulty.TWO_THIN)
                        .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                        .withRarity(Rarity.EPIC),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("tide", "great_white_shark")
                        .withBucketedFish("tide", "great_white_shark_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "great_white_shark"))
                        .withSizeAndWeight(400, 120, 16000, 11000)
                        .withDifficulty(Difficulty.HARD)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
                        .withRarity(Rarity.EPIC),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.deepOcean(context)
                        .withFish("tide", "shooting_starfish")
                        .withBucketedFish("tide", "shooting_starfish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "shooting_starfish"))
                        .withSizeAndWeight(400, 120, 16000, 11000)
                        .withDifficulty(Difficulty.TWO_THIN)
                        .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                        .withBaseChance(1)
                        .addRestrictions(BaitRestriction.LEGENDARY_BAIT)
                        .withRarity(Rarity.EPIC),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.deepOcean(context)
                        .withFish("tide", "coelacanth")
                        .withBucketedFish("tide", "coelacanth_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "coelacanth"))
                        .withSizeAndWeight(400, 120, 16000, 11000)
                        .withDifficulty(Difficulty.SINGLE_AQUA_MOVING)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                        .withWeather(WeatherRestriction.RAIN)
                        .addRestrictions(BaitRestriction.LEGENDARY_BAIT)
                        .withRarity(Rarity.LEGENDARY),
                "tide"
        );


        //underground
        FishRegistration.register(
                context,
                PresetRestrictions.underground(context)
                        .withFish("tide", "cave_eel")
                        .withBucketedFish("tide", "cave_eel_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "cave_eel"))
                        .withSizeAndWeight(500, 150, 10000, 2000)
                        .withDifficulty(Difficulty.EASY)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.underground(context)
                        .withFish("tide", "deep_grouper")
                        .withBucketedFish("tide", "deep_grouper_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "deep_grouper"))
                        .withSizeAndWeight(50, 15, 1000, 200)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING_MOVING)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.underground(context)
                        .withFish("tide", "cave_crawler")
                        .withBucketedFish("tide", "cave_crawler_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "cave_crawler"))
                        .withSizeAndWeight(50, 15, 1000, 200)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.deepslate(context)
                        .withFish("tide", "shadow_snapper")
                        .withBucketedFish("tide", "shadow_snapper_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "shadow_snapper"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.underground(context)
                        .withFish("tide", "glowfish")
                        .withBucketedFish("tide", "glowfish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "glowfish"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.UNCOMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.caves(context)
                        .withFish("tide", "anglerfish")
                        .withBucketedFish("tide", "anglerfish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "anglerfish"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.UNCOMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.deepslate(context)
                        .withFish("tide", "abyss_angler")
                        .withBucketedFish("tide", "abyss_angler_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "abyss_angler"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.UNCOMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.underground(context)
                        .withFish("tide", "iron_tetra")
                        .withBucketedFish("tide", "iron_tetra_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "iron_tetra"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.EIGHT_STONE_SPOTS)
                        .withRarity(Rarity.UNCOMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.underground(context)
                        .withFish("tide", "dripstone_darter")
                        .withBucketedFish("tide", "dripstone_darter_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "dripstone_darter"))
                        .withSizeAndWeight(6, 2, 7, 6)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.UNCOMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.deepslate(context)
                        .withFish("tide", "lapis_lanternfish")
                        .withBucketedFish("tide", "lapis_lanternfish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "lapis_lanternfish"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.UNCOMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.deepslate(context)
                        .withFish("tide", "crystal_shrimp")
                        .withBucketedFish("tide", "crystal_shrimp_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "crystal_shrimp"))
                        .withSizeAndWeight(10, 3, 20, 10)
                        .withDifficulty(Difficulty.EASY_FAST_FISH)
                        .withRarity(Rarity.UNCOMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.deepslate(context)
                        .withFish("tide", "crystalline_carp")
                        .withBucketedFish("tide", "crystalline_carp_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "crystalline_carp"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDifficulty(Difficulty.TWO_AQUA)
                        .withRarity(Rarity.RARE),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.deepslate(context)
                        .withFish("tide", "luminescent_jellyfish")
                        .withBucketedFish("tide", "crystalline_carp_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "crystalline_carp"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.RARE),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.caves(context)
                        .withFish("tide", "gilded_minnow")
                        .withBucketedFish("tide", "gilded_minnow_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "gilded_minnow"))
                        .withSizeAndWeight(6, 4, 5, 3)
                        .withDifficulty(Difficulty.FOUR_THIN)
                        .withRarity(Rarity.RARE),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.caves(context)
                        .withFish("tide", "bedrock_tetra")
                        .withBucketedFish("tide", "bedrock_tetra_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "bedrock_tetra"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.FOUR_STONE_SPOTS)
                        .withRarity(Rarity.RARE),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.caves(context)
                        .withFish("tide", "windbass")
                        .withBucketedFish("tide", "windbass_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "windbass"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.FOUR_BIG_MOVING)
                        .withBaseChance(2)
                        .withRarity(Rarity.EPIC),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.deepDark(context)
                        .withFish("tide", "echo_snapper")
                        .withBucketedFish("tide", "echo_snapper_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "echo_snapper"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.FOUR_BIG_MOVING)
                        .withRarity(Rarity.EPIC),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.deepDark(context)
                        .withFish("tide", "chasm_eel")
                        .withBucketedFish("tide", "chasm_eel_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "chasm_eel"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.TWO_THIN)
                        .withRarity(Rarity.EPIC),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.deepslate(context)
                        .withFish("tide", "midas_fish")
                        .withBucketedFish("tide", "echo_snapper_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "echo_snapper"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withRarity(Rarity.LEGENDARY)
                        .withDifficulty(Difficulty.SINGLE_THIN_FAST)
                        .withBaseChance(1)
                        .addRestrictions(BaitRestriction.LEGENDARY_BAIT),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.caves(context)
                        .withFish("tide", "devils_hole_pupfish")
                        .withBucketedFish("tide", "devils_hole_pupfish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "devils_hole_pupfish"))
                        .withSizeAndWeight(35, 25, 1000, 700)
                        .withRarity(Rarity.LEGENDARY)
                        .withDifficulty(Difficulty.SINGLE_THIN_FAST)
                        .addRestrictions(BaitRestriction.LEGENDARY_BAIT)
                        .addRestrictions(DimensionRestriction.OVERWORLD,
                                new ElevationRestriction(20, 30, "")
                        ),
                "tide"
        );


        //lava
        FishRegistration.register(
                context,
                PresetRestrictions.surfaceLava(context)
                        .withFish("tide", "magma_mackerel")
                        .withBucketedFish("tide", "magma_mackerel_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "magma_mackerel"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.surfaceLava(context)
                        .withFish("tide", "ember_koi")
                        .withBucketedFish("tide", "ember_koi_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "ember_koi"))
                        .withSizeAndWeight(60, 13, 3500, 731)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.surfaceLava(context)
                        .withFish("tide", "ash_perch")
                        .withBucketedFish("tide", "ash_perch_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "ash_perch"))
                        .withSizeAndWeight(27.0f, 11, 500, 352)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.undergroundLava(context)
                        .withFish("tide", "obsidian_pike")
                        .withBucketedFish("tide", "obsidian_pike_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "obsidian_pike"))
                        .withSizeAndWeight(75, 20, 5000, 3000)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.COMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.undergroundLava(context)
                        .withFish("tide", "volcano_tuna")
                        .withBucketedFish("tide", "volcano_tuna_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "volcano_tuna"))
                        .withSizeAndWeight(150, 50, 120000, 60000)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.UNCOMMON),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.netherLava(context)
                        .withFish("tide", "crimson_fangjaw")
                        .withBucketedFish("tide", "crimson_fangjaw_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "crimson_fangjaw"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING_MOVING)
                        .withRarity(Rarity.RARE),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.netherLava(context)
                        .withFish("tide", "warped_guppy")
                        .withBucketedFish("tide", "warped_guppy_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "warped_guppy"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.HARD_VANISHING)
                        .withRarity(Rarity.RARE),
                "tide"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.netherLava(context)
                        .withFish("tide", "soulscale")
                        .withBucketedFish("tide", "soulscale_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "soulscale"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.HARD_VANISHING)
                        .withRarity(Rarity.RARE),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.netherLava(context)
                        .withFish("tide", "witherfin")
                        .withBucketedFish("tide", "witherfin_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "witherfin"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.RARE),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.netherLava(context)
                        .withFish("tide", "inferno_guppy")
                        .withBucketedFish("tide", "inferno_guppy_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "inferno_guppy"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.RARE),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.netherLava(context)
                        .withFish("tide", "blazing_swordfish")
                        .withBucketedFish("tide", "blazing_swordfish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "blazing_swordfish"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.EPIC),
                "tide"
        );


        //void
        FishRegistration.register(
                context,
                PresetRestrictions.end(context)
                        .withFish("tide", "amber_rockfish")
                        .withBucketedFish("tide", "amber_rockfish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "amber_rockfish"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.TWO_STONE_SPOTS_EASY)
                        .withRarity(Rarity.COMMON)
                        .withBaseChance(20),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.end(context)
                        .withFish("tide", "pale_clubfish")
                        .withBucketedFish("tide", "pale_clubfish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "pale_clubfish"))
                        .withSizeAndWeight(300, 150, 26000, 7000)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING)
                        .withRarity(Rarity.COMMON)
                        .withBaseChance(20),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.end(context)
                        .withFish("tide", "enderfin")
                        .withBucketedFish("tide", "enderfin_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "enderfin"))
                        .withSizeAndWeight(300, 150, 16000, 7000)
                        .withDifficulty(Difficulty.TWO_AQUA)
                        .withRarity(Rarity.COMMON)
                        .withBaseChance(20),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.empty(context)
                        .withFish("tide", "incandescent_larva")
                        .withBucketedFish("tide", "enderfin_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "enderfin"))
                        .withSizeAndWeight(6, 4, 5, 3)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING)
                        .withRarity(Rarity.COMMON)
                        .withBaseChance(20),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.coldOcean(context)
                        .withFish("tide", "bedrock_bug")
                        .withBucketedFish("tide", "bedrock_bug_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "bedrock_bug"))
                        .withSizeAndWeight(300, 150, 26000, 7000)
                        .withDifficulty(Difficulty.TWO_STONE_SPOTS_EASY)
                        .withRarity(Rarity.COMMON)
                        .withBaseChance(20),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.coldOcean(context)
                        .withFish("tide", "sleepy_carp")
                        .withBucketedFish("tide", "sleepy_carp_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "sleepy_carp"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON)
                        .withBaseChance(20),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.end(context)
                        .withFish("tide", "chorus_cod")
                        .withBucketedFish("tide", "chorus_cod_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "chorus_cod"))
                        .withSizeAndWeight(80, 40, 12000, 7000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.UNCOMMON)
                        .withBaseChance(15),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.end(context)
                        .withFish("tide", "ender_glider")
                        .withBucketedFish("tide", "ender_glider_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "ender_glider"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.UNCOMMON)
                        .withBaseChance(15),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.end(context)
                        .withFish("tide", "endergazer")
                        .withBucketedFish("tide", "endergazer_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "endergazer"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDifficulty(Difficulty.HARD_MOVING)
                        .withRarity(Rarity.UNCOMMON)
                        .withBaseChance(15),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.coldOcean(context)
                        .withFish("tide", "blue_neonfish")
                        .withBucketedFish("tide", "blue_neonfish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "blue_neonfish"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING)
                        .withRarity(Rarity.UNCOMMON)
                        .withBaseChance(15),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.coldOcean(context)
                        .withFish("tide", "judgment_fish")
                        .withBucketedFish("tide", "judgment_fish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "judgment_fish"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING)
                        .withRarity(Rarity.UNCOMMON)
                        .withBaseChance(15),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.coldOcean(context)
                        .withFish("tide", "deep_blue")
                        .withBucketedFish("tide", "deep_blue_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "deep_blue"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.TWO_AQUA)
                        .withRarity(Rarity.UNCOMMON)
                        .withBaseChance(15),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.end(context)
                        .withFish("tide", "violet_carp")
                        .withBucketedFish("tide", "violet_carp_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "violet_carp"))
                        .withSizeAndWeight(60, 20, 6000, 4000)
                        .withDifficulty(Difficulty.FOUR_BIG_VANISHING)
                        .withRarity(Rarity.UNCOMMON)
                        .withBaseChance(15),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.coldOcean(context)
                        .withFish("tide", "nephrosilu")
                        .withBucketedFish("tide", "nephrosilu_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "nephrosilu"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.FOUR_THIN)
                        .withRarity(Rarity.UNCOMMON)
                        .withBaseChance(15),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.end(context)
                        .withFish("tide", "red_40")
                        .withBucketedFish("tide", "red_40_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "red_40"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.FOUR_THIN)
                        .withRarity(Rarity.RARE)
                        .withBaseChance(10),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.end(context)
                        .withFish("tide", "dutchman_sock")
                        .withBucketedFish("tide", "dutchman_sock_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "dutchman_sock"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.FOUR_THIN_MOVING)
                        .withRarity(Rarity.RARE)
                        .withBaseChance(10),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.coldOcean(context)
                        .withFish("tide", "vengeance")
                        .withBucketedFish("tide", "vengeance_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "vengeance"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.THREE_BIG_TWO_THIN_VANISHING)
                        .withRarity(Rarity.RARE)
                        .withBaseChance(10),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.coldOcean(context)
                        .withFish("tide", "pentapus")
                        .withBucketedFish("tide", "pentapus_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "pentapus"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.FOUR_THIN_VANISHING)
                        .withRarity(Rarity.RARE)
                        .withBaseChance(10),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.end(context)
                        .withFish("tide", "elytrout")
                        .withBucketedFish("tide", "elytrout_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "elytrout"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.SINGLE_BIG_FAST)
                        .withRarity(Rarity.RARE)
                        .withBaseChance(10),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.end(context)
                        .withFish("tide", "mantyvern")
                        .withBucketedFish("tide", "mantyvern_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "mantyvern"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.SINGLE_BIG_FAST_VANISHING)
                        .withRarity(Rarity.EPIC)
                        .withBaseChance(10),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.end(context)
                        .withFish("tide", "snatcher_squid")
                        .withBucketedFish("tide", "snatcher_squid_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "snatcher_squid"))
                        .withSizeAndWeight(40, 20, 1300, 700)
                        .withDifficulty(Difficulty.TWO_AQUA_ONE_THIN)
                        .withRarity(Rarity.EPIC)
                        .withBaseChance(10),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.coldOcean(context)
                        .withFish("tide", "darkness_eater")
                        .withBucketedFish("tide", "darkness_eater_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "darkness_eater"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.HEAVY_EIGHT_AQUA)
                        .withRarity(Rarity.EPIC)
                        .withBaseChance(10),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.coldOcean(context)
                        .withFish("tide", "shadow_shark")
                        .withBucketedFish("tide", "shadow_shark_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "shadow_shark"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.TWO_AQUA_ONE_THIN)
                        .withRarity(Rarity.EPIC)
                        .withBaseChance(10),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.end(context)
                        .withFish("tide", "voidseeker")
                        .withBucketedFish("tide", "voidseeker_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "voidseeker"))
                        .withSizeAndWeight(60, 20, 7000, 2000)
                        .withDifficulty(Difficulty.NON_STOP_ACTION_AQUA)
                        .withRarity(Rarity.LEGENDARY)
                        .addRestrictions(BaitRestriction.LEGENDARY_BAIT)
                        .withBaseChance(3),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.coldOcean(context)
                        .withFish("tide", "alpha_fish")
                        .withBucketedFish("tide", "alpha_fish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "alpha_fish"))
                        .withSizeAndWeight(80, 40, 12000, 7000)
                        .withSkipsMinigame()
                        .withRarity(Rarity.LEGENDARY)
                        .addRestrictions(BaitRestriction.LEGENDARY_BAIT)
                        .withBaseChance(3),
                "tide"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.end(context)
                        .withFish("tide", "dragon_fish")
                        .withBucketedFish("tide", "dragon_fish_bucket")
                        .withEntityToSpawn(U.holderEntity("tide", "dragon_fish"))
                        .withSizeAndWeight(500, 150, 700000, 130000)
                        .withDifficulty(Difficulty.WITHER)
                        .withRarity(Rarity.LEGENDARY)
                        .addRestrictions(BaitRestriction.LEGENDARY_BAIT)
                        .withBaseChance(3),
                "tide"
        );

    }
}
