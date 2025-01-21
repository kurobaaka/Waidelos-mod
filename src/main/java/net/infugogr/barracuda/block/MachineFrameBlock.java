package net.infugogr.barracuda.block;

import com.mojang.serialization.MapCodec;
import net.infugogr.barracuda.block.entity.MachineFrameBlockEntity;
import net.infugogr.barracuda.block.entity.ModBlockEntityType;
import net.infugogr.barracuda.item.ModItems;
import net.infugogr.barracuda.util.ModTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MachineFrameBlock extends HorizontalFacingBlock implements BlockEntityProvider {
    private static final MapCodec<MachineFrameBlock> CODEC = createCodec(MachineFrameBlock::new);
    public static final BooleanProperty IS_PLATED = BooleanProperty.of("is_plated");
    public MachineFrameBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(IS_PLATED, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity ent = world.getBlockEntity(pos);
        ItemStack stack = player.getStackInHand(hand);
        if (ent instanceof MachineFrameBlockEntity blockEntity) {
            if (stack.isIn(ModTags.PARTS)) {
                return blockEntity.setStack(stack, state);
            } else if (stack.getItem() == Items.AIR) {
                return blockEntity.removeStack(state);
            } else if (stack.getItem() == ModItems.SCREWDRIVER) {
                blockEntity.building(state);
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.PASS;
            }
        } else {
            return ActionResult.PASS;
        }
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(IS_PLATED, false);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, IS_PLATED);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityType.MACHINE_FRAME.instantiate(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
