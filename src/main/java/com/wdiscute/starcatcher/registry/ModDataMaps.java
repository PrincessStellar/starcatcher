package com.wdiscute.starcatcher.registry;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.registry.blocks.aquarium.AquariumBlock;
import com.wdiscute.starcatcher.registry.custom.sellingbinprocessor.AbstractSellingBinProcessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

import java.util.List;

public class ModDataMaps {
    public static final DataMapType<Item, AquariumBlock.Interaction> AQUARIUM_INTERACTION = DataMapType.builder(
            Starcatcher.rl("aquarium_interaction"), Registries.ITEM, AquariumBlock.Interaction.CODEC).build();

    public static final DataMapType<Item, List<AbstractSellingBinProcessor>> SELLING_BIN_VALUE = DataMapType.builder(
            Starcatcher.rl("selling_bin_value"), Registries.ITEM, AbstractSellingBinProcessor.ABSTRACT_PROCESSOR_CODEC_LIST).build();

    public static <T> T getOrDefault(ItemStack stack, DataMapType<Item, T> dataMap, T d)
    {
        T data = stack.getItemHolder().getData(dataMap);
        if(data == null) return d;
        return data;
    }
}
