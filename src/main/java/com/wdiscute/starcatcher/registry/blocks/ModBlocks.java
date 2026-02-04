package com.wdiscute.starcatcher.registry.blocks;

import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.registry.blocks.Telescope.TelescopeBlock;
import com.wdiscute.starcatcher.registry.blocks.display.DisplayBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface ModBlocks
{
    DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Starcatcher.MOD_ID);

    DeferredBlock<Block> TROPHY_GOLD = registerBlockDatagen("trophy_gold", TrophyBlock::new);
    DeferredBlock<Block> TROPHY_SILVER = registerBlockDatagen("trophy_silver", TrophyBlock::new);
    DeferredBlock<Block> TROPHY_BRONZE = registerBlockDatagen("trophy_bronze", TrophyBlock::new);

    DeferredBlock<Block> STAND = registerBlock("tournament_stand", StandBlock::new);

    DeferredBlock<Block> DISPLAY = registerBlock("display", DisplayBlock::new);

    DeferredBlock<Block> TELESCOPE = registerBlock("telescope", TelescopeBlock::new);


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block)
    {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        ModItems.BLOCKITEMS_REGISTRY.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> DeferredBlock<T> registerBlockDatagen(String name, Supplier<T> block)
    {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItemDatagen(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItemDatagen(String name, DeferredBlock<T> block)
    {
        ModItems.ITEMS_REGISTRY.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
