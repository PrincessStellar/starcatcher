package com.wdiscute.starcatcher.io.network;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.FishProperties;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record CBFishCaughtPayload(FishProperties fp, boolean newFish, int size, int weight,
                                  float percentile) implements CustomPacketPayload
{

    public static final Type<CBFishCaughtPayload> TYPE = new Type<>(Starcatcher.rl("fish_caught"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CBFishCaughtPayload> STREAM_CODEC = StreamCodec.composite(
            FishProperties.STREAM_CODEC,
            CBFishCaughtPayload::fp,
            ByteBufCodecs.BOOL,
            CBFishCaughtPayload::newFish,
            ByteBufCodecs.INT,
            CBFishCaughtPayload::size,
            ByteBufCodecs.INT,
            CBFishCaughtPayload::weight,
            ByteBufCodecs.FLOAT,
            CBFishCaughtPayload::percentile,
            CBFishCaughtPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return TYPE;
    }

    public void handle(IPayloadContext context)
    {
        context.enqueueWork(() ->
        {
            if (fp.hasGuideEntry())
                Starcatcher.fishCaughtToast(fp(), newFish(), size(), weight());
        });
    }
}
