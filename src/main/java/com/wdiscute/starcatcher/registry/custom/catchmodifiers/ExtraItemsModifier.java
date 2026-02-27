package com.wdiscute.starcatcher.registry.custom.catchmodifiers;

import com.wdiscute.starcatcher.U;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;

import java.util.ArrayList;
import java.util.List;

public class ExtraItemsModifier extends AbstractCatchModifier
{
    final int count;
    public ExtraItemsModifier(int count)
    {
        this.count = count;
    }

    @Override
    public List<ItemStack> addToFishedItems(int time, boolean perfectCatch, int hits, boolean completedTreasure)
    {
        if(!instance.fpToFish.hasGuideEntry()) return List.of();
        if(instance.fpToFish.catchInfo().alwaysSpawnEntity() ||
                ModList.get().isLoaded("fishingreal") ||
                instance.modifiers.stream().anyMatch(AbstractCatchModifier::forceSpawnEntity)) return List.of();

        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < count; i++)
        {
            //pick size, weight and golden
            float percentile = U.r.nextFloat(100);
            int size = U.getRandomSize(instance.fpToFish, percentile);
            int weight = U.getRandomWeight(instance.fpToFish, percentile);
            ItemStack stack = U.makeItemStack(ItemStack.EMPTY, instance.fpToFish, size, weight, percentile, false);
            items.add(stack);
        }
        return items;
    }
}
