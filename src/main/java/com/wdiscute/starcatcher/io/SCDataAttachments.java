package com.wdiscute.starcatcher.io;

import com.mojang.serialization.Codec;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.io.attachments.FishingBobAttachment;
import com.wdiscute.starcatcher.io.attachments.FishingGuideAttachment;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.function.Supplier;

public class SCDataAttachments
{
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(
            NeoForgeRegistries.ATTACHMENT_TYPES, Starcatcher.MOD_ID);


    public static final Supplier<AttachmentType<FishingBobAttachment>> FISHING_BOB = ATTACHMENT_TYPES.register(
            "fishing_bob", () -> AttachmentType.builder(() -> new FishingBobAttachment(""))
                    .sync(FishingBobAttachment.STREAM_CODEC)
                    .build()
    );


    public static final Supplier<AttachmentType<FishingGuideAttachment>> FISHING_GUIDE = ATTACHMENT_TYPES.register(
            "fishing_guide", () -> AttachmentType.builder(FishingGuideAttachment::createDefault)
                    .serialize(FishingGuideAttachment.CODEC)
                    .sync(FishingGuideAttachment.STREAM_CODEC)
                    .copyOnDeath()
                    .build()
    );


    @Deprecated // use FISHING_GUIDE attachment!!!
    public static final Supplier<AttachmentType<Boolean>> RECEIVED_GUIDE = ATTACHMENT_TYPES.register(
            "received_guide", () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL)
                    .sync(ByteBufCodecs.BOOL)
                    .build()
    );

    @Deprecated // use FISHING_GUIDE attachment!!!
    public static final Supplier<AttachmentType<List<LegacyFishCaughtCounter>>> FISHES_CAUGHT = ATTACHMENT_TYPES.register(
            "fishes_caught", () ->
                    AttachmentType.builder(() -> List.<LegacyFishCaughtCounter>of())
                            .serialize(LegacyFishCaughtCounter.LIST_CODEC)
                            .sync(LegacyFishCaughtCounter.LIST_STREAM_CODEC)
                            .copyOnDeath()
                            .build()
    );

    @Deprecated  // use FISHING_GUIDE attachment!!!
    public static final Supplier<AttachmentType<List<ResourceLocation>>> TROPHIES_CAUGHT = ATTACHMENT_TYPES.register(
            "trophies_caught", () ->
                    AttachmentType.builder(() -> List.<ResourceLocation>of())
                            .serialize(ResourceLocation.CODEC.listOf())
                            .sync(ResourceLocation.STREAM_CODEC.apply(ByteBufCodecs.list()))
                            .copyOnDeath()
                            .build()
    );

    @Deprecated // use FISHING_GUIDE attachment!!!
    public static final Supplier<AttachmentType<List<ResourceLocation>>> FISHES_NOTIFICATION = ATTACHMENT_TYPES.register(
            "fishes_notification", () ->
                    AttachmentType.builder(() -> List.<ResourceLocation>of())
                            .serialize(ResourceLocation.CODEC.listOf())
                            .sync(ResourceLocation.STREAM_CODEC.apply(ByteBufCodecs.list()))
                            .copyOnDeath()
                            .build()
    );


    public static final Supplier<AttachmentType<ResourceLocation>> TACKLE_SKIN = ATTACHMENT_TYPES.register(
            "tackle_skin", () ->
                    AttachmentType.builder(() -> Starcatcher.rl("base"))
                            .serialize(ResourceLocation.CODEC)
                            .sync(ResourceLocation.STREAM_CODEC)
                            .build()
    );


    // sets the value to default
    public static <T> void remove(Entity holder, Supplier<AttachmentType<T>> attachmentType)
    {
        if(holder == null) return;
        holder.removeData(attachmentType);
    }

    // sets the value to default
    public static <T> void remove(Entity holder, AttachmentType<T> attachmentType)
    {
        if(holder == null) return;
        holder.removeData(attachmentType);
    }

    public static <T> void set(Entity holder, Supplier<AttachmentType<T>> attachmentType, T data)
    {
        if(holder == null) return;
        holder.setData(attachmentType, data);
    }

    public static <T> void set(Entity holder, AttachmentType<T> attachmentType, T data)
    {
        if(holder == null) return;
        holder.setData(attachmentType, data);
    }

    public static <T> T get(Entity holder, Supplier<AttachmentType<T>> attachmentType)
    {
        if(holder == null) throw new RuntimeException("Called Starcatcher DataAttachments Get() with a null entity");
        return holder.getData(attachmentType);
    }

    public static <T> T get(Entity holder, AttachmentType<T> attachmentType)
    {
        if(holder == null) throw new RuntimeException("Called Starcatcher DataAttachments Get() with a null entity");
        return holder.getData(attachmentType);
    }

    public static void register(IEventBus eventBus)
    {
        ATTACHMENT_TYPES.register(eventBus);
    }

}
