package net.infugogr.barracuda.entity.custom;

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
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
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

    public AzureReaperEntity(EntityType<? extends AzureReaperEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 250.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 5.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimAroundGoal(this, 1.0D, 40));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.5D, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0D, 10));
        this.goalSelector.add(4, new LookAroundGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, VillagerEntity.class, true));
    }

    
    @Override
    public void travel(Vec3d movementInput) {
        if (this.isTouchingWater()) {
            // Управляем движением в воде
            this.updateVelocity(0.01f, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9));

            if (this.getTarget() == null) {
                this.setVelocity(this.getVelocity().add(0.0, -0.005, 0.0));
            }
        } else {
            super.travel(movementInput);
        }
    }

    @Override
    public void tickMovement() {
        if (this.isTouchingWater()) {
            this.setNoGravity(true);
        } else {
            this.setNoGravity(false);
        }

        if (!this.isTouchingWater() && this.isOnGround() && this.verticalCollision) {
            this.setVelocity(this.getVelocity().add(
                (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F,
                0.4F,
                (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F
            ));
            this.setOnGround(false);
            this.velocityDirty = true;
            this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getSoundPitch());
        }
        super.tickMovement();
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
        } else if (this.isTouchingWater()) {
            if (velocity > 0.1) {
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
        return new ItemStack(Items.WATER_BUCKET);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_COD_FLOP;
    }

    private boolean isRoaring() {
        return this.getTarget() != null && this.distanceTo(this.getTarget()) < 10.0;
    }
}