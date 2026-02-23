package com.wdiscute.starcatcher.registry.custom.catchmodifiers;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.io.MessagesSavedData;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.secretnotes.LetterItem;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class FishMessagesModifier extends AbstractCatchModifier
{
    @Override
    public void afterChoosingTheCatch(List<FishProperties> immutableAvailable)
    {

        List<LetterItem.Message> messages = MessagesSavedData.get(((ServerLevel) instance.level())).getMessages();

        //if there are any messages
        List<LetterItem.Message> list = messages.stream().filter(o -> o.dimension().equals(instance.level().dimension().location())).toList();

        if (!list.isEmpty())
        {
            ItemStack is = new ItemStack(ModItems.SECRET_NOTE.get());

            ModDataComponents.set(is, ModDataComponents.MESSAGE, list.get(U.r.nextInt(list.size())));


            //make ItemEntities for fish item stack
            ItemEntity itemFished = new ItemEntity(instance.level(), instance.position().x, instance.position().y + 1.2f, instance.position().z, is);

            //assign delta movement so fish flies towards player
            double x = Math.clamp((instance.player.position().x - instance.position().x) / 25, -1, 1);
            double y = Math.clamp((instance.player.position().y - instance.position().y) / 20, -1, 1);
            double z = Math.clamp((instance.player.position().z - instance.position().z) / 25, -1, 1);
            Vec3 vec3 = new Vec3(x, 0.7 + y, z);
            itemFished.setDeltaMovement(vec3);
        }

    }

    @Override
    public boolean shouldCancelBeforeSkipsMinigameCheck()
    {
        List<LetterItem.Message> messages = MessagesSavedData.get(((ServerLevel) instance.level())).getMessages();
        return messages.stream().anyMatch(o -> o.dimension().equals(instance.level().dimension().location()));
    }
}
