package com.wdiscute.starcatcher.datagen.fish;

import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.FreezeOnMissModifier;
import com.wdiscute.starcatcher.registry.fishrestrictions.*;
import net.minecraft.data.worldgen.BootstrapContext;

import java.util.List;

public class PresetRestrictions
{
    public static final List<AbstractFishRestriction> OVERWORLD =
            List.of(
                    DimensionRestriction.OVERWORLD
            );

    public static FishProperties empty(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty();
    }

    public static FishProperties lake(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.lakes(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties lakeMountain(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                //todo rethink the mountain elevation bias interaction with the base chance
                .withBaseChance(0)
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.lakes(context))
                .addRestriction(ElevationBias.MOUNTAIN)
                .addRestriction(FluidRestriction.WATER)
                ;
    }


    public static FishProperties coldLake(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.coldLakes(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties warmLake(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.warmLakes(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties swamp(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.swamps(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties darkOakForest(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.darkForest(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties forest(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.forest(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }


    public static FishProperties cherryGrove(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.cherryGroves(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties jungle(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.jungles(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties bambooJungle(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.bambooJungle(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties flowerForest(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.flowerForest(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties sunflowerPlains(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.sunflowerPlains(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties river(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.rivers(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties coldRiver(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.coldRivers(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties allOceans(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.allOceans(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties deepOcean(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.deepOceans(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties warmOcean(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.warmOceans(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties coldOcean(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.coldOceans(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties beach(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.beaches(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties mushroomFields(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.mushroomFields(context))
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties underground(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(ElevationRestriction.BELOW_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties caves(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(ElevationRestriction.ZERO_TO_FIFTY)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties dripstoneCaves(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.dripstoneCaves(context))
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties lushCaves(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.lushCaves(context))
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties deepslate(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(ElevationRestriction.BELOW_ZERO)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties deepDark(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(BiomeRestriction.deepDark(context))
                .addRestriction(ElevationRestriction.BELOW_ZERO)
                .addRestriction(FluidRestriction.WATER)
                ;
    }

    public static FishProperties surfaceLava(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(ElevationRestriction.ABOVE_FIFTY)
                .addRestriction(FluidRestriction.LAVA)
                ;
    }

    public static FishProperties undergroundLava(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(ElevationRestriction.BELOW_FIFTY)
                .addRestriction(FluidRestriction.LAVA)
                ;
    }

    public static FishProperties deepslateLava(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.OVERWORLD)
                .addRestriction(ElevationRestriction.BELOW_ZERO)
                .addRestriction(FluidRestriction.LAVA)
                ;
    }

    public static FishProperties netherLava(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.NETHER)
                .addRestriction(FluidRestriction.LAVA)
                ;
    }

    public static FishProperties netherLavaBasaltDeltas(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.NETHER)
                .addRestriction(BiomeRestriction.basaltDeltas(context))
                .addRestriction(FluidRestriction.LAVA)
                ;
    }

    public static FishProperties soulSandValley(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.NETHER)
                .addRestriction(BiomeRestriction.soulSandValley(context))
                .addRestriction(FluidRestriction.LAVA)
                ;
    }

    public static FishProperties end(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.END)
                ;
    }

    public static FishProperties endOuterIslands(BootstrapContext<FishProperties> context)
    {
        return FishProperties.empty()
                .addRestriction(DimensionRestriction.END)
                .addRestriction(BiomeRestriction.outerIslands(context))
                ;
    }
}
