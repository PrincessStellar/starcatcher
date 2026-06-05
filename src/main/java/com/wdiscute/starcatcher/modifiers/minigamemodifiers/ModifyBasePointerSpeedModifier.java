package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.resources.ResourceLocation;

public class ModifyBasePointerSpeedModifier extends AbstractMinigameModifier
{
    public float baseSpeedRatio;

    public static final MapCodec<ModifyBasePointerSpeedModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.FLOAT.optionalFieldOf("base_speed_ratio", 0f).forGetter(o -> o.baseSpeedRatio),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
                    ).apply(instance, ModifyBasePointerSpeedModifier::new));

    public ModifyBasePointerSpeedModifier(float baseSpeed, String translationOverride)
    {
        super(translationOverride);
        this.baseSpeedRatio = baseSpeed;
    }

    @Override
    public void onAdd(FishingMinigameScreen instance)
    {
        super.onAdd(instance);
        instance.pointerBaseSpeed = instance.pointerBaseSpeed * baseSpeedRatio;
        instance.pointerSpeed = instance.pointerBaseSpeed;
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("faster_handle_speed");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }
}
