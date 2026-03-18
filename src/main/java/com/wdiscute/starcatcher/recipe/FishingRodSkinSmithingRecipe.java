package com.wdiscute.starcatcher.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.SCRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.stream.Stream;

public class FishingRodSkinSmithingRecipe implements SmithingRecipe
{

    public final Ingredient template;
    public final Ingredient base;
    public final Ingredient addition;
    public final ItemStack result;

    public FishingRodSkinSmithingRecipe(Ingredient template, Ingredient base, Ingredient addition, ItemStack result)
    {
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }


    public boolean matches(SmithingRecipeInput input, Level level)
    {
        return this.template.test(input.template())
                && this.base.test(input.base())
                && this.addition.test(input.addition())
                && !input.base().is(result.getItem());
    }

    public ItemStack assemble(SmithingRecipeInput input, HolderLookup.Provider registries)
    {
        return input.base().transmuteCopy(result.getItem());
    }

    @Override
    public boolean isTemplateIngredient(ItemStack stack)
    {
        return this.template.test(stack);
    }

    @Override
    public boolean isBaseIngredient(ItemStack stack)
    {
        return this.base.test(stack);
    }

    @Override
    public boolean isAdditionIngredient(ItemStack stack)
    {
        return this.addition.test(stack);
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries)
    {
        ItemStack itemstack = new ItemStack(SCItems.ROD.get());
        ModDataComponents.set(itemstack, ModDataComponents.NETHERITE_UPGRADE, true);
        return itemstack;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return SCRecipes.FISHING_ROD_SKIN_SMITHING.get();
    }

    @Override
    public RecipeType<?> getType()
    {
        return RecipeType.SMITHING;
    }

    @Override
    public boolean isIncomplete()
    {
        return Stream.of(this.template, this.base, this.addition).anyMatch(Ingredient::hasNoItems);
    }

    public static class Serializer implements RecipeSerializer<FishingRodSkinSmithingRecipe>
    {
        private static final MapCodec<FishingRodSkinSmithingRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                Ingredient.CODEC.fieldOf("template").forGetter((o) -> o.template),
                Ingredient.CODEC.fieldOf("base").forGetter((o) -> o.base),
                Ingredient.CODEC.fieldOf("addition").forGetter((o) -> o.addition),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(o -> o.result)
        ).apply(instance, FishingRodSkinSmithingRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, FishingRodSkinSmithingRecipe> STREAM_CODEC = StreamCodec.of(
                FishingRodSkinSmithingRecipe.Serializer::toNetwork, FishingRodSkinSmithingRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<FishingRodSkinSmithingRecipe> codec()
        {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, FishingRodSkinSmithingRecipe> streamCodec()
        {
            return STREAM_CODEC;
        }

        private static FishingRodSkinSmithingRecipe fromNetwork(RegistryFriendlyByteBuf buffer)
        {
            Ingredient template = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient base = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient addition = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
            return new FishingRodSkinSmithingRecipe(template, base, addition, result);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, FishingRodSkinSmithingRecipe recipe)
        {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.template);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.base);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.addition);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
        }
    }
}
