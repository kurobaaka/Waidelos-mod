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

    // Хитбоксы для тела, головы и хвоста
    private Box bodyHitbox;
    private Box headHitbox;
    private Box tailHitbox;

    public AzureReaperEntity(EntityType<? extends AzureReaperEntity> entityType, World world) {
        super(entityType, world);
        this.setNoGravity(true); // Отключаем гравитацию
        // Инициализация хитбоксов
        updateHitboxes();
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return FishEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 250.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 5.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimAroundGoal(this, 1.0D, 10)); // Параметры как у ванильных рыб
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.5D, false)); // Атака
        // this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0D, 10)); // Блуждание
        this.goalSelector.add(4, new LookAroundGoal(this)); // Осматривание

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true)); // Атака игрока
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, VillagerEntity.class, true)); // Атака деревенских жителей
    }

    
    @Override
    public void travel(Vec3d movementInput) {
        if (this.isTouchingWater()) {
            // Управляем движением в воде
            this.updateVelocity(0.01f, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9)); // Замедляем движение
        } else {
            super.travel(movementInput);
        }
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.isTouchingWater()) {
            this.setVelocity(this.getVelocity().add(0, -0.01, 0)); // Медленно опускаем моба в воде
        }
        if (!this.isTouchingWater()) {
            this.setVelocity(this.getVelocity().add(0.0D, -0.05D, 0.0D)); // Падение на земле
        }
        super.tickMovement();
        // Обновляем хитбоксы каждый тик
        updateHitboxes();
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

    // Метод для обновления хитбоксов
    private void updateHitboxes() {
        Vec3d pos = this.getPos(); // Получаем текущую позицию сущности

        // Обновляем хитбоксы на основе текущей позиции
        this.bodyHitbox = createBodyHitbox(pos);
        this.headHitbox = createHeadHitbox(pos);
        this.tailHitbox = createTailHitbox(pos);
    }

    // Методы для создания хитбоксов
    private Box createBodyHitbox(Vec3d pos) {
        float width = 9.0F;  // Ширина тела
        float height = 9.0F; // Высота тела
        float depth = 19.0F; // Глубина тела
        return new Box(
            pos.x - width / 2, pos.y - height / 2, pos.z - depth / 2,
            pos.x + width / 2, pos.y + height / 2, pos.z + depth / 2
        );
    }

    private Box createHeadHitbox(Vec3d pos) {
        float width = 7.0F;  // Ширина головы
        float height = 6.0F; // Высота головы
        float depth = 7.0F;  // Глубина головы
        return new Box(
            pos.x - width / 2, pos.y - height / 2, pos.z - depth / 2 - 8.0F, // Смещение головы вперед
            pos.x + width / 2, pos.y + height / 2, pos.z + depth / 2 - 8.0F
        );
    }

    private Box createTailHitbox(Vec3d pos) {
        float width = 8.0F;  // Ширина хвоста
        float height = 8.0F; // Высота хвоста
        float depth = 16.0F; // Глубина хвоста
        return new Box(
            pos.x - width / 2, pos.y - height / 2, pos.z - depth / 2 + 11.0F, // Смещение хвоста назад
            pos.x + width / 2, pos.y + height / 2, pos.z + depth / 2 + 11.0F
        );
    }

    // Методы для получения хитбоксов
    public Box getBodyHitbox() {
        return bodyHitbox;
    }

    public Box getHeadHitbox() {
        return headHitbox;
    }

    public Box getTailHitbox() {
        return tailHitbox;
    }
}