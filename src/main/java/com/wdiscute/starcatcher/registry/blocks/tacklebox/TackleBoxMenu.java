package com.wdiscute.starcatcher.registry.blocks.tacklebox;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.registry.SCMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class TackleBoxMenu extends AbstractContainerMenu
{
    private final Container container;
    public static final int ROD_SLOT = 0;
    public static final int BOBBER_SLOT = 1;
    public static final int BAIT_SLOT = 2;
    public static final int HOOK_SLOT = 3;

    public TackleBoxMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData)
    {
        this(containerId, playerInventory, new SimpleContainer(27));
    }

    public TackleBoxMenu(int containerId, Inventory playerInventory, Container container)
    {
        super(SCMenuTypes.TACKLE_BOX.get(), containerId);
        checkContainerSize(container, 27);
        this.container = container;
        container.startOpen(playerInventory.player);

        this.addSlot(new TackleBoxRodSlot(this, container, ROD_SLOT, 134, 37));

        this.addSlot(new TackleBoxAttachmentSlot(this, StarcatcherTags.BOBBERS, container, BOBBER_SLOT, 158, 11, Starcatcher.rl("item/background/bobber_white")));
        this.addSlot(new TackleBoxAttachmentSlot(this, StarcatcherTags.BAITS, container, BAIT_SLOT, 158, 31, Starcatcher.rl("item/background/bait_white")));
        this.addSlot(new TackleBoxAttachmentSlot(this, StarcatcherTags.HOOKS, container, HOOK_SLOT, 158, 51, Starcatcher.rl("item/background/hook_white")));


        for (int k = 0; k < 2; ++k)
            for (int l = 0; l < 7; ++l)
                this.addSlot(new TackleBoxStorageSlot(container, 4 + l + k * 7, l * 18 + 4, 37 + k * 18));


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

    public void update()
    {
        ModDataComponents.set(container.getItem(ROD_SLOT), ModDataComponents.BOBBER, new SingleStackContainer(container.getItem(BOBBER_SLOT).copy()));
        ModDataComponents.set(container.getItem(ROD_SLOT), ModDataComponents.BAIT, new SingleStackContainer(container.getItem(BAIT_SLOT).copy()));
        ModDataComponents.set(container.getItem(ROD_SLOT), ModDataComponents.HOOK, new SingleStackContainer(container.getItem(HOOK_SLOT).copy()));

        container.setItem(BOBBER_SLOT, ModDataComponents.getOrDefault(container.getItem(ROD_SLOT), ModDataComponents.BOBBER, SingleStackContainer.empty()).stack());
        container.setItem(BAIT_SLOT, ModDataComponents.getOrDefault(container.getItem(ROD_SLOT), ModDataComponents.BAIT, SingleStackContainer.empty()).stackDoNotUse());
        container.setItem(HOOK_SLOT, ModDataComponents.getOrDefault(container.getItem(ROD_SLOT), ModDataComponents.HOOK, SingleStackContainer.empty()).stack());
    }

    public ItemStack getRod()
    {
        return container.getItem(ROD_SLOT);
    }

    public void onPlaceRod(ItemStack newStack)
    {
        container.setItem(BOBBER_SLOT, ModDataComponents.getOrDefault(newStack, ModDataComponents.BOBBER, SingleStackContainer.empty()).stack());
        container.setItem(BAIT_SLOT, ModDataComponents.getOrDefault(newStack, ModDataComponents.BAIT, SingleStackContainer.empty()).stack());
        container.setItem(HOOK_SLOT, ModDataComponents.getOrDefault(newStack, ModDataComponents.HOOK, SingleStackContainer.empty()).stack());
    }
}
