package com.wdiscute.starcatcher.registry.fishrestrictions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.antlr.v4.runtime.misc.Triple;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BiomeRestriction extends AbstractFishRestriction
{
    private final List<ResourceLocation> biomes;
    private final List<ResourceLocation> biomesTags;
    private final List<ResourceLocation> biomesBlacklist;
    private final List<ResourceLocation> biomesBlacklistTags;
    private final String translationOverride;

    public static final MapCodec<BiomeRestriction> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    ResourceLocation.CODEC.listOf().fieldOf("biomes").forGetter(BiomeRestriction::getBiomes),
                    ResourceLocation.CODEC.listOf().fieldOf("biomes_tags").forGetter(BiomeRestriction::getBiomesTags),
                    ResourceLocation.CODEC.listOf().fieldOf("biomes_blacklist").forGetter(BiomeRestriction::getBiomesBlacklist),
                    ResourceLocation.CODEC.listOf().fieldOf("biomes_blacklist_tags").forGetter(BiomeRestriction::getBiomesBlacklistTags),
                    Codec.STRING.fieldOf("translation_override").forGetter(BiomeRestriction::getTranslationOverride)
            ).apply(instance, BiomeRestriction::new));

    public BiomeRestriction()
    {
        this.biomes = List.of();
        this.biomesTags = List.of();
        this.biomesBlacklist = List.of();
        this.biomesBlacklistTags = List.of();
        this.translationOverride = "";
    }

    public BiomeRestriction(ResourceLocation biome, String translationOverride)
    {
        this.biomes = List.of(biome);
        this.biomesTags = List.of();
        this.biomesBlacklist = List.of();
        this.biomesBlacklistTags = List.of();
        this.translationOverride = translationOverride;
    }

    public BiomeRestriction(List<ResourceLocation> biomes, List<ResourceLocation> biomesTags, List<ResourceLocation> biomesBlacklist, List<ResourceLocation> biomesBlacklistTags, String translationOverride)
    {
        this.biomes = biomes;
        this.biomesTags = biomesTags;
        this.biomesBlacklist = biomesBlacklist;
        this.biomesBlacklistTags = biomesBlacklistTags;
        this.translationOverride = translationOverride;
    }

    public List<ResourceLocation> getBiomes()
    {
        return biomes;
    }

    public List<ResourceLocation> getBiomesTags()
    {
        return biomesTags;
    }

    public List<ResourceLocation> getBiomesBlacklist()
    {
        return biomesBlacklist;
    }

    public List<ResourceLocation> getBiomesBlacklistTags()
    {
        return biomesBlacklistTags;
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
        return SCFishRestrictions.BIOME;
    }

    @Override
    public int getFishChance(int currentChance, Level level, FishProperties fp, @NotNull Entity entity, ItemStack rod, Context context)
    {
        Holder<Biome> biome = level.getBiome(entity.blockPosition());
        ResourceLocation biomeRL = biome.getKey().location();

        //if biomes or biomesTags then check if biome is in any of them
        if (!biomes.isEmpty() || !biomesTags.isEmpty())
        {
            boolean safe = false;

            if (biomes.contains(biomeRL)) safe = true;

            if (biomesTags.stream().anyMatch(rl -> biome.is(TagKey.create(Registries.BIOME, rl))))
                safe = true;

            if (!safe) return -9999;
        }

        //return if biome is in blacklist
        if (biomesBlacklist.contains(biomeRL)) return -9999;

        //return if biome is part of blacklist tags
        for (ResourceLocation rl : biomesBlacklistTags)
            if (biome.is(TagKey.create(Registries.BIOME, rl)))
                return -9999;

        return 0;
    }

    @Override
    public Triple<Component, List<Component>, List<Component>> getPageDescription(Level level, FishProperties fp, @Nullable Player player, Context context)
    {
        MutableComponent comp;
        List<Component> hover = new ArrayList<>();
        List<Component> blacklist = new ArrayList<>();

        Holder<Biome> currentBiome = level.getBiome(player.blockPosition());
        ResourceLocation currentBiomeRL = currentBiome.getKey().location();

        List<ResourceLocation> biomesList = FishProperties.getBiomesAsListFromTags(biomes, biomesTags, level);
        List<ResourceLocation> biomesBlacklistList = FishProperties.getBiomesBlacklistAsList(biomesBlacklist, biomesBlacklistTags, level);

        if (biomesList.isEmpty())
        {
            comp = Component.translatable("gui.guide.no_restriction");
        }
        else
        {
            //single biome name / biome tag name / [hover]
            if (biomesList.size() == 1)
                comp = Component.translatable("biome." + biomesList.getFirst().toLanguageKey());
            else if (biomesTags.size() == 1)
                comp = Component.translatable("tag." + biomesTags.getFirst().toLanguageKey());
            else
                comp = Component.translatable("gui.guide.hover");
        }


        //hover
        {
            if (!biomesList.isEmpty())
            {
                if (!biomesTags.isEmpty())
                {
                    hover.add(Component.translatable("gui.guide.biome_tags").withStyle(Style.EMPTY.withBold(true)));

                    for (ResourceLocation rl : biomesTags)
                        hover.add(Component.translatable("tag." + rl.toLanguageKey()));
                    hover.add(Component.empty());
                }

                hover.add(Component.translatable("gui.guide.biomes").withStyle(Style.EMPTY.withBold(true)));
                if (biomesList.isEmpty())
                    hover.add(Component.translatable("gui.guide.biomes.empty"));

                for (ResourceLocation rl : biomesList)
                    hover.add(Component.translatable("biome." + rl.toLanguageKey()));
            }
        }

        //blacklist
        {
            if (!biomesBlacklistTags.isEmpty())
            {
                blacklist.add(Component.translatable("gui.guide.blacklisted_biome_tags").withStyle(Style.EMPTY.withBold(true)));

                for (ResourceLocation rl : biomesBlacklistTags)
                    blacklist.add(Component.translatable("tag." + rl.toLanguageKey()));
                blacklist.add(Component.empty());
            }

            blacklist.add(Component.translatable("gui.guide.blacklisted_biomes").withStyle(Style.EMPTY.withBold(true)));
            if (biomesBlacklistList.isEmpty())
                blacklist.add(Component.translatable("gui.guide.biomes.empty"));

            for (ResourceLocation rl : biomesBlacklistList)
                blacklist.add(Component.translatable("biome." + rl.toLanguageKey()));
        }

        comp.withStyle(Style.EMPTY.withColor(0x40752c));

        //makes text red
        if (!biomesList.contains(currentBiomeRL) && !biomes.isEmpty())
            comp.withStyle(Style.EMPTY.withColor(0xa34536));
        if (biomesBlacklistList.contains(currentBiomeRL))
            comp.withStyle(Style.EMPTY.withColor(0xa34536));

        Component start = Component.translatable("gui.guide.biome");

        if (!translationOverride.isEmpty())
            comp = Component.translatable(translationOverride);

        return new Triple<>(start.copy().append(comp), hover, blacklist);
    }

    //Vanilla
    public static final BiomeRestriction LUSH_CAVES = new BiomeRestriction(Biomes.LUSH_CAVES.location(), "");
    public static final BiomeRestriction DRIPSTONE_CAVES = new BiomeRestriction(Biomes.DRIPSTONE_CAVES.location(), "");
    public static final BiomeRestriction DEEP_DARK = new BiomeRestriction(Biomes.DEEP_OCEAN.location(), "");
    public static final BiomeRestriction SWAMP_ONLY = new BiomeRestriction(Biomes.SWAMP.location(), "");
    public static final BiomeRestriction BAMBOO_JUNGLE = new BiomeRestriction(Biomes.BAMBOO_JUNGLE.location(), "");
    public static final BiomeRestriction RIVERS = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_RIVER), List.of(), List.of(), "");
    public static final BiomeRestriction ALL_OCEANS = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_OCEAN), List.of(), List.of(), "");
    public static final BiomeRestriction NORMAL_OCEANS = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_NORMAL_OCEAN), List.of(), List.of(), "");
    public static final BiomeRestriction LUKEWARM_OCEAN = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_LUKEWARM_OCEAN), List.of(), List.of(), "");
    public static final BiomeRestriction COLD_AND_LUKEWARM_OCEAN = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_LUKEWARM_OCEAN, StarcatcherTags.IS_COLD_OCEAN), List.of(), List.of(), "");
    public static final BiomeRestriction WARM_OCEANS = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_WARM_OCEAN), List.of(), List.of(), "");
    public static final BiomeRestriction DEEP_OCEANS = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_DEEP_OCEAN), List.of(), List.of(), "");
    public static final BiomeRestriction LAKES = new BiomeRestriction(List.of(), List.of(), List.of(), List.of(StarcatcherTags.IS_OCEAN, StarcatcherTags.IS_RIVER, StarcatcherTags.IS_MUSHROOM_FIELDS), "");
    public static final BiomeRestriction WARM_LAKES = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_WARM_LAKE), List.of(), List.of(), "");
    public static final BiomeRestriction COLD_RIVERS = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_COLD_RIVER), List.of(), List.of(), "");
    public static final BiomeRestriction COLD_OCEANS = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_COLD_OCEAN), List.of(), List.of(), "");
    public static final BiomeRestriction COLD_LAKES = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_COLD_LAKE), List.of(), List.of(), "");
    public static final BiomeRestriction SAVANNAS = new BiomeRestriction(List.of(), List.of(BiomeTags.IS_SAVANNA.location()), List.of(), List.of(), "");
    public static final BiomeRestriction BEACHES = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_BEACH), List.of(), List.of(), "");
    public static final BiomeRestriction MUSHROOM_FIELDS = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_MUSHROOM_FIELDS), List.of(), List.of(), "");
    public static final BiomeRestriction JUNGLES = new BiomeRestriction(List.of(), List.of(BiomeTags.IS_JUNGLE.location()), List.of(), List.of(), "");
    public static final BiomeRestriction TAIGAS = new BiomeRestriction(List.of(), List.of(BiomeTags.IS_TAIGA.location()), List.of(), List.of(), "");
    public static final BiomeRestriction CHERRY_GROVES = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_CHERRY_GROVE), List.of(), List.of(), "");
    public static final BiomeRestriction JUNGLES_AND_SWAMPS = new BiomeRestriction(List.of(), List.of(BiomeTags.IS_JUNGLE.location(), StarcatcherTags.IS_SWAMP), List.of(), List.of(), "");
    public static final BiomeRestriction SWAMPS = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_SWAMP), List.of(), List.of(), "");
    public static final BiomeRestriction MANGROVE_SWAMP = new BiomeRestriction(List.of(Biomes.MANGROVE_SWAMP.location()), List.of(), List.of(), List.of(), "");
    public static final BiomeRestriction DARK_FOREST = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_DARK_FOREST), List.of(), List.of(), "");
    public static final BiomeRestriction FOREST = new BiomeRestriction(List.of(), List.of(BiomeTags.IS_FOREST.location()), List.of(), List.of(), "");
    public static final BiomeRestriction LUSH_CAVES_AND_JUNGLES = new BiomeRestriction(List.of(Biomes.LUSH_CAVES.location()), List.of(BiomeTags.IS_JUNGLE.location()), List.of(), List.of(), "");
    public static final BiomeRestriction CRIMSON_FOREST = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_CRIMSON_FOREST), List.of(), List.of(), "");
    public static final BiomeRestriction WARPED_FOREST = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_WARPED_FOREST), List.of(), List.of(), "");
    public static final BiomeRestriction SOUL_SAND_VALLEY = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_SOUL_SAND_VALLEY), List.of(), List.of(), "");
    public static final BiomeRestriction BASALT_DELTAS = new BiomeRestriction(List.of(), List.of(StarcatcherTags.IS_BASALT_DELTAS), List.of(), List.of(), "");
    public static final BiomeRestriction OUTER_ISLANDS = new BiomeRestriction(List.of(), List.of(BiomeTags.IS_END.location()), List.of(Biomes.THE_END.location()), List.of(), "");
}
