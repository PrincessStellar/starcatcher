package com.wdiscute.starcatcher.registry.blocks.sellingbin;

import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.registry.ModMenuTypes;
import com.wdiscute.starcatcher.registry.blocks.stand.StandBlockEntity;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

public class SellingBinMenu extends AbstractContainerMenu
{
    public final SellingBinBlockEntity ssbe;
    public final Level level;

    public SellingBinMenu(int containerId, Inventory inv, FriendlyByteBuf extraData)
    {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public SellingBinMenu(int containerId, Inventory inv, BlockEntity blockEntity)
    {
        super(ModMenuTypes.SELLING_BIN_MENU.get(), containerId);
        ssbe = ((SellingBinBlockEntity) blockEntity);
        level = inv.player.level();

            this.addSlot(new SlotItemHandler(ssbe.storage, 0, 21, 157)
            {
                @Override
                public boolean mayPlace(ItemStack stack)
                {
                    return super.mayPlace(stack);
                }
            });
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i)
    {
        return null;
    }

    @Override
    public boolean stillValid(Player player)
    {
        return true;
    }
}
