package com.wdiscute.starcatcher.compat;

import com.wdiscute.starcatcher.registry.SCItems;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public interface CreateCompat
{
    DeferredItem<Item> COGGILL = SCItems.registerBucketFish("coggill");
    DeferredItem<Item> MECHANICAL_BRASS_SNAIL = SCItems.registerBucketFish("mechanical_brass_snail");
    DeferredItem<Item> MECHANICAL_SNAIL = SCItems.registerBucketFish("mechanical_snail");
    DeferredItem<Item> PHILLIPSFISH = SCItems.registerBucketFish("phillipsfish");
    DeferredItem<Item> VALVE = SCItems.registerBucketFish("valve");
    DeferredItem<Item> PIPEHEAD = SCItems.registerBucketFish("pipehead");
    DeferredItem<Item> COGTOPUS = SCItems.registerBucketFish("cogtopus");
    DeferredItem<Item> EEL_DYNAMO = SCItems.registerBucketFish("eel_dynamo");
    DeferredItem<Item> DRIVE_PIKE = SCItems.registerBucketFish("drive_pike");
    DeferredItem<Item> BRASSGILL = SCItems.registerBucketFish("brassgill");
    DeferredItem<Item> MEKA_AGAVE_BREAM = SCItems.registerBucketFish("meka_agave_bream");


    static void register()
    {
    }
}
