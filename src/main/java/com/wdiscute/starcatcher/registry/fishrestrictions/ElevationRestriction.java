package com.wdiscute.starcatcher.registry.fishrestrictions;

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

public class ElevationRestriction extends AbstractFishRestriction
{
    private final int minY;
    private final int maxY;
    private final String translationOverride;

    public static final MapCodec<ElevationRestriction> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.fieldOf("min_y").forGetter(ElevationRestriction::getMinY),
                    Codec.INT.fieldOf("max_y").forGetter(ElevationRestriction::getMaxY),
                    Codec.STRING.fieldOf("translation_override").forGetter(ElevationRestriction::getTranslationOverride)
            ).apply(instance, ElevationRestriction::new));

    public ElevationRestriction()
    {
        this.minY = Integer.MIN_VALUE;
        this.maxY = Integer.MAX_VALUE;
        this.translationOverride = "";
    }

    public ElevationRestriction(int minY, int maxY, String translationOverride)
    {
        this.minY = minY;
        this.maxY = maxY;
        this.translationOverride = translationOverride;
    }

    public int getMinY()
    {
        return minY;
    }

    public int getMaxY()
    {
        return maxY;
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
        return SCFishRestrictions.ELEVATION_RESTRICTION;
    }

    @Override
    public int getFishChance(int currentChance, Level level, FishProperties fp, @NotNull Entity entity, ItemStack rod, Context context)
    {
        if (entity.getY() > minY && entity.getY() < getMaxY())
            return 0;
        else
            return -9999;
    }

    @Override
    public Triple<Component, List<Component>, List<Component>> getPageDescription(Level level, FishProperties fp, @NotNull Player player, Context context)
    {
        MutableComponent comp = Component.empty();
        List<Component> hover = new ArrayList<>();
        List<Component> blacklist = fp.baseChance() == 0 ? List.of(Component.translatable("gui.guide.bait_required")) : List.of();

        if (translationOverride.isEmpty())
        {
            if (minY != Integer.MIN_VALUE && maxY != Integer.MAX_VALUE)
                comp = Component.literal("> " + minY + ", < " + maxY);

            if (minY != Integer.MIN_VALUE && maxY == Integer.MAX_VALUE)
                comp = Component.translatable("gui.guide.above", minY);

            if (minY == Integer.MIN_VALUE && maxY != Integer.MAX_VALUE)
                comp = Component.translatable("gui.guide.below", maxY);
        }
        else
            comp = Component.translatable(translationOverride);


        return new Triple<>(Component.translatable("gui.guide.elevation").copy().append(comp), hover, blacklist);
    }

    public static final ElevationRestriction ABOVE_FIFTY = new ElevationRestriction(50, Integer.MAX_VALUE, "");
    public static final ElevationRestriction ABOVE_HUNDRED = new ElevationRestriction(100, Integer.MAX_VALUE, "");
    public static final ElevationRestriction BELOW_FIFTY = new ElevationRestriction(Integer.MIN_VALUE, 50, "");
    public static final ElevationRestriction FIFTY_TO_HUNDRED = new ElevationRestriction(50, 100, "");
    public static final ElevationRestriction BELOW_ZERO = new ElevationRestriction(Integer.MIN_VALUE, 0, "");
    public static final ElevationRestriction BELOW_MINUS_SIXTY_FOUR = new ElevationRestriction(Integer.MIN_VALUE, -64, "");
    public static final ElevationRestriction ZERO_TO_FIFTY = new ElevationRestriction(0, 50, "");
}
