package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.datagen.fish.FishRegistration;
import com.wdiscute.starcatcher.datagen.fish.PresetRestrictions;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.fishrestrictions.BaitRestriction;
import net.minecraft.data.worldgen.BootstrapContext;
import org.jetbrains.annotations.Nullable;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;
import static com.wdiscute.starcatcher.datagen.fish.PresetRestrictions.*;

public class DGUpgradeAquaticFishes
{
    public static void bootstrap(@Nullable BootstrapContext<FishProperties> context)
    {
        //
        //,--. ,--.                                     ,--.               ,---.                               ,--.   ,--.
        //|  | |  |  ,---.   ,---.  ,--.--.  ,--,--.  ,-|  |  ,---.       /  O  \   ,---.  ,--.,--.  ,--,--. ,-'  '-. `--'  ,---.
        //|  | |  | | .-. | | .-. | |  .--' ' ,-.  | ' .-. | | .-. :     |  .-.  | | .-. | |  ||  | ' ,-.  | '-.  .-' ,--. | .--'
        //'  '-'  ' | '-' ' ' '-' ' |  |    \ '-'  | \ `-' | \   --.     |  | |  | ' '-' | '  ''  ' \ '-'  |   |  |   |  | \ `--.
        // `-----'  |  |-'  .`-  /  `--'     `--`--'  `---'   `----'     `--' `--'  `-|  |  `----'   `--`--'   `--'   `--'  `---'
        //          `--'    `---'                                                     `--'

        //todo
        FishRegistration.register(
                context,
                PresetRestrictions.empty(context)
                        .withFish("upgrade_aquatic", "pike")
                        .withBucketedFish(new MaybeStack("upgrade_aquatic", "pike_bucket"))
                        .withEntityToSpawn(U.holderEntity("upgrade_aquatic", "pike"))
                        .withSizeAndWeight(75, 20, 5000, 3000)
                        .withDifficulty(Difficulty.EASY.moving())
                        .withRarity(Rarity.COMMON),
                "upgrade_aquatic"
                //.addRestrictions(DimensionRestriction.OVERWORLD,
                //        new BiomeRestriction(List.of(), List.of(U.rl("upgrade_aquatic", "biome/has_spawn/pike")), List.of(), List.of(), ""))
        );

        FishRegistration.register(
                context,
                PresetRestrictions.empty(context)
                        .withFish("upgrade_aquatic", "perch")
                        .withBucketedFish(new MaybeStack("upgrade_aquatic", "perch_bucket"))
                        .withEntityToSpawn(U.holderEntity("upgrade_aquatic", "perch"))
                        .withSizeAndWeight(27.0f, 11, 500, 352)
                        .withDifficulty(Difficulty.EASY.moving())
                        .withRarity(Rarity.COMMON),
                "upgrade_aquatic"
                //.addRestrictions(DimensionRestriction.OVERWORLD,
                //        new BiomeRestriction(List.of(), List.of(U.rl("upgrade_aquatic", "biome/has_spawn/perch")), List.of(), List.of(), ""))
        );

        FishRegistration.register(
                context,
                PresetRestrictions.empty(context)
                        .withFish("upgrade_aquatic", "lionfish")
                        .withBucketedFish(new MaybeStack("upgrade_aquatic", "lionfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("upgrade_aquatic", "lionfish"))
                        .withSizeAndWeight(27.0f, 11, 500, 352)
                        .withDifficulty(Difficulty.FOUR_BIG_VANISHING)
                        .withRarity(Rarity.UNCOMMON),
                "upgrade_aquatic"
                //.addRestrictions(DimensionRestriction.OVERWORLD,
                //        new BiomeRestriction(List.of(), List.of(U.rl("upgrade_aquatic", "biome/has_spawn/lionfish")), List.of(), List.of(), ""))
        );
    }
}
