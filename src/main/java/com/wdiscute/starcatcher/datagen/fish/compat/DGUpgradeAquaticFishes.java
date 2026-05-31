package com.wdiscute.starcatcher.datagen.fish.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.fish.MaybeStack;
import com.wdiscute.starcatcher.fish.Rarity;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.fishrestrictions.BaitRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.BiomeRestriction;
import com.wdiscute.starcatcher.registry.fishrestrictions.DimensionRestriction;
import net.minecraft.world.item.Item;

import java.util.List;

import static com.wdiscute.starcatcher.datagen.fish.DGSCFishProperties.*;

public class DGUpgradeAquaticFishes
{
    public static void bootstrap()
    {
        //
        //,--. ,--.                                     ,--.               ,---.                               ,--.   ,--.
        //|  | |  |  ,---.   ,---.  ,--.--.  ,--,--.  ,-|  |  ,---.       /  O  \   ,---.  ,--.,--.  ,--,--. ,-'  '-. `--'  ,---.
        //|  | |  | | .-. | | .-. | |  .--' ' ,-.  | ' .-. | | .-. :     |  .-.  | | .-. | |  ||  | ' ,-.  | '-.  .-' ,--. | .--'
        //'  '-'  ' | '-' ' ' '-' ' |  |    \ '-'  | \ `-' | \   --.     |  | |  | ' '-' | '  ''  ' \ '-'  |   |  |   |  | \ `--.
        // `-----'  |  |-'  .`-  /  `--'     `--`--'  `---'   `----'     `--' `--'  `-|  |  `----'   `--`--'   `--'   `--'  `---'
        //          `--'    `---'                                                     `--'

        register(fish(new MaybeStack("upgrade_aquatic", "pike"))
                .withBucketedFish(new MaybeStack("upgrade_aquatic", "pike_bucket"))
                .withEntityToSpawn(U.holderEntity("upgrade_aquatic", "pike"))
                .withSizeAndWeight(75, 20, 5000, 3000)
                .withDifficulty(Difficulty.EASY_MOVING)
                .withRarity(Rarity.COMMON)
                .addRestrictions(DimensionRestriction.OVERWORLD,
                        new BiomeRestriction(List.of(), List.of(U.rl("upgrade_aquatic", "biome/has_spawn/pike")), List.of(), List.of(), ""))
        );

        register(fish(new MaybeStack("upgrade_aquatic", "perch"))
                .withBucketedFish(new MaybeStack("upgrade_aquatic", "perch_bucket"))
                .withEntityToSpawn(U.holderEntity("upgrade_aquatic", "perch"))
                .withSizeAndWeight(27.0f, 11, 500, 352)
                .withDifficulty(Difficulty.EASY_MOVING)
                .withRarity(Rarity.COMMON)
                .addRestrictions(DimensionRestriction.OVERWORLD,
                        new BiomeRestriction(List.of(), List.of(U.rl("upgrade_aquatic", "biome/has_spawn/perch")), List.of(), List.of(), ""))
        );

        register(fish(new MaybeStack("upgrade_aquatic", "lionfish"))
                .withBucketedFish(new MaybeStack("upgrade_aquatic", "lionfish_bucket"))
                .withEntityToSpawn(U.holderEntity("upgrade_aquatic", "lionfish"))
                .withSizeAndWeight(27.0f, 11, 500, 352)
                .withDifficulty(Difficulty.FOUR_BIG_VANISHING)
                .withRarity(Rarity.UNCOMMON)
                .addRestrictions(DimensionRestriction.OVERWORLD,
                        new BiomeRestriction(List.of(), List.of(U.rl("upgrade_aquatic", "biome/has_spawn/lionfish")), List.of(), List.of(), ""))
        );

        register(overworldWarmOceanFish(new MaybeStack("upgrade_aquatic", "thrasher_tooth"))
                .withBaseChance(0)
                .withEntityToSpawn(U.holderEntity("upgrade_aquatic", "great_thrasher"))
                .addRestrictions(BaitRestriction.ALMIGHTY_WORM)
                .withSizeAndWeight(28, 8, 260, 60)
                .withDifficulty(Difficulty.FOUR_STONE_SPOTS)
                .withItemToOverrideWith(new MaybeStack(SCItems.UNKNOWN_FISH))
        );
    }
}
