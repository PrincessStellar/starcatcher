package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.datagen.fish.FishRegistration;
import com.wdiscute.starcatcher.datagen.fish.PresetRestrictions;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import net.minecraft.data.worldgen.BootstrapContext;
import org.jetbrains.annotations.Nullable;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;
import static com.wdiscute.starcatcher.datagen.fish.PresetRestrictions.*;

public class DGBetterEndFishes
{
    public static void bootstrap(@Nullable BootstrapContext<FishProperties> context)
    {

        //
        //,--.              ,--.     ,--.                                           ,--.
        //|  |-.   ,---.  ,-'  '-. ,-'  '-.  ,---.  ,--.--.      ,---.  ,--,--,   ,-|  |
        //| .-. ' | .-. : '-.  .-' '-.  .-' | .-. : |  .--'     | .-. : |      \ ' .-. |
        //| `-' | \   --.   |  |     |  |   \   --. |  |        \   --. |  ||  | \ `-' |
        // `---'   `----'   `--'     `--'    `----' `--'         `----' `--''--'  `---'
        //

        FishRegistration.register(context,
                PresetRestrictions.endOuterIslands(context)
                        .withFish("betterend", "end_fish_raw")
                        .withBucketedFish(new MaybeStack("betterend", "bucket_end_fish"))
                        .withEntityToSpawn(U.holderEntity("betterend", "end_fish"))
                        .withSizeAndWeight(80, 40, 2000, 400)
                        .withRarity(Rarity.RARE)
        );
    }
}
