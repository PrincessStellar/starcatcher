package com.wdiscute.starcatcher.registry.catchmodifiers;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class SkipMinigameIfVanillaLoot extends AbstractCatchModifier
{

    @Override
    public List<ItemStack> addToFishedItems(int time, boolean perfectCatch, int hits, boolean completedTreasure, Player player)
    {
        if(player.level().getRandom().nextFloat() < 0.1f)
            return List.of(instance.treasure);
        return super.addToFishedItems(time, perfectCatch, hits, completedTreasure, player);
    }

    @Override
    public boolean forceSkipMinigame(Boolean enableMinigameConfig)
    {
        return instance.modifiers.stream().anyMatch(o -> o instanceof VanillaLootModifier);
    }
}
