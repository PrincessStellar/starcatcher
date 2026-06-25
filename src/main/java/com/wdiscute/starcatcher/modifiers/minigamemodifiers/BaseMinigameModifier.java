package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class BaseMinigameModifier extends AbstractMinigameModifier
{
    public static final MapCodec<BaseMinigameModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.STRING.optionalFieldOf("translation_override", "").forGetter(o -> o.translationOverride)
            ).apply(instance, BaseMinigameModifier::new));

    public BaseMinigameModifier(String translationOverride)
    {
        super(translationOverride);
    }

    @Override
    public void onMiss(FishingMinigameScreen instance)
    {
        super.onMiss(instance);
        //kimbe marker
        instance.kimbeMarkerAlpha = 1;
        //You have to make the actual texture white before trying to recolor like this, dummy
        instance.kimbeMarkerColor = 0xff6767;
        instance.kimbeMarkerPos = instance.getPointerPosPrecise();

        //refresh all vanishes
        instance.refreshSweetSpotsAlphas();

        instance.perfectCatch = false;

        instance.consecutiveHits = 0;
    }

    @Override
    public boolean onHit(FishingMinigameScreen instance, ActiveSweetSpot ass)
    {
        instance.kimbeMarkerAlpha = 1;
        instance.kimbeMarkerColor = 0x2ce17d;
        instance.kimbeMarkerPos = instance.getPointerPosPrecise();

        instance.consecutiveHits++;

        if (U.r.nextFloat() > 0.98 && !instance.treasureActive && instance.treasureProgress == 0)
        {
            instance.addSweetSpot(new ActiveSweetSpot(instance, Difficulty.SweetSpot.TREASURE));
        }

        return super.onHit(instance, ass);
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("base");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }

    @Override
    public String toString()
    {
        return "[BaseModifier@" + Integer.toHexString(hashCode()) + "]";
    }
}
