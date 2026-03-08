package com.wdiscute.starcatcher.secretnotes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.io.ExtraComposites;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.storage.FishProperties;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SecretNote extends Item
{
    public SecretNote()
    {
        super(new Properties().stacksTo(1).component(ModDataComponents.SECRET_NOTE, Note.SAMPLE_NOTE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand)
    {
        ItemStack itemInHand = player.getItemInHand(usedHand);
        if (!level.isClientSide) return InteractionResultHolder.success(itemInHand);

        //read note
        openNoteScreen(ModDataComponents.getOrDefault(itemInHand, ModDataComponents.SECRET_NOTE, Note.SAMPLE_NOTE));

        return InteractionResultHolder.success(itemInHand);
    }

    @OnlyIn(Dist.CLIENT)
    private void openNoteScreen(Note note)
    {
        Minecraft.getInstance().setScreen(new SecretNoteScreen(note));
    }

    public enum Note implements StringRepresentable
    {
        SAMPLE_NOTE("sample_note", "message_overworld"),
        CRYSTAL_HOOK("crystal_hook", "message_overworld"),
        ARNWULF_1("lava_proof_bottle_1", "message_overworld"),
        ARNWULF_2("lava_proof_bottle_2", "message_overworld"),
        HOPEFUL_NOTE("hopeful_note", "message_overworld"),
        HOPELESS_NOTE("hopeless_note", "message_overworld"),
        WITHER("wither_note", "message_nether"),
        TRUE_BLUE("true_blue", "message_overworld");

        public static final Codec<Note> CODEC = StringRepresentable.fromEnum(Note::values);
        public static final StreamCodec<FriendlyByteBuf, Note> STREAM_CODEC = NeoForgeStreamCodecs.enumCodec(Note.class);
        private final String key;
        private final String texture;

        Note(String key, String texture)
        {
            this.key = key;
            this.texture = texture;
        }

        public @NotNull String getSerializedName()
        {
            return this.key;
        }
        public @NotNull String getTexture()
        {
            return this.texture;
        }
    }

}


