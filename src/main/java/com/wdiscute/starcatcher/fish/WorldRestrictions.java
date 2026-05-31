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
                    BiomeRestriction.LUSH_CAVES,
                    ElevationRestriction.BELOW_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_LUSH_CAVES_AND_JUNGLES =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.LUSH_CAVES_AND_JUNGLES,
                    ElevationRestriction.BELOW_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_BAMBOO_JUNGLE =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.BAMBOO_JUNGLE,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_STONE_CAVES =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    ElevationRestriction.ZERO_TO_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_DEEPSLATE =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    ElevationRestriction.BELOW_ZERO
            );

    public static final List<AbstractFishRestriction> OVERWORLD_DRIPSTONE_CAVES =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.DRIPSTONE_CAVES,
                    ElevationRestriction.BELOW_ZERO
            );

    public static final List<AbstractFishRestriction> OVERWORLD_DEEP_DARK =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.DEEP_DARK,
                    ElevationRestriction.BELOW_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_RIVER =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.RIVERS,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_ALL_OCEANS =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.ALL_OCEANS,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_OCEAN =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.NORMAL_OCEANS,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_LUKEWARM_OCEAN =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.LUKEWARM_OCEAN,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_COLD_AND_LUKEWARM_OCEAN =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.COLD_AND_LUKEWARM_OCEAN,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_WARM_OCEAN =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.WARM_OCEANS,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_DEEP_OCEAN =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.DEEP_OCEANS,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_MOUNTAIN =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.LAKES,
                    ElevationRestriction.ABOVE_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_LAKE =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.LAKES,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_WARM_LAKE =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.WARM_LAKES,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_SAVANNA =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.SAVANNAS,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_COLD_RIVER =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.COLD_RIVERS,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_COLD_OCEAN =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.COLD_OCEANS,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_COLD_LAKE =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.COLD_LAKES,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_COLD_MOUNTAIN =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.COLD_LAKES,
                    ElevationRestriction.ABOVE_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_BEACH =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.BEACHES,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_MUSHROOM_FIELDS =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.MUSHROOM_FIELDS,
                    ElevationRestriction.FIFTY_TO_HUNDRED
            );

    public static final List<AbstractFishRestriction> OVERWORLD_JUNGLE =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.JUNGLES,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_TAIGA =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.TAIGAS,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_CHERRY_GROVE =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.CHERRY_GROVES,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_JUNGLES_AND_SWAMPS =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.JUNGLES_AND_SWAMPS,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_SWAMPS =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.SWAMPS,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_SWAMP_ONLY =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.SWAMP_ONLY,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_MANGROVE_SWAMP =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.MANGROVE_SWAMP,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_DARK_FOREST =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.DARK_FOREST,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_FOREST =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    BiomeRestriction.FOREST,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_SURFACE =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    ElevationRestriction.ABOVE_FIFTY
            );

    public static final List<AbstractFishRestriction> OVERWORLD_LAVA_SURFACE =
            List.of(
                    DimensionRestriction.OVERWORLD,
                    ElevationRestriction.ABOVE_FIFTY,
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
                    DimensionRestriction.END
            );

    public static final List<AbstractFishRestriction> END_OUTER_ISLANDS =
            List.of(
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

    public static List<ResourceLocation> getBiomesAsListFromTags(List<ResourceLocation> biomes, List<ResourceLocation> tags, Level level)
    {
        level.registryAccess().registry(Registries.BIOME);

        List<ResourceLocation> rls = new ArrayList<>();

        for (ResourceLocation rl : tags)
        {
            TagKey<Biome> biomeBeingChecked = TagKey.create(Registries.BIOME, rl);

            Optional<HolderSet.Named<Biome>> optional = level.registryAccess().lookupOrThrow(Registries.BIOME).get(biomeBeingChecked);

            if (optional.isPresent())
            {
                for (Holder<Biome> biomeHolder : optional.get())
                {
                    String biomeString = biomeHolder.getRegisteredName();

                    rls.add(ResourceLocation.parse(biomeString));
                }
            }
        }

        for (ResourceLocation rl : biomes)
        {
            Optional<Holder.Reference<Biome>> optional = level.registryAccess().lookupOrThrow(Registries.BIOME).get(ResourceKey.create(Registries.BIOME, rl));
            if (optional.isPresent()) if (!rls.contains(rl)) rls.add(rl);
        }

        return rls;
    }

    public static List<ResourceLocation> getBiomesBlacklistAsList(List<ResourceLocation> biomesBlacklist, List<ResourceLocation> biomesBlacklistTags, Level level)
    {
        level.registryAccess().registry(Registries.BIOME);

        List<ResourceLocation> rls = new ArrayList<>();

        for (ResourceLocation rl : biomesBlacklistTags)
        {
            TagKey<Biome> biomeBeingChecked = TagKey.create(Registries.BIOME, rl);

            Optional<HolderSet.Named<Biome>> optional = level.registryAccess().lookupOrThrow(Registries.BIOME).get(biomeBeingChecked);

            if (optional.isPresent())
            {
                for (Holder<Biome> biomeHolder : optional.get())
                {
                    String biomeString = biomeHolder.getRegisteredName();

                    rls.add(ResourceLocation.parse(biomeString));
                }
            }
        }

        for (ResourceLocation rl : biomesBlacklist)
        {
            Optional<Holder.Reference<Biome>> optional = level.registryAccess().lookupOrThrow(Registries.BIOME).get(ResourceKey.create(Registries.BIOME, rl));
            if (optional.isPresent()) if (!rls.contains(rl)) rls.add(rl);
        }

        return rls;
    }

}
