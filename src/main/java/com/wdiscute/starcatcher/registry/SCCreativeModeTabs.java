package com.wdiscute.starcatcher.registry;

import com.wdiscute.sellingbin.registry.SBBlocks;
import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.FishApi;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.messageinabottle.message.Message;
import com.wdiscute.starcatcher.modifiers.catchmodifiers.AdjustLureTimeModifier;
import com.wdiscute.starcatcher.modifiers.catchmodifiers.ExtraGoldenChanceModifier;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.NeverLoseModifier;
import com.wdiscute.utils.Utils;
import net.mcexpanded.fancytabsections.FancyTabSections;
import net.mcexpanded.fancytabsections.Section.Section;
import net.mcexpanded.fancytabsections.Section.SectionAnimatedTextured;
import net.mcexpanded.fancytabsections.Section.SectionColored;
import net.mcexpanded.fancytabsections.creativetab.ConglomerateOfItems;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
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
                new SectionAnimatedTextured(Starcatcher.rl("must_have"))
                {
                    @Override
                    public void renderToggle(CreativeModeInventoryScreen screen, GuiGraphics graphics, Section<?> section, int x, int y, int w, int h, int mouseX, int mouseY, boolean isHoveringAny)
                    {

                    }
                }
                        .setFrames(9)
                        .setFrameTimeInMS(200)
                        .setTextOutline(0xff000000)
                        .setTitle(Component.empty())
                        .setCollapsible(false)
                        .add(SCItems.ROD)
                        .add(() ->
                        {
                            ItemStack devRod = SCItems.ICEBORN_ROD.toStack();
                            SCDataComponents.set(devRod, SCDataComponents.MODIFIERS, List.of(
                                    new NeverLoseModifier(""),
                                    new AdjustLureTimeModifier(0.05f, 0.05f, 1f, "")
                            ));
                            return devRod;
                        })
                        .add(() ->
                        {
                            ItemStack devRod = SCItems.OBSIDIAN_ROD.toStack();
                            SCDataComponents.set(devRod, SCDataComponents.MODIFIERS, List.of(
                                    new ExtraGoldenChanceModifier(1, false, ""),
                                    new AdjustLureTimeModifier(0.05f, 0.05f, 1f, "")
                            ));
                            return devRod;
                        })
                        .add(SCItems.GUIDE)
                        .add(SCBlocks.STAND)
                        .add(SCBlocks.DISPLAY)
                        .add(SCBlocks.TACKLE_BOX)
                        .add(SCBlocks.AQUARIUM)
                        .add(SBBlocks.SELLING_BIN)
        );


        //hooks & bobbers
        FancyTabSections.addSection(Starcatcher.rl("starcatcher"),
                new SectionColored(Starcatcher.rl("hooks_bobbers"))
                        .setBannerColor(0xff344545)

                        .addItemTag(SCTags.HOOKS)
                        .addItemTag(SCTags.BOBBERS)
        );

        //cosmetics
        FancyTabSections.addSection(Starcatcher.rl("starcatcher"),
                new SectionColored(Starcatcher.rl("cosmetics"))
                        .setBannerColor(0xff344545)
                        .add(SCItems.RODS_REGISTRY)
                        .add(SCItems.TEMPLATES_REGISTRY)
                        .add((d) -> SCBlocks.HATS.getEntries().stream().map(o -> o.get().asItem().getDefaultInstance()).toList())
        );

        //tackle boxes
        FancyTabSections.addSection(Starcatcher.rl("starcatcher"),
                new SectionColored(Starcatcher.rl("tackle_boxes"))
                        .setBannerColor(0xff344545)
                        .add((d) -> SCBlocks.TACKLE_BOXES.getEntries().stream().map(o -> o.get().asItem().getDefaultInstance()).toList())
        );

        //Trophies & Secrets
        FancyTabSections.addSection(Starcatcher.rl("starcatcher"),
                new SectionColored(Starcatcher.rl("trophies"))
                        .setBannerColor(0xff344545)

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
                        .add((registryAccess) ->
                                {
                                    List<ItemStack> list = new ArrayList<>();
                                    for (FishProperties fp : FishApi.getMessages(registryAccess))
                                    {
                                        ItemStack stack = fp.catchInfo().fish().toStack();

                                        if (stack.has(SCDataComponents.MESSAGE))
                                        {
                                            SCDataComponents.set(stack, SCDataComponents.MESSAGE, stack.get(SCDataComponents.MESSAGE));
                                            list.add(stack);
                                        }
                                    }
                                    return list;
                                }
                        )
        );

        //Fish
        FancyTabSections.addSection(Starcatcher.rl("starcatcher"),
                new SectionColored(Starcatcher.rl("fish"))
                        .setBannerColor(0xff344545)
                        .add(SCItems.BUCKETABLE_FISHES_REGISTRY)
                        .add(SCItems.NON_BUCKETABLE_FISH_REGISTRY)
                        .add(SCBlocks.CLAM)
                        .add(SCBlocks.CONCH)
        );

        //Miscellaneous
        FancyTabSections.addSection(Starcatcher.rl("starcatcher"),
                new SectionColored(Starcatcher.rl("miscellaneous"))
                        .setBannerColor(0xff344545)
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
        );
    }

    Supplier<CreativeModeTab> STARCATCHER = CREATIVE_MODE_TABS.register(
            "starcatcher", () -> CreativeModeTab.builder().icon(() -> new ItemStack(SCItems.ROD.get()))
                    .title(Component.translatable("itemGroup.starcatcher"))
                    .build()
    );
}
