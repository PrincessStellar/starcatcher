package com.wdiscute.starcatcher.registry.items;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.io.SCDataComponents;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.registry.catchmodifiers.SCCatchModifiers;
import com.wdiscute.starcatcher.registry.items.rod.StarcatcherFishingRodItem;
import com.wdiscute.starcatcher.registry.minigamemodifiers.SCMinigameModifiers;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RodSlotTooltipRenderer implements ClientTooltipComponent
{
    public static final ResourceLocation TEXTURE = Starcatcher.rl("textures/gui/rod_tooltip.png");
    public static final ResourceLocation BOBBER = Starcatcher.rl("textures/item/background/bobber_white.png");
    public static final ResourceLocation BAIT = Starcatcher.rl("textures/item/background/bait_white.png");
    public static final ResourceLocation HOOK = Starcatcher.rl("textures/item/background/hook_white.png");

    ItemStack bobber;
    ItemStack bait;
    ItemStack hook;

    List<Component> components;

    int width;


    public StarcatcherFishingRodItem.RodSlotTooltip tooltip;

    public RodSlotTooltipRenderer(StarcatcherFishingRodItem.RodSlotTooltip tooltip)
    {
        this.tooltip = tooltip;
        ItemStack rod = tooltip.rod();
        bobber = SCDataComponents.getOrDefault(rod, SCDataComponents.BOBBER, SingleStackContainer.empty()).stack();
        bait = SCDataComponents.getOrDefault(tooltip.rod(), SCDataComponents.BAIT, SingleStackContainer.empty()).stack();
        hook = SCDataComponents.getOrDefault(tooltip.rod(), SCDataComponents.HOOK, SingleStackContainer.empty()).stack();

        components = new ArrayList<>();

        if(!Screen.hasShiftDown()) return;

        List<ResourceLocation> modifiers = new ArrayList<>();

        SCDataComponents.getSlotsInRod(rod).forEach(o -> modifiers.addAll(SCCatchModifiers.getCatchModifiersRLs(o)));
        SCDataComponents.getSlotsInRod(rod).forEach(o -> modifiers.addAll(SCMinigameModifiers.getMinigameModifiersRLs(o)));

        int maxWidth = 0;

        if (!modifiers.isEmpty())
        {
            //components.add(Component.translatable("tooltip.starcatcher.modifiers").withStyle(ChatFormatting.GRAY));

            for (ResourceLocation rl : modifiers)
            {
                for (int i = 0; i < 100; i++)
                {
                    if (I18n.exists("tooltip.modifier." + rl.toLanguageKey() + "." + i))
                    {
                        MutableComponent start = i == 0 ? Component.literal("- ") : Component.literal("");
                        MutableComponent comp = start.append(Component.translatable("tooltip.modifier." + rl.toLanguageKey() + "." + i));
                        components.add(comp.withStyle(ChatFormatting.DARK_GRAY));
                        maxWidth = Math.max(Minecraft.getInstance().font.width(comp), maxWidth);
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
        width = maxWidth;
    }

    @Override
    public int getHeight()
    {
        return 21 + components.size() * 10;
    }

    @Override
    public int getWidth(Font font)
    {
        return Math.max(56, width);
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics)
    {
        //todo render modifiers if holding shift
        guiGraphics.blit(TEXTURE, x, y, 0, 0, 56, 19, 56, 19);


        if (bobber.isEmpty())
            guiGraphics.blit(BOBBER, x + 2, y + 1, 0, 0, 16, 16, 16, 16);
        else
            guiGraphics.renderItem(bobber, x + 2, y + 1);

        if (bait.isEmpty())
            guiGraphics.blit(BAIT, x + 18 + 2, y + 1, 0, 0, 16, 16, 16, 16);
        else
            guiGraphics.renderItem(bait, x + 18 + 2, y + 1);

        if (hook.isEmpty())
            guiGraphics.blit(HOOK, x + 18 + 18 + 2, y + 1, 0, 0, 16, 16, 16, 16);
        else
            guiGraphics.renderItem(hook, x + 18 + 18 + 2, y + 1);


        for (int i = 0; i < components.size(); i++)
        {
            guiGraphics.drawString(Minecraft.getInstance().font, components.get(i), x, y + 20 + (i * 10), 5592405);
        }
    }
}
