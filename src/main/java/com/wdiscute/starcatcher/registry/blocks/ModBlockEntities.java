package com.wdiscute.starcatcher.registry.blocks;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.registry.blocks.aquarium.AquariumBlockEntity;
import com.wdiscute.starcatcher.registry.blocks.Telescope.TelescopeBlockEntity;
import com.wdiscute.starcatcher.registry.blocks.display.DisplayBlockEntity;
import com.wdiscute.starcatcher.registry.blocks.sellingbin.SellingBinBlockEntity;
import com.wdiscute.starcatcher.registry.blocks.stand.StandBlockEntity;
import com.wdiscute.starcatcher.registry.blocks.tacklebox.TackleBoxBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Starcatcher.MOD_ID);

    public static final Supplier<BlockEntityType<StandBlockEntity>> STAND = BLOCK_ENTITIES.register("stand",
            () -> BlockEntityType.Builder.of(StandBlockEntity::new,
                            ModBlocks.STAND.get()
                    ).build(null));

    public static final Supplier<BlockEntityType<SellingBinBlockEntity>> SELLING_BIN = BLOCK_ENTITIES.register("selling_bin",
            () -> BlockEntityType.Builder.of(SellingBinBlockEntity::new,
                    ModBlocks.SELLING_BIN.get()
            ).build(null));

    public static final Supplier<BlockEntityType<TelescopeBlockEntity>> TELESCOPE = BLOCK_ENTITIES.register("telescope",
            () -> BlockEntityType.Builder.of(TelescopeBlockEntity::new,
                    ModBlocks.TELESCOPE.get()
            ).build(null));

    public static final Supplier<BlockEntityType<DisplayBlockEntity>> DISPLAY = BLOCK_ENTITIES.register("display",
            () -> BlockEntityType.Builder.of(DisplayBlockEntity::new,
                    ModBlocks.DISPLAY.get()
            ).build(null));

    public static final Supplier<BlockEntityType<AquariumBlockEntity>> AQUARIUM = BLOCK_ENTITIES.register("aquarium",
            () -> BlockEntityType.Builder.of(AquariumBlockEntity::new,
                    ModBlocks.AQUARIUM.get()
            ).build(null));

    public static final Supplier<BlockEntityType<TackleBoxBlockEntity>> TACKLE_BOX = BLOCK_ENTITIES.register("tackle_box.png",
            () -> BlockEntityType.Builder.of(TackleBoxBlockEntity::new,
                    ModBlocks.TACKLE_BOX.get(),
                    ModBlocks.TACKLE_BOX_WHITE.get(),
                    ModBlocks.TACKLE_BOX_LIME.get(),
                    ModBlocks.TACKLE_BOX_ORANGE.get(),
                    ModBlocks.TACKLE_BOX_RED.get(),
                    ModBlocks.TACKLE_BOX_GRAY.get(),
                    ModBlocks.TACKLE_BOX_LIGHT_GRAY.get(),
                    ModBlocks.TACKLE_BOX_BLACK.get(),
                    ModBlocks.TACKLE_BOX_BROWN.get(),
                    ModBlocks.TACKLE_BOX_YELLOW.get(),
                    ModBlocks.TACKLE_BOX_PINK.get(),
                    ModBlocks.TACKLE_BOX_MAGENTA.get(),
                    ModBlocks.TACKLE_BOX_PURPLE.get(),
                    ModBlocks.TACKLE_BOX_BLUE.get(),
                    ModBlocks.TACKLE_BOX_LIGHT_BLUE.get(),
                    ModBlocks.TACKLE_BOX_CYAN.get(),
                    ModBlocks.TACKLE_BOX_GREEN.get()
            ).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
