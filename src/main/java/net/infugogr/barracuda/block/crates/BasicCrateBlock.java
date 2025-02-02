package net.infugogr.barracuda.block.crates;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import net.infugogr.barracuda.util.LootTableGenerator;

import java.util.List;

public class BasicCrateBlock extends Block {
    public BasicCrateBlock(Settings settings){
        super(settings.hardness(1.9f));
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        float minPitch = .95f, maxPitch = 1.05f, volume = .5f;
        float pitch = LootTableGenerator.random.nextFloat((maxPitch - minPitch) + minPitch);
        if (player.getServer() == null) {
            return state;
        }
        if(world instanceof ServerWorld sWorld && !player.getAbilities().creativeMode){
            //Sounds
            playSound(pos, SoundEvents.BLOCK_WOOD_BREAK, volume, pitch, sWorld);
            playSound(pos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, volume, pitch, sWorld);
            playSound(pos, SoundEvents.ENTITY_FIREWORK_ROCKET_TWINKLE, volume, pitch, sWorld);
            playSound(pos, SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, volume, pitch, sWorld);
            playSound(pos, SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, volume + 1f, pitch - .5f, sWorld);

            //Particles
            spawnParticle(pos, 10, ParticleTypes.FIREWORK, sWorld);
            spawnParticle(pos, 5, ParticleTypes.POOF, sWorld);
            spawnParticle(pos, 5, ParticleTypes.EXPLOSION, sWorld);
        }else if(world instanceof ServerWorld sWorld && player.getAbilities().creativeMode){
            playSound(pos, SoundEvents.BLOCK_WOOD_BREAK, volume + 1f, pitch - .5f, sWorld);
            spawnBreakParticles(sWorld, player, pos, state);
        }
        return state;
    }
    private void playSound(BlockPos pos, SoundEvent soundEvent, float volume, float pitch, ServerWorld world){
        world.playSound(null, pos, soundEvent, SoundCategory.BLOCKS, volume, pitch);
    }

    private void spawnParticle(BlockPos pos, int count, ParticleEffect particleType, ServerWorld world){
        world.spawnParticles(particleType, pos.getX(), pos.getY(), pos.getZ(), count, 0.5f, 0.5f, 0.5f, 0);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("block.barracuda.basic_crate.tooltip_1").formatted(Formatting.YELLOW));
        tooltip.add(Text.translatable("block.barracuda.basic_crate.tooltip_2").formatted(Formatting.YELLOW));
    }
}