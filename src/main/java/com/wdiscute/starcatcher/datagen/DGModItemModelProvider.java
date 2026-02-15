package com.wdiscute.starcatcher.datagen;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.registry.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.wdiscute.starcatcher.registry.ModItems.*;

public class DGModItemModelProvider extends ItemModelProvider
{
    public DGModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, Starcatcher.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        //bucket fishes
        for (DeferredHolder<Item, ? extends Item> item : ModItems.BUCKETABLE_FISHES_REGISTRY.getEntries())
            simpleItem((DeferredItem<? extends Item>) item);

        //non bucket fishes
        simpleItem(GEODE_EEL);
        simpleItem(OBSIDIAN_EEL);
        simpleItem(MOLTEN_SHRIMP);
        simpleItem(OBSIDIAN_CRAB);
        simpleItem(SCORCHED_BLOODSUCKER);
        simpleItem(MOLTEN_DEEPSLATE_CRAB);
        simpleItem(LAVA_CRAB);
        simpleItem(CINDER_SQUID);
        simpleItem(CHORUS_CRAB);
        simpleItem(CONCH);
        simpleItem(CLAM);

        //trash
        simpleItem(BOOT);
        simpleItem(DRIED_SEAWEED);
        simpleItem(WATERLOGGED_BOTTLE);
        simpleItem(LAVA_CRAB_CLAW);


        //items
        simpleItem(MISSINGNO);
        simpleItem(UNKNOWN_FISH);
        simpleItem(GUIDE);
        simpleItem(FISH_RADAR);
        simpleItem(STARCATCHER_TWINE);
        simpleItem(WATERLOGGED_SATCHEL);
        simpleItem(FISH_BONES);
        simpleItem(STARCAUGHT_BUCKET);
        simpleItem(COOKED_STARCAUGHT_FISH);
        simpleItem(SETTINGS);

        //notes
        simpleItem(SECRET_NOTE);
        simpleItem(BROKEN_BOTTLE);
        simpleItem(DRIFTING_WATERLOGGED_BOTTLE);
        simpleItem(SCALDING_BOTTLE);
        simpleItem(BURNING_BOTTLE);
        simpleItem(HOPEFUL_BOTTLE);
        simpleItem(HOPELESS_BOTTLE);
        simpleItem(TRUE_BLUE_BOTTLE);
        simpleItem(WITHERED_BOTTLE);

        //cheater items
        simpleItem(AWARD_ALL_FISHES);
        simpleItem(AWARD_ONE_FISH);
        simpleItem(REVOKE_ALL_FISHES);
        simpleItem(AWARD_ALL_TROPHIES);
        simpleItem(REVOKE_ALL_TROPHIES);
        simpleItem(AWARD_ALL_SECRETS);
        simpleItem(REVOKE_ALL_SECRETS);
        simpleItem(REVOKE_ALL_EXTRAS);

        //hooks
        simpleItem(HOOK);
        simpleItem(CRYSTAL_HOOK);
        simpleItem(SHINY_HOOK);
        simpleItem(GOLD_HOOK);
        simpleItem(MOSSY_HOOK);
        simpleItem(STONE_HOOK);
        simpleItem(SPLIT_HOOK);
        simpleItem(HEAVY_HOOK);

        //bobbers
        simpleItem(BOBBER);
        simpleItem(STEADY_BOBBER);
        simpleItem(CLEAR_BOBBER);
        simpleItem(AQUA_BOBBER);
        simpleItem(VANILLA_BOBBER);

        //baits
        simpleItem(WORM);
        simpleItem(ALMIGHTY_WORM);
        simpleItem(SEEKING_WORM);
        simpleItem(GUNPOWDER_BAIT);
        simpleItem(CHERRY_BAIT);
        simpleItem(LUSH_BAIT);
        simpleItem(SCULK_BAIT);
        simpleItem(DRIPSTONE_BAIT);
        simpleItem(MURKWATER_BAIT);
        simpleItem(LEGENDARY_BAIT);
        simpleItem(METEOROLOGICAL_BAIT);

        //templates
        simpleItem(PEARL_SMITHING_TEMPLATE);
        simpleItem(KIMBE_SMITHING_TEMPLATE);
        simpleItem(COLORFUL_SMITHING_TEMPLATE);
        simpleItem(CLEAR_SMITHING_TEMPLATE);
        simpleItem(FROG_SMITHING_TEMPLATE);
        simpleItem(KING_SMITHING_TEMPLATE);

        //rods
        //custom model




        //trophies block item
        simpleBlockItem(ModBlocks.TROPHY_BRONZE.get());
        simpleBlockItem(ModBlocks.TROPHY_SILVER.get());
        simpleBlockItem(ModBlocks.TROPHY_GOLD.get());
    }




    private ItemModelBuilder simpleItem(DeferredItem<? extends Item> item)
    {
        return withExistingParent(item.getId().getPath(), mcLoc("item/generated")).texture("layer0", modLoc("item/" + item.getId().getPath()));
    }
}
