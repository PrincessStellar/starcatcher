package com.wdiscute.starcatcher.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.io.FishCaughtCounter;
import net.minecraft.core.UUIDUtil;

import java.util.UUID;

public record SignedGuide(UUID owner, FishCaughtCounter fcc, String signature)
{
    public static final Codec<SignedGuide> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    UUIDUtil.CODEC.fieldOf("uuid").forGetter(SignedGuide::owner),
                    FishCaughtCounter.CODEC.fieldOf("fish_caught_counter").forGetter(SignedGuide::fcc),
                    Codec.STRING.fieldOf("signature").forGetter(SignedGuide::signature)
            ).apply(instance, SignedGuide::new)
    );


}
