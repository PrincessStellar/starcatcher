package com.wdiscute.starcatcher.registry.blocks.sellingbin;

import com.wdiscute.starcatcher.registry.ModDataMaps;
import com.wdiscute.starcatcher.registry.blocks.ModBlockEntities;
import com.wdiscute.starcatcher.registry.blocks.TickableBlockEntity;
import com.wdiscute.starcatcher.registry.custom.sellingbinprocessor.ModSellingBinProcessors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.nikdo53.tinymultiblocklib.blockentities.AbstractMultiBlockEntity;
import oshi.util.tuples.Pair;

import javax.annotation.Nullable;
import java.util.List;

public class SellingBinBlockEntity extends AbstractMultiBlockEntity implements WorldlyContainer, MenuProvider, TickableBlockEntity
{

    private NonNullList<ItemStack> itemStacks;
    public int storedProgress;
    public boolean instaSell = false;
    public List<Pair<Item, Integer>> currencies;
    public List<Pair<Item, Integer>> currenciesReversed;

    public SellingBinBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(ModBlockEntities.SELLING_BIN.get(), pos, blockState);
        this.itemStacks = NonNullList.withSize(2, ItemStack.EMPTY);
        this.currencies = ModDataMaps.getCurrencies();
        this.currenciesReversed = currencies.reversed();
    }

    public void sell(boolean all)
    {
        int value = ModSellingBinProcessors.calculateValueFromSingleStack(getItem(SellingBinMenu.ITEM_SLOT));
        if (value <= 0) return;

        while (getItem(SellingBinMenu.ITEM_SLOT).getCount() > 0)
        {
            storedProgress += value;
            getItem(SellingBinMenu.ITEM_SLOT).shrink(1);
            update();
            updateToClient();
            if (!all) return;
        }
        updateToClient();
    }

    public int getProgressAvailable()
    {
        return storedProgress + ModDataMaps.getOrDefault(getItem(SellingBinMenu.RESULT_SLOT), ModDataMaps.SELLING_BIN_CURRENCIES, 0) * getItem(SellingBinMenu.RESULT_SLOT).getCount();
    }

    public void update()
    {
        ItemStack is = getItem(SellingBinMenu.RESULT_SLOT);

        int progressAvailable = getProgressAvailable();

        for (Pair<Item, Integer> c : currenciesReversed)
        {
            if (progressAvailable > c.getB() && (is.isEmpty() || is.getCount() < is.getMaxStackSize()))
            {
                if (!is.is(c.getA())) is = new ItemStack(c.getA());

                int count = Math.clamp(progressAvailable / c.getB(), 0, c.getA().getMaxStackSize(new ItemStack(c.getA())));

                is.setCount(count);

                setItem(SellingBinMenu.RESULT_SLOT, is);

                storedProgress = progressAvailable - c.getB() * count;

                break;
            }
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

    @Override
    public int[] getSlotsForFace(Direction direction)
    {
        if (direction == Direction.DOWN) return new int[]{1};
        return new int[]{0};
    }

    @Override
    public boolean canPlaceItemThroughFace(int i, ItemStack itemStack, @org.jetbrains.annotations.Nullable Direction direction)
    {
        int value = ModSellingBinProcessors.calculateValueFromSingleStack(itemStack);
        return value > 0 && direction != Direction.DOWN;
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack itemStack, Direction direction)
    {
        return direction == Direction.DOWN;
    }

    @Override
    public void tick()
    {
        update();
        if (instaSell) sell(true);
    }
}
