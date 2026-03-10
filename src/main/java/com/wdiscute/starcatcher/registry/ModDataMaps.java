package com.wdiscute.starcatcher.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.registry.blocks.aquarium.AquariumBlock;
import com.wdiscute.starcatcher.registry.custom.sellingbinprocessor.AbstractSellingBinProcessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ModDataMaps {
    public static final DataMapType<Item, AquariumBlock.Interaction> AQUARIUM_INTERACTION = DataMapType.builder(
            Starcatcher.rl("aquarium_interaction"), Registries.ITEM, AquariumBlock.Interaction.CODEC).build();

    public static final DataMapType<Item, ItemValue> SELLING_BIN_VALUE = DataMapType.builder(
            Starcatcher.rl("selling_bin_value"), Registries.ITEM, ItemValue.CODEC).build();

    public static final DataMapType<Item, Integer> SELLING_BIN_CURRENCIES = DataMapType.builder(
            Starcatcher.rl("selling_bin_currencies"), Registries.ITEM, Codec.INT).build();


    public static List<Pair<Item, Integer>> getCurrencies()
    {
        //add all currencies from datamap
        Map<ResourceKey<Item>, Integer> dataMap = BuiltInRegistries.ITEM.getDataMap(SELLING_BIN_CURRENCIES);
        List<Pair<Item, Integer>> currenciesUnfiltered = new ArrayList<>();
        dataMap.forEach((i, v) -> currenciesUnfiltered.add(new Pair<>(BuiltInRegistries.ITEM.get(i), v)));

        //remove entries with negative value
        List<Pair<Item, Integer>> currencies = new ArrayList<>(currenciesUnfiltered.stream().filter(o -> o.getB() > 0).toList());

        //if no entries remain, use default of just emeralds
        if(currencies.isEmpty()) return List.of(new Pair<>(Items.EMERALD, 100));

        //sort by lowest value
        currencies.sort(Comparator.comparingInt(Pair::getB));
        return currencies;
    }

    public static <T> T getOrDefault(ItemStack stack, DataMapType<Item, T> dataMap, T d)
    {
        T data = stack.getItemHolder().getData(dataMap);
        if(data == null) return d;
        return data;
    }

    public record ItemValue(int baseValue, List<AbstractSellingBinProcessor> processors)
    {
        public static final Codec<ItemValue> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.INT.fieldOf("base_value").forGetter(ItemValue::baseValue),
                        AbstractSellingBinProcessor.ABSTRACT_PROCESSOR_CODEC_LIST.fieldOf("processors").forGetter(ItemValue::processors)
                ).apply(instance, ItemValue::new));

        public static ItemValue empty()
        {
            return new ItemValue(0, List.of());
        }

        public static ItemValue empty(int baseValue)
        {
            return new ItemValue(baseValue, List.of());
        }

        public ItemValue add(AbstractSellingBinProcessor processor)
        {
            List<AbstractSellingBinProcessor> list = new ArrayList<>(this.processors);
            list.add(processor);
            return new ItemValue(0, list);
        }
    }
}
