package com.wdiscute.starcatcher.registry.fishrestrictions;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.antlr.v4.runtime.misc.Triple;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DimensionRestriction extends AbstractFishRestriction
{

    private final List<ResourceLocation> dimensions;
    private final List<ResourceLocation> dimensionsBlacklist;

    public static final MapCodec<DimensionRestriction> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    ResourceLocation.CODEC.listOf().fieldOf("dimensions").forGetter(DimensionRestriction::getDimensions),
                    ResourceLocation.CODEC.listOf().fieldOf("dimensions_blacklist").forGetter(DimensionRestriction::getDimensionsBlacklist)
            ).apply(instance, DimensionRestriction::new));

    public DimensionRestriction()
    {
        this.dimensions = List.of();
        this.dimensionsBlacklist = List.of();
    }

    public DimensionRestriction(List<ResourceLocation> dimensions, List<ResourceLocation> dimensionsBlacklist)
    {
        this.dimensions = dimensions;
        this.dimensionsBlacklist = dimensionsBlacklist;
    }

    public List<ResourceLocation> getDimensions()
    {
        return dimensions;
    }

    public List<ResourceLocation> getDimensionsBlacklist()
    {
        return dimensionsBlacklist;
    }

    @Override
    public MapCodec<? extends AbstractFishRestriction> codec()
    {
        return CODEC;
    }

    @Override
    public DeferredHolder<AbstractFishRestriction, AbstractFishRestriction> getRegistryHolder()
    {
        return SCFishRestrictions.DIMENSION;
    }

    @Override
    public int getFishChance(int currentChance, Level level, FishProperties fp, @NotNull Entity entity, ItemStack rod, Context context)
    {
        ResourceLocation dim = level.dimension().location();
        if (!dimensions.isEmpty() && !dimensions.contains(dim)) return -9999;
        if (dimensionsBlacklist.contains(dim)) return -9999;
        return 0;
    }

    @Override
    public Triple<Component, List<Component>, List<Component>> getPageDescription(Level level, FishProperties fp, @Nullable Player player, Context context)
    {
        MutableComponent comp;
        List<Component> hover = new ArrayList<>();
        List<Component> blacklist = new ArrayList<>();

        if (dimensions.isEmpty())
        {
            comp = Component.translatable("gui.guide.no_restriction");
        }
        else
        {
            //if there's only one dimension
            if (dimensions.size() == 1)
            {
                comp = Component.translatable("dimension." + dimensions.getFirst().toLanguageKey());
            }
            else
            {
                comp = Component.translatable("gui.guide.hover");

                //show tooltip while hovering
                List<Component> c = new ArrayList<>();

                c.add(Component.translatable("gui.guide.dimensions"));

                for (ResourceLocation dimension : dimensions)
                    hover.add(Component.translatable("dimension." + dimension.toLanguageKey()));
            }
        }

        if (dimensions.isEmpty())
            comp.withStyle(Style.EMPTY.withColor(0x40752c));
        else if (dimensions.contains(level.dimension().location()))
            comp.withStyle(Style.EMPTY.withColor(0x40752c));
        else
            comp.withStyle(Style.EMPTY.withColor(0xa34536));


        if (!dimensionsBlacklist.isEmpty())
        {
            //show tooltip while hovering
            blacklist.add(Component.translatable("gui.guide.blacklisted_dimensions"));

            for (ResourceLocation resourceLocation : dimensionsBlacklist)
                blacklist.add(Component.literal(resourceLocation.toString()));
        }

        Component start = Component.translatable("gui.guide.dimension");

        return new Triple<>(start.copy().append(comp), hover, blacklist);
    }

    public static final DimensionRestriction OVERWORLD = new DimensionRestriction(List.of(Level.OVERWORLD.location()), List.of());
    public static final DimensionRestriction NETHER = new DimensionRestriction(List.of(Level.NETHER.location()), List.of());
    public static final DimensionRestriction END = new DimensionRestriction(List.of(Level.END.location()), List.of());
}
