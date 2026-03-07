package com.wdiscute.starcatcher.registry.blocks.tacklebox;

import com.wdiscute.starcatcher.StarcatcherTags;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class TackleBoxRodSlot extends Slot
{
    TackleBoxMenu tackleMenu;

    public TackleBoxRodSlot(TackleBoxMenu tackleMenu, Container container, int slot, int x, int y)
    {
        super(container, slot, x, y);
        this.tackleMenu = tackleMenu;
        tackleMenu.update();
    }

    @Override
    public boolean mayPlace(ItemStack stack)
    {
        return stack.is(StarcatcherTags.RODS);
    }

    @Override
    public void setByPlayer(ItemStack newStack, ItemStack oldStack)
    {
        super.setByPlayer(newStack, oldStack);
        tackleMenu.onPlaceRod(newStack);
    }

    @Override
    public void onTake(Player player, ItemStack stack)
    {
        super.onTake(player, stack);

        if(player.containerMenu instanceof TackleBoxMenu tbm)
        {
            tbm.onTakeRod(player, stack);
        }
    }
}
