package com.wdiscute.starcatcher.registry;

import com.mojang.serialization.Codec;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.modifiers.Modifier;
import com.wdiscute.utils.DataEntry;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

public interface SCDataEntries
{
    DataEntry<Map<String, List<ResourceLocation>>> DIMENSION_ENTRIES = DataEntry.register(Starcatcher.rl("dimension_entries"),
            Codec.unboundedMap(Codec.STRING, ResourceLocation.CODEC.listOf()),
            Map.of());

    DataEntry<List<Modifier>> DEFAULT_CATCH_MODIFIERS = DataEntry.register(Starcatcher.rl("default_catch_modifiers"), Modifier.CODEC.listOf(),
            List.of());

    DataEntry<List<Modifier>> DEFAULT_MINIGAME_MODIFIERS = DataEntry.register(Starcatcher.rl("default_minigame_modifiers"), Modifier.CODEC.listOf(),
            List.of());


}
