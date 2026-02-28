package com.wdiscute.starcatcher.datagen;

import com.wdiscute.starcatcher.Starcatcher;
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
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Starcatcher.MOD_ID)
public class DataGenerators
{

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator gen = event.getGenerator();

        //fish properties
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        PackOutput output = gen.getPackOutput();
        gen.addProvider(
                event.includeServer(),
                new DGFishingPropertiesProvider(output, lookupProvider)
        );

        gen.addProvider(event.includeServer(), new DGBiomeModifierProvider(output, lookupProvider));

        gen.addProvider(event.includeServer(), new DGTrophyPropertiesProvider(output, lookupProvider));

        //fish models
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        gen.addProvider(event.includeServer(), new DGItemModelProvider(output, existingFileHelper));

        //block tags
        BlockTagsProvider btp = new DGModBlocksTagProvider(output, lookupProvider, existingFileHelper);
        gen.addProvider(event.includeServer(), btp);

        //item tags
        ItemTagsProvider itp = new DGModItemsTagProvider(output, lookupProvider, btp.contentsGetter(), existingFileHelper);
        gen.addProvider(event.includeServer(), itp);

        //advancements
        gen.addProvider(event.includeServer(), new DGModAdvancementProvider(output, lookupProvider, existingFileHelper));

        //biome tags
        gen.addProvider(event.includeServer(), new DGBiomeTagsProvider(output, lookupProvider, existingFileHelper));

        //loot table
        gen.addProvider(event.includeServer(), new LootTableProvider(output, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(DGModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));

        //recipes
        gen.addProvider(event.includeServer(), new DGRecipeProvider(output, lookupProvider));

        //data maps
        gen.addProvider(event.includeServer(), new DGDataMapsProvider(output, lookupProvider));

    }
}
