package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.datagen.fish.FishRegistration;
import com.wdiscute.starcatcher.datagen.fish.PresetRestrictions;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.fishrestrictions.BiomeRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.DimensionRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.SeasonRestriction;
import net.minecraft.data.worldgen.BootstrapContext;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGCollectorsReapFishes
{
    public static void bootstrap(@Nullable BootstrapContext<FishProperties> context)
    {
        //
        // ,-----.         ,--. ,--.                  ,--.                   ,--.             ,------.
        //'  .--./  ,---.  |  | |  |  ,---.   ,---. ,-'  '-.  ,---.  ,--.--. |  |  ,---.      |  .--. '  ,---.   ,--,--.  ,---.
        //|  |     | .-. | |  | |  | | .-. : | .--' '-.  .-' | .-. | |  .--' `-'  (  .-'      |  '--'.' | .-. : ' ,-.  | | .-. |
        //'  '--'\ ' '-' ' |  | |  | \   --. \ `--.   |  |   ' '-' ' |  |         .-'  `)     |  |\  \  \   --. \ '-'  | | '-' '
        // `-----'  `---'  `--' `--'  `----'  `---'   `--'    `---'  `--'         `----'      `--' '--'  `----'  `--`--' |  |-'
        //                                                                                                               `--'

        FishRegistration.register(context,
                PresetRestrictions.empty(context)
                        .withFish("collectorsreap", "platinum_bass")
                        .withBucketedFish(new MaybeStack("collectorsreap", "platinum_bass_bucket"))
                        .withEntityToSpawn(U.holderEntity("collectorsreap", "platinum_bass"))
                        .withSizeAndWeight(40, 12, 1600, 1100)
                        .withDifficulty(Difficulty.EASY.moving())
                        .addRestrictions(
                                DimensionRestriction.OVERWORLD//,
//                        new BiomeRestriction(List.of(), List.of(U.rl("collectorsreap", "biome/has_spawn/platinum_bass")),
//                                List.of(), List.of(), "")
                        ),
                "collectorsreap"
        );


        FishRegistration.register(context,
                PresetRestrictions.empty(context)
                        .withFish("collectorsreap", "tiger_prawn")
                        .withBucketedFish(new MaybeStack("collectorsreap", "tiger_prawn_bucket"))
                        .withEntityToSpawn(U.holderEntity("collectorsreap", "tiger_prawn"))
                        .withSizeAndWeight(28, 8, 260, 60)
                        .withDifficulty(Difficulty.MEDIUM.vanishing())
                        .withRarity(Rarity.RARE)
                        .addRestrictions(
                                DimensionRestriction.OVERWORLD//,
//                        new BiomeRestriction(List.of(), List.of(U.rl("collectorsreap", "biome/has_spawn/tiger_prawn")),
//                                List.of(), List.of(), "")
                        ),
                "collectorsreap"
        );

        FishRegistration.register(context,
                PresetRestrictions.empty(context)
                        .withFish("collectorsreap", "clam")  //no mini game
                        .withBucketedFish(new MaybeStack("collectorsreap", "clam_bucket"))
                        .withEntityToSpawn(U.holderEntity("collectorsreap", "clam"))
                        .withSkipsMinigame()
                        .addRestrictions(
                                DimensionRestriction.OVERWORLD//,
//                        new BiomeRestriction(List.of(), List.of(U.rl("collectorsreap", "biome/has_spawn/clam")),
//                                List.of(), List.of(), "")
                        ),
                "collectorsreap"
        );

        FishRegistration.register(context,
                PresetRestrictions.empty(context)
                        .withFish("collectorsreap", "urchin")  //no mini game
                        .withBucketedFish(new MaybeStack("collectorsreap", "urchin_bucket"))
                        .withEntityToSpawn(U.holderEntity("collectorsreap", "urchin"))
                        .withHasGuideEntry(false)
                        .withSkipsMinigame()
                        .addRestrictions(
                                DimensionRestriction.OVERWORLD//,
//                        new BiomeRestriction(List.of(), List.of(U.rl("collectorsreap", "biome/has_spawn/urchin")),
//                                List.of(), List.of(), "")
                        ),
                "collectorsreap"
        );


        FishRegistration.register(context,
                PresetRestrictions.empty(context)
                        .withFish("collectorsreap", "chieftain_crab")
                        .withBucketedFish(new MaybeStack("collectorsreap", "chieftain_crab_bucket"))
                        .withEntityToSpawn(U.holderEntity("collectorsreap", "chieftain_crab"))
                        .withSizeAndWeight(28, 8, 260, 60)
                        .withDifficulty(Difficulty.NETHER_CRAB)
                        .withRarity(Rarity.UNCOMMON)
                        .addRestrictions(
                                DimensionRestriction.OVERWORLD//,
//                        new BiomeRestriction(List.of(), List.of(U.rl("collectorsreap", "biome/has_spawn/chieftain_crab")),
//                                List.of(), List.of(), "")
                        )
                        .withAlwaysSpawnEntity()
                        .withItemToOverrideWith(new MaybeStack(SCItems.UNKNOWN_FISH)),
                "collectorsreap"
        );
    }
}
