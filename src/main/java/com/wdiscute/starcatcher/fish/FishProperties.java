package com.wdiscute.starcatcher.fish;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.io.*;
import com.wdiscute.starcatcher.registry.*;
import com.wdiscute.starcatcher.registry.fishrestrictions.*;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

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
    public static final ResourceLocation CAVE = Starcatcher.rl("textures/gui/minigame/cave.png");
    public static final ResourceLocation ICY = Starcatcher.rl("textures/gui/minigame/icy.png");
    public static final ResourceLocation DEEP_DARK = Starcatcher.rl("textures/gui/minigame/deep_dark.png");
    public static final ResourceLocation END = Starcatcher.rl("textures/gui/minigame/end.png");
    public static final ResourceLocation END_VOID = Starcatcher.rl("textures/gui/minigame/end_void.png");

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
                new ArrayList<>(),
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

    public FishProperties withTextures(ResourceLocation textures)
    {
        return new FishProperties(this.catchInfo, this.baseChance, this.sizeWeight, this.rarity,
                this.restrictions, this.dif, this.skipMinigame, this.hasGuideEntry, textures);
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

    public FishProperties withDifficultyRaw(Difficulty dif)
    {
        return new FishProperties(this.catchInfo, this.baseChance, this.sizeWeight, this.rarity,
                this.restrictions, dif, this.skipMinigame, this.hasGuideEntry, this.textures);
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

    public FishProperties addModifier(Modifier modifier)
    {
        return withDifficultyRaw(dif.addModifiers(List.of(modifier)));
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
                map.putAll(be.baits);
                override.set(be.translationOverride);
            }
        });

        //put baits from method param
        map.putAll(bait.baits);
        override.set(bait.translationOverride);

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
        return FishApi.calculateChance(this, entity, level, rod, context);
    }
}
