package com.wdiscute.starcatcher.registry.fishrestrictions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.antlr.v4.runtime.misc.Triple;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WeatherRestriction extends AbstractFishRestriction
{
    private final int clearChance;
    private final int rainChance;
    private final int thunderChance;
    private final String translationOverride;

    public static final MapCodec<WeatherRestriction> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.fieldOf("clear_extra_chance").forGetter(WeatherRestriction::getClearChance),
                    Codec.INT.fieldOf("rain_extra_chance").forGetter(WeatherRestriction::getRainChance),
                    Codec.INT.fieldOf("thunder_extra_chance").forGetter(WeatherRestriction::getThunderChance),
                    Codec.STRING.fieldOf("translation_override").forGetter(WeatherRestriction::getTranslationOverride)
            ).apply(instance, WeatherRestriction::new));

    public WeatherRestriction()
    {
        this.clearChance = 0;
        this.rainChance = 0;
        this.thunderChance = 0;
        this.translationOverride = "";
    }

    public WeatherRestriction(int clearChance, int rainChance, int thunderChance, String translationOverride)
    {
        this.clearChance = clearChance;
        this.rainChance = rainChance;
        this.thunderChance = thunderChance;
        this.translationOverride = translationOverride;
    }

    public int getClearChance()
    {
        return clearChance;
    }

    public int getRainChance()
    {
        return rainChance;
    }

    public int getThunderChance()
    {
        return thunderChance;
    }

    public String getTranslationOverride()
    {
        return translationOverride;
    }

    @Override
    public MapCodec<? extends AbstractFishRestriction> codec()
    {
        return CODEC;
    }

    @Override
    public DeferredHolder<AbstractFishRestriction, AbstractFishRestriction> getRegistryHolder()
    {
        return SCFishRestrictions.WEATHER;
    }

    @Override
    public int getFishChance(int currentChance, Level level, FishProperties fp, @NotNull Entity entity, ItemStack rod, Context context)
    {
        //fishes in area for guidebook ignores this restriction
        if (context.equals(Context.GUIDE_FISHES_IN_AREA)) return 0;

        float currentRainfall = level.getRainLevel(0);
        float currentThunder = level.getThunderLevel(0);

        int chance = 0;

        if (currentRainfall > 0.5f) chance += rainChance;
        if (currentThunder > 0.5f) chance += thunderChance;
        if (currentRainfall < 0.5f && currentThunder < 0.5f) chance += clearChance;

        return chance;
    }

    @Override
    public Triple<Component, List<Component>, List<Component>> getPageDescription(Level level, FishProperties fp, @NotNull Player player, Context context)
    {
        MutableComponent comp = translationOverride.isEmpty() ? Component.translatable("gui.guide.hover") : Component.translatable(translationOverride);
        List<Component> hover = new ArrayList<>();
        List<Component> blacklist = List.of();


        hover.add(Component.translatable("gui.guide.clear_chance", clearChance));
        hover.add(Component.translatable("gui.guide.rain_chance", rainChance));
        hover.add(Component.translatable("gui.guide.thunder_chance", thunderChance));


        return new Triple<>(Component.translatable("gui.guide.elevation").copy().append(comp), hover, blacklist);
    }


    public static final WeatherRestriction CLEAR = new WeatherRestriction(
            0, -9999, -9999, "gui.guide.weather.clear");

    public static final WeatherRestriction RAIN = new WeatherRestriction(
            -9999, 0, -9999, "gui.guide.weather.rain");

    public static final WeatherRestriction THUNDER = new WeatherRestriction(
            -9999, -9999, 0, "gui.guide.weather.thunder");

}
