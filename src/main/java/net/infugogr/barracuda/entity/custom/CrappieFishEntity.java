import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.MoveIntoWaterGoal;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public class CrappieFishEntity extends FishEntity {
    public CrappieFishEntity(EntityType<? extends FishEntity> type, World world) {
        super(type, world);
    }
    
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new MoveIntoWaterGoal(this));
        this.goalSelector.add(4, new FishSwimGoal(this));
    }

    @Override
    public ItemStack getBucketItem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBucketItem'");
    }

    @Override
    protected SoundEvent getFlopSound() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFlopSound'");
    }
}                   