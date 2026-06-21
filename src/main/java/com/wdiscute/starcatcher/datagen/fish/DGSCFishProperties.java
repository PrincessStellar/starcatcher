package com.wdiscute.starcatcher.datagen.fish;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.datagen.fish.compat.*;
import com.wdiscute.starcatcher.fish.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class DGSCFishProperties extends DatapackBuiltinEntriesProvider
{
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(Starcatcher.FISH_REGISTRY_KEY, DGSCFishProperties::bootstrap);

    public DGSCFishProperties(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> registries)
    {
        super(
                output,
                registries,
                BUILDER,
                Set.of(
                        "minecraft",

                        Starcatcher.MOD_ID,

                        "tide",
                        "aquaculture",
                        "fishofthieves",
                        "netherdepthsupgrade",
                        "environmental",
                        "miners_delight",
                        "crittersandcompanions",
                        "hybrid_aquatic",

                        "alexscaves",
                        "collectorsreap",
                        "sullysmod",
                        "upgrade_aquatic",
                        "spawn",
                        "unusualfishmod"
                )
        );
    }

    public static void bootstrap(BootstrapContext<FishProperties> context)
    {
        //vanilla
        DGTrophies.bootstrap(context);
        DGMinecraftFishes.bootstrap(context);

        //starcatcher
        DGStarcatcherFishes.bootstrap(context);

        //starcatcher compat
        DGCreateFishes.bootstrap(context);

        //compat
        DGTideFishes.bootstrap(context);
        DGAquacultureFishes.bootstrap(context);
        DGFishOfThievesFishes.bootstrap(context);
        DGNetherDepthsUpgradeFishes.bootstrap(context);
        DGEnvironmentalFishes.bootstrap(context);
        DGMinersDelightFishes.bootstrap(context);
        DGCrittersAndCompanionsFishes.bootstrap(context);
        DGHybridAquaticFishes.bootstrap(context);
        //todo dont forget to add to the list of ids
        //DGEternalStarlightFishes.bootstrap(context);

        DGAlexsCavesFishes.bootstrap(context);
        DGCollectorsReapFishes.bootstrap(context);
        DGSullysModFishes.bootstrap(context);
        DGBetterEndFishes.bootstrap(context);
        DGUpgradeAquaticFishes.bootstrap(context);
        DGSpawnFishes.bootstrap(context);
        DGUnusualFishFishes.bootstrap(context);
    }

    @Override
    public String getName()
    {
        return "FishingProperties";
    }
}
