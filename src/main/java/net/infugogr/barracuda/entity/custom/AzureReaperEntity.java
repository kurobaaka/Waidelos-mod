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
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
    private int attackCooldown = 0;

    public AzureReaperEntity(EntityType<? extends FishEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 250.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimAroundGoal(this, 1.0D, 40)); // Параметры как у ванильных рыб
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.5D, false)); // Атака
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0D, 10)); // Блуждание
        this.goalSelector.add(4, new LookAroundGoal(this)); // Осматривание

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true)); // Атака игрока
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, VillagerEntity.class, true)); // Атака деревенских жителей
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.isTouchingWater()) {
            // Движение в воде, как у ванильных рыб
            this.updateVelocity(0.01F, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9D));
            if (this.getTarget() == null) {
                this.setVelocity(this.getVelocity().add(0.0D, -0.005D, 0.0D)); // Небольшая гравитация в воде
            }
        } else {
            // Движение на суше
            super.travel(movementInput);
        }
    }

    @Override
    public void tick() {
        super.tick();

        // Логика атаки
        if (attackCooldown > 0) {
            attackCooldown--;
        }

        if (this.getTarget() != null && attackCooldown <= 0) {
            this.tryAttack(this.getTarget());
            attackCooldown = 20; // 1 секунда кулдауна
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
        return new ItemStack(Items.WATER_BUCKET); // Замените на ваш предмет
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_COD_FLOP;
    }

    private boolean isRoaring() {
        return this.getTarget() != null && this.distanceTo(this.getTarget()) < 10.0;
    }
}