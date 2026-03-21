package com.wdiscute.starcatcher.registry.items.modifieritem;

import com.wdiscute.starcatcher.io.SCDataComponents;
import com.wdiscute.starcatcher.registry.custom.minigamemodifiers.AbstractMinigameModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MinigameModifierItem extends Item
{
    @SafeVarargs
    public MinigameModifierItem(DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>>... modifiers)
    {
        this(1, modifiers);
    }

    @SafeVarargs
    public MinigameModifierItem(int maxStackSize, DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>>... modifiers)
    {
        super(new Item.Properties()
                .component(SCDataComponents.MINIGAME_MODIFIERS, getAsList(modifiers))
                .stacksTo(maxStackSize)
        );
    }

    @SafeVarargs
    static List<ResourceLocation> getAsList(DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>>... modifiers)
    {
        List<ResourceLocation> list = new ArrayList<>();
        for (DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> p : modifiers)
        {
            list.add(p.getId());
        }
        return list;
    }

}
