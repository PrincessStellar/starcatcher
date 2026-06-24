package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.datagen.fish.FishRegistration;
import com.wdiscute.starcatcher.datagen.fish.PresetRestrictions;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.fishrestrictions.WeatherRestriction;
import com.wdiscute.utils.MaybeStack;
import net.minecraft.data.worldgen.BootstrapContext;
import org.jetbrains.annotations.Nullable;

public class DGFishOfThievesFishes
{
    public static void bootstrap(@Nullable BootstrapContext<FishProperties> context)
    {

        //
        //  ,---. ,--.         ,--.                   ,---.
        // /  .-' `--'  ,---.  |  ,---.       ,---.  /  .-'
        // |  `-, ,--. (  .-'  |  .-.  |     | .-. | |  `-,
        // |  .-' |  | .-'  `) |  | |  |     ' '-' ' |  .-'
        // `--'   `--' `----'  `--' `--'      `---'  `--'
        //   ,--.   ,--.      ,--.
        // ,-'  '-. |  ,---.  `--'  ,---.  ,--.  ,--.  ,---.   ,---.
        // '-.  .-' |  .-.  | ,--. | .-. :  \  `'  /  | .-. : (  .-'
        //   |  |   |  | |  | |  | \   --.   \    /   \   --. .-'  `)
        //   `--'   `--' `--' `--'  `----'    `--'     `----' `----'
        //

        FishRegistration.register(context,
                PresetRestrictions.allOceans(context)
                        .withFish("fishofthieves", "splashtail")
                        .withBucketedFish(new MaybeStack("fishofthieves", "splashtail_bucket"))
                        .withEntityToSpawn(U.holderEntity("fishofthieves", "splashtail"))
                        .withSizeAndWeight(250, 70, 7600, 2000)
                        .withDifficulty(Difficulty.EASY.moving()),
                "fishofthieves"
        );

        FishRegistration.register(context,
                PresetRestrictions.lake(context)
                        .withFish("fishofthieves", "pondie")
                        .withBucketedFish(new MaybeStack("fishofthieves", "pondie_bucket"))
                        .withEntityToSpawn(U.holderEntity("fishofthieves", "pondie"))
                        .withSizeAndWeight(190, 30, 9000, 3600)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.UNCOMMON),
                "fishofthieves"
        );

        FishRegistration.register(context,
                PresetRestrictions.river(context)
                        .withFish("fishofthieves", "islehopper")
                        .withBucketedFish(new MaybeStack("fishofthieves", "islehopper_bucket"))
                        .withEntityToSpawn(U.holderEntity("fishofthieves", "islehopper"))
                        .withSizeAndWeight(300, 20, 23000, 3600)
                        .withDifficulty(Difficulty.MEDIUM.vanishing())
                        .withRarity(Rarity.UNCOMMON),
                "fishofthieves"
        );

        FishRegistration.register(context,
                PresetRestrictions.allOceans(context)
                        .withFish("fishofthieves", "ancientscale")
                        .withBucketedFish(new MaybeStack("fishofthieves", "ancientscale_bucket"))
                        .withEntityToSpawn(U.holderEntity("fishofthieves", "ancientscale"))
                        .withSizeAndWeight(70, 10, 4000, 2000)
                        .withDifficulty(Difficulty.HARD.vanishing())
                        .withRarity(Rarity.RARE),
                "fishofthieves"
        );

        FishRegistration.register(context,
                PresetRestrictions.allOceans(context)
                        .withFish("fishofthieves", "plentifin")
                        .withBucketedFish(new MaybeStack("fishofthieves", "plentifin_bucket"))
                        .withEntityToSpawn(U.holderEntity("fishofthieves", "plentifin"))
                        .withSizeAndWeight(90, 10, 4300, 2500)
                        .withDifficulty(Difficulty.MEDIUM)
                        .withRarity(Rarity.UNCOMMON),
                "fishofthieves"
        );

        FishRegistration.register(context,
                PresetRestrictions.lushCaves(context)
                        .withFish("fishofthieves", "wildsplash")
                        .withBucketedFish(new MaybeStack("fishofthieves", "wildsplash_bucket"))
                        .withEntityToSpawn(U.holderEntity("fishofthieves", "wildsplash"))
                        .withSizeAndWeight(120, 30, 8000, 2200)
                        .withDifficulty(Difficulty.MEDIUM.moving())
                        .withRarity(Rarity.UNCOMMON),
                "fishofthieves"
        );

        FishRegistration.register(context,
                PresetRestrictions.deepslate(context)
                        .withFish("fishofthieves", "devilfish")
                        .withBucketedFish(new MaybeStack("fishofthieves", "devilfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("fishofthieves", "devilfish"))
                        .withSizeAndWeight(180, 80, 20000, 2200)
                        .withDifficulty(Difficulty.HARD)
                        .withRarity(Rarity.RARE),
                "fishofthieves"
        );

        FishRegistration.register(context,
                PresetRestrictions.allOceans(context)
                        .withFish("fishofthieves", "battlegill")
                        .withBucketedFish(new MaybeStack("fishofthieves", "battlegill_bucket"))
                        .withEntityToSpawn(U.holderEntity("fishofthieves", "battlegill"))
                        .withSizeAndWeight(100, 10, 19000, 4200)
                        .withDifficulty(Difficulty.MEDIUM.vanishing())
                        .withRarity(Rarity.UNCOMMON),
                "fishofthieves"
        );

        FishRegistration.register(context,
                PresetRestrictions.end(context)
                        .withFish("fishofthieves", "wrecker")
                        .withBucketedFish(new MaybeStack("fishofthieves", "wrecker_bucket"))
                        .withEntityToSpawn(U.holderEntity("fishofthieves", "wrecker"))
                        .withSizeAndWeight(100, 10, 19000, 4200)
                        .withDifficulty(Difficulty.MEDIUM.vanishing())
                        .withRarity(Rarity.EPIC),
                "fishofthieves"
        );

        FishRegistration.register(context,
                PresetRestrictions.deepOcean(context)
                        .withFish("fishofthieves", "stormfish")
                        .withBucketedFish(new MaybeStack("fishofthieves", "stormfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("fishofthieves", "stormfish"))
                        .withSizeAndWeight(150, 30, 14000, 2000)
                        .withDifficulty(Difficulty.MEDIUM.vanishing())
                        .withWeather(WeatherRestriction.THUNDER)
                        .withRarity(Rarity.RARE),
                "fishofthieves"
        );
    }
}
