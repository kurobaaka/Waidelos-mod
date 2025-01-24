package net.infugogr.barracuda.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class RedstoneiumBlock extends Block {
    public RedstoneiumBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient()) {
            if (world.isThundering() && world.isRaining()) {
                if (random.nextInt(4)==0) {
                    Entity entity = EntityType.LIGHTNING_BOLT.create(world);
                    assert entity != null;
                    entity.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
                    world.spawnEntity(entity);
                    world.setBlockState(pos.add(0,0,0), (ModBlocks.CHARGED_REDSTONEIUM_BLOCK).getDefaultState());
                }
            }
        }
        super.randomTick(state, world, pos, random);
    }
}
