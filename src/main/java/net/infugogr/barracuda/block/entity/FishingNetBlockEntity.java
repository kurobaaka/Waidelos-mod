package net.infugogr.barracuda.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.infugogr.barracuda.item.ModItems;
import net.infugogr.barracuda.screenhandler.FishingNetScreenHandler;
import net.infugogr.barracuda.util.SyncableStorage;
import net.infugogr.barracuda.util.SyncableTickableBlockEntity;
import net.infugogr.barracuda.util.UpdatableBlockEntity;
import net.infugogr.barracuda.util.energy.SyncingEnergyStorage;
import net.infugogr.barracuda.util.inventory.SyncingSimpleInventory;
import net.infugogr.barracuda.util.inventory.WrappedInventoryStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FishingNetBlockEntity extends UpdatableBlockEntity implements SyncableTickableBlockEntity, ExtendedScreenHandlerFactory{
    private final WrappedInventoryStorage<SimpleInventory> inventoryStorage = new WrappedInventoryStorage<>();

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public FishingNetBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.FISHING_NET, pos, state);
        this.inventoryStorage.addInventory(new SyncingSimpleInventory(this, 1));
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> FishingNetBlockEntity.this.progress;
                    case 1 -> FishingNetBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> FishingNetBlockEntity.this.progress = value;
                    case 1 -> FishingNetBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public List<SyncableStorage> getSyncableStorages() {
        var inventory = (SyncingSimpleInventory) this.inventoryStorage.getInventory(0);
        assert inventory != null;
        return List.of(inventory);
    }

    @Override
    public void onTick() {

    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Fishing net");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.put("Inventory", this.inventoryStorage.writeNbt());
        nbt.putInt("BurnTime", this.progress);
        nbt.putInt("FuelTime", this.maxProgress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        this.inventoryStorage.readNbt(nbt.getList("Inventory", NbtElement.COMPOUND_TYPE));
        this.progress = nbt.getInt("BurnTime");
        this.maxProgress = nbt.getInt("FuelTime");
    }


    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FishingNetScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public WrappedInventoryStorage<SimpleInventory> getWrappedInventoryStorage() {
        return this.inventoryStorage;
    }

    public boolean isValid(ItemStack itemStack, int slot) {
        return slot == 0;
    }
}