package net.infugogr.barracuda.item;

import net.infugogr.barracuda.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

import java.util.Objects;

import static net.infugogr.barracuda.item.ModItems.IRON_PLATE;
import static net.minecraft.block.HorizontalFacingBlock.FACING;

public class IronPlate extends Item {
    public IronPlate(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState blockState = context.getWorld().getBlockState(context.getBlockPos().up());
        if (context.getStack().getItem() == IRON_PLATE
                & Objects.requireNonNull(context.getPlayer()).isSneaking()
                & blockState  == Blocks.AIR.getDefaultState()
                & context.getStack().getCount() >= 8){
            context.getWorld().setBlockState(context.getBlockPos().up(), ModBlocks.MACHINE_FRAME.getDefaultState()
                    .with(FACING, context.getHorizontalPlayerFacing().getOpposite()));
            context.getStack().decrement(8);

        }
        return super.useOnBlock(context);
    }
}
