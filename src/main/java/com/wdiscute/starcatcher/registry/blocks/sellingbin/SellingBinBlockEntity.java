package com.wdiscute.starcatcher.registry.blocks.sellingbin;

import com.wdiscute.starcatcher.Config;
import com.wdiscute.starcatcher.registry.blocks.ModBlockEntities;
import com.wdiscute.starcatcher.registry.custom.sellingbinprocessor.ModSellingBinProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.nikdo53.tinymultiblocklib.blockentities.AbstractMultiBlockEntity;

import javax.annotation.Nullable;

public class SellingBinBlockEntity extends AbstractMultiBlockEntity implements Container, MenuProvider
{

    private NonNullList<ItemStack> itemStacks;
    public int storedProgress;
    public boolean instaSell = false;

    public SellingBinBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(ModBlockEntities.SELLING_BIN.get(), pos, blockState);
        this.itemStacks = NonNullList.withSize(2, ItemStack.EMPTY);
    }



    public void sell(boolean all)
    {
        int value = ModSellingBinProcessors.calculateFromStack(getItem(SellingBinMenu.ITEM_SLOT));
        if (value <= 0) return;

        while (getItem(SellingBinMenu.ITEM_SLOT).getCount() > 0)
        {
            storedProgress += value;
            getItem(SellingBinMenu.ITEM_SLOT).shrink(1);
            update();
            updateToClient();
            if(!all) return;
        }
        updateToClient();
    }

    public void update()
    {
        ItemStack is = getItem(SellingBinMenu.RESULT_SLOT);
        while ((is.isEmpty() || is.getCount() < is.getMaxStackSize()) && storedProgress >= Config.SELLING_BIN_LOWEST_VALUE.get())
        {
            if (is.isEmpty())
                is = new ItemStack(Items.EMERALD);
            else
                is.grow(1);
            setItem(SellingBinMenu.RESULT_SLOT, is);
            storedProgress -= Config.SELLING_BIN_LOWEST_VALUE.getAsInt();
        }

        updateToClient();
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

        //insta sell
        tag.putBoolean("insta_sell", instaSell);

        //stored progress
        tag.putInt("stored_progress", storedProgress);

        //save items (from ShulkerBoxBlockEntity)
        ContainerHelper.saveAllItems(tag, this.itemStacks, false, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.loadAdditional(tag, registries);
        if (!isCenter()) return;

        //insta sell
        if (tag.contains("insta_sell")) instaSell = tag.getBoolean("insta_sell");

        //stored progress
        if (tag.contains("stored_progress")) storedProgress = tag.getInt("stored_progress");

        //retrieve items (from ShulkerBoxBlockEntity)
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (tag.contains("Items", 9))
        {
            ContainerHelper.loadAllItems(tag, this.itemStacks, registries);
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

    public void updateToClient()
    {
        setChanged();
        if (level instanceof ServerLevel serverLevel)
        {
            serverLevel.sendBlockUpdated(getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
        }
    }
}
