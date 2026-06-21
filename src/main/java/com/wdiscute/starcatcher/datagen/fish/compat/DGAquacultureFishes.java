package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.datagen.fish.FishRegistration;
import com.wdiscute.starcatcher.datagen.fish.PresetRestrictions;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.fishrestrictions.*;
import net.minecraft.data.worldgen.BootstrapContext;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;
import static com.wdiscute.starcatcher.datagen.fish.PresetRestrictions.*;

public class DGAquacultureFishes
{
    public static void bootstrap(BootstrapContext<FishProperties> context)
    {

        //
        //                                                   ,--.   ,--.                                 ,---.
        // ,--,--.  ,---.  ,--.,--.  ,--,--.  ,---. ,--.,--. |  | ,-'  '-. ,--.,--. ,--.--.  ,---.      '.-.  \
        //' ,-.  | | .-. | |  ||  | ' ,-.  | | .--' |  ||  | |  | '-.  .-' |  ||  | |  .--' | .-. :      .-' .'
        //\ '-'  | ' '-' | '  ''  ' \ '-'  | \ `--. '  ''  ' |  |   |  |   '  ''  ' |  |    \   --.     /   '-.
        // `--`--'  `-|  |  `----'   `--`--'  `---'  `----'  `--'   `--'    `----'  `--'     `----'     '-----'
        //            `--'

        //freshwater
        FishRegistration.register(context,
                PresetRestrictions.river(context)
                        .withFish("aquaculture", "smallmouth_bass")
                        .withBucketedFish(new MaybeStack("aquaculture", "smallmouth_bass_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "smallmouth_bass"))
                        .withSizeAndWeight(30, 10, 1500, 500)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
                        .withRarity(Rarity.UNCOMMON)
        );

        FishRegistration.register(context,
                PresetRestrictions.river(context)
                        .withFish("aquaculture", "bluegill")
                        .withBucketedFish(new MaybeStack("aquaculture", "smallmouth_bass_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "smallmouth_bass"))
                        .withSizeAndWeight(15, 3, 300, 200)
        );

        FishRegistration.register(context,
                PresetRestrictions.river(context)
                        .withFish("aquaculture", "brown_trout")
                        .withBucketedFish(new MaybeStack("aquaculture", "brown_trout_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "brown_trout"))
                        .withSizeAndWeight(45, 15, 3000, 2000)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                        .withWeather(WeatherRestriction.CLEAR)
        );

        FishRegistration.register(context,
                PresetRestrictions.river(context)
                        .withFish("aquaculture", "carp")
                        .withBucketedFish(new MaybeStack("aquaculture", "carp_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "carp"))
                        .withSizeAndWeight(60, 20, 10000, 4000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.RARE)
                        .withWeather(WeatherRestriction.RAIN)
        );

        FishRegistration.register(context,
                PresetRestrictions.river(context)
                        .withFish("aquaculture", "catfish")
                        .withBucketedFish(new MaybeStack("aquaculture", "catfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "catfish"))
                        .withSizeAndWeight(150, 40, 100000, 25000)
                        .withDifficulty(Difficulty.THIN_NO_DECAY_NOT_FORGIVING)
                        .withRarity(Rarity.EPIC)
                        .withWeather(WeatherRestriction.RAIN)
        );

        FishRegistration.register(context,
                PresetRestrictions.river(context)
                        .withFish("aquaculture", "gar")
                        .withBucketedFish(new MaybeStack("aquaculture", "gar_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "gar"))
                        .withSizeAndWeight(160, 30, 160000, 20000)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.lake(context)
                        .withFish("aquaculture", "minnow")
                        .withBucketedFish(new MaybeStack("aquaculture", "minnow_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "minnow"))
                        .withSizeAndWeight(6, 4, 10, 4)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.lake(context)
                        .withFish("aquaculture", "muskellunge")
                        .withBucketedFish(new MaybeStack("aquaculture", "muskellunge_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "muskellunge"))
                        .withSizeAndWeight(100, 10, 7000, 3000)
                        .withRarity(Rarity.RARE)
                        .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.lake(context)
                        .withFish("aquaculture", "perch")
                        .withBucketedFish(new MaybeStack("aquaculture", "perch_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "perch"))
                        .withSizeAndWeight(20, 5, 500, 200)
        );

        //arid
        FishRegistration.register(
                context,
                PresetRestrictions.lake(context)
                        .withFish("aquaculture", "bayad")
                        .withBucketedFish(new MaybeStack("aquaculture", "bayad_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "bayad"))
                        .withSizeAndWeight(170, 30, 150000, 20000)
                        .withRarity(Rarity.UNCOMMON)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.warmLake(context)
                        .withFish("aquaculture", "boulti")
                        .withBucketedFish(new MaybeStack("aquaculture", "boulti_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "boulti"))
                        .withSizeAndWeight(40, 10, 4000, 300)
                        .withRarity(Rarity.RARE)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
                        .withDifficulty(Difficulty.HARD)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.warmLake(context)
                        .withFish("aquaculture", "capitaine")
                        .withBucketedFish(new MaybeStack("aquaculture", "capitaine_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "capitaine"))
                        .withSizeAndWeight(130, 50, 12000, 3000)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.warmLake(context)
                        .withFish("aquaculture", "synodontis")
                        .withBucketedFish(new MaybeStack("aquaculture", "synodontis_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "synodontis"))
                        .withSizeAndWeight(35, 15, 1000, 300)
                        .withDifficulty(Difficulty.HARD_MOVING)
                        .withRarity(Rarity.EPIC)
        );

        //arctic ocean
        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("aquaculture", "atlantic_cod")
                        .withBucketedFish(new MaybeStack("aquaculture", "atlantic_cod_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "atlantic_cod"))
                        .withSizeAndWeight(100, 50, 15000, 10000)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("aquaculture", "blackfish")
                        .withBucketedFish(new MaybeStack("aquaculture", "blackfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "blackfish"))
                        .withSizeAndWeight(50, 20, 5000, 3000)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                        .withRarity(Rarity.UNCOMMON)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("aquaculture", "pacific_halibut")
                        .withBucketedFish(new MaybeStack("aquaculture", "pacific_halibut_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "pacific_halibut"))
                        .withSizeAndWeight(150, 50, 80000, 5000)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("aquaculture", "atlantic_halibut")
                        .withBucketedFish(new MaybeStack("aquaculture", "atlantic_halibut_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "atlantic_halibut"))
                        .withSizeAndWeight(200, 80, 150000, 10000)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withWeather(WeatherRestriction.RAIN)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("aquaculture", "atlantic_herring")
                        .withBucketedFish(new MaybeStack("aquaculture", "atlantic_herring_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "atlantic_herring"))
                        .withSizeAndWeight(25, 5, 200, 100)
                        .withDifficulty(Difficulty.HARD_MOVING)
                        .withRarity(Rarity.RARE)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("aquaculture", "pink_salmon")
                        .withBucketedFish(new MaybeStack("aquaculture", "pink_salmon_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "pink_salmon"))
                        .withSizeAndWeight(50, 10, 2000, 1000)
                        .withRarity(Rarity.EPIC)
                        .withWeather(WeatherRestriction.THUNDER)
                        .withDifficulty(Difficulty.HARD)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("aquaculture", "pollock")
                        .withBucketedFish(new MaybeStack("aquaculture", "pollock_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "pollock"))
                        .withSizeAndWeight(70, 30, 5000, 4000)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("aquaculture", "rainbow_trout")
                        .withBucketedFish(new MaybeStack("aquaculture", "rainbow_trout_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "rainbow_trout"))
                        .withSizeAndWeight(60, 20, 2000, 1500)
                        .withRarity(Rarity.UNCOMMON)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
        );

        //saltwater
        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("aquaculture", "jellyfish")
                        .withBucketedFish(new MaybeStack("aquaculture", "jellyfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "jellyfish"))
                        .withSizeAndWeight(100, 70, 50000, 40000)
                        .withRarity(Rarity.UNCOMMON)
                        .withDifficulty(Difficulty.HARD)
                        .withBaseChance(3)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("aquaculture", "red_grouper")
                        .withBucketedFish(new MaybeStack("aquaculture", "red_grouper_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "red_grouper"))
                        .withSizeAndWeight(100, 50, 15000, 10000)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("aquaculture", "tuna")
                        .withBucketedFish(new MaybeStack("aquaculture", "tuna_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "tuna"))
                        .withSizeAndWeight(200, 100, 200000, 150000)
        );

        //jungle
        FishRegistration.register(
                context,
                PresetRestrictions.jungle(context)
                        .withFish("aquaculture", "arapaima")
                        .withBucketedFish(new MaybeStack("aquaculture", "arapaima_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "arapaima"))
                        .withSizeAndWeight(250, 50, 50000, 150000)
                        .withRarity(Rarity.RARE)
                        .withDifficulty(Difficulty.HARD)
                        .withWeather(WeatherRestriction.RAIN)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.jungle(context)
                        .withFish("aquaculture", "arrau_turtle")
                        .withEntityToSpawn(U.holderEntity("aquaculture", "arrau_turtle"))
                        .withSizeAndWeight(100, 30, 80000, 150000)
                        .withDifficulty(Difficulty.MEDIUM)
        );


        FishRegistration.register(
                context,
                PresetRestrictions.jungle(context)
                        .withFish("aquaculture", "piranha")
                        .withBucketedFish(new MaybeStack("aquaculture", "piranha_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "piranha"))
                        .withSizeAndWeight(30, 10, 500, 300)
                        .addBait(BaitRestriction.LEGENDARY_BAIT)
                        .withRarity(Rarity.LEGENDARY)
                        .withDifficulty(Difficulty.FOUR_BIG)
                        .withDaytimeRestriction(DaytimeRestriction.NOON)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.jungle(context)
                        .withFish("aquaculture", "tambaqui")
                        .withBucketedFish(new MaybeStack("aquaculture", "tambaqui_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "tambaqui"))
                        .withSizeAndWeight(100, 30, 150000, 10000)
                        .withRarity(Rarity.UNCOMMON)
                        .withDifficulty(Difficulty.MEDIUM)
        );

        //swamp
        FishRegistration.register(
                context,
                PresetRestrictions.swamp(context)
                        .withFish("aquaculture", "leech")
                        //no bucketed version
                        //no entity
                        .withSizeAndWeight(10, 5, 5, 3)
                        .withRarity(Rarity.RARE)
                        .withDifficulty(Difficulty.HARD)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.swamp(context)
                        .withFish("aquaculture", "box_turtle")
                        //no bucketed version
                        .withEntityToSpawn(U.holderEntity("aquaculture", "box_turtle"))
                        .withSizeAndWeight(20, 5, 1000, 500)
                        .withRarity(Rarity.EPIC)
                        .withDifficulty(Difficulty.HARD)
                        .withWeather(WeatherRestriction.RAIN)
        );

        //mushroom island
        FishRegistration.register(
                context,
                PresetRestrictions.mushroomFields(context)
                        .withFish("aquaculture", "brown_shrooma")
                        .withBucketedFish(new MaybeStack("aquaculture", "brown_shrooma_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "brown_shrooma"))
                        .withSizeAndWeight(100, 20, 3000, 500)
                        .withRarity(Rarity.EPIC)
                        .withDifficulty(Difficulty.FOUR_BIG)
        );

        FishRegistration.register(
                context,
                PresetRestrictions.mushroomFields(context)
                        .withFish("aquaculture", "red_shrooma")
                        .withBucketedFish(new MaybeStack("aquaculture", "brown_shrooma_bucket"))
                        .withEntityToSpawn(U.holderEntity("aquaculture", "brown_shrooma"))
                        .withSizeAndWeight(100, 20, 3000, 500)
                        .withRarity(Rarity.EPIC)
                        .withDifficulty(Difficulty.FOUR_BIG)
        );

        //anywhere
        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("aquaculture", "goldfish")
                        //no bucketed version
                        //no entity
                        .withSizeAndWeight(15, 5, 100, 5)
                        .withBaseChance(1)
                        //0.5% weight
                        .withPercentageChance(0.005f)
        );


        //extras
        //neptunium ingot
        FishRegistration.register(
                context,
                PresetRestrictions.allOceans(context)
                        .withFish("aquaculture", "neptunium_ingot")
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
