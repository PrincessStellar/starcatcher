package com.wdiscute.starcatcher.event;

import com.wdiscute.libtooltips.Tooltips;
import com.wdiscute.starcatcher.SCColors;
import com.wdiscute.starcatcher.SCConfig;
import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.SizeAndWeight;
import com.wdiscute.starcatcher.io.CaughtFishInfo;
import com.wdiscute.starcatcher.modifiers.Modifier;
import com.wdiscute.starcatcher.registry.SCDataComponents;
import com.wdiscute.starcatcher.registry.SCDataMaps;
import com.wdiscute.starcatcher.registry.tackleskin.AbstractTackleSkin;
import com.wdiscute.starcatcher.registry.tackleskin.SCTackleSkins;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = Starcatcher.MOD_ID, value = Dist.CLIENT)
public class TooltipEvents
{

    static int cachedTimer = 0;
    static ItemStack cachedItem = ItemStack.EMPTY;
    static List<Component> cachedComps = List.of();
    static boolean cachedShift = false;

    @SubscribeEvent
    public static void tooltipEvent(ItemTooltipEvent event)
    {
        List<Component> comp = new ArrayList<>();
        ItemStack stack = event.getItemStack();
        boolean hasShiftDown = event.getFlags().hasShiftDown();

        //cache check
        if (stack == cachedItem && cachedTimer > 0 && hasShiftDown == cachedShift)
        {
            cachedTimer--;
            if (!event.getToolTip().isEmpty())
                event.getToolTip().addAll(1, cachedComps);
            else
                event.getToolTip().addAll(cachedComps);
            return;
        }

        cachedTimer = 100;
        cachedShift = hasShiftDown;
        cachedItem = stack;

        //Netherite Upgrade
        if (SCDataComponents.has(stack, SCDataComponents.NETHERITE_UPGRADE))
        {
            if (Boolean.TRUE.equals(SCDataComponents.get(stack, SCDataComponents.NETHERITE_UPGRADE)))
            {
                comp.add(Tooltips.resolveTagsToComponentFromTranslationKey("tooltip.starcatcher.rod.netherite"));
            }
        }

        //tackle skin data component
        ResourceLocation tackleSkinDC = Starcatcher.TACKLE_SKIN_REGISTRY.getKey(
                SCDataComponents.getOrDefault(stack, SCDataComponents.TACKLE_SKIN, Starcatcher.TACKLE_SKIN_REGISTRY.get(Starcatcher.BASE)));
        if (tackleSkinDC != null && !tackleSkinDC.equals(Starcatcher.BASE))
        {
            String s = I18n.get("tooltip.tackle." + tackleSkinDC.toLanguageKey());
            if (!s.isEmpty())
            {
                comp.add(Tooltips.resolveTagsToComponentFromTranslationKey("tooltip.starcatcher.tackle").withStyle(ChatFormatting.GRAY));
                comp.add(Component.literal(" -").append(Component.literal(s))
                        .withStyle(Style.EMPTY.withColor(SCColors.TOOLTIP_GRAY)));
            }
        }

        //tackle skin data map
        ResourceLocation tackleSkinDM = Starcatcher.TACKLE_SKIN_REGISTRY.getKey(
                SCDataMaps.getOrDefault(stack, SCDataMaps.TACKLE_SKIN, Starcatcher.TACKLE_SKIN_REGISTRY.get(Starcatcher.BASE)));
        if (tackleSkinDM != null && !tackleSkinDM.equals(Starcatcher.BASE))
        {
            String s = I18n.get("tooltip.tackle." + tackleSkinDM.toLanguageKey());
            if (!s.isEmpty())
            {
                comp.add(Tooltips.resolveTagsToComponentFromTranslationKey("tooltip.starcatcher.tackle").withStyle(ChatFormatting.GRAY));
                comp.add(Component.literal(" -").append(Component.literal(s))
                        .withStyle(Style.EMPTY.withColor(SCColors.TOOLTIP_GRAY)));
            }
        }

        //modifiers
        Player player = Minecraft.getInstance().player;
        List<Modifier> modifiers;

        //if rod, get every modifier not just the itemstack
        if (player != null && (stack == player.getItemInHand(InteractionHand.MAIN_HAND) || stack == player.getItemInHand(InteractionHand.OFF_HAND)))
            modifiers = Modifier.getModifiers(player);
        else
            modifiers = Modifier.getModifiers(stack);

        if (!modifiers.isEmpty())
        {
            List<Component> modComp = new ArrayList<>();

            //add modifiers
            Player entity = event.getEntity();
            if (entity != null)
            {
                //add catch modifiers
                modifiers.forEach(o ->
                        o.getDescription(hasShiftDown).forEach(
                                c ->
                                {
                                    if (!c.getString().equals("hide") && !c.getString().isEmpty())
                                        modComp.add(Component.literal(" -").append(c)
                                                .withStyle(Style.EMPTY.withColor(SCColors.TOOLTIP_GRAY)));
                                })
                );

                if (!modComp.isEmpty())
                    //if it's the active rod, add active modifiers, otherwise fishing modifiers
                    if (player != null && stack.is(SCTags.RODS) && (stack == player.getItemInHand(InteractionHand.MAIN_HAND) || stack == player.getItemInHand(InteractionHand.OFF_HAND)))
                    {
                        cachedTimer = -1;
                        comp.add(Tooltips.resolveTagsToComponentFromTranslationKey("tooltip.starcatcher.modifiers.active").withStyle(ChatFormatting.GRAY));
                    }
                    else
                        comp.add(Tooltips.resolveTagsToComponentFromTranslationKey("tooltip.starcatcher.modifiers").withStyle(ChatFormatting.GRAY));

                comp.addAll(modComp);
            }
        }

        //caught fish info
        if (SCDataComponents.has(stack, SCDataComponents.CAUGHT_FISH_INFO))
        {
            SizeAndWeight.Units units = SCConfig.UNIT.get();
            CaughtFishInfo sw = SCDataComponents.get(stack, SCDataComponents.CAUGHT_FISH_INFO);

            if (sw.golden())
            {
                cachedTimer = -1;
                MutableComponent element = Component.empty().append(Tooltips.resolveTagsToComponentFromTranslationKey("gui.guide.rarity.golden")).withStyle(Style.EMPTY.withColor(0x888888));
                if (hasShiftDown)
                    element.append(Component.literal(" (top 0%)").withStyle(Style.EMPTY.withColor(0x707070)));
                comp.add(element);
            }
            else
            {
                String size = units.getSizeAsString(sw.sizeInCentimeters());
                String weight = units.getWeightAsString(sw.weightInGrams());
                String percentile = " (top " + (int) sw.percentile() + "%)";

                MutableComponent element = Component.literal(size + " - " + weight).withStyle(Style.EMPTY.withColor(0x888888));
                if (hasShiftDown)
                    element.append(Component.literal(percentile).withStyle(Style.EMPTY.withColor(0x707070)));
                comp.add(element);
            }
        }

        cachedComps = comp;
        if (!event.getToolTip().isEmpty())
            event.getToolTip().addAll(1, comp);
        else
            event.getToolTip().addAll(comp);
    }
}
