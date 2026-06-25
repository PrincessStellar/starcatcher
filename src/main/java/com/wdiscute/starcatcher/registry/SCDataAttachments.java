package com.wdiscute.starcatcher.registry;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.io.attachments.FishingBobAttachment;
import com.wdiscute.starcatcher.io.attachments.FishingGuideAttachment;
import com.wdiscute.starcatcher.modifiers.Modifier;
import com.wdiscute.starcatcher.modifiers.catchmodifiers.AbstractCatchModifier;
import com.wdiscute.starcatcher.registry.tackleskin.AbstractTackleSkin;
import com.wdiscute.starcatcher.registry.tackleskin.BaseTackleSkin;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.function.Supplier;

public interface SCDataAttachments
{
    DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(
            NeoForgeRegistries.ATTACHMENT_TYPES, Starcatcher.MOD_ID);


    Supplier<AttachmentType<FishingBobAttachment>> FISHING_BOB = ATTACHMENT_TYPES.register(
            "fishing_bob", () -> AttachmentType.builder(() -> new FishingBobAttachment(""))
                    .sync(FishingBobAttachment.STREAM_CODEC)
                    .build()
    );

    Supplier<AttachmentType<ResourceLocation>> TRACKED_FISH = ATTACHMENT_TYPES.register(
            "tracked_fish", () -> AttachmentType.builder(() -> Starcatcher.MISSINGNO)
                    .serialize(ResourceLocation.CODEC)
                    .sync(ResourceLocation.STREAM_CODEC)
                    .build()
    );

    Supplier<AttachmentType<FishingGuideAttachment>> FISHING_GUIDE = ATTACHMENT_TYPES.register(
            "fishing_guide", () -> AttachmentType.builder(FishingGuideAttachment::createDefault)
                    .serialize(FishingGuideAttachment.CODEC)
                    .sync(FishingGuideAttachment.STREAM_CODEC)
                    .copyOnDeath()
                    .build()
    );

    Supplier<AttachmentType<ResourceLocation>> TACKLE_SKIN = ATTACHMENT_TYPES.register(
            "tackle_skin", () ->
                    AttachmentType.builder(() -> Starcatcher.BASE)
                            .serialize(ResourceLocation.CODEC)
                            .sync(ResourceLocation.STREAM_CODEC)
                            .build()
    );

    Supplier<AttachmentType<List<Modifier>>> MODIFIERS = ATTACHMENT_TYPES.register(
            "modifiers", () ->
                    AttachmentType.builder(() -> List.<Modifier>of())
                            .serialize(Modifier.CODEC.listOf())
                            .sync(ByteBufCodecs.fromCodec(Modifier.CODEC).apply(ByteBufCodecs.list()))
                            .build()
    );


    // sets the value to default
    static <T> void remove(Entity holder, Supplier<AttachmentType<T>> attachmentType)
    {
        if(holder == null) return;
        holder.removeData(attachmentType);
    }

    // sets the value to default
    static <T> void remove(Entity holder, AttachmentType<T> attachmentType)
    {
        if(holder == null) return;
        holder.removeData(attachmentType);
    }

    static <T> void set(Entity holder, Supplier<AttachmentType<T>> attachmentType, T data)
    {
        if(holder == null) return;
        holder.setData(attachmentType, data);
    }

    static <T> void set(Entity holder, AttachmentType<T> attachmentType, T data)
    {
        if(holder == null) return;
        holder.setData(attachmentType, data);
    }

    static <T> T get(Entity holder, Supplier<AttachmentType<T>> attachmentType)
    {
        if(holder == null) throw new RuntimeException("Called Starcatcher DataAttachments Get() with a null entity");
        return holder.getData(attachmentType);
    }

    static <T> T get(Entity holder, AttachmentType<T> attachmentType)
    {
        if(holder == null) throw new RuntimeException("Called Starcatcher DataAttachments Get() with a null entity");
        return holder.getData(attachmentType);
    }

    static void register(IEventBus eventBus)
    {
        ATTACHMENT_TYPES.register(eventBus);
    }

}
