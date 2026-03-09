package com.wdiscute.starcatcher.registry.blocks.sellingbin;

import com.wdiscute.starcatcher.registry.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.nikdo53.tinymultiblocklib.blockentities.AbstractMultiBlockEntity;

import javax.annotation.Nullable;

public class SellingBinBlockEntity extends AbstractMultiBlockEntity implements Container, MenuProvider
{

    private NonNullList<ItemStack> itemStacks;
    public int storedProgress;

    public SellingBinBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(ModBlockEntities.SELLING_BIN.get(), pos, blockState);
        this.itemStacks = NonNullList.withSize(2, ItemStack.EMPTY);
    }

    @Nullable
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player)
    {
        if (!player.isSpectator())
            return new SellingBinMenu(containerId, playerInventory, this, this);
        else
            return null;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.saveAdditional(tag, registries);

        if (!isCenter()) return;
        ContainerHelper.saveAllItems(tag, this.itemStacks, false, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.loadAdditional(tag, registries);
        if (!isCenter()) return;
        //from ShulkerBoxBlockEntity
        this.loadFromTag(tag, registries);
    }

    public void loadFromTag(CompoundTag tag, HolderLookup.Provider levelRegistry)
    {
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        //from ShulkerBoxBlockEntity
        if (tag.contains("Items", 9))
        {
            ContainerHelper.loadAllItems(tag, this.itemStacks, levelRegistry);
        }

    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries)
    {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    //container methods copied from ShulkerBoxBlockEntity and it's extends
    protected NonNullList<ItemStack> getItems()
    {
        return this.itemStacks;
    }

    @Override
    public int getContainerSize()
    {
        return 2;
    }

    @Override
    public boolean isEmpty()
    {
        for (ItemStack itemstack : this.getItems())
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getItem(int slot)
    {
        return this.getItems().get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount)
    {
        ItemStack itemstack = ContainerHelper.removeItem(this.getItems(), slot, amount);
        if (!itemstack.isEmpty())
        {
            this.setChanged();
        }

        return itemstack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot)
    {
        return ContainerHelper.takeItem(this.getItems(), slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack)
    {
        this.getItems().set(slot, stack);
        stack.limitSize(this.getMaxStackSize(stack));
        this.setChanged();
    }

    @Override
    public boolean stillValid(Player player)
    {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent()
    {
        this.getItems().clear();
    }

    @Override
    public Component getDisplayName()
    {
        return Component.empty();
    }
}
