package com.wdiscute.starcatcher.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.registry.ModRecipes;
import com.wdiscute.starcatcher.registry.custom.catchmodifiers.ModCatchModifiers;
import com.wdiscute.starcatcher.registry.custom.tackleskin.AbstractTackleSkin;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class NetheriteUpgradeSmithingRecipe implements SmithingRecipe
{

    public final Ingredient template;
    public final Ingredient base;
    public final Ingredient addition;

    public NetheriteUpgradeSmithingRecipe(Ingredient template, Ingredient base, Ingredient addition)
    {
        this.template = template;
        this.base = base;
        this.addition = addition;
    }


    public boolean matches(SmithingRecipeInput input, Level level)
    {
        return this.template.test(input.template())
                && this.base.test(input.base())
                && this.addition.test(input.addition())
                && !ModDataComponents.getOrDefault(input.base(), ModDataComponents.NETHERITE_UPGRADE, false);
    }

    public ItemStack assemble(SmithingRecipeInput input, HolderLookup.Provider registries)
    {
        ItemStack newRod = input.base().copy();

        //assemble netherite upgraded rod
        List<ResourceLocation> catchModifiers = ModDataComponents.getOrDefault(newRod, ModDataComponents.CATCH_MODIFIERS, new ArrayList<>());

        catchModifiers.add(ModCatchModifiers.SURVIVES_LAVA.getFirst());

        ModDataComponents.set(newRod, ModDataComponents.CATCH_MODIFIERS, catchModifiers);
        ModDataComponents.set(newRod, ModDataComponents.NETHERITE_UPGRADE, true);
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
        return ModRecipes.FISHING_ROD_SMITHING.get();
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

    public static class Serializer implements RecipeSerializer<NetheriteUpgradeSmithingRecipe>
    {
        private static final MapCodec<NetheriteUpgradeSmithingRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                Ingredient.CODEC.fieldOf("template").forGetter((o) -> o.template),
                Ingredient.CODEC.fieldOf("base").forGetter((o) -> o.base),
                Ingredient.CODEC.fieldOf("addition").forGetter((o) -> o.addition)
        ).apply(instance, NetheriteUpgradeSmithingRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, NetheriteUpgradeSmithingRecipe> STREAM_CODEC = StreamCodec.of(
                NetheriteUpgradeSmithingRecipe.Serializer::toNetwork, NetheriteUpgradeSmithingRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<NetheriteUpgradeSmithingRecipe> codec()
        {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, NetheriteUpgradeSmithingRecipe> streamCodec()
        {
            return STREAM_CODEC;
        }

        private static NetheriteUpgradeSmithingRecipe fromNetwork(RegistryFriendlyByteBuf buffer)
        {
            Ingredient template = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient base = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient addition = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            return new NetheriteUpgradeSmithingRecipe(template, base, addition);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, NetheriteUpgradeSmithingRecipe recipe)
        {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.template);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.base);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.addition);
        }
    }
}
