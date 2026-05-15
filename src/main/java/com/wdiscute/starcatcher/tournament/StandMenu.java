package com.wdiscute.starcatcher.tournament;

import com.wdiscute.starcatcher.registry.SCMenuTypes;
import com.wdiscute.starcatcher.blocks.SCBlocks;
import com.wdiscute.starcatcher.blocks.stand.StandBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StandMenu extends AbstractContainerMenu
{
    public final StandBlockEntity sbe;
    public final Level level;

    public StandMenu(int containerId, Inventory inv, BlockEntity blockEntity)
    {
        super(SCMenuTypes.STAND_MENU.get(), containerId);
        sbe = ((StandBlockEntity) blockEntity);
        level = inv.player.level();
    }

    public StandMenu(int containerId, Inventory inv, FriendlyByteBuf extraData)
    {
        this(containerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    @Override
    public boolean clickMenuButton(Player player, int id)
    {
        if (level.isClientSide) return false;

        //six seven
        //¯\_(ツ)¯\_
        //
        //_/¯(ツ)_/¯

        Tournament currentTournament = sbe.makeOrGetTournament(player);
        if (currentTournament.status == Tournament.Status.PREPARING && currentTournament.owner.equals(player.getUUID()))
        {
            //duration -
            if (id == 101)
            {
                if (currentTournament.durationInTicks > 1200)
                {
                    currentTournament.durationInTicks -= 1200;
                }
            }

            //duration --
            if (id == 102)
            {
                if (currentTournament.durationInTicks > 12000)
                {
                    currentTournament.durationInTicks -= 12000;
                }
            }

            //duration +
            if (id == 103)
            {
                currentTournament.durationInTicks += 1200;
            }

            //duration ++
            if (id == 104)
            {
                currentTournament.durationInTicks += 12000;
            }

            //trash
            if (id == 200)
                currentTournament.scoreSettings.trashScore = Math.clamp(currentTournament.scoreSettings.trashScore + 0.1f, 0, 9.9f);

            if (id == 201)
                currentTournament.scoreSettings.trashScore = Math.clamp(currentTournament.scoreSettings.trashScore - 0.1f, 0, 9.9f);

            //common
            if (id == 210)
                currentTournament.scoreSettings.commonScore = Math.clamp(currentTournament.scoreSettings.commonScore + 0.1f, 0, 9.9f);

            if (id == 211)
                currentTournament.scoreSettings.commonScore = Math.clamp(currentTournament.scoreSettings.commonScore - 0.1f, 0, 9.9f);

            //uncommon
            if (id == 202)
                currentTournament.scoreSettings.uncommonScore = Math.clamp(currentTournament.scoreSettings.uncommonScore + 0.1f, 0, 9.9f);

            if (id == 221)
                currentTournament.scoreSettings.uncommonScore = Math.clamp(currentTournament.scoreSettings.uncommonScore - 0.1f, 0, 9.9f);

            //rare
            if (id == 230)
                currentTournament.scoreSettings.rareScore = Math.clamp(currentTournament.scoreSettings.rareScore + 0.1f, 0, 9.9f);

            if (id == 231)
                currentTournament.scoreSettings.rareScore = Math.clamp(currentTournament.scoreSettings.rareScore - 0.1f, 0, 9.9f);

            //epic
            if (id == 240)
                currentTournament.scoreSettings.epicScore = Math.clamp(currentTournament.scoreSettings.epicScore + 0.1f, 0, 9.9f);

            if (id == 241)
                currentTournament.scoreSettings.epicScore = Math.clamp(currentTournament.scoreSettings.epicScore - 0.1f, 0, 9.9f);

            //legendary
            if (id == 250)
                currentTournament.scoreSettings.legendaryScore = Math.clamp(currentTournament.scoreSettings.legendaryScore + 0.1f, 0, 9.9f);

            if (id == 251)
                currentTournament.scoreSettings.legendaryScore = Math.clamp(currentTournament.scoreSettings.legendaryScore - 0.1f, 0, 9.9f);

            //percentile
            if (id == 260)
                currentTournament.scoreSettings.percentileMultiplier = Math.clamp(currentTournament.scoreSettings.percentileMultiplier + 0.1f, 0, 9.9f);

            if (id == 261)
                currentTournament.scoreSettings.percentileMultiplier = Math.clamp(currentTournament.scoreSettings.percentileMultiplier - 0.1f, 0, 9.9f);

            //perfect catch
            if (id == 270)
                currentTournament.scoreSettings.perfectCatchMultiplier = Math.clamp(currentTournament.scoreSettings.perfectCatchMultiplier + 0.1f, 0, 9.9f);

            if (id == 271)
                currentTournament.scoreSettings.perfectCatchMultiplier = Math.clamp(currentTournament.scoreSettings.perfectCatchMultiplier - 0.1f, 0, 9.9f);

            sbe.sync();
            return true;
        }


        //gold button
        if (id == 67)
        {
            //owner
            if (currentTournament.owner.equals(player.getUUID()))
            {
                if (currentTournament.status.equals(Tournament.Status.PREPARING))
                    TournamentHandler.startTournament(player, currentTournament);

                if (currentTournament.status.equals(Tournament.Status.ACTIVE))
                    TournamentHandler.cancelTournament(player.level(), currentTournament);
            }
            //not owner
            else
            {
                //if player is not already signed up in another tournament
                if (TournamentHandler.getTournamentForPlayer(player) == null)
                {
                    List<Tournament.PlayerScore> list = currentTournament.playerScores.stream().filter(o -> o.uuid.equals(player.getUUID())).toList();

                    //if player is not registered, add it
                    if (list.isEmpty())
                    {
                        ArrayList<Tournament.PlayerScore> playerScores = new ArrayList<>(currentTournament.playerScores);
                        playerScores.add(new Tournament.PlayerScore(player.getUUID(), player.getName().getString(), 0));
                        currentTournament.playerScores = playerScores;
                    }
                    //else remove it
                    else
                    {
                        ArrayList<Tournament.PlayerScore> playerScores = new ArrayList<>(currentTournament.playerScores);
                        playerScores.remove(list.getFirst());
                        currentTournament.playerScores = playerScores;
                    }
                }
            }

            sbe.sync();
            return true;
        }

        //wipe a finished/canceled tournament
        if (id == 53 && currentTournament.status.isDone())
        {
            Tournament tournamentOld = sbe.tournament;
            sbe.setUuid(UUID.randomUUID());
            sbe.tournament = null;
            sbe.tournament = sbe.makeOrGetTournament(player);
        }

        sbe.sync();
        return super.clickMenuButton(player, id);
    }


    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 0;  // must be the number of slots you have!


    public ItemStack quickMoveStack(Player playerIn, int pIndex)
    {
        Slot sourceSlot = slots.get(pIndex);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT)
        {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(
                    sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                            + TE_INVENTORY_SLOT_COUNT, false))
            {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        }
        else if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT)
        {
            // This is a TE slot so merge the stack into the playerScores inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false))
            {
                return ItemStack.EMPTY;
            }
        }
        else
        {
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0)
        {
            sourceSlot.set(ItemStack.EMPTY);
        }
        else
        {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player player)
    {
        return stillValid(
                ContainerLevelAccess.create(level, sbe.getBlockPos()),
                player, SCBlocks.STAND.get());
    }
}
