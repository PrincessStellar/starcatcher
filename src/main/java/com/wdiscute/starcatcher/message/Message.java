package com.wdiscute.starcatcher.message;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record Message(
        UUID sender,
        String senderDisplayName,
        List<String> text,
        ResourceLocation dimension,
        ResourceLocation background
)
{
    public static final ResourceLocation BACKGROUND_OVERWORLD = Starcatcher.rl("textures/gui/message/message_overworld.png");
    public static final ResourceLocation BACKGROUND_NETHER = Starcatcher.rl("textures/gui/message/message_nether.png");
    public static final ResourceLocation BACKGROUND_END = Starcatcher.rl("textures/gui/message/message_end.png");

    public Message lock()
    {
        return new Message(this.sender, this.senderDisplayName, this.text,
                this.dimension, this.background);
    }

    public static Message createBuiltInMessage(String sender, String translationKey, ResourceLocation background)
    {
        List<String> text = new ArrayList<>();

        for (int i = 0; i < 16; i++)
            text.add("gui.secret_note." + translationKey + "." + i);

        return new Message(
                UUID.nameUUIDFromBytes(sender.getBytes(StandardCharsets.UTF_8)),
                sender,
                text,
                Starcatcher.MISSINGNO,
                background
        );
    }

    public static final Message DEFAULT = new Message(
            UUID.randomUUID(),
            "<sclegendary>-dev (wd)</sclegendary>",
            List.of(
                    "",
                    "",
                    "This is a cheated message from creative.",
                    "",
                    "Usually players would write their own",
                    "message and send it to the ocean for",
                    "others to fish later.",
                    "(5% weight if there is any available)",
                    "",
                    "<scgolden>Also did you know it supports markdown?</scgolden>",
                    "learn more about it in the LibTooltips wiki"
            ),
            Starcatcher.MISSINGNO,
            Starcatcher.MISSINGNO
    );

    public static final Codec<Message> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    UUIDUtil.CODEC.fieldOf("sender").forGetter(Message::sender),
                    Codec.STRING.fieldOf("sender_display_name").forGetter(Message::senderDisplayName),
                    Codec.STRING.listOf().fieldOf("text").forGetter(Message::text),
                    ResourceLocation.CODEC.fieldOf("dimension").forGetter(Message::dimension),
                    ResourceLocation.CODEC.fieldOf("dimension").forGetter(Message::background)
            ).apply(instance, Message::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, Message> STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, Message::sender,
            ByteBufCodecs.STRING_UTF8, Message::senderDisplayName,
            ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list()), Message::text,
            ResourceLocation.STREAM_CODEC, Message::dimension,
            ResourceLocation.STREAM_CODEC, Message::background,
            Message::new
    );
}
