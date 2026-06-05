package com.wdiscute.starcatcher.io.network;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record FishingStartedPayload(FishProperties fp, MaybeStack treasure, MaybeStack rod) implements CustomPacketPayload {

    public static final Type<FishingStartedPayload> TYPE = new Type<>(Starcatcher.rl("fishing_started"));

    public static final StreamCodec<RegistryFriendlyByteBuf, FishingStartedPayload> STREAM_CODEC = StreamCodec.composite(
            FishProperties.STREAM_CODEC, FishingStartedPayload::fp,
            MaybeStack.STREAM_CODEC, FishingStartedPayload::treasure,
            MaybeStack.STREAM_CODEC, FishingStartedPayload::rod,
            FishingStartedPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }


    public void handle(IPayloadContext context) {
        context.enqueueWork(()-> client(this, context));
    }

    @OnlyIn(Dist.CLIENT)
    public static void client(FishingStartedPayload data, IPayloadContext context) {
        Minecraft.getInstance().setScreen(new FishingMinigameScreen(data.fp(), data.treasure.toStack(), data.rod.toStack()));
    }
}
