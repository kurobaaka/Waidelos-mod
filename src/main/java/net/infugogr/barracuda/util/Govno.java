package net.infugogr.barracuda.util;

import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.world.Portal;
import net.minecraft.entity.Entity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.dimension.DimensionType;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class Govno {
    protected  boolean inPortal;

    public static void moveToWorld(ServerWorld destination, Entity entity) {
        if (entity.getWorld() instanceof ServerWorld && !entity.isRemoved()) {
            entity.getWorld().getProfiler().push("changeDimension");
            entity.detach();
            entity.getWorld().getProfiler().push("reposition");
            TeleportTarget teleportTarget = getTeleportTarget(destination, entity);
            if (teleportTarget != null) {
                entity.getWorld().getProfiler().swap("reloading");
                entity = entity.getType().create(destination);
                if (entity != null) {
                    entity.copyFrom(entity);
                    entity.refreshPositionAndAngles(teleportTarget.position.x, teleportTarget.position.y, teleportTarget.position.z, teleportTarget.yaw, entity.getPitch());
                    entity.setVelocity(teleportTarget.velocity);
                    destination.onDimensionChanged(entity);
                }

                assert entity != null;
                removeFromDimension(entity);
                entity.getWorld().getProfiler().pop();
                ((ServerWorld)entity.getWorld()).resetIdleTimeout();
                destination.resetIdleTimeout();
                entity.getWorld().getProfiler().pop();
            }
        }
    }

    protected static void removeFromDimension(Entity entity) {
        entity.setRemoved(Entity.RemovalReason.CHANGED_DIMENSION);
    }

    @Nullable
    protected static TeleportTarget getTeleportTarget(ServerWorld destination, Entity entity) {
        boolean bl3 = destination.getRegistryKey() == World.NETHER;
        if (entity.getWorld().getRegistryKey() != World.NETHER && !bl3) {
            return null;
        } else {
            WorldBorder worldBorder = destination.getWorldBorder();
            double d = DimensionType.getCoordinateScaleFactor(entity.getWorld().getDimension(), destination.getDimension());BlockPos blockPos2 = worldBorder.clamp(entity.getX() * d, entity.getY(), entity.getZ() * d);
            return getPortalRect(destination, blockPos2, bl3, worldBorder).map((rect) -> {
                Direction.Axis axis;
                Vec3d vec3d;
                axis = Direction.Axis.X;
                vec3d = new Vec3d(0.5, 0.0, 0.0);
                return Portal.getTeleportTarget(destination, rect, axis, vec3d, entity, entity.getVelocity(), entity.getYaw(), entity.getPitch());
            }).orElse(null);
        }
    }

    public void setInPortal() {
        inPortal = true;
    }

    protected static Optional<BlockLocating.Rectangle> getPortalRect(ServerWorld destWorld, BlockPos destPos, boolean destIsNether, WorldBorder worldBorder) {
        return destWorld.getPortalForcer().getPortalRect(destPos, destIsNether, worldBorder);
    }

    public void tickPortal(Entity entity) {
        if (entity.getWorld() instanceof ServerWorld serverWorld) {
            if (inPortal) {
                MinecraftServer minecraftServer = serverWorld.getServer();
                RegistryKey<World> registryKey = entity.getWorld().getRegistryKey() == World.NETHER ? World.OVERWORLD : World.NETHER;
                ServerWorld serverWorld2 = minecraftServer.getWorld(registryKey);
                if (serverWorld2 != null && minecraftServer.isNetherAllowed() && !entity.hasVehicle()) {
                    entity.getWorld().getProfiler().push("portal");
                    entity.resetPortalCooldown();
                    moveToWorld(serverWorld2, entity);
                    entity.getWorld().getProfiler().pop();
                    inPortal = false;
                }
            }
        }
    }
}

