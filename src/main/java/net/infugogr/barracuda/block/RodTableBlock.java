//package net.infugogr.barracuda.block;
//
//import net.infugogr.barracuda.Barracuda;
//import net.infugogr.barracuda.gui.RodTableGui;
//import eu.pb4.polymer.blocks.api.BlockModelType;
//import eu.pb4.polymer.blocks.api.PolymerBlockModel;
//import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
//import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.server.network.ServerPlayerEntity;
//import net.minecraft.text.Text;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.Hand;
//import net.minecraft.util.hit.BlockHitResult;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//
//public class RodTableBlock extends Block implements PolymerTexturedBlock {
//    public final BlockState texturedState;
//
//    public RodTableBlock(Settings settings) {
//        super(settings);
//        texturedState = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, PolymerBlockModel.of(Barracuda.id("block/rod_table")));
//    }
//
//    @Override
//    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
//        if (world.isClient)
//            return ActionResult.SUCCESS;
//        RodTableGui gui = new RodTableGui((ServerPlayerEntity) player);
//        gui.open();
//        return ActionResult.SUCCESS;
//    }
//
//    @Override
//    public Block getPolymerBlock(BlockState state) {
//        return texturedState.getBlock();
//    }
//
//    @Override
//    public BlockState getPolymerBlockState(BlockState state) {
//        return texturedState;
//    }
//}
