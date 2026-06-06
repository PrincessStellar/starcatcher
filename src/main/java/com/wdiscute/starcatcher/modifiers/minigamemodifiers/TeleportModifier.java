package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import org.joml.Quaternionf;

public class TeleportModifier extends AbstractMinigameModifier
{
    public static final ResourceLocation OVERLAY = Starcatcher.rl("textures/gui/minigame/modifiers/teleport.png");

    private float kimbePosition = U.r.nextInt(359);

    public static final MapCodec<TeleportModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, TeleportModifier::new));

    public TeleportModifier(String translationOverride)
    {
        super(translationOverride);
    }

    @Override
    public boolean onHit(ActiveSweetSpot ass)
    {
        Minecraft.getInstance().player.playSound(SoundEvents.ENDERMAN_TELEPORT, 0.6f, 1f);
        instance.addParticles(instance.handlePos, 20, 0x7935de);
        instance.handlePos = kimbePosition;
        kimbePosition = ass.pos;
        return super.onHit(ass);
    }

    @Override
    public boolean skipRenderingKimbeMarker()
    {
        return false;
    }

    @Override
    public boolean skipHitParticles()
    {
        return true;
    }

    @Override
    public boolean skipHitSound()
    {
        return true;
    }

    @Override
    public boolean skipMissSound()
    {
        return true;
    }

    @Override
    public void onMiss()
    {
        super.onMiss();
        Minecraft.getInstance().player.playSound(SoundEvents.SHULKER_AMBIENT, 0.6f, 1f);
        instance.addParticles(instance.handlePos, 20, 0x7935de);
        instance.handlePos = U.r.nextFloat(360);
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
    public void renderForeground(GuiGraphics guiGraphics, float partialTick, int width, int height)
    {
        super.renderForeground(guiGraphics, partialTick, width, height);
        renderKimbeMarker(guiGraphics, width, height);
    }

    public void renderKimbeMarker(GuiGraphics guiGraphics, int width, int height)
    {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();

        float centerX = width / 2f;
        float centerY = height / 2f;

        poseStack.translate(centerX, centerY, 0);
        poseStack.mulPose(new Quaternionf().rotateZ((float) Math.toRadians(kimbePosition + 180)));
        poseStack.translate(-centerX, -centerY, 0);

        RenderSystem.setShaderColor(
                (float) U.intToRed(0x653bea) / 255,
                (float) U.intToGreen(0x653bea) / 255,
                (float) U.intToBlue(0x653bea) / 255,
                0.6f);

        RenderSystem.enableBlend();

        guiGraphics.renderOutline(width / 2, height / 2 + 8, 2, 28, 0xffffffff);


        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.disableBlend();

        poseStack.popPose();
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("teleport");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }
}
