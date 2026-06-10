package com.wdiscute.starcatcher.datagen.fish;

import com.wdiscute.starcatcher.fish.*;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.SCBlocks;
import com.wdiscute.starcatcher.registry.fishrestrictions.*;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;

import java.util.ArrayList;
import java.util.List;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGStarcatcherFishes
{
    public static final List<FishProperties> ALL_FISHABLE = new ArrayList<>();
    public static final List<FishProperties> STARCATCHER_FISHABLE = new ArrayList<>();
    public static final List<FishProperties> STARCATCHER_BUCKETABLE = new ArrayList<>();

    public static void bootstrap()
    {
        register(overworldLakeFish(new MaybeStack(SCItems.OBIDONTIEE))
                .withSizeAndWeight(new SizeAndWeight(17.7f, 5, 1200, 200)));

        register(overworldLakeFish(new MaybeStack(SCItems.MORGANITE))
                .withSeasons(SeasonRestriction.SUMMER_AUTUMN)
                .withSizeAndWeight(new SizeAndWeight(120, 80, 7000, 1000))
                .withWeather(WeatherRestriction.RAIN)
                .withBaseChance(8)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM));

        register(overworldLakeFish(new MaybeStack(SCItems.SILVERVEIL_PERCH))
                .withSeasons(SeasonRestriction.SPRING_WINTER)
                .withSizeAndWeight(new SizeAndWeight(27.0f, 11, 500, 352))
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM_MOVING));

        register(overworldLakeFish(new MaybeStack(SCItems.ELDERSCALE))
                .withSizeAndWeight(new SizeAndWeight(160.0f, 85, 2300, 652))
                .withSeasons(SeasonRestriction.SUMMER_AUTUMN)
                .withDifficulty(Difficulty.EASY_VANISHING)
                .withRarity(Rarity.UNCOMMON)
                .withBaseChance(3));

        register(overworldLakeFish(new MaybeStack(SCItems.DRIFTFIN))
                .withSizeAndWeight(new SizeAndWeight(16.0f, 3, 167, 70))
                .withSeasons(SeasonRestriction.SUMMER_AUTUMN)
                .withWeather(WeatherRestriction.CLEAR));

        register(overworldLakeFish(new MaybeStack(SCItems.TWILIGHT_KOI))
                .withSizeAndWeight(new SizeAndWeight(60, 13, 3500, 731))
                .withBaseChance(25)
                .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                .withRarity(Rarity.EPIC)
                .withWeather(WeatherRestriction.RAIN)
                .withDifficulty(Difficulty.HARD_MOVING));

        register(overworldLakeFish(new MaybeStack(SCItems.THUNDER_BASS))
                .withSizeAndWeight(new SizeAndWeight(40, 12, 1200, 800))
                .withSeasons(SeasonRestriction.SUMMER_AUTUMN)
                .withRarity(Rarity.RARE)
                .withBaseChance(15)
                .withWeather(WeatherRestriction.THUNDER)
                .withDifficulty(Difficulty.HARD));

        register(overworldLakeFish(new MaybeStack(SCItems.LIGHTNING_BASS))
                .withSizeAndWeight(new SizeAndWeight(40, 12, 1300, 620))
                .withSeasons(SeasonRestriction.SUMMER_AUTUMN)
                .withBaseChance(15)
                .withRarity(Rarity.RARE)
                .withWeather(WeatherRestriction.THUNDER)
                .withDifficulty(Difficulty.HARD_VANISHING));

        register(overworldLakeFish(new MaybeStack(SCItems.BOOT)).withBaseChance(1)
                .withDifficulty(Difficulty.TRASH)
                .withRarity(Rarity.TRASH)
                .withHasGuideEntry(false));


        //cold lake
        register(overworldColdLakeFish(new MaybeStack(SCItems.FROSTJAW_TROUT))
                .withSizeAndWeight(new SizeAndWeight(35, 8, 1600, 1200))
                .withDifficulty(Difficulty.FOUR_BIG_VANISHING)
        );

        register(overworldColdLakeFish(new MaybeStack(SCItems.CRYSTALBACK_TROUT))
                .withSizeAndWeight(new SizeAndWeight(35, 8, 1600, 1200))
                .withSeasons(SeasonRestriction.AUTUMN_WINTER)
                .withDifficulty(Difficulty.MEDIUM));

        register(overworldColdLakeFish(new MaybeStack(SCItems.AURORA))
                .withSizeAndWeight(new SizeAndWeight(10, 8, 120, 30))
                .withRarity(Rarity.LEGENDARY)
                .addBait(BaitRestriction.LEGENDARY_BAIT)
                .withBaseChance(2)
                .withDifficulty(Difficulty.AURORA));

        register(overworldColdLakeFish(new MaybeStack(SCItems.WINTERY_PIKE))
                .withSeasons(SeasonRestriction.AROUND_WINTER)
                .withSizeAndWeight(new SizeAndWeight(75, 20, 5000, 3000))
                .withDifficulty(Difficulty.EASY_MOVING));


        //lake warm
        register(overworldWarmLakeFish(new MaybeStack(SCItems.SANDTAIL))
                .withSizeAndWeight(new SizeAndWeight(200, 100, 1600, 1200))
                .withSeasons(SeasonRestriction.SUMMER_AUTUMN)
                .withBaseChance(8)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT));

        register(overworldWarmLakeFish(new MaybeStack(SCItems.MIRAGE_CARP))
                .withSizeAndWeight(new SizeAndWeight(60, 20, 6000, 4000))
                .withDifficulty(Difficulty.MEDIUM)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
                .withWeather(WeatherRestriction.CLEAR)
                .withRarity(Rarity.UNCOMMON));

        register(overworldWarmLakeFish(new MaybeStack(SCItems.SCORCHFISH))
                .withSizeAndWeight(new SizeAndWeight(60, 20, 6000, 4000))
                .withSeasons(SeasonRestriction.NOT_WINTER)
                .withWeather(WeatherRestriction.CLEAR));

        register(overworldWarmLakeFish(new MaybeStack(SCItems.CACTIFISH))
                .withSizeAndWeight(new SizeAndWeight(100, 50, 10000, 3000))
                .withSeasons(SeasonRestriction.SUMMER)
                .withDaytimeRestriction(DaytimeRestriction.DAY));

        register(overworldWarmLakeFish(new MaybeStack(SCItems.AGAVE_BREAM))
                .withSizeAndWeight(new SizeAndWeight(36, 12, 2000, 1000))
                .withRarity(Rarity.RARE)
                .withBaseChance(8)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                .withWeather(WeatherRestriction.CLEAR)
                .withDifficulty(Difficulty.HARD)
        );


        //mountain
        register(overworldMountainFish(new MaybeStack(SCItems.SUNNY_STURGEON))
                .withSizeAndWeight(new SizeAndWeight(400, 200, 100000, 50000))
                .withSeasons(SeasonRestriction.SPRING_SUMMER)
                .withDifficulty(Difficulty.HARD_MOVING)
                .withRarity(Rarity.RARE)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
                .withBaseChance(2));

        register(overworldMountainFish(new MaybeStack(SCItems.PEAKDWELLER))
                .withSeasons(SeasonRestriction.AROUND_AUTUMN)
                .withSizeAndWeight(new SizeAndWeight(100, 50, 10000, 5000))
                .withDifficulty(Difficulty.HARD));

        register(overworldMountainFish(new MaybeStack(SCItems.ROCKGILL))
                .withSizeAndWeight(new SizeAndWeight(100, 50, 10000, 5000))
                .withDifficulty(Difficulty.MEDIUM));

        register(overworldMountainFish(new MaybeStack(SCItems.SUN_SEEKING_CARP))
                .withSeasons(SeasonRestriction.AROUND_SUMMER)
                .withSizeAndWeight(new SizeAndWeight(60, 20, 6000, 4000))
                .withRarity(Rarity.RARE)
                .withBaseChance(15)
                .withDaytimeRestriction(DaytimeRestriction.NOON));


        //swamp
        register(overworldSwampFish(new MaybeStack(SCItems.SLUDGE_CATFISH))
                .withSizeAndWeight(new SizeAndWeight(100, 50, 10000, 3000))
                .withSeasons(SeasonRestriction.SPRING_SUMMER)
                .withRarity(Rarity.UNCOMMON));

        register(overworldSwampFish(new MaybeStack(SCItems.LILY_SNAPPER))
                .withSizeAndWeight(new SizeAndWeight(60, 20, 7000, 2000))
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.MEDIUM));

        register(overworldSwampFish(new MaybeStack(SCItems.SAGE_CATFISH))
                .withSizeAndWeight(new SizeAndWeight(100, 50, 10000, 3000))
                .withSeasons(SeasonRestriction.AROUND_WINTER)
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.SINGLE_BIG_FAST)
                .withBaseChance(8)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                .withWeather(WeatherRestriction.CLEAR));

        register(overworldSwampFish(new MaybeStack(SCItems.MOSSY_BOOT)).withBaseChance(1)
                .withDifficulty(Difficulty.TRASH)
                .withRarity(Rarity.TRASH)
                .withHasGuideEntry(false));


        //darkoak forest
        register(overworldDarkForestFish(new MaybeStack(SCItems.PALE_PINFISH))
                .withSizeAndWeight(new SizeAndWeight(15, 5, 150, 100))
                .withSeasons(SeasonRestriction.AROUND_WINTER)
                .withBaseChance(15)
                .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.TWO_AQUA_ONE_THIN));

        register(overworldDarkForestFish(new MaybeStack(SCItems.PINFISH))
                .withSizeAndWeight(new SizeAndWeight(15, 5, 150, 100))
                .withBaseChance(8)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM));

        register(overworldDarkForestFish(new MaybeStack(SCItems.PALE_CARP))
                .withSeasons(SeasonRestriction.AROUND_WINTER)
                .withSizeAndWeight(new SizeAndWeight(60, 20, 6000, 4000))
                .withDaytimeRestriction(DaytimeRestriction.DAY));


        //cherry grove
        register(overworldCherryGroveFish(new MaybeStack(SCItems.VESANI))
                .withSeasons(SeasonRestriction.SPRING_WINTER)
                .withSizeAndWeight(new SizeAndWeight(10, 3, 67, 0))
                .withRarity(Rarity.LEGENDARY)
                .addBait(BaitRestriction.LEGENDARY_BAIT)
                .withDifficulty(Difficulty.NO_SWEET_SPOTS.withHP(300)));

        register(overworldCherryGroveFish(new MaybeStack(SCItems.BLOSSOMFISH))
                .withSeasons(SeasonRestriction.SPRING_SUMMER)
                .withSizeAndWeight(new SizeAndWeight(60, 20, 6000, 4000))
                .withWeather(WeatherRestriction.CLEAR));

        register(overworldCherryGroveFish(new MaybeStack(SCItems.PETALDRIFT_CARP))
                .withSizeAndWeight(new SizeAndWeight(60, 20, 6000, 4000))
                .withDifficulty(Difficulty.MEDIUM)
                .withWeather(WeatherRestriction.RAIN)
                .withBaseChance(8)
                .withRarity(Rarity.UNCOMMON));

        register(overworldCherryGroveFish(new MaybeStack(SCItems.PINK_KOI))
                .withSeasons(SeasonRestriction.SPRING_AUTUMN)
                .withSizeAndWeight(new SizeAndWeight(60, 20, 3000, 2000))
                .withBaseChance(8)
                .withWeather(WeatherRestriction.RAIN));

        register(overworldCherryGroveFish(new MaybeStack(SCItems.ROSE_SIAMESE_FISH))
                .withSeasons(SeasonRestriction.SPRING_AUTUMN)
                .withSizeAndWeight(new SizeAndWeight(30, 10, 1000, 500))
                .withDifficulty(Difficulty.MEDIUM_VANISHING)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
                .withBaseChance(8)
                .withWeather(WeatherRestriction.RAIN)
                .withRarity(Rarity.EPIC));


        //cold mountain
        register(overworldColdMountainFish(new MaybeStack(SCItems.CRYSTALBACK_STURGEON))
                .withSizeAndWeight(new SizeAndWeight(400, 200, 100000, 50000)));

        register(overworldColdMountainFish(new MaybeStack(SCItems.ICETOOTH_STURGEON))
                .withSeasons(SeasonRestriction.AROUND_SPRING)
                .withSizeAndWeight(new SizeAndWeight(400, 200, 100000, 50000))
                .withDifficulty(Difficulty.MEDIUM));

        register(overworldColdMountainFish(new MaybeStack(SCItems.BOREAL))
                .withSizeAndWeight(new SizeAndWeight(30, 15, 1000, 200))
                .withDifficulty(Difficulty.MEDIUM_VANISHING_MOVING)
                .withRarity(Rarity.LEGENDARY)
                .addBait(BaitRestriction.LEGENDARY_BAIT)
                .withBaseChance(3));

        register(overworldColdMountainFish(new MaybeStack(SCItems.CRYSTALBACK_BOREAL))
                .withSeasons(SeasonRestriction.AROUND_WINTER)
                .withSizeAndWeight(new SizeAndWeight(30, 15, 6000, 2000))
                .withDifficulty(Difficulty.MEDIUM));


        //river
        register(overworldRiverFish(new MaybeStack(SCItems.DOWNFALL_BREAM))
                .withSizeAndWeight(new SizeAndWeight(36, 12, 2000, 1000))
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                .withBaseChance(18)
                .withWeather(WeatherRestriction.RAIN)
                .withDifficulty(Difficulty.EASY_VANISHING));

        register(overworldRiverFish(new MaybeStack(SCItems.DRIFTING_BREAM))
                .withSeasons(SeasonRestriction.SUMMER_AUTUMN)
                .withSizeAndWeight(new SizeAndWeight(36, 12, 2000, 1000))
                .withBaseChance(8)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                .withDifficulty(Difficulty.EASY_MOVING));

        register(overworldRiverFish(new MaybeStack(SCItems.WILLOW_BREAM))
                .withSeasons(SeasonRestriction.SUMMER_AUTUMN)
                .withSizeAndWeight(new SizeAndWeight(36, 12, 2000, 1000))
                .withBaseChance(8)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                .withDifficulty(Difficulty.HARD_VANISHING.withHP(200))
                .withRarity(Rarity.EPIC));

        register(overworldRiverFish(new MaybeStack(SCItems.HOLLOWBELLY_DARTER))
                .withSeasons(SeasonRestriction.SPRING_SUMMER)
                .withSizeAndWeight(new SizeAndWeight(6, 2, 7, 6))
                .withDifficulty(Difficulty.EASY_MOVING));

        register(overworldRiverFish(new MaybeStack(SCItems.MISTBACK_CHUB))
                .withSeasons(SeasonRestriction.SPRING_SUMMER)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
                .withSizeAndWeight(new SizeAndWeight(30, 10, 1400, 600)));

        register(overworldRiverFish(new MaybeStack(SCItems.BLUEGIGI))
                .withSeasons(SeasonRestriction.SPRING_SUMMER)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
                .withSizeAndWeight(new SizeAndWeight(20, 5, 400, 100)));

        register(overworldRiverFish(new MaybeStack(SCItems.SILVERFIN_PIKE))
                .withSizeAndWeight(new SizeAndWeight(75, 20, 5000, 3000))
                .withDifficulty(Difficulty.EASY_MOVING)
                .withRarity(Rarity.UNCOMMON));

        register(overworldRiverFish(new MaybeStack(SCItems.CARPENJOE))
                .withSizeAndWeight(new SizeAndWeight(178, 0, 72000, 0))
                .withDifficulty(Difficulty.HARD_VANISHING)
                .withBaseChance(2)
                .withRarity(Rarity.EPIC));

        register(overworldRiverFish(new MaybeStack(SCItems.DRIED_SEAWEED))
                .withDifficulty(Difficulty.TRASH)
                .withRarity(Rarity.TRASH)
                .withBaseChance(1)
                .withHasGuideEntry(false));


        //cold river
        register(overworldColdRiverFish(new MaybeStack(SCItems.FROSTGILL_CHUB))
                .withSizeAndWeight(new SizeAndWeight(30, 10, 1400, 600))
                .withSeasons(SeasonRestriction.NOT_WINTER));

        register(overworldColdRiverFish(new MaybeStack(SCItems.CRYSTALBACK_MINNOW))
                .withSizeAndWeight(new SizeAndWeight(6, 4, 5, 3))
                .withSeasons(SeasonRestriction.WINTER)
                .withDifficulty(Difficulty.EASY_MOVING)
                .withBaseChance(8)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT));

        register(overworldColdRiverFish(new MaybeStack(SCItems.AZURE_CRYSTALBACK_MINNOW))
                .withSizeAndWeight(new SizeAndWeight(6, 4, 5, 3))
                .withBaseChance(25)
                .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                .withRarity(Rarity.LEGENDARY)
                .addBait(BaitRestriction.LEGENDARY_BAIT)
                .withDifficulty(Difficulty.NON_STOP_ACTION_THREE_BIG));

        register(overworldColdRiverFish(new MaybeStack(SCItems.BLUE_CRYSTAL_FIN))
                .withSeasons(SeasonRestriction.AROUND_SPRING)
                .withSizeAndWeight(new SizeAndWeight(12, 4, 70, 30))
                .withDaytimeRestriction(DaytimeRestriction.DAY)
                .withDifficulty(Difficulty.EASY_MOVING)
                .withRarity(Rarity.UNCOMMON));


        //ocean
        register(overworldOceanFish(new MaybeStack(SCItems.SEA_BASS))
                .withSeasons(SeasonRestriction.NOT_SPRING)
                .withSizeAndWeight(new SizeAndWeight(40, 12, 1600, 1100))
                .withBaseChance(15)
                .withDifficulty(Difficulty.EASY_MOVING)
                .withDaytimeRestriction(DaytimeRestriction.DAY));

        register(overworldOceanFish(new MaybeStack(SCItems.BLUE_HERRING))
                .withSizeAndWeight(new SizeAndWeight(40, 12, 1600, 1100))
                .withDifficulty(Difficulty.HARD_MOVING)
                .withBaseChance(3)
                .withRarity(Rarity.RARE)
                .withDaytimeRestriction(DaytimeRestriction.DAY));

        register(overworldOceanFish(new MaybeStack(SCItems.IRONJAW_HERRING))
                .withSizeAndWeight(new SizeAndWeight(30, 8, 300, 100))
                .withDifficulty(Difficulty.EIGHT_THIN_VANISHING)
                .withBaseChance(2)
                .withRarity(Rarity.UNCOMMON));

        register(overworldOceanFish(new MaybeStack(SCItems.DEEPJAW_HERRING))
                .withSeasons(SeasonRestriction.SPRING_SUMMER)
                .withSizeAndWeight(new SizeAndWeight(30, 8, 300, 100))
                .withDifficulty(Difficulty.MEDIUM));

        register(overworldOceanFish(new MaybeStack(SCItems.DUSKTAIL_SNAPPER))
                .withSeasons(SeasonRestriction.SPRING_SUMMER)
                .withSizeAndWeight(new SizeAndWeight(60, 20, 7000, 2000)));

        register(overworldOceanFish(new MaybeStack(SCItems.JOEL))
                .withSeasons(SeasonRestriction.SUMMER)
                .withSizeAndWeight(new SizeAndWeight(69, 0, 2000, 600))
                .withDifficulty(Difficulty.JOEL)
                .withBaseChance(1)
                .addBait(BaitRestriction.LEGENDARY_BAIT)
                .withRarity(Rarity.LEGENDARY));

        register(overworldOceanFish(new MaybeStack(SCItems.REDSCALED_TUNA))
                .withSizeAndWeight(new SizeAndWeight(150, 50, 120000, 60000))
                .withBaseChance(8)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.SINGLE_BIG_FAST));

        //deep ocean
        register(overworldDeepOceanFish(new MaybeStack(SCItems.BIGEYE_TUNA))
                .withSeasons(SeasonRestriction.SPRING_WINTER)
                .withSizeAndWeight(new SizeAndWeight(150, 50, 120000, 60000))
                .withBaseChance(8)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.HARD_MOVING));

        //beach
        register(overworldBeachFish(new MaybeStack(SCBlocks.CONCH))
                .withSizeAndWeight(new SizeAndWeight(5, 2, 100, 49))
                .withBaseChance(1)
                .withRarity(Rarity.TRASH)
                .withDifficulty(Difficulty.TRASH));

        register(overworldBeachFish(new MaybeStack(SCBlocks.CLAM))
                .withSizeAndWeight(new SizeAndWeight(20, 5, 1000, 400))
                .withBaseChance(1)
                .withRarity(Rarity.TRASH)
                .withDifficulty(Difficulty.TRASH));

        //mushroom islands
        register(overworldMushroomFieldsFish(new MaybeStack(SCItems.SHROOMFISH))
                .withSizeAndWeight(new SizeAndWeight(70, 50, 4000, 2000))
                .withRarity(Rarity.LEGENDARY)
                .addBait(BaitRestriction.LEGENDARY_BAIT)
                .withDifficulty(Difficulty.EIGHT_THIN_MOVING_VANISHING));

        register(overworldMushroomFieldsFish(new MaybeStack(SCItems.SPOREFISH))
                .withSizeAndWeight(new SizeAndWeight(70, 50, 4000, 2000))
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.HARD_MOVING));


        //underground
        register(overworldUndergroundFish(new MaybeStack(SCItems.GOLD_FAN))
                .withSizeAndWeight(new SizeAndWeight(70, 50, 4000, 2000)));

        register(overworldUndergroundFish(new MaybeStack(SCItems.GEODE_EEL))
                .withSizeAndWeight(new SizeAndWeight(500, 150, 10000, 2000))
                .withRarity(Rarity.EPIC)
                .withBaseChance(1)
                .withDifficulty(Difficulty.HARD_VANISHING));

        //caves
        register(overworldCavesFish(new MaybeStack(SCItems.WHITEVEIL))
                .withSizeAndWeight(new SizeAndWeight(100, 30, 33000, 7000))
                .withDifficulty(Difficulty.EASY_STONE));

        register(overworldCavesFish(new MaybeStack(SCItems.BLACK_EEL))
                .withSizeAndWeight(new SizeAndWeight(500, 150, 6000, 2000))
                .withDifficulty(Difficulty.MEDIUM_STONE)
                .withRarity(Rarity.UNCOMMON));

        register(overworldCavesFish(new MaybeStack(SCItems.STONEFISH))
                .withSizeAndWeight(new SizeAndWeight(300, 150, 26000, 7000))
                .withRarity(Rarity.LEGENDARY)
                .addBait(BaitRestriction.LEGENDARY_BAIT)
                .withDifficulty(Difficulty.STONEFISH));

        register(fish(new MaybeStack(SCItems.AMETHYSTBACK))
                .withSizeAndWeight(new SizeAndWeight(300, 150, 16000, 7000))
                .withDifficulty(Difficulty.SINGLE_BIG_FAST)
                .withRarity(Rarity.EPIC)
                .withRestrictions(List.of(
                        DimensionRestriction.OVERWORLD,
                        new ElevationRestriction(-40, -20, ""))
                )
        );

        //dripstone caves
        register(overworldDripstoneCavesFish(new MaybeStack(SCItems.FOSSILIZED_ANGELFISH))
                .withSizeAndWeight(new SizeAndWeight(700, 150, 36000, 7000))
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.TWO_THIN_NO_DECAY));

        register(overworldDripstoneCavesFish(new MaybeStack(SCItems.DRIPFIN))
                .withSizeAndWeight(new SizeAndWeight(300, 150, 16000, 7000))
                .withDifficulty(Difficulty.EASY_MOVING));

        register(overworldDripstoneCavesFish(new MaybeStack(SCItems.YELLOWSTONE_FISH))
                .withSizeAndWeight(new SizeAndWeight(600, 150, 22000, 7000))
                .withDifficulty(Difficulty.MEDIUM)
                .withRarity(Rarity.UNCOMMON));


        //lush caves
        register(overworldLushCavesFish(new MaybeStack(SCItems.LUSH_PIKE))
                .withSizeAndWeight(new SizeAndWeight(75, 20, 5000, 3000))
                .withDifficulty(Difficulty.HEAVY_EIGHT_AQUA_MOVING)
                .addBait(BaitRestriction.LEGENDARY_BAIT)
                .withRarity(Rarity.LEGENDARY)
                .withBaseChance(2));

        register(overworldLushCavesFish(new MaybeStack(SCItems.VIVID_MOSS))
                .withSizeAndWeight(new SizeAndWeight(120, 70, 7000, 3000))
                .withDifficulty(Difficulty.HARD_MOVING)
                .withRarity(Rarity.UNCOMMON)
                .withBaseChance(4));

        register(fish(new MaybeStack(SCItems.THE_QUARRISH))
                .withSizeAndWeight(new SizeAndWeight(620, 270, 700000, 300000))
                .withDifficulty(Difficulty.HEAVY_FIVE_NORMAL)
                .withRarity(Rarity.EPIC)
                .withRestrictions(WorldRestrictions.OVERWORLD_LUSH_CAVES_AND_JUNGLES));


        //deepslate
        register(overworldDeepslateFish(new MaybeStack(SCItems.GHOSTLY_PIKE))
                .withSizeAndWeight(new SizeAndWeight(75, 20, 5000, 3000))
                .withRarity(Rarity.UNCOMMON)
                .withBaseChance(2));

        register(overworldDeepslateFish(new MaybeStack(SCItems.DEEPSLATEFISH))
                .withSizeAndWeight(new SizeAndWeight(420, 70, 70000, 20000))
                .withDifficulty(Difficulty.HARD));

        register(overworldDeepslateFish(new MaybeStack(SCItems.AQUAMARINE_PIKE))
                .withSizeAndWeight(new SizeAndWeight(75, 20, 5000, 3000))
                .withDifficulty(Difficulty.MEDIUM));

        register(overworldDeepslateFish(new MaybeStack(SCItems.GARNET_MACKEREL))
                .withSizeAndWeight(new SizeAndWeight(40, 20, 2000, 1500))
                .withDifficulty(Difficulty.HARD_VANISHING)
                .withRarity(Rarity.UNCOMMON));

        register(overworldDeepslateFish(new MaybeStack(SCItems.BRIGHT_AMETHYST_SNAPPER))
                .withSizeAndWeight(new SizeAndWeight(60, 20, 7000, 2000))
                .withDifficulty(Difficulty.HARD)
                .withRarity(Rarity.EPIC)
                .withBaseChance(2));

        register(overworldDeepslateFish(new MaybeStack(SCItems.DARK_AMETHYST_SNAPPER))
                .withSizeAndWeight(new SizeAndWeight(60, 20, 7000, 2000))
                .withDifficulty(Difficulty.EIGHT_THIN_MOVING)
                .withRarity(Rarity.EPIC)
                .withBaseChance(2));


        //deep dark
        register(overworldDeepDarkFish(new MaybeStack(SCItems.SCULKFISH))
                .withSizeAndWeight(new SizeAndWeight(30, 10, 2000, 600))
                .withDifficulty(Difficulty.MEDIUM_MOVING.withHP(200))
                .withRarity(Rarity.EPIC));

        register(overworldDeepDarkFish(new MaybeStack(SCItems.WARD))
                .withSizeAndWeight(new SizeAndWeight(50, 10, 2600, 600))
                .withDifficulty(Difficulty.HARD_VANISHING.withHP(500))
                .addBait(BaitRestriction.LEGENDARY_BAIT)
                .withRarity(Rarity.LEGENDARY)
                .withBaseChance(2));

        register(overworldDeepDarkFish(new MaybeStack(SCItems.GLOWING_DARK))
                .withSizeAndWeight(new SizeAndWeight(100, 10, 3000, 600))
                .withDifficulty(Difficulty.SINGLE_BIG_FAST_MOVING.withHP(300))
                .withRarity(Rarity.UNCOMMON));


        //overworld surface lava
        register(overworldSurfaceLava(new MaybeStack(SCItems.SUNEATER))
                .withSizeAndWeight(new SizeAndWeight(100, 10, 3000, 600))
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.SINGLE_BIG_FAST_MOVING));

        register(overworldSurfaceLava(new MaybeStack(SCItems.PYROTROUT))
                .withSizeAndWeight(new SizeAndWeight(40, 20, 1200, 700))
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM));

        register(overworldSurfaceLava(new MaybeStack(SCItems.OBSIDIAN_EEL))
                .withSizeAndWeight(new SizeAndWeight(500, 150, 70000, 13000))
                .withWeather(WeatherRestriction.RAIN)
                .withBaseChance(8)
                .withDifficulty(Difficulty.MEDIUM_VANISHING_MOVING)
                .addBait(BaitRestriction.LEGENDARY_BAIT)
                .withRarity(Rarity.LEGENDARY));

        //overworld underground lava
        register(overworldUndergroundLava(new MaybeStack(SCItems.MOLTEN_SHRIMP))
                .withSizeAndWeight(new SizeAndWeight(10, 3, 20, 10))
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.HARD));

        register(overworldUndergroundLava(new MaybeStack(SCItems.OBSIDIAN_CRAB))
                .withSizeAndWeight(new SizeAndWeight(15, 8, 700, 300))
                .withDifficulty(Difficulty.OBSIDIAN_CRAB)
                .withRarity(Rarity.EPIC));

        //overworld deepslate lava
        register(overworldDeepslateLava(new MaybeStack(SCItems.SCORCHED_BLOODSUCKER))
                .withSizeAndWeight(new SizeAndWeight(60, 30, 1700, 300))
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.HARD));

        register(overworldDeepslateLava(new MaybeStack(SCItems.MOLTEN_DEEPSLATE_CRAB))
                .withSizeAndWeight(new SizeAndWeight(15, 8, 700, 300))
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.DEEPSLATE_CRAB));


        //nether
        register(netherLavaFish(new MaybeStack(SCItems.EMBERGILL))
                .withSizeAndWeight(new SizeAndWeight(220, 70, 5700, 900))
                .withDifficulty(Difficulty.HARD));

        register(netherLavaFish(new MaybeStack(SCItems.LAVA_CRAB))
                .withSizeAndWeight(new SizeAndWeight(15, 8, 700, 300))
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.NETHER_CRAB));

        register(netherLavaFish(new MaybeStack(SCItems.MAGMA_FISH))
                .withSizeAndWeight(new SizeAndWeight(120, 40, 3700, 900))
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.HARD));

        register(netherLavaFish(new MaybeStack(SCItems.GLOWSTONE_SEEKER))
                .withSizeAndWeight(new SizeAndWeight(120, 40, 3700, 900))
                .withDifficulty(Difficulty.NON_STOP_ACTION_THREE_BIG));

        register(netherLavaFish(new MaybeStack(SCItems.CINDER_SQUID))
                .withSizeAndWeight(new SizeAndWeight(40, 20, 1300, 700))
                .withDifficulty(Difficulty.FOUR_AQUA)
                .withRarity(Rarity.RARE)
                .withBaseChance(2));

        register(netherLavaFish(new MaybeStack(SCItems.CERBERAY))
                .withSizeAndWeight(new SizeAndWeight(200, 53, 4000, 300))
                .withRarity(Rarity.LEGENDARY)
                .addBait(BaitRestriction.LEGENDARY_BAIT)
                .withDifficultyRaw(Difficulty.CERBERAY));

        register(netherLavaFish(new MaybeStack(SCItems.SCALDING_PIKE))
                .withSizeAndWeight(new SizeAndWeight(75, 20, 5000, 3000))
                .withDifficulty(Difficulty.MEDIUM_VANISHING));

        register(netherLavaFish(new MaybeStack(SCItems.GLOWSTONE_PUFFERFISH))
                .withSizeAndWeight(new SizeAndWeight(35, 25, 1000, 700))
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.MEDIUM_VANISHING));

        register(netherLavaBasaltDeltasFish(new MaybeStack(SCItems.WILLISH))
                .withSizeAndWeight(new SizeAndWeight(75, 25, 4000, 700))
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM_MOVING));

        register(netherLavaFish(new MaybeStack(SCItems.LAVA_CRAB_CLAW)).withBaseChance(1)
                .withRarity(Rarity.TRASH)
                .withDifficulty(Difficulty.TRASH)
                .withHasGuideEntry(false));


        //the end
        register(endFish(new MaybeStack(SCItems.CHARFISH))
                .withSizeAndWeight(new SizeAndWeight(135, 25, 4000, 700))
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.HARD.withHP(250)));

        register(endFish(new MaybeStack(SCItems.CHORUS_CRAB))
                .withSizeAndWeight(new SizeAndWeight(15, 8, 700, 300))
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.END_CRAB.withHP(350)));

        register(endFish(new MaybeStack(SCItems.END_GLOW))
                .withSizeAndWeight(new SizeAndWeight(235, 25, 7000, 700))
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM.withHP(250)));

        //end void
        register(fish(new MaybeStack(SCItems.VOIDBITER))
                .withSizeAndWeight(new SizeAndWeight(50, 15, 2000, 200))
                .addRestriction(WorldRestrictions.END_VOID)
                .withRarity(Rarity.LEGENDARY)
                .withTextures(FishProperties.END_VOID)
                .withDifficulty(Difficulty.VOIDBITER)
                .addBait(BaitRestriction.LEGENDARY_BAIT)
        );

        register(endVoidFish(new MaybeStack(SCItems.PURPLE_CARP))
                .withSizeAndWeight(new SizeAndWeight(150, 70, 4000, 1200))
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.HARD_MOVING.withHP(350))
        );

        register(endVoidFish(new MaybeStack(SCItems.VOIDFIN))
                .withSizeAndWeight(new SizeAndWeight(250, 60, 6000, 800))
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.HARD_VANISHING.withHP(350))
        );

        //end void outer islands
        register(endVoidOuterIslandsFish(new MaybeStack(SCItems.SPACEJELLY))
                .withSizeAndWeight(new SizeAndWeight(100, 20, 10, 1))
                .withRarity(Rarity.RARE)
                .addRestriction(new StructureRestriction(List.of(BuiltinStructures.END_CITY.location()), ""))
                .withDifficulty(Difficulty.HARD_MOVING.withPenalty(25).withHP(250))
        );

        register(endVoidOuterIslandsFish(new MaybeStack(SCItems.CHORUS_MINNOW))
                .withSizeAndWeight(new SizeAndWeight(250, 60, 6000, 2200))
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.HARD_VANISHING.withHP(250))
        );

        register(endVoidOuterIslandsFish(new MaybeStack(SCItems.NEBULA_SQUID))
                .withSizeAndWeight(new SizeAndWeight(40, 20, 1300, 700))
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.HARD_MOVING.withHP(250))
        );
    }
}
