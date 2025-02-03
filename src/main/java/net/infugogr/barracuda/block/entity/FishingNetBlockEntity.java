// package net.infugogr.barracuda.block.entity;

// import java.util.List;

// import org.jetbrains.annotations.Nullable;
// import org.spongepowered.include.com.google.common.base.Objects;

// import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
// import net.infugogr.barracuda.util.SyncableStorage;
// import net.infugogr.barracuda.util.energy.SyncingEnergyStorage;
// import net.infugogr.barracuda.util.inventory.SyncingSimpleInventory;
// import net.infugogr.barracuda.util.inventory.WrappedInventoryStorage;
// import net.minecraft.block.BlockState;
// import net.minecraft.block.entity.BlockEntity;
// import net.minecraft.block.entity.BlockEntityType;
// import net.minecraft.inventory.SimpleInventory;
// import net.minecraft.nbt.NbtCompound;
// import net.minecraft.nbt.NbtElement;
// import net.minecraft.network.listener.ClientPlayPacketListener;
// import net.minecraft.network.packet.Packet;
// import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
// import net.minecraft.util.collection.DefaultedList;
// import net.minecraft.util.math.BlockPos;
// import net.minecraft.util.math.Direction;
// import net.minecraft.item.ItemStack;
// import net.minecraft.screen.ArrayPropertyDelegate;
// import net.minecraft.screen.PropertyDelegate;
// import net.minecraft.server.network.ServerPlayerEntity;
// import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
// import net.infugogr.barracuda.Barracuda;
// import net.infugogr.barracuda.screenhandler.FuelGeneratorScreenHandler;
// import net.infugogr.barracuda.util.SyncableTickableBlockEntity;
// import net.infugogr.barracuda.util.UpdatableBlockEntity;
// import net.infugogr.barracuda.util.energy.EnergySpreader;
// import net.infugogr.barracuda.util.energy.WrappedEnergyStorage;
// import net.minecraft.entity.player.PlayerEntity;
// import net.minecraft.entity.player.PlayerInventory;
// import net.minecraft.network.PacketByteBuf;
// import net.minecraft.screen.ScreenHandler;
// import net.minecraft.text.Text;
// import team.reborn.energy.api.EnergyStorage;
// import team.reborn.energy.api.base.SimpleEnergyStorage;

// public class FishingNetBlockEntity extends BlockEntity {
//     private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY); // Пример: 9 слотов
//     protected final PropertyDelegate propertyDelegate;
//     private final WrappedInventoryStorage<SimpleInventory> inventoryStorage = new WrappedInventoryStorage<>();

//     public DefaultedList<ItemStack> getInventory() {
//         return inventory;
//     }
//         public FishingNetBlockEntity(BlockPos pos, BlockState state) {
//         super(ModBlockEntityType.FISHING_NET, pos, state);
//         this.inventoryStorage.addInventory(new SyncingSimpleInventory(this, 9));
//         this.propertyDelegate = new PropertyDelegate() {
//             @Override
//             public int get(int index) {
//                 return switch (index) {
//                     default -> 0;
//                 };
//             }
//             @Override
//             public void set(int index, int value) {
//             }
//             @Override
//             public int size() {
//                 return 4;
//             }
//         };
//     }

//     public List<SyncableStorage> getSyncableStorages() {
//         var inventory = (SyncingSimpleInventory) this.inventoryStorage.getInventory(0);
//         assert inventory != null;
//         return List.of(inventory);
//     }


//     @Override
//     public void readNbt(NbtCompound nbt) {
//         super.readNbt(nbt);
//         if (nbt.contains("Items", 9)) {
//             inventory = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);
//             for (int i = 0; i < inventory.size(); i++) {
//                 inventory.set(i, ItemStack.fromNbt(nbt.getCompound(String.valueOf(i))));
//             }
//         }
//     }

//     @Override
//     public void writeNbt(NbtCompound nbt) {
//         super.writeNbt(nbt);
//         for (int i = 0; i < inventory.size(); i++) {
//             if (!inventory.get(i).isEmpty()) {
//                 nbt.put(String.valueOf(i), inventory.get(i).writeNbt(new NbtCompound()));
//             }
//         }
//     }
// }

// @Override
// public List<SyncableStorage> getSyncableStorages() {
//     var energy = (SyncingEnergyStorage) this.energyStorage.getStorage(null);
//     var inventory = (SyncingSimpleInventory) this.inventoryStorage.getInventory(0);
//     assert inventory != null;
//     return List.of(energy, inventory);
// }

// // @Override
// // public void onTick() {
// //     if (this.world == null || this.world.isClient)
// //         return;

// //     SimpleEnergyStorage energyStorage = this.energyStorage.getStorage(null);

// //     spread(this.world, this.pos, energyStorage);

// //     if (this.progress >0){
// //         this.progress--;
// //     }

// //     if (energyStorage.getAmount() >= energyStorage.getCapacity()) {
// //         if (energyStorage.getAmount() > energyStorage.getCapacity()){
// //             energyStorage.amount -= (energyStorage.getAmount() - energyStorage.getCapacity());
// //         }
// //         return;
// //     }
// //     if (this.progress > 0) {
// //         energyStorage.amount += output;
// //     } else {
// //         SimpleInventory inventory = this.inventoryStorage.getInventory(0);
// //         assert inventory != null;
// //         ItemStack stack = inventory.getStack(0);
// //         if (isFuel(stack)) {
// //             this.maxProgress = getFuelTime(stack);
// //             this.progress = getFuelTime(stack);
// //             this.output = getFuelTime(stack)/220;
// //             stack.decrement(1);
// //             update();
// //         }
// //     }
// // }

// @Override
// public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
//     packetByteBuf.writeBlockPos(this.pos);
// }

// @Override
// public void writeNbt(NbtCompound nbt) {
//     nbt.put("EnergyStorage", this.energyStorage.writeNbt());
//     nbt.put("Inventory", this.inventoryStorage.writeNbt());
//     nbt.putInt("BurnTime", this.progress);
//     nbt.putInt("FuelTime", this.maxProgress);
// }

// @Override
// public void readNbt(NbtCompound nbt) {
//     this.energyStorage.readNbt(nbt.getList("EnergyStorage", NbtElement.COMPOUND_TYPE));
//     this.inventoryStorage.readNbt(nbt.getList("Inventory", NbtElement.COMPOUND_TYPE));
//     this.progress = nbt.getInt("BurnTime");
//     this.maxProgress = nbt.getInt("FuelTime");
// }

// @Override
// public NbtCompound toInitialChunkDataNbt() {
//     return createNbt();
// }

// @Override
// public Text getDisplayName() {
//     return TITLE;
// }

// @Override
// public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
//     return new FishingNetScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
// }

// public boolean isFuel(ItemStack stack) {
//     return createFuelTimeMap().containsKey(stack.getItem());
// }

// public int getFuelTime(ItemStack stack) {
//     return createFuelTimeMap().getOrDefault(stack.getItem(), 0);
// }

// public WrappedInventoryStorage<SimpleInventory> getWrappedInventoryStorage() {
//     return this.inventoryStorage;
// }

// public boolean isValid(ItemStack itemStack, int slot) {
//     return slot == 0 && isFuel(itemStack);
// }

// public InventoryStorage getInventoryProvider(Direction direction) {
//     return this.inventoryStorage.getStorage(direction);
// }

// public ItemStack getRenderStack() {
//     return Objects.requireNonNull(this.inventoryStorage.getInventory(0)).getStack(0);
// }

// @Nullable
// @Override
// public Packet<ClientPlayPacketListener> toUpdatePacket() {
//     return BlockEntityUpdateS2CPacket.create(this);
// }
// }