package com.wdiscute.starcatcher.registry;

import com.wdiscute.sellingbin.registry.SBBlocks;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.messageinabottle.message.Message;
import com.wdiscute.starcatcher.modifiers.catchmodifiers.AdjustLureTimeModifier;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.NeverLoseModifier;
import net.mcexpanded.fancytabsections.FancyTabSections;
import net.mcexpanded.fancytabsections.Section.SectionColored;
import net.mcexpanded.fancytabsections.creativetab.ConglomerateOfItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;


public interface SCCreativeModeTabs
{
    DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Starcatcher.MOD_ID);

    static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
        addItems();
    }

    static void addItems()
    {
        //Must Have
        FancyTabSections.addSection(Starcatcher.rl("starcatcher"),
                new SectionColored(
                        Starcatcher.rl("must_have"),
                        Component.translatable("creativetab.starcatcher.starcatcher.must_have"),
                        0xff344545,
                        0xffffffff,
                        ConglomerateOfItems.create()
                                .add(SCItems.ROD)
                                .add(() ->
                                {
                                    ItemStack devRod = SCItems.ICEBORN_ROD.toStack();
                                    SCDataComponents.set(devRod, SCDataComponents.MODIFIERS, List.of(
                                            new NeverLoseModifier(""),
                                            new AdjustLureTimeModifier(0.2f, 0.2f, 0.2f, "")
                                    ));
                                    return devRod;
                                })
                                .add(SCItems.GUIDE)
                                .add(SCBlocks.STAND)
                                .add(SCBlocks.DISPLAY)
                                .add(SCBlocks.TACKLE_BOX)
                                .add(SCBlocks.AQUARIUM)
                                .add(SBBlocks.SELLING_BIN)

                )
        );


        //hooks & bobbers
        FancyTabSections.addSection(Starcatcher.rl("starcatcher"),
                new SectionColored(
                        Starcatcher.rl("hooks_bobbers"),
                        Component.translatable("creativetab.starcatcher.starcatcher.hooks_bobbers"),
                        0xff344545,
                        0xffffffff,
                        ConglomerateOfItems.create()
                                .add(SCItems.HOOKS_REGISTRY)

                                .add(SCItems.BOBBERS_REGISTRY)

                                .add(SCItems.WORM)
                                .add(SCItems.ALMIGHTY_WORM)
                                .add(SCItems.SEEKING_WORM)

                                .add(SCItems.GUNPOWDER_BAIT)
                                .add(SCItems.CHERRY_BAIT)
                                .add(SCItems.LUSH_BAIT)
                                .add(SCItems.SCULK_BAIT)
                                .add(SCItems.DRIPSTONE_BAIT)
                                .add(SCItems.MURKWATER_BAIT)
                                .add(SCItems.LEGENDARY_BAIT)
                                .add(SCItems.METEOROLOGICAL_BAIT)
                )
        );


        ConglomerateOfItems cosmetics = ConglomerateOfItems.create()
                .add(SCItems.RODS_REGISTRY)
                .add(SCItems.TEMPLATES_REGISTRY);

        cosmetics.conglomerate.addAll(SCBlocks.HATS.getEntries());

        FancyTabSections.addSection(Starcatcher.rl("starcatcher"),
                new SectionColored(
                        Starcatcher.rl("cosmetics"),
                        Component.translatable("creativetab.starcatcher.starcatcher.cosmetics"),
                        0xff344545,
                        0xffffffff,
                        cosmetics
                )
        );

        //tackle boxes
        ConglomerateOfItems items = ConglomerateOfItems.create();
        items.conglomerate.addAll(SCBlocks.TACKLE_BOXES.getEntries());
        FancyTabSections.addSection(Starcatcher.rl("starcatcher"),
                new SectionColored(
                        Starcatcher.rl("tackle_boxes"),
                        Component.translatable("creativetab.starcatcher.starcatcher.tackle_boxes"),
                        0xff344545,
                        0xffffffff,
                        items
                )
        );

        //Trophies & Secrets
        FancyTabSections.addSection(Starcatcher.rl("starcatcher"),
                new SectionColored(
                        Starcatcher.rl("trophies"),
                        Component.translatable("creativetab.starcatcher.starcatcher.trophies"),
                        0xff344545,
                        0xffffffff,
                        ConglomerateOfItems.create()
                                .add(SCBlocks.TROPHY_COPPER)
                                .add(SCBlocks.TROPHY_IRON)
                                .add(SCBlocks.TROPHY_GOLD)
                                .add(SCBlocks.TROPHY_EMERALD)
                                .add(SCBlocks.TROPHY_DIAMOND)
                                .add(SCBlocks.TROPHY_OF_THE_OLDER_ANGLER)

                                .add(SCItems.LETTER)
                                .add(SCItems.BOTTLED_LETTER)
                                .add(SCItems.MESSAGE_IN_A_BOTTLE)
                                .add(SCItems.BROKEN_BOTTLE)
                                .add(SCItems.MESSAGE)

                                //secret messages
                                //amethyst hook
                                .add(() ->
                                {
                                    ItemStack stack = SCItems.MESSAGE_IN_A_BOTTLE.toStack();
                                    SCDataComponents.set(stack, SCDataComponents.MESSAGE, Message.BuiltIn.AMETHYST_HOOK);
                                    return stack;
                                })

                                //hopeful
                                .add(() ->
                                {
                                    ItemStack stack = SCItems.MESSAGE_IN_A_BOTTLE.toStack();
                                    SCDataComponents.set(stack, SCDataComponents.MESSAGE, Message.BuiltIn.HOPEFUL);
                                    return stack;
                                })

                                //hopeful
                                .add(() ->
                                {
                                    ItemStack stack = SCItems.MESSAGE_IN_A_BOTTLE.toStack();
                                    SCDataComponents.set(stack, SCDataComponents.MESSAGE, Message.BuiltIn.HOPELESS);
                                    return stack;
                                })

                                //lava 1
                                .add(() ->
                                {
                                    ItemStack stack = SCItems.MESSAGE_IN_A_BOTTLE.toStack();
                                    SCDataComponents.set(stack, SCDataComponents.MESSAGE, Message.BuiltIn.LAVA_PROOF_1);
                                    return stack;
                                })

                                //lava 2
                                .add(() ->
                                {
                                    ItemStack stack = SCItems.MESSAGE_IN_A_BOTTLE.toStack();
                                    SCDataComponents.set(stack, SCDataComponents.MESSAGE, Message.BuiltIn.LAVA_PROOF_2);
                                    return stack;
                                })

                                //true blue
                                .add(() ->
                                {
                                    ItemStack stack = SCItems.MESSAGE_IN_A_BOTTLE.toStack();
                                    SCDataComponents.set(stack, SCDataComponents.MESSAGE, Message.BuiltIn.TRUE_BLUE);
                                    return stack;
                                })

                                //wither
                                .add(() ->
                                {
                                    ItemStack stack = SCItems.MESSAGE_IN_A_BOTTLE.toStack();
                                    SCDataComponents.set(stack, SCDataComponents.MESSAGE, Message.BuiltIn.WITHER);
                                    return stack;
                                })





                )
        );

        //Fish
        FancyTabSections.addSection(Starcatcher.rl("starcatcher"),
                new SectionColored(
                        Starcatcher.rl("fish"),
                        Component.translatable("creativetab.starcatcher.starcatcher.fish"),
                        0xff344545,
                        0xffffffff,
                        ConglomerateOfItems.create()
                                .add(SCItems.BUCKETABLE_FISHES_REGISTRY)
                                .add(SCItems.NON_BUCKETABLE_FISH_REGISTRY)
                                .add(SCBlocks.CLAM)
                                .add(SCBlocks.CONCH)
                )
        );

        //Miscellaneous
        FancyTabSections.addSection(Starcatcher.rl("starcatcher"),
                new SectionColored(
                        Starcatcher.rl("miscellaneous"),
                        Component.translatable("creativetab.starcatcher.starcatcher.miscellaneous"),
                        0xff344545,
                        0xffffffff,
                        ConglomerateOfItems.create()
                                .add(SCItems.BOOT)
                                .add(SCItems.MOSSY_BOOT)
                                .add(SCItems.DRIED_SEAWEED)
                                .add(SCItems.LAVA_CRAB_CLAW)

                                .add(SCItems.FISH_BONES)

                                .add(SCItems.FISH_RADAR)
                                .add(SCItems.PEARL)
                                .add(SCItems.STARCATCHER_TWINE)
                                .add(SCItems.MISSINGNO)
                                .add(SCItems.UNKNOWN_FISH)
                                .add(SCItems.STARCAUGHT_BUCKET)
                                .add(SCItems.STARCAUGHT_LAVA_BUCKET)
                                .add(SCItems.STARCAUGHT_FISH)
                                .add(SCItems.COOKED_STARCAUGHT_FISH)
                )
        );
    }

    Supplier<CreativeModeTab> STARCATCHER = CREATIVE_MODE_TABS.register(
            "starcatcher", () -> CreativeModeTab.builder().icon(() -> new ItemStack(SCItems.ROD.get()))
                    .title(Component.translatable("creativetab.starcatcher.starcatcher"))
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        //empty as it's added through Fancy Tab Sections instead
                    })
                    .build()
    );
}
