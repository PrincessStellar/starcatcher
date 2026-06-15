package com.wdiscute.starcatcher.registry.fishrestrictions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.SCColors;
import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.WorldRestrictions;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.LevelStem;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DimensionRestriction extends AbstractFishRestriction
{
    private final List<ResourceLocation> dimensions;
    private final List<ResourceLocation> dimensionsTags;
    private final String hover;

    public static final MapCodec<DimensionRestriction> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    ResourceLocation.CODEC.listOf().fieldOf("dimensions").forGetter(o -> o.dimensions),
                    ResourceLocation.CODEC.listOf().fieldOf("dimensions_tags").forGetter(o -> o.dimensionsTags),
                    Codec.STRING.optionalFieldOf("hover_translation", "").forGetter(o -> o.hover),
                    Codec.STRING.optionalFieldOf("translation_override", "").forGetter(o -> o.translationOverride)
            ).apply(instance, DimensionRestriction::new));

    public DimensionRestriction()
    {
        super("");
        this.dimensions = List.of();
        this.dimensionsTags = List.of();
        this.hover = "";
    }

    public DimensionRestriction(ResourceLocation dimension, String translationOverride)
    {
        super(translationOverride);
        this.dimensions = List.of(dimension);
        this.dimensionsTags = List.of();
        this.hover = "";
    }

    public DimensionRestriction(List<ResourceLocation> dimensions, List<ResourceLocation> dimensionsTags, String translationOverride)
    {
        super(translationOverride);
        this.dimensions = dimensions;
        this.dimensionsTags = dimensionsTags;
        this.hover = "";
    }

    public DimensionRestriction(List<ResourceLocation> dimensions, List<ResourceLocation> dimensionsTags, String hover, String translationOverride)
    {
        super(translationOverride);
        this.dimensions = dimensions;
        this.dimensionsTags = dimensionsTags;
        this.hover = hover;
    }

    @Override
    public int getSortPriority()
    {
        return -100;
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
        if(level.isClientSide) return 0;
        ResourceLocation dimensionRL = level.dimension().location();
        Holder<Level> dimensionHolder = level.registryAccess()
                .lookupOrThrow(Registries.DIMENSION)
                .getOrThrow(ResourceKey.create(Registries.DIMENSION, dimensionRL));

        // if dimensions or dimensionTags then check if dimension is in any of them
        if (!dimensions.isEmpty() || !dimensionsTags.isEmpty())
        {
            boolean safe = false;

            if (dimensions.contains(dimensionRL)) safe = true;

            if (dimensionsTags.stream().anyMatch(rl -> dimensionHolder.is(TagKey.create(Registries.DIMENSION, rl))))
                safe = true;

            if (!safe) return -9999;
        }

        return 0;
    }

    @Override
    public List<Component> getIndexHover(Level level, FishProperties fp, @NotNull Player player, Context context)
    {
        if (getFishChance(0, level, fp, player, ItemStack.EMPTY, Context.GUIDE_FISHES_HOVER) >= 0)
            return List.of(Component.translatable("gui.guide.hover.dimension.correct").withStyle(Style.EMPTY.withColor(SCColors.GUIDE_GREEN)));
        else
            return List.of(Component.translatable("gui.guide.hover.dimension.incorrect").withStyle(Style.EMPTY.withColor(SCColors.GUIDE_RED)));
    }

    @Override
    public MutableComponent getDescriptionPrefix()
    {
        return Component.translatable("gui.guide.dimension");
    }

    @Override
    public MutableComponent getNonOverriddenDescription(Level level, FishProperties fp, @NotNull Player player, Context context)
    {
        List<ResourceLocation> dimensionList = WorldRestrictions.getDimensionsAsListFromTags(dimensions, dimensionsTags, level);

        //Dimensions: [No Dimensions]
        if (dimensionList.isEmpty())
            return Component.translatable("gui.guide.dimension.empty");

        //single dimension name / dimension tag name / [hover]
        if (dimensionList.size() == 1)
            return Component.translatable("dimension." + dimensionList.getFirst().toLanguageKey());
        else if (dimensionsTags.size() == 1)
            return Component.translatable("tag." + dimensionsTags.getFirst().toLanguageKey());
        else
            return Component.translatable("gui.guide.hover");
    }

    @Override
    public List<Component> getHover(Level level, FishProperties fp, @NotNull Player player, Context context)
    {
        List<Component> hover = new ArrayList<>();
        List<ResourceLocation> dimensionList = WorldRestrictions.getDimensionsAsListFromTags(dimensions, dimensionsTags, level);

        if (!this.hover.isEmpty()) return List.of(Component.translatable(this.hover));

        if (!dimensionList.isEmpty())
        {
            if (!dimensionsTags.isEmpty())
            {
                hover.add(Component.translatable("gui.guide.dimension_tags").withStyle(Style.EMPTY.withBold(true)));

                for (ResourceLocation rl : dimensionsTags)
                    hover.add(Component.translatable("tag." + rl.toLanguageKey()));
                hover.add(Component.empty());
            }

            hover.add(Component.translatable("gui.guide.dimension").withStyle(Style.EMPTY.withBold(true)));
            if (dimensionList.isEmpty())
                hover.add(Component.translatable("gui.guide.dimension.empty"));

            for (ResourceLocation rl : dimensionList)
                hover.add(Component.translatable("dimension." + rl.toLanguageKey()));
        }

        return hover;
    }

    public static final DimensionRestriction OVERWORLD = new DimensionRestriction(List.of(), List.of(SCTags.IS_OVERWORLD), "dimension.minecraft.overworld");
    public static final DimensionRestriction NETHER = new DimensionRestriction(List.of(), List.of(SCTags.IS_NETHER), "dimension.minecraft.the_nether");
    public static final DimensionRestriction END = new DimensionRestriction(List.of(), List.of(SCTags.IS_END), "dimension.minecraft.the_end");
}
