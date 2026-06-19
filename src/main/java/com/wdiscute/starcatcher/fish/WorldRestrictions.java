package com.wdiscute.starcatcher.fish;

import com.wdiscute.starcatcher.registry.fishrestrictions.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorldRestrictions
{
    public static final List<AbstractFishRestriction> OVERWORLD =
            List.of(
                    DimensionRestriction.OVERWORLD
            );

    public static final List<AbstractFishRestriction> OVERWORLD_LUSH_CAVES =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    FluidRestriction.WATER,
                    BiomeRestriction.LUSH_CAVES,
                    ElevationRestriction.BELOW_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_LUSH_CAVES_AND_JUNGLES =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.LUSH_CAVES_AND_JUNGLES,
                    FluidRestriction.WATER,
                    ElevationRestriction.BELOW_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_BAMBOO_JUNGLE =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    FluidRestriction.WATER,
                    BiomeRestriction.BAMBOO_JUNGLE,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> FLOWER_FOREST =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    FluidRestriction.WATER,
                    BiomeRestriction.FLOWER_FOREST,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> SUNFLOWER_PLAINS =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    FluidRestriction.WATER,
                    BiomeRestriction.SUNFLOWER_PLAINS,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_STONE_CAVES =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    ElevationRestriction.ZERO_TO_FIFTY,
                    FluidRestriction.WATER
            );

    public static final List<AbstractFishRestriction> OVERWORLD_DEEPSLATE =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    FluidRestriction.WATER,
                    ElevationRestriction.BELOW_ZERO
            );

    public static final List<AbstractFishRestriction> OVERWORLD_DRIPSTONE_CAVES =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.DRIPSTONE_CAVES,
                    FluidRestriction.WATER,
                    ElevationRestriction.BELOW_ZERO
            );

    public static final List<AbstractFishRestriction> OVERWORLD_DEEP_DARK =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.DEEP_DARK,
                    FluidRestriction.WATER,
                    ElevationRestriction.BELOW_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_RIVER =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    FluidRestriction.WATER,
                    BiomeRestriction.RIVERS,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_ALL_OCEANS =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    FluidRestriction.WATER,
                    BiomeRestriction.ALL_OCEANS,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_OCEAN =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    FluidRestriction.WATER,
                    BiomeRestriction.NORMAL_OCEANS,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_LUKEWARM_OCEAN =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    FluidRestriction.WATER,
                    BiomeRestriction.LUKEWARM_OCEAN,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_COLD_AND_LUKEWARM_OCEAN =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    FluidRestriction.WATER,
                    BiomeRestriction.COLD_AND_LUKEWARM_OCEAN,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_WARM_OCEAN =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    FluidRestriction.WATER,
                    BiomeRestriction.WARM_OCEANS,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_DEEP_OCEAN =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.DEEP_OCEANS,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_MOUNTAIN =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.LAKES,
                    ElevationRestriction.ABOVE_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_SKY =
            List.of(
                    FluidRestriction.VOID,
                    DimensionRestriction.OVERWORLD,
                    ElevationRestriction.ABOVE_TWO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_LAKE =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.LAKES,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_WARM_LAKE =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.WARM_LAKES,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_SAVANNA =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.SAVANNAS,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_COLD_RIVER =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.COLD_RIVERS,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_COLD_OCEAN =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.COLD_OCEANS,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_COLD_LAKE =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.COLD_LAKES,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_COLD_MOUNTAIN =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.COLD_LAKES,
                    ElevationRestriction.ABOVE_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_BEACH =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.BEACHES,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_MUSHROOM_FIELDS =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.MUSHROOM_FIELDS,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_JUNGLE =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.JUNGLES,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_TAIGA =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.TAIGAS,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_CHERRY_GROVE =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.CHERRY_GROVES,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_JUNGLES_AND_SWAMPS =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.JUNGLES_AND_SWAMPS,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_SWAMPS =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.SWAMPS,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_SWAMP_ONLY =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.SWAMP_ONLY,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_MANGROVE_SWAMP =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.MANGROVE_SWAMP,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_DARK_FOREST =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.DARK_FOREST,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_FOREST =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.FOREST,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_SURFACE =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.OVERWORLD,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_LAVA_SURFACE =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    ElevationRestriction.ABOVE_FIFTY,
                    BiomeRestriction.LAKES,
                    FluidRestriction.LAVA
            );

    public static final List<AbstractFishRestriction> OVERWORLD_LAVA_UNDERGROUND =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    ElevationRestriction.BELOW_FIFTY,
                    FluidRestriction.LAVA
            );

    public static final List<AbstractFishRestriction> OVERWORLD_UNDERGROUND =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    ElevationRestriction.BELOW_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_LAVA_DEEPSLATE =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    ElevationRestriction.BELOW_ZERO,
                    FluidRestriction.LAVA
            );

    public static final List<AbstractFishRestriction> NETHER_LAVA =
            List.of(
                    DimensionRestriction.NETHER,
                    FluidRestriction.LAVA
            );

    public static final List<AbstractFishRestriction> NETHER_LAVA_CRIMSON_FOREST =
            List.of(
                    DimensionRestriction.NETHER,
                    BiomeRestriction.CRIMSON_FOREST,
                    FluidRestriction.LAVA
            );

    public static final List<AbstractFishRestriction> NETHER_LAVA_WARPED_FOREST =
            List.of(
                    DimensionRestriction.NETHER,
                    BiomeRestriction.WARPED_FOREST,
                    FluidRestriction.LAVA
            );

    public static final List<AbstractFishRestriction> NETHER_LAVA_SOUL_SAND_VALLEY =
            List.of(
                    DimensionRestriction.NETHER,
                    BiomeRestriction.SOUL_SAND_VALLEY,
                    FluidRestriction.LAVA
            );

    public static final List<AbstractFishRestriction> NETHER_LAVA_BASALT_DELTAS =
            List.of(
                    DimensionRestriction.NETHER,
                    BiomeRestriction.BASALT_DELTAS,
                    FluidRestriction.LAVA
            );

    public static final List<AbstractFishRestriction> END =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.END
            );

    public static final List<AbstractFishRestriction> END_OUTER_ISLANDS =
            List.of(
                    FluidRestriction.WATER,
                    DimensionRestriction.END,
                    BiomeRestriction.OUTER_ISLANDS
            );

    public static final List<AbstractFishRestriction> END_VOID_OUTER_ISLANDS =
            List.of(
                    FluidRestriction.VOID,
                    DimensionRestriction.END,
                    BiomeRestriction.OUTER_ISLANDS
            );

    public static final List<AbstractFishRestriction> OVERWORLD_VOID =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    ElevationRestriction.BELOW_MINUS_SIXTY_FOUR,
                    FluidRestriction.VOID
            );

    public static final List<AbstractFishRestriction> NETHER_VOID =
            List.of(
                    DimensionRestriction.NETHER,
                    ElevationRestriction.BELOW_ZERO,
                    FluidRestriction.VOID
            );

    public static final List<AbstractFishRestriction> END_VOID =
            List.of(
                    DimensionRestriction.END,
                    FluidRestriction.VOID
            );
}
