package com.wdiscute.starcatcher.event;

import com.wdiscute.libtooltips.Tooltips;
import com.wdiscute.starcatcher.Config;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.guide.SettingsScreen;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.io.CaughtFishInfo;
import com.wdiscute.starcatcher.registry.custom.sellingbinprocessor.ModSellingBinProcessors;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EventBusSubscriber(modid = Starcatcher.MOD_ID, value = Dist.CLIENT)
public class TooltipEvents
{

    @SubscribeEvent
    public static void tooltipEvent(ItemTooltipEvent event)
    {
        List<Component> comp = event.getToolTip();
        ItemStack stack = event.getItemStack();
        boolean hasShiftDown = event.getFlags().hasShiftDown();

        if (ModDataComponents.has(stack, ModDataComponents.MINIGAME_MODIFIERS) || ModDataComponents.has(stack, ModDataComponents.CATCH_MODIFIERS))
        {
            List<ResourceLocation> modifiers = new ArrayList<>();

            if (ModDataComponents.has(stack, ModDataComponents.CATCH_MODIFIERS))
                modifiers.addAll(Objects.requireNonNull(ModDataComponents.get(stack, ModDataComponents.CATCH_MODIFIERS)));
            if (ModDataComponents.has(stack, ModDataComponents.MINIGAME_MODIFIERS))
                modifiers.addAll(Objects.requireNonNull(ModDataComponents.get(stack, ModDataComponents.MINIGAME_MODIFIERS)));

            if (!modifiers.isEmpty())
            {
                comp.add(Component.translatable("tooltip.starcatcher.modifiers").withStyle(ChatFormatting.GRAY));

                for (ResourceLocation rl : modifiers)
                {
                    for (int i = 0; i < 100; i++)
                    {
                        if (I18n.exists("tooltip.modifier." + rl.toLanguageKey() + "." + i))
                        {
                            MutableComponent start = i == 0 ? Component.literal("- ") : Component.literal("");
                            comp.add(start.append(Component.translatable("tooltip.modifier." + rl.toLanguageKey() + "." + i)).withStyle(ChatFormatting.DARK_GRAY));
                        }
                        else
                        {
                            break;
                        }
                    }
                }
            }
        }

        //caught fish info
        if (ModDataComponents.has(stack, ModDataComponents.CAUGHT_FISH_INFO))
        {
            SettingsScreen.Units units = Config.UNIT.get();
            CaughtFishInfo sw = ModDataComponents.get(stack, ModDataComponents.CAUGHT_FISH_INFO);

            if (sw.golden())
            {
                MutableComponent element = Component.empty().append(Tooltips.decodeTranslationKey("gui.guide.rarity.golden")).withStyle(Style.EMPTY.withColor(0x888888));
                if (hasShiftDown)
                    element.append(Component.literal(" (top 0%)").withStyle(Style.EMPTY.withColor(0x707070)));
                comp.add(1, element);
            }
            else
            {
                String size = units.getSizeAsString(sw.sizeInCentimeters());
                String weight = units.getWeightAsString(sw.weightInGrams());
                String percentile = " (top " + (int) sw.percentile() + "%)";

                MutableComponent element = Component.literal(size + " - " + weight).withStyle(Style.EMPTY.withColor(0x888888));
                if (hasShiftDown)
                    element.append(Component.literal(percentile).withStyle(Style.EMPTY.withColor(0x707070)));
                comp.add(1, element);
            }
        }

        //selling bin info
        if (Screen.hasShiftDown())
        {
            int value = ModSellingBinProcessors.calculateFromStack(stack);
            if (value > 0)
            {
                Integer lowestValue = Config.SELLING_BIN_LOWEST_VALUE.get();

                //if (value > lowestValue)
                {
                    DecimalFormat df = new DecimalFormat("#.##");
                    float v = ((float) value / lowestValue);
                    MutableComponent component = Component.literal(df.format(v) + " Emeralds");

                    if(stack.getCount() > 1)
                        component.append(Component.literal(" (" + df.format(v * stack.getCount()) + " Emeralds)"));


                    comp.add(1, component.withStyle(ChatFormatting.DARK_GRAY));
                }

                //if (value > Config.SELLING_BIN_LOWEST_VALUE.get())
                {

                }

                //if (value > Config.SELLING_BIN_LOWEST_VALUE.get())
                {

                }
            }
        }

        //tackle skin
        if (ModDataComponents.has(stack, ModDataComponents.TACKLE_SKIN))
        {
            ResourceLocation rl = ModDataComponents.get(stack, ModDataComponents.TACKLE_SKIN);
            comp.add(Component.translatable("tooltip.starcatcher.tackle").withStyle(ChatFormatting.GRAY));

            for (int i = 0; i < 100; i++)
            {
                if (I18n.exists("tooltip.tackle." + rl.toLanguageKey() + "." + i))
                {
                    MutableComponent start = i == 0 ? Component.literal("- ") : Component.literal("");
                    comp.add(start.append(Component.translatable("tooltip.tackle." + rl.toLanguageKey() + "." + i)).withStyle(ChatFormatting.DARK_GRAY));
                }
                else break;
            }
        }

        //Netherite Upgrade
        if (ModDataComponents.has(stack, ModDataComponents.NETHERITE_UPGRADE))
        {
            if (Boolean.TRUE.equals(ModDataComponents.get(stack, ModDataComponents.NETHERITE_UPGRADE)))
            {
                comp.add(1, Tooltips.decodeTranslationKey("tooltip.starcatcher.rod.netherite"));
            }
        }
    }
}
