package com.wdiscute.starcatcher.event;

import com.wdiscute.libtooltips.Tooltips;
import com.wdiscute.starcatcher.Config;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.guide.SettingsScreen;
import com.wdiscute.starcatcher.io.SCDataComponents;
import com.wdiscute.starcatcher.io.CaughtFishInfo;
import net.minecraft.ChatFormatting;
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

        //modifiers
        if (SCDataComponents.has(stack, SCDataComponents.MINIGAME_MODIFIERS) || SCDataComponents.has(stack, SCDataComponents.CATCH_MODIFIERS))
        {
            List<ResourceLocation> modifiers = new ArrayList<>();

            if (SCDataComponents.has(stack, SCDataComponents.CATCH_MODIFIERS))
                modifiers.addAll(Objects.requireNonNull(SCDataComponents.get(stack, SCDataComponents.CATCH_MODIFIERS)));
            if (SCDataComponents.has(stack, SCDataComponents.MINIGAME_MODIFIERS))
                modifiers.addAll(Objects.requireNonNull(SCDataComponents.get(stack, SCDataComponents.MINIGAME_MODIFIERS)));

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
        if (SCDataComponents.has(stack, SCDataComponents.CAUGHT_FISH_INFO))
        {
            SettingsScreen.Units units = Config.UNIT.get();
            CaughtFishInfo sw = SCDataComponents.get(stack, SCDataComponents.CAUGHT_FISH_INFO);

            if (sw.golden())
            {
                MutableComponent element = Component.empty().append(Component.translatable("gui.guide.rarity.golden")).withStyle(Style.EMPTY.withColor(0x888888));
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

        //tackle skin
        if (SCDataComponents.has(stack, SCDataComponents.TACKLE_SKIN))
        {
            ResourceLocation rl = SCDataComponents.get(stack, SCDataComponents.TACKLE_SKIN);
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
        if (SCDataComponents.has(stack, SCDataComponents.NETHERITE_UPGRADE))
        {
            if (Boolean.TRUE.equals(SCDataComponents.get(stack, SCDataComponents.NETHERITE_UPGRADE)))
            {
                comp.add(1, Component.translatable("tooltip.starcatcher.rod.netherite"));
            }
        }
    }
}
