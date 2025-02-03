package net.infugogr.barracuda.screenhandler;

import net.infugogr.barracuda.block.entity.FishingNetBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class FishingNetScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final FishingNetBlockEntity blockEntity;

    public FishingNetScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(2));
    }

    public FishingNetScreenHandler(int syncId, PlayerInventory playerInventory,
                                     BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(ModScreenHandlerType.FISHING_NET, syncId);
        checkSize(((Inventory) blockEntity), 2);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = ((FishingNetBlockEntity) blockEntity);

        this.addSlot(new Slot(inventory, 0, 80, 11));
        this.addSlot(new Slot(inventory, 1, 80, 59));


        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(arrayPropertyDelegate);
    }

    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowSize = 26; // This is the width in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
// package net.infugogr.barracuda.screenhandler;

// import net.minecraft.entity.player.PlayerEntity;
// import net.minecraft.entity.player.PlayerInventory;
// import net.minecraft.inventory.Inventory;
// import net.minecraft.inventory.SimpleInventory;
// import net.minecraft.item.ItemStack;
// import net.minecraft.screen.ScreenHandler;
// import net.minecraft.screen.slot.Slot;

// public class FishingNetScreenHandler extends ScreenHandler {
//     private final Inventory inventory;

//     // Конструктор для клиента
//     public FishingNetScreenHandler(int syncId, PlayerInventory playerInventory) {
//         this(syncId, playerInventory, new SimpleInventory(9)); // Контейнер на 9 слотов
//     }

//     // Конструктор для сервера
//     public FishingNetScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
//         super(ModScreenHandlerType.FISHING_NET, syncId);
//         checkSize(inventory, 9); // Проверяем, что контейнер имеет 9 слотов
//         this.inventory = inventory;
//         inventory.onOpen(playerInventory.player);
        

//         // Добавляем слоты для контейнера (3x3)
//         for (int i = 0; i < 3; ++i) {
//             for (int j = 0; j < 3; ++j) {
//                 this.addSlot(new Slot(inventory, j + i * 3, 62 + j * 18, 17 + i * 18));
//             }
//         }

//         // Добавляем слоты для инвентаря игрока (3x9)
//         for (int i = 0; i < 3; ++i) {
//             for (int j = 0; j < 9; ++j) {
//                 this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
//             }
//         }

//         // Добавляем слоты для горячей панели игрока (1x9)
//         for (int i = 0; i < 9; ++i) {
//             this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
//         }
//     }

//     @Override
//     public ItemStack quickMove(PlayerEntity player, int index) {
//         ItemStack itemStack = ItemStack.EMPTY;
//         Slot slot = this.slots.get(index);
//         if (slot != null && slot.hasStack()) {
//             ItemStack itemStack1 = slot.getStack();
//             itemStack = itemStack1.copy();
//             if (index < 9) {
//                 if (!this.insertItem(itemStack1, 9, 45, true)) {
//                     return ItemStack.EMPTY;
//                 }
//             } else if (!this.insertItem(itemStack1, 0, 9, false)) {
//                 return ItemStack.EMPTY;
//             }

//             if (itemStack1.isEmpty()) {
//                 slot.setStack(ItemStack.EMPTY);
//             } else {
//                 slot.markDirty();
//             }

//             if (itemStack1.getCount() == itemStack.getCount()) {
//                 return ItemStack.EMPTY;
//             }

//             slot.onTakeItem(player, itemStack1);
//         }

//         return itemStack;
//     }

//     @Override
//     public boolean canUse(PlayerEntity player) {
//         return this.inventory.canPlayerUse(player);
//     }
//     //     @Override
// //     public void onClosed(PlayerEntity player) {
// //         super.onClosed(player);
// //         this.inventory.getWrappedInventoryStorage().onClose(player);
// //     }
//    }