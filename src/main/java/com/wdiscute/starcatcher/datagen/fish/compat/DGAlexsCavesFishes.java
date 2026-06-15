package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.fishrestrictions.BiomeRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.DimensionRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.FluidRestriction;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

import java.util.List;

public class DGAlexsCavesFishes
{
    public static void bootstrap()
    {

        //
        //  ,---.   ,--.                    ,--.              ,-----.
        // /  O  \  |  |  ,---.  ,--.  ,--. |  |  ,---.      '  .--./  ,--,--. ,--.  ,--.  ,---.   ,---.
        //|  .-.  | |  | | .-. :  \  `'  /  `-'  (  .-'      |  |     ' ,-.  |  \  `'  /  | .-. : (  .-'
        //|  | |  | |  | \   --.  /  /.  \       .-'  `)     '  '--'\ \ '-'  |   \    /   \   --. .-'  `)
        //`--' `--' `--'  `----' '--'  '--'      `----'       `-----'  `--`--'    `--'     `----' `----'
        //

        final BiomeRestriction TOXIC_CAVES = new BiomeRestriction(List.of(U.rl("alexscaves", "toxic_caves")), List.of(), List.of(), List.of(), "");
        final BiomeRestriction CANDY_CAVITY = new BiomeRestriction(List.of(U.rl("alexscaves", "candy_cavity")), List.of(), List.of(), List.of(), "");
        final BiomeRestriction ABYSSAL_CHASM = new BiomeRestriction(List.of(U.rl("alexscaves", "abyssal_chasm")), List.of(), List.of(), List.of(), "");
        final BiomeRestriction PRIMORDIAL_CAVES = new BiomeRestriction(List.of(U.rl("alexscaves", "primordial_caves")), List.of(), List.of(), List.of(), "");


        register(fish(new MaybeStack("alexscaves", "radgill"))
                .withBucketedFish(new MaybeStack("alexscaves", "radgill_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "radgill"))
                .withSizeAndWeight(80, 40, 12000, 7000)
                .withRarity(Rarity.UNCOMMON)
                .addRestrictions(
                        DimensionRestriction.OVERWORLD,
                        TOXIC_CAVES,
                        FluidRestriction.ACID)
        );

        register(fish(new MaybeStack("alexscaves", "sweetish_fish_blue"))
                .withBucketedFish(new MaybeStack("alexscaves", "sweetish_fish_blue_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "sweetish_fish"))
                .withSizeAndWeight(80, 40, 12000, 7000)
                .withRarity(Rarity.UNCOMMON)
                .addRestrictions(
                        DimensionRestriction.OVERWORLD,
                        CANDY_CAVITY,
                        FluidRestriction.PURPLE_SODA
                )
        );

        register(fish(new MaybeStack("alexscaves", "sweetish_fish_green"))
                .withBucketedFish(new MaybeStack("alexscaves", "sweetish_fish_blue_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "sweetish_fish"))
                .withSizeAndWeight(80, 40, 12000, 7000)
                .withRarity(Rarity.UNCOMMON)
                .addRestrictions(
                        DimensionRestriction.OVERWORLD,
                        CANDY_CAVITY,
                        FluidRestriction.PURPLE_SODA
                )
        );

        register(fish(new MaybeStack("alexscaves", "sweetish_fish_pink"))
                .withBucketedFish(new MaybeStack("alexscaves", "sweetish_fish_pink_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "sweetish_fish"))
                .withSizeAndWeight(80, 40, 12000, 70000)
                .withRarity(Rarity.UNCOMMON)
                .addRestrictions(
                        DimensionRestriction.OVERWORLD,
                        CANDY_CAVITY,
                        FluidRestriction.PURPLE_SODA
                )
        );

        register(fish(new MaybeStack("alexscaves", "sweetish_fish_red"))
                .withBucketedFish(new MaybeStack("alexscaves", "sweetish_fish_red_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "sweetish_fish"))
                .withSizeAndWeight(80, 40, 12000, 7000)
                .withRarity(Rarity.UNCOMMON)
                .addRestrictions(
                        DimensionRestriction.OVERWORLD,
                        CANDY_CAVITY,
                        FluidRestriction.PURPLE_SODA
                )
        );

        register(fish(new MaybeStack("alexscaves", "sweetish_fish_yellow"))
                .withBucketedFish(new MaybeStack("alexscaves", "sweetish_fish_yellow_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "sweetish_fish"))
                .withSizeAndWeight(80, 40, 12000, 7000)
                .withRarity(Rarity.UNCOMMON)
                .addRestrictions(
                        DimensionRestriction.OVERWORLD,
                        CANDY_CAVITY,
                        FluidRestriction.PURPLE_SODA
                )
        );


        register(fish(new MaybeStack("alexscaves", "lanternfish"))
                .withBucketedFish(new MaybeStack("alexscaves", "lanternfish_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "lanternfish"))
                .withSizeAndWeight(100, 50, 15000, 10000)
                .withRarity(Rarity.RARE)
                .addRestrictions(
                        DimensionRestriction.OVERWORLD,
                        ABYSSAL_CHASM
                )
        );

        register(fish(new MaybeStack("alexscaves", "tripodfish"))
                .withBucketedFish(new MaybeStack("alexscaves", "tripodfish_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "tripodfish"))
                .withSizeAndWeight(30, 10, 1000, 5000)
                .withRarity(Rarity.RARE)
                .addRestrictions(
                        DimensionRestriction.OVERWORLD,
                        ABYSSAL_CHASM
                )
        );


        register(fish(new MaybeStack("alexscaves", "trilocaris_tail"))
                .withBucketedFish(new MaybeStack("alexscaves", "trilocaris_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "trilocaris"))
                .withSizeAndWeight(30, 10, 1000, 5000)
                .withRarity(Rarity.RARE)
                .addRestrictions(
                        DimensionRestriction.OVERWORLD,
                        PRIMORDIAL_CAVES
                )
                .withAlwaysSpawnEntity()
                .withEntityToSpawn(U.holderEntity("alexscaves", "trilocaris"))
                .withItemToOverrideWith(new MaybeStack(SCItems.UNKNOWN_FISH))
        );
    }
}
