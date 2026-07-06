package com.wdiscute.starcatcher.registry.fishrestrictions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.SCColors;
import com.wdiscute.starcatcher.fish.FishProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ElevationBias extends AbstractFishRestriction
{
    private final int bestY;
    private final int range;
    private final int extraChance;

    public static final MapCodec<ElevationBias> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.fieldOf("best_y").forGetter(o -> o.bestY),
                    Codec.INT.fieldOf("range").forGetter(o -> o.range),
                    Codec.INT.fieldOf("extra_chance_at_best").forGetter(o -> o.extraChance),
                    Codec.STRING.optionalFieldOf("translation_override", "").forGetter(o -> o.translationOverride)
            ).apply(instance, ElevationBias::new));

    public ElevationBias()
    {
        super("");
        this.bestY = 90;
        this.range = 10;
        this.extraChance = 0;
    }

    public ElevationBias(int bestY, int range, int extraChance, String translationOverride)
    {
        super(translationOverride);
        this.bestY = bestY;
        this.range = range;
        this.extraChance = extraChance;
    }

    @Override
    public MapCodec<? extends AbstractFishRestriction> codec()
    {
        return CODEC;
    }

    @Override
    public DeferredHolder<AbstractFishRestriction, AbstractFishRestriction> getRegistryHolder()
    {
        return SCFishRestrictions.ELEVATION_BIAS;
    }

    @Override
    public int getFishChance(int currentChance, Level level, FishProperties fp, @NotNull Entity entity, ItemStack rod, Context context)
    {
        //returns the extra weight scaled linearly from 0 to extraChance, with extraChance at 100% at bestY
        int distance = Math.abs(entity.blockPosition().getY() - bestY);

        int scaledChance = (int) (extraChance * (1 - (float) distance / (float) range));

        return scaledChance > 0 ? scaledChance : -9999;
    }

    @Override
    public List<Component> getIndexHover(Level level, FishProperties fp, @NotNull Player player, Context context)
    {
        if (getFishChance(0, level, fp, player, ItemStack.EMPTY, Context.GUIDE_FISHES_HOVER) >= 0)
            return List.of(Component.translatable("gui.guide.hover.elevation.correct").withStyle(Style.EMPTY.withColor(SCColors.GUIDE_GREEN)));
        else
            return List.of(Component.translatable("gui.guide.hover.elevation.incorrect").withStyle(Style.EMPTY.withColor(SCColors.GUIDE_RED)));
    }

    @Override
    public MutableComponent getDescriptionPrefix()
    {
        return Component.translatable("gui.guide.elevation");
    }

    @Override
    public MutableComponent getNonOverriddenDescription(Level level, FishProperties fp, @NotNull Player player, Context context)
    {
        return Component.translatable("gui.guide.elevation_bias", bestY);
    }

    public static final ElevationBias MOUNTAIN = new ElevationBias(100, 20, 7, "");
}
