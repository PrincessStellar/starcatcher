package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.resources.ResourceLocation;

public class StoneHookModifier extends AbstractTimedModifier
{
    public static final MapCodec<StoneHookModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.optionalFieldOf("length", -1).forGetter(AbstractTimedModifier::getLength),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, StoneHookModifier::new));

    public StoneHookModifier(int length, String translationOverride)
    {
        super(length, translationOverride);
    }

    @Override
    public boolean onHit(ActiveSweetSpot ass)
    {
        instance.gracePeriod = instance.rarity.getStoneHookGraceTicks();
        ;

        return super.onHit(ass);
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("stop_decay_on_hit");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }
}
