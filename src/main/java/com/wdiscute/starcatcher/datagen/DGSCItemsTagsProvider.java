package com.wdiscute.starcatcher.datagen;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.datagen.fish.DGStarcatcherFishes;
import com.wdiscute.starcatcher.fish.FishProperties;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.wdiscute.starcatcher.registry.SCItems.*;
import static com.wdiscute.starcatcher.registry.SCBlocks.*;

public class DGSCItemsTagsProvider extends ItemTagsProvider
{

    public DGSCItemsTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                 CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, blockTags, Starcatcher.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        //fishes, cat_food, foods/raw_fish
        for (var item : BUCKETABLE_FISHES_REGISTRY.getEntries())
        {
            tag(ItemTags.FISHES).add(item.get());
            tag(ItemTags.CAT_FOOD).add(item.get());
            tag(Tags.Items.FOODS_RAW_FISH).add(item.get());
            tag(SCTags.BUCKETABLE_FISHES).add(item.get());
        }

        for (var item : NON_BUCKETABLE_FISH_REGISTRY.getEntries())
        {
            tag(ItemTags.FISHES).add(item.get());
            tag(ItemTags.CAT_FOOD).add(item.get());
            tag(Tags.Items.FOODS_RAW_FISH).add(item.get());
        }

        //rarity tags for starcatcher fish only
        for (FishProperties fp : DGStarcatcherFishes.STARCATCHER_FISHABLE)
        {
            ResourceLocation key = BuiltInRegistries.ITEM.getKey(fp.catchInfo().fish().toItem());
            tag(SCTags.STARCAUGHT_FISHES).addOptional(key);

            switch (fp.rarity())
            {
                case COMMON -> tag(SCTags.COMMON_FISHES).addOptional(key);
                case UNCOMMON -> tag(SCTags.UNCOMMON_FISHES).addOptional(key);
                case RARE -> tag(SCTags.RARE_FISHES).addOptional(key);
                case EPIC -> tag(SCTags.EPIC_FISHES).addOptional(key);
                case LEGENDARY -> tag(SCTags.LEGENDARY_FISHES).addOptional(key);
            }
        }

        //fishable for tackle box
        for (FishProperties fp : DGStarcatcherFishes.FISHABLE)
        {
            ResourceLocation key = BuiltInRegistries.ITEM.getKey(fp.catchInfo().fish().toItem());
            tag(SCTags.FISHABLE).addOptional(key);
        }


        //crabs
        tag(SCTags.CRABS)
                .add(CHORUS_CRAB.get())
                .add(LAVA_CRAB.get())
                .add(MOLTEN_DEEPSLATE_CRAB.get())
                .add(OBSIDIAN_CRAB.get());

        //eels
        tag(SCTags.EELS)
                .add(BLACK_EEL.get())
                .add(GEODE_EEL.get())
                .add(OBSIDIAN_EEL.get());

        //shrimps
        tag(SCTags.SHRIMPS)
                .add(MOLTEN_SHRIMP.get());

        //worms
        tag(SCTags.WORMS)
                .add(WORM.get())
                .add(ALMIGHTY_WORM.get())
                .add(SEEKING_WORM.get());

        //baits tag
        tag(SCTags.BAITS)
                .add(WORM.get())
                .add(ALMIGHTY_WORM.get())
                .add(SEEKING_WORM.get())
                .add(DEV_WORM.get())
                .add(GUNPOWDER_BAIT.get())
                .add(CHERRY_BAIT.get())
                .add(LUSH_BAIT.get())
                .add(SCULK_BAIT.get())
                .add(DRIPSTONE_BAIT.get())
                .add(MURKWATER_BAIT.get())
                .add(LEGENDARY_BAIT.get())
                .add(METEOROLOGICAL_BAIT.get())
                .add(Items.WITHER_SKELETON_SKULL)
                .addTag(Tags.Items.BUCKETS)

                .addOptional(rl("fishofthieves", "earthworms"))
                .addOptional(rl("fishofthieves", "grubs"))
                .addOptional(rl("fishofthieves", "leeches"))

                .addOptional(rl("tfc", "food/bluegill"))
                .addOptional(rl("tfc", "food/cod"))
                .addOptional(rl("tfc", "food/salmon"))
                .addOptional(rl("tfc", "food/tropical_fish"))
        ;

        //templates tag
        TEMPLATES_REGISTRY.getEntries().forEach(o -> tag(SCTags.TEMPLATES).add(o.get()));

        //tackle skins
        tag(SCTags.TACKLE_SKINS)
                .add(PEARL_SMITHING_TEMPLATE.get())
                .add(KING_SMITHING_TEMPLATE.get())
                .add(COLORFUL_SMITHING_TEMPLATE.get())
                .add(CLEAR_SMITHING_TEMPLATE.get())
                .add(FROG_SMITHING_TEMPLATE.get())
                .add(PEARL_SMITHING_TEMPLATE.get())
        ;

        //Equipment tag
        RODS_REGISTRY.getEntries().forEach(o -> tag(SCTags.EQUIPMENTS).add(o.get()));
        //ModItems.HATS_REGISTRY.getEntries().stream().forEach(o -> tag(StarcatcherTags.EQUIPMENTS).add(o.get()));

        //gadgets
        tag(SCTags.GADGETS).add(FISH_RADAR.get());

        //hooks tag
        HOOKS_REGISTRY.getEntries().forEach(o -> tag(SCTags.HOOKS).add(o.get()));
        tag(SCTags.HOOKS).addOptional(rl("tide", "void_fishing_hook"));

        //bobbers tag
        BOBBERS_REGISTRY.getEntries().forEach(o -> tag(SCTags.BOBBERS).add(o.get()));

        //rods and tools/fishing_rod
        RODS_REGISTRY.getEntries().forEach(o -> tag(SCTags.RODS).add(o.get()));
        RODS_REGISTRY.getEntries().forEach(o -> tag(Tags.Items.TOOLS_FISHING_ROD).add(o.get()));

        tag(SCTags.AQUARIUM_INTERACTIONS)
                .add(Items.DIAMOND_PICKAXE)
                .add(Items.DIAMOND_SHOVEL)
                .add(Items.STONE)
                .add(Items.GRAVEL)
                .add(Items.SAND)
                .add(Items.RED_SAND)
                .add(Items.KELP)
                .add(Items.SEAGRASS)
                .addTag(Tags.Items.BUCKETS)
                .add(AURORA.get())
                .add(CONCH.asItem())
                .add(CLAM.asItem())
        ;

        //hats
        HATS.getEntries().forEach(o -> tag(SCTags.HATS).add(((DeferredBlock<?>) o).asItem()));

        //equippable hats
        tag(ItemTags.EQUIPPABLE_ENCHANTABLE)
                .addTag(SCTags.HATS);

        tag(SCTags.PLACEABLE_IN_DISPLAY)
                .addTag(SCTags.BUCKETABLE_FISHES)
                .add(GUIDE.get())
        ;

        tag(SCTags.PLACEABLE_IN_TACKLE_BOX)
                .addTag(SCTags.BAITS)
                .addTag(SCTags.HOOKS)
                .addTag(SCTags.BOBBERS)
                .addTag(ItemTags.FISHES)
                .addTag(ItemTags.FISHES)
        ;

        tag(SCTags.PLACEABLE_IN_TACKLE_BOX_FISH_SLOT)
                .addTag(ItemTags.FISHES)
        ;


        //tackle boxes
        tag(SCTags.TACKLE_BOXES)
                .add(TACKLE_BOX.asItem())
                .add(TACKLE_BOX_BLACK.asItem())
                .add(TACKLE_BOX_BLUE.asItem())
                .add(TACKLE_BOX_LIGHT_BLUE.asItem())
                .add(TACKLE_BOX_ORANGE.asItem())
                .add(TACKLE_BOX_YELLOW.asItem())
                .add(TACKLE_BOX_RED.asItem())
                .add(TACKLE_BOX_BROWN.asItem())
                .add(TACKLE_BOX_CYAN.asItem())
                .add(TACKLE_BOX_GREEN.asItem())
                .add(TACKLE_BOX_LIME.asItem())
                .add(TACKLE_BOX_GRAY.asItem())
                .add(TACKLE_BOX_LIGHT_GRAY.asItem())
                .add(TACKLE_BOX_PINK.asItem())
                .add(TACKLE_BOX_MAGENTA.asItem())
                .add(TACKLE_BOX_PURPLE.asItem())
                .add(TACKLE_BOX_WHITE.asItem())
        ;

        tag(ItemTags.BOOKSHELF_BOOKS)
                .add(GUIDE.get());

    }


    public static ResourceLocation rl(String ns, String path)
    {
        return ResourceLocation.fromNamespaceAndPath(ns, path);
    }
}
