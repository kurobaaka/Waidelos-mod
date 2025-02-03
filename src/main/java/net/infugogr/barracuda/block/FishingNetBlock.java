// package net.infugogr.barracuda.block;

// import com.mojang.serialization.MapCodec;

// import net.infugogr.barracuda.block.entity.FishingNetBlockEntity;
// import net.infugogr.barracuda.block.entity.FuelGeneratorBlockEntity;
// import net.infugogr.barracuda.block.entity.ModBlockEntityType;
// import net.infugogr.barracuda.screenhandler.FuelGeneratorScreenHandler;
// import net.infugogr.barracuda.util.TickableBlockEntity;
// import net.minecraft.block.*;
// import net.minecraft.block.entity.BlockEntity;
// import net.minecraft.block.entity.BlockEntityTicker;
// import net.minecraft.block.entity.BlockEntityType;
// import net.minecraft.entity.player.PlayerEntity;
// import net.minecraft.entity.player.PlayerInventory;
// import net.minecraft.fluid.FluidState;
// import net.minecraft.fluid.Fluids;
// import net.minecraft.inventory.Inventory;
// import net.minecraft.item.ItemPlacementContext;
// import net.minecraft.screen.NamedScreenHandlerFactory;
// import net.minecraft.screen.ScreenHandler;
// import net.minecraft.state.StateManager;
// import net.minecraft.state.property.BooleanProperty;
// import net.minecraft.state.property.DirectionProperty;
// import net.minecraft.state.property.Properties;
// import net.minecraft.util.ActionResult;
// import net.minecraft.util.Hand;
// import net.minecraft.util.ItemScatterer;
// import net.minecraft.util.function.BooleanBiFunction;
// import net.minecraft.util.hit.BlockHitResult;
// import net.minecraft.util.math.BlockPos;
// import net.minecraft.util.math.Direction;
// import net.minecraft.util.shape.VoxelShape;
// import net.minecraft.util.shape.VoxelShapes;
// import net.minecraft.world.BlockView;
// import net.minecraft.world.World;
// import net.minecraft.world.WorldAccess;
// import org.jetbrains.annotations.Nullable;

// import java.util.EnumMap;

// public class FishingNetBlock extends BlockWithEntity implements BlockEntityProvider, Waterloggable {
//     private static final MapCodec<FishingNetBlock> CODEC = createCodec(FishingNetBlock::new);
//     private static final EnumMap<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);
//     public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
//     public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

//     protected FishingNetBlock(Settings settings) {
//         super(settings);
//         setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
//         runShapeCalculation(createShape());
//     }

//     @Override
//     protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
//         super.appendProperties(builder);
//         builder.add(FACING, WATERLOGGED);
//     }

//     @Override
//     public FluidState getFluidState(BlockState state) {
//         return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
//     }

//     @Override
//     public BlockState getPlacementState(ItemPlacementContext ctx) {
//         FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
//         return getDefaultState()
//             .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
//             .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
//     }

//     @Override
//     public BlockState getStateForNeighborUpdate(
//         BlockState state, 
//         Direction direction, 
//         BlockState neighborState, 
//         WorldAccess world, 
//         BlockPos pos, 
//         BlockPos neighborPos
//     ) {
//         if (state.get(WATERLOGGED)) {
//             world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
//         }
//         return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
//     }
    
//     @Override
// public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
//     if (!world.isClient) {
//         BlockEntity blockEntity = world.getBlockEntity(pos);
//         if (blockEntity instanceof FishingNetBlockEntity) {
//             player.openHandledScreen((NamedScreenHandlerFactory) blockEntity);
//         }
//     }
//     return ActionResult.SUCCESS;
// }

//     @Override
//     public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
//         return true; // Включаем поддержку прозрачности
//     }
    
//     @Override
//     public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
//         return 0; // Указываем, что блок полностью прозрачный
//     }

//     private static void runShapeCalculation(VoxelShape shape) {
//         for (final Direction direction : Direction.values()) {
//             SHAPES.put(direction, calculateShapes(direction, shape));
//         }
//     }

//     private static VoxelShape calculateShapes(Direction to, VoxelShape shape) {
//         final VoxelShape[] buffer = {shape, VoxelShapes.empty()};

//         final int times = (to.getHorizontal() - Direction.NORTH.getHorizontal() + 4) % 4;
//         for (int i = 0; i < times; i++) {
//             buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) ->
//                     buffer[1] = VoxelShapes.union(buffer[1],
//                             VoxelShapes.cuboid(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
//             buffer[0] = buffer[1];
//             buffer[1] = VoxelShapes.empty();
//         }

//         return buffer[0];
//     }

//     private static VoxelShape createShape() {
//         var shape = VoxelShapes.empty();
//         shape = VoxelShapes.combineAndSimplify(shape, VoxelShapes.cuboid(0.1875, 0, 0, 0.8125, 0.8125, 1), BooleanBiFunction.OR);
//         return shape.simplify();
//     }

//     @Override
//     protected MapCodec<? extends BlockWithEntity> getCodec() {
//         return CODEC;
//     }

//     @Override
//     public BlockRenderType getRenderType(BlockState state) {
//         return BlockRenderType.MODEL;
//     }

//     @Override
//     public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
//         return ModBlockEntityType.FUEL_GENERATOR.instantiate(pos, state);
//     }

//     @Override
//     public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
//         if (state.getBlock() != newState.getBlock()) {
//             BlockEntity blockEntity = world.getBlockEntity(pos);
//             if (blockEntity instanceof FuelGeneratorBlockEntity) {
//                 ItemScatterer.spawn(world, pos, (Inventory) blockEntity);
//                 world.updateComparators(pos, this);
//             }
//             super.onStateReplaced(state, world, pos, newState, moved);
//         }
//     }

//     @Override
//     public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
//         return SHAPES.get(state.get(FACING));
//     }

//     @Nullable
//     @Override
//     public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
//         return TickableBlockEntity.createTicker(world);
//     }
// }