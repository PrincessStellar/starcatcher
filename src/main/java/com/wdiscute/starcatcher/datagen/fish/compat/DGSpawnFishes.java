package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.datagen.fish.FishRegistration;
import com.wdiscute.starcatcher.datagen.fish.PresetRestrictions;
import com.wdiscute.starcatcher.fish.*;
import com.wdiscute.starcatcher.registry.fishrestrictions.DaytimeRestriction;
import com.wdiscute.utils.MaybeStack;
import net.minecraft.data.worldgen.BootstrapContext;
import org.jetbrains.annotations.Nullable;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;
import static com.wdiscute.starcatcher.datagen.fish.PresetRestrictions.*;

public class DGSpawnFishes
{
    public static void bootstrap(@Nullable BootstrapContext<FishProperties> context)
    {

        //
        // ,---.
        //'   .-'   ,---.   ,--,--. ,--.   ,--. ,--,--,
        //`.  `-.  | .-. | ' ,-.  | |  |.'.|  | |      \
        //.-'    | | '-' ' \ '-'  | |   .'.   | |  ||  |
        //`-----'  |  |-'   `--`--' '--'   '--' `--''--'
        //         `--'

        FishRegistration.register(context,
                PresetRestrictions.deepOcean(context)
                        .withFish("spawn", "angler_fish")
                        .withBucketedFish(new MaybeStack("spawn", "angler_fish_bucket"))
                        .withEntityToSpawn(U.holderEntity("spawn", "angler_fish"))
                        .withSizeAndWeight(80, 40, 12000, 7000)
                        .withRarity(Rarity.RARE)
                        .withDaytimeRestriction(DaytimeRestriction.MIDNIGHT)
                        .withDifficulty(Difficulty.FOUR_AQUA)
                        .withBaseChance(20),
                "spawn"
        );

        FishRegistration.register(context,
                PresetRestrictions.allOceans(context)
                        .withFish("spawn", "herring")
                        .withBucketedFish(new MaybeStack("spawn", "herring_bucket"))
                        .withEntityToSpawn(U.holderEntity("spawn", "herring"))
                        .withSizeAndWeight(80, 40, 12000, 7000)
                        .withRarity(Rarity.COMMON)
                        .withDifficulty(Difficulty.EASY.moving()),
                "spawn"
        );

    }
}
