package com.wdiscute.starcatcher.registry.fishing.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.fishing.FishingPropertiesRegistry;
import com.wdiscute.starcatcher.registry.fishrestrictions.*;
import com.wdiscute.starcatcher.registry.FishProperties;

public class DGUnusualFishFishes extends FishingPropertiesRegistry
{
    public static void bootstrap()
    {

        //
        //,--. ,--.                                             ,--.
        //|  | |  | ,--,--,  ,--.,--.  ,---.  ,--.,--.  ,--,--. |  |
        //|  | |  | |      \ |  ||  | (  .-'  |  ||  | ' ,-.  | |  |
        //'  '-'  ' |  ||  | '  ''  ' .-'  `) '  ''  ' \ '-'  | |  |
        // `-----'  `--''--'  `----'  `----'   `----'   `--`--' `--'
        //,------. ,--.         ,--.
        //|  .---' `--'  ,---.  |  ,---.   ,---.   ,---.
        //|  `--,  ,--. (  .-'  |  .-.  | | .-. : (  .-'
        //|  |`    |  | .-'  `) |  | |  | \   --. .-'  `)
        //`--'     `--' `----'  `--' `--'  `----' `----'
        //

        //ocean
//       Currently entity only, will possibly be itemized in the future
//        register(fish(U.holderItem("unusualfishmod", "wizard_jelly_bucket"))
//                .withBucketedFish(U.holderItem("unusualfishmod", "wizard_jelly_bucket"))
//                .withEntityToSpawn(U.holderEntity("unusualfishmod", "wizard_jelly"))
//                .withAlwaysSpawnEntity()
//                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_OCEAN)
//                .withRarity(FishProperties.Rarity.EPIC)
//                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
//                .withDifficulty(FishProperties.Difficulty.HARD)
//                .withBaseChance(2)
//        );

        register(fish(U.holderItem("unusualfishmod", "raw_aero_mono"))
                .withBucketedFish(U.holderItem("unusualfishmod", "aero_mono_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "aero_mono"))
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_OCEAN)
                .withRarity(FishProperties.Rarity.COMMON)
                .withDifficulty(FishProperties.Difficulty.EASY)
        );

        register(fish(U.holderItem("unusualfishmod", "raw_beaked_herring"))
                .withBucketedFish(U.holderItem("unusualfishmod", "beaked_herring_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "beaked_herring"))
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_OCEAN)
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
        );

        //deep oceans
        register(helper("demon_herring")
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_DEEP_OCEAN)
                .withRarity(FishProperties.Rarity.COMMON)
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
        );

        //cold oceans
//       Currently entity only, will possibly be itemized in the future
//        register(fish(U.holderItem("unusualfishmod", "volt_angler_bucket"))
//                .withEntityToSpawn(U.holderEntity("unusualfishmod", "volt_angler"))
//                .withAlwaysSpawnEntity()
//                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_COLD_OCEAN)
//                .withRarity(FishProperties.Rarity.RARE)
//                .withWeather(WeatherRestriction.RAIN)
//                .withDifficulty(FishProperties.Difficulty.HARD)
//        );

        register(fish(U.holderItem("unusualfishmod", "raw_frosty_fin"))
                .withBucketedFish(U.holderItem("unusualfishmod", "frosty_fin_fish_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "frostyfin"))
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_COLD_OCEAN)
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING_MOVING)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
        );

        //lukewarm oceans

        register(helper("circus_fish")
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "circus"))
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_LUKEWARM_OCEAN)
                .withRarity(FishProperties.Rarity.RARE)
                .withWeather(WeatherRestriction.CLEAR)
                .withDifficulty(FishProperties.Difficulty.HEAVY_FIVE_NORMAL)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
        );


        register(fish(U.holderItem("unusualfishmod", "raw_copperflame_anthias"))
                .withBucketedFish(U.holderItem("unusualfishmod", "copperflame_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "copperflame"))
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_LUKEWARM_OCEAN)
                .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
                .withRarity(FishProperties.Rarity.COMMON)
        );

        register(helper("forkfish")
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_LUKEWARM_OCEAN)
                .withRarity(FishProperties.Rarity.COMMON)
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
        );

        register(fish(U.holderItem("unusualfishmod", "raw_picklefish"))
                .withBucketedFish(U.holderItem("unusualfishmod", "picklefish_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "picklefish"))
                .withAlwaysSpawnEntity()
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_LUKEWARM_OCEAN)
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                .withRarity(FishProperties.Rarity.COMMON)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
        );

        //warm ocean
        register(helper("amber_goby")
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_WARM_OCEAN)
                .withRarity(FishProperties.Rarity.COMMON)
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
        );

        register(fish(U.holderItem("unusualfishmod", "raw_sneep_snorp"))
                .withBucketedFish(U.holderItem("unusualfishmod", "sneepsnorp_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "sneep_snorp"))
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_WARM_OCEAN)
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
        );

        register(helper("duality_damselfish")
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_WARM_OCEAN)
                .withDifficulty(FishProperties.Difficulty.EASY_FAST_FISH)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        register(helper("spindlefish")
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_WARM_OCEAN)
                .withWeather(WeatherRestriction.THUNDER)
                .withDifficulty(FishProperties.Difficulty.SINGLE_AQUA)
                .withBaseChance(30)
                .withRarity(FishProperties.Rarity.LEGENDARY)
        );


        //river
        register(helper("triple_twirl_pleco")
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_RIVER)
                .withRarity(FishProperties.Rarity.COMMON)
        );

//       Currently entity only, will possibly be itemized in the future
//        register(fish(U.holderItem("unusualfishmod", "pinkfin_idol_bucket"))
//                .withBucketedFish(U.holderItem("unusualfishmod", "pinkfin_idol_bucket"))
//                .withEntityToSpawn(U.holderEntity("unusualfishmod", "pinkfin"))
//                .withAlwaysSpawnEntity()
//                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_COLD_RIVER)
//                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
//                .withRarity(FishProperties.Rarity.UNCOMMON)
//                .withDaytimeRestriction(DaytimeRestriction.DAY)
//        );


        //swamp(s)
        register(helper("bark_angelfish")
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_SWAMP_ONLY)
                .withRarity(FishProperties.Rarity.COMMON)
        );
//       Currently entity only, will possibly be itemized in the future
//        register(helperOnlyBucket("stout_bichir")
//                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_MANGROVE_SWAMP)
//                .withRarity(FishProperties.Rarity.COMMON)
//        );

        register(helper("drooping_gourami")
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_MANGROVE_SWAMP)
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

//       Currently entity only, will possibly be itemized in the future
//        register(fish(U.holderItem("unusualfishmod", "lobed_skipper_bucket"))
//                .withBucketedFish(U.holderItem("unusualfishmod", "lobed_skipper_bucket"))
//                .withEntityToSpawn(U.holderEntity("unusualfishmod", "skipper"))
//                .withAlwaysSpawnEntity()
//                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_MANGROVE_SWAMP)
//                .withDifficulty(FishProperties.Difficulty.SINGLE_BIG_FAST_MOVING)
//                .withRarity(FishProperties.Rarity.EPIC)
//                .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
//                .withBaseChance(15)
//        );

        register(helper("sailor_barb")
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_SWAMPS)
                .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        //jungle
        register(helper("eyelash")
                .withBucketedFish(U.holderItem("unusualfishmod", "eyelash_fish_bucket"))
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_JUNGLE)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        //savanna

        //mushroom fields

        //underground
        register(fish(U.holderItem("unusualfishmod", "raw_blind_sailfin"))
                .withBucketedFish(U.holderItem("unusualfishmod", "blind_sailfin_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "blindsailfin"))
                .withAlwaysSpawnEntity()
                .addRestrictions(DimensionRestriction.OVERWORLD,
                        new ElevationRestriction(Integer.MIN_VALUE, 30, ""))
                .withDifficulty(FishProperties.Difficulty.HARD_VANISHING)
                .withRarity(FishProperties.Rarity.RARE)
                .withBaseChance(2)
        );

        register(fish(U.holderItem("unusualfishmod", "raw_hatchetfish"))
                .withBucketedFish(U.holderItem("unusualfishmod", "hatchet_fish_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "hatchet_fish"))
                .withAlwaysSpawnEntity()
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_DEEPSLATE)
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );
    }
    public static FishProperties.Builder helper(String s)
    {
        return fish(U.holderItem("unusualfishmod", "raw_" + s))
                .withBucketedFish(U.holderItem("unusualfishmod", s + "_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", s))
                .withSizeAndWeight(FishProperties.sizeWeight(80, 40, 12000, 7000)
                );
    }
}
