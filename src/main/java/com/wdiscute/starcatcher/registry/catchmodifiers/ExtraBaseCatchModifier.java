package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

public class ExtraBaseCatchModifier extends AbstractCatchModifier
{
    final int count;

    public static final MapCodec<ExtraBaseCatchModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.fieldOf("count").forGetter(o -> o.count),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, ExtraBaseCatchModifier::new));

    public ExtraBaseCatchModifier(int count, String translationOverride)
    {
        super(translationOverride);
        this.count = count;
    }

    @Override
    public DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolder()
    {
        return SCCatchModifiers.EXTRA_BASE_DROPS;
    }

    @Override
    public MapCodec<? extends AbstractCatchModifier> codec()
    {
        return CODEC;
    }
}
