package net.infugogr.barracuda.world;

import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class Portal {
    private static final AbstractBlock.ContextPredicate IS_VALID_FRAME_BLOCK = (state, world, pos) -> {
        return state.isOf(Blocks.OBSIDIAN);
    };
    private static final float FALLBACK_THRESHOLD = 4.0F;
    private static final double HEIGHT_STRETCH = 1.0;
    private final WorldAccess world;
    private final Direction.Axis axis;
    private final Direction negativeDir;
    private int foundPortalBlocks;
    @Nullable
    private BlockPos lowerCorner;

    public static Optional<Portal> getNewPortal(WorldAccess world, BlockPos pos, Direction.Axis axis) {
        return getOrEmpty(world, pos, (areaHelper) -> areaHelper.isValid() && areaHelper.foundPortalBlocks == 0, axis);
    }

    public static Optional<Portal> getOrEmpty(WorldAccess world, BlockPos pos, Predicate<Portal> validator, Direction.Axis axis) {
        Optional<Portal> optional = Optional.of(new Portal(world, pos, axis)).filter(validator);
        if (optional.isPresent()) {
            return optional;
        } else {
            Direction.Axis axis2 = axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
            return Optional.of(new Portal(world, pos, axis2)).filter(validator);
        }
    }

    public Portal(WorldAccess world, BlockPos pos, Direction.Axis axis) {
        this.world = world;
        this.axis = axis;
        this.negativeDir = axis == Direction.Axis.X ? Direction.WEST : Direction.SOUTH;
    }

    private static boolean validStateInsidePortal(BlockState state) {
        return state.isAir() || state.isIn(BlockTags.FIRE) || state.isOf(Blocks.NETHER_PORTAL);
    }

    public boolean isValid() {
        return this.lowerCorner != null;
    }

    public void createPortal() {
        BlockState blockState = Blocks.NETHER_PORTAL.getDefaultState().with(NetherPortalBlock.AXIS, this.axis);
        assert this.lowerCorner != null;
        BlockPos.iterate(this.lowerCorner, this.lowerCorner.offset(Direction.UP).offset(this.negativeDir)).forEach((pos) -> {
            this.world.setBlockState(pos, blockState, 18);
        });
    }

    public boolean wasAlreadyValid() {
        return this.isValid();
    }

    public static Vec3d entityPosInPortal(BlockLocating.Rectangle portalRect, Direction.Axis portalAxis, Vec3d entityPos, EntityDimensions entityDimensions) {
        double d = (double)portalRect.width - (double)entityDimensions.width;
        double e = (double)portalRect.height - (double)entityDimensions.height;
        BlockPos blockPos = portalRect.lowerLeft;
        double g;
        double f;
        if (d > 0.0) {
            f = (double)blockPos.getComponentAlongAxis(portalAxis) + (double)entityDimensions.width / 2.0;
            g = MathHelper.clamp(MathHelper.getLerpProgress(entityPos.getComponentAlongAxis(portalAxis) - f, 0.0, d), 0.0, 1.0);
        } else {
            g = 0.5;
        }

        Direction.Axis axis;
        if (e > 0.0) {
            axis = Direction.Axis.Y;
            f = MathHelper.clamp(MathHelper.getLerpProgress(entityPos.getComponentAlongAxis(axis) - (double)blockPos.getComponentAlongAxis(axis), 0.0, e), 0.0, 1.0);
        } else {
            f = 0.0;
        }

        axis = portalAxis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
        double h = entityPos.getComponentAlongAxis(axis) - ((double)blockPos.getComponentAlongAxis(axis) + 0.5);
        return new Vec3d(g, f, h);
    }

    public static TeleportTarget getTeleportTarget(ServerWorld destination, BlockLocating.Rectangle portalRect, Direction.Axis portalAxis, Vec3d offset, Entity entity, Vec3d velocity, float yaw, float pitch) {
        BlockPos blockPos = portalRect.lowerLeft;
        BlockState blockState = destination.getBlockState(blockPos);
        Direction.Axis axis = blockState.getOrEmpty(Properties.HORIZONTAL_AXIS).orElse(Direction.Axis.X);
        double d = portalRect.width;
        double e = portalRect.height;
        EntityDimensions entityDimensions = entity.getDimensions(entity.getPose());
        int i = portalAxis == axis ? 0 : 90;
        Vec3d vec3d = portalAxis == axis ? velocity : new Vec3d(velocity.z, velocity.y, -velocity.x);
        double f = (double)entityDimensions.width / 2.0 + (d - (double)entityDimensions.width) * offset.getX();
        double g = (e - (double)entityDimensions.height) * offset.getY();
        double h = 0.5 + offset.getZ();
        boolean bl = axis == Direction.Axis.X;
        Vec3d vec3d2 = new Vec3d((double)blockPos.getX() + (bl ? f : h), (double)blockPos.getY() + g, (double)blockPos.getZ() + (bl ? h : f));
        Vec3d vec3d3 = findOpenPosition(vec3d2, destination, entity, entityDimensions);
        return new TeleportTarget(vec3d3, vec3d, yaw + (float)i, pitch);
    }

    private static Vec3d findOpenPosition(Vec3d fallback, ServerWorld world, Entity entity, EntityDimensions dimensions) {
        if (!(dimensions.width > 4.0F) && !(dimensions.height > 4.0F)) {
            double d = (double)dimensions.height / 2.0;
            Vec3d vec3d = fallback.add(0.0, d, 0.0);
            VoxelShape voxelShape = VoxelShapes.cuboid(Box.of(vec3d, dimensions.width, 0.0, dimensions.width).stretch(0.0, 1.0, 0.0).expand(1.0E-6));
            Optional<Vec3d> optional = world.findClosestCollision(entity, voxelShape, vec3d, dimensions.width, dimensions.height, dimensions.width);
            Optional<Vec3d> optional2 = optional.map((pos) -> pos.subtract(0.0, d, 0.0));
            return optional2.orElse(fallback);
        } else {
            return fallback;
        }
    }
}
