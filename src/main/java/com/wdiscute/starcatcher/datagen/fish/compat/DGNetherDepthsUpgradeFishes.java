package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.datagen.fish.FishRegistration;
import com.wdiscute.starcatcher.datagen.fish.PresetRestrictions;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import net.minecraft.data.worldgen.BootstrapContext;
import org.jetbrains.annotations.Nullable;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;
import static com.wdiscute.starcatcher.datagen.fish.PresetRestrictions.*;

public class DGNetherDepthsUpgradeFishes
{
    public static void bootstrap(@Nullable BootstrapContext<FishProperties> context)
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


        FishRegistration.register(context,
                PresetRestrictions.netherLava(context)
                        .withFish("netherdepthsupgrade", "bonefish")
                        .withBucketedFish("netherdepthsupgrade", "bonefish_bucket")
                        .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "bonefish"))
                        .withSizeAndWeight(120, 40, 700, 200)
                        .withRarity(Rarity.UNCOMMON)
                        .withDifficulty(Difficulty.MEDIUM_MOVING),
                "netherdepthsupgrade"
        );

        //TODO ADD STRUCTURE RESTRICTION
        FishRegistration.register(context,
                PresetRestrictions.netherLava(context)
                        .withFish("netherdepthsupgrade", "blazefish")
                        .withBucketedFish("netherdepthsupgrade", "blazefish_bucket")
                        .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "blazefish"))
                        .withSizeAndWeight(160, 29, 5200, 1200)
                        .withRarity(Rarity.LEGENDARY)
                        .withDifficulty(Difficulty.HARD_VANISHING),
                "netherdepthsupgrade"
        );

        FishRegistration.register(context,
                PresetRestrictions.netherLava(context)
                        .withFish("netherdepthsupgrade", "eyeball_fish")
                        .withBucketedFish("netherdepthsupgrade", "eyeball_fish_bucket")
                        .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "eyeball_fish"))
                        .withSizeAndWeight(70, 40, 700, 200)
                        .withRarity(Rarity.RARE)
                        .withDifficulty(Difficulty.HARD_MOVING),
                "netherdepthsupgrade"
        );

        FishRegistration.register(context,
                PresetRestrictions.netherLava(context)
                        .withFish("netherdepthsupgrade", "glowdine")
                        .withBucketedFish("netherdepthsupgrade", "glowdine_bucket")
                        .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "glowdine"))
                        .withSizeAndWeight(130, 30, 3400, 900)
                        .withRarity(Rarity.RARE)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING_MOVING),
                "netherdepthsupgrade"
        );

        FishRegistration.register(context,
                PresetRestrictions.netherLava(context)
                        .withFish("netherdepthsupgrade", "lava_pufferfish")
                        .withBucketedFish("netherdepthsupgrade", "lava_pufferfish_bucket")
                        .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "lava_pufferfish"))
                        .withSizeAndWeight(90, 30, 3700, 900)
                        .withRarity(Rarity.EPIC)
                        .withDifficulty(Difficulty.HARD_MOVING),
                "netherdepthsupgrade"
        );

        FishRegistration.register(context,
                PresetRestrictions.netherLava(context)
                        .withFish("netherdepthsupgrade", "magmacubefish")
                        .withBucketedFish("netherdepthsupgrade", "magmacubefish_bucket")
                        .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "magmacubefish"))
                        .withSizeAndWeight(120, 40, 3000, 400)
                        .withRarity(Rarity.UNCOMMON)
                        .withDifficulty(Difficulty.EASY_MOVING),
                "netherdepthsupgrade"
        );

        FishRegistration.register(context,
                PresetRestrictions.netherLava(context)
                        .withFish("netherdepthsupgrade", "obsidianfish")
                        .withBucketedFish("netherdepthsupgrade", "obsidianfish_bucket")
                        .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "obsidianfish"))
                        .withSizeAndWeight(200, 50, 500000, 68000)
                        .withRarity(Rarity.UNCOMMON)
                        .withDifficulty(Difficulty.FOUR_STONE_SPOTS),
                "netherdepthsupgrade"
        );

        FishRegistration.register(context,
                PresetRestrictions.netherLava(context)
                        .withFish("netherdepthsupgrade", "searing_cod")
                        .withBucketedFish("netherdepthsupgrade", "searing_cod_bucket")
                        .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "searing_cod"))
                        .withSizeAndWeight(500, 50, 80000, 20000)
                        .withRarity(Rarity.UNCOMMON)
                        .withDifficulty(Difficulty.EASY_FAST_FISH),
                "netherdepthsupgrade"
        );

        FishRegistration.register(context,
                PresetRestrictions.netherLava(context)
                        .withFish("netherdepthsupgrade", "soulsucker")
                        .withBucketedFish("netherdepthsupgrade", "soulsucker_bucket")
                        .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "soulsucker"))
                        .withSizeAndWeight(140, 30, 12000, 3000)
                        .withRarity(Rarity.EPIC)
                        .withDifficulty(Difficulty.MEDIUM_VANISHING),
                "netherdepthsupgrade"
        );

        FishRegistration.register(context,
                PresetRestrictions.netherLava(context)
                        .withFish("netherdepthsupgrade", "wither_bonefish")
                        .withBucketedFish("netherdepthsupgrade", "wither_bonefish_bucket")
                        .withEntityToSpawn(U.holderEntity("netherdepthsupgrade", "wither_bonefish"))
                        .withSizeAndWeight(400, 100, 32000, 7000)
                        .withRarity(Rarity.EPIC)
                        .withDifficulty(Difficulty.HARD_MOVING),
                "netherdepthsupgrade"
        );
    }
}
