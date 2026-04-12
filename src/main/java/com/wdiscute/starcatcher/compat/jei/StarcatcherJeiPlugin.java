package com.wdiscute.starcatcher.compat.jei;

import com.wdiscute.sellingbin.SellingBin;
import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.blocks.SCBlocks;
import com.wdiscute.starcatcher.registry.FishProperties;
import com.wdiscute.starcatcher.registry.SCItems;
import dev.emi.emi.api.recipe.EmiInfoRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.vanilla.IJeiIngredientInfoRecipe;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.crafting.IngredientType;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class StarcatcherJeiPlugin implements IModPlugin
{
    public static final ResourceLocation ARROW = SellingBin.rl("textures/gui/selling_bin/selling_bin_background.png");
    public static final ResourceLocation SLOT_BACKGROUND = SellingBin.rl("textures/gui/slot_background.png");

    public static List<StarcatcherJeiFPRecipe.Recipe> listRecipes = new ArrayList<>();

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration)
    {
        listRecipes.clear();

        //add all fps as a recipe to the list
        Registry<FishProperties> fpRegistry = Minecraft.getInstance().level.registryAccess().registryOrThrow(Starcatcher.FISH_REGISTRY_KEY);
        fpRegistry.stream().forEach(fp -> listRecipes.add(StarcatcherJeiFPRecipe.Recipe.of(fp)));

        //register categories
        registration.addRecipeCategories(new StarcatcherJeiFPRecipe(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        //add sellables recipes
        registration.addRecipes(StarcatcherJeiFPRecipe.Recipe.TYPE, listRecipes);

        //worms info
        registration.addItemStackInfo(
                BuiltInRegistries.ITEM.getTag(SCTags.WORMS).get()
                        .stream().map(o -> o.value().getDefaultInstance()).toList(),
                Component.translatable("emi.info.starcatcher.worms.0"),
                Component.translatable("emi.info.starcatcher.worms.1"),
                Component.translatable("emi.info.starcatcher.worms.2")
        );

        //clams info
        registration.addItemStackInfo(List.of(SCItems.PEARL.get().getDefaultInstance(),
                SCBlocks.CLAM.get().asItem().getDefaultInstance()),
                Component.translatable("emi.info.starcatcher.pearls.0"),
                Component.translatable("emi.info.starcatcher.pearls.1")
        );

        //hooks, baits, bobbers
        List<ItemStack> attachments = new ArrayList<>();
        attachments.addAll(BuiltInRegistries.ITEM.getTag(SCTags.HOOKS).get()
                .stream().map(o -> o.value().getDefaultInstance()).toList());

        attachments.addAll(BuiltInRegistries.ITEM.getTag(SCTags.WORMS).get()
                .stream().map(o -> o.value().getDefaultInstance()).toList());

        attachments.addAll(BuiltInRegistries.ITEM.getTag(SCTags.BAITS).get()
                .stream().map(o -> o.value().getDefaultInstance()).toList());

        registration.addItemStackInfo(attachments,
                        Component.translatable("emi.info.starcatcher.attachments.0"),
                        Component.translatable("emi.info.starcatcher.attachments.1")
        );

        //fine bones info
        registration.addItemStackInfo(SCItems.FISH_BONES.get().getDefaultInstance(),
                Component.translatable("emi.info.starcatcher.fish_bones.0")
        );

    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {

        registration.addRecipeCatalyst(SCItems.ROD.get(), StarcatcherJeiFPRecipe.Recipe.TYPE);
    }

    @Override
    public ResourceLocation getPluginUid()
    {
        return SellingBin.rl("selling_bin_jei_plugin");
    }
}
