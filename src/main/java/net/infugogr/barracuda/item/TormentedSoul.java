package net.infugogr.barracuda.item;

import net.infugogr.barracuda.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;

import java.util.Map;

public class TormentedSoul extends Item {
    private static final Map<Block, Block> CURSED_MAP =
            Map.of(
                    Blocks.GOLD_BLOCK, ModBlocks.CURSED_GOLD_BLOCK
            );


    public TormentedSoul(Settings setting) {
        super(setting);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if (CURSED_MAP.containsKey(clickedBlock)) {
            if (!world.isClient()) {
                world.setBlockState(context.getBlockPos(), CURSED_MAP.get(clickedBlock).getDefaultState());

                context.getStack().decrement(1);

                world.playSound(null, context.getBlockPos(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.BLOCKS);
            }
            if(world.isClient) {
                ParticleUtil.spawnParticle(world, context.getBlockPos(), ParticleTypes.LAVA, UniformIntProvider.create(1, 10));
            }
        }

        return super.useOnBlock(context);
    }
}