package com.wdiscute.starcatcher.registry.blocks.tacklebox;

import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TackleBoxAttachmentSlot extends Slot
{
    TackleBoxMenu tackleMenu;
    TagKey<Item> mayPlaceTag;

    public TackleBoxAttachmentSlot(TackleBoxMenu tackleMenu, TagKey<Item> mayPlaceTag, Container container, int slot, int x, int y)
    {
        super(container, slot, x, y);
        this.tackleMenu = tackleMenu;
        this.mayPlaceTag = mayPlaceTag;
        tackleMenu.update();
    }


    @Override
    public boolean mayPlace(ItemStack stack)
    {
        return stack.is(mayPlaceTag) && !tackleMenu.getRod().isEmpty() && super.mayPlace(stack);
    }

    @Override
    public void onTake(Player player, ItemStack stack)
    {
        if(tackleMenu != null)
        {
            tackleMenu.update();
        }

        super.onTake(player, stack);
    }

    @Override
    public void setByPlayer(ItemStack stack)
    {
        super.setByPlayer(stack);
        if(tackleMenu != null)
        {
            tackleMenu.update();
        }
    }
}
