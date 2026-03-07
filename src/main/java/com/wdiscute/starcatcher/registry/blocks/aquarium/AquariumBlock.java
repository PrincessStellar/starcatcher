package com.wdiscute.starcatcher.registry.blocks.aquarium;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.ModDataMaps;
import com.wdiscute.starcatcher.registry.blocks.ModBlockEntities;
import com.wdiscute.starcatcher.registry.blocks.ModBlocks;
import com.wdiscute.starcatcher.registry.blocks.TickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
import net.minecraft.world.phys.shapes.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AquariumBlock extends BaseEntityBlock implements SimpleWaterloggedBlock
{
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty BOTTOM = BooleanProperty.create("bottom");
    public static final BooleanProperty TOP = BooleanProperty.create("top");
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final EnumProperty<Decoration> DECORATION = EnumProperty.create("decoration", Decoration.class);
    public static final EnumProperty<Ground> GROUND = EnumProperty.create("ground", Ground.class);


    public AquariumBlock()
    {
        super(Properties.of()
                .pushReaction(PushReaction.IGNORE)
                .sound(SoundType.GLASS)
                .strength(2.0F)
                .noOcclusion());
    }

    @Override
    protected float getShadeBrightness(BlockState p_308911_, BlockGetter p_308952_, BlockPos p_308918_) {
        return 1.0F;
    }

    protected VoxelShape getVisualShape(BlockState p_309057_, BlockGetter p_308936_, BlockPos p_308956_, CollisionContext p_309006_) {
        return Shapes.empty();
    }

    @Override
    public ItemStack pickupBlock(@Nullable Player player, LevelAccessor level, BlockPos pos, BlockState state)
    {
        return ItemStack.EMPTY;
    }

    @Override
    protected boolean canBeReplaced(BlockState state, Fluid fluid)
    {
        return false;
    }

    @Override
    public boolean hasDynamicShape()
    {
        return true;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid)
    {
        return level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        if (context instanceof EntityCollisionContext e)
        {
            if (e.getEntity() != null)
            {
                VoxelShape shape = Shapes.empty();

                if (!state.getValue(BOTTOM)) shape = Shapes.join(shape, Block.box(-2, -2, -2, 18, 2, 18), BooleanOp.OR);
                //if (!state.getValue(TOP)) shape = Shapes.join(shape, Block.box(-2, 14, -2, 16, 16, 16), BooleanOp.OR);

                if (!state.getValue(NORTH)) shape = Shapes.join(shape, Block.box(-2, -2, -2, 18, 18, 0), BooleanOp.OR);
                if (!state.getValue(WEST)) shape = Shapes.join(shape, Block.box(-2, -2, -2, 0, 18, 18), BooleanOp.OR);

                if (!state.getValue(EAST)) shape = Shapes.join(shape, Block.box(16, -2, -2, 18, 18, 18), BooleanOp.OR);
                if (!state.getValue(SOUTH)) shape = Shapes.join(shape, Block.box(-2, -2, 16, 18, 18, 18), BooleanOp.OR);

                return shape;
            }
        }

        return Shapes.block();
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
    {
        VoxelShape shape = Shapes.empty();

        if (!state.getValue(BOTTOM)) shape = Shapes.join(shape, Block.box(-2, -2, -2, 18, 2, 18), BooleanOp.OR);
        //if (!state.getValue(TOP)) shape = Shapes.join(shape, Block.box(-2, 14, -2, 16, 16, 16), BooleanOp.OR);

        if (!state.getValue(NORTH)) shape = Shapes.join(shape, Block.box(-2, -2, -2, 18, 18, 4), BooleanOp.OR);
        if (!state.getValue(WEST)) shape = Shapes.join(shape, Block.box(-2, -2, -2, 4, 18, 18), BooleanOp.OR);

        if (!state.getValue(EAST)) shape = Shapes.join(shape, Block.box(12, -2, -2, 18, 18, 18), BooleanOp.OR);
        if (!state.getValue(SOUTH)) shape = Shapes.join(shape, Block.box(-2, -2, 12, 18, 18, 18), BooleanOp.OR);

        return shape;
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter level, BlockPos pos, FluidState fluidState)
    {
        return false;
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state)
    {
        return false;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos)
    {
        return true;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        if (stack.getItem() instanceof BucketItem bucket)
        {
            bucket.checkExtraContent(player, level, stack, pos);
            player.setItemInHand(hand, BucketItem.getEmptySuccessItem(stack, player));
            return ItemInteractionResult.SUCCESS;
        }

        Interaction interaction = ModDataMaps.getOrDefault(stack, ModDataMaps.AQUARIUM_INTERACTION, Interaction.NOTHING);
        if (interaction.executePlace(level, pos, state, stack))
        {
            if (interaction.sound != null) player.playSound(interaction.sound);
            return ItemInteractionResult.SUCCESS;
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos)
    {
        if (state.getValue(WATERLOGGED))
        {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected FluidState getFluidState(BlockState state)
    {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context)
    {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        BlockState bs = defaultBlockState();

        bs = bs.setValue(WATERLOGGED, true);
        bs = bs.setValue(DECORATION, Decoration.NOTHING);
        bs = bs.setValue(GROUND, Ground.NOTHING);
        if (!level.getBlockState(pos.below()).is(ModBlocks.AQUARIUM)) bs = bs.setValue(GROUND, Ground.SAND);

        bs = bs.setValue(BOTTOM, level.getBlockState(pos.below()).is(ModBlocks.AQUARIUM));
        bs = bs.setValue(TOP, level.getBlockState(pos.above()).is(ModBlocks.AQUARIUM));
        bs = bs.setValue(EAST, level.getBlockState(pos.east()).is(ModBlocks.AQUARIUM));
        bs = bs.setValue(WEST, level.getBlockState(pos.west()).is(ModBlocks.AQUARIUM));
        bs = bs.setValue(NORTH, level.getBlockState(pos.north()).is(ModBlocks.AQUARIUM));
        bs = bs.setValue(SOUTH, level.getBlockState(pos.south()).is(ModBlocks.AQUARIUM));

        return bs;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED, DECORATION, GROUND, NORTH, SOUTH, EAST, WEST, BOTTOM, TOP);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston)
    {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);

        Decoration decoration = state.getValue(DECORATION);
        Ground ground = state.getValue(GROUND);

        BlockState stateBellow = level.getBlockState(pos.below());
        Decoration decorationBelow = stateBellow.is(ModBlocks.AQUARIUM) ? stateBellow.getValue(DECORATION) : Decoration.NOTHING;
        Ground groundBelow = stateBellow.is(ModBlocks.AQUARIUM) ? stateBellow.getValue(GROUND) : Ground.NOTHING;

        BlockState stateAbove = level.getBlockState(pos.above());
        Decoration decorationAbove = stateAbove.is(ModBlocks.AQUARIUM) ? stateAbove.getValue(DECORATION) : Decoration.NOTHING;

        //update connections
        state = state.setValue(BOTTOM, level.getBlockState(pos.below()).is(ModBlocks.AQUARIUM));
        state = state.setValue(TOP, level.getBlockState(pos.above()).is(ModBlocks.AQUARIUM));
        state = state.setValue(EAST, level.getBlockState(pos.east()).is(ModBlocks.AQUARIUM));
        state = state.setValue(WEST, level.getBlockState(pos.west()).is(ModBlocks.AQUARIUM));
        state = state.setValue(NORTH, level.getBlockState(pos.north()).is(ModBlocks.AQUARIUM));
        state = state.setValue(SOUTH, level.getBlockState(pos.south()).is(ModBlocks.AQUARIUM));

        level.setBlockAndUpdate(pos, state);

        //update ground
        if (!ground.isEmpty())
            if (stateBellow.is(ModBlocks.AQUARIUM))
                level.setBlockAndUpdate(pos, state.setValue(GROUND, Ground.NOTHING));

        if (ground.isEmpty())
            if (!stateBellow.is(ModBlocks.AQUARIUM))
                level.setBlockAndUpdate(pos, state.setValue(GROUND, Ground.SAND));

        //update kelp top
        if (decoration == Decoration.KELP_TOP)
            if (decorationBelow != Decoration.KELP && ground.isEmpty())
                level.setBlockAndUpdate(pos, state.setValue(DECORATION, Decoration.NOTHING));

        //update kelp
        if (decoration == Decoration.KELP)
            if (decorationAbove != Decoration.KELP && decorationAbove != Decoration.KELP_TOP)
                level.setBlockAndUpdate(pos, state.setValue(DECORATION, Decoration.NOTHING));

        //update kelp
        if (decoration == Decoration.KELP)
            if (decorationBelow != Decoration.KELP && ground.isEmpty())
                level.setBlockAndUpdate(pos, state.setValue(DECORATION, Decoration.NOTHING));

        //update seagrass/castle/cave
        if (decoration.requiresGround() && ground.isEmpty())
            level.setBlockAndUpdate(pos, state.setValue(DECORATION, Decoration.NOTHING));

    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player player, BlockGetter level, BlockPos pos, BlockState state, Fluid fluid)
    {
        return false;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec()
    {
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        return ModBlockEntities.AQUARIUM.get().create(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType)
    {
        return TickableBlockEntity.getTicketHelper(level);
    }

    public enum Ground implements StringRepresentable
    {
        NOTHING("nothing"),
        SAND("sand"),
        RED_SAND("red_sand"),
        GRAVEL("gravel"),
        STONE("stone");

        final String name;

        Ground(String name)
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
        NOTHING("nothing", false, true),
        CLAM("clam", true, true),
        CONCH("conch", true, true),
        SEAGRASS("seagrass", true, true),
        KELP("kelp", false, true),
        KELP_TOP("kelp_top", false, true),
        CAVE("cave", true, false),
        CASTLE_SAND("castle_sand", true, false),
        CASTLE_RED_SAND("castle_red_sand", true, false);

        final String name;
        final boolean requiresGround;
        final boolean canFishSwimInside;

        Decoration(String name, boolean requiresGround, boolean canFishSwimInside)
        {
            this.name = name;
            this.requiresGround = requiresGround;
            this.canFishSwimInside = canFishSwimInside;
        }

        public boolean requiresGround()
        {
            return requiresGround;
        }

        @Override
        public @NotNull String getSerializedName()
        {
            return name;
        }

        public boolean isEmpty(Object... o)
        {
            return this == NOTHING;
        }
    }

    public enum Interaction implements StringRepresentable
    {
        NOTHING("nothing", null, U::alwaysFalse),

        PLACE_FISH("place_sand", SoundEvents.DOLPHIN_SPLASH, (l, bp, bs, is) ->
        {
            if (l.getBlockEntity(bp) instanceof AquariumBlockEntity abe)
            {
                abe.setFish(is);
                return true;
            }

            return false;
        }),

        PLACE_SAND("place_sand", SoundEvents.SAND_PLACE, (l, bp, bs, is) -> placeGround(l, bp, bs, Ground.SAND)),
        PLACE_RED_SAND("place_red_sand", SoundEvents.SAND_PLACE, (l, bp, bs, is) -> placeGround(l, bp, bs, Ground.RED_SAND)),
        PLACE_STONE("place_stone", SoundEvents.STONE_PLACE, (l, bp, bs, is) -> placeGround(l, bp, bs, Ground.STONE)),
        PLACE_GRAVEL("place_gravel", SoundEvents.GRAVEL_PLACE, (l, bp, bs, is) -> placeGround(l, bp, bs, Ground.GRAVEL)),

        PLACE_KELP("place_kelp", SoundEvents.WET_GRASS_PLACE, (l, bp, bs, is) ->
        {
            if (bs.getValue(DECORATION) == Decoration.KELP) return false;
            if (bs.getValue(DECORATION) == Decoration.KELP_TOP) return false;
            if (!bs.getValue(GROUND).isEmpty())
                return l.setBlockAndUpdate(bp, bs.setValue(DECORATION, Decoration.KELP_TOP));

            BlockState stateUnder = l.getBlockState(bp.below());
            if (stateUnder.is(ModBlocks.AQUARIUM))
                if (stateUnder.getValue(DECORATION) == Decoration.KELP_TOP)
                {
                    l.setBlockAndUpdate(bp.below(), stateUnder.setValue(DECORATION, Decoration.KELP));
                    return l.setBlockAndUpdate(bp, bs.setValue(DECORATION, Decoration.KELP_TOP));
                }

            return false;
        }),

        PLACE_SEAGRASS("place_seagrass", SoundEvents.WET_GRASS_PLACE, (l, bp, bs, is) ->
        {
            if (!bs.getValue(GROUND).isEmpty())
                return l.setBlockAndUpdate(bp, bs.setValue(DECORATION, Decoration.SEAGRASS));
            else return false;
        }),

        PLACE_CLAM("place_clam", SoundEvents.BONE_BLOCK_PLACE, (l, bp, bs, is) ->
        {
            if (!bs.getValue(GROUND).isEmpty())
                return l.setBlockAndUpdate(bp, bs.setValue(DECORATION, Decoration.CLAM));
            else return false;
        }),

        PLACE_CONCH("place_conch", SoundEvents.BONE_BLOCK_PLACE, (l, bp, bs, is) ->
        {
            if (!bs.getValue(GROUND).isEmpty())
                return l.setBlockAndUpdate(bp, bs.setValue(DECORATION, Decoration.CONCH));
            else return false;
        }),

        BUILD_CASTLE("build_castle", SoundEvents.SAND_HIT, (l, bp, bs, is) ->
        {
            if (!bs.getValue(GROUND).isEmpty())
            {
                Ground ground = bs.getValue(GROUND);
                if (ground == Ground.SAND && bs.getValue(DECORATION) != Decoration.CASTLE_SAND)
                    return l.setBlockAndUpdate(bp, bs.setValue(DECORATION, Decoration.CASTLE_SAND));

                if (ground == Ground.RED_SAND && bs.getValue(DECORATION) != Decoration.CASTLE_RED_SAND)
                    return l.setBlockAndUpdate(bp, bs.setValue(DECORATION, Decoration.CASTLE_RED_SAND));
            }
            return false;
        }),

        BUILD_CAVE("build_cave", SoundEvents.STONE_HIT, (l, bp, bs, is) ->
        {
            if (bs.getValue(GROUND) == Ground.STONE && bs.getValue(DECORATION) != Decoration.CAVE)
                return l.setBlockAndUpdate(bp, bs.setValue(DECORATION, Decoration.CAVE));

            return false;
        });

        final String name;
        final SoundEvent sound;
        final Consumer<Level, BlockPos, BlockState, ItemStack, Boolean> place;

        public static final Codec<Interaction> CODEC = StringRepresentable.fromEnum(Interaction::values);

        Interaction(String name, SoundEvent sound, Consumer<Level, BlockPos, BlockState, ItemStack, Boolean> place)
        {
            this.name = name;
            this.sound = sound;
            this.place = place;
        }

        static boolean placeGround(Level l, BlockPos pos, BlockState bs, Ground ground)
        {
            if (bs.getValue(GROUND) == ground) return false;
            return l.setBlockAndUpdate(pos, bs.setValue(GROUND, ground));
        }

        @Override
        public @NotNull String getSerializedName()
        {
            return name;
        }

        public boolean executePlace(Level level, BlockPos pos, BlockState state, ItemStack is)
        {
            return place.place(level, pos, state, is);
        }
    }

    public interface Consumer<P1, P2, P3, P4, R>
    {
        R place(P1 p1, P2 p2, P3 p3, P4 p4);
    }
}
