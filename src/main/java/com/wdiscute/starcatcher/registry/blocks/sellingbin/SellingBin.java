package com.wdiscute.starcatcher.registry.blocks.sellingbin;

import com.wdiscute.starcatcher.registry.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.nikdo53.tinymultiblocklib.block.AbstractMultiBlock;
import net.nikdo53.tinymultiblocklib.block.IMultiBlock;
import net.nikdo53.tinymultiblocklib.block.IPreviewableMultiblock;
import net.nikdo53.tinymultiblocklib.components.IBlockPosOffsetEnum;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Function;

public class SellingBin extends AbstractMultiBlock implements IPreviewableMultiblock
{

    public SellingBin()
    {
        super(BlockBehaviour.Properties.of().noOcclusion());
    }

    @Override
    public List<BlockPos> makeFullBlockShape(Level level, BlockPos center, BlockState blockState, @Nullable BlockEntity blockEntity, @Nullable Direction direction)
    {
        assert direction != null;
        return List.of(center, center.relative(direction.getClockWise()));
    }

    @Override
    public BlockState getDefaultStateForPreviews(Direction direction)
    {
        return IPreviewableMultiblock.super.getDefaultStateForPreviews(direction);
    }

    @Override
    public RenderShape getMultiblockRenderShape(BlockState state, boolean c)
    {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable DirectionProperty getDirectionProperty()
    {
        return FACING;
    }

    @Override
    public BlockState getStateForEachBlock(BlockState state, BlockPos pos, BlockPos centerOffset, Level level, @Nullable Direction direction)
    {
        state = state.setValue(SELLING_BIN_PART, IBlockPosOffsetEnum.fromOffset(SellingBinPart.class, centerOffset, direction, SellingBinPart.CENTER));

        return state;
    }


    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    //This probably doesn't need to be there since the center property does the same thing
    public static final EnumProperty<SellingBinPart> SELLING_BIN_PART = EnumProperty.create("part", SellingBinPart.class);

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(SELLING_BIN_PART);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult)
    {
        BlockPos center = IMultiBlock.getCenter(level, pos);
        if (level.getBlockEntity(center) instanceof SellingBinBlockEntity sbbe)
        {
            player.openMenu(new SimpleMenuProvider(sbbe, Component.empty()), center);
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        return ModBlockEntities.SELLING_BIN.get().create(blockPos, blockState);
    }

    @Override
    public boolean hasCustomBE() {
        return true;
    }

    public enum SellingBinPart implements StringRepresentable, IBlockPosOffsetEnum
    {
        CENTER("center", pos -> pos),
        EAST("east", BlockPos::east);

        private final String name;
        public final Function<BlockPos, BlockPos> offset;

        SellingBinPart(String name, Function<BlockPos, BlockPos> offset)
        {
            this.name = name;
            this.offset = offset;
        }

        @Override
        public String toString()
        {
            return this.name;
        }

        @Override
        public String getSerializedName()
        {
            return this.name;
        }

        @Override
        public Function<BlockPos, BlockPos> getOffsetFunction() {
            return offset;
        }
    }
}
