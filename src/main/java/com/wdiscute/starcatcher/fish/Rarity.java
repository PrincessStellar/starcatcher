package com.wdiscute.starcatcher.fish;

import com.mojang.serialization.Codec;
import com.wdiscute.starcatcher.data.CaughtFishInfo;
import com.wdiscute.starcatcher.registry.SCDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;

public enum Rarity implements StringRepresentable
{
    NONE("none", 0, Style.EMPTY.applyFormat(ChatFormatting.WHITE), 99),
    TRASH("trash", 0, Style.EMPTY.applyFormat(ChatFormatting.WHITE), 99),
    COMMON("common", 4, Style.EMPTY.applyFormat(ChatFormatting.WHITE), 40),
    UNCOMMON("uncommon", 8, Style.EMPTY.applyFormat(ChatFormatting.GREEN), 40),
    RARE("rare", 12, Style.EMPTY.applyFormat(ChatFormatting.BLUE), 30),
    EPIC("epic", 20, Style.EMPTY.applyFormat(ChatFormatting.LIGHT_PURPLE), 10),
    LEGENDARY("legendary", 35, Style.EMPTY.applyFormat(ChatFormatting.GOLD), 10),
    GOLDEN("golden", 0, Style.EMPTY.applyFormat(ChatFormatting.GOLD), 0);

    public static final Codec<Rarity> CODEC = StringRepresentable.fromEnum(Rarity::values);
    public static final StreamCodec<FriendlyByteBuf, Rarity> STREAM_CODEC = NeoForgeStreamCodecs.enumCodec(Rarity.class);
    private final String key;
    private final int xp;
    private final Style style;
    private final int stoneHookGraceTicks;

    Rarity(String key, int xp, Style style, int stoneHookGraceTicks)
    {
        this.key = key;
        this.xp = xp;
        this.style = style;
        this.stoneHookGraceTicks = stoneHookGraceTicks;
    }

    public Component wrapWithRarityMarkdown(String s)
    {
        return Component.literal("<sc" + getSerializedName() + ">" + s + "</sc" + getSerializedName() + ">");
    }

    public String wrapWithRarityMarkdownAsString(String s)
    {
        return "<sc" + getSerializedName() + ">" + s + "</sc" + getSerializedName() + ">";
    }

    public String getSerializedName()
    {
        return this.key;
    }

    public int getStoneHookGraceTicks()
    {
        return stoneHookGraceTicks;
    }

    public int getId()
    {
        return this.ordinal();
    }

    public int getXp()
    {
        return xp;
    }

    public Style getStyle()
    {
        return style;
    }

    public static boolean isGolden(ItemStack stack)
    {
        if (stack.has(SCDataComponents.CAUGHT_FISH_INFO))
        {
            CaughtFishInfo caughtFishInfo = stack.get(SCDataComponents.CAUGHT_FISH_INFO);
            return caughtFishInfo != null && caughtFishInfo.golden();
        }
        return false;
    }
}
