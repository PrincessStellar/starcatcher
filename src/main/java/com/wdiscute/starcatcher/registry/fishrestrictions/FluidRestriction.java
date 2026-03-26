package com.wdiscute.starcatcher.registry.fishrestrictions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.antlr.v4.runtime.misc.Triple;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FluidRestriction extends AbstractFishRestriction
{
    private final Map<ResourceLocation, Integer> fluids;
    private final String translationOverride;

    public static final MapCodec<FluidRestriction> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    ExtraCodecs.strictUnboundedMap(ResourceLocation.CODEC, Codec.INT).fieldOf("fluids").forGetter(FluidRestriction::getFluids),
                    Codec.STRING.fieldOf("translation_override").forGetter(FluidRestriction::getTranslationOverride)
            ).apply(instance, FluidRestriction::new));

    public FluidRestriction()
    {
        this.fluids = Map.of();
        this.translationOverride = "";
    }

    public FluidRestriction(Map<ResourceLocation, Integer> baits, String translationOverride)
    {
        this.fluids = baits;
        this.translationOverride = translationOverride;
    }

    public Map<ResourceLocation, Integer> getFluids()
    {
        return fluids;
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
        return SCFishRestrictions.FLUID;
    }

    @Override
    public int getFishChance(int currentChance, Level level, FishProperties fp, @NotNull Entity entity, ItemStack rod, Context context)
    {
        List<ResourceLocation> fluidsList = fluids.keySet().stream().toList();

        if (context.equals(Context.GUIDE_FISHES_IN_AREA))
        {
            return 0;
        }
        else
        {
            BlockPos bp = entity.blockPosition();
            boolean fluid = fluidsList.contains(BuiltInRegistries.FLUID.getKey(getSource(level.getFluidState(bp).getType())));
            boolean fluidAbove = fluidsList.contains(BuiltInRegistries.FLUID.getKey(getSource(level.getFluidState(bp.above()).getType())));
            boolean fluidBelow = fluidsList.contains(BuiltInRegistries.FLUID.getKey(getSource(level.getFluidState(bp.below()).getType())));

            if (!fluid && !fluidAbove && !fluidBelow) return -9999;
            else return 0;
        }
    }

    @Override
    public Triple<Component, List<Component>, List<Component>> getPageDescription(Level level, FishProperties fp, @Nullable Player player, Context context)
    {
        Component comp;
        List<Component> hover = new ArrayList<>();
        List<Component> blacklist = fp.baseChance() == 0 ? List.of(Component.translatable("gui.guide.bait_required")) : List.of();

        //bait name / [hover]
        if (fluids.size() == 1)
            comp = Component.translatable("block." + fluids.entrySet().stream().findFirst().get().getKey().toLanguageKey());
        else
            comp = Component.translatable("gui.guide.hover");

        //hover - Chance added by bait:
        if (fluids.entrySet().stream().anyMatch((entry) -> entry.getValue() > 0))
        {
            hover.add(Component.translatable("gui.guide.fluif_chance_added").withStyle(Style.EMPTY.withBold(true)));
            hover.add(Component.empty());

            fluids.forEach((key, value) -> hover.add(Component.translatable("block." + key.toLanguageKey())));
        }

        if (!translationOverride.isEmpty()) comp = Component.translatable(translationOverride);

        return new Triple<>(Component.translatable("gui.guide.fluid").copy().append(comp), hover, blacklist);
    }

    private static Fluid getSource(Fluid fluid1)
    {
        if (fluid1 instanceof FlowingFluid fluid)
        {
            return fluid.getSource();
        }

        return fluid1;
    }

    public static final FluidRestriction LAVA = new FluidRestriction(Map.of(ResourceLocation.withDefaultNamespace("lava"), 0), "");
    public static final FluidRestriction WATER = new FluidRestriction(Map.of(ResourceLocation.withDefaultNamespace("water"), 0), "");
    public static final FluidRestriction VOID = new FluidRestriction(Map.of(ResourceLocation.withDefaultNamespace("empty"), 0), "");
    public static final FluidRestriction ACID = new FluidRestriction(Map.of(U.rl("alexscaves", "acid"), 0), "");
    public static final FluidRestriction PURPLE_SODA = new FluidRestriction(Map.of(U.rl("alexscaves", "purple_soda"), 0), "");
}
