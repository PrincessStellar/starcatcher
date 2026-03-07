package com.wdiscute.starcatcher.registry.blocks.tacklebox;

import com.wdiscute.starcatcher.Config;
import com.wdiscute.starcatcher.StarcatcherTags;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class TackleBoxStorageSlot extends Slot
{
    public TackleBoxStorageSlot(Container container, int slot, int x, int y)
    {
        super(container, slot, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack)
    {
        return !Config.RESTRICT_TACKLE_BOX_TO_TAG.get() || stack.is(StarcatcherTags.PLACEABLE_IN_TACKLE_BOX);
    }
}
