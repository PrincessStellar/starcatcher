package com.wdiscute.starcatcher.registry.fishing.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.fishing.FishingPropertiesRegistry;
import com.wdiscute.starcatcher.storage.FishProperties;

public class DGUpgradeAquaticFishes extends FishingPropertiesRegistry
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

        register(fish(U.holderItem("upgrade_aquatic", "pike"))
                .withBucketedFish(U.holderItem("upgrade_aquatic", "pike_bucket"))
                .withEntityToSpawn(U.holderEntity("upgrade_aquatic", "pike"))
                .withSizeAndWeight(FishProperties.sizeWeight(75, 20, 5000, 3000))
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                .withRarity(FishProperties.Rarity.COMMON)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withBiomesTags(U.rl("upgrade_aquatic", "biome/has_spawn/pike")))
        );

        register(fish(U.holderItem("upgrade_aquatic", "perch"))
                .withBucketedFish(U.holderItem("upgrade_aquatic", "perch_bucket"))
                .withEntityToSpawn(U.holderEntity("upgrade_aquatic", "perch"))
                .withSizeAndWeight(FishProperties.sizeWeight(27.0f, 11, 500, 352))
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                .withRarity(FishProperties.Rarity.COMMON)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withBiomesTags(U.rl("upgrade_aquatic", "biome/has_spawn/perch")))
        );

        register(fish(U.holderItem("upgrade_aquatic", "lionfish"))
                .withBucketedFish(U.holderItem("upgrade_aquatic", "lionfish_bucket"))
                .withEntityToSpawn(U.holderEntity("upgrade_aquatic", "lionfish"))
                .withSizeAndWeight(FishProperties.sizeWeight(27.0f, 11, 500, 352))
                .withDifficulty(FishProperties.Difficulty.FOUR_BIG_VANISHING)
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withBiomesTags(U.rl("upgrade_aquatic", "biome/has_spawn/lionfish")))
        );

        register(overworldWarmOceanFish(U.holderItem("upgrade_aquatic", "thrasher_tooth"))
                .withBaseChance(0)
                .withEntityToSpawn(U.holderEntity("upgrade_aquatic", "great_thrasher"))
                .withBaitRestrictions(FishProperties.BaitRestrictions.DEFAULT
                        .withCorrectBait(SCItems.ALMIGHTY_WORM.getId())
                        .withCorrectBaitChanceAdded(5))
                .withSizeAndWeight(FishProperties.sizeWeight(28, 8, 260, 60))
                .withDifficulty(FishProperties.Difficulty.FOUR_STONE_SPOTS)
                .withItemToOverrideWith(SCItems.UNKNOWN_FISH)
        );
    }
}
