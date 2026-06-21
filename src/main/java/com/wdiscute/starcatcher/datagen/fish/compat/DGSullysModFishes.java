package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.datagen.fish.FishRegistration;
import com.wdiscute.starcatcher.datagen.fish.PresetRestrictions;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.fishrestrictions.BiomeRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.DimensionRestriction;
import net.minecraft.data.worldgen.BootstrapContext;

import java.util.List;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGSullysModFishes
{
    public static void bootstrap(BootstrapContext<FishProperties> context)
    {
        //
        // ,---.            ,--. ,--.           ,--.             ,--.   ,--.            ,--.
        //'   .-'  ,--.,--. |  | |  | ,--. ,--. |  |  ,---.      |   `.'   |  ,---.   ,-|  |
        //`.  `-.  |  ||  | |  | |  |  \  '  /  `-'  (  .-'      |  |'.'|  | | .-. | ' .-. |
        //.-'    | '  ''  ' |  | |  |   \   '        .-'  `)     |  |   |  | ' '-' ' \ `-' |
        //`-----'   `----'  `--' `--' .-'  /         `----'      `--'   `--'  `---'   `---'
        //                            `---'

        //todo
        FishRegistration.register(context,
                PresetRestrictions.empty(context)
                        .withFish("sullysmod", "piranha")
                        .withBucketedFish(new MaybeStack("sullysmod", "piranha_bucket"))
                        .withEntityToSpawn(U.holderEntity("sullysmod", "piranha"))
                        .withSizeAndWeight(30, 10, 500, 300)
                        .withRarity(Rarity.UNCOMMON)
                        .withDifficulty(Difficulty.HARD_MOVING)
                //.addRestrictions(DimensionRestriction.OVERWORLD,
                //        new BiomeRestriction(List.of(), List.of(U.rl("sullysmod", "biome/piranha_spawn_in")), List.of(), List.of(), ""))
        );

        FishRegistration.register(context,
                PresetRestrictions.empty(context)
                        .withFish("sullysmod", "lanternfish")
                        .withBucketedFish(new MaybeStack("sullysmod", "lanternfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("sullysmod", "lanternfish"))
                        .withSizeAndWeight(100, 50, 15000, 10000)
                        .withRarity(Rarity.UNCOMMON)
                        .withDifficulty(Difficulty.HARD)
                //.addRestrictions(DimensionRestriction.OVERWORLD,
                //        new BiomeRestriction(List.of(), List.of(U.rl("sullysmod", "biome/lanternfish_spawn_in")), List.of(), List.of(), ""))
        );
    }
}
