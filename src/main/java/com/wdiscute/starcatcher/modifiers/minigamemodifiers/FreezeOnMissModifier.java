package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class FreezeOnMissModifier extends AbstractMinigameModifier
{
    public static final ResourceLocation OVERLAY = Starcatcher.rl("textures/gui/minigame/modifiers/freeze.png");

    private final int length;
    private final int rampTime;

    public static final MapCodec<FreezeOnMissModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.fieldOf("length").forGetter(o -> o.length),
                    Codec.INT.optionalFieldOf("rampTime", -1).forGetter(o -> o.rampTime),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, FreezeOnMissModifier::new));

    public FreezeOnMissModifier(int length, int rampTime, String translationOverride)
    {
        super(translationOverride);
        this.rampTime = rampTime;
        this.length = length;
    }

    @Override
    public void onMiss()
    {
        super.onMiss();
        instance.addUniqueModifier(new FrozenPointerWhileActiveModifier(length, rampTime));
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, float partialTick, int width, int height)
    {
        super.renderBackground(guiGraphics, partialTick, width, height);
        guiGraphics.blit(
                OVERLAY, width / 2 - 48, height / 2 - 48,
                96, 96, 0, 0, 96, 96, 96, 96);
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("freeze_on_miss");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }
}
