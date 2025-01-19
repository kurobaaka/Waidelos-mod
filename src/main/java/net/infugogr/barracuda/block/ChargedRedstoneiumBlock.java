package net.infugogr.barracuda.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ChargedRedstoneiumBlock extends Block {

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(world.isClient) {
            if ((long) world.random.nextInt(200) <= world.getTime() % 200L) {
                ParticleUtil.spawnParticle(world, pos, ParticleTypes.ELECTRIC_SPARK, UniformIntProvider.create(1, 2));
            }
        }
    }

    public ChargedRedstoneiumBlock(Settings settings) {
        super(settings);
    }


}
