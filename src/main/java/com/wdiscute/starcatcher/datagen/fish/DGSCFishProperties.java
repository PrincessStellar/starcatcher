package com.wdiscute.starcatcher.datagen.fish;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.datagen.fish.compat.*;
import com.wdiscute.starcatcher.fish.*;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.*;
import com.wdiscute.starcatcher.registry.SCEntities;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.fishrestrictions.BaitRestriction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class DGSCFishProperties extends DatapackBuiltinEntriesProvider
{
    static
    {
        COMPAT_KEYS = new HashMap<>();
        PROPERTIES = new HashMap<>();

        boolean compat = false;

        //register all entries of built-in compat mods

        //vanilla & starcatcher
        DGTrophies.bootstrap();
        DGMinecraftFishes.bootstrap();
        DGStarcatcherFishes.bootstrap();

        if(compat)
        {
            //compat
            DGTideFishes.bootstrap();
            DGAquacultureFishes.bootstrap();
            DGFishOfThievesFishes.bootstrap();
            DGNetherDepthsUpgradeFishes.bootstrap();
            DGEnvironmentalFishes.bootstrap();
            DGMinersDelightFishes.bootstrap();
            DGCrittersAndCompanionsFishes.bootstrap();
            DGHybridAquaticFishes.bootstrap();
            DGCreateFishes.bootstrap();
            DGEternalStarlightFishes.bootstrap();

            //compat 1.20.1 only
            DGUnusualFishFishes.bootstrap();
            DGAlexsCavesFishes.bootstrap();
            DGCollectorsReapFishes.bootstrap();
            DGFintasticFishes.bootstrap();
            DGSullysModFishes.bootstrap();
            DGTerraFirmaCraftFishes.bootstrap();
            DGBetterEndFishes.bootstrap();
            DGUpgradeAquaticFishes.bootstrap();
        }
    }

    public static final Map<ResourceKey<FishProperties>, FishProperties> PROPERTIES;
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
                        //That's a lot of compatibilities 67
                ));
    }

    static ResourceKey<FishProperties> createKey(FishProperties fp)
    {
        ResourceLocation key;

        //if alwaysSpawnEntity, use entity rl instead of item
        if (fp.catchInfo().alwaysSpawnEntity())
            key = ResourceLocation.parse(fp.catchInfo().entityToSpawn().getRegisteredName());
        else
        {
            if (fp.catchInfo().fish().isEmpty())
                key = fp.catchInfo().fish().rl();
            else
                key = BuiltInRegistries.ITEM.getKey(fp.catchInfo().fish().toStack().getItem());
        }

        if (fp.catchInfo().fishEntryType().equals(CatchInfo.FishEntryType.FISH))
        {
            return ResourceKey.create(Starcatcher.FISH_REGISTRY_KEY, key);
        }

        return ResourceKey.create(Starcatcher.FISH_REGISTRY_KEY, ResourceLocation.parse(
                key.getNamespace() + ":" +
                fp.catchInfo().fishEntryType().name().toLowerCase(Locale.ROOT) + "_" +
                key.getPath()
        ));
    }

    public static void register(FishProperties builder)
    {
        register(builder, "");
    }

    public static void register(FishProperties fp, String modLoadedRestriction)
    {
        fp = fp.withRestrictions(fp.restrictions().stream().sorted().toList());

        String modIdOfFish = fp.catchInfo().fish().rl().getNamespace();

        ItemStack fish = fp.catchInfo().fish().toStack();

        //if bucketable fish, add bucket and entity
        if (SCItems.BUCKETABLE_FISHES_REGISTRY.getEntries().stream().map(o -> o.getDelegate().value()).toList()
                    .stream().anyMatch(fish::is)
            && fp.catchInfo().fishEntryType().equals(CatchInfo.FishEntryType.FISH))
        {
            fp = fp.withCatchInfo(fp.catchInfo().withBucket(new MaybeStack(SCItems.STARCAUGHT_BUCKET)));
            fp = fp.withCatchInfo(fp.catchInfo().withEntityToSpawn(SCEntities.FISH));

            //if item is from starcatcher, add to list of bucketable fishes, for rarity tagging
            if (modIdOfFish.equals("starcatcher"))
                DGStarcatcherFishes.STARCATCHER_BUCKETABLE.add(fp);
        }

        //adds every starcatcher item that has guide entry, is of fish type, and is not trash rarity
        if (modIdOfFish.equals("starcatcher") && fp.hasGuideEntry() && fp.catchInfo().fishEntryType().equals(CatchInfo.FishEntryType.FISH) && !fp.rarity().equals(Rarity.TRASH))
            DGStarcatcherFishes.STARCATCHER_FISHABLE.add(fp);

        DGStarcatcherFishes.ALL_FISHABLE.add(fp);

        ResourceKey<FishProperties> key = createKey(fp);

        COMPAT_KEYS.put(key, modLoadedRestriction.isEmpty() ? modIdOfFish : modLoadedRestriction);
        PROPERTIES.put(key, fp);
    }

    public static void bootstrap(BootstrapContext<FishProperties> context)
    {
        PROPERTIES.forEach(context::register);
    }


    //region builders
    public static FishProperties fish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish);
    }

    public static FishProperties overworldFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD);
    }

    public static FishProperties endFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.END)
                .withTextures(FishProperties.END)
                .addModifier(new TeleportModifier(""));
    }

    public static FishProperties endVoidFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.END_VOID)
                .withTextures(FishProperties.END_VOID)
                .addModifier(new TeleportModifier(""))
                ;
    }

    public static FishProperties endVoidOuterIslandsFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .withTextures(FishProperties.END_VOID)
                .addRestriction(WorldRestrictions.END_VOID_OUTER_ISLANDS)
                .addModifier(new TeleportModifier(""));
    }

    public static FishProperties endOuterIslandsFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .withTextures(FishProperties.END)
                .addRestriction(WorldRestrictions.END_OUTER_ISLANDS)
                .addModifier(new TeleportModifier(""));
    }

    public static FishProperties netherLavaFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.NETHER_LAVA)
                .withTextures(FishProperties.NETHER)
                .addModifier(new Nikdo53Modifier(1, ""))
                .addModifier(new BurnOnMissModifier(40, 10, 16, ""))
                ;
    }

    public static FishProperties netherLavaCrimsonForestFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .withTextures(FishProperties.NETHER)
                .addRestriction(WorldRestrictions.NETHER_LAVA_CRIMSON_FOREST)
                .addModifier(new Nikdo53Modifier(1, ""))
                .addModifier(new BurnOnMissModifier(40, 10, 16, ""));
    }

    public static FishProperties netherLavaWarpedForestFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .withTextures(FishProperties.NETHER)
                .addRestriction(WorldRestrictions.NETHER_LAVA_WARPED_FOREST)
                .addModifier(new Nikdo53Modifier(1, ""))
                .addModifier(new BurnOnMissModifier(40, 10, 16, ""));
    }

    public static FishProperties netherLavaSoulSandValleyFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .withTextures(FishProperties.NETHER)
                .addRestriction(WorldRestrictions.NETHER_LAVA_SOUL_SAND_VALLEY)
                .addModifier(new Nikdo53Modifier(1, ""))
                .addModifier(new BurnOnMissModifier(40, 10, 16, ""));
    }

    public static FishProperties netherLavaBasaltDeltasFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .withTextures(FishProperties.NETHER)
                .addRestriction(WorldRestrictions.NETHER_LAVA_BASALT_DELTAS)
                .addModifier(new Nikdo53Modifier(1, ""))
                .addModifier(new BurnOnMissModifier(40, 10, 16, ""));
    }

    public static FishProperties overworldLushCavesFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .withTextures(FishProperties.CAVE)
                .addRestriction(WorldRestrictions.OVERWORLD_LUSH_CAVES)
                .addBait(BaitRestriction.LUSH_BAIT);
    }

    public static FishProperties overworldDeepDarkFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .withTextures(FishProperties.CAVE)
                .addModifier(new DeepDarkModifier(""))
                .withTextures(FishProperties.DEEP_DARK)
                .addRestriction(WorldRestrictions.OVERWORLD_DEEP_DARK)
                .addBait(BaitRestriction.SCULK_BAIT);
    }

    public static FishProperties overworldSurfaceFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_SURFACE);
    }

    public static FishProperties overworldSurfaceLava(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_LAVA_SURFACE)
                .addModifier(new BurnOnMissModifier(40, 10, 16, ""));
    }

    public static FishProperties overworldCavesFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .withTextures(FishProperties.CAVE)
                .addRestriction(WorldRestrictions.OVERWORLD_STONE_CAVES);
    }

    public static FishProperties overworldDripstoneCavesFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .withTextures(FishProperties.CAVE)
                .addRestriction(WorldRestrictions.OVERWORLD_DRIPSTONE_CAVES)
                .addBait(BaitRestriction.DRIPSTONE_BAIT);
    }

    public static FishProperties overworldUndergroundFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .withTextures(FishProperties.CAVE)
                .addRestriction(WorldRestrictions.OVERWORLD_UNDERGROUND);
    }

    public static FishProperties overworldUndergroundLava(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .withTextures(FishProperties.CAVE)
                .addRestriction(WorldRestrictions.OVERWORLD_LAVA_UNDERGROUND)
                .addModifier(new BurnOnMissModifier(40, 10, 16, ""));
    }

    public static FishProperties overworldMountainFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_MOUNTAIN);
    }

    public static FishProperties overworldSkyFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_SKY);
    }


    public static FishProperties overworldDeepslateFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .withTextures(FishProperties.CAVE)
                .addRestriction(WorldRestrictions.OVERWORLD_DEEPSLATE);
    }

    public static FishProperties overworldDeepslateLava(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_LAVA_DEEPSLATE)
                .addModifier(new BurnOnMissModifier(40, 10, 16, ""));
    }

    public static FishProperties overworldColdLakeFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .withTextures(FishProperties.ICY)
                .addRestriction(WorldRestrictions.OVERWORLD_COLD_LAKE)
                .withDifficulty(Difficulty.EASY.addModifiers(List.of(new FreezeOnMissModifier(40, 10, ""))));
    }

    public static FishProperties overworldWarmLakeFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_WARM_LAKE);
    }

    public static FishProperties overworldWarmMountainFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_WARM_LAKE);
    }

    public static FishProperties overworldColdMountainFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_COLD_MOUNTAIN)
                .withTextures(FishProperties.ICY)
                .withDifficulty(Difficulty.EASY.addModifiers(List.of(new FreezeOnMissModifier(40, 10, ""))));
    }

    public static FishProperties overworldColdOceanFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .withTextures(FishProperties.ICY)
                .withDifficulty(Difficulty.EASY.addModifiers(List.of(new FreezeOnMissModifier(40, 10, ""))))
                .addRestriction(WorldRestrictions.OVERWORLD_COLD_OCEAN);
    }

    public static FishProperties overworldColdRiverFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .withTextures(FishProperties.ICY)
                .withDifficulty(Difficulty.EASY.addModifiers(List.of(new FreezeOnMissModifier(40, 10, ""))))
                .addRestriction(WorldRestrictions.OVERWORLD_COLD_RIVER);
    }

    public static FishProperties overworldLakeFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_LAKE);
    }

    public static FishProperties overworldOceanFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_ALL_OCEANS);
    }

    public static FishProperties overworldWarmOceanFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_WARM_OCEAN);
    }

    public static FishProperties overworldDeepOceanFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_DEEP_OCEAN);
    }

    public static FishProperties overworldRiverFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_RIVER);
    }

    public static FishProperties overworldBeachFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_BEACH);
    }

    public static FishProperties overworldMushroomFieldsFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_MUSHROOM_FIELDS);
    }

    public static FishProperties overworldBambooJungleFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_BAMBOO_JUNGLE);
    }

    public static FishProperties flowerForestFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.FLOWER_FOREST);
    }

    public static FishProperties sunflowerPlainsFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.SUNFLOWER_PLAINS);
    }

    public static FishProperties overworldJungleFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_JUNGLE);
    }

    public static FishProperties overworldTaigaFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_TAIGA);
    }

    public static FishProperties overworldCherryGroveFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_CHERRY_GROVE)
                .addBait(BaitRestriction.CHERRY_BAIT);
    }

    public static FishProperties overworldSwampFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_SWAMPS)
                .addBait(BaitRestriction.MURKWATER_BAIT);
    }

    public static FishProperties overworldDarkForestFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_DARK_FOREST);
    }

    public static FishProperties overworldForestFish(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_FOREST);
    }

    public static FishProperties overworldVoidFishing(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.OVERWORLD_VOID);
    }

    public static FishProperties netherVoidFishing(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.NETHER_VOID);
    }

    public static FishProperties endVoidFishing(MaybeStack fish)
    {
        return FishProperties.empty().withFish(fish)
                .addRestriction(WorldRestrictions.END_VOID);
    }

    //endregion


    @Override
    public String getName()
    {
        return "FishingProperties";
    }
}
