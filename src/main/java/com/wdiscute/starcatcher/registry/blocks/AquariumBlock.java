package com.wdiscute.starcatcher.registry.blocks;

import com.wdiscute.starcatcher.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AquariumBlock extends Block implements SimpleWaterloggedBlock
{
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<Decoration> DECORATION = EnumProperty.create("decoration", Decoration.class);
    public static final EnumProperty<Bottom> BOTTOM = EnumProperty.create("bottom", Bottom.class);

    public AquariumBlock()
    {
        super(Properties.of()
                .pushReaction(PushReaction.IGNORE)
                .sound(SoundType.GLASS)
                .noOcclusion()
                .noCollission());
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        Bottom bottom = state.getValue(BOTTOM);
        Decoration decoration = state.getValue(DECORATION);

        //seagrass
        if (stack.is(Items.SEAGRASS)
                && !bottom.isEmpty()
                && decoration != Decoration.SEAGRASS)
        {
            level.setBlockAndUpdate(pos, state.setValue(DECORATION, Decoration.SEAGRASS));
            stack.consume(1, player);
            player.playSound(SoundEvents.WET_GRASS_PLACE);
        }

        //kelp top
        if (stack.is(Items.KELP)
                && decoration != Decoration.KELP_TOP
                && !bottom.isEmpty())
        {
            level.setBlockAndUpdate(pos, state.setValue(DECORATION, Decoration.KELP_TOP));
            stack.consume(1, player);
            player.playSound(SoundEvents.WET_GRASS_PLACE);
        }

        //kelp + replace kelp bellow
        if (stack.is(Items.KELP)
                && decoration != Decoration.KELP_TOP
                && bottom.isEmpty()
                && (level.getBlockState(pos.below()).getValue(DECORATION) == Decoration.KELP ||
                level.getBlockState(pos.below()).getValue(DECORATION) == Decoration.KELP_TOP))
        {
            level.setBlockAndUpdate(pos, state.setValue(DECORATION, Decoration.KELP_TOP));
            level.setBlockAndUpdate(pos.below(), level.getBlockState(pos.below()).setValue(DECORATION, Decoration.KELP));
            stack.consume(1, player);
            player.playSound(SoundEvents.WET_GRASS_PLACE);
        }

        //make sand castle
        if (stack.is(ItemTags.SHOVELS) && bottom == Bottom.SAND && decoration != Decoration.CASTLE_SAND)
        {
            level.setBlockAndUpdate(pos, state.setValue(DECORATION, Decoration.CASTLE_SAND));
            stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            player.playSound(SoundEvents.BRUSH_SAND_COMPLETED);
        }

        //make red sand castle
        if (stack.is(ItemTags.SHOVELS) && bottom == Bottom.RED_SAND && decoration != Decoration.CASTLE_RED_SAND)
        {
            level.setBlockAndUpdate(pos, state.setValue(DECORATION, Decoration.CASTLE_RED_SAND));
            stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            player.playSound(SoundEvents.BRUSH_SAND_COMPLETED);
        }

        //place sand
        if (stack.is(Items.SAND) && bottom != Bottom.SAND)
        {
            level.setBlockAndUpdate(pos, state.setValue(BOTTOM, Bottom.SAND));
            stack.consume(1, player);
            player.playSound(SoundEvents.SAND_PLACE);
        }

        //place red sand
        if (stack.is(Items.RED_SAND) && bottom != Bottom.RED_SAND)
        {
            level.setBlockAndUpdate(pos, state.setValue(BOTTOM, Bottom.RED_SAND));
            stack.consume(1, player);
            player.playSound(SoundEvents.SAND_PLACE);
        }

        //place stone
        if (stack.is(Items.STONE) && bottom != Bottom.STONE)
        {
            level.setBlockAndUpdate(pos, state.setValue(BOTTOM, Bottom.STONE));
            stack.consume(1, player);
            player.playSound(SoundEvents.STONE_PLACE);
        }

        //place gravel
        if (stack.is(Items.GRAVEL) && bottom != Bottom.GRAVEL)
        {
            level.setBlockAndUpdate(pos, state.setValue(BOTTOM, Bottom.GRAVEL));
            stack.consume(1, player);
            player.playSound(SoundEvents.GRAVEL_PLACE);
        }

        if(stack.is(ModBlocks.AQUARIUM.asItem())) return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        return ItemInteractionResult.SUCCESS;
    }

    @Override
    protected FluidState getFluidState(BlockState state)
    {
        return Fluids.WATER.getSource(false);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context)
    {
        BlockState bs = defaultBlockState();
        bs = bs.setValue(WATERLOGGED, true);
        bs = bs.setValue(DECORATION, Decoration.NOTHING);
        if(context.getLevel().getBlockState(context.getClickedPos().below()).is(ModBlocks.AQUARIUM))
            bs = bs.setValue(BOTTOM, Bottom.NOTHING);
        else
            bs = bs.setValue(BOTTOM, Bottom.SAND);
        return bs;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED, DECORATION, BOTTOM);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston)
    {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player player, BlockGetter level, BlockPos pos, BlockState state, Fluid fluid)
    {
        return false;
    }

    public enum Bottom implements StringRepresentable
    {
        NOTHING("nothing"),
        SAND("sand"),
        RED_SAND("red_sand"),
        GRAVEL("gravel"),
        STONE("stone");

        final String name;

        Bottom(String name)
        {
            this.name = name;
        }

        @Override
        public @NotNull String getSerializedName()
        {
            return name;
        }

        public boolean isEmpty()
        {
            return this == NOTHING;
        }
    }

    public enum Decoration implements StringRepresentable
    {
        NOTHING("nothing"),
        SEAGRASS("seagrass"),
        KELP("kelp"),
        KELP_TOP("kelp_top"),
        CASTLE_SAND("castle_sand"),
        CASTLE_RED_SAND("castle_red_sand");

        final String name;

        Decoration(String name)
        {
            this.name = name;
        }

        @Override
        public @NotNull String getSerializedName()
        {
            return name;
        }

        public boolean isEmpty()
        {
            return this == NOTHING;
        }
    }
}
