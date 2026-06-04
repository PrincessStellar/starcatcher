package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.io.FishCaughtCounter;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.text.DecimalFormat;
import java.util.List;

public class ExtraGoldenRiskModifier extends AbstractCatchModifier
{
    final float risk;
    final boolean onlyForPerfectCatch;

    public static final MapCodec<ExtraGoldenRiskModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.FLOAT.fieldOf("chance").forGetter(o -> o.risk),
                    Codec.BOOL.fieldOf("only_for_perfect_catch").forGetter(o -> o.onlyForPerfectCatch),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, ExtraGoldenRiskModifier::new));

    public ExtraGoldenRiskModifier(float chance, boolean onlyForPerfectCatch, String translationOverride)
    {
        super(translationOverride);
        this.risk = chance;
        this.onlyForPerfectCatch = onlyForPerfectCatch;
    }

    @Override
    public DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolder()
    {
        return SCCatchModifiers.EXTRA_GOLDEN_CHANCE;
    }

    @Override
    public MapCodec<? extends AbstractCatchModifier> codec()
    {
        return CODEC;
    }


    @Override
    public List<Component> getNonOverriddenDescription(boolean shift)
    {
        String chanceS = new DecimalFormat("#.##").format(risk * 100);

        if (onlyForPerfectCatch)
        {
            if (shift)
                return List.of(Component.translatableEscape("tooltip.modifier.starcatcher.extra_golden_chance.perfect.shift", chanceS));
            else
                return List.of(Component.translatableEscape("tooltip.modifier.starcatcher.extra_golden_chance.perfect", chanceS));
        }
        else
        {
            if (shift)
                return List.of(Component.translatableEscape("tooltip.modifier.starcatcher.extra_golden_chance.base.shift", chanceS));
            else
                return List.of(Component.translatableEscape("tooltip.modifier.starcatcher.extra_golden_chance.base", chanceS));
        }
    }

    @Override
    public boolean shouldBeGolden(int time, boolean treasure, boolean perfect, int hits)
    {
        //if dont need perfect catch, do normal math
        if (!onlyForPerfectCatch)
            return FishCaughtCounter.canCatchGolden(instance.fpToFish, (ServerPlayer) instance.player)
                    && instance.level().getRandom().nextFloat() < risk;

        //otherwise only run if it's perfect
        if (perfect)
            return FishCaughtCounter.canCatchGolden(instance.fpToFish, (ServerPlayer) instance.player)
                    && instance.level().getRandom().nextFloat() < risk;

        return false;
    }
}
