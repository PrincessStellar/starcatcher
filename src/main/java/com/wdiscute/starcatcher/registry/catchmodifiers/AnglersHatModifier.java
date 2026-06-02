package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.compat.ReliquifiedArtifactsCompat;
import com.wdiscute.starcatcher.fish.CatchInfo;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;

public class AnglersHatModifier extends AbstractCatchModifier
{
    public static final MapCodec<AnglersHatModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.STRING.fieldOf("translation_override").forGetter(o -> o.translationOverride)
            ).apply(instance, AnglersHatModifier::new));

    public AnglersHatModifier(String translationOverrider)
    {
        super(translationOverrider);
    }

    @Override
    public DeferredHolder<AbstractCatchModifier, AbstractCatchModifier> getRegistryHolder()
    {
        return SCCatchModifiers.ANGLERS_HAT;
    }

    @Override
    public MapCodec<? extends AbstractCatchModifier> codec()
    {
        return CODEC;
    }

    @Override
    public boolean forceAwardTreasure(com.wdiscute.starcatcher.bobentity.FishingBobEntity fbe, int time, boolean completedTreasure, boolean perfectCatch, int hits)
    {
        if (completedTreasure) return false;
        if (ModList.get().isLoaded("reliquified_artifacts"))
        {
            return ReliquifiedArtifactsCompat.shouldAwardBonusTreasure(instance.player);
        }
        return false;
    }

    @Override
    public List<ItemStack> addToFishedItems(int time, boolean perfectCatch, int hits, boolean completedTreasure, Player player)
    {
        if (instance.fpToFish.catchInfo().alwaysSpawnEntity() ||
                instance.modifiers.stream().anyMatch(AbstractCatchModifier::forceSpawnEntity) ||
                !instance.fpToFish.hasGuideEntry() || instance.fpToFish.catchInfo().fishEntryType()
                .equals(CatchInfo.FishEntryType.FISH)) return List.of();

        if (ModList.get().isLoaded("reliquified_artifacts"))
        {
            return ReliquifiedArtifactsCompat.getBonusCatchItems(player, instance);
        }
        return List.of();
    }

    @Override
    public void onSuccessfulMinigameCompletion(ServerPlayer player, int time, boolean completedTreasure, boolean perfectCatch, int hits)
    {
        if (ModList.get().isLoaded("reliquified_artifacts"))
        {
            ReliquifiedArtifactsCompat.awardRelicXP(player, completedTreasure);
        }
    }
}
