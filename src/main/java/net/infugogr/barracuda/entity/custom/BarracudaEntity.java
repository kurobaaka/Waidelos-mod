package net.infugogr.barracuda.entity.custom;

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
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;


public class BarracudaEntity extends FishEntity implements GeoEntity {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public BarracudaEntity(EntityType<? extends FishEntity> entityType, World world) {
        super(entityType, world);
    }

 public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 5.0);
    }

@Override
protected void initGoals() {
    // Основные цели для плавания и охоты
    this.goalSelector.add(1, new SwimAroundGoal(this, 1.2D, 10)); // Активное плавание
    this.goalSelector.add(2, new MeleeAttackGoal(this, 1.5D, false)); // Атака добычи
    this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0D, 10)); // Блуждание в поисках добычи
    this.goalSelector.add(4, new LookAroundGoal(this)); // Осматриваться

    // Цели для выбора добычи
    this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true)); // Атаковать игрока
    this.targetSelector.add(2, new ActiveTargetGoal<>(this, FishEntity.class, true)); // Атаковать других рыб
    this.targetSelector.add(3, new ActiveTargetGoal<>(this, SquidEntity.class, true)); // Атаковать кальмаров
    this.targetSelector.add(4, new ActiveTargetGoal<>(this, VillagerEntity.class, true)); // Атаковать жителей в воде
}

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (!this.isTouchingWater()) {
            this.setVelocity(this.getVelocity().add(0.0D, -0.05D, 0.0D)); // Падение на земле
        }
    }

    @Override
    public EntityGroup getGroup() {
        return EntityGroup.AQUATIC; // Относится к водной группе
    }
    
    @Override
    public void travel(Vec3d movementInput) {
    if (this.isTouchingWater()) {
        this.updateVelocity(0.01F, movementInput); // Обновляем скорость в воде
        this.move(MovementType.SELF, this.getVelocity()); // Применяем движение
        this.setVelocity(this.getVelocity().multiply(0.9D)); // Замедляем движение
    } else {
        super.travel(movementInput);
    }
}

    
    @Override
    public void tick() {
        super.tick();
    
        if (!this.isTouchingWater()) {
            this.setVelocity(this.getVelocity().add(0.0D, -0.05D, 0.0D)); // Падение на земле
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        double velocity = Math.sqrt(this.getVelocity().horizontalLengthSquared());
    
        if (velocity > 0.01 && this.isTouchingWater()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.barracuda.swim", Animation.LoopType.LOOP));
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
}