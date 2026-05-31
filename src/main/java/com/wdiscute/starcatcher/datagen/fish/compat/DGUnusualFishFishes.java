package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.*;
import com.wdiscute.starcatcher.registry.fishrestrictions.*;

import java.util.List;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGUnusualFishFishes
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
        register(fish(new MaybeStack("unusualfishmod", "wizard_jelly_bucket"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "wizard_jelly_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "wizard_jelly"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_OCEAN)
                .withRarity(Rarity.EPIC)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                .withDifficulty(Difficulty.HARD)
                .withBaseChance(2)
        );

        register(fish(new MaybeStack("unusualfishmod", "raw_aero_mono"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "aero_mono_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "aero_mono"))
                .addRestriction(WorldRestrictions.OVERWORLD_OCEAN)
                .withRarity(Rarity.COMMON)
                .withDifficulty(Difficulty.EASY)
        );

        register(fish(new MaybeStack("unusualfishmod", "raw_beaked_herring"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "beaked_herring_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "beaked_herring"))
                .addRestriction(WorldRestrictions.OVERWORLD_OCEAN)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.EASY_MOVING)
        );

        register(fish(new MaybeStack("unusualfishmod", "brick_snail_bucket"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "brick_snail_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "brick_snail"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_OCEAN)
                .withRarity(Rarity.RARE)
                .withWeather(WeatherRestriction.RAIN)
                .withDifficulty(Difficulty.MEDIUM_VANISHING)
                .withBaseChance(10)
        );

        register(fish(new MaybeStack("unusualfishmod", "celestial_fish_spawn_egg"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "celestial"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_OCEAN)
                .withRarity(Rarity.LEGENDARY)
                .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                .withWeather(WeatherRestriction.CLEAR)
                .withDifficulty(Difficulty.TWO_AQUA)
                .withBaseChance(30)
        );

        //deep oceans
        register(fish(new MaybeStack("unusualfishmod", "tribble_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "tribble"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_DEEP_OCEAN)
                .withDifficulty(Difficulty.MEDIUM)
                .withRarity(Rarity.UNCOMMON)
        );

        register(helper("demon_herring")
                .addRestriction(WorldRestrictions.OVERWORLD_DEEP_OCEAN)
                .withRarity(Rarity.COMMON)
                .withDifficulty(Difficulty.MEDIUM)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
        );

        register(fish(new MaybeStack("unusualfishmod", "sea_spider_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "sea_spider"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_DEEP_OCEAN)
                .withRarity(Rarity.COMMON)
                .withDifficulty(Difficulty.EASY_VANISHING)
        );

        //cold oceans
        register(fish(new MaybeStack("unusualfishmod", "volt_angler_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "volt_angler"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_COLD_OCEAN)
                .withRarity(Rarity.RARE)
                .withWeather(WeatherRestriction.RAIN)
                .withDifficulty(Difficulty.HARD)
        );

        register(fish(new MaybeStack("unusualfishmod", "blizzardfin_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "blizzardfin"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_COLD_OCEAN)
                .withRarity(Rarity.UNCOMMON)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
                .withDifficulty(Difficulty.EASY_VANISHING)
        );

        register(fish(new MaybeStack("unusualfishmod", "raw_snowflake"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "snowflake_tail_fish_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "snowflaketail"))
                .addRestriction(WorldRestrictions.OVERWORLD_COLD_OCEAN)
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.MEDIUM_VANISHING_MOVING)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
        );

        //lukewarm oceans
        register(fish(new MaybeStack("unusualfishmod", "trumpet_squid_bucket"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "trumpet_squid_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "trumpet_squid"))
                .addRestriction(WorldRestrictions.OVERWORLD_LUKEWARM_OCEAN)
                .withDifficulty(Difficulty.MEDIUM)
                .withRarity(Rarity.UNCOMMON)
        );

        register(fish(new MaybeStack("unusualfishmod", "squoddle_bucket"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "squoddle_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "squoddle"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_LUKEWARM_OCEAN)
                .withDifficulty(Difficulty.MEDIUM_MOVING)
                .withRarity(Rarity.COMMON)
        );

        register(fish(new MaybeStack("unusualfishmod", "spoon_shark_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "spoon_shark"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_LUKEWARM_OCEAN)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM_MOVING)
        );

        register(helper("circus")
                .addRestriction(WorldRestrictions.OVERWORLD_LUKEWARM_OCEAN)
                .withRarity(Rarity.RARE)
                .withWeather(WeatherRestriction.CLEAR)
                .withDifficulty(Difficulty.HEAVY_FIVE_NORMAL)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
        );

        register(fish(new MaybeStack("unusualfishmod", "sea_pancake_spawn_egg"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "sea_pancake"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_LUKEWARM_OCEAN)
                .withDifficulty(Difficulty.EASY_VANISHING)
                .withRarity(Rarity.UNCOMMON)
        );

        register(fish(new MaybeStack("unusualfishmod", "raw_copperflame_anthias"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "copperflame_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "copperflame"))
                .addRestriction(WorldRestrictions.OVERWORLD_LUKEWARM_OCEAN)
                .withDifficulty(Difficulty.EASY_VANISHING)
                .withRarity(Rarity.COMMON)
        );

        register(helper("forkfish")
                .addRestriction(WorldRestrictions.OVERWORLD_LUKEWARM_OCEAN)
                .withRarity(Rarity.COMMON)
                .withDifficulty(Difficulty.EASY_MOVING)
        );

        register(fish(new MaybeStack("unusualfishmod", "picklefish_bucket"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "picklefish_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "picklefish"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_LUKEWARM_OCEAN)
                .withDifficulty(Difficulty.EASY_MOVING)
                .withRarity(Rarity.COMMON)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
        );

        register(fish(new MaybeStack("unusualfishmod", "porcupine_lobsta_bucket"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "porcupine_lobsta_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "porcupine_lobsta"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_LUKEWARM_OCEAN)
                .withDifficulty(Difficulty.MEDIUM_VANISHING)
                .withRarity(Rarity.UNCOMMON)
        );

        //warm ocean
        register(fish(new MaybeStack("unusualfishmod", "tiger_puffer_bucket"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "tiger_puffer_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "tiger_puffer"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_WARM_OCEAN)
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.FOUR_THIN_VANISHING)
                .withBaseChance(2)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
        );

        register(fish(new MaybeStack("unusualfishmod", "zebra_cornetfish_spawn_egg"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "zebra_cornetfish"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_WARM_OCEAN)
                .withDifficulty(Difficulty.FOUR_THIN_MOVING)
                .withRarity(Rarity.EPIC)
                .withWeather(WeatherRestriction.RAIN)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                .withBaseChance(20)
        );

        register(helper("amber_goby")
                .addRestriction(WorldRestrictions.OVERWORLD_WARM_OCEAN)
                .withRarity(Rarity.COMMON)
                .withDifficulty(Difficulty.EASY_MOVING)
        );

        register(fish(new MaybeStack("unusualfishmod", "raw_sneep_snorp"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "sneepsnorp_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "sneep_snorp"))
                .addRestriction(WorldRestrictions.OVERWORLD_WARM_OCEAN)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.EASY_MOVING)
        );

        register(fish(new MaybeStack("unusualfishmod", "sea_mosquito_bucket"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "sea_mosquito_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "sea_mosquito"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_WARM_OCEAN)
                .withDifficulty(Difficulty.MEDIUM_MOVING)
                .withRarity(Rarity.COMMON)
        );

        register(fish(new MaybeStack("unusualfishmod", "clownthorn_shark_bucket"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "clownthorn_shark_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "clownthorn_shark"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_WARM_OCEAN)
                .withDifficulty(Difficulty.EASY_VANISHING)
                .withRarity(Rarity.UNCOMMON)
        );

        register(fish(new MaybeStack("unusualfishmod", "coral_skrimp_bucket"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "coral_skrimp_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "coral_skrimp"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_WARM_OCEAN)
                .withDifficulty(Difficulty.EASY_VANISHING)
                .withRarity(Rarity.COMMON)
        );

        register(fish(new MaybeStack("unusualfishmod", "crimsonshell_squid_bucket"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "crimsonshell_squid_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "crimsonshell"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_WARM_OCEAN)
                .withDifficulty(Difficulty.THREE_BIG_TWO_THIN_VANISHING)
                .withRarity(Rarity.RARE)
                .withWeather(WeatherRestriction.RAIN)
        );

        register(helper("duality_damselfish")
                .addRestriction(WorldRestrictions.OVERWORLD_WARM_OCEAN)
                .withDifficulty(Difficulty.EASY_FAST_FISH)
                .withRarity(Rarity.UNCOMMON)
        );

        register(helper("spindlefish")
                .addRestriction(WorldRestrictions.OVERWORLD_WARM_OCEAN)
                .withWeather(WeatherRestriction.THUNDER)
                .withDifficulty(Difficulty.SINGLE_AQUA)
                .withBaseChance(30)
                .withRarity(Rarity.LEGENDARY)
        );


        //river
        register(helper("triple_twirl_pleco")
                .addRestriction(WorldRestrictions.OVERWORLD_RIVER)
                .withRarity(Rarity.COMMON)
        );

        register(helperOnlyBucket("blackcap_snail")
                .addRestriction(WorldRestrictions.OVERWORLD_RIVER)
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.HARD_MOVING)
                .withBaseChance(1)
        );

        register(helperOnlyBucket("ripper")
                .addRestriction(WorldRestrictions.OVERWORLD_RIVER)
                .withDifficulty(Difficulty.MEDIUM_MOVING)
                .withRarity(Rarity.UNCOMMON)
        );

        register(fish(new MaybeStack("unusualfishmod", "pinkfin_idol_bucket"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "pinkfin_idol_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "pinkfin"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_COLD_RIVER)
                .withDifficulty(Difficulty.EASY_MOVING)
                .withRarity(Rarity.UNCOMMON)
                .withDaytimeRestriction(DaytimeRestriction.DAY)
        );


        //swamp(s)
        register(helper("bark_angelfish")
                .addRestriction(WorldRestrictions.OVERWORLD_SWAMP_ONLY)
                .withRarity(Rarity.COMMON)
        );

        register(fish(new MaybeStack("unusualfishmod", "roughback_guitarfish_spawn_egg"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "roughback_guitarfish_spawn_egg"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_SWAMP_ONLY)
                .withDifficulty(Difficulty.HARD_MOVING)
                .withRarity(Rarity.RARE)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                .withBaseChance(7)
        );

        register(helperOnlyBucket("stout_bichir")
                .addRestriction(WorldRestrictions.OVERWORLD_MANGROVE_SWAMP)
                .withRarity(Rarity.COMMON)
        );

        register(helper("drooping_gourami")
                .addRestriction(WorldRestrictions.OVERWORLD_MANGROVE_SWAMP)
                .withDifficulty(Difficulty.MEDIUM)
                .withRarity(Rarity.UNCOMMON)
        );

        register(fish(new MaybeStack("unusualfishmod", "lobed_skipper_bucket"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "lobed_skipper_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "skipper"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_MANGROVE_SWAMP)
                .withDifficulty(Difficulty.SINGLE_BIG_FAST_MOVING)
                .withRarity(Rarity.EPIC)
                .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                .withBaseChance(15)
        );

        register(fish(new MaybeStack("unusualfishmod", "muddytop_snail_bucket"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "muddytop_snail_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "muddytop"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_MANGROVE_SWAMP)
                .withDifficulty(Difficulty.MEDIUM_MOVING)
                .withRarity(Rarity.UNCOMMON)
        );

        register(helper("sailor_barb")
                .addRestriction(WorldRestrictions.OVERWORLD_SWAMPS)
                .withDifficulty(Difficulty.EASY_VANISHING)
                .withRarity(Rarity.UNCOMMON)
        );

        //jungle
        register(fish(new MaybeStack("unusualfishmod", "tiger_jungle_shark_spawn_egg"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "jungleshark"))
                .withAlwaysSpawnEntity()
                .withDifficulty(Difficulty.EASY_VANISHING)
                .addRestriction(WorldRestrictions.OVERWORLD_JUNGLE)
                .withRarity(Rarity.COMMON)
        );

        register(helper("eyelash")
                .addRestriction(WorldRestrictions.OVERWORLD_JUNGLE)
                .withRarity(Rarity.UNCOMMON)
        );

        register(helperOnlyBucket("freshwater_mantis")
                .addRestriction(WorldRestrictions.OVERWORLD_JUNGLE)
                .withDifficulty(Difficulty.MEDIUM_VANISHING_MOVING)
                .withRarity(Rarity.RARE)
                .withBaseChance(1)
        );

        register(fish(new MaybeStack("unusualfishmod", "gnasher_spawn_egg"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "gnasher"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_JUNGLE)
                .withDifficulty(Difficulty.SINGLE_THIN_FAST)
                .withRarity(Rarity.LEGENDARY)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                .withWeather(WeatherRestriction.CLEAR)
                .withBaseChance(1)
        );

        //savanna
        register(fish(new MaybeStack("unusualfishmod", "rhino_tetra_spawn_egg"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "rhino_tetra"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_SAVANNA)
                .withRarity(Rarity.UNCOMMON)
        );

        //mushroom fields
        register(fish(new MaybeStack("unusualfishmod", "kalappa_spawn_egg"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "kalappa"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_MUSHROOM_FIELDS)
                .withDifficulty(Difficulty.MEDIUM_VANISHING)
                .withRarity(Rarity.RARE)
        );


        //underground
        register(fish(new MaybeStack("unusualfishmod", "raw_blind_sailfin"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "blind_sailfin_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "blindsailfin"))
                .withAlwaysSpawnEntity()
                .addRestriction(List.of(DimensionRestriction.OVERWORLD,
                        new ElevationRestriction(Integer.MIN_VALUE, 30, "")))
                .withDifficulty(Difficulty.HARD_VANISHING)
                .withRarity(Rarity.RARE)
                .withBaseChance(2)
        );

        register(helperOnlyBucket("deep_crawler")
                .addRestriction(List.of(DimensionRestriction.OVERWORLD,
                        new ElevationRestriction(Integer.MIN_VALUE, 20, "")))
                .withRarity(Rarity.COMMON)
        );

        register(fish(new MaybeStack("unusualfishmod", "raw_hatchetfish"))
                .withBucketedFish(new MaybeStack("unusualfishmod", "hatchet_fish_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "hatchet_fish"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_DEEPSLATE)
                .withDifficulty(Difficulty.EASY_MOVING)
                .withRarity(Rarity.UNCOMMON)
        );

        register(helperOnlyBucket("mossthorn")
                .addRestriction(List.of(DimensionRestriction.OVERWORLD,
                        BiomeRestriction.LUSH_CAVES,
                        new ElevationRestriction(Integer.MIN_VALUE, 40, "")))
                .withDifficulty(Difficulty.EASY_MOVING)
                .withRarity(Rarity.COMMON)
        );

        register(fish(new MaybeStack("unusualfishmod", "prawn_spawn_egg"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", "prawn"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_DRIPSTONE_CAVES)
                .withDifficulty(Difficulty.SINGLE_THIN_FAST)
                .withRarity(Rarity.LEGENDARY)
        );

        register(helperOnlyBucket("shockcat")
                .addRestriction(WorldRestrictions.OVERWORLD)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.EASY_MOVING)
        );
    }

    public static FishProperties helperOnlyBucket(String s)
    {
        return fish(new MaybeStack("unusualfishmod", s + "_bucket"))
                .withBucketedFish(new MaybeStack("unusualfishmod", s + "_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", s))
                .withAlwaysSpawnEntity()
                .withSizeAndWeight(80, 40, 12000, 7000);
    }

    public static FishProperties helper(String s)
    {
        return fish(new MaybeStack("unusualfishmod", "raw_" + s))
                .withBucketedFish(new MaybeStack("unusualfishmod", s + "_bucket"))
                .withEntityToSpawn(U.holderEntity("unusualfishmod", s))
                .withSizeAndWeight(80, 40, 12000, 7000);
    }
}
