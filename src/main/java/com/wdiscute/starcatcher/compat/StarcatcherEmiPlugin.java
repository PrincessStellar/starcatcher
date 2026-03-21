package com.wdiscute.starcatcher.compat;

import com.wdiscute.sellingbin.SellingBin;
import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.storage.FishProperties;
import com.wdiscute.starcatcher.storage.TrophyProperties;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiInfoRecipe;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;

import java.awt.*;
import java.util.List;
import java.util.function.Supplier;

@EmiEntrypoint
public class StarcatcherEmiPlugin implements EmiPlugin
{
    public static final EmiStack MY_WORKSTATION = EmiStack.of(SCItems.ROD);
    public static final EmiRecipeCategory STARCATCHER_CATEGORY
            = new EmiRecipeCategory(
            Starcatcher.rl("fishing"),
            MY_WORKSTATION);

    @Override
    public void register(EmiRegistry registry)
    {
        // Tell EMI to add a tab for your category
        registry.addCategory(STARCATCHER_CATEGORY);

        // Add all the workstations your category uses
        registry.addWorkstation(STARCATCHER_CATEGORY, MY_WORKSTATION);

        //worms info
        registry.addRecipe(new EmiInfoRecipe(List.of(
                EmiIngredient.of(StarcatcherTags.WORMS)),
                List.of(
                        Component.translatable("emi.info.starcatcher.worms.0"),
                        Component.translatable("emi.info.starcatcher.worms.1"),
                        Component.translatable("emi.info.starcatcher.worms.2")
                ),
                SellingBin.rl("/worms")));


        //todo rework emi starcatcher compat, make a custom multipurpose book-like screen to show a specific fish/trophy restrictions
        Registry<FishProperties> fps = Minecraft.getInstance().level.registryAccess().registryOrThrow(Starcatcher.FISH_REGISTRY);

        for (FishProperties fp : fps)
            registry.addRecipe(new StarcatcherEmiRecipe(fps.getKey(fp), fp));


        Registry<TrophyProperties> trophies = Minecraft.getInstance().level.registryAccess().registryOrThrow(Starcatcher.TROPHY_REGISTRY);

        for (TrophyProperties fp : trophies)
        {
            if (fp.trophyType().equals(TrophyProperties.TrophyType.TROPHY) || fp.trophyType().equals(TrophyProperties.TrophyType.SECRET))
                registry.addRecipe(new StarcatcherEmiRecipe(trophies.getKey(fp), fp));
        }

//        for (SmithingRecipe recipe : getRecipes(registry, RecipeType.SMITHING))
//        {
//            if (recipe instanceof NetheriteUpgradeSmithingRecipe frsr && Arrays.stream(((NetheriteUpgradeSmithingRecipe) recipe).template().getItems()).anyMatch(o -> o.is(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)))
//            {
//                registry.addRecipe(new StarcatcherEmiSmithingRecipe(frsr));
//            }
//        }

        for (Holder<Item> item : BuiltInRegistries.ITEM.getTag(StarcatcherTags.TEMPLATES).get())
        {
            registry.addRecipe(new StarcatcherEmiSmithingRecipe(item.value()));
        }

    }
}
