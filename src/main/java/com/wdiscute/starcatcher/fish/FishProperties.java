package com.wdiscute.starcatcher.fish;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.SCConfig;
import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.bobentity.FishingBobEntity;
import com.wdiscute.starcatcher.compat.QualityFoodCompat;
import com.wdiscute.starcatcher.fishentity.FishEntity;
import com.wdiscute.starcatcher.io.*;
import com.wdiscute.starcatcher.registry.*;
import com.wdiscute.starcatcher.registry.catchmodifiers.AbstractCatchModifier;
import com.wdiscute.starcatcher.registry.fishrestrictions.*;
import com.wdiscute.starcatcher.registry.minigamemodifiers.AbstractMinigameModifier;
import com.wdiscute.starcatcher.registry.tackleskin.SCTackleSkins;
import com.wdiscute.starcatcher.tournament.TournamentHandler;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
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
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

//      <><|    <- fish
public record FishProperties(
        CatchInfo catchInfo,
        int baseChance,
        SizeAndWeight sizeWeight,
        Rarity rarity,
        List<AbstractFishRestriction> restrictions,
        Difficulty dif,
        boolean skipMinigame,
        boolean hasGuideEntry,
        ResourceLocation textures
)
{
    public static final ResourceLocation SURFACE = Starcatcher.rl("textures/gui/minigame/surface.png");
    public static final ResourceLocation NETHER = Starcatcher.rl("textures/gui/minigame/nether.png");
    public static final ResourceLocation END = Starcatcher.rl("textures/gui/minigame/end.png");

    public static final Codec<FishProperties> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    CatchInfo.CODEC.fieldOf("catch_info").forGetter(FishProperties::catchInfo),
                    Codec.INT.fieldOf("base_chance").forGetter(FishProperties::baseChance),
                    SizeAndWeight.CODEC.fieldOf("size_and_weight").forGetter(FishProperties::sizeWeight),
                    Rarity.CODEC.fieldOf("rarity").forGetter(FishProperties::rarity),
                    AbstractFishRestriction.ABSTRACT_PROCESSOR_CODEC.listOf().fieldOf("restrictions").forGetter(FishProperties::restrictions),
                    Difficulty.CODEC.fieldOf("difficulty").forGetter(FishProperties::dif),
                    Codec.BOOL.fieldOf("skips_minigame").forGetter(FishProperties::skipMinigame),
                    Codec.BOOL.fieldOf("has_guide_entry").forGetter(FishProperties::hasGuideEntry),
                    ResourceLocation.CODEC.fieldOf("textures").forGetter(FishProperties::textures)

            ).apply(instance, FishProperties::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, FishProperties> STREAM_CODEC = ExtraComposites.composite(
            CatchInfo.STREAM_CODEC, FishProperties::catchInfo,
            ByteBufCodecs.VAR_INT, FishProperties::baseChance,
            SizeAndWeight.STREAM_CODEC, FishProperties::sizeWeight,
            Rarity.STREAM_CODEC, FishProperties::rarity,
            ByteBufCodecs.fromCodec(AbstractFishRestriction.ABSTRACT_PROCESSOR_CODEC.listOf()), FishProperties::restrictions,
            Difficulty.STREAM_CODEC, FishProperties::dif,
            ByteBufCodecs.BOOL, FishProperties::skipMinigame,
            ByteBufCodecs.BOOL, FishProperties::hasGuideEntry,
            ResourceLocation.STREAM_CODEC, FishProperties::textures,
            FishProperties::new
    );

    @Override
    public @NotNull String toString()
    {
        return "[FishProperties] " + getDisplayName().getString();
    }

    public Component getDisplayName()
    {
        if (catchInfo.alwaysSpawnEntity())
            return Component.translatable("entity." + catchInfo.entityToSpawn().getRegisteredName().replace(":", "."));
        else
            return Component.translatable(catchInfo.fish().toStack().getDescriptionId());
    }

    public ResourceLocation toLoc(Level level)
    {
        return U.getRlFromFp(level, this);
    }

    public static FishProperties empty()
    {
        return new FishProperties(
                CatchInfo.DEFAULT,
                5,
                SizeAndWeight.DEFAULT,
                Rarity.COMMON,
                new ArrayList<>()
                {{
                    add(FluidRestriction.WATER);
                }},
                Difficulty.EASY,
                false,
                true,
                SURFACE
        );
    }

    //base constructors
    public FishProperties withCatchInfo(CatchInfo catchInfo)
    {
        return new FishProperties(catchInfo, this.baseChance, this.sizeWeight, this.rarity,
                this.restrictions, this.dif, this.skipMinigame, this.hasGuideEntry, this.textures);
    }

    public FishProperties withBaseChance(int baseChance)
    {
        return new FishProperties(this.catchInfo, baseChance, this.sizeWeight, this.rarity,
                this.restrictions, this.dif, this.skipMinigame, this.hasGuideEntry, this.textures);
    }

    public FishProperties withSizeAndWeight(SizeAndWeight sizeAndWeight)
    {
        return new FishProperties(this.catchInfo, this.baseChance, sizeAndWeight, this.rarity,
                this.restrictions, this.dif, this.skipMinigame, this.hasGuideEntry, this.textures);
    }

    public FishProperties withSizeAndWeight(float sizeAverage, float sizeDeviation, float weightAverage,
                                            float weightDeviation)
    {
        return new FishProperties(this.catchInfo, this.baseChance,
                new SizeAndWeight(sizeAverage, sizeDeviation, weightAverage, weightDeviation), this.rarity,
                this.restrictions, this.dif, this.skipMinigame, this.hasGuideEntry, this.textures);
    }

    public FishProperties withRarity(Rarity rarity)
    {
        return new FishProperties(this.catchInfo, this.baseChance, this.sizeWeight, rarity,
                this.restrictions, this.dif, this.skipMinigame, this.hasGuideEntry, this.textures);
    }

    public FishProperties withHasGuideEntry(boolean hasGuideEntry)
    {
        return new FishProperties(this.catchInfo, this.baseChance, this.sizeWeight, this.rarity,
                this.restrictions, this.dif, this.skipMinigame, hasGuideEntry, this.textures);
    }

    public FishProperties withRestrictions(List<AbstractFishRestriction> restrictions)
    {
        return new FishProperties(this.catchInfo, this.baseChance, this.sizeWeight, this.rarity,
                restrictions, this.dif, this.skipMinigame, this.hasGuideEntry, this.textures);
    }

    public FishProperties withDifficulty(Difficulty dif)
    {
        return new FishProperties(this.catchInfo, this.baseChance, this.sizeWeight, this.rarity,
                this.restrictions, dif.addModifiers(this.dif.modifiers()), this.skipMinigame, this.hasGuideEntry, this.textures);
    }

    public FishProperties withSkipsMinigame()
    {
        return new FishProperties(this.catchInfo, this.baseChance, this.sizeWeight, this.rarity,
                this.restrictions, this.dif, true, this.hasGuideEntry, this.textures);
    }

    public FishProperties lava()
    {
        removeWater();
        addLavaRestriction();
        return this;
    }

    public FishProperties removeWater()
    {
        restrictions.removeIf(o -> o.equals(FluidRestriction.WATER));
        return this;
    }

    public FishProperties addLavaRestriction()
    {
        this.restrictions.add(FluidRestriction.LAVA);
        return this;
    }

    public FishProperties addModifier
            (DeferredHolder<Supplier<AbstractMinigameModifier>, Supplier<AbstractMinigameModifier>> teleport)
    {
        return withDifficulty(dif.addModifiers(List.of(teleport)));
    }

    public FishProperties addRestriction(AbstractFishRestriction restriction)
    {
        this.restrictions.add(restriction);
        return this;
    }

    public FishProperties addRestriction(List<AbstractFishRestriction> restriction)
    {
        this.restrictions.addAll(restriction);
        return this;
    }

    public FishProperties addRestrictions(AbstractFishRestriction... restriction)
    {
        this.restrictions.addAll(List.of(restriction));
        return this;
    }

    public FishProperties withEntityToSpawn(Holder<EntityType<?>> entityTypeHolder)
    {
        return withCatchInfo(catchInfo.withEntityToSpawn(entityTypeHolder));
    }

    public FishProperties withPercentageChance(float chance)
    {
        restrictions.add(new ChancePercentageRestriction(0.05f));
        return this;
    }

    public FishProperties withItemToOverrideWith(MaybeStack override)
    {
        return withCatchInfo(catchInfo.withItemToOverrideWith(override));
    }

    public FishProperties withMaxLimit(int max)
    {
        restrictions.add(new CaughtLimitRestriction(max, ""));
        return this;
    }

    public FishProperties trophy()
    {
        return withCatchInfo(catchInfo.withFishType(CatchInfo.FishEntryType.TROPHY));
    }

    public FishProperties secret()
    {
        return withCatchInfo(catchInfo.withFishType(CatchInfo.FishEntryType.SECRET));
    }

    public FishProperties extra()
    {
        return withCatchInfo(catchInfo.withFishType(CatchInfo.FishEntryType.EXTRA));
    }

    public FishProperties withFish(MaybeStack fish)
    {
        return withCatchInfo(catchInfo.withFish(fish));
    }

    public FishProperties withBucketedFish(MaybeStack bucket)
    {
        return withCatchInfo(catchInfo.withBucket(bucket));
    }

    public FishProperties addRarityRestriction(RarityCountRestriction.RarityCount... rarityCount)
    {
        restrictions.add(new RarityCountRestriction(rarityCount));
        return this;
    }

    public FishProperties withAlwaysSpawnEntity()
    {
        return withCatchInfo(catchInfo.withAlwaysSpawnEntity());
    }

    public FishProperties addBait(BaitRestriction bait)
    {
        Map<ResourceLocation, Integer> map = new HashMap<>();
        AtomicReference<String> override = new AtomicReference<>();
        override.set("");

        //put baits already in the fp
        this.restrictions.forEach(o ->
        {
            if (o instanceof BaitRestriction be)
            {
                map.putAll(be.getBaits());
                override.set(be.getTranslationOverride());
            }
        });

        //put baits from method param
        map.putAll(bait.getBaits());
        override.set(bait.getTranslationOverride());

        this.restrictions.removeIf(o -> o instanceof BaitRestriction);
        this.restrictions.add(new BaitRestriction(map, override.get()));

        return this;
    }

    public FishProperties withSeasons(SeasonRestriction summerAutumn)
    {
        this.restrictions.add(summerAutumn);
        return this;
    }

    public FishProperties withWeather(WeatherRestriction rain)
    {
        this.restrictions.add(rain);
        return this;
    }

    public FishProperties withDaytimeRestriction(DaytimeRestriction midnight)
    {
        this.restrictions.add(midnight);
        return this;
    }


    public int calculateChance(Entity entity, Level level, ItemStack rod, AbstractFishRestriction.Context context)
    {
        //if dev worm return base chance
        if (SCDataComponents.getOrDefault(rod, SCDataComponents.BAIT, new SingleStackContainer(ItemStack.EMPTY)).stack().is(SCItems.DEV_WORM) &&
                catchInfo.fishEntryType().equals(CatchInfo.FishEntryType.FISH))
            return 1;

        int chance = baseChance;

        for (var restriction : restrictions)
            chance += restriction.getFishChance(chance, level, this, entity, rod, context);

        return chance;
    }

    public static ItemStack makeItemStack(ItemStack rod, FishProperties fp, int size, int weight, float percentile,
                                          boolean golden, Player player, boolean perfectCatch)
    {
        ItemStack bait = SCDataComponents.getOrDefault(rod, SCDataComponents.BAIT, SingleStackContainer.empty()).stack();
        boolean isBucketed = fp.catchInfo().bucketedFish().toStack().isEmpty() && bait.is(Tags.Items.BUCKETS);
        CaughtFishInfo caughtFishInfo = new CaughtFishInfo(size, weight, percentile, fp.rarity(), golden);

        //if bucketed fish
        if (isBucketed)
        {
            //if starcatcher bucketed fish
            if (fp.catchInfo.fish().toStack().is(SCTags.BUCKETABLE_FISHES))
            {
                ItemStack fish = fp.catchInfo().fish().toStack().copy();

                //quality food compat
                if (ModList.get().isLoaded("quality_food") && SCConfig.SAVE_DATA_TO_ITEMS.get())
                    QualityFoodCompat.addQuality(fish, player, player.level(), golden, perfectCatch, percentile);

                ItemStack bucket = fp.catchInfo.bucketedFish().toStack().copy();
                //only save data on fish stack if config is enabled
                if (SCConfig.SAVE_DATA_TO_ITEMS.get())
                    SCDataComponents.set(fish, SCDataComponents.CAUGHT_FISH_INFO, caughtFishInfo);
                SCDataComponents.set(bucket, SCDataComponents.BUCKETED_FISH, new SingleStackContainer(fish));
                return bucket;
            }
            else
                //non starcatcher bucketed fish
                return fp.catchInfo().bucketedFish().toStack().copy();
        }

        //normal itemstack
        ItemStack fish = fp.catchInfo().fish().toStack().copy();

        //store caught fish info data component
        if (fp.hasGuideEntry() && SCConfig.SAVE_DATA_TO_ITEMS.get())
            SCDataComponents.set(fish, SCDataComponents.CAUGHT_FISH_INFO, caughtFishInfo);

        //quality food compat
        if (ModList.get().isLoaded("quality_food"))
            QualityFoodCompat.addQuality(fish, player, player.level(), golden, perfectCatch, percentile);

        return fish;
    }

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

                //pick size, weight and golden
                float percentile = U.r.nextFloat(100);
                int size = SizeAndWeight.getRandomSize(fp, percentile);
                int weight = SizeAndWeight.getRandomWeight(fp, percentile);

                //golden if got lucky & hasn't caught golden yet
                boolean golden = U.r.nextFloat() < fp.sizeWeight().goldenChance() && FishCaughtCounter.canCatchGolden(fp, player);
                //golden if previous, or any modifier overrides it to be golden
                golden = golden || fbe.modifiers.stream().anyMatch(o -> o.shouldBeGolden(time, completedTreasure, perfectCatch, hits));

                if (fbe.modifiers.stream().anyMatch(AbstractCatchModifier::cancelGolden)) golden = false;

                //award fish counter
                FishCaughtCounter.awardFishCaughtCounter(fbe.fpToFish, fbe.rlToAwardUponFishingComplete, player, time, size, weight, percentile, perfectCatch, true, golden);

                //add score to tournaments
                TournamentHandler.addScore(player, fp, perfectCatch, percentile);

                //award exp
                int exp = fp.rarity().getXp();
                player.giveExperiencePoints(exp);

                List<ItemStack> items = new ArrayList<>();

                ResourceLocation location = fp.catchInfo.entityToSpawn().getKey().location();

                boolean canSpawnEntity;
                if (location.getNamespace().equals("starcatcher"))
                    //if entity is from starcatcher, can only spawn if it's a bucketable fish
                    canSpawnEntity = SCItems.BUCKETABLE_FISHES_REGISTRY.getEntries().stream().map(DeferredHolder::getDelegate).toList().contains(fp.catchInfo.fish());
                else
                    //if entity is not from starcatcher, then it can spawn
                    canSpawnEntity = true;

                //if should spawn entity
                if ((fp.catchInfo().alwaysSpawnEntity() ||
                        ModList.get().isLoaded("fishingreal") ||
                        fbe.modifiers.stream().anyMatch(AbstractCatchModifier::forceSpawnEntity)) && canSpawnEntity)
                {
                    Vec3 objPos = player.position().subtract(fbe.position());

                    double x = objPos.x / 25;
                    double y = objPos.y / 20;
                    double z = objPos.z / 25;

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
                        fe.setFish(getFishedItemStackFromFPForStarcatcherFishEntitySpecifically(fp, size, weight, percentile, golden));

                    entity.setPos(fbe.position().add(0, 1.2f, 0));

                    Vec3 vec3 = new Vec3(x, 0.7 + y, z);
                    entity.setDeltaMovement(vec3);
                    level.addFreshEntity(entity);
                }
                //if not entity then add base item stack
                else
                {
                    ItemStack is = makeItemStack(fbe.rod, fbe.fpToFish, size, weight, percentile, golden, player, perfectCatch);
                    items.add(is);

                    //modify base itemstack from modifiers
                    for (AbstractCatchModifier acm : fbe.modifiers) acm.modifyBaseItemStack(is);
                }

                //add items to list from modifiers
                for (AbstractCatchModifier acm : fbe.modifiers)
                    items.addAll(acm.addToFishedItems(time, perfectCatch, hits, completedTreasure, player));

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

    public ItemStack getTreasure(ServerPlayer player)
    {
        Registry<FishProperties> fishProperties = player.level().registryAccess().registryOrThrow(Starcatcher.FISH_REGISTRY_KEY);

        Treasure.TreasureInstance data = fishProperties.wrapAsHolder(this).getData(SCDataMaps.TREASURE);

        if (data == null) return ItemStack.EMPTY;

        return data.unpack(player);
    }

    private static ItemStack getFishedItemStackFromFPForStarcatcherFishEntitySpecifically(FishProperties fp,
                                                                                          int size, int weight, float percentile, boolean golden)
    {
        ItemStack is = fp.catchInfo().fish().toStack().copy();
        if (fp.hasGuideEntry() && SCConfig.SAVE_DATA_TO_ITEMS.get())
            SCDataComponents.set(is, SCDataComponents.CAUGHT_FISH_INFO, new CaughtFishInfo(size, weight, percentile, fp.rarity(), golden));
        return is;
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
        return registryAccess.registryOrThrow(Starcatcher.FISH_REGISTRY_KEY).stream().filter(o -> o.catchInfo.fishEntryType().equals(CatchInfo.FishEntryType.TROPHY)).toList();
    }

    public static List<FishProperties> getSecrets(Level level)
    {
        return getSecrets(level.registryAccess());
    }

    public static List<FishProperties> getSecrets(RegistryAccess registryAccess)
    {
        return registryAccess.registryOrThrow(Starcatcher.FISH_REGISTRY_KEY).stream().filter(o -> o.catchInfo.fishEntryType().equals(CatchInfo.FishEntryType.SECRET)).toList();
    }
}
