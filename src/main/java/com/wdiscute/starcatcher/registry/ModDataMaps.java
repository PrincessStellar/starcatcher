package com.wdiscute.starcatcher.registry;

import com.mojang.serialization.Codec;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.registry.blocks.AquariumBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

public class ModDataMaps {
    public static final DataMapType<Item, AquariumBlock.Decoration> AQUARIUM_DECOR = DataMapType.builder(
            Starcatcher.rl("aquarium_decorations"), Registries.ITEM, AquariumBlock.Decoration.CODEC).build();

}
