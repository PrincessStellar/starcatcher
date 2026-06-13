package com.wdiscute.starcatcher.registry.items.rod;

import com.wdiscute.starcatcher.SCConfig;
import com.wdiscute.starcatcher.SCTags;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.bobentity.FishingBobEntity;
import com.wdiscute.starcatcher.registry.SCDataAttachments;
import com.wdiscute.starcatcher.registry.SCDataComponents;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.io.attachments.FishingBobAttachment;
import com.wdiscute.starcatcher.registry.SCItems;
import com.wdiscute.starcatcher.registry.tackleskin.AbstractTackleSkin;
import com.wdiscute.starcatcher.registry.tackleskin.SCTackleSkins;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class StarcatcherFishingRodItem extends Item implements MenuProvider
{
    public StarcatcherFishingRodItem()
    {
        super(new Properties()
                .rarity(Rarity.EPIC)
                .fireResistant()
                .stacksTo(1)
                .component(SCDataComponents.BOBBER.get(), new SingleStackContainer(new ItemStack(SCItems.BOBBER.get())))
                .component(SCDataComponents.BAIT.get(), SingleStackContainer.empty())
                .component(SCDataComponents.HOOK.get(), new SingleStackContainer(new ItemStack(SCItems.HOOK.get())))
        );
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack is = player.getItemInHand(hand);

        if (!is.is(SCTags.RODS))
            return InteractionResultHolder.pass(is);

        if (SCDataComponents.getOrDefault(is, SCDataComponents.HOOK, SingleStackContainer.empty()).stack().isEmpty()
            || SCDataComponents.getOrDefault(is, SCDataComponents.BOBBER, SingleStackContainer.empty()).stack().isEmpty())
        {
            player.displayClientMessage(Component.translatable("gui.starcatcher.no_hook_or_bobber"), true);
            return InteractionResultHolder.fail(is);
        }

        FishingBobAttachment fishingBobAttachment = SCDataAttachments.get(player, SCDataAttachments.FISHING_BOB.get());
        if (player.isCrouching() && fishingBobAttachment.isEmpty() && SCConfig.ENABLE_ROD_MENU.get())
        {
            player.openMenu(this);
            return InteractionResultHolder.success(is);
        }

        if (level.isClientSide) return InteractionResultHolder.success(is);

        AbstractTackleSkin tackleSkin = SCDataComponents.getOrDefault(is, SCDataComponents.TACKLE_SKIN, Starcatcher.TACKLE_SKIN_REGISTRY.get(Starcatcher.BASE));

        if (fishingBobAttachment.isEmpty())
        {
            if (level instanceof ServerLevel)
            {
                Entity entity = new FishingBobEntity(level, player, is, tackleSkin);

                level.addFreshEntity(entity);
                entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(player.getX(), entity.getEyeY(), player.getZ()));

                fishingBobAttachment.setUuid(player, entity.getUUID());

                SCDataAttachments.set(entity, SCDataAttachments.TACKLE_SKIN.get(), Starcatcher.TACKLE_SKIN_REGISTRY.getKey(tackleSkin));
            }
        }
        else
        {
            Entity maybeEntity = ((ServerLevel) level).getEntity(fishingBobAttachment.getUuid());

            if (maybeEntity instanceof FishingBobEntity fbe && !fbe.checkBiting())
            {
                tackleSkin.onRetrieve(player);

                fbe.kill();
                SCDataAttachments.remove(player, SCDataAttachments.FISHING_BOB.get());
            }

        }


        return InteractionResultHolder.success(is);
    }


    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack)
    {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack)
    {
        return itemStack.copy();
    }

    @Override
    public Component getDisplayName()
    {
        return Component.literal("");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player)
    {
        if (player.getMainHandItem().is(SCTags.RODS))
            return new FishingRodMenu(i, inventory, player.getMainHandItem());
        else
            return new FishingRodMenu(i, inventory, player.getOffhandItem());
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack)
    {
        return Optional.of(new RodSlotTooltip(stack));
    }

    public record RodSlotTooltip(ItemStack rod) implements TooltipComponent
    {
    }
}

