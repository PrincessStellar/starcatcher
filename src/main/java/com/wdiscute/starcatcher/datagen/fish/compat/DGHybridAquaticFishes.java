package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.datagen.fish.FishRegistration;
import com.wdiscute.starcatcher.datagen.fish.PresetRestrictions;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.fishrestrictions.DaytimeRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.WeatherRestriction;
import net.minecraft.data.worldgen.BootstrapContext;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;
import static com.wdiscute.starcatcher.datagen.fish.PresetRestrictions.*;

public class DGHybridAquaticFishes
{
    public static void bootstrap(BootstrapContext<FishProperties> context)
    {

        //Literally The Coolest Mod Ever (this fact has been fact-checked by true fisherman ✅)

        //
        // ,--.  ,--.           ,--.            ,--.    ,--.       ,---.                               ,--.   ,--.
        // |  '--'  | ,--. ,--. |  |-.  ,--.--. `--'  ,-|  |      /  O  \   ,---.  ,--.,--.  ,--,--. ,-'  '-. `--'  ,---.
        // |  .--.  |  \  '  /  | .-. ' |  .--' ,--. ' .-. |     |  .-.  | | .-. | |  ||  | ' ,-.  | '-.  .-' ,--. | .--'
        // |  |  |  |   \   '   | `-' | |  |    |  | \ `-' |     |  | |  | ' '-' | '  ''  ' \ '-'  |   |  |   |  | \ `--.
        // `--'  `--' .-'  /     `---'  `--'    `--'  `---'      `--' `--'  `-|  |  `----'   `--`--'   `--'   `--'  `---'
        //            `---'                                                   `--'

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("hybrid-aquatic", "clownfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "clownfish"))
                        .withSizeAndWeight(8, 3, 140, 60)
                        .withDifficulty(Difficulty.EASY_MOVING)
                        .withRarity(Rarity.COMMON)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
                        .withWeather(WeatherRestriction.CLEAR)
        );

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("hybrid-aquatic", "surgeonfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "surgeonfish"))
                        .withSizeAndWeight(15, 5, 600, 200)
                        .withDifficulty(Difficulty.EASY_MOVING)
                        .withRarity(Rarity.COMMON)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
                        .withWeather(WeatherRestriction.CLEAR));

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("hybrid-aquatic", "blowfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "blowfish"))
                        .withSizeAndWeight(18, 4, 5, 3)
                        .withDifficulty(Difficulty.EASY_MOVING)
                        .withRarity(Rarity.UNCOMMON)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
                        .withWeather(WeatherRestriction.CLEAR));

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("hybrid-aquatic", "boxfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "boxfish"))
                        .withSizeAndWeight(27, 13, 180, 90)
                        .withDifficulty(Difficulty.EASY_MOVING)
                        .withRarity(Rarity.UNCOMMON)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
                        .withWeather(WeatherRestriction.CLEAR));

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("hybrid-aquatic", "damselfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "damselfish"))
                        .withSizeAndWeight(6, 3, 18, 5)
                        .withDifficulty(Difficulty.EASY_MOVING)
                        .withRarity(Rarity.COMMON)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
                        .withWeather(WeatherRestriction.CLEAR));

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("hybrid-aquatic", "moray_eel")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "moray_eel"))
                        .withSizeAndWeight(180, 70, 21000, 8000)
                        .withDifficulty(Difficulty.HARD_VANISHING)
                        .withRarity(Rarity.RARE)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT));

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("hybrid-aquatic", "blue_spotted_stingray")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "stingray"))
                        .withSizeAndWeight(50, 30, 4000, 1000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.RARE)
                        .withDaytimeRestriction(DaytimeRestriction.NOON)
                        .withWeather(WeatherRestriction.CLEAR));

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("hybrid-aquatic", "spotted_eagle_ray")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "stingray"))
                        .withSizeAndWeight(400, 100, 20000, 3000)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.RARE)
                        .withDaytimeRestriction(DaytimeRestriction.NOON)
                        .withWeather(WeatherRestriction.CLEAR));

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("hybrid-aquatic", "lionfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "lionfish"))
                        .withSizeAndWeight(33, 5, 900, 150)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.UNCOMMON)
                        .withDaytimeRestriction(DaytimeRestriction.NOON)
                        .withWeather(WeatherRestriction.CLEAR));

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("hybrid-aquatic", "needlefish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "needlefish"))
                        .withSizeAndWeight(100, 10, 2300, 300)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.UNCOMMON)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
                        .withWeather(WeatherRestriction.CLEAR));

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("hybrid-aquatic", "stonefish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "stonefish"))
                        .withSizeAndWeight(35, 5, 1500, 500)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.UNCOMMON)
                        .withDaytimeRestriction(DaytimeRestriction.NOON)
                        .withWeather(WeatherRestriction.CLEAR));

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("hybrid-aquatic", "parrotfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "parrotfish"))
                        .withSizeAndWeight(40, 10, 1600, 1400)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
                        .withWeather(WeatherRestriction.CLEAR));

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("hybrid-aquatic", "seahorse")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "seahorse"))
                        .withSizeAndWeight(15, 10, 20, 15)
                        .withDifficulty(Difficulty.EASY_MOVING)
                        .withRarity(Rarity.UNCOMMON)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
                        .withWeather(WeatherRestriction.CLEAR));

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("hybrid-aquatic", "triggerfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "triggerfish"))
                        .withSizeAndWeight(90, 10, 4300, 2500)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.UNCOMMON)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
                        .withWeather(WeatherRestriction.CLEAR));

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("hybrid-aquatic", "flying_fish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "flying_fish"))
                        .withSizeAndWeight(19, 11, 570, 330)
                        .withDifficulty(Difficulty.EASY_MOVING)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.deepOcean(context)
                        .withFish("hybrid-aquatic", "anglerfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "anglerfish"))
                        .withSizeAndWeight(10.5f, 7.5f, 585, 295)
                        .withDifficulty(Difficulty.EASY_VANISHING)
                        .withRarity(Rarity.UNCOMMON));

        FishRegistration.register(context,
                PresetRestrictions.deepOcean(context)
                        .withFish("hybrid-aquatic", "barreleye")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "barreleye"))
                        .withSizeAndWeight(15, 5, 99.25f, 42.5f)
                        .withDifficulty(Difficulty.EASY_MOVING)
                        .withRarity(Rarity.UNCOMMON));

        FishRegistration.register(context,
                PresetRestrictions.deepOcean(context)
                        .withFish("hybrid-aquatic", "dragonfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "dragonfish"))
                        .withSizeAndWeight(12, 3, 41.5f, 11.5f)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.UNCOMMON)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT));

        FishRegistration.register(context,
                PresetRestrictions.deepOcean(context)
                        .withFish("hybrid-aquatic", "coelacanth")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "coelacanth"))
                        .withSizeAndWeight(175, 25, 59500, 8000)
                        .withDifficulty(Difficulty.HARD_VANISHING)
                        .withRarity(Rarity.RARE));

        FishRegistration.register(context,
                PresetRestrictions.deepOcean(context)
                        .withFish("hybrid-aquatic", "oarfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "oarfish"))
                        .withSizeAndWeight(500, 300, 122727.25f, 61363.6f)
                        .withDifficulty(Difficulty.HARD_VANISHING)
                        .withRarity(Rarity.RARE)
                        .withWeather(WeatherRestriction.THUNDER));

        FishRegistration.register(context,
                PresetRestrictions.deepOcean(context)
                        .withFish("hybrid-aquatic", "ratfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "ratfish"))
                        .withSizeAndWeight(90, 10, 4300, 2500)
                        .withDifficulty(Difficulty.EASY_MOVING)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.deepOcean(context)
                        .withFish("hybrid-aquatic", "snailfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "snailfish"))
                        .withSizeAndWeight(19.65f, 9.15f, 84, 76)
                        .withDifficulty(Difficulty.EASY_MOVING)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.deepOcean(context)
                        .withFish("hybrid-aquatic", "john_dory")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "john_dory"))
                        .withSizeAndWeight(50, 15, 3846.15f, 1153.85f)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING)
                        .withRarity(Rarity.UNCOMMON));

        FishRegistration.register(context,
                PresetRestrictions.deepOcean(context)
                        .withFish("hybrid-aquatic", "flashlight_fish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "flashlight_fish"))
                        .withSizeAndWeight(20, 10, 80, 40)
                        .withDifficulty(Difficulty.EASY_FAST_FISH)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.deepOcean(context)
                        .withFish("hybrid-aquatic", "squirrelfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "squirrelfish"))
                        .withSizeAndWeight(21, 3, 300, 100)
                        .withDifficulty(Difficulty.EASY_MOVING)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.allOceans(context)
                        .withFish("hybrid-aquatic", "tuna")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "tuna"))
                        .withSizeAndWeight(300, 100, 680250, 226750)
                        .withDifficulty(Difficulty.HARD_MOVING)
                        .withRarity(Rarity.UNCOMMON));

        FishRegistration.register(context,
                PresetRestrictions.allOceans(context)
                        .withFish("hybrid-aquatic", "mahi")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "mahi"))
                        .withSizeAndWeight(90, 10, 4300, 2500)
                        .withDifficulty(Difficulty.HARD_MOVING)
                        .withRarity(Rarity.UNCOMMON));

        FishRegistration.register(context,
                PresetRestrictions.allOceans(context)
                        .withFish("hybrid-aquatic", "mackerel")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "mackerel"))
                        .withSizeAndWeight(48, 18, 1250, 750)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.coldOcean(context)
                        .withFish("hybrid-aquatic", "herring")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "herring"))
                        .withSizeAndWeight(40, 12, 663.4f, 436.6f)
                        .withDifficulty(Difficulty.EASY_MOVING)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.allOceans(context)
                        .withFish("hybrid-aquatic", "sea_bass")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "sea_bass"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.allOceans(context)
                        .withFish("hybrid-aquatic", "sheepshead_wrasse")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "wrasse"))
                        .withSizeAndWeight(90, 10, 4300, 2500)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.allOceans(context)
                        .withFish("hybrid-aquatic", "pearlfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "pearlfish"))
                        .withSizeAndWeight(14.45f, 2.15f, 2.675f, 1.435f)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.allOceans(context)
                        .withFish("hybrid-aquatic", "rockfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "rockfish"))
                        .withSizeAndWeight(90, 10, 4300, 2500)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.deepOcean(context)
                        .withFish("hybrid-aquatic", "opah")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "opah"))
                        .withSizeAndWeight(90, 10, 4300, 2500)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.UNCOMMON)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                        .withWeather(WeatherRestriction.CLEAR));

        FishRegistration.register(context,
                PresetRestrictions.deepOcean(context)
                        .withFish("hybrid-aquatic", "ocean_sunfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "ocean_sunfish"))
                        .withSizeAndWeight(230, 100, 1273500, 1026500)
                        .withDifficulty(Difficulty.HARD_MOVING)
                        .withRarity(Rarity.RARE)
                        .withDaytimeRestriction(DaytimeRestriction.DAY)
                        .withWeather(WeatherRestriction.CLEAR));

        FishRegistration.register(context,
                PresetRestrictions.cherryGrove(context)
                        .withFish("hybrid-aquatic", "goldfish")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "goldfish"))
                        .withSizeAndWeight(20, 5, 200, 100)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.UNCOMMON));

        FishRegistration.register(context,
                PresetRestrictions.river(context)
                        .withFish("hybrid-aquatic", "carp")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "carp"))
                        .withSizeAndWeight(60, 20, 8000, 4000)
                        .withDifficulty(Difficulty.EASY_VANISHING)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.jungle(context)
                        .withFish("hybrid-aquatic", "golden_dorado")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "golden_dorado"))
                        .withSizeAndWeight(37, 20, 6500, 3500)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.RARE));

        FishRegistration.register(context,
                PresetRestrictions.jungle(context)
                        .withFish("hybrid-aquatic", "oscar")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "oscar"))
                        .withSizeAndWeight(30, 10, 5290, 3710)
                        .withDifficulty(Difficulty.EASY_VANISHING)
                        .withRarity(Rarity.UNCOMMON));

        FishRegistration.register(context,
                PresetRestrictions.jungle(context)
                        .withFish("hybrid-aquatic", "neon_tetra")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "tetra"))
                        .withSizeAndWeight(4, 1, 0.37f, 0.12f)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.jungle(context)
                        .withFish("hybrid-aquatic", "tiger_barb")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "tiger_barb"))
                        .withSizeAndWeight(10, 3, 1.9f, 0.5f)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.jungle(context)
                        .withFish("hybrid-aquatic", "betta")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "betta"))
                        .withSizeAndWeight(7, 1, 1.8f, 0.3f)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.UNCOMMON));

        FishRegistration.register(context,
                PresetRestrictions.jungle(context)
                        .withFish("hybrid-aquatic", "danio")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "danio"))
                        .withSizeAndWeight(4.4f, 0.6f, 0.7f, 0.2f)
                        .withDifficulty(Difficulty.EASY_VANISHING)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.jungle(context)
                        .withFish("hybrid-aquatic", "gourami")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "gourami"))
                        .withSizeAndWeight(90, 10, 4300, 2500)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.jungle(context)
                        .withFish("hybrid-aquatic", "discus")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "discus"))
                        .withSizeAndWeight(90, 10, 4300, 2500)
                        .withDifficulty(Difficulty.MEDIUM_MOVING)
                        .withRarity(Rarity.COMMON));

        FishRegistration.register(context,
                PresetRestrictions.jungle(context)
                        .withFish("hybrid-aquatic", "pleco")
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "pleco"))
                        .withSizeAndWeight(35.5f, 25.5f, 170.1f, 122.2f)
                        .withDifficulty(Difficulty.EASY_FAST_FISH)
                        .withRarity(Rarity.COMMON));
    }
}
