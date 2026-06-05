package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

public class FrozenPointerWhileActiveModifier extends AbstractTimedModifier
{
    private final int rampTime;

    public static final MapCodec<FrozenPointerWhileActiveModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.optionalFieldOf("length", -1).forGetter(AbstractTimedModifier::getLength),
                    Codec.INT.fieldOf("ramp_time").forGetter(mod -> mod.rampTime),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, FrozenPointerWhileActiveModifier::new));

    public FrozenPointerWhileActiveModifier(int length, int rampTime, String translationOverride)
    {
        super(length, translationOverride);
        this.rampTime = rampTime;
    }

    @Override
    public void onAdd(FishingMinigameScreen instance)
    {
        super.onAdd(instance);
        //cancel if any modifiers with the CancelFrozenEffect interface are active
        if(instance.getModifiers().stream().anyMatch(o -> o instanceof CancelFrozenEffect))
            removed = true;
        onMiss();
    }

    @Override
    public void tick()
    {
        super.tick();
        float currentSpeed = instance.pointerSpeed;

        float decreaseTime = Math.abs(instance.pointerBaseSpeed) / rampTime;

        //who knows wtf is going on here tbh
        if(tickCount <= rampTime)
        {
            instance.pointerSpeed = Math.abs(currentSpeed) < decreaseTime ? 0 : currentSpeed - Math.signum(currentSpeed) * decreaseTime;
        }

        if(tickCount >= length - rampTime)
        {
            float newPointerSpeed = currentSpeed + U.sign(currentSpeed) * decreaseTime;
            instance.pointerSpeed = Math.abs(instance.pointerBaseSpeed) < newPointerSpeed ? instance.pointerBaseSpeed : newPointerSpeed;
        }
    }

    @Override
    public void onMiss()
    {
        tickCount = 0;
        Minecraft.getInstance().player.playSound(SoundEvents.GLASS_BREAK, 0.4f, 1f);
        Minecraft.getInstance().player.playSound(SoundEvents.SNOW_BREAK, 1f, 1f);
    }

    @Override
    public void onRemove()
    {
        super.onRemove();
        instance.pointerSpeed = instance.pointerBaseSpeed;
    }

    public static final ResourceLocation FROZEN = Starcatcher.rl("textures/gui/minigame/modifiers/freeze_center.png");

    @Override
    public void renderForeground(GuiGraphics guiGraphics, float partialTick, int width, int height)
    {
        super.renderForeground(guiGraphics, partialTick, width, height);
        RenderSystem.setShaderColor(1, 1, 1, 1 - (instance.pointerSpeed - instance.pointerBaseSpeed / 2) / (instance.pointerBaseSpeed - instance.pointerBaseSpeed / 2));
        RenderSystem.enableBlend();
        guiGraphics.blit(FROZEN, width / 2 - 16, height / 2 - 16, 32, 32, 0, 0, 32, 32, 256, 256);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("frozen_pointer");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }
}
