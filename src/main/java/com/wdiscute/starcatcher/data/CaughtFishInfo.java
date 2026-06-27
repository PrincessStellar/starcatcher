package com.wdiscute.starcatcher.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.SCConfig;
import com.wdiscute.starcatcher.fish.Rarity;

public record CaughtFishInfo(
        int sizeInCentimeters,
        int weightInGrams,
        float percentile,
        Rarity rarity,
        boolean golden
)
{

    public static final Codec<CaughtFishInfo> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("size").forGetter(CaughtFishInfo::sizeInCentimeters),
                    Codec.INT.fieldOf("weight").forGetter(CaughtFishInfo::weightInGrams),
                    Codec.FLOAT.optionalFieldOf("percentile", 0f).forGetter(CaughtFishInfo::percentile),
                    Rarity.CODEC.optionalFieldOf("rarity", Rarity.COMMON).forGetter(CaughtFishInfo::rarity),
                    Codec.BOOL.optionalFieldOf("golden", false).forGetter(CaughtFishInfo::golden)
            ).apply(instance, CaughtFishInfo::new));

    public float getScale()
    {
        float maxScale = ((float) SCConfig.FISH_MAX_SCALE.getAsDouble());
        float minScale = ((float) SCConfig.FISH_MIN_SCALE.getAsDouble());

        return maxScale - (percentile / 100f) * (maxScale - minScale);
    }

}
