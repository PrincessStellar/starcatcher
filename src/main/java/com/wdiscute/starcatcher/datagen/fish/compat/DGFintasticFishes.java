package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.*;
import com.wdiscute.starcatcher.registry.fishrestrictions.BiomeRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.DimensionRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.ElevationRestriction;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;

import java.util.List;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGFintasticFishes
{
    public static void bootstrap()
    {

        //
        //,------. ,--.            ,--.                      ,--.   ,--.
        //|  .---' `--' ,--,--,  ,-'  '-.  ,--,--.  ,---.  ,-'  '-. `--'  ,---.
        //|  `--,  ,--. |      \ '-.  .-' ' ,-.  | (  .-'  '-.  .-' ,--. | .--'
        //|  |`    |  | |  ||  |   |  |   \ '-'  | .-'  `)   |  |   |  | \ `--.
        //`--'     `--' `--''--'   `--'    `--`--' `----'    `--'   `--'  `---'
        //


        register(fish(new MaybeStack("fintastic", "minnow_bucket"))
                .withBucketedFish(new MaybeStack("fintastic", "minnow_bucket"))
                .withEntityToSpawn(U.holderEntity("fintastic", "minnow"))
                .withAlwaysSpawnEntity()
                .addRestrictions(
                        DimensionRestriction.OVERWORLD,
                        ElevationRestriction.FIFTY_TO_HUNDRED,
                        new BiomeRestriction(
                                List.of(),
                                List.of(
                                        BiomeTags.IS_JUNGLE.location(),
                                        SCTags.IS_SWAMP,
                                        BiomeTags.IS_RIVER.location()
                                ),
                                List.of(),
                                List.of(),
                                ""
                        )
                )
                .withRarity(Rarity.COMMON)
                .withDifficulty(Difficulty.EASY_MOVING)
        );

        register(fish(new MaybeStack("fintastic", "featherback_bucket"))
                .withBucketedFish(new MaybeStack("fintastic", "featherback_bucket"))
                .withEntityToSpawn(U.holderEntity("fintastic", "featherback"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_SWAMPS)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.HARD_MOVING)
        );

        register(fish(new MaybeStack("fintastic", "guppy_bucket"))
                .withBucketedFish(new MaybeStack("fintastic", "guppy_bucket"))
                .withEntityToSpawn(U.holderEntity("fintastic", "guppy"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_JUNGLES_AND_SWAMPS)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.EASY)
        );

        register(fish(new MaybeStack("fintastic", "arapaima_bucket"))
                .withBucketedFish(new MaybeStack("fintastic", "arapaima_bucket"))
                .withEntityToSpawn(U.holderEntity("fintastic", "arapaima"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_JUNGLE)
                .withDifficulty(Difficulty.HARD)
                .withRarity(Rarity.RARE)
        );

        register(fish(new MaybeStack("fintastic", "pleco_bucket"))
                .withBucketedFish(new MaybeStack("fintastic", "pleco_bucket"))
                .withEntityToSpawn(U.holderEntity("fintastic", "pleco"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_JUNGLES_AND_SWAMPS)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.EASY_FAST_FISH)
        );

        register(fish(new MaybeStack("fintastic", "moony_bucket"))
                .withBucketedFish(new MaybeStack("fintastic", "moony_bucket"))
                .withEntityToSpawn(U.holderEntity("fintastic", "moony"))
                .withAlwaysSpawnEntity()
                .addRestrictions(
                        DimensionRestriction.OVERWORLD,
                        ElevationRestriction.FIFTY_TO_HUNDRED,
                        new BiomeRestriction(
                                List.of(
                                        Biomes.BEACH.location(),
                                        Biomes.MANGROVE_SWAMP.location()
                                ),
                                List.of(
                                        SCTags.IS_LUKEWARM_OCEAN,
                                        SCTags.IS_WARM_OCEAN
                                ),
                                List.of(),
                                List.of(),
                                ""
                        )
                )
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.SINGLE_AQUA)
        );

        register(fish(new MaybeStack("fintastic", "coelacanth_spawn_egg"))
                .withEntityToSpawn(U.holderEntity("fintastic", "coelacanth"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_WARM_OCEAN)
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.MEDIUM_VANISHING_MOVING)
        );

        register(fish(new MaybeStack("fintastic", "gourami_bucket"))
                .withBucketedFish(new MaybeStack("fintastic", "gourami_bucket"))
                .withEntityToSpawn(U.holderEntity("fintastic", "gourami"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_SWAMPS)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM_MOVING)
        );

        register(fish(new MaybeStack("fintastic", "daphnia_bucket"))
                .withBucketedFish(new MaybeStack("fintastic", "daphnia_bucket"))
                .withEntityToSpawn(U.holderEntity("fintastic", "daphnia"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_RIVER)
                .withRarity(Rarity.COMMON)
                .withDifficulty(Difficulty.HARD)
        );

        register(fish(new MaybeStack("fintastic", "artemia_bucket"))
                .withBucketedFish(new MaybeStack("fintastic", "artemia_bucket"))
                .withEntityToSpawn(U.holderEntity("fintastic", "artemia"))
                .withAlwaysSpawnEntity()
                .addRestriction(WorldRestrictions.OVERWORLD_WARM_LAKE)
                .withDifficulty(Difficulty.MEDIUM_MOVING)
                .withRarity(Rarity.UNCOMMON)
        );
    }
}
