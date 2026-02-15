package com.wdiscute.starcatcher.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.registry.ModRecipes;
import com.wdiscute.starcatcher.registry.custom.catchmodifiers.ModCatchModifiers;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TackleSkinSmithingRecipe implements SmithingRecipe
{

    public final Ingredient template;
    public final Ingredient base;
    public final Ingredient addition;

    public TackleSkinSmithingRecipe(Ingredient template, Ingredient base, Ingredient addition)
    {
        this.template = template;
        this.base = base;
        this.addition = addition;
    }


    public boolean matches(SmithingRecipeInput input, Level level)
    {
        ResourceLocation rodSkin = ModDataComponents.get(input.base(), ModDataComponents.TACKLE_SKIN);
        ResourceLocation templateSkin = ModDataComponents.get(input.template(), ModDataComponents.TACKLE_SKIN);

        if(rodSkin == null) return false;
        if(templateSkin == null) return false;
        if (rodSkin.equals(templateSkin)) return false;

        return this.template.test(input.template())
                && this.base.test(input.base())
                && this.addition.test(input.addition());
    }

    public ItemStack assemble(SmithingRecipeInput input, HolderLookup.Provider registries)
    {
        ItemStack newRod = input.base().copy();
        ModDataComponents.set(newRod, ModDataComponents.TACKLE_SKIN, ModDataComponents.get(input.template(), ModDataComponents.TACKLE_SKIN));
        return newRod;
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
        ItemStack itemstack = new ItemStack(ModItems.ROD.get());
        ModDataComponents.set(itemstack, ModDataComponents.NETHERITE_UPGRADE, true);
        return itemstack;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.TACKLE_SKIN_SMITHING.get();
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

    public static class Serializer implements RecipeSerializer<TackleSkinSmithingRecipe>
    {
        private static final MapCodec<TackleSkinSmithingRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                Ingredient.CODEC.fieldOf("template").forGetter((o) -> o.template),
                Ingredient.CODEC.fieldOf("base").forGetter((o) -> o.base),
                Ingredient.CODEC.fieldOf("addition").forGetter((o) -> o.addition)
        ).apply(instance, TackleSkinSmithingRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, TackleSkinSmithingRecipe> STREAM_CODEC = StreamCodec.of(
                TackleSkinSmithingRecipe.Serializer::toNetwork, TackleSkinSmithingRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<TackleSkinSmithingRecipe> codec()
        {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, TackleSkinSmithingRecipe> streamCodec()
        {
            return STREAM_CODEC;
        }

        private static TackleSkinSmithingRecipe fromNetwork(RegistryFriendlyByteBuf buffer)
        {
            Ingredient template = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient base = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient addition = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            return new TackleSkinSmithingRecipe(template, base, addition);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, TackleSkinSmithingRecipe recipe)
        {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.template);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.base);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.addition);
        }
    }
}
