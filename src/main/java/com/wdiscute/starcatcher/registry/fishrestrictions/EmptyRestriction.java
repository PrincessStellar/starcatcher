package com.wdiscute.starcatcher.registry.fishrestrictions;

import com.mojang.serialization.MapCodec;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.antlr.v4.runtime.misc.Triple;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EmptyRestriction extends AbstractFishRestriction
{

    public static final MapCodec<EmptyRestriction> CODEC = MapCodec.unit(EmptyRestriction::new);

    @Override
    public MapCodec<? extends AbstractFishRestriction> codec()
    {
        return CODEC;
    }

    @Override
    public DeferredHolder<AbstractFishRestriction, AbstractFishRestriction> getRegistryHolder()
    {
        return SCFishRestrictions.EMPTY;
    }

    @Override
    public boolean isEnabled()
    {
        return false;
    }

    @Override
    public int getFishChance(int currentChance, Level level, FishProperties fp, @NotNull Entity entity, ItemStack rod, Context context)
    {
        return 0;
    }
}
