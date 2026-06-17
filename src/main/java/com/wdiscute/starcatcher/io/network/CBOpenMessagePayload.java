package com.wdiscute.starcatcher.io.network;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.message.EditableMessage;
import com.wdiscute.starcatcher.message.Message;
import com.wdiscute.starcatcher.message.MessageScreen;
import com.wdiscute.starcatcher.registry.SCDataComponents;
import com.wdiscute.starcatcher.registry.SCItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.List;

public record CBOpenMessagePayload(Message message) implements CustomPacketPayload
{
    public static final Type<CBOpenMessagePayload> TYPE = new Type<>(Starcatcher.rl("open_message"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CBOpenMessagePayload> STREAM_CODEC = StreamCodec.composite(
            Message.STREAM_CODEC, CBOpenMessagePayload::message,
            CBOpenMessagePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return TYPE;
    }

    public void handle(IPayloadContext context)
    {
        context.enqueueWork(() -> MessageScreen.openMessageScreen(message));
    }
}
