package com.wdiscute.starcatcher.registry.fishrestrictions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.SCColors;
import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.datagen.SCDataGenerators;
import com.wdiscute.starcatcher.fish.FishProperties;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.HolderSetCodec;
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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BiomeRestriction extends AbstractFishRestriction
{
    private final List<HolderSet<Biome>> biomes;
    private final List<HolderSet<Biome>> blacklist;
    private final String hover;

    public static final MapCodec<BiomeRestriction> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    HolderSetCodec.create(Registries.BIOME, Biome.CODEC, false).listOf()
                            .fieldOf("biomes")
                            .forGetter(o -> o.biomes),
                    HolderSetCodec.create(Registries.BIOME, Biome.CODEC, false).listOf()
                            .fieldOf("blacklist")
                            .forGetter(o -> o.blacklist),
                    Codec.STRING.optionalFieldOf("hover_translation", "").forGetter(o -> o.hover),
                    Codec.STRING.optionalFieldOf("translation_override", "").forGetter(o -> o.translationOverride)
            ).apply(instance, BiomeRestriction::new));

    public BiomeRestriction(List<HolderSet<Biome>> biomes, List<HolderSet<Biome>> blacklist, String hover, String translation)
    {
        super(translation);
        this.biomes = biomes;
        this.blacklist = blacklist;
        this.hover = hover;
    }

    @Override
    public int getSortPriority()
    {
        return -50;
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

        //if biomes contains biome and blacklist doesn't contain biome
        if (biomes.stream().anyMatch(o -> o.contains(biome)) && biomes.stream().noneMatch(o -> o.contains(biome)))
            return -0;
        else
            return -9999;
    }

    @Override
    public List<Component> getIndexHover(Level level, FishProperties fp, @NotNull Player player, Context context)
    {
        if (getFishChance(0, level, fp, player, ItemStack.EMPTY, Context.GUIDE_FISHES_HOVER) >= 0)
            return List.of(Component.translatable("gui.guide.hover.biome.correct").withStyle(Style.EMPTY.withColor(SCColors.GUIDE_GREEN)));
        else
            return List.of(Component.translatable("gui.guide.hover.biome.incorrect").withStyle(Style.EMPTY.withColor(SCColors.GUIDE_RED)));
    }

    @Override
    public MutableComponent getDescriptionPrefix()
    {
        return Component.translatable("gui.guide.biome");
    }

    @Override
    public MutableComponent getNonOverriddenDescription(Level level, FishProperties fp, @NotNull Player player, Context context)
    {
        //Biomes: ------
        if (biomes.isEmpty())
            return Component.translatable("gui.guide.biomes.empty");

        //
        if (biomes.size() == 1)
        {
            //single biome name / biome tag name / [hover]
            Optional<TagKey<Biome>> tag = biomes.get(0).unwrap().left();
            Optional<TagKey<Biome>> singleBiome = biomes.get(0).unwrap().left();

            //intelij compressed my if checks into this, hopefully I never have to change/debug it
            return tag.map(biomeTagKey -> Component.translatable("tag." + biomeTagKey.location().toLanguageKey()))
                    .orElseGet(() -> singleBiome.map(biomeTagKey -> Component.translatable("biome." + biomeTagKey.location().toLanguageKey()))
                            .orElseGet(() -> Component.translatable("gui.guide.biomes.empty")));
        }
        else
        {
            return Component.translatable("gui.guide.hover");
        }
    }

    @Override
    public List<Component> getHover(Level level, FishProperties fp, @NotNull Player player, Context context)
    {
        List<Component> hover = new ArrayList<>();

        if (!this.hover.isEmpty()) return List.of(Component.translatable(this.hover));

//        if (!biomesList.isEmpty())
//        {
//            if (!biomesTags.isEmpty())
//            {
//                hover.add(Component.translatable("gui.guide.biome_tags").withStyle(Style.EMPTY.withBold(true)));
//
//                for (ResourceLocation rl : biomesTags)
//                    hover.add(Component.translatable("tag." + rl.toLanguageKey()));
//                hover.add(Component.empty());
//            }
//
//            hover.add(Component.translatable("gui.guide.biomes").withStyle(Style.EMPTY.withBold(true)));
//            if (biomesList.isEmpty())
//                hover.add(Component.translatable("gui.guide.biomes.empty"));
//
//            for (ResourceLocation rl : biomesList)
//                hover.add(Component.translatable("biome." + rl.toLanguageKey()));
//        }

        return hover;
    }

    public BiomeRestriction biome(BootstrapContext<FishProperties> context, ResourceLocation biome)
    {
        HolderSet.Named<Biome> set = context.lookup(Registries.BIOME).getOrThrow(TagKey.create(Registries.BIOME, biome));
        return new BiomeRestriction(new ArrayList<>(biomes)
        {{
            add(set);
        }}, blacklist, hover, translationOverride);
    }

    public BiomeRestriction tag(BootstrapContext<FishProperties> context, ResourceLocation tag)
    {
        HolderSet.Named<Biome> set = context.lookup(Registries.BIOME).getOrThrow(TagKey.create(Registries.BIOME, tag));
        return new BiomeRestriction(new ArrayList<>(biomes)
        {{
            add(set);
        }}, blacklist, hover, translationOverride);
    }

    public BiomeRestriction blacklisted(BootstrapContext<FishProperties> context, ResourceLocation biome)
    {
        HolderSet.Named<Biome> set = context.lookup(Registries.BIOME).getOrThrow(TagKey.create(Registries.BIOME, biome));
        return new BiomeRestriction(biomes, new ArrayList<>(blacklist)
        {{
            add(set);
        }}, hover, translationOverride);
    }

    public BiomeRestriction blacklistedTag(BootstrapContext<FishProperties> context, ResourceLocation tag)
    {
        HolderSet.Named<Biome> set = context.lookup(Registries.BIOME).getOrThrow(TagKey.create(Registries.BIOME, tag));
        return new BiomeRestriction(biomes, new ArrayList<>(blacklist)
        {{
            add(set);
        }}, hover, translationOverride);
    }

    public BiomeRestriction hover(String hover)
    {
        return new BiomeRestriction(biomes, blacklist, hover, translationOverride);
    }

    public BiomeRestriction translation(String translation)
    {
        return new BiomeRestriction(biomes, blacklist, hover, translation);
    }

    public static final BiomeRestriction EMPTY = new BiomeRestriction(List.of(), List.of(), "", "");

    public static BiomeRestriction lakes(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .blacklistedTag(context, SCTags.IS_OCEAN)
                .blacklistedTag(context, SCTags.IS_RIVER)
                .blacklistedTag(context, SCTags.IS_MUSHROOM_FIELDS)
                .blacklistedTag(context, SCTags.IS_COLD_LAKE)
                .blacklistedTag(context, SCTags.IS_WARM_LAKE)
                .hover("gui.guide.lakes.hover")
                .translation("gui.guide.lakes");
    }

    public static BiomeRestriction warmLakes(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_WARM_LAKE);
    }

    public static BiomeRestriction coldLakes(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_COLD_LAKE);
    }

    public static BiomeRestriction flowerForest(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .biome(context, Biomes.FLOWER_FOREST.location());
    }

    public static BiomeRestriction sunflowerPlains(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .biome(context, Biomes.SUNFLOWER_PLAINS.location());
    }

    public static BiomeRestriction swampOnly(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .biome(context, Biomes.SWAMP.location());
    }

    public static BiomeRestriction bambooJungle(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .biome(context, Biomes.BAMBOO_JUNGLE.location());
    }

    // underground
    public static BiomeRestriction lushCaves(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .biome(context, Biomes.LUSH_CAVES.location());
    }

    public static BiomeRestriction dripstoneCaves(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .biome(context, Biomes.DRIPSTONE_CAVES.location());
    }

    public static BiomeRestriction deepDark(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .biome(context, Biomes.DEEP_DARK.location());
    }

    // oceans
    public static BiomeRestriction coldOceans(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_COLD_OCEAN);
    }

    public static BiomeRestriction allOceans(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_OCEAN);
    }

    public static BiomeRestriction normalOceans(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_NORMAL_OCEAN);
    }

    public static BiomeRestriction lukewarmOcean(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_LUKEWARM_OCEAN);
    }

    public static BiomeRestriction coldAndLukewarmOcean(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_LUKEWARM_OCEAN)
                .tag(context, SCTags.IS_COLD_OCEAN);
    }

    public static BiomeRestriction warmOceans(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_WARM_OCEAN);
    }

    public static BiomeRestriction deepOceans(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_DEEP_OCEAN);
    }

    // rivers
    public static BiomeRestriction coldRivers(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_COLD_RIVER);
    }

    public static BiomeRestriction rivers(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_RIVER)
                .blacklistedTag(context, SCTags.IS_COLD_RIVER);
    }

    public static BiomeRestriction savannas(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, BiomeTags.IS_SAVANNA.location());
    }

    public static BiomeRestriction beaches(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_BEACH);
    }

    public static BiomeRestriction mushroomFields(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_MUSHROOM_FIELDS);
    }

    public static BiomeRestriction jungles(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, BiomeTags.IS_JUNGLE.location());
    }

    public static BiomeRestriction taigas(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, BiomeTags.IS_TAIGA.location());
    }

    public static BiomeRestriction cherryGroves(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_CHERRY_GROVE);
    }

    public static BiomeRestriction junglesAndSwamps(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, BiomeTags.IS_JUNGLE.location())
                .tag(context, SCTags.IS_SWAMP);
    }

    public static BiomeRestriction swamps(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_SWAMP);
    }

    public static BiomeRestriction mangroveSwamp(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .biome(context, Biomes.MANGROVE_SWAMP.location());
    }

    public static BiomeRestriction darkForest(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_DARK_FOREST);
    }

    public static BiomeRestriction forest(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, BiomeTags.IS_FOREST.location());
    }

    public static BiomeRestriction lushCavesAndJungles(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .biome(context, Biomes.LUSH_CAVES.location())
                .tag(context, BiomeTags.IS_JUNGLE.location());
    }

    public static BiomeRestriction crimsonForest(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_CRIMSON_FOREST);
    }

    public static BiomeRestriction warpedForest(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_WARPED_FOREST);
    }

    public static BiomeRestriction soulSandValley(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_SOUL_SAND_VALLEY);
    }

    public static BiomeRestriction basaltDeltas(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, SCTags.IS_BASALT_DELTAS);
    }

    public static BiomeRestriction outerIslands(BootstrapContext<FishProperties> context)
    {
        return EMPTY
                .tag(context, BiomeTags.IS_END.location())
                .biome(context, Biomes.THE_END.location());
    }
}
