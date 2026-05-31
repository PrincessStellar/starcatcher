package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.registry.SCItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import java.util.List;
import java.util.Optional;

public class VanillaLootModifier extends AbstractCatchModifier
{
    @Override
    public void afterChoosingTheCatch(List<FishProperties> immutableAvailable)
    {
        this.instance.fpToFish = FishProperties.empty().withItemToOverrideWith(new MaybeStack(SCItems.UNKNOWN_FISH));
        this.instance.rlToAwardUponFishingComplete = Starcatcher.rl("missingno");
    }

    @Override
    public void modifyBaseItemStack(ItemStack is)
    {
        //removes the base item fished
        is.shrink(100);
    }

    @Override
    public List<ItemStack> addToFishedItems(int time, boolean perfectCatch, int hits, boolean completedTreasure, Player player)
    {
        Level level = instance.level();

        LootParams lootparams = new LootParams.Builder((ServerLevel) level)
                .withParameter(LootContextParams.ORIGIN, instance.position())
                .withParameter(LootContextParams.TOOL, instance.rod)
                .withParameter(LootContextParams.THIS_ENTITY, instance)
                .withParameter(LootContextParams.ATTACKING_ENTITY, instance.getOwner())
                .withLuck(player.getLuck())
                .create(LootContextParamSets.FISHING);

        LootTable table = level.getServer().reloadableRegistries().getLootTable(BuiltInLootTables.FISHING);

        Optional<ItemStack> any = table.getRandomItems(lootparams).stream().findAny();
        return any.isPresent() ? List.of(any.get()) : List.of();
    }
}
