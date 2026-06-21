package com.wdiscute.starcatcher.datagen.fish;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.CatchInfo;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.registry.SCEntities;
import com.wdiscute.starcatcher.registry.SCItems;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public final class FishRegistration
{
    private FishRegistration()
    {
    }

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
        FishProperties fp = prepare(input);

        context.register(key, fp);

        //todo compat keys?
    }

    private static FishProperties prepare(FishProperties fp)
    {
        fp = sortRestrictions(fp);
        fp = applyStarcaughtLogic(fp);
        //todo list for item tags?
        //fp = updateGlobalLists(fp);
        return fp;
    }

    private static FishProperties sortRestrictions(FishProperties fp)
    {
        return fp.withRestrictions(fp.restrictions().stream().sorted().toList());
    }

    private static FishProperties applyStarcaughtLogic(FishProperties fp)
    {
        ItemStack fish = fp.catchInfo().fish().toStack();
        String modId = fp.catchInfo().fish().rl().getNamespace();

        boolean isBucketable = SCItems.BUCKETABLE_FISHES_REGISTRY.getEntries().stream().map(e -> e.getDelegate().value()).anyMatch(fish::is);

        if (isBucketable && fp.catchInfo().fishEntryType() == CatchInfo.FishEntryType.FISH)
        {
            fp = fp.withCatchInfo(fp.catchInfo().withBucket(new MaybeStack(SCItems.STARCAUGHT_BUCKET)));

            fp = fp.withCatchInfo(fp.catchInfo().withEntityToSpawn(SCEntities.FISH));

            if (modId.equals("starcatcher"))
            {
                DGStarcatcherFishes.STARCATCHER_BUCKETABLE.add(fp);
            }
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
