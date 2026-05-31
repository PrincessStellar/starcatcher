package com.wdiscute.starcatcher.registry.catchmodifiers;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.CatchInfo;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.MaybeStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;

import java.util.List;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.fish;

public class AddCreeperModifier extends AbstractCatchModifier
{

    @Override
    public boolean clearDefaultPool()
    {
        return true;
    }

    @Override
    public List<FishProperties> modifyAvailablePool(List<FishProperties> available)
    {
        if (U.r.nextInt(25) == 6)
            return List.of(
                    fish(new MaybeStack(Items.CREEPER_HEAD))
                            .withCatchInfo(CatchInfo.DEFAULT
                                    .withAlwaysSpawnEntity()
                                    .withEntityToSpawn(EntityType.CREEPER.builtInRegistryHolder()))
                            .withDifficulty(Difficulty.CREEPER)
                            .withSkipsMinigame()
            );

        return super.modifyAvailablePool(available);
    }
}
