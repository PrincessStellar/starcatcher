package com.wdiscute.starcatcher.registry.custom.sellingbinprocessor;

import com.wdiscute.starcatcher.Config;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.ModDataMaps;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import oshi.util.tuples.Pair;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.function.Supplier;

public interface ModSellingBinProcessors
{
    DeferredRegister<AbstractSellingBinProcessor> REGISTRY =
            DeferredRegister.create(Starcatcher.SELLING_BIN_REGISTRY, Starcatcher.MOD_ID);

    DeferredHolder<AbstractSellingBinProcessor, AbstractSellingBinProcessor> FISH = registerCatchModifier("fish_processor", FishSellingBinProcessor::new);


    static String getStringFromValue(int value)
    {
        List<Pair<Item, Integer>> currencies = ModDataMaps.getCurrencies().reversed();

        boolean found = false;

        String s = "";

        for (Pair<Item, Integer> c : currencies)
        {
            if (value > c.getB())
            {
                float numOfCurrency = (float) value / c.getB();

                DecimalFormat df = new DecimalFormat("#.##");

                if (numOfCurrency == 1)
                    s = s + df.format(numOfCurrency) + " " + c.getA().getDescription().getString(100);
                else
                    s = s + df.format(numOfCurrency) + " " + U.getPluralTranslation(c.getA()).getString(100);

                found = true;
                break;
            }
        }

        if (!found)
        {
            float numOfCurrency = (float) value / currencies.getLast().getB();
            DecimalFormat df = new DecimalFormat("#.##");

            if (numOfCurrency == 1)
                s = s + df.format(numOfCurrency) + " " + currencies.getLast().getA().getDescription().getString(100);
            else
                s = s + df.format(numOfCurrency) + " " + U.getPluralTranslation(currencies.getLast().getA()).getString(100);
        }

        return s;
    }

    //this does not take into account stack count!
    static int calculateValueFromSingleStack(ItemStack is)
    {
        var instance = ModDataMaps.getOrDefault(is, ModDataMaps.SELLING_BIN_VALUE, ModDataMaps.ItemValue.empty());

        int value = instance.baseValue();

        for (var p : instance.processors())
        {
            value += p.addValue(value, instance.baseValue(), is);
        }

        return (int) (value * Config.SELLING_BIN_MULTIPLIER.get());
    }

    static DeferredHolder<AbstractSellingBinProcessor, AbstractSellingBinProcessor> registerCatchModifier(String name, Supplier<AbstractSellingBinProcessor> sup)
    {
        return REGISTRY.register(name, sup);
    }

    static void register(IEventBus eventBus)
    {
        REGISTRY.register(eventBus);
    }

}
