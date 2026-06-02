package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

public class RemoveBaseFishedItemModifier extends AbstractCatchModifier
{
    public static final MapCodec<RemoveBaseFishedItemModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, RemoveBaseFishedItemModifier::new));

    public RemoveBaseFishedItemModifier(String translationOverride)
    {
        super(translationOverride);
    }

    @Override
    public DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolder()
    {
        return SCCatchModifiers.REMOVE_BASE_FISHED_ITEM;
    }

    @Override
    public MapCodec<? extends AbstractCatchModifier> codec()
    {
        return CODEC;
    }

    @Override
    public void modifyBaseItemStack(ItemStack is)
    {
        is.shrink(999);
    }
}
