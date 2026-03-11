package com.wdiscute.starcatcher.registry.blocks.sellingbin;

import com.wdiscute.starcatcher.registry.blocks.ModBlockEntities;
import com.wdiscute.starcatcher.registry.blocks.TickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
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

public class SellingBinBlock extends AbstractMultiBlock implements IPreviewableMultiblock
{

    public SellingBinBlock()
    {
        super(BlockBehaviour.Properties.of()
                .noOcclusion()
                .destroyTime(2)
        );
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType)
    {
        return TickableBlockEntity.getTicketHelper(level);
    }

    @Override
    protected float getShadeBrightness(BlockState p_308911_, BlockGetter p_308952_, BlockPos p_308918_)
    {
        return 1.0F;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState p_309084_, BlockGetter p_309133_, BlockPos p_309097_)
    {
        return true;
    }

    @Override
    public List<BlockPos> makeFullBlockShape(Level level, BlockPos center, BlockState blockState, @Nullable BlockEntity blockEntity, @Nullable Direction direction)
    {
        assert direction != null;
        return List.of(center, center.relative(direction.getClockWise()));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return super.getStateForPlacement(context);
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

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult)
    {
        BlockPos center = IMultiBlock.getCenter(level, pos);
        if (level.getBlockEntity(center) instanceof SellingBinBlockEntity sbbe && !level.isClientSide)
        {
            player.openMenu(sbbe, center);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        return ModBlockEntities.SELLING_BIN.get().create(blockPos, blockState);
    }

    @Override
    public boolean hasCustomBE()
    {
        return true;
    }

}
