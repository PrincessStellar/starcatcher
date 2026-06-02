package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.bobentity.FishingBobEntity;
import com.wdiscute.starcatcher.fish.FishProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

public abstract class AbstractCatchModifier
{
    FishingBobEntity instance;
    final String translationOverride;

    public static final Codec<AbstractCatchModifier> CODEC = ResourceLocation.CODEC
            .dispatch(processor -> processor.getRegistryHolderOrThrow().getId(),
                    loc ->
                    {
                        if(Starcatcher.CATCH_MODIFIERS_REGISTRY.get(loc) == null)
                        {
                            LogUtils.getLogger().error("Catch Modifier {} is not registered! " +
                                    "Using empty modifier instead.", loc);
                            return EmptyCatchModifier.CODEC;
                        }
                        return Starcatcher.CATCH_MODIFIERS_REGISTRY.get(loc).getCodecOrThrow();
                    });

    protected AbstractCatchModifier(String translationOverride)
    {
        this.translationOverride = translationOverride;
    }

    public List<Component> getNonOverriddenDescription()
    {
        return List.of(Component.translatable("tooltip.modifier." + getRegistryHolder().getId().toLanguageKey()));
    }

    public final List<Component> getDescription()
    {
        if(translationOverride.equals("hide")) return List.of();

        if(translationOverride.isEmpty())
        {
            return getNonOverriddenDescription();
        }

        return List.of(Component.translatable(translationOverride));
    }



    public abstract DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolder();

    public abstract MapCodec<? extends AbstractCatchModifier> codec();

    public DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolderOrThrow()
    {
        var holder = getRegistryHolder();
        if (holder == null)
        {
            throw new IllegalStateException("Catch Modifier " + this + " does not have a registry holder!");
        }
        return holder;
    }

    public MapCodec<? extends AbstractCatchModifier> getCodecOrThrow()
    {
        var codec = codec();
        if (codec == null)
        {
            throw new IllegalStateException("Catch Modifier " + this + " does not have a codec!");
        }
        return codec;
    }


    public void onAdd(FishingBobEntity fishingBobEntity)
    {
        this.instance = fishingBobEntity;
    }

    public int adjustMinTicksToFish(int minTicksToFish)
    {
        return minTicksToFish;
    }

    public int adjustMaxTicksToFish(int maxTicksToFish)
    {
        return maxTicksToFish;
    }

    public float adjustChanceToFishEachTick(float chanceToFishEachTick)
    {
        return chanceToFishEachTick;
    }

    public boolean survivesLava()
    {
        return false;
    }

    public void onReelStart()
    {

    }

    public List<FishProperties> modifyAvailablePool(List<FishProperties> available)
    {
        return available;
    }

    public boolean clearDefaultPool()
    {
        return true;
    }

    public void afterChoosingTheCatch(List<FishProperties> immutableAvailable)
    {
    }

    public boolean forceSkipMinigame(Boolean enableMinigameConfig)
    {
        return false;
    }

    public boolean shouldStopFishing()
    {
        return false;
    }

    public boolean forceSpawnEntity()
    {
        return false;
    }

    public void onFailedMinigame()
    {
    }

    public void onSuccessfulMinigameCompletion(ServerPlayer player, int time, boolean completedTreasure, boolean perfectCatch, int hits)
    {
    }

    public boolean shouldCancelAfterSuccessfulMinigameCompletion(ServerPlayer player, int time, boolean completedTreasure, boolean perfectCatch, int hits)
    {
        return false;
    }

    public boolean shouldCancelBeforeSkipsMinigameCheck()
    {
        return false;
    }

    public boolean forceAwardTreasure(FishingBobEntity fbe, int time, boolean completedTreasure, boolean perfectCatch, int hits)
    {
        return false;
    }

    public boolean shouldBeGolden()
    {
        return false;
    }

    public boolean cancelGolden()
    {
        return false;
    }

    public void modifyBaseItemStack(ItemStack is)
    {

    }

    public List<ItemStack> addToFishedItems(int time, boolean perfectCatch, int hits, boolean completedTreasure, Player player)
    {
        return List.of();
    }

    public ItemStack modifyTreasure(ItemStack originalTreasure, FishProperties fp, Player player, Entity entity)
    {
        return originalTreasure;
    }

    public boolean shouldHideCatch()
    {
        return false;
    }
}
