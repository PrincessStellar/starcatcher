package com.wdiscute.starcatcher.modifiers;

import com.mojang.serialization.*;
import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.compat.curios.CuriosCompat;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.registry.SCDataComponents;
import com.wdiscute.starcatcher.registry.SCDataMaps;
import com.wdiscute.starcatcher.modifiers.catchmodifiers.*;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.AbstractMinigameModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Modifier
{
    Map<ResourceLocation, MapCodec<? extends Modifier>> MODIFIERS = new HashMap<>();

    List<Component> getDescription(boolean shift);

    ResourceLocation getIdentifier();

    MapCodec<? extends Modifier> getCodec();

    Codec<Modifier> CODEC = ResourceLocation.CODEC
            .dispatch(
                    Modifier::getIdentifier,
                    rl ->
                    {
                        if (MODIFIERS.containsKey(rl))
                            return MODIFIERS.get(rl);

                        throw new IllegalArgumentException("Modifier " + rl + " does not exist");
                    }
            );

    static List<AbstractCatchModifier> getCatchModifiers(Player player)
    {
        return getModifiers(player).stream().filter(o -> o instanceof AbstractCatchModifier).map(o -> (AbstractCatchModifier) o).toList();
    }

    static List<AbstractMinigameModifier> getMinigameModifiers(Player player)
    {
        return getModifiers(player).stream().filter(o -> o instanceof AbstractMinigameModifier).map(o -> (AbstractMinigameModifier) o).toList();
    }

    static List<AbstractCatchModifier> getCatchModifiers(ItemStack itemStack)
    {
        return getModifiers(itemStack).stream().filter(o -> o instanceof AbstractCatchModifier).map(o -> (AbstractCatchModifier) o).toList();
    }

    static List<AbstractMinigameModifier> getMinigameModifiers(ItemStack itemStack)
    {
        return getModifiers(itemStack).stream().filter(o -> o instanceof AbstractMinigameModifier).map(o -> (AbstractMinigameModifier) o).toList();
    }

    static List<Modifier> getModifiers(Player player)
    {
        List<Modifier> modifiers = new ArrayList<>();

        //rod
        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();

        if (main.is(SCTags.RODS))
            modifiers.addAll(getModifiers(main));
        else if (player.getOffhandItem().is(SCTags.RODS))
            modifiers.addAll(getModifiers(off));

        //armor
        player.getInventory().armor.forEach(o -> modifiers.addAll(getModifiers(o)));

        //curios
        if (ModList.get().isLoaded("curios"))
            CuriosCompat.getItems(player).forEach(o -> modifiers.addAll(getModifiers(o)));

        return modifiers;
    }

    static List<Modifier> getModifiers(ItemStack itemStack)
    {
        List<Modifier> modifiers = new ArrayList<>();

        //add all modifiers stored directly in the item, in the data component
        modifiers.addAll(SCDataComponents.getOrDefault(itemStack, SCDataComponents.MODIFIERS, List.of()));

        //add all modifiers from base data map
        modifiers.addAll(SCDataMaps.getOrDefault(itemStack, SCDataMaps.ITEM_MODIFIERS, List.of()));

        //todo add enchants

        //todo add potion effects

        if (!itemStack.is(SCTags.RODS)) return modifiers;

        //if not a rod, add hook bobber and bait slot modifiers too
        var hook = SCDataComponents.getOrDefault(itemStack, SCDataComponents.HOOK, SingleStackContainer.empty()).stack();
        var bait = SCDataComponents.getOrDefault(itemStack, SCDataComponents.BAIT, SingleStackContainer.empty()).stack();
        var bobber = SCDataComponents.getOrDefault(itemStack, SCDataComponents.BOBBER, SingleStackContainer.empty()).stack();

        modifiers.addAll(getModifiers(hook));
        modifiers.addAll(getModifiers(bait));
        modifiers.addAll(getModifiers(bobber));

        return modifiers;
    }
}