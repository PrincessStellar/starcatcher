package com.wdiscute.starcatcher.fish;

import com.mojang.logging.LogUtils;
import com.wdiscute.starcatcher.SCConfig;
import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.bobentity.FishingBobEntity;
import com.wdiscute.starcatcher.compat.QualityFoodCompat;
import com.wdiscute.starcatcher.fishentity.FishEntity;
import com.wdiscute.starcatcher.io.CaughtFishInfo;
import com.wdiscute.starcatcher.io.FishCaughtCounter;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.registry.*;
import com.wdiscute.starcatcher.registry.catchmodifiers.AbstractCatchModifier;
import com.wdiscute.starcatcher.registry.tackleskin.SCTackleSkins;
import com.wdiscute.starcatcher.tournament.TournamentHandler;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.player.ItemFishedEvent;

import java.util.ArrayList;
import java.util.List;

public class FishApi
{
    public static void spawnFishFromPlayerFishing(ServerPlayer player, int time, boolean completedTreasure, boolean perfectCatch, int hits)
    {
        ServerLevel level = ((ServerLevel) player.level());

        if (SCDataAttachments.get(player, SCDataAttachments.FISHING_BOB).isEmpty()) return;

        Entity levelEntity = level.getEntity(SCDataAttachments.get(player, SCDataAttachments.FISHING_BOB).getUuid());
        if (levelEntity instanceof FishingBobEntity fbe)
        {
            if (time != -1)
            {
                FishProperties fp = fbe.fpToFish;

                //todo change this to be a neoforge event

                //award stat
                player.awardStat(Stats.FISH_CAUGHT);
                player.awardStat(SCStats.STARCAUGHT_FISH.get());

                //trigger modifiers
                fbe.modifiers.forEach(m -> m.onSuccessfulMinigameCompletion(player, time, completedTreasure, perfectCatch, hits));

                //play sound
                SCTackleSkins.get(level, fbe.rod).onSuccessfulMinigame(player);

                //if should cancel because of modifier, return
                if (fbe.modifiers.stream().anyMatch(m -> m.shouldCancelAfterSuccessfulMinigameCompletion(
                        player, time, completedTreasure, perfectCatch, hits))) return;

                //pick base percentile
                float percentile = U.r.nextFloat(100);

                //modify percentile from modifiers
                for (AbstractCatchModifier modifier : fbe.modifiers)
                    percentile = modifier.modifyPercentile(percentile);

                //pick size and weight based on percentile
                int size = SizeAndWeight.getRandomSize(fp, percentile);
                int weight = SizeAndWeight.getRandomWeight(fp, percentile);

                //golden if got lucky & hasn't caught golden yet
                boolean golden = U.r.nextFloat() < fp.sizeWeight().goldenChance() && FishCaughtCounter.canCatchGolden(fp, player);

                //golden if previous, or any modifier overrides it to be golden
                golden = golden || fbe.modifiers.stream().anyMatch(o -> o.shouldBeGolden(time, completedTreasure, perfectCatch, hits));

                //if any modifier cancels golden, then not golden
                if (fbe.modifiers.stream().anyMatch(AbstractCatchModifier::cancelGolden)) golden = false;

                //award fish counter entry to guide book
                FishCaughtCounter.awardFishCaughtCounter(fbe.fpToFish, fbe.rlToAwardUponFishingComplete, player, time, size, weight, percentile, perfectCatch, true, golden);

                //add score to tournaments
                TournamentHandler.addScore(player, fp, perfectCatch, percentile);

                //award exp
                int exp = fp.rarity().getXp();
                player.giveExperiencePoints(exp);

                //create list of items to fish up
                List<ItemStack> items = new ArrayList<>();

                //check if it can spawn entity
                boolean canSpawnEntity;
                ResourceLocation location = fp.catchInfo().entityToSpawn().getKey().location();
                if (location.getNamespace().equals("starcatcher"))
                    //if entity is from starcatcher, can only spawn if it's a bucketable fish (aka has a model)
                    canSpawnEntity = SCItems.BUCKETABLE_FISHES_REGISTRY.getEntries().stream().map(
                            o -> BuiltInRegistries.ITEM.getKey(o.getDelegate().value())
                    ).anyMatch(rl -> rl.equals(location));
                else
                    //if entity is not from starcatcher, then it can spawn
                    canSpawnEntity = true;


                //check if should spawn entity, can catch && anything wants to spawn it
                if (canSpawnEntity &&
                    (
                            fp.catchInfo().alwaysSpawnEntity()
                            || fbe.modifiers.stream().anyMatch(AbstractCatchModifier::forceSpawnEntity)
                            || ModList.get().isLoaded("fishingreal")
                    )
                )
                {
                    Vec3 pPos = player.position().subtract(fbe.position());

                    double x = pPos.x / 25;
                    double y = pPos.y / 20;
                    double z = pPos.z / 25;

                    x = Math.clamp(x, -1, 1);
                    y = Math.clamp(y, -1, 1);
                    z = Math.clamp(z, -1, 1);

                    x *= 2.5;
                    y *= 2;
                    z *= 2.5;

                    Entity entity = fp.catchInfo().entityToSpawn().value().create(level);

                    if (entity == null)
                    {
                        LogUtils.getLogger().warn("starcatcher doesnt like when the flag or whatever is not enabled");
                        return;
                    }

                    //set fish item if it's a starcatcher fish entity
                    if (entity instanceof FishEntity fe)
                        fe.setFish(makeItemStackNonBucket(fp, size, weight, percentile, golden, player, perfectCatch));

                    entity.setPos(fbe.position().add(0, 1.2f, 0));

                    Vec3 vec3 = new Vec3(x, 0.7 + y, z);
                    entity.setDeltaMovement(vec3);
                    level.addFreshEntity(entity);
                }
                //if not entity then add base item stack
                else
                {

                    ItemStack is = makeItemStack(fbe.rod, fbe.fpToFish, size, weight, percentile, golden, player, perfectCatch);

                    if (fbe.modifiers.stream().noneMatch(acm -> acm.shouldSkipAddingBaseItem(is)))
                        items.add(is);
                }

                //add items to list from modifiers
                for (AbstractCatchModifier acm : fbe.modifiers)
                    items.addAll(acm.addToFishedItems(fp, time, perfectCatch, hits, completedTreasure, player));

                //add treasure
                if (completedTreasure || fbe.modifiers.stream().anyMatch(acm -> acm.forceAwardTreasure(fbe, time, completedTreasure, perfectCatch, hits)))
                {
                    player.awardStat(SCStats.STARCAUGHT_TREASURES.get());
                    items.add(fbe.treasure);
                }

                //fire ItemFishedEvent for mod compat (e.g. PMMO). Throwaway FishingHook only exists to satisfy the event constructor.
                if (!items.isEmpty())
                {
                    FishingHook fakeHook = new FishingHook(player, level, 0, 0);
                    fakeHook.setPos(fbe.position());
                    ItemFishedEvent event = new ItemFishedEvent(items, 0, fakeHook);
                    NeoForge.EVENT_BUS.post(event);
                    if (event.isCanceled()) items.clear();
                    fakeHook.discard();
                }

                //spawn items from list
                for (ItemStack itemStackToSpawn : items)
                {
                    //make ItemEntities for fish item stack
                    ItemEntity itemFished = new ItemEntity(level, fbe.position().x, fbe.position().y + 1.2f, fbe.position().z, itemStackToSpawn);

                    //assign delta movement so fish flies towards player
                    double x = Math.clamp((player.position().x - fbe.position().x) / 25, -1, 1);
                    double y = Math.clamp((player.position().y - fbe.position().y) / 20, -1, 1);
                    double z = Math.clamp((player.position().z - fbe.position().z) / 25, -1, 1);
                    Vec3 vec3 = new Vec3(x, 0.7 + y, z);
                    itemFished.setDeltaMovement(vec3);

                    //add item entity to level
                    level.addFreshEntity(itemFished);
                }

            }
            else
            {
                //award stat of fish missed
                player.awardStat(SCStats.STARCAUGHT_FISH_MISSED.get());

                //if fish minigame failed/canceled
                fbe.modifiers.forEach(AbstractCatchModifier::onFailedMinigame);

                //play sound from tackle skin
                SCTackleSkins.get(level, fbe.rod).onFailedMinigame(player);
            }

            //consume bait if not bucket
            ItemStack bait = SCDataComponents.getOrDefault(fbe.rod, SCDataComponents.BAIT, SingleStackContainer.empty()).stack();
            if (!bait.is(Tags.Items.BUCKETS))
            {
                bait.shrink(1);
                SCDataComponents.set(fbe.rod, SCDataComponents.BAIT, new SingleStackContainer(bait));
            }

            //consume bait if bucket & bucketed fish available, and completed minigame (fish don't eat buckets!)
            if (bait.is(Tags.Items.BUCKETS) && !fbe.fpToFish.catchInfo().bucketedFish().toStack().isEmpty() && time != -1)
            {
                bait.shrink(1);
                SCDataComponents.set(fbe.rod, SCDataComponents.BAIT, new SingleStackContainer(bait));
            }

            fbe.kill();
        }

        SCDataAttachments.remove(player, SCDataAttachments.FISHING_BOB.get());
    }


    public static ItemStack makeItemStackNonBucket(FishProperties fp, int size, int weight, float percentile,
                                                   boolean golden, Player player, boolean perfectCatch)
    {
        //normal itemstack
        ItemStack fish = fp.catchInfo().fish().toStack();

        //quality food compat
        if (ModList.get().isLoaded("quality_food"))
            QualityFoodCompat.addQuality(fish, player, golden, perfectCatch, percentile);

        //store caught fish info data component
        if (fp.hasGuideEntry() && SCConfig.SAVE_DATA_TO_ITEMS.get())
            SCDataComponents.set(fish, SCDataComponents.CAUGHT_FISH_INFO, new CaughtFishInfo(size, weight, percentile, fp.rarity(), golden));

        return fish;
    }

    public static ItemStack makeItemStack(ItemStack rod, FishProperties fp, int size, int weight, float percentile,
                                          boolean golden, Player player, boolean perfectCatch)
    {
        ItemStack bait = SCDataComponents.getOrDefault(rod, SCDataComponents.BAIT, SingleStackContainer.empty()).stack();

        boolean canBeBucketed = !fp.catchInfo().bucketedFish().toStack().isEmpty() && bait.is(Tags.Items.BUCKETS);

        //if bucketed fish
        if (canBeBucketed)
        {
            ItemStack baseFish = fp.catchInfo().fish().toStack();

            CaughtFishInfo caughtFishInfo = new CaughtFishInfo(size, weight, percentile, fp.rarity(), golden);

            //if starcatcher bucketed fish
            if (fp.catchInfo().fish().toStack().is(SCTags.BUCKETABLE_FISHES))
            {
                ItemStack bucket = SCItems.STARCAUGHT_BUCKET.toStack();

                //quality food compat
                if (ModList.get().isLoaded("quality_food") && SCConfig.SAVE_DATA_TO_ITEMS.get())
                    QualityFoodCompat.addQuality(baseFish, player, golden, perfectCatch, percentile);

                //only save data on fish stack if config is enabled
                if (SCConfig.SAVE_DATA_TO_ITEMS.get())
                    SCDataComponents.set(bucket, SCDataComponents.CAUGHT_FISH_INFO, caughtFishInfo);

                SCDataComponents.set(bucket, SCDataComponents.BUCKETED_FISH, new SingleStackContainer(baseFish));
                return bucket;
            }
            else
                //non starcatcher bucketed fish
                return fp.catchInfo().bucketedFish().toStack().copy();
        }

        //if non bucket fish, make normal itemstack
        return makeItemStackNonBucket(fp, size, weight, percentile, golden, player, perfectCatch);
    }

    public static ItemStack getTreasure(ServerPlayer player, FishProperties fp)
    {
        Registry<FishProperties> fishProperties = player.level().registryAccess().registryOrThrow(Starcatcher.FISH_REGISTRY_KEY);

        Treasure.TreasureInstance data = fishProperties.wrapAsHolder(fp).getData(SCDataMaps.TREASURE);

        if (data == null) return ItemStack.EMPTY;

        return data.unpack(player);
    }

    public static ResourceLocation getKey(Level level, FishProperties fp)
    {
        return level.registryAccess().registryOrThrow(Starcatcher.FISH_REGISTRY_KEY).getKey(fp);
    }

    public static List<FishProperties> getFishes(RegistryAccess registryAccess)
    {
        return registryAccess.registryOrThrow(Starcatcher.FISH_REGISTRY_KEY).stream()
                .filter(o -> o.catchInfo().fishEntryType().equals(CatchInfo.FishEntryType.FISH)).toList();
    }

    public static List<FishProperties> getFishes(Level level)
    {
        return getFishes(level.registryAccess());
    }

    public static Registry<FishProperties> getRegistry(Level level)
    {
        return level.registryAccess().registryOrThrow(Starcatcher.FISH_REGISTRY_KEY);
    }

    public static FishProperties getFP(RegistryAccess registryAccess, ResourceLocation rl)
    {
        return registryAccess.registryOrThrow(Starcatcher.FISH_REGISTRY_KEY).get(rl);
    }

    public static FishProperties getFP(Level level, ResourceLocation rl)
    {
        return getFP(level.registryAccess(), rl);
    }

    public static List<FishProperties> getNonFishes(RegistryAccess registryAccess)
    {
        return registryAccess.registryOrThrow(Starcatcher.FISH_REGISTRY_KEY).stream()
                .filter(o -> !o.catchInfo().fishEntryType().equals(CatchInfo.FishEntryType.FISH)).toList();
    }

    public static List<FishProperties> getNonFishes(Level level)
    {
        return getNonFishes(level.registryAccess());
    }

    public static List<FishProperties> getAllFPs(Level level)
    {
        return getAllFPs(level.registryAccess());
    }

    public static List<FishProperties> getAllFPs(RegistryAccess registryAccess)
    {
        return registryAccess.registryOrThrow(Starcatcher.FISH_REGISTRY_KEY).stream().toList();
    }

    public static List<FishProperties> getTrophies(Level level)
    {
        return getTrophies(level.registryAccess());
    }

    public static List<FishProperties> getTrophies(RegistryAccess registryAccess)
    {
        return registryAccess.registryOrThrow(Starcatcher.FISH_REGISTRY_KEY).stream().filter(o -> o.catchInfo().fishEntryType().equals(CatchInfo.FishEntryType.TROPHY)).toList();
    }

    public static List<FishProperties> getSecrets(Level level)
    {
        return getSecrets(level.registryAccess());
    }

    public static List<FishProperties> getSecrets(RegistryAccess registryAccess)
    {
        return registryAccess.registryOrThrow(Starcatcher.FISH_REGISTRY_KEY).stream().filter(o -> o.catchInfo().fishEntryType().equals(CatchInfo.FishEntryType.SECRET)).toList();
    }
}
