package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

public class BurnPointerWhileActiveModifier extends AbstractTimedModifier
{
    public static final ResourceLocation TEXTURE = U.rl("minecraft", "textures/block/fire_0.png");
    private final int rampTime;
    private final float extraSpeed;

    public static final MapCodec<BurnPointerWhileActiveModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.optionalFieldOf("length", -1).forGetter(AbstractTimedModifier::getLength),
                    Codec.INT.fieldOf("ramp_time").forGetter(mod -> mod.rampTime),
                    Codec.FLOAT.fieldOf("extra_speed").forGetter(mod -> mod.extraSpeed)
            ).apply(instance, BurnPointerWhileActiveModifier::new));

    public BurnPointerWhileActiveModifier(int length, int rampTime, float extraSpeed)
    {
        super(length, "");
        this.rampTime = rampTime;
        this.extraSpeed = extraSpeed;
    }

    @Override
    public void onAdd(FishingMinigameScreen instance)
    {
        super.onAdd(instance);
        onMiss();
    }

    @Override
    public void tick()
    {
        super.tick();
        float currentSpeed = instance.handleSpeed;

        float increaseValueEveryTick = Math.abs(extraSpeed) / rampTime;

        float targetSpeed = instance.handleBaseSpeed + extraSpeed;

        //who knows wtf is going on here tbh
        //speed up / starting
        if (tickCount <= rampTime)
        {
            //if the increase is bigger than the target speed, set to target speed, otherwise increase by the value every tick
            instance.handleSpeed = Math.abs(currentSpeed) + increaseValueEveryTick > targetSpeed ? targetSpeed : currentSpeed + Math.signum(currentSpeed) * increaseValueEveryTick;
        }

        //slowdown / ending
        if (tickCount >= length - rampTime)
        {
            float newPointerSpeed = currentSpeed - U.sign(currentSpeed) * increaseValueEveryTick;
            instance.handleSpeed = Math.abs(instance.handleBaseSpeed) < newPointerSpeed ? newPointerSpeed : instance.handleBaseSpeed;
        }

    }

    @Override
    public void onMiss()
    {
        tickCount = 0;
        Minecraft.getInstance().player.playSound(SoundEvents.BLAZE_BURN, 0.9f, 1f);
        Minecraft.getInstance().player.playSound(SoundEvents.BLAZE_SHOOT, 0.3f, 0.6f);
        Minecraft.getInstance().player.playSound(SoundEvents.FIRE_EXTINGUISH, 0.2f, 0.7f);
    }

    @Override
    public void onRemove()
    {
        super.onRemove();
        instance.handleSpeed = instance.handleBaseSpeed;
    }

    @Override
    public void renderForeground(GuiGraphics guiGraphics, float partialTick, int width, int height)
    {
        super.renderForeground(guiGraphics, partialTick, width, height);

        float alpha = 1 - (instance.handleBaseSpeed - instance.handleSpeed / 2) / (instance.handleSpeed - instance.handleSpeed / 2);

        RenderSystem.setShaderColor(1, 1, 1, alpha);
        RenderSystem.enableBlend();
        int yoffset = tickCount % 32;
        guiGraphics.blit(TEXTURE, width / 2 - 8, height / 2 - 8 - 7, 16, 16, 0, yoffset * 16, 16, 16, 16, 512);
        guiGraphics.blit(TEXTURE, width / 2 - 8, height / 2 - 8, 16, 16, 0, (yoffset + 8) * 16, 16, 16, 16, 512);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return null;
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return null;
    }
}
