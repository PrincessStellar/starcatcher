package com.wdiscute.starcatcher.datagen;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.registry.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.wdiscute.starcatcher.registry.SCItems.*;
import static com.wdiscute.starcatcher.registry.blocks.ModBlocks.*;

public class DGItemModelProvider extends ItemModelProvider
{
    public DGItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, Starcatcher.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        //bucket fishes
        for (DeferredHolder<Item, ? extends Item> item : BUCKETABLE_FISHES_REGISTRY.getEntries())
            simpleItem((DeferredItem<? extends Item>) item);

        //non bucket fishes
        simpleItem(BLACK_EEL);
        simpleItem(GEODE_EEL);
        simpleItem(OBSIDIAN_EEL);
        simpleItem(MOLTEN_SHRIMP);
        simpleItem(OBSIDIAN_CRAB);
        simpleItem(SCORCHED_BLOODSUCKER);
        simpleItem(MOLTEN_DEEPSLATE_CRAB);
        simpleItem(LAVA_CRAB);
        simpleItem(CINDER_SQUID);
        simpleItem(CHORUS_CRAB);

        //trash
        simpleItem(BOOT);
        simpleItem(DRIED_SEAWEED);
        simpleItem(LAVA_CRAB_CLAW);
        simpleItem(MOSSY_BOOT);

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

        //notes & messages
        simpleItem(LETTER);
        simpleItem(BOTTLED_LETTER);

        simpleItem(MESSAGE_IN_A_BOTTLE);
        simpleItem(MESSAGE);

        simpleItem(BROKEN_BOTTLE);

        simpleItem(SECRET_NOTE);
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
        simpleItem(DEV_WORM);
        simpleItem(GUNPOWDER_BAIT);
        simpleItem(CHERRY_BAIT);
        simpleItem(LUSH_BAIT);
        simpleItem(SCULK_BAIT);
        simpleItem(DRIPSTONE_BAIT);
        simpleItem(MURKWATER_BAIT);
        simpleItem(LEGENDARY_BAIT);
        simpleItem(METEOROLOGICAL_BAIT);

        //templates
        TEMPLATES_REGISTRY.getEntries().forEach(o -> simpleItem(((DeferredItem) o)));

        //rods
        //custom model



        //trophies block item
        simpleBlockItem(ModBlocks.TROPHY_BRONZE.get());
        simpleBlockItem(ModBlocks.TROPHY_SILVER.get());
        simpleBlockItem(ModBlocks.TROPHY_GOLD.get());

        simpleItem(DeferredItem.createItem(Starcatcher.rl("clam")));
        simpleItem(DeferredItem.createItem(Starcatcher.rl("conch")));

        //hats model, just parents to block
        simpleBlockItem(FISHERMAN_HAT_WHITE.get());
        simpleBlockItem(FISHERMAN_HAT_LIME.get());
        simpleBlockItem(FISHERMAN_HAT_ORANGE.get());
        simpleBlockItem(FISHERMAN_HAT_RED.get());
        simpleBlockItem(FISHERMAN_HAT_GRAY.get());
        simpleBlockItem(FISHERMAN_HAT_LIGHT_GRAY.get());
        simpleBlockItem(FISHERMAN_HAT_BLACK.get());
        simpleBlockItem(FISHERMAN_HAT_BROWN.get());
        simpleBlockItem(FISHERMAN_HAT_YELLOW.get());
        simpleBlockItem(FISHERMAN_HAT_PINK.get());
        simpleBlockItem(FISHERMAN_HAT_MAGENTA.get());
        simpleBlockItem(FISHERMAN_HAT_PURPLE.get());
        simpleBlockItem(FISHERMAN_HAT_BLUE.get());
        simpleBlockItem(FISHERMAN_HAT_LIGHT_BLUE.get());
        simpleBlockItem(FISHERMAN_HAT_CYAN.get());
        simpleBlockItem(FISHERMAN_HAT_GREEN.get());
        
        //tacklebox
        simpleBlockItem(TACKLE_BOX.get());
        simpleBlockItem(TACKLE_BOX_WHITE.get());
        simpleBlockItem(TACKLE_BOX_LIME.get());
        simpleBlockItem(TACKLE_BOX_ORANGE.get());
        simpleBlockItem(TACKLE_BOX_RED.get());
        simpleBlockItem(TACKLE_BOX_GRAY.get());
        simpleBlockItem(TACKLE_BOX_LIGHT_GRAY.get());
        simpleBlockItem(TACKLE_BOX_BLACK.get());
        simpleBlockItem(TACKLE_BOX_BROWN.get());
        simpleBlockItem(TACKLE_BOX_YELLOW.get());
        simpleBlockItem(TACKLE_BOX_PINK.get());
        simpleBlockItem(TACKLE_BOX_MAGENTA.get());
        simpleBlockItem(TACKLE_BOX_PURPLE.get());
        simpleBlockItem(TACKLE_BOX_BLUE.get());
        simpleBlockItem(TACKLE_BOX_LIGHT_BLUE.get());
        simpleBlockItem(TACKLE_BOX_CYAN.get());
        simpleBlockItem(TACKLE_BOX_GREEN.get());

    }




    private ItemModelBuilder simpleItem(DeferredItem<? extends Item> item)
    {
        return withExistingParent(item.getId().getPath(), mcLoc("item/generated")).texture("layer0", modLoc("item/" + item.getId().getPath()));
    }
}
