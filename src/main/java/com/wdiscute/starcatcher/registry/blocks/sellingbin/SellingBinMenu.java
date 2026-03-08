package com.wdiscute.starcatcher.registry.blocks.sellingbin;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.registry.ModMenuTypes;
import com.wdiscute.starcatcher.registry.blocks.tacklebox.TackleBoxAttachmentSlot;
import com.wdiscute.starcatcher.registry.blocks.tacklebox.TackleBoxRodSlot;
import com.wdiscute.starcatcher.registry.blocks.tacklebox.TackleBoxStorageSlot;
import com.wdiscute.starcatcher.registry.custom.sellingbinprocessor.ModSellingBinProcessors;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class SellingBinMenu extends AbstractContainerMenu
{
    private final Container container;
    public static final int ITEM_SLOT = 0;
    public static final int RESULT_SLOT = 1;

    public SellingBinMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData)
    {
        this(containerId, playerInventory, new SimpleContainer(2));
    }

    @Override
    public boolean clickMenuButton(Player player, int id)
    {

        if(id == 67)
        {
            int value = ModSellingBinProcessors.calculateFromStack(container.getItem(ITEM_SLOT));

            //System.out.println(value);
        }

        return super.clickMenuButton(player, id);
    }

    public SellingBinMenu(int containerId, Inventory playerInventory, Container container)
    {
        super(ModMenuTypes.SELLING_BIN_MENU.get(), containerId);
        checkContainerSize(container, 2);
        this.container = container;
        container.startOpen(playerInventory.player);

        this.addSlot(new SellingBinItemSlot(container, ITEM_SLOT, 42, 39));

        this.addSlot(new SellingBinResultSlot(container, RESULT_SLOT, 80, 58));


        for (int i1 = 0; i1 < 3; ++i1)
            for (int k1 = 0; k1 < 9; ++k1)
                this.addSlot(new Slot(playerInventory, k1 + i1 * 9 + 9, 8 + k1 * 18, 84 + i1 * 18));

        for (int j1 = 0; j1 < 9; ++j1)
            this.addSlot(new Slot(playerInventory, j1, 8 + j1 * 18, 142));

    }

    public boolean stillValid(Player player)
    {
        return this.container.stillValid(player);
    }

    public ItemStack quickMoveStack(Player player, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem())
        {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < this.container.getContainerSize())
            {
                if (!this.moveItemStackTo(itemstack1, this.container.getContainerSize(), this.slots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.moveItemStackTo(itemstack1, 0, this.container.getContainerSize(), false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
                slot.setByPlayer(ItemStack.EMPTY);
            else
                slot.setChanged();
        }

        return itemstack;
    }

    public void removed(Player player)
    {
        super.removed(player);
        this.container.stopOpen(player);
    }
}
