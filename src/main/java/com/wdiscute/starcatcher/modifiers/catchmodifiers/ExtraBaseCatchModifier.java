package com.wdiscute.starcatcher.modifiers.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.CatchInfo;
import com.wdiscute.starcatcher.fish.FishApi;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.SizeAndWeight;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ExtraBaseCatchModifier extends AbstractCatchModifier
{
    final int count;
    final boolean onlyForPerfectCatch;

    public static final MapCodec<ExtraBaseCatchModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.fieldOf("count").forGetter(o -> o.count),
                    Codec.BOOL.fieldOf("only_for_perfect_catch").forGetter(o -> o.onlyForPerfectCatch),
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, ExtraBaseCatchModifier::new));

    public ExtraBaseCatchModifier(int count, boolean perfect, String translationOverride)
    {
        super(translationOverride);
        this.count = count;
        this.onlyForPerfectCatch = perfect;
    }

    @Override
    public List<Component> getNonOverriddenDescription(boolean shift)
    {
        return List.of(Component.translatable("tooltip.modifier.starcatcher.extra_base_catch", NumberToWords.convert(count)));
    }

    @Override
    public List<ItemStack> addToFishedItems(FishProperties fp, int time, boolean perfectCatch, int hits, boolean completedTreasure, Player player)
    {
        //return if not a fish aka trophy/secret
        if (!fp.catchInfo().fishEntryType().equals(CatchInfo.FishEntryType.FISH)) return List.of();

        List<ItemStack> list = new ArrayList<>();

        for (int i = 0; i < count; i++)
        {
            float percentile = U.r.nextFloat(100);

            int size = SizeAndWeight.getRandomSize(fp, percentile);
            int weight = SizeAndWeight.getRandomWeight(fp, percentile);

            ItemStack itemStack = FishApi.makeItemStackNonBucket(fp, size, weight, percentile, false, player, perfectCatch);

            list.add(itemStack);
        }

        return list;
    }

    @Override
    public ResourceLocation getIdentifier()
    {
        return Starcatcher.rl("extra_base_catch");
    }

    @Override
    public MapCodec<? extends Modifier> getCodec()
    {
        return CODEC;
    }

    //chatgpt code, don't blame me for any bugs
    public static class NumberToWords
    {
        private static final String[] units = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        private static final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        private static final String[] multipliers = {"", " Thousand", " Million", " Billion"};

        public static String convert(int number)
        {
            if (number == 0) return "Zero";
            String res = "";
            int group = 0;
            while (number > 0)
            {
                if (number % 1000 != 0) res = convertGroup(number % 1000) + multipliers[group] + " " + res;
                number /= 1000;
                group++;
            }
            return res.trim();
        }

        private static String convertGroup(int n)
        {
            if (n < 100) return convertTwoDigit(n);
            return units[n / 100] + " Hundred " + convertTwoDigit(n % 100);
        }

        private static String convertTwoDigit(int n)
        {
            if (n < 20) return units[n];
            return tens[n / 10] + (n % 10 > 0 ? " " + units[n % 10] : "");
        }
    }
}
