package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.compat.curios.CuriosCompat;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.registry.SCDataComponents;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.registry.SCDataMaps;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public interface SCCatchModifiers
{
    DeferredRegister<AbstractCatchModifier> REGISTRY =
            DeferredRegister.create(Starcatcher.CATCH_MODIFIERS_REGISTRY, Starcatcher.MOD_ID);

    //todo built-in modifiers to skip minigame for low rarity or something, using AbstractCatchModifier#forceSkipMinigame


    //empty
    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> EMPTY = register("empty",
            new EmptyCatchModifier(""));


    //every bait
    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> ADJUST_LURE_TIME = register("adjust_lure_time",
            new AdjustLureTimeModifier(0.7f, 0.8f, 1.3f, ""));


    //vanilla bobber
    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> ADD_LOOT_TABLE_TO_FISHING_LOOT = register("add_loot_table_to_fishing_loot",
            new AddLootTableToFishedItemsModifier(U.rl("gameplay/fishing"), ""));

    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> REMOVE_BASE_FISHED_ITEM = register("remove_base_fished_item",
            new RemoveBaseFishedItemModifier(""));

    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> MODIFY_AWARD_FISH = register("modify_award_fish",
            new ModifyAwardFishRlModifier(Starcatcher.rl("missingno"), ""));

    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> OVERRIDE_FISH_CAUGHT = register("override_fish_caught",
            new OverrideFishPropertiesModifier(FishProperties.empty().withFish(new MaybeStack(Starcatcher.rl("unknown_fish"))), ""));

    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> TRIGGER_SKIP_MINIGAME = register("trigger_skip_minigame",
            new TriggersSkipMinigameModifier(""));


    //vanilla hook
    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> SKIP_MINIGAME_IF_TRIGGER_FOUND =
            register("skip_minigame_if_trigger_found", new SkipMinigameIfTriggerFoundModifier(""));


    //seeking worm
    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> NEW_CATCH_INCREASE =
            register("new_catch_increase", new NewCatchIncreaseModifier(1, ""));


    //almighty worm
    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> FORCE_FISH_ENTITY =
            register("force_fish_entity", new ForceFishEntityModifier(1, ""));


    //split hook
    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> EXTRA_BASE_CATCH =
            register("extra_base_catch", new ExtraBaseCatchModifier(1, false, ""));


    //gold hook
    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> EXTRA_EXP_BASED_ON_PERFORMANCE =
            register("extra_exp_based_on_performance", new ExtraExpBasedOnPerformanceModifier(""));


    //meteorological bait
    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> IGNORE_DAYTIME_RESTRICTION =
            register("ignore_daytime_restrictions", new IgnoreDaytimeRestrictionsModifier(1, ""));

    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> IGNORE_WEATHER_RESTRICTION =
            register("ignore_weather_restrictions", new IgnoreWeatherRestrictionsModifier(1, ""));


    //skip minigame
    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> SKIP_MINIGAME =
            register("skip_minigame", new SkipMinigameModifier(""));


    //survives lava
    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> SURVIVES_LAVA =
            register("survives_lava", new SurvivesLavaModifier(""));


    //golden
    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> EXTRA_GOLDEN_CHANCE =
            register("extra_golden_chance", new ExtraGoldenRiskModifier(1f, false, ""));

    //cancel golden
    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> CANCEL_GOLDEN =
            register("cancel_golden", new CancelGoldenModifier(""));

    //hide catch
    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> HIDE_CATCH =
            register("hide_catch", new HideCatchModifier(1, ""));


    //angler's hat (artifacts / reliquified artifacts compat)
    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> ANGLERS_HAT =
            register("anglers_hat", new AnglersHatModifier(""));

    //lime hat
    DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> ADD_TO_AVAILABLE_POOL = register("add_to_available_pool",
            new AddToAvailablePoolModifier(FishProperties.empty(), Starcatcher.rl("missingno"), 0, ""));


    static DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> register(String name, AbstractCatchModifier sup)
    {
        return REGISTRY.register(name, () -> sup);
    }

    static void register(IEventBus eventBus)
    {
        REGISTRY.register(eventBus);
    }

    static List<AbstractCatchModifier> getCatchModifiers(Player player)
    {
        List<AbstractCatchModifier> modifiers = new ArrayList<>();

        //rod
        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();

        if (main.is(SCTags.RODS))
            modifiers.addAll(getCatchModifiers(main));
        else if (player.getOffhandItem().is(SCTags.RODS))
            modifiers.addAll(getCatchModifiers(off));

        //armor
        player.getInventory().armor.forEach(o -> modifiers.addAll(getCatchModifiers(o)));

        //curios
        if (ModList.get().isLoaded("curios"))
        {
            CuriosCompat.getItems(player).forEach(o -> modifiers.addAll(getCatchModifiers(o)));
        }

        return modifiers;
    }

    static List<AbstractCatchModifier> getCatchModifiers(ItemStack itemStack)
    {
        List<AbstractCatchModifier> modifiers = new ArrayList<>(SCDataComponents.getOrDefault(itemStack, SCDataComponents.CATCH_MODIFIERS, List.of()));

        modifiers.addAll(SCDataMaps.getOrDefault(itemStack, SCDataMaps.CATCH_MODIFIERS, List.of()));

        if (!itemStack.is(SCTags.RODS)) return modifiers;

        //if not a rod, add hook bobber and bait slot modifiers too
        var hook = SCDataComponents.getOrDefault(itemStack, SCDataComponents.HOOK, SingleStackContainer.empty()).stack();
        var bait = SCDataComponents.getOrDefault(itemStack, SCDataComponents.BAIT, SingleStackContainer.empty()).stack();
        var bobber = SCDataComponents.getOrDefault(itemStack, SCDataComponents.BOBBER, SingleStackContainer.empty()).stack();

        modifiers.addAll(SCDataMaps.getOrDefault(hook, SCDataMaps.CATCH_MODIFIERS, List.of()));
        modifiers.addAll(SCDataMaps.getOrDefault(bait, SCDataMaps.CATCH_MODIFIERS, List.of()));
        modifiers.addAll(SCDataMaps.getOrDefault(bobber, SCDataMaps.CATCH_MODIFIERS, List.of()));

        modifiers.addAll(SCDataComponents.getOrDefault(hook, SCDataComponents.CATCH_MODIFIERS, List.of()));
        modifiers.addAll(SCDataComponents.getOrDefault(bait, SCDataComponents.CATCH_MODIFIERS, List.of()));
        modifiers.addAll(SCDataComponents.getOrDefault(bobber, SCDataComponents.CATCH_MODIFIERS, List.of()));



        return modifiers;
    }
}

