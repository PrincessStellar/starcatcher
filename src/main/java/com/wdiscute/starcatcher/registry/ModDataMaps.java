package com.wdiscute.starcatcher.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.registry.blocks.aquarium.AquariumBlock;
import com.wdiscute.starcatcher.registry.custom.sellingbinprocessor.AbstractSellingBinProcessor;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

import java.util.List;

public class ModDataMaps {
    public static final DataMapType<Item, AquariumBlock.Interaction> AQUARIUM_INTERACTION = DataMapType.builder(
            Starcatcher.rl("aquarium_interaction"), Registries.ITEM, AquariumBlock.Interaction.CODEC).build();

    public static final DataMapType<Item, AbstractSellingBinProcessor.Instance> SELLING_BIN_VALUE = DataMapType.builder(
            Starcatcher.rl("selling_bin_value"), Registries.ITEM, AbstractSellingBinProcessor.Instance.CODEC).build();

    public static <T> T getOrDefault(ItemStack stack, DataMapType<Item, T> dataMap, T d)
    {
        T data = stack.getItemHolder().getData(dataMap);
        if(data == null) return d;
        return data;
    }
}
