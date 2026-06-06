package com.wdiscute.starcatcher.modifiers;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.*;
import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.compat.curios.CuriosCompat;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.*;
import com.wdiscute.starcatcher.registry.SCDataComponents;
import com.wdiscute.starcatcher.registry.SCDataMaps;
import com.wdiscute.starcatcher.modifiers.catchmodifiers.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;

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

                        if (FMLEnvironment.dist.isClient())
                            LogUtils.getLogger().warn("Modifier [" + rl + "] not found. If this log is in");
                        return EmptyModifier.CODEC;
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
        return getModifiers(itemStack).stream()
                .filter(o -> o instanceof AbstractMinigameModifier)
                .map(o -> (AbstractMinigameModifier) o)
                .toList()
                ;
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

    List<AbstractCatchModifier> BASE_CATCH_MODIFIERS = new ArrayList<>();

    static void registerCatch()
    {
        BASE_CATCH_MODIFIERS.add(new FishMessagesModifier());

        //catch modifier
        Modifier.MODIFIERS.put(Starcatcher.rl("empty"), EmptyModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("adjust_lure_time"), AdjustLureTimeModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("add_loot_table_to_fishing_loot"), AddLootTableToFishedItemsModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("remove_base_fished_item"), RemoveBaseFishedItemModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("override_fish_caught"), OverrideFishPropertiesModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("trigger_skip_minigame"), TriggersSkipMinigameModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("skip_minigame_if_trigger_found"), SkipMinigameIfTriggerFoundModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("new_catch_increase"), NewCatchIncreaseModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("force_fish_entity"), ForceFishEntityModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("extra_base_catch"), ExtraBaseCatchModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("extra_exp_based_on_performance"), ExtraExpBasedOnPerformanceModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("ignore_daytime_restrictions"), IgnoreDaytimeRestrictionsModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("ignore_weather_restrictions"), IgnoreWeatherRestrictionsModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("skip_minigame"), SkipMinigameModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("survives_lava"), SurvivesLavaModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("extra_golden_chance"), ExtraGoldenRiskModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("cancel_golden"), CancelGoldenModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("hide_catch"), HideCatchModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("anglers_hat"), AnglersHatModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("add_to_available_pool"), AddToAvailablePoolModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("modify_award_fish"), ModifyAwardFishRlModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("award_treasure_on_perfect_catch"), AwardTreasureOnPerfectCatch.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("no_gravity"), NoGravityModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("bost_thrown_speed"), BoostThrownSpeedModifier.CODEC);
    }

    List<AbstractMinigameModifier> BASE_MINIGAME_MODIFIERS = new ArrayList<>();

    static void registerMinigame()
    {
        BASE_MINIGAME_MODIFIERS.add(new BaseMinigameModifier());

        //minigame modifier
        Modifier.MODIFIERS.put(Starcatcher.rl("freeze_on_miss"), FreezeOnMissModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("burn_on_miss"), BurnOnMissModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("teleport"), TeleportModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("spawn_treasure_on_hit_x"), SpawnTreasureOnHitX.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("adjust_moving_sweet_spots"), AdjustMovingModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("stop_decay_on_hit"), StoneHookModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("bigger_green_sweet_spots"), SteadyBobberModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("adjust_vanishing_rate"), AdjustVanishingRate.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("nikdo53_modifier"), Nikdo53Modifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("flip_every_hit"), FlipEveryHitModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("spawn_sweet_spots"), SpawnSweetSpotsModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("adjust_handle_speed"), AdjustBaseHandleSpeedModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("add_leaves_spots"), AddLeavesSweetspotsModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("bounce_back"), BounceBackModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("never_lose"), NeverLoseModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("prevent_frozen"), PreventFrozenModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("deep_dark"), DeepDarkModifier.CODEC);
        Modifier.MODIFIERS.put(Starcatcher.rl("add_basic_sweet_spot"), AddBasicSweetSpotModifier.CODEC);
    }
}