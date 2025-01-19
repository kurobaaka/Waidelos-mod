package net.infugogr.barracuda.screenhandler;

import net.infugogr.barracuda.block.ModBlocks;
import net.infugogr.barracuda.block.entity.SMESblockEntity;
import net.infugogr.barracuda.screenhandler.slot.PredicateSlot;
import net.infugogr.barracuda.util.inventory.WrappedInventoryStorage;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.math.MathHelper;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import java.util.Arrays;
import java.util.Objects;

public class SMESScreenHandler extends ScreenHandler {
    private final SMESblockEntity blockEntity;
    private final ScreenHandlerContext context;

    public SMESScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        this(syncId, playerInventory, (SMESblockEntity) Objects.requireNonNull(playerInventory.player.getWorld().getBlockEntity(packetByteBuf.readBlockPos())));
    }

    public SMESScreenHandler(int syncId, PlayerInventory playerInventory, SMESblockEntity blockEntity) {
        super(ModScreenHandlerType.SMES, syncId);
        this.blockEntity = blockEntity;
        this.context = ScreenHandlerContext.create(blockEntity.getWorld(), blockEntity.getPos());

        WrappedInventoryStorage<SimpleInventory> inventory = blockEntity.getWrappedInventory();
        inventory.checkSize(1);
        inventory.onOpen(playerInventory.player);
        addBlockEntityInventory();
    }

    private void addBlockEntityInventory() {
        SimpleInventory inventory = this.blockEntity.getWrappedInventory().getRecipeInventory();
        addSlot(new PredicateSlot(inventory, 0, 8, 28, (stack) -> this.blockEntity.isValid(stack, 0)));
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.blockEntity.getWrappedInventory().onClose(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(player, ModBlocks.SMES);
    }

    private boolean canUse(PlayerEntity player, Block... blocks) {
        return Arrays.stream(blocks).anyMatch(block -> canUse(this.context, player, block));
    }

    public float getEnergyPercent() {
        SimpleEnergyStorage storage = this.blockEntity.getEnergy();
        long energy = storage.getAmount();
        long capacity = storage.getCapacity();
        if(energy == 0 || capacity == 0)
            return 0.0f;

        return MathHelper.clamp((float) energy / (float) capacity, 0.0f, 1.0f);
    }

    public long getEnergy() {
        return this.blockEntity.getEnergy().getAmount();
    }

    public long getMaxEnergy() {
        return this.blockEntity.getEnergy().getCapacity();
    }

    public SMESblockEntity.ChargeMode getChargeMode() {
        return this.blockEntity.getChargeMode();
    }

    public SMESblockEntity getBlockEntity() {
        return this.blockEntity;
    }
}
