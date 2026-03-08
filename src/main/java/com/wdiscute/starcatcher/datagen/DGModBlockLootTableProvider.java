package com.wdiscute.starcatcher.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.nikdo53.tinymultiblocklib.block.AbstractMultiBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.wdiscute.starcatcher.registry.blocks.ModBlocks.*;

public class DGModBlockLootTableProvider extends BlockLootSubProvider
{
    protected DGModBlockLootTableProvider(HolderLookup.Provider registries)
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate()
    {
        HATS.getEntries().forEach(o -> dropSelf(o.get()));
        TACKLE_BOXES.getEntries().forEach(o -> add(o.get(), createShulkerBoxDrop(o.get())));

        dropSelf(AQUARIUM.get());

        dropSelf(TROPHY_BRONZE.get());
        dropSelf(TROPHY_SILVER.get());
        dropSelf(TROPHY_GOLD.get());

        dropSelf(CLAM.get());
        dropSelf(CONCH.get());

        //selling bin because datagen sucks
        LootTable.Builder builder = LootTable.lootTable().withPool(this.applyExplosionCondition(SELLING_BIN.get(), LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(SELLING_BIN.get()).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(SELLING_BIN.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AbstractMultiBlock.CENTER, true))))));
        add(SELLING_BIN.get(), builder);
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        List<Block> list = new ArrayList<>();
        list.addAll(HATS.getEntries().stream().map(Holder::value).toList());
        list.addAll(TACKLE_BOXES.getEntries().stream().map(Holder::value).toList());

        list.add(TROPHY_BRONZE.get());
        list.add(TROPHY_SILVER.get());
        list.add(TROPHY_GOLD.get());

        list.add(AQUARIUM.get());

        list.add(CLAM.get());
        list.add(CONCH.get());

        list.add(SELLING_BIN.get());
        return list::iterator;
    }
}
