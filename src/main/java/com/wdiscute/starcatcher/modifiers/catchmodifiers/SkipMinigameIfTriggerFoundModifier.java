package com.wdiscute.starcatcher.modifiers.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class SkipMinigameIfTriggerFoundModifier extends AbstractCatchModifier
{
    public static final MapCodec<SkipMinigameIfTriggerFoundModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, SkipMinigameIfTriggerFoundModifier::new));

    public SkipMinigameIfTriggerFoundModifier(String translationOverride)
    {
        super(translationOverride);
    }

    @Override
    public List<ItemStack> addToFishedItems(FishProperties fp, int time, boolean perfectCatch, int hits, boolean completedTreasure, Player player)
    {
        if (player.level().getRandom().nextFloat() < 0.1f && instance.modifiers.stream().anyMatch(o -> o instanceof SkipsMinigame))
            return List.of(instance.treasure);
        return super.addToFishedItems(fp, time, perfectCatch, hits, completedTreasure, player);
    }

    @Override
    public boolean forceSkipMinigame(Boolean enableMinigameConfig)
    {
        return instance.modifiers.stream().anyMatch(o -> o instanceof SkipsMinigame);
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("skip_minigame_if_trigger_found");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }

    public interface SkipsMinigame
    {
    }
}
