package com.wdiscute.starcatcher.registry.fishrestrictions;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.antlr.v4.runtime.misc.Triple;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DaytimeRestriction extends AbstractFishRestriction
{
    private final List<Pair<Integer, Integer>> ranges;
    private final String translationOverride;

    public static final MapCodec<DaytimeRestriction> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.pair(Codec.INT, Codec.INT).listOf().fieldOf("ranges").forGetter(DaytimeRestriction::getRanges),
                    Codec.STRING.fieldOf("translation_override").forGetter(DaytimeRestriction::getTranslationOverride)
            ).apply(instance, DaytimeRestriction::new));

    public DaytimeRestriction()
    {
        this.ranges = List.of();
        this.translationOverride = "";
    }

    public DaytimeRestriction(List<Pair<Integer, Integer>> ranges, String translationOverride)
    {
        this.ranges = ranges;
        this.translationOverride = translationOverride;
    }

    public List<Pair<Integer, Integer>> getRanges()
    {
        return ranges;
    }

    public String getTranslationOverride()
    {
        return translationOverride;
    }

    @Override
    public MapCodec<? extends AbstractFishRestriction> codec()
    {
        return CODEC;
    }

    @Override
    public DeferredHolder<AbstractFishRestriction, AbstractFishRestriction> getRegistryHolder()
    {
        return SCFishRestrictions.DAYTIME_RESTRICTION;
    }

    @Override
    public int getFishChance(int currentChance, Level level, FishProperties fp, @NotNull Entity entity, ItemStack rod, Context context)
    {
        float daytime = level.dimensionType().timeOfDay(level.dayTime());

        if (ranges.stream().anyMatch(range -> daytime > range.getFirst() && daytime < range.getSecond()))
            return 0;

        return -9999;
    }

    @Override
    public Triple<Component, List<Component>, List<Component>> getPageDescription(Level level, FishProperties fp, @NotNull Player player, Context context)
    {
        MutableComponent comp = translationOverride.isEmpty() ? Component.translatable("gui.guide.hover") : Component.translatable(translationOverride);
        List<Component> hover = new ArrayList<>();
        List<Component> blacklist = List.of();


        for (Pair<Integer, Integer> range : ranges)
        {
            hover.add(Component.literal(" > " + range.getFirst() + " & < " + range.getSecond()));
        }

        return new Triple<>(Component.translatable("gui.guide.daytime").copy().append(comp), hover, blacklist);
    }

    public static final DaytimeRestriction MIDNIGHT = new DaytimeRestriction(List.of(), "");
    public static final DaytimeRestriction NOON = new DaytimeRestriction(List.of(), "");
    public static final DaytimeRestriction DAY = new DaytimeRestriction(List.of(), "");
    public static final DaytimeRestriction NIGHT = new DaytimeRestriction(List.of(), "");

}
