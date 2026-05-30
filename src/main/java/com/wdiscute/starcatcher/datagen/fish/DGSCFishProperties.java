package com.wdiscute.starcatcher.datagen.fish;

import com.mojang.datafixers.util.Pair;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.datagen.fish.compat.*;
import com.wdiscute.starcatcher.registry.FishProperties;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.fishrestrictions.BaitRestriction;
import com.wdiscute.starcatcher.registry.minigamemodifiers.SCMinigameModifiers;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class DGSCFishProperties extends DatapackBuiltinEntriesProvider
{
    static
    {
        COMPAT_KEYS = new HashMap<>();
        PROPERTIES = new ArrayList<>();

        //register all entries before anything else
        DGTrophies.bootstrap();
        DGMinecraftFishes.bootstrap();
        DGStarcatcherFishes.bootstrap();
        DGTideFishes.bootstrap();
        DGAquacultureFishes.bootstrap();
         DGFishOfThievesFishes.bootstrap();
        DGNetherDepthsUpgradeFishes.bootstrap();
        DGSullysModFishes.bootstrap();
        DGUpgradeAquaticFishes.bootstrap();
        DGEnvironmentalFishes.bootstrap();
        DGBetterEndFishes.bootstrap();
        DGCollectorsReapFishes.bootstrap();
        DGMinersDelightFishes.bootstrap();
        DGAlexsCavesFishes.bootstrap();
        DGCrittersAndCompanionsFishes.bootstrap();
        DGHybridAquaticFishes.bootstrap();
        DGAquamiraeFishes.bootstrap();
        DGTerraFirmaCraftFishes.bootstrap();
        DGUnusualFishFishes.bootstrap();
        DGSpawnFishes.bootstrap();
        DGFintasticFishes.bootstrap();
    }

    public static final List<Pair<ResourceKey<FishProperties>, FishProperties>> PROPERTIES;
    public static final Map<ResourceKey<FishProperties>, String> COMPAT_KEYS;
    public static final RegistrySetBuilder REGISTRY = new RegistrySetBuilder().add(Starcatcher.FISH_REGISTRY_KEY, DGSCFishProperties::bootstrap);

    public DGSCFishProperties(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, REGISTRY, (consumer) ->
                        COMPAT_KEYS.forEach((k, v) ->
                        {
                            //fix for hybrid aquatic as their modid is hybrid_aquatic but items use hybrid-aquatic
                            if (k.location().getNamespace().equals("hybrid-aquatic"))
                                consumer.accept(k, new ModLoadedCondition("hybrid_aquatic"));
                            else
                                consumer.accept(k, new ModLoadedCondition(v));
                        }),
                Set.of(
                        Starcatcher.MOD_ID,
                        "minecraft",
                        "tide",
                        "aquaculture",
                        "fishofthieves",
                        "netherdepthsupgrade",
                        "sullysmod",
                        "upgrade_aquatic",
                        "environmental",
                        "collectorsreap",
                        "miners_delight",
                        "alexscaves",
                        "crittersandcompanions",
                        "aquamirae",
                        "hybrid_aquatic",
                        "hybrid-aquatic",
                        "tfc",
                        "betterend",
                        "unusualfishmod",
                        "spawn",
                        "fintastic"
                        //That's a lot of compatibilities
                ));
    }

    static ResourceKey<FishProperties> createKey(FishProperties fp)
    {
        if (fp.catchInfo().fishEntryType().equals(FishProperties.CatchInfo.FishEntryType.FISH))
            return ResourceKey.create(Starcatcher.FISH_REGISTRY_KEY, ResourceLocation.parse(fp.catchInfo().fish().getRegisteredName()));

        return ResourceKey.create(Starcatcher.FISH_REGISTRY_KEY, ResourceLocation.parse(
                fp.catchInfo().fish().getKey().location().getNamespace() + ":" +
                        fp.catchInfo().fishEntryType().name().toLowerCase(Locale.ROOT) + "_" +
                        fp.catchInfo().fish().getKey().location().getPath()
        ));
    }

    public static void register(FishProperties.Builder builder)
    {
        register(builder, "");
    }

    public static void register(FishProperties.Builder builder, String modLoadedRestriction)
    {
        FishProperties fp = builder.build();

        //if bucketable fish, add bucket and entity
        if (SCItems.BUCKETABLE_FISHES_REGISTRY.getEntries().stream().map(DeferredHolder::getDelegate).toList().contains(fp.catchInfo().fish())
        && fp.catchInfo().fishEntryType().equals(FishProperties.CatchInfo.FishEntryType.FISH))
        {
            builder.withBucketedFish(SCItems.STARCAUGHT_BUCKET);
            builder.withEntityToSpawn(U.holderEntity("starcatcher", "fish"));
            if (fp.catchInfo().fish().getRegisteredName().contains("starcatcher"))
                DGStarcatcherFishes.BUCKETABLE_FISHES_EVEN_WITHOUT_MODEL.add(builder.build());
            fp = builder.build();
        }

        ResourceKey<FishProperties> key = createKey(fp);

        COMPAT_KEYS.put(key, modLoadedRestriction.isEmpty() ? fp.catchInfo().fish().getRegisteredName().split(":")[0] : modLoadedRestriction);
        PROPERTIES.add(Pair.of(key, fp));
    }

    public static void bootstrap(BootstrapContext<FishProperties> context)
    {
        PROPERTIES.forEach(p -> context.register(p.getFirst(), p.getSecond()));
    }



    //region builders
    public static FishProperties.Builder fish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish);
    }

    public static FishProperties.Builder overworldFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD);
    }

    public static FishProperties.Builder endFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.END)
                .addModifier(SCMinigameModifiers.TELEPORT);
    }

    public static FishProperties.Builder endOuterIslandsFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.END_OUTER_ISLANDS)
                .addModifier(SCMinigameModifiers.TELEPORT);
    }

    public static FishProperties.Builder netherLavaFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.NETHER_LAVA)
                .addModifier(SCMinigameModifiers.BURN_ON_MISS);
    }

    public static FishProperties.Builder netherLavaCrimsonForestFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.NETHER_LAVA_CRIMSON_FOREST)
                .addModifier(SCMinigameModifiers.BURN_ON_MISS);
    }

    public static FishProperties.Builder netherLavaWarpedForestFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.NETHER_LAVA_WARPED_FOREST)
                .addModifier(SCMinigameModifiers.BURN_ON_MISS);
    }

    public static FishProperties.Builder netherLavaSoulSandValleyFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.NETHER_LAVA_SOUL_SAND_VALLEY)
                .addModifier(SCMinigameModifiers.BURN_ON_MISS);
    }

    public static FishProperties.Builder netherLavaBasaltDeltasFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.NETHER_LAVA_BASALT_DELTAS)
                .addModifier(SCMinigameModifiers.BURN_ON_MISS);
    }

    public static FishProperties.Builder overworldLushCavesFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_LUSH_CAVES)
                .addRestrictions(BaitRestriction.LUSH_BAIT);
    }

    public static FishProperties.Builder overworldDeepDarkFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_DEEP_DARK)
                .addRestrictions(BaitRestriction.SCULK_BAIT);
    }

    public static FishProperties.Builder overworldSurfaceFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_SURFACE);
    }

    public static FishProperties.Builder overworldSurfaceLava(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_LAVA_SURFACE)
                .addModifier(SCMinigameModifiers.BURN_ON_MISS);
    }

    public static FishProperties.Builder overworldCavesFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_STONE_CAVES);
    }

    public static FishProperties.Builder overworldDripstoneCavesFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_DRIPSTONE_CAVES)
                .addRestrictions(BaitRestriction.DRIPSTONE_BAIT);
    }

    public static FishProperties.Builder overworldUndergroundFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_UNDERGROUND);
    }

    public static FishProperties.Builder overworldUndergroundLava(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_LAVA_UNDERGROUND)
                .addModifier(SCMinigameModifiers.BURN_ON_MISS);
    }

    public static FishProperties.Builder overworldMountainFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_MOUNTAIN);
    }

    public static FishProperties.Builder overworldDeepslateFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_DEEPSLATE);
    }

    public static FishProperties.Builder overworldDeepslateLava(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_LAVA_DEEPSLATE)
                .addModifier(SCMinigameModifiers.BURN_ON_MISS);
    }

    public static FishProperties.Builder overworldColdLakeFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_COLD_LAKE)
                .withDifficulty(FishProperties.Difficulty.EASY.addModifiers(List.of(SCMinigameModifiers.FREEZE_ON_MISS)));
    }

    public static FishProperties.Builder overworldWarmLakeFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_WARM_LAKE);
    }

    public static FishProperties.Builder overworldWarmMountainFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_WARM_LAKE);
    }

    public static FishProperties.Builder overworldColdMountainFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_COLD_MOUNTAIN)
                .withDifficulty(FishProperties.Difficulty.EASY.addModifiers(List.of(SCMinigameModifiers.FREEZE_ON_MISS)));
    }

    public static FishProperties.Builder overworldColdOceanFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .withDifficulty(FishProperties.Difficulty.EASY.addModifiers(List.of(SCMinigameModifiers.FREEZE_ON_MISS)))
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_COLD_OCEAN);
    }

    public static FishProperties.Builder overworldColdRiverFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .withDifficulty(FishProperties.Difficulty.EASY.addModifiers(List.of(SCMinigameModifiers.FREEZE_ON_MISS)))
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_COLD_RIVER);
    }

    public static FishProperties.Builder overworldLakeFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_LAKE);
    }

    public static FishProperties.Builder overworldOceanFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_ALL_OCEANS);
    }

    public static FishProperties.Builder overworldWarmOceanFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_WARM_OCEAN);
    }

    public static FishProperties.Builder overworldDeepOceanFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_DEEP_OCEAN);
    }

    public static FishProperties.Builder overworldRiverFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_RIVER);
    }

    public static FishProperties.Builder overworldBeachFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_BEACH);
    }

    public static FishProperties.Builder overworldMushroomFieldsFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_MUSHROOM_FIELDS);
    }

    public static FishProperties.Builder overworldBambooJungleFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_BAMBOO_JUNGLE);
    }

    public static FishProperties.Builder overworldJungleFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_JUNGLE);
    }

    public static FishProperties.Builder overworldTaigaFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_TAIGA);
    }

    public static FishProperties.Builder overworldCherryGroveFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_CHERRY_GROVE)
                .addRestrictions(BaitRestriction.CHERRY_BAIT);
    }

    public static FishProperties.Builder overworldSwampFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_SWAMPS)
                .addRestrictions(BaitRestriction.MURKWATER_BAIT);
    }

    public static FishProperties.Builder overworldDarkForestFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_DARK_FOREST);
    }

    public static FishProperties.Builder overworldForestFish(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_FOREST);
    }

    public static FishProperties.Builder overworldVoidFishing(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.OVERWORLD_VOID);
    }

    public static FishProperties.Builder netherVoidFishing(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.NETHER_VOID);
    }

    public static FishProperties.Builder endVoidFishing(Holder<Item> fish)
    {
        return FishProperties.builder().withFish(fish)
                .addRestrictions(FishProperties.WorldRestrictions.END_VOID);
    }

    //endregion



    @Override
    public String getName()
    {
        return "FishingProperties";
    }
}
