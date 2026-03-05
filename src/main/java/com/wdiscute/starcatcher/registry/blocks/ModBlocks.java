package com.wdiscute.starcatcher.registry.blocks;

import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.registry.blocks.aquarium.AquariumBlock;
import com.wdiscute.starcatcher.registry.blocks.Telescope.TelescopeBlock;
import com.wdiscute.starcatcher.registry.blocks.display.DisplayBlock;
import com.wdiscute.starcatcher.registry.blocks.sellingbin.SellingBin;
import com.wdiscute.starcatcher.registry.blocks.stand.StandBlock;
import com.wdiscute.starcatcher.registry.custom.catchmodifiers.ModCatchModifiers;
import com.wdiscute.starcatcher.registry.items.HatItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface ModBlocks
{
    DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Starcatcher.MOD_ID);
    DeferredRegister.Blocks HATS = DeferredRegister.createBlocks(Starcatcher.MOD_ID);

    DeferredBlock<Block> TROPHY_GOLD = registerBlock("trophy_gold", TrophyBlock::new);
    DeferredBlock<Block> TROPHY_SILVER = registerBlock("trophy_silver", TrophyBlock::new);
    DeferredBlock<Block> TROPHY_BRONZE = registerBlock("trophy_bronze", TrophyBlock::new);

    DeferredBlock<Block> STAND = registerBlock("tournament_stand", StandBlock::new);

    DeferredBlock<Block> DISPLAY = registerBlock("display", DisplayBlock::new);

    DeferredBlock<Block> TELESCOPE = registerBlock("telescope", TelescopeBlock::new);

    DeferredBlock<Block> SELLING_BIN = registerBlock("selling_bin", SellingBin::new);

    DeferredBlock<Block> TACKLE_BOX = registerBlock("tackle_box", () -> new Block(BlockBehaviour.Properties.of()));

    DeferredBlock<Block> AQUARIUM = registerBlock("aquarium", AquariumBlock::new);

    //hats
    DeferredBlock<Block> FISHERMAN_HAT_WHITE = registerHat("fisherman_hat_white", HatBlock::new);
    DeferredBlock<Block> FISHERMAN_HAT_LIME = registerHat("fisherman_hat_lime", HatBlock::new, ModCatchModifiers.BIG_DECREASES_LURE_TIME.getFirst(), ModCatchModifiers.ADD_CREEPER.getFirst());
    DeferredBlock<Block> FISHERMAN_HAT_ORANGE = registerHat("fisherman_hat_orange", HatBlock::new);
    DeferredBlock<Block> FISHERMAN_HAT_RED = registerHat("fisherman_hat_red", HatBlock::new);
    DeferredBlock<Block> FISHERMAN_HAT_GRAY = registerHat("fisherman_hat_gray", HatBlock::new);
    DeferredBlock<Block> FISHERMAN_HAT_LIGHT_GRAY = registerHat("fisherman_hat_light_gray", HatBlock::new);
    DeferredBlock<Block> FISHERMAN_HAT_BLACK = registerHat("fisherman_hat_black", HatBlock::new, ModCatchModifiers.HIDE_CATCH.getFirst(), ModCatchModifiers.EXTRA_ITEM.getFirst());
    DeferredBlock<Block> FISHERMAN_HAT_BROWN = registerHat("fisherman_hat_brown", HatBlock::new);
    DeferredBlock<Block> FISHERMAN_HAT_YELLOW = registerHat("fisherman_hat_yellow", HatBlock::new);
    DeferredBlock<Block> FISHERMAN_HAT_PINK = registerHat("fisherman_hat_pink", HatBlock::new);
    DeferredBlock<Block> FISHERMAN_HAT_MAGENTA = registerHat("fisherman_hat_magenta", HatBlock::new);
    DeferredBlock<Block> FISHERMAN_HAT_PURPLE = registerHat("fisherman_hat_purple", HatBlock::new);
    DeferredBlock<Block> FISHERMAN_HAT_BLUE = registerHat("fisherman_hat_blue", HatBlock::new);
    DeferredBlock<Block> FISHERMAN_HAT_LIGHT_BLUE = registerHat("fisherman_hat_light_blue", HatBlock::new);
    DeferredBlock<Block> FISHERMAN_HAT_CYAN = registerHat("fisherman_hat_cyan", HatBlock::new);
    DeferredBlock<Block> FISHERMAN_HAT_GREEN = registerHat("fisherman_hat_green", HatBlock::new, ModCatchModifiers.BIG_DECREASES_LURE_TIME.getFirst());

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block)
    {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerHat(String name, Supplier<T> block, ResourceLocation... modifiers)
    {
        DeferredBlock<T> toReturn = HATS.register(name, block);
        if (modifiers.length == 0)
            ModItems.ITEMS.register(name, () -> new HatItem(toReturn.get()));
        else
            ModItems.ITEMS.register(name, () -> new HatItem(toReturn.get(), modifiers));
        return toReturn;
    }

    static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
        HATS.register(eventBus);
    }
}
