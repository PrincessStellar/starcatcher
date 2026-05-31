package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGNetherDepthsUpgradeFishes
{
    public static void bootstrap()
    {

        //
        //,--.  ,--.           ,--.   ,--.                          ,------.                     ,--.   ,--.
        //|  ,'.|  |  ,---.  ,-'  '-. |  ,---.   ,---.  ,--.--.     |  .-.  \   ,---.   ,---.  ,-'  '-. |  ,---.   ,---.
        //|  |' '  | | .-. : '-.  .-' |  .-.  | | .-. : |  .--'     |  |  \  : | .-. : | .-. | '-.  .-' |  .-.  | (  .-'
        //|  | `   | \   --.   |  |   |  | |  | \   --. |  |        |  '--'  / \   --. | '-' '   |  |   |  | |  | .-'  `)
        //`--'  `--'  `----'   `--'   `--' `--'  `----' `--'        `-------'   `----' |  |-'    `--'   `--' `--' `----'
        //                                                                             `--'
        //
        //,--. ,--.                                     ,--.
        //|  | |  |  ,---.   ,---.  ,--.--.  ,--,--.  ,-|  |  ,---.
        //|  | |  | | .-. | | .-. | |  .--' ' ,-.  | ' .-. | | .-. :
        //'  '-'  ' | '-' ' ' '-' ' |  |    \ '-'  | \ `-' | \   --.
        // `-----'  |  |-'  .`-  /  `--'     `--`--'  `---'   `----'
        //          `--'    `---'


        register(netherLavaFish(new MaybeStack("netherdepthsupgrade", "bonefish"))
                .withBucketedFish(new MaybeStack("netherdepthsupgrade", "bonefish_bucket"))
                .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "bonefish"))
                .withSizeAndWeight(120, 40, 700, 200)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.MEDIUM_MOVING)
        );

        //TODO ADD STRUCTURE RESTRICTION
        register(netherLavaFish(new MaybeStack("netherdepthsupgrade", "blazefish"))
                .withBucketedFish(new MaybeStack("netherdepthsupgrade", "blazefish_bucket"))
                .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "blazefish"))
                .withSizeAndWeight(160, 29, 5200, 1200)
                .withRarity(Rarity.LEGENDARY)
                .withDifficulty(Difficulty.HARD_VANISHING)
        );

        register(netherLavaCrimsonForestFish(new MaybeStack("netherdepthsupgrade", "eyeball_fish"))
                .withBucketedFish(new MaybeStack("netherdepthsupgrade", "eyeball_fish_bucket"))
                .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "eyeball_fish"))
                .withSizeAndWeight(70, 40, 700, 200)
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.HARD_MOVING)
        );

        register(netherLavaFish(new MaybeStack("netherdepthsupgrade", "glowdine"))
                .withBucketedFish(new MaybeStack("netherdepthsupgrade", "glowdine_bucket"))
                .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "glowdine"))
                .withSizeAndWeight(130, 30, 3400, 900)
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.MEDIUM_VANISHING_MOVING)
        );

        register(netherLavaWarpedForestFish(new MaybeStack("netherdepthsupgrade", "lava_pufferfish"))
                .withBucketedFish(new MaybeStack("netherdepthsupgrade", "lava_pufferfish_bucket"))
                .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "lava_pufferfish"))
                .withSizeAndWeight(90, 30, 3700, 900)
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.HARD_MOVING)
        );

        register(netherLavaBasaltDeltasFish(new MaybeStack("netherdepthsupgrade", "magmacubefish"))
                .withBucketedFish(new MaybeStack("netherdepthsupgrade", "magmacubefish_bucket"))
                .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "magmacubefish"))
                .withSizeAndWeight(120, 40, 3000, 400)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.EASY_MOVING)
        );

        register(netherLavaBasaltDeltasFish(new MaybeStack("netherdepthsupgrade", "obsidianfish"))
                .withBucketedFish(new MaybeStack("netherdepthsupgrade", "obsidianfish_bucket"))
                .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "obsidianfish"))
                .withSizeAndWeight(200, 50, 500000, 68000)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.FOUR_STONE_SPOTS)
        );

        register(netherLavaFish(new MaybeStack("netherdepthsupgrade", "searing_cod"))
                .withBucketedFish(new MaybeStack("netherdepthsupgrade", "searing_cod_bucket"))
                .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "searing_cod"))
                .withSizeAndWeight(500, 50, 80000, 20000)
                .withRarity(Rarity.UNCOMMON)
                .withDifficulty(Difficulty.EASY_FAST_FISH)
        );

        register(netherLavaSoulSandValleyFish(new MaybeStack("netherdepthsupgrade", "soulsucker"))
                .withBucketedFish(new MaybeStack("netherdepthsupgrade", "soulsucker_bucket"))
                .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "soulsucker"))
                .withSizeAndWeight(140, 30, 12000, 3000)
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.MEDIUM_VANISHING)
        );

        register(netherLavaSoulSandValleyFish(new MaybeStack("netherdepthsupgrade", "wither_bonefish"))
                .withBucketedFish(new MaybeStack("netherdepthsupgrade", "wither_bonefish_bucket"))
                .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "wither_bonefish"))
                .withSizeAndWeight(400, 100, 32000, 7000)
                .withRarity(Rarity.EPIC)
                .withDifficulty(Difficulty.HARD_MOVING)
        );
    }
}
