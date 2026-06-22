package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.datagen.fish.FishRegistration;
import com.wdiscute.starcatcher.datagen.fish.PresetRestrictions;
import com.wdiscute.starcatcher.fish.*;
import com.wdiscute.starcatcher.registry.fishrestrictions.*;
import net.minecraft.data.worldgen.BootstrapContext;
import org.jetbrains.annotations.Nullable;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;
import static com.wdiscute.starcatcher.datagen.fish.PresetRestrictions.*;

public class DGUnusualFishFishes
{
    public static void bootstrap(@Nullable BootstrapContext<FishProperties> context)
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
//        FishRegistration.register(context,
//                PresetRestrictions.allOceans(context)
//                        .withFish("unusualfishmod", "wizard_jelly_bucket")
//                        .withBucketedFish("unusualfishmod", "wizard_jelly_bucket")
//                        .withEntityToSpawn(U.holderEntity("unusualfishmod", "wizard_jelly"))
//                        .withAlwaysSpawnEntity()
//                        .withRarity(Rarity.EPIC)
//                        .withDaytimeRestriction(DaytimeRestriction.NIGHT)
//                        .withDifficulty(Difficulty.HARD)
//                        .withBaseChance(2)
//        );

        FishRegistration.register(context,
                PresetRestrictions.allOceans(context)
                        .withFish("unusualfishmod", "raw_aero_mono")
                        .withBucketedFish("unusualfishmod", "aero_mono_bucket")
                        .withEntityToSpawn(U.holderEntity("unusualfishmod", "aero_mono"))
                        .withRarity(Rarity.COMMON)
                        .withDifficulty(Difficulty.EASY),
                "unusualfishmod"
        );

        FishRegistration.register(context,
                PresetRestrictions.allOceans(context)
                        .withFish("unusualfishmod", "raw_beaked_herring")
                        .withBucketedFish("unusualfishmod", "beaked_herring_bucket")
                        .withEntityToSpawn(U.holderEntity("unusualfishmod", "beaked_herring"))
                        .withRarity(Rarity.UNCOMMON)
                        .withDifficulty(Difficulty.EASY_MOVING),
                "unusualfishmod"
        );

        //deep oceans
        FishRegistration.register(context,
                PresetRestrictions.deepOcean(context)
                        .withFish("unusualfishmod", "demon_herring")
                        .withRarity(Rarity.COMMON)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withDaytimeRestriction(DaytimeRestriction.DAY),
                "unusualfishmod"
        );

        //cold oceans
//       Currently entity only, will possibly be itemized in the future
//                FishRegistration.register(
//        context,
//                PresetRestrictions.fish("unusualfishmod", "volt_angler_bucket"))
//                .withEntityToSpawn(U.holderEntity("unusualfishmod", "volt_angler"))
//                .withAlwaysSpawnEntity()
//                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_COLD_OCEAN)
//                .withRarity(FishProperties.Rarity.RARE)
//                .withWeather(WeatherRestriction.RAIN)
//                .withDifficulty(FishProperties.Difficulty.HARD)
//        );

        FishRegistration.register(context,
                PresetRestrictions.coldOcean(context)
                        .withFish("unusualfishmod", "raw_frosty_fin")
                        .withBucketedFish("unusualfishmod", "frosty_fin_fish_bucket")
                        .withEntityToSpawn(U.holderEntity("unusualfishmod", "frostyfin"))
                        .withRarity(Rarity.EPIC)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING_MOVING)
                        .withDaytimeRestriction(DaytimeRestriction.NIGHT),
                "unusualfishmod"
        );

        //lukewarm oceans
        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("unusualfishmod", "circus_fish")
                        .withEntityToSpawn(U.holderEntity("unusualfishmod", "circus"))
                        .withRarity(Rarity.RARE)
                        .withWeather(WeatherRestriction.CLEAR)
                        .withDifficulty(Difficulty.HEAVY_FIVE_NORMAL)
                        .withDaytimeRestriction(DaytimeRestriction.DAY),
                "unusualfishmod"
        );


        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("unusualfishmod", "raw_copperflame_anthias")
                        .withBucketedFish("unusualfishmod", "copperflame_bucket")
                        .withEntityToSpawn(U.holderEntity("unusualfishmod", "copperflame"))
                        .withDifficulty(Difficulty.EASY_VANISHING)
                        .withRarity(Rarity.COMMON),
                "unusualfishmod"
        );

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("unusualfishmod", "forkfish")
                        .withRarity(Rarity.COMMON)
                        .withDifficulty(Difficulty.EASY_MOVING),
                "unusualfishmod"
        );

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("unusualfishmod", "raw_picklefish")
                        .withBucketedFish("unusualfishmod", "picklefish_bucket")
                        .withEntityToSpawn(U.holderEntity("unusualfishmod", "picklefish"))
                        .withDifficulty(Difficulty.EASY_MOVING)
                        .withRarity(Rarity.COMMON)
                        .withDaytimeRestriction(DaytimeRestriction.DAY),
                "unusualfishmod"
        );

        //warm ocean
        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("unusualfishmod", "amber_goby")
                        .withRarity(Rarity.COMMON)
                        .withDifficulty(Difficulty.EASY_MOVING),
                "unusualfishmod"
        );

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("unusualfishmod", "raw_sneep_snorp")
                        .withBucketedFish("unusualfishmod", "sneepsnorp_bucket")
                        .withEntityToSpawn(U.holderEntity("unusualfishmod", "sneep_snorp"))
                        .withRarity(Rarity.UNCOMMON)
                        .withDifficulty(Difficulty.EASY_MOVING),
                "unusualfishmod"
        );

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context).withFish("unusualfishmod", "duality_damselfish")
                        .withDifficulty(Difficulty.EASY_FAST_FISH)
                        .withRarity(Rarity.UNCOMMON),
                "unusualfishmod"
        );

        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("unusualfishmod", "spindlefish")
                        .withWeather(WeatherRestriction.THUNDER)
                        .withDifficulty(Difficulty.SINGLE_AQUA)
                        .withBaseChance(30)
                        .withRarity(Rarity.LEGENDARY),
                "unusualfishmod"
        );


        //river
        FishRegistration.register(context,
                PresetRestrictions.warmOcean(context)
                        .withFish("unusualfishmod", "triple_twirl_pleco")
                        .withRarity(Rarity.COMMON),
                "unusualfishmod"
        );

//       Currently entity only, will possibly be itemized in the future
//                FishRegistration.register(
//        context,
//                PresetRestrictions.fish("unusualfishmod", "pinkfin_idol_bucket"))
//                .withBucketedFish("unusualfishmod", "pinkfin_idol_bucket"))
//                .withEntityToSpawn(U.holderEntity("unusualfishmod", "pinkfin"))
//                .withAlwaysSpawnEntity()
//                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_COLD_RIVER)
//                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
//                .withRarity(FishProperties.Rarity.UNCOMMON)
//                .withDaytimeRestriction(DaytimeRestriction.DAY)
//        );


        //swamp(s)
        FishRegistration.register(context,
                PresetRestrictions.swamp(context)
                        .withFish("unusualfishmod", "bark_angelfish")
                        .withRarity(Rarity.COMMON),
                "unusualfishmod"
        );

//       Currently entity only, will possibly be itemized in the future
//                FishRegistration.register(
//        context,
//                PresetRestrictions.helperOnlyBucket("stout_bichir")
//                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_MANGROVE_SWAMP)
//                .withRarity(FishProperties.Rarity.COMMON)
//        );

        FishRegistration.register(context,
                PresetRestrictions.swamp(context)
                        .withFish("unusualfishmod", "drooping_gourami")
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.UNCOMMON),
                "unusualfishmod"
        );

//       Currently entity only, will possibly be itemized in the future
//                FishRegistration.register(
//        context,
//                PresetRestrictions.fish("unusualfishmod", "lobed_skipper_bucket"))
//                .withBucketedFish("unusualfishmod", "lobed_skipper_bucket"))
//                .withEntityToSpawn(U.holderEntity("unusualfishmod", "skipper"))
//                .withAlwaysSpawnEntity()
//                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_MANGROVE_SWAMP)
//                .withDifficulty(FishProperties.Difficulty.SINGLE_BIG_FAST_MOVING)
//                .withRarity(FishProperties.Rarity.EPIC)
//                .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
//                .withBaseChance(15)
//        );

        FishRegistration.register(context,
                PresetRestrictions.swamp(context)
                        .withFish("unusualfishmod", "sailor_barb")
                        .withDifficulty(Difficulty.EASY_VANISHING)
                        .withRarity(Rarity.UNCOMMON),
                "unusualfishmod"
        );

        //jungle
        FishRegistration.register(context,
                PresetRestrictions.jungle(context).withFish("unusualfishmod", "eyelash")
                        .withBucketedFish("unusualfishmod", "eyelash_fish_bucket")
                        .withRarity(Rarity.UNCOMMON),
                "unusualfishmod"
        );

        //savanna

        //mushroom fields

        //underground
        FishRegistration.register(context,
                PresetRestrictions.empty(context)
                        .withFish("unusualfishmod", "raw_blind_sailfin")
                        .withBucketedFish("unusualfishmod", "blind_sailfin_bucket")
                        .withEntityToSpawn(U.holderEntity("unusualfishmod", "blindsailfin"))
                        .addRestrictions(
                                DimensionRestriction.OVERWORLD,
                                new ElevationRestriction(Integer.MIN_VALUE, 30, "")
                        )
                        .withDifficulty(Difficulty.HARD_VANISHING)
                        .withRarity(Rarity.RARE)
                        .withBaseChance(2),
                "unusualfishmod"
        );

        FishRegistration.register(context,
                PresetRestrictions.deepslate(context).withFish("unusualfishmod", "raw_hatchetfish")
                        .withBucketedFish("unusualfishmod", "hatchet_fish_bucket")
                        .withEntityToSpawn(U.holderEntity("unusualfishmod", "hatchet_fish"))
                        .withDifficulty(Difficulty.EASY_MOVING)
                        .withRarity(Rarity.UNCOMMON),
                "unusualfishmod"
        );
    }
}
