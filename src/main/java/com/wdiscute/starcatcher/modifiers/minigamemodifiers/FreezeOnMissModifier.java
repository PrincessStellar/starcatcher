package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class FreezeOnMissModifier extends AbstractTimedModifier
{
    public static final ResourceLocation OVERLAY = Starcatcher.rl("textures/gui/minigame/modifiers/freeze.png");

    public static final MapCodec<FreezeOnMissModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.optionalFieldOf("length", -1).forGetter(AbstractTimedModifier::getLength),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, FreezeOnMissModifier::new));

    public FreezeOnMissModifier(int length, String translationOverride)
    {
        super(length, translationOverride);
    }

    @Override
    public void onMiss()
    {
        super.onMiss();
        instance.addUniqueModifier(new FrozenPointerWhileActiveModifier(40, 10, ""));
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
