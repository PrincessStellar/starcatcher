package com.wdiscute.starcatcher.registry.items;

import com.mojang.datafixers.util.Pair;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.registry.custom.catchmodifiers.AbstractCatchModifier;
import com.wdiscute.starcatcher.registry.custom.catchmodifiers.ModCatchModifiers;
import com.wdiscute.starcatcher.registry.custom.minigamemodifiers.ModMinigameModifiers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class HatItem extends BlockItem implements Equipable
{

    public HatItem(Block block, ResourceLocation... modifiers)
    {
        super(block, new Properties()
                .stacksTo(1)
                .component(ModDataComponents.CATCH_MODIFIERS, List.of(modifiers))
        );
    }

    public HatItem(Block block)
    {
        super(block, new Properties()
                .stacksTo(1)
                .component(ModDataComponents.CATCH_MODIFIERS, List.of(ModCatchModifiers.DECREASES_LURE_TIME.getFirst()))
        );
    }

    @Override
    public @NotNull EquipmentSlot getEquipmentSlot()
    {
        return EquipmentSlot.HEAD;
    }
}
