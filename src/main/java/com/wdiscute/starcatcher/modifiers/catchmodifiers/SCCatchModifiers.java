package com.wdiscute.starcatcher.modifiers.catchmodifiers;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.modifiers.Modifier;

public interface SCCatchModifiers
{
    static void register()
    {
        //catch modifier
        Modifier.MODIFIERS.put(Starcatcher.rl("empty"), EmptyCatchModifier.CODEC);
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
    }
}

