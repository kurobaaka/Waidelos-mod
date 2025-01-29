package net.infugogr.barracuda.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;

public class AzureReaperEntity extends FishEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private int attackCooldown = 0; // New field to track attack cooldown

    public AzureReaperEntity(EntityType<? extends FishEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 250.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 5.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimAroundGoal(this, 1.2D, 60)); // Active swimming
        this.goalSelector.add(2, new MeleeAttackGoal(this, 5.0D, false)); // Attacking prey
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0D, 10)); // Wandering in search of prey
        this.goalSelector.add(4, new LookAroundGoal(this)); // Looking around

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true)); // Attack player
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, FishEntity.class, true)); // Attack other fish
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, SquidEntity.class, true)); // Attack squids
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, VillagerEntity.class, true)); // Attack villagers in water
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (!this.isTouchingWater()) {
            this.setVelocity(this.getVelocity().add(0.0D, -0.05D, 0.0D)); // Falling on land
        }
    }

    @Override
    public EntityGroup getGroup() {
        return EntityGroup.AQUATIC; // Belongs to aquatic group
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.isTouchingWater()) {
            this.updateVelocity(0.01F, movementInput); // Update speed in water
            this.move(MovementType.SELF, this.getVelocity()); // Apply movement
            this.setVelocity(this.getVelocity().multiply(0.9D)); // Slow down movement
        } else {
            super.travel(movementInput);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.isTouchingWater()) {
            this.setVelocity(this.getVelocity().add(0.0D, -0.05D, 0.0D)); // Falling on land
        }

        if (attackCooldown > 0) {
            attackCooldown--;
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        double velocity = Math.sqrt(this.getVelocity().horizontalLengthSquared());

        if (this.isAttacking()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.azure_reaper.attack", Animation.LoopType.PLAY_ONCE));
            return PlayState.CONTINUE;
        } else if (this.isRoaring()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.azure_reaper.roar", Animation.LoopType.PLAY_ONCE));
            return PlayState.CONTINUE;
        } else if (velocity > 0.01 && this.isTouchingWater()) {
            if (velocity > 0.1) { // Adjust threshold for fast swim
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.azure_reaper.fast_swim", Animation.LoopType.LOOP));
            } else {
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.azure_reaper.swim", Animation.LoopType.LOOP));
            }
            return PlayState.CONTINUE;
        }

        tAnimationState.getController().forceAnimationReset();
        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public ItemStack getBucketItem() {
        throw new UnsupportedOperationException("Unimplemented method 'getBucketItem'");
    }
    
    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_COD_FLOP;
    }

    private boolean isRoaring() {
        // Implement logic to determine if the entity is roaring
        // This could be a custom behavior or triggered by certain events
        return false; // Placeholder, replace with actual logic
    }

}