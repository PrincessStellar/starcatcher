package com.wdiscute.starcatcher.mixin;

import com.wdiscute.libtooltips.Tooltips;
import com.wdiscute.starcatcher.io.CaughtFishInfo;
import com.wdiscute.starcatcher.io.ModDataComponents;
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

        if (ModDataComponents.has(stack, ModDataComponents.CAUGHT_FISH_INFO))
        {
            Component baseName;
            Component customName = stack.get(DataComponents.CUSTOM_NAME);
            Component itemName = stack.get(DataComponents.ITEM_NAME);

            if(customName != null)
            {
                baseName = customName;
            }else if (itemName != null)
            {
                baseName = itemName;
            }else baseName = stack.getItem().getName(stack);

            CaughtFishInfo sw = ModDataComponents.get(stack, ModDataComponents.CAUGHT_FISH_INFO);

            String s = sw.rarity().getPre() + baseName.getString(100) + sw.rarity().getPost();

            cir.setReturnValue(Tooltips.decodeString(s));
            cir.cancel();
        }
    }
}
