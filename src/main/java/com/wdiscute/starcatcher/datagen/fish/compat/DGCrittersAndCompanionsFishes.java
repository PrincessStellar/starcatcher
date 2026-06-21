package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.datagen.fish.FishRegistration;
import com.wdiscute.starcatcher.datagen.fish.PresetRestrictions;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import net.minecraft.data.worldgen.BootstrapContext;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;
import static com.wdiscute.starcatcher.datagen.fish.PresetRestrictions.*;

public class DGCrittersAndCompanionsFishes
{


    public static void bootstrap(BootstrapContext<FishProperties> context)
    {

        //
        // ,-----.         ,--.   ,--.     ,--.                                                    ,--.      ,-----.                                              ,--.
        //'  .--./ ,--.--. `--' ,-'  '-. ,-'  '-.  ,---.  ,--.--.  ,---.       ,--,--. ,--,--,   ,-|  |     '  .--./  ,---.  ,--,--,--.  ,---.   ,--,--. ,--,--,  `--'  ,---.  ,--,--,   ,---.
        //|  |     |  .--' ,--. '-.  .-' '-.  .-' | .-. : |  .--' (  .-'      ' ,-.  | |      \ ' .-. |     |  |     | .-. | |        | | .-. | ' ,-.  | |      \ ,--. | .-. | |      \ (  .-'
        //'  '--'\ |  |    |  |   |  |     |  |   \   --. |  |    .-'  `)     \ '-'  | |  ||  | \ `-' |     '  '--'\ ' '-' ' |  |  |  | | '-' ' \ '-'  | |  ||  | |  | ' '-' ' |  ||  | .-'  `)
        // `-----' `--'    `--'   `--'     `--'    `----' `--'    `----'       `--`--' `--''--'  `---'       `-----'  `---'  `--`--`--' |  |-'   `--`--' `--''--' `--'  `---'  `--''--' `----'
        //                                                                                                                              `--'


        FishRegistration.register(context,
                PresetRestrictions.beach(context)
                        .withFish("crittersandcompanions", "clam")
                        .withSkipsMinigame()
        );

        FishRegistration.register(context,
                PresetRestrictions.river(context)
                        .withFish("crittersandcompanions", "koi_fish")
                        .withBucketedFish(new MaybeStack("crittersandcompanions", "koi_fish_bucket"))
                        .withEntityToSpawn(U.holderEntity("crittersandcompanions", "koi_fish"))
                        .withSizeAndWeight(60, 20, 3000, 2000)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.COMMON)
        );
    }
}
