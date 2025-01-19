package net.infugogr.barracuda.screenhandler;

import net.infugogr.barracuda.block.entity.FuelGeneratorBlockEntity;
import net.infugogr.barracuda.block.ModBlocks;
import net.infugogr.barracuda.screenhandler.slot.PredicateSlot;
import net.infugogr.barracuda.util.inventory.WrappedInventoryStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;

import java.util.Objects;

public class FuelGeneratorScreenHandler extends ScreenHandler {
    private final FuelGeneratorBlockEntity blockEntity;
    private final ScreenHandlerContext context;

    public FuelGeneratorScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        this(syncId, playerInventory, (FuelGeneratorBlockEntity) Objects.requireNonNull(playerInventory.player.getWorld().getBlockEntity(packetByteBuf.readBlockPos())));
    }

    public FuelGeneratorScreenHandler(int syncId, PlayerInventory playerInventory, FuelGeneratorBlockEntity blockEntity) {
        super(ModScreenHandlerType.FUEL_GENERATOR, syncId);

        this.blockEntity = blockEntity;
        this.context = ScreenHandlerContext.create(blockEntity.getWorld(), blockEntity.getPos());

        WrappedInventoryStorage<SimpleInventory> inventory = blockEntity.getWrappedInventoryStorage();
        inventory.checkSize(1);
        inventory.onOpen(playerInventory.player);

        addBlockEntityInventory();
    }

    private void addBlockEntityInventory() {
        addSlot(new PredicateSlot( this.blockEntity.getWrappedInventoryStorage().getInventory(0), 0, 8, 28,
                itemStack -> this.blockEntity.isValid(itemStack, 0)));
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.blockEntity.getWrappedInventoryStorage().onClose(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slotObject = this.slots.get(slot);

        if (slotObject.hasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            if (slot < 0) {
                if (!insertItem(stackInSlot, 0, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!insertItem(stackInSlot, 0, 0, false)) {
                return ItemStack.EMPTY;
            }

            if (stackInSlot.isEmpty()) {
                slotObject.setStack(ItemStack.EMPTY);
            } else {
                slotObject.markDirty();
            }
        }

        return stack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, ModBlocks.FUEL_GENERATOR);
    }

    public FuelGeneratorBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    public long getEnergy() {
        return this.blockEntity.getEnergyStorage().getAmount();
    }

    public long getMaxEnergy() {
        return this.blockEntity.getEnergyStorage().getCapacity();
    }

    public int getBurnTime() {return this.blockEntity.getBurnTime();}

    public int getEnergyPerTick() {
        return this.blockEntity.getEnergyOutput();
    }

    public int getFuelTime() {
        return this.blockEntity.getFuelTime();
    }
}