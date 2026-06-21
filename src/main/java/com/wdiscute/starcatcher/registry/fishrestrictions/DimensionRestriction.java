package com.wdiscute.starcatcher.registry.fishrestrictions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.SCColors;
import com.wdiscute.starcatcher.fish.FishProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DimensionRestriction extends AbstractFishRestriction
{
    private final List<ResourceLocation> dimensions;
    private final String hover;

    public static final MapCodec<DimensionRestriction> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    ResourceLocation.CODEC.listOf().fieldOf("dimensions").forGetter(o -> o.dimensions),
                    Codec.STRING.optionalFieldOf("hover_translation", "").forGetter(o -> o.hover),
                    Codec.STRING.optionalFieldOf("translation_override", "").forGetter(o -> o.translationOverride)
            ).apply(instance, DimensionRestriction::new));

    public DimensionRestriction()
    {
        super("");
        this.dimensions = List.of();
        this.hover = "";
    }

    public DimensionRestriction(ResourceLocation dimension, String translationOverride)
    {
        super(translationOverride);
        this.dimensions = List.of(dimension);
        this.hover = "";
    }

    public DimensionRestriction(List<ResourceLocation> dimensions, String translationOverride)
    {
        super(translationOverride);
        this.dimensions = dimensions;
        this.hover = "";
    }

    public DimensionRestriction(List<ResourceLocation> dimensions, String hover, String translationOverride)
    {
        super(translationOverride);
        this.dimensions = dimensions;
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
        if (dimensions.isEmpty()) return 0;
        ResourceLocation dimensionRL = level.dimension().location();

        //check if dimension is in list
        return dimensions.contains(dimensionRL) ? 0 : -9999;
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
        //Dimensions: [No Dimensions]
        if (dimensions.isEmpty())
            return Component.translatable("gui.guide.dimension.empty");

        //single dimension name / [hover]
        if (dimensions.size() == 1)
            return Component.translatable("dimension." + dimensions.getFirst().toLanguageKey());
        else
            return Component.translatable("gui.guide.hover");
    }

    @Override
    public List<Component> getHover(Level level, FishProperties fp, @NotNull Player player, Context context)
    {
        List<Component> hover = new ArrayList<>();

        if (!this.hover.isEmpty()) return List.of(Component.translatable(this.hover));

        if (!dimensions.isEmpty() && dimensions.size() > 1)
        {
            hover.add(Component.translatable("gui.guide.dimension").withStyle(Style.EMPTY.withBold(true)));
            for (ResourceLocation rl : dimensions)
                hover.add(Component.translatable("dimension." + rl.toLanguageKey()));
        }

        return hover;
    }

    public static final DimensionRestriction OVERWORLD = new DimensionRestriction(List.of(Level.OVERWORLD.location()), "");
    public static final DimensionRestriction NETHER = new DimensionRestriction(List.of(Level.NETHER.location()), "");
    public static final DimensionRestriction END = new DimensionRestriction(List.of(Level.END.location()), "");
}
