package com.wdiscute.starcatcher.registry.tackleskin;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public class PearlTackleSkin extends AbstractTackleSkin
{
    @Override
    public void onCast(Player player)
    {
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.NEUTRAL, 1F, 1f);
    }

    @Override
    public void onSuccessfulMinigame(Player player)
    {
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.NEUTRAL, 2F, 1f);
    }
}
