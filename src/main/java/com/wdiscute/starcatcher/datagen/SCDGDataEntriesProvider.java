package com.wdiscute.starcatcher.datagen;

import com.mojang.serialization.Codec;
import com.wdiscute.starcatcher.data.CaughtFishInfo;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.modifiers.catchmodifiers.ExtraGoldenChanceModifier;
import com.wdiscute.starcatcher.modifiers.catchmodifiers.FishMessagesModifier;
import com.wdiscute.starcatcher.modifiers.catchmodifiers.LuckAttributeModifier;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.BaseMinigameModifier;
import com.wdiscute.starcatcher.registry.SCDataComponents;
import com.wdiscute.starcatcher.registry.SCDataEntries;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.utils.Utils;
import com.wdiscute.utils.datagen.DataEntryProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SCDGDataEntriesProvider
{
    public static void start(DataGenerator gen, PackOutput output, boolean includeServer)
    {
        gen.addProvider(includeServer,
                new DataEntryProvider<>(output, SCDataEntries.DIMENSION_TAGS,
                        Map.of(
                                "overworld", List.of(
                                        Utils.rl("overworld")
                                ),

                                "the_nether", List.of(
                                        Utils.rl("the_nether")
                                ),

                                "the_end", List.of(
                                        Utils.rl("the_end")
                                )
                        )
                )
        );

        gen.addProvider(includeServer,
                new DataEntryProvider<>(output, SCDataEntries.DEFAULT_CATCH_MODIFIERS,
                        List.of(
                                new FishMessagesModifier(0.05f, ""),
                                new LuckAttributeModifier(new HashMap<>()
                                {{
                                    put(Rarity.COMMON, 0);
                                    put(Rarity.UNCOMMON, 1);
                                    put(Rarity.RARE, 2);
                                    put(Rarity.EPIC, 3);
                                    put(Rarity.LEGENDARY, 5);
                                }}, "tooltip.modifier.starcatcher.luck_attribute"),
                                new ExtraGoldenChanceModifier(0.01f, false, ""),
                                new ExtraGoldenChanceModifier(0.01f, true, "")
                        )
                )
        );

        gen.addProvider(includeServer, new DataEntryProvider<>(output, SCDataEntries.DEFAULT_MINIGAME_MODIFIERS,
                List.of(new BaseMinigameModifier("")
                ))
        );

        ItemStack stack = SCItems.SEEKING_WORM.toStack();
        SCDataComponents.set(stack, SCDataComponents.CAUGHT_FISH_INFO, new CaughtFishInfo(0, 0, 0, Rarity.COMMON, true));

        gen.addProvider(includeServer, new DataEntryProvider<>(output, SCDataEntries.WORMS,
                List.of(
                        new Utils.Duo<>(SCItems.WORM.toStack(), 149),
                        new Utils.Duo<>(SCItems.ALMIGHTY_WORM.toStack(), 40),
                        new Utils.Duo<>(SCItems.SEEKING_WORM.toStack(), 10),
                        new Utils.Duo<>(stack, 1)
                )
        ));
    }
}
