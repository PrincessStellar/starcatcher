package com.wdiscute.starcatcher.datagen.fish;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.fishrestrictions.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;

import java.util.Map;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;


public class DGMinecraftFishes
{
    public static void bootstrap()
    {

        //ocean
        register(overworldOceanFish(new MaybeStack(Items.COD))
                .withBucketedFish(new MaybeStack(Items.COD_BUCKET))
                .withEntityToSpawn(EntityType.COD.builtInRegistryHolder())
                .withDifficulty(Difficulty.EASY_MOVING)
                .withSizeAndWeight(80, 40, 12000, 7000)
        );

        register(overworldOceanFish(new MaybeStack(Items.PUFFERFISH))
                .withBucketedFish(new MaybeStack(Items.PUFFERFISH_BUCKET))
                .withEntityToSpawn(EntityType.PUFFERFISH.builtInRegistryHolder())
                .withSizeAndWeight(70, 20, 10000, 3000)
                .withDifficulty(Difficulty.MEDIUM_VANISHING)
                .withRarity(Rarity.UNCOMMON)
        );


        //warm ocean
        register(overworldWarmOceanFish(new MaybeStack(Items.TROPICAL_FISH))
                .withBucketedFish(new MaybeStack(Items.TROPICAL_FISH_BUCKET))
                .withEntityToSpawn(EntityType.TROPICAL_FISH.builtInRegistryHolder())
                .withSizeAndWeight(70, 20, 10000, 3000)
                .withDifficulty(Difficulty.MEDIUM_VANISHING)
                .withRarity(Rarity.RARE)
                .withDifficulty(Difficulty.FOUR_AQUA)
        );

        //river
        register(overworldRiverFish(new MaybeStack(Items.SALMON))
                .withBucketedFish(new MaybeStack(Items.SALMON_BUCKET))
                .withEntityToSpawn(EntityType.SALMON.builtInRegistryHolder())
                .withSizeAndWeight(80, 40, 10000, 8000)
        );

        //mobs
        register(fish(new MaybeStack(Items.NETHER_STAR))
                .withAlwaysSpawnEntity()
                .withEntityToSpawn(EntityType.WITHER.builtInRegistryHolder())
                .withBaseChance(0)
                .addBait(new BaitRestriction(Map.of(U.rl("wither_skeleton_skull"), 200), ""))
                .withDifficulty(Difficulty.WITHER)
                .withItemToOverrideWith(new MaybeStack(SCItems.UNKNOWN_FISH))
                .withRarity(Rarity.LEGENDARY)
        );

        register(fish(new MaybeStack(Items.CREEPER_HEAD))
                .withAlwaysSpawnEntity()
                .withEntityToSpawn(EntityType.CREEPER.builtInRegistryHolder())
                .withBaseChance(0)
                .addRestrictions(DimensionRestriction.OVERWORLD)
                .addRestrictions(new BaitRestriction(Map.of(Starcatcher.rl("gunpowder_bait"), 200), ""))
                .withDifficulty(Difficulty.CREEPER)
                .withItemToOverrideWith(new MaybeStack(SCItems.UNKNOWN_FISH))
                .withRarity(Rarity.EPIC)
        );

        register(overworldSurfaceFish(new MaybeStack(Items.ROTTEN_FLESH))
                .withEntityToSpawn(EntityType.DROWNED.builtInRegistryHolder())
                .withBaseChance(1)
                .withDaytimeRestriction(DaytimeRestriction.NIGHT)
                .withWeather(WeatherRestriction.RAIN)
                .withHasGuideEntry(false)
                .withAlwaysSpawnEntity()
                .withSkipsMinigame());
    }
}
