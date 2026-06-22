package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.datagen.fish.FishRegistration;
import com.wdiscute.starcatcher.datagen.fish.PresetRestrictions;
import com.wdiscute.starcatcher.fish.*;
import net.minecraft.data.worldgen.BootstrapContext;
import org.jetbrains.annotations.Nullable;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;
import static com.wdiscute.starcatcher.datagen.fish.PresetRestrictions.*;

public class DGMinersDelightFishes
{
    public static void bootstrap(@Nullable BootstrapContext<FishProperties> context)
    {
        //
        //,--.   ,--. ,--.                          ,--.             ,------.           ,--. ,--.         ,--.        ,--.
        //|   `.'   | `--' ,--,--,   ,---.  ,--.--. |  |  ,---.      |  .-.  \   ,---.  |  | `--'  ,---.  |  ,---.  ,-'  '-.
        //|  |'.'|  | ,--. |      \ | .-. : |  .--' `-'  (  .-'      |  |  \  : | .-. : |  | ,--. | .-. | |  .-.  | '-.  .-'
        //|  |   |  | |  | |  ||  | \   --. |  |         .-'  `)     |  '--'  / \   --. |  | |  | ' '-' ' |  | |  |   |  |
        //`--'   `--' `--' `--''--'  `----' `--'         `----'      `-------'   `----' `--' `--' .`-  /  `--' `--'   `--'
        //                                                                                        `---'

        FishRegistration.register(context,
                PresetRestrictions.river(context)
                        .withFish("miners_delight", "squid")
                        .withEntityToSpawn(U.holderEntity("minecraft", "squid"))
                        .withSizeAndWeight(40, 20, 1300, 700)
                        .withDifficulty(Difficulty.SINGLE_BIG_FAST_MOVING)
                        .withRarity(Rarity.UNCOMMON),
                "miners_delight"
        );

        FishRegistration.register(context,
                PresetRestrictions.underground(context)
                        .withFish("miners_delight", "glow_squid")
                        .withEntityToSpawn(U.holderEntity("minecraft", "glow_squid"))
                        .withSizeAndWeight(40, 20, 1300, 700)
                        .withDifficulty(Difficulty.SINGLE_BIG_FAST_MOVING)
                        .withRarity(Rarity.COMMON),
                "miners_delight"
        );
    }
}
