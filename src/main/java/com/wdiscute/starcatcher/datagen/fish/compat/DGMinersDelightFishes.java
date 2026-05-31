package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.fish.WorldRestrictions;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGMinersDelightFishes
{
    public static void bootstrap()
    {
        //
        //,--.   ,--. ,--.                          ,--.             ,------.           ,--. ,--.         ,--.        ,--.
        //|   `.'   | `--' ,--,--,   ,---.  ,--.--. |  |  ,---.      |  .-.  \   ,---.  |  | `--'  ,---.  |  ,---.  ,-'  '-.
        //|  |'.'|  | ,--. |      \ | .-. : |  .--' `-'  (  .-'      |  |  \  : | .-. : |  | ,--. | .-. | |  .-.  | '-.  .-'
        //|  |   |  | |  | |  ||  | \   --. |  |         .-'  `)     |  '--'  / \   --. |  | |  | ' '-' ' |  | |  |   |  |
        //`--'   `--' `--' `--''--'  `----' `--'         `----'      `-------'   `----' `--' `--' .`-  /  `--' `--'   `--'
        //                                                                                        `---'

        register(overworldSurfaceFish(new MaybeStack("miners_delight", "squid"))
                .withEntityToSpawn(U.holderEntity("minecraft", "squid"))
                .withSizeAndWeight(40, 20, 1300, 700)
                .withDifficulty(Difficulty.SINGLE_BIG_FAST_MOVING)
                .withRarity(Rarity.UNCOMMON)
                .addRestriction(WorldRestrictions.OVERWORLD_OCEAN)
        );

        register(overworldUndergroundFish(new MaybeStack("miners_delight", "glow_squid"))
                .withEntityToSpawn(U.holderEntity("minecraft", "glow_squid"))
                .withSizeAndWeight(40, 20, 1300, 700)
                .withDifficulty(Difficulty.SINGLE_BIG_FAST_MOVING)
                .withRarity(Rarity.COMMON)
        );
    }
}
