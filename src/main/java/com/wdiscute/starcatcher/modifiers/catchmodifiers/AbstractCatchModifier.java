package com.wdiscute.starcatcher.modifiers.catchmodifiers;

import com.wdiscute.starcatcher.bobentity.FishingBobEntity;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public abstract class AbstractCatchModifier implements Modifier
{
    FishingBobEntity instance;
    final String translationOverride;

    protected AbstractCatchModifier(String translationOverride)
    {
        this.translationOverride = translationOverride;
    }

    public List<Component> getNonOverriddenDescription(boolean shift)
    {
        return List.of(Component.translatable("tooltip.modifier." + getIdentifier().toLanguageKey()));
    }

    public final List<Component> getDescription(boolean shift)
    {
        if(translationOverride.equals("hide")) return List.of();

        if(translationOverride.isEmpty())
        {
            return getNonOverriddenDescription(shift);
        }

        return List.of(Component.translatable(translationOverride));
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
        return false;
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

    public boolean shouldBeGolden(int time, boolean treasure, boolean perfect, int hits)
    {
        return false;
    }

    public boolean cancelGolden()
    {
        return false;
    }

    public boolean shouldSkipAddingBaseItem(ItemStack is)
    {
        return false;
    }

    public List<ItemStack> addToFishedItems(FishProperties fp, int time, boolean perfectCatch, int hits, boolean completedTreasure, Player player)
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

    public float modifyPercentile(float percentile)
    {
        return percentile;
    }

    public boolean noGravity()
    {
        return false;
    }

    public Vec3 modifyThrowVec(Vec3 vec3)
    {
        return vec3;
    }
}
