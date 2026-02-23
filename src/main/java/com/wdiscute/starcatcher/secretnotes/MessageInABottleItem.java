package com.wdiscute.starcatcher.secretnotes;

import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.registry.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MessageInABottleItem extends Item
{
    public MessageInABottleItem()
    {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand)
    {
        ItemStack itemInHand = player.getItemInHand(usedHand);
        if(ModDataComponents.has(itemInHand, ModDataComponents.MESSAGE))
        {
            //give note
            ItemStack is = new ItemStack(ModItems.LETTER.get());
            LetterItem.Message message = ModDataComponents.get(itemInHand, ModDataComponents.MESSAGE);
            ModDataComponents.set(is, ModDataComponents.MESSAGE, message.lock());
            player.addItem(is);

            //replace with broken bottle
            player.setItemInHand(usedHand, new ItemStack(ModItems.BROKEN_BOTTLE.get()));
        }

        return InteractionResultHolder.success(player.getItemInHand(usedHand));
    }
}
