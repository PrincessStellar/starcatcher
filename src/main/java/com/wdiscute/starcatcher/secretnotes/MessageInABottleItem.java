package com.wdiscute.starcatcher.secretnotes;

import com.wdiscute.starcatcher.registry.SCDataComponents;
import com.wdiscute.starcatcher.registry.SCItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

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
        if(SCDataComponents.has(itemInHand, SCDataComponents.MESSAGE))
        {
            //give note
            ItemStack is = new ItemStack(SCItems.MESSAGE.get());
            LetterItem.Message message = SCDataComponents.get(itemInHand, SCDataComponents.MESSAGE);
            SCDataComponents.set(is, SCDataComponents.MESSAGE, message.lock());
            player.addItem(is);

            player.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);

            //replace with broken bottle
            player.setItemInHand(usedHand, new ItemStack(SCItems.BROKEN_BOTTLE.get()));
        }
        else
        {
            LetterItem.Message message = new LetterItem.Message(UUID.randomUUID(), "-wd", Level.OVERWORLD.location(),
                    List.of(
                            "",
                            "",
                            "This is an example message.",
                            "",
                            "Players can write their own letters,",
                            "bottle them up and then throw them into the ocean.",
                            "",
                            "Other players in that dimension can then fish them up."
                    ),
                    true
            );

            //give note
            ItemStack is = new ItemStack(SCItems.MESSAGE.get());
            SCDataComponents.set(is, SCDataComponents.MESSAGE, message);
            player.addItem(is);

            player.playSound(SoundEvents.PLAYER_ATTACK_SWEEP);

            //replace with broken bottle
            player.setItemInHand(usedHand, new ItemStack(SCItems.BROKEN_BOTTLE.get()));
        }

        return InteractionResultHolder.success(player.getItemInHand(usedHand));
    }
}
