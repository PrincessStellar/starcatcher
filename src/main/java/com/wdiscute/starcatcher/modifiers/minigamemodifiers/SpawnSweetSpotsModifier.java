package com.wdiscute.starcatcher.modifiers.minigamemodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.resources.ResourceLocation;

import java.util.Random;

public class SpawnSweetSpotsModifier extends AbstractTimedModifier
{
    private final Random r = new Random();
    public int cooldown;
    public float chance;
    public Difficulty.SweetSpot sweetSpot;
    public boolean sudokuVanish;

    public static final MapCodec<SpawnSweetSpotsModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.optionalFieldOf("length", -1).forGetter(AbstractTimedModifier::getLength),
                    Codec.INT.fieldOf("cooldown").forGetter(mod -> mod.cooldown),
                    Codec.FLOAT.fieldOf("chance").forGetter(mod -> mod.chance),
                    Difficulty.SweetSpot.CODEC.fieldOf("sweet_spot").forGetter(mod -> mod.sweetSpot),
                    Codec.BOOL.fieldOf("sudoku_vanish").forGetter(mod -> mod.sudokuVanish),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, SpawnSweetSpotsModifier::new));

    public SpawnSweetSpotsModifier(int length, int cooldown, float chance, Difficulty.SweetSpot sweetSpot, boolean sudokuVanish, String translationOverride)
    {
        super(length, translationOverride);
        this.cooldown = cooldown;
        this.chance = chance;
        this.sweetSpot = sweetSpot;
        this.sudokuVanish = sudokuVanish;
    }

    @Override
    public void tick()
    {
        super.tick();
        if (tickCount % cooldown == 0 && r.nextFloat() < chance)
        {
            ActiveSweetSpot activeSweetSpot = new ActiveSweetSpot(instance, sweetSpot);
            instance.addSweetSpot(activeSweetSpot);
        }
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("spawn_sweet_spots");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }
}
