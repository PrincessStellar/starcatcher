package com.wdiscute.starcatcher.datagen;

import com.wdiscute.starcatcher.Starcatcher;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Starcatcher.MOD_ID)
public class DataGenerators
{

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator gen = event.getGenerator();

        //fish properties
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();
        PackOutput output = gen.getPackOutput();
        gen.addProvider(
                event.includeServer(),
                new DGFishingPropertiesProvider(output, registries)
        );

        gen.addProvider(event.includeServer(), new DGModRegistryProvider(output, registries));

        //fish models
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        gen.addProvider(event.includeServer(), new DGModItemModelProvider(output, existingFileHelper));

        //block tags
        BlockTagsProvider btp = new DGModBlocksTagProvider(output, registries, existingFileHelper);
        gen.addProvider(event.includeServer(), btp);

        //fish tags
        ItemTagsProvider itp = new DGModItemsTagProvider(output, registries, btp.contentsGetter(), existingFileHelper);
        gen.addProvider(event.includeServer(), itp);

        gen.addProvider(event.includeServer(), new DGModAdvancementProvider(output, registries, existingFileHelper));
    }
}
