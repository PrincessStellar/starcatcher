package com.wdiscute.starcatcher.registry.items.modifieritem;

import com.wdiscute.starcatcher.io.SCDataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class TackleSkinItem extends Item
{
    public TackleSkinItem(ResourceLocation tackleSkin)
    {
        this(1, tackleSkin);
    }

    public TackleSkinItem(int maxStackSize, ResourceLocation rl)
    {
        super(new Item.Properties()
                .component(SCDataComponents.TACKLE_SKIN, rl)
                .stacksTo(maxStackSize)
        );
    }
}
