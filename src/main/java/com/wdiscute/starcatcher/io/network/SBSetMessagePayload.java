package com.wdiscute.starcatcher.io.network;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.registry.SCDataComponents;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.secretnotes.LetterItem;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.List;

public record SBSetMessagePayload(List<String> text, String name) implements CustomPacketPayload
{
    public static final Type<SBSetMessagePayload> TYPE = new Type<>(Starcatcher.rl("set_message"));

    public static final StreamCodec<ByteBuf, SBSetMessagePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list()), SBSetMessagePayload::text,
            ByteBufCodecs.STRING_UTF8, SBSetMessagePayload::name,
            SBSetMessagePayload::new
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
            Player player = context.player();

            Level level = player.level();
            if (level instanceof ServerLevel sl)
            {
                ItemStack is = null;
                ItemStack main = player.getMainHandItem();
                ItemStack off = player.getOffhandItem();

                if(main.is(SCItems.LETTER)) is = main;
                if(off.is(SCItems.LETTER)) is = off;
                if(is == null) return;

                LetterItem.Message message = new LetterItem.Message(player.getUUID(), name, level.dimension().location(), text(), false);
                SCDataComponents.set(is, SCDataComponents.MESSAGE, message);
            }

        });
    }
}
