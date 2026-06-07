package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import java.util.List;

public abstract class AbstractMinigameModifier implements Modifier
{
    final String translationOverride;

    @Override
    public final List<Component> getDescription(boolean shift)
    {
        if(translationOverride.equals("hide")) return List.of();

        if(translationOverride.isEmpty())
        {
            return getNonOverriddenDescription(shift);
        }

        return List.of(Component.translatable(translationOverride));
    }

    public List<Component> getNonOverriddenDescription(boolean shift)
    {
        return List.of(Component.translatable("tooltip.modifier." + getIdentifier().toLanguageKey()));
    }

    public void mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY)
    {
    }

    protected AbstractMinigameModifier(String translationOverride)
    {
        this.translationOverride = translationOverride;
    }

    public boolean removed = false;
    public int tickCount = 0;
    protected FishingMinigameScreen instance;

    public void onAdd(FishingMinigameScreen instance)
    {
        this.instance = instance;
    }

    /**
     * Runs when removed or the minigame ends
     */
    public void onRemove()
    {
    }

    /**
     * Transforms an ActiveSweetSpot before it gets added.
     * Setting spot removed to true cancels it
     */
    public ActiveSweetSpot onSpotAdded(ActiveSweetSpot spot)
    {
        return spot;
    }

    /**
     * Runs right before the sweetSpotBehaviour hit
     *
     * @return whether the hit should be cancelled
     */
    public boolean onHit(ActiveSweetSpot ass)
    {
        return false;
    }

    public void onMiss()
    {
    }

    public void tick()
    {
        tickCount++;
    }

    public void onKeyPress(int key, int scanCode, int keyModifiers)
    {
    }

    public boolean shouldDarkenWheel()
    {
        return false;
    }

    public void onKeyReleased(int key, int scanCode, int keyModifiers)
    {
    }

    public void renderBackground(GuiGraphics guiGraphics, float partialTick, int width, int height)
    {
    }

    public void renderForeground(GuiGraphics guiGraphics, float partialTick, int width, int height)
    {
    }

    /**
     * Disables rendering the included pointer
     * <p>
     * Still renders {@link #renderOnPointer(GuiGraphics, PoseStack, float)}
     */
    public boolean disablePointerRendering()
    {
        return false;
    }

    /**
     * Has the correctly rotated poseStack already
     */
    public void renderOnPointer(GuiGraphics guiGraphics, PoseStack poseStack, float partialTick)
    {
    }

    public boolean disableSweetSpotRendering(ActiveSweetSpot spot)
    {
        return false;
    }

    public void renderOnSweetSpot(GuiGraphics guiGraphics, PoseStack poseStack, ActiveSweetSpot spot, float partialTick)
    {
    }

    public boolean forceAwardTreasure()
    {
        return false;
    }

    public boolean skipRenderingKimbeMarker()
    {
        return false;
    }

    public boolean skipHitParticles()
    {
        return false;
    }

    public boolean skipMissSound()
    {
        return false;
    }

    public boolean skipHitSound()
    {
        return false;
    }
}
