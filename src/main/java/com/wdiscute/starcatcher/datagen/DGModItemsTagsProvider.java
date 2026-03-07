package com.wdiscute.starcatcher.datagen;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.registry.fishing.DGStarcatcherFishes;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.core.HolderLookup;
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

import static com.wdiscute.starcatcher.registry.ModItems.*;
import static com.wdiscute.starcatcher.registry.blocks.ModBlocks.*;

public class DGModItemsTagsProvider extends ItemTagsProvider
{

    public DGModItemsTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
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
            tag(StarcatcherTags.BUCKETABLE_FISHES).add(item.get());
            tag(StarcatcherTags.STARCAUGHT_FISHES).add(item.get());
        }

        //todo figure out what to do with crabs/eels tags?

        //rarity tags
        for (FishProperties fp : DGStarcatcherFishes.STARCATCHER_FISHES)
        {
            switch (fp.rarity())
            {
                case COMMON -> tag(StarcatcherTags.COMMON_FISHES).add(fp.catchInfo().fish().value());
                case UNCOMMON -> tag(StarcatcherTags.UNCOMMON_FISHES).add(fp.catchInfo().fish().value());
                case RARE -> tag(StarcatcherTags.RARE_FISHES).add(fp.catchInfo().fish().value());
                case EPIC -> tag(StarcatcherTags.EPIC_FISHES).add(fp.catchInfo().fish().value());
                case LEGENDARY -> tag(StarcatcherTags.LEGENDARY_FISHES).add(fp.catchInfo().fish().value());
            }
        }


        //baits tag
        tag(StarcatcherTags.BAITS)
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
                .add(Items.BUCKET)

                .addOptional(rl("fishofthieves", "earthworms"))
                .addOptional(rl("fishofthieves", "grubs"))
                .addOptional(rl("fishofthieves", "leeches"))

                .addOptional(rl("tfc", "food/bluegill"))
                .addOptional(rl("tfc", "food/cod"))
                .addOptional(rl("tfc", "food/salmon"))
                .addOptional(rl("tfc", "food/tropical_fish"))
        ;

        //templates tag
        TEMPLATES_REGISTRY.getEntries().forEach(o -> tag(StarcatcherTags.TEMPLATES).add(o.get()));

        //Equipment tag
        RODS_REGISTRY.getEntries().forEach(o -> tag(StarcatcherTags.EQUIPMENTS).add(o.get()));
        //ModItems.HATS_REGISTRY.getEntries().stream().forEach(o -> tag(StarcatcherTags.EQUIPMENTS).add(o.get()));

        //gadgets
        tag(StarcatcherTags.GADGETS).add(FISH_RADAR.get());

        //hooks tag
        HOOKS_REGISTRY.getEntries().forEach(o -> tag(StarcatcherTags.HOOKS).add(o.get()));
        tag(StarcatcherTags.HOOKS).addOptional(rl("tide", "void_fishing_hook"));

        //bobbers tag
        BOBBERS_REGISTRY.getEntries().forEach(o -> tag(StarcatcherTags.BOBBERS).add(o.get()));

        //rods and tools/fishing_rod
        RODS_REGISTRY.getEntries().forEach(o -> tag(StarcatcherTags.RODS).add(o.get()));
        RODS_REGISTRY.getEntries().forEach(o -> tag(Tags.Items.TOOLS_FISHING_ROD).add(o.get()));

        //hats
        HATS.getEntries().forEach(o -> tag(StarcatcherTags.HATS).add(((DeferredBlock<?>) o).asItem()));

        //equippable hats
        tag(ItemTags.EQUIPPABLE_ENCHANTABLE)
                .addTag(StarcatcherTags.HATS);

        tag(StarcatcherTags.PLACEABLE_IN_TACKLE_BOX)
                .addTag(StarcatcherTags.BAITS)
                .addTag(StarcatcherTags.HOOKS)
                .addTag(StarcatcherTags.BOBBERS)
                .addTag(ItemTags.FISHES)
                .addTag(StarcatcherTags.COMMON_FISHES)
                .addTag(StarcatcherTags.UNCOMMON_FISHES)
                .addTag(StarcatcherTags.RARE_FISHES)
                .addTag(StarcatcherTags.EPIC_FISHES)
                .addTag(StarcatcherTags.LEGENDARY_FISHES)
        ;

    }


    public static ResourceLocation rl(String ns, String path)
    {
        return ResourceLocation.fromNamespaceAndPath(ns, path);
    }
}
