package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

public class SkipMinigameModifier extends AbstractCatchModifier
{
    public static final MapCodec<SkipMinigameModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, SkipMinigameModifier::new));

    public SkipMinigameModifier(String translationOverride)
    {
        super(translationOverride);
    }

    @Override
    public DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolder()
    {
        return SCCatchModifiers.SKIP_MINIGAME;
    }

    @Override
    public MapCodec<? extends AbstractCatchModifier> codec()
    {
        return CODEC;
    }

    @Override
    public List<ItemStack> addToFishedItems(int time, boolean perfectCatch, int hits, boolean completedTreasure, Player player)
    {
        if (player.level().getRandom().nextFloat() < 0.1f)
            return List.of(instance.treasure);
        return super.addToFishedItems(time, perfectCatch, hits, completedTreasure, player);
    }

    @Override
    public boolean forceSkipMinigame(Boolean enableMinigameConfig)
    {
        return true;
    }
}
