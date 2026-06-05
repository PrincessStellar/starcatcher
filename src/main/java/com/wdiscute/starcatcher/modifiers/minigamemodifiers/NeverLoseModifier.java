package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

public class NeverLoseModifier extends AbstractMinigameModifier
{
    public static final MapCodec<NeverLoseModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, NeverLoseModifier::new));

    public NeverLoseModifier(String translationOverride)
    {
        super(translationOverride);
    }

    @Override
    public void tick()
    {
        super.tick();

        if (instance.progressSmooth < 5)
        {
            instance.progressSmooth += instance.hp / 5;
            instance.progress = (float) instance.hp / 5;
        }
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("never_lose");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }
}
