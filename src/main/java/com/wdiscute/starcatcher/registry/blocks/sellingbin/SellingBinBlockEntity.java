package com.wdiscute.starcatcher.registry.blocks.sellingbin;

import com.wdiscute.starcatcher.io.NBTCodecHelper;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.registry.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.nikdo53.tinymultiblocklib.blockentities.AbstractMultiBlockEntity;
import org.jetbrains.annotations.Nullable;

public class SellingBinBlockEntity extends AbstractMultiBlockEntity implements MenuProvider
{
    public final ItemStackHandler storage = new ItemStackHandler(1)
    {
        @Override
        protected int getStackLimit(int slot, ItemStack stack)
        {
            return 64;
        }
    };

    public SellingBinBlockEntity(BlockPos pos, BlockState blockState)
    {
        super(ModBlockEntities.SELLING_BIN.get(), pos, blockState);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player)
    {
        return new SellingBinMenu(i, inventory, this);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.saveAdditional(tag, registries);

        if (!isCenter()) return;

        NBTCodecHelper.encode(SingleStackContainer.CODEC, new SingleStackContainer(storage.getStackInSlot(0)), tag, "storage");
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries)
    {
        super.loadAdditional(tag, registries);

        if (!isCenter()) return;

        if(tag.contains("storage"))
        {
            com.wdiscute.starcatcher.io.SingleStackContainer singleStackContainer = NBTCodecHelper.decode(SingleStackContainer.CODEC, tag, "storage");
            storage.setStackInSlot(0, singleStackContainer.stack().copy());
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

    @Override
    public Component getDisplayName()
    {
        return Component.empty();
    }
}
