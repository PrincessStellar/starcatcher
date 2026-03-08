package com.wdiscute.starcatcher.registry.custom.sellingbinprocessor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.wdiscute.starcatcher.Starcatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

public abstract class AbstractSellingBinProcessor
{
    public static final Codec<AbstractSellingBinProcessor> ABSTRACT_PROCESSOR_CODEC = ResourceLocation.CODEC
            .dispatch(processor -> processor.getRegistryHolderOrThrow().getId(),
                    loc -> Starcatcher.SELLING_BIN_REGISTRY.get(loc).getCodecOrThrow());

    public static final Codec<List<AbstractSellingBinProcessor>> ABSTRACT_PROCESSOR_CODEC_LIST = ABSTRACT_PROCESSOR_CODEC.listOf();


    public abstract MapCodec<? extends AbstractSellingBinProcessor> codec();

    public abstract DeferredHolder<AbstractSellingBinProcessor, AbstractSellingBinProcessor> getRegistryHolder();

    public DeferredHolder<AbstractSellingBinProcessor, AbstractSellingBinProcessor> getRegistryHolderOrThrow(){
        var holder = getRegistryHolder();
        if (holder == null){
            throw new IllegalStateException("Selling Bin Processor " + this + " does not have a registry holder!");
        }
        return holder;
    }

    public MapCodec<? extends AbstractSellingBinProcessor> getCodecOrThrow(){
        var codec = codec();
        if (codec == null){
            throw new IllegalStateException("Selling Bin Processor " + this + " does not have a codec!");
        }
        return codec;
    }


    abstract public int calculateValue(ItemStack itemStack);

    public void onSellComplete(ItemStack itemStack){}

    public List<AbstractSellingBinProcessor> toList()
    {
        return List.of(this);
    }
}
