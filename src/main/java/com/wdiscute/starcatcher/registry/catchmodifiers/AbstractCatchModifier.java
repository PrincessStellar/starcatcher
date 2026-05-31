package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.wdiscute.starcatcher.bobentity.FishingBobEntity;
import com.wdiscute.starcatcher.fish.FishProperties;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public abstract class AbstractCatchModifier
{
    FishingBobEntity instance;

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

    public void onReel()
    {

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
