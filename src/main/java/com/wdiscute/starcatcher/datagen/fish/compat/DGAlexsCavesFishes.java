package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.datagen.fish.FishRegistration;
import com.wdiscute.starcatcher.datagen.fish.PresetRestrictions;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.fishrestrictions.BiomeRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.DimensionRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.FluidRestriction;
import com.wdiscute.utils.MaybeStack;
import net.minecraft.data.worldgen.BootstrapContext;
import org.jetbrains.annotations.Nullable;

public class DGAlexsCavesFishes
{
    public static void bootstrap(@Nullable BootstrapContext<FishProperties> context)
    {

        //
        //  ,---.   ,--.                    ,--.              ,-----.
        // /  O  \  |  |  ,---.  ,--.  ,--. |  |  ,---.      '  .--./  ,--,--. ,--.  ,--.  ,---.   ,---.
        //|  .-.  | |  | | .-. :  \  `'  /  `-'  (  .-'      |  |     ' ,-.  |  \  `'  /  | .-. : (  .-'
        //|  | |  | |  | \   --.  /  /.  \       .-'  `)     '  '--'\ \ '-'  |   \    /   \   --. .-'  `)
        //`--' `--' `--'  `----' '--'  '--'      `----'       `-----'  `--`--'    `--'     `----' `----'
        //

        //final BiomeRestriction TOXIC_CAVES = new BiomeRestriction(List.of(U.rl("alexscaves", "toxic_caves")), List.of(), List.of(), List.of(), "");
        //final BiomeRestriction CANDY_CAVITY = new BiomeRestriction(List.of(U.rl("alexscaves", "candy_cavity")), List.of(), List.of(), List.of(), "");
        //final BiomeRestriction ABYSSAL_CHASM = new BiomeRestriction(List.of(U.rl("alexscaves", "abyssal_chasm")), List.of(), List.of(), List.of(), "");
        //final BiomeRestriction PRIMORDIAL_CAVES = new BiomeRestriction(List.of(U.rl("alexscaves", "primordial_caves")), List.of(), List.of(), List.of(), "");

        final BiomeRestriction TOXIC_CAVES = BiomeRestriction.empty();
        final BiomeRestriction CANDY_CAVITY = BiomeRestriction.empty();
        final BiomeRestriction ABYSSAL_CHASM = BiomeRestriction.empty();
        final BiomeRestriction PRIMORDIAL_CAVES = BiomeRestriction.empty();

        FishRegistration.register(context,
                PresetRestrictions.empty(context)
                        .withFish("alexscaves", "radgill")
                        .withBucketedFish(new MaybeStack("alexscaves", "radgill_bucket"))
                        .withEntityToSpawn(U.holderEntity("alexscaves", "radgill"))
                        .withSizeAndWeight(80, 40, 12000, 7000)
                        .withRarity(Rarity.UNCOMMON)
                        .addRestrictions(
                                DimensionRestriction.OVERWORLD,
                                TOXIC_CAVES,
                                FluidRestriction.ACID),
                "alexscaves"
        );

        FishRegistration.register(context,
                PresetRestrictions.empty(context)
                        .withFish("alexscaves", "sweetish_fish_blue")
                        .withBucketedFish(new MaybeStack("alexscaves", "sweetish_fish_blue_bucket"))
                        .withEntityToSpawn(U.holderEntity("alexscaves", "sweetish_fish"))
                        .withSizeAndWeight(80, 40, 12000, 7000)
                        .withRarity(Rarity.UNCOMMON)
                        .addRestrictions(
                                DimensionRestriction.OVERWORLD,
                                CANDY_CAVITY,
                                FluidRestriction.PURPLE_SODA
                        ),
                "alexscaves"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.empty(context)
                        .withFish("alexscaves", "sweetish_fish_green")
                        .withBucketedFish(new MaybeStack("alexscaves", "sweetish_fish_blue_bucket"))
                        .withEntityToSpawn(U.holderEntity("alexscaves", "sweetish_fish"))
                        .withSizeAndWeight(80, 40, 12000, 7000)
                        .withRarity(Rarity.UNCOMMON)
                        .addRestrictions(
                                DimensionRestriction.OVERWORLD,
                                CANDY_CAVITY,
                                FluidRestriction.PURPLE_SODA
                        ),
                "alexscaves"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.empty(context)
                        .withFish("alexscaves", "sweetish_fish_pink")
                        .withBucketedFish(new MaybeStack("alexscaves", "sweetish_fish_pink_bucket"))
                        .withEntityToSpawn(U.holderEntity("alexscaves", "sweetish_fish"))
                        .withSizeAndWeight(80, 40, 12000, 70000)
                        .withRarity(Rarity.UNCOMMON)
                        .addRestrictions(
                                DimensionRestriction.OVERWORLD,
                                CANDY_CAVITY,
                                FluidRestriction.PURPLE_SODA
                        ),
                "alexscaves"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.empty(context)
                        .withFish("alexscaves", "sweetish_fish_red")
                        .withBucketedFish(new MaybeStack("alexscaves", "sweetish_fish_red_bucket"))
                        .withEntityToSpawn(U.holderEntity("alexscaves", "sweetish_fish"))
                        .withSizeAndWeight(80, 40, 12000, 7000)
                        .withRarity(Rarity.UNCOMMON)
                        .addRestrictions(
                                DimensionRestriction.OVERWORLD,
                                CANDY_CAVITY,
                                FluidRestriction.PURPLE_SODA
                        ),
                "alexscaves"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.empty(context)
                        .withFish("alexscaves", "sweetish_fish_yellow")
                        .withBucketedFish(new MaybeStack("alexscaves", "sweetish_fish_yellow_bucket"))
                        .withEntityToSpawn(U.holderEntity("alexscaves", "sweetish_fish"))
                        .withSizeAndWeight(80, 40, 12000, 7000)
                        .withRarity(Rarity.UNCOMMON)
                        .addRestrictions(
                                DimensionRestriction.OVERWORLD,
                                CANDY_CAVITY,
                                FluidRestriction.PURPLE_SODA
                        ),
                "alexscaves"
        );


        FishRegistration.register(
                context,
                PresetRestrictions.empty(context)
                        .withFish("alexscaves", "lanternfish")
                        .withBucketedFish(new MaybeStack("alexscaves", "lanternfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("alexscaves", "lanternfish"))
                        .withSizeAndWeight(100, 50, 15000, 10000)
                        .withRarity(Rarity.RARE)
                        .addRestrictions(
                                DimensionRestriction.OVERWORLD,
                                ABYSSAL_CHASM
                        ),
                "alexscaves"
        );

        FishRegistration.register(
                context,
                PresetRestrictions.empty(context)
                        .withFish("alexscaves", "tripodfish")
                        .withBucketedFish(new MaybeStack("alexscaves", "tripodfish_bucket"))
                        .withEntityToSpawn(U.holderEntity("alexscaves", "tripodfish"))
                        .withSizeAndWeight(30, 10, 1000, 5000)
                        .withRarity(Rarity.RARE)
                        .addRestrictions(
                                DimensionRestriction.OVERWORLD,
                                ABYSSAL_CHASM
                        ),
                "alexscaves"
        );
    }
}
