package com.wdiscute.starcatcher.datagen;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DGRecipeProvider extends RecipeProvider
{
    public DGRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output)
    {
        //guide
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, SCItems.GUIDE)
                .requires(SCItems.ROD)
                .requires(Items.BOOK)
                .unlockedBy("in_water", insideOf(Blocks.WATER))
                .save(output);

        //rod
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, SCItems.ROD)
                .define('S', Items.STICK)
                .define('B', SCItems.BOBBER)
                .define('H', SCItems.HOOK)
                .define('T', SCItems.STARCATCHER_TWINE)
                .pattern("  S")
                .pattern(" ST")
                .pattern("SHB")
                .unlockedBy("in_water", insideOf(Blocks.WATER))
                .save(output);

        //rod from vanilla
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, SCItems.ROD)
                .requires(Items.FISHING_ROD)
                .requires(SCItems.HOOK)
                .requires(SCItems.BOBBER)
                .requires(SCItems.STARCATCHER_TWINE)
                .unlockedBy("in_water", insideOf(Blocks.WATER))
                .save(output, Starcatcher.rl("rod_from_vanilla"));

        //dripstone bait
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.DRIPSTONE_BAIT, 4)
                .requires(Items.BONE_MEAL)
                .requires(Items.DRIPSTONE_BLOCK)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.DRIPSTONE_BAIT, 4)
                .requires(Items.BONE_MEAL)
                .requires(Items.POINTED_DRIPSTONE)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output, Starcatcher.rl("dripstone_bait_from_pointed_dripstone"));

        //murkwater bait
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.MURKWATER_BAIT, 4)
                .requires(Items.BONE_MEAL)
                .requires(Items.MANGROVE_LEAVES)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.MURKWATER_BAIT, 4)
                .requires(Items.BONE_MEAL)
                .requires(Items.LILY_PAD)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output, Starcatcher.rl("murkwater_bait_from_lilypad"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.MURKWATER_BAIT, 4)
                .requires(Items.BONE_MEAL)
                .requires(Items.MANGROVE_ROOTS)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output, Starcatcher.rl("murkwater_bait_from_mangrove_roots"));

        //cherry bait
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.CHERRY_BAIT, 4)
                .requires(Items.BONE_MEAL)
                .requires(Items.PINK_PETALS)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //gunpowder bait
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.GUNPOWDER_BAIT, 4)
                .requires(Items.BONE_MEAL)
                .requires(Items.GUNPOWDER)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //lush bait
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.LUSH_BAIT, 4)
                .requires(Items.BONE_MEAL)
                .requires(Items.MOSS_BLOCK)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.LUSH_BAIT, 2)
                .requires(Items.BONE_MEAL)
                .requires(Items.MOSS_CARPET)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output, Starcatcher.rl("lust_bait_from_moss_carpet"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.LUSH_BAIT, 8)
                .requires(Items.BONE_MEAL)
                .requires(SCItems.MOSSY_BOOT)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output, Starcatcher.rl("lust_bait_from_mossy_boot"));

        //moss block from mossy boot
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.MOSS_BLOCK, 1)
                .requires(SCItems.MOSSY_BOOT)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //sculk
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.SCULK_BAIT, 4)
                .requires(Items.BONE_MEAL)
                .requires(Items.SCULK)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.SCULK_BAIT, 16)
                .requires(Items.BONE_MEAL)
                .requires(Items.SCULK_CATALYST)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output, Starcatcher.rl("sculk_bait_from_sculk_catalyst"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.SCULK_BAIT, 16)
                .requires(Items.BONE_MEAL)
                .requires(SCItems.SCULKFISH)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output, Starcatcher.rl("sculk_bait_from_sculkfish"));

        //legendary bait
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.LEGENDARY_BAIT, 4)
                .requires(Items.BONE_MEAL)
                .requires(Items.GOLDEN_APPLE)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.LEGENDARY_BAIT, 64)
                .requires(Items.BONE_MEAL)
                .requires(Items.ENCHANTED_GOLDEN_APPLE)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output, Starcatcher.rl("legendary_bait_from_enchanted_golden_apple"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.LEGENDARY_BAIT, 16)
                .requires(Items.BONE_MEAL)
                .requires(StarcatcherTags.LEGENDARY_FISHES)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output, Starcatcher.rl("legendary_bait_from_legendary_fish"));

        //meteorological
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.METEOROLOGICAL_BAIT, 32)
                .requires(Items.BONE_MEAL)
                .requires(Items.HEART_OF_THE_SEA)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.METEOROLOGICAL_BAIT, 8)
                .requires(Items.BONE_MEAL)
                .requires(StarcatcherTags.EPIC_FISHES)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output, Starcatcher.rl("meteorological_bait_from_epic_fishes"));

        //hats
        colorBlockWithDye(output, dyes, hats, "fisherman_hats");

        //bobber
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCItems.BOBBER)
                .define('P', ItemTags.PLANKS)
                .define('W', ItemTags.WOOL)
                .define('S', Items.STICK)
                .pattern(" PS")
                .pattern("PWP")
                .pattern("SP ")
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //steady bobber
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCItems.STEADY_BOBBER)
                .define('B', SCItems.BOBBER)
                .define('S', Items.STICK)
                .define('I', Items.IRON_INGOT)
                .define('C', Items.COPPER_INGOT)
                .pattern(" IS")
                .pattern("CBC")
                .pattern("SC ")
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //clear bobber
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCItems.CLEAR_BOBBER)
                .define('B', SCItems.BOBBER)
                .define('S', Items.STICK)
                .define('G', Items.GLASS)
                .pattern(" GS")
                .pattern("GBG")
                .pattern("SG ")
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //aqua bobber
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCItems.AQUA_BOBBER)
                .define('B', SCItems.BOBBER)
                .define('S', Items.STICK)
                .define('D', Items.DIAMOND)
                .define('H', Items.HEART_OF_THE_SEA)
                .pattern(" HS")
                .pattern("DBD")
                .pattern("SD ")
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //vanilla bobber
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCItems.VANILLA_BOBBER)
                .define('B', SCItems.BOBBER)
                .define('S', Items.STICK)
                .define('W', Items.RED_WOOL)
                .pattern(" WS")
                .pattern("WBW")
                .pattern("SW ")
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //vanilla bobber from rod
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, SCItems.VANILLA_BOBBER)
                .requires(Items.FISHING_ROD)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output, Starcatcher.rl("vanilla_bobber_from_vanilla_fishing_rod"));

        //hook
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCItems.HOOK)
                .define('I', Items.IRON_INGOT)
                .define('N', Items.IRON_NUGGET)
                .pattern("N  ")
                .pattern("I I")
                .pattern(" I ")
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //crystal hook
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCItems.CRYSTAL_HOOK)
                .define('H', SCItems.HOOK)
                .define('G', Items.GLASS)
                .define('D', Items.DIAMOND)
                .pattern("D  ")
                .pattern("GHG")
                .pattern(" G ")
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //shiny hook
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCItems.SHINY_HOOK)
                .define('H', SCItems.HOOK)
                .define('I', Items.IRON_NUGGET)
                .define('D', Items.DIAMOND)
                .pattern("I  ")
                .pattern("DHD")
                .pattern(" D ")
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //gold hook
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCItems.GOLD_HOOK)
                .define('H', SCItems.HOOK)
                .define('G', Items.GOLD_INGOT)
                .define('N', Items.GOLD_NUGGET)
                .pattern("N  ")
                .pattern("GHG")
                .pattern(" G ")
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //mossy
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCItems.MOSSY_HOOK)
                .define('H', SCItems.HOOK)
                .define('M', Items.MOSS_BLOCK)
                .define('N', Items.IRON_NUGGET)
                .pattern("N  ")
                .pattern("MHM")
                .pattern(" M ")
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //stone hook
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCItems.STONE_HOOK)
                .define('H', SCItems.HOOK)
                .define('S', Items.STONE)
                .define('N', Items.IRON_NUGGET)
                .pattern("N  ")
                .pattern("SHS")
                .pattern(" S ")
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //split hook
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCItems.SPLIT_HOOK)
                .define('H', SCItems.HOOK)
                .define('C', Items.CHAIN)
                .define('N', Items.IRON_NUGGET)
                .pattern("N  ")
                .pattern("CHC")
                .pattern(" C ")
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //heavy hook
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCItems.HEAVY_HOOK)
                .define('H', SCItems.HOOK)
                .define('I', Items.IRON_BLOCK)
                .define('N', Items.IRON_NUGGET)
                .pattern("N  ")
                .pattern("IHI")
                .pattern(" I ")
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //stand
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STAND)
                .define('G', Items.BOOK)
                .define('P', ItemTags.PLANKS)
                .define('B', Items.BARREL)
                .pattern(" G ")
                .pattern("PPP")
                .pattern(" B ")
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //twine
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SCItems.STARCATCHER_TWINE)
                .define('S', Items.STICK)
                .define('T', Items.STRING)
                .pattern(" T ")
                .pattern("TST")
                .pattern(" T ")
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output);

        //bonemeal from clam
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BONE_MEAL, 4)
                .requires(ModBlocks.CLAM)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output, Starcatcher.rl("bone_meal_from_clam"));

        //bonemeal from conch
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BONE_MEAL, 4)
                .requires(ModBlocks.CONCH)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output, Starcatcher.rl("bone_meal_from_conch"));

        //bonemeal from fishbones
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BONE_MEAL, 4)
                .requires(SCItems.FISH_BONES)
                .unlockedBy("has_starcatcher_rod", has(StarcatcherTags.RODS))
                .save(output, Starcatcher.rl("bone_meal_from_fish_bones"));

        //cooked fish
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(StarcatcherTags.STARCAUGHT_FISHES), RecipeCategory.FOOD, SCItems.COOKED_STARCAUGHT_FISH, 0.35F, 200)
                .unlockedBy("has_starcaught_fish", has(StarcatcherTags.STARCAUGHT_FISHES))
                .save(output, Starcatcher.rl("starcaught_fish_from_smelting"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(StarcatcherTags.STARCAUGHT_FISHES), RecipeCategory.FOOD, SCItems.COOKED_STARCAUGHT_FISH, 0.35F, 600)
                .unlockedBy("has_starcaught_fish", has(StarcatcherTags.STARCAUGHT_FISHES))
                .save(output, Starcatcher.rl("starcaught_fish_from_campfire"));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(StarcatcherTags.STARCAUGHT_FISHES), RecipeCategory.FOOD, SCItems.COOKED_STARCAUGHT_FISH, 0.35F, 100)
                .unlockedBy("has_starcaught_fish", has(StarcatcherTags.STARCAUGHT_FISHES))
                .save(output, Starcatcher.rl("starcaught_fish_from_smoking"));


    }

    protected static void colorBlockWithDye(RecipeOutput recipeOutput, List<Item> dyes, List<Item> dyeableItems, String group) {
        for(int i = 0; i < dyes.size(); ++i) {
            Item item = dyes.get(i);
            Item item1 = dyeableItems.get(i);
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, item1).requires(item).requires(Ingredient.of(dyeableItems.stream().filter((p_288265_) -> !p_288265_.equals(item1)).map(ItemStack::new))).group(group).unlockedBy("has_needed_dye", has(item)).save(recipeOutput, "dye_" + getItemName(item1));
        }

    }

    List<Item> dyes = List.of(
            Items.BLACK_DYE,
            Items.BLUE_DYE,
            Items.BROWN_DYE,
            Items.CYAN_DYE,
            Items.GRAY_DYE,
            Items.GREEN_DYE,
            Items.LIGHT_BLUE_DYE,
            Items.LIGHT_GRAY_DYE,
            Items.LIME_DYE,
            Items.MAGENTA_DYE,
            Items.ORANGE_DYE,
            Items.PINK_DYE,
            Items.PURPLE_DYE,
            Items.RED_DYE,
            Items.YELLOW_DYE,
            Items.WHITE_DYE
    );

    List<Item> hats = List.of(
            ModBlocks.FISHERMAN_HAT_BLACK.asItem(),
            ModBlocks.FISHERMAN_HAT_BLUE.asItem(),
            ModBlocks.FISHERMAN_HAT_BROWN.asItem(),
            ModBlocks.FISHERMAN_HAT_CYAN.asItem(),
            ModBlocks.FISHERMAN_HAT_GRAY.asItem(),
            ModBlocks.FISHERMAN_HAT_GREEN.asItem(),
            ModBlocks.FISHERMAN_HAT_LIGHT_BLUE.asItem(),
            ModBlocks.FISHERMAN_HAT_LIGHT_GRAY.asItem(),
            ModBlocks.FISHERMAN_HAT_LIME.asItem(),
            ModBlocks.FISHERMAN_HAT_MAGENTA.asItem(),
            ModBlocks.FISHERMAN_HAT_ORANGE.asItem(),
            ModBlocks.FISHERMAN_HAT_PINK.asItem(),
            ModBlocks.FISHERMAN_HAT_PURPLE.asItem(),
            ModBlocks.FISHERMAN_HAT_RED.asItem(),
            ModBlocks.FISHERMAN_HAT_YELLOW.asItem(),
            ModBlocks.FISHERMAN_HAT_WHITE.asItem()
    );
}
