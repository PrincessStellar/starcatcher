package com.wdiscute.starcatcher.mixin;

import com.wdiscute.libtooltips.Tooltips;
import com.wdiscute.starcatcher.io.CaughtFishInfo;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class GetNameMixin
{
    @Inject(method = "getHoverName", at = @At("HEAD"), cancellable = true)
    public void getHoverNameMixin(CallbackInfoReturnable<Component> cir)
    {
        ItemStack stack = (ItemStack) (Object) this;

        if(ModDataComponents.has(stack, ModDataComponents.BUCKETED_FISH))
        {
            stack = ModDataComponents.get(stack, ModDataComponents.BUCKETED_FISH).stack();
        }

        if (ModDataComponents.has(stack, ModDataComponents.CAUGHT_FISH_INFO))
        {
            Component baseName;
            Component customName = stack.get(DataComponents.CUSTOM_NAME);
            Component itemName = stack.get(DataComponents.ITEM_NAME);

            if (customName != null)
            {
                baseName = customName;
            }
            else if (itemName != null)
            {
                baseName = itemName;
            }
            else baseName = stack.getItem().getName(stack);

            //get sw
            CaughtFishInfo sw = ModDataComponents.get(stack, ModDataComponents.CAUGHT_FISH_INFO);

            //if golden, use golden rarity color
            FishProperties.Rarity rarity = sw.golden() ? FishProperties.Rarity.GOLDEN : sw.rarity();

            //build name string
            String name = rarity.getPre() + baseName.getString(100) + rarity.getPost();

            //decode name string and return value
            cir.setReturnValue(Tooltips.decodeString(name));
            cir.cancel();
        }
    }
}
