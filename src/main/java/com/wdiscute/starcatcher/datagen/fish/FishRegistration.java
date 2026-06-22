package com.wdiscute.starcatcher.datagen.fish;

import com.mojang.datafixers.util.Pair;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.CatchInfo;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.registry.SCEntities;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.items.StarcaughtBucket;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class FishRegistration
{
    public static final List<FishProperties> ALL_FISHABLE = new ArrayList<>();
    public static final List<FishProperties> STARCATCHER_FISHABLE = new ArrayList<>();
    public static final List<FishProperties> STARCATCHER_BUCKETABLE = new ArrayList<>();

    public static void register(BootstrapContext<FishProperties> context, FishProperties fp)
    {
        registerInternal(context, key(fp), fp, "");
    }

    public static void register(BootstrapContext<FishProperties> context, FishProperties fp, String requiredModId)
    {
        registerInternal(context, key(fp), fp, requiredModId);
    }

    public static void register(BootstrapContext<FishProperties> context, ResourceKey<FishProperties> key, FishProperties properties, String requiredModId)
    {
        registerInternal(context, key, properties, "");
    }

    private static void registerInternal(BootstrapContext<FishProperties> context,
                                         ResourceKey<FishProperties> key,
                                         FishProperties input,
                                         String requiredModId)
    {

        //if running during conditions generation, dont register as context is null
        if (DGSCFishProperties.runningOnlyForConditions)
        {
            //if has a required modid,
            if (!requiredModId.isEmpty())
                DGSCFishProperties.conditionsFps.add(Pair.of(key, requiredModId));
            return;
        }

        FishProperties fp = prepare(input);
        context.register(key, fp);
    }

    private static FishProperties prepare(FishProperties fp)
    {
        fp = sortRestrictions(fp);
        fp = applyStarcaughtLogic(fp);
        //todo list for item tags?
        addToLists(fp);
        return fp;
    }

    private static void addToLists(FishProperties fp)
    {
        ALL_FISHABLE.add(fp);

        if (fp.catchInfo().fish().rl().getNamespace().equals("starcatcher") && fp.catchInfo().fishEntryType().equals(CatchInfo.FishEntryType.FISH))
            STARCATCHER_FISHABLE.add(fp);

        if (SCItems.BUCKETABLE_FISHES_REGISTRY.getEntries().stream().map(o -> o.getKey().location())
                .anyMatch(o -> fp.catchInfo().fish().rl().equals(o)))
            STARCATCHER_BUCKETABLE.add(fp);

    }

    private static FishProperties sortRestrictions(FishProperties fp)
    {
        return fp.withRestrictions(fp.restrictions().stream().sorted().toList());
    }

    private static FishProperties applyStarcaughtLogic(FishProperties fp)
    {
        ItemStack fish = fp.catchInfo().fish().toStack();

        boolean isBucketable = SCItems.BUCKETABLE_FISHES_REGISTRY.getEntries().stream().map(e -> e.getDelegate().value()).anyMatch(fish::is);

        if (isBucketable && fp.catchInfo().fishEntryType() == CatchInfo.FishEntryType.FISH)
        {
            fp = fp.withCatchInfo(fp.catchInfo().withBucket(new MaybeStack(StarcaughtBucket.getBucketForStack(fish))));

            fp = fp.withCatchInfo(fp.catchInfo().withEntityToSpawn(SCEntities.FISH));
        }

        return fp;
    }

    public static ResourceKey<FishProperties> key(ResourceLocation id)
    {
        return ResourceKey.create(Starcatcher.FISH_REGISTRY_KEY, id);
    }

    public static ResourceKey<FishProperties> key(FishProperties fp)
    {
        return ResourceKey.create(Starcatcher.FISH_REGISTRY_KEY, fp.catchInfo().fish().rl());
    }
}
