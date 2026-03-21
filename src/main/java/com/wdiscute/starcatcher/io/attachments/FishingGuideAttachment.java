package com.wdiscute.starcatcher.io.attachments;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.io.FishCaughtCounter;
import com.wdiscute.starcatcher.io.SCDataAttachments;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

public class FishingGuideAttachment {
    public Map<ResourceLocation, FishCaughtCounter> fishesCaught;
    public Map<ResourceLocation, Integer> trophiesCaught;
    public boolean receivedGuide;

    public FishingGuideAttachment(Map<ResourceLocation, FishCaughtCounter> fishesCaught, Map<ResourceLocation, Integer> trophiesCaught, boolean receivedGuide ) {
        this.fishesCaught = new HashMap<>(fishesCaught); //guarantees the map is mutable
        this.trophiesCaught = new HashMap<>(trophiesCaught);
        this.receivedGuide = receivedGuide;
    }

    public static FishingGuideAttachment createDefault() {
        return new FishingGuideAttachment(
                new HashMap<>(),
                new HashMap<>(),
                false);
    }

    public static final Codec<FishingGuideAttachment> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.unboundedMap(ResourceLocation.CODEC, FishCaughtCounter.CODEC).fieldOf("fishes_caught").forGetter(data -> data.fishesCaught),
                    Codec.unboundedMap(ResourceLocation.CODEC, Codec.INT).fieldOf("trophies_caught").forGetter(data -> data.trophiesCaught),
                    Codec.BOOL.lenientOptionalFieldOf("received_guide", false).forGetter(data -> data.receivedGuide)
            ).apply(instance, FishingGuideAttachment::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, FishingGuideAttachment> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.map(HashMap::new, ResourceLocation.STREAM_CODEC, FishCaughtCounter.STREAM_CODEC), data -> data.fishesCaught,
            ByteBufCodecs.map(HashMap::new, ResourceLocation.STREAM_CODEC, ByteBufCodecs.INT), data -> data.trophiesCaught,
            ByteBufCodecs.BOOL, data -> data.receivedGuide,

            FishingGuideAttachment::new
    );

    public static Map<ResourceLocation, Integer> getTrophiesCaught(Player player) {
        return get(player).trophiesCaught;
    }

    public static void setTrophiesCaught(Player player, Map<ResourceLocation, Integer> trophiesCaught) {
        get(player).trophiesCaught = trophiesCaught;
        sync(player);
    }

    public static Map<ResourceLocation, FishCaughtCounter> getFishesCaught(Player player) {
        return get(player).fishesCaught;
    }

    public static void setFishesCaught(Player player, Map<ResourceLocation, FishCaughtCounter> fishesCaught) {
        get(player).fishesCaught = fishesCaught;
        sync(player);
    }

    public static boolean getReceivedGuide(Player player) {
        return get(player).receivedGuide;
    }

    public static void setReceivedGuide(Player player, boolean receivedGuide) {
        get(player).receivedGuide = receivedGuide;
        sync(player);
    }

    public static FishingGuideAttachment get(Entity holder){
        return holder.getData(SCDataAttachments.FISHING_GUIDE);
    }

    public static void sync(Player player){
        player.syncData(SCDataAttachments.FISHING_GUIDE);
    }

    @SuppressWarnings("deprecation")
    public static boolean hasLegacyData(Player player){
        return SCDataAttachments.get(player, SCDataAttachments.RECEIVED_GUIDE) || FishingGuideAttachment.getFishesCaught(player).size() > 1;
    }

    @SuppressWarnings("deprecation")
    public void loadFromLegacy(Player player){

        SCDataAttachments.get(player, SCDataAttachments.FISHES_CAUGHT).forEach(legacy -> {
            fishesCaught.putIfAbsent(legacy.fp(), legacy.covert());
        });

        SCDataAttachments.get(player, SCDataAttachments.TROPHIES_CAUGHT).forEach(loc -> trophiesCaught.putIfAbsent(loc, 0));

        receivedGuide = SCDataAttachments.get(player, SCDataAttachments.RECEIVED_GUIDE) || receivedGuide;

        SCDataAttachments.remove(player, SCDataAttachments.FISHES_CAUGHT);
        SCDataAttachments.remove(player, SCDataAttachments.FISHES_NOTIFICATION);
        SCDataAttachments.remove(player, SCDataAttachments.TROPHIES_CAUGHT);
        SCDataAttachments.remove(player, SCDataAttachments.RECEIVED_GUIDE);

        sync(player);
    }

}
