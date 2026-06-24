package com.wdiscute.starcatcher.datagen;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.modifiers.catchmodifiers.ExtraGoldenChanceModifier;
import com.wdiscute.starcatcher.modifiers.catchmodifiers.FishMessagesModifier;
import com.wdiscute.starcatcher.modifiers.catchmodifiers.LuckAttributeModifier;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.BaseMinigameModifier;
import com.wdiscute.starcatcher.registry.SCDataEntries;
import com.wdiscute.utils.Utils;
import com.wdiscute.utils.datagen.DataEntryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Starcatcher.MOD_ID)
public class SCDataGenerators
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator gen = event.getGenerator();

        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        PackOutput output = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        //fish properties datapack??
        event.createDatapackRegistryObjects(DGSCFishProperties.BUILDER);

        //fish properties
        gen.addProvider(
                event.includeServer(),
                new DGSCFishProperties(output, lookupProvider)
        );

        gen.addProvider(event.includeServer(), new DGSCBiomeModifierProvider(output, lookupProvider));

        //fish models
        gen.addProvider(event.includeServer(), new DGSCItemModelProvider(output, existingFileHelper));

        //block tags
        BlockTagsProvider btp = new DGSCBlocksTagsProvider(output, lookupProvider, existingFileHelper);
        gen.addProvider(event.includeServer(), btp);

        //item tags
        ItemTagsProvider itp = new DGSCItemsTagsProvider(output, lookupProvider, btp.contentsGetter(), existingFileHelper);
        gen.addProvider(event.includeServer(), itp);

        //fp tags todo
        //gen.addProvider(event.includeServer(), new DGSCFPTagsProvider(output, lookupProvider, existingFileHelper));

        //advancements
        gen.addProvider(event.includeServer(), new DGSCAdvancementProvider(output, lookupProvider, existingFileHelper));

        //loot modifiers
        gen.addProvider(event.includeServer(), new DGSCLootModifiers(output, lookupProvider));

        //biome tags
        gen.addProvider(event.includeServer(), new DGSCBiomeTagsProvider(output, lookupProvider, existingFileHelper));

        //loot table
        gen.addProvider(event.includeServer(), new LootTableProvider(output, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(DGSCBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));

        //recipes
        gen.addProvider(event.includeServer(), new DGSCRecipeProvider(output, lookupProvider));

        //data maps
        gen.addProvider(event.includeServer(), new DGSCDataMapsProvider(output, lookupProvider));

        //data entries
        SCDataEntriesProvider.start(gen, output, event.includeServer());

    }
}
