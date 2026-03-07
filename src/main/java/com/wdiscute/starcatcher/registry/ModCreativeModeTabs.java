package com.wdiscute.starcatcher.registry;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.registry.blocks.ModBlocks;
import com.wdiscute.starcatcher.secretnotes.LetterItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;


public class ModCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Starcatcher.MOD_ID);

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final LetterItem.Message MESSAGE = new LetterItem.Message(
            UUID.randomUUID(),
            "<rgb>-dev (wd)</rgb>",
            Level.OVERWORLD.location(),
            List.of(
                    "",
                    "",
                    "This is a cheated message from creative.",
                    "",
                    "Usually players would write their own",
                    "message and send it to the ocean for",
                    "others to fish later.",
                    "(5% chance if there is any available)",
                    "",
                    "<rgb>also did you know it supports rgb?</rgb>",
                    "learn more about it in the LibTooltips wiki"
            ),
            true
    );

    public static final Supplier<CreativeModeTab> STARCATCHER = CREATIVE_MODE_TABS.register(
            "starcatcher", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ROD.get()))
                    .title(Component.translatable("creativetab.starcatcher.starcatcher"))
                    .displayItems((itemDisplayParameters, output) ->
                    {

                        output.accept(ModItems.ROD);

                        //adds items
                        for (DeferredHolder<Item, ? extends Item> item : ModItems.ITEMS.getEntries())
                        {
                            if (item.equals(ModItems.ROD)) continue;

                            if (item.equals(ModItems.BOTTLED_LETTER))
                            {
                                ItemStack is = new ItemStack(ModItems.BOTTLED_LETTER.get());

                                ModDataComponents.set(is, ModDataComponents.MESSAGE, MESSAGE);
                                output.accept(is);
                                continue;
                            }

                            if (item.equals(ModItems.MESSAGE_IN_A_BOTTLE))
                            {
                                ItemStack is = new ItemStack(ModItems.MESSAGE_IN_A_BOTTLE.get());

                                ModDataComponents.set(is, ModDataComponents.MESSAGE, MESSAGE);
                                output.accept(is);
                                continue;
                            }

                            if (item.equals(ModItems.MESSAGE)) continue;

                            output.accept(item.get());
                        }


                        //adds bobbers
                        for (DeferredHolder<Item, ? extends Item> item : ModItems.BOBBERS_REGISTRY.getEntries())
                            output.accept(item.get());

                        //adds hooks
                        for (DeferredHolder<Item, ? extends Item> item : ModItems.HOOKS_REGISTRY.getEntries())
                            output.accept(item.get());

                        //adds templates
                        for (DeferredHolder<Item, ? extends Item> item : ModItems.TEMPLATES_REGISTRY.getEntries())
                            output.accept(item.get());

                        //adds rods besides default
                        for (DeferredHolder<Item, ? extends Item> item : ModItems.RODS_REGISTRY.getEntries())
                            if (!item.equals(ModItems.ROD))
                                output.accept(item.get());

                        //adds fish
                        for (DeferredHolder<Item, ? extends Item> item : ModItems.BUCKETABLE_FISHES_REGISTRY.getEntries())
                            output.accept(item.get());

                    })
                    .build()
    );
}
