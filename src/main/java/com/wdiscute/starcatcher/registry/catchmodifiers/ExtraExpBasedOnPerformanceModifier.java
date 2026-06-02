package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ExtraExpBasedOnPerformanceModifier extends AbstractCatchModifier
{
    public static final MapCodec<ExtraExpBasedOnPerformanceModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, ExtraExpBasedOnPerformanceModifier::new));

    public ExtraExpBasedOnPerformanceModifier(String translationOverride)
    {
        super(translationOverride);
    }

    @Override
    public DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolder()
    {
        return SCCatchModifiers.EXTRA_EXP_BASED_ON_PERFORMANCE;
    }

    @Override
    public MapCodec<? extends AbstractCatchModifier> codec()
    {
        return CODEC;
    }

    @Override
    public void onSuccessfulMinigameCompletion(ServerPlayer player, int time, boolean completedTreasure, boolean perfectCatch, int hits)
    {
        super.onSuccessfulMinigameCompletion(player, time, completedTreasure, perfectCatch, hits);
        int hitsNonCheated = Math.min(hits, 20);
        player.giveExperiencePoints(instance.fpToFish.rarity().getXp() * (hitsNonCheated / 3) + 1);
    }
}

