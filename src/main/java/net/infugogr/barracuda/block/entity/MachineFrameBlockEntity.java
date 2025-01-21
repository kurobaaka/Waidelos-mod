package net.infugogr.barracuda.block.entity;

import net.infugogr.barracuda.block.ModBlocks;
import net.infugogr.barracuda.item.ModItems;
import net.infugogr.barracuda.util.ModTags;
import net.infugogr.barracuda.util.UpdatableBlockEntity;
import net.minecraft.block.*;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import static net.infugogr.barracuda.block.MachineFrameBlock.IS_PLATED;
import static net.minecraft.block.Block.dropStack;
import static net.minecraft.block.HorizontalFacingBlock.FACING;

public class MachineFrameBlockEntity extends UpdatableBlockEntity{
    private final DefaultedList<ItemStack> inventory;

    public MachineFrameBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.MACHINE_FRAME, pos, state);
        this.inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory, true);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public ActionResult setStack(ItemStack stack, BlockState state) {
        if (stack.isIn(ModTags.PLATES) & inventory.get(0).getItem() == Items.AIR) {
            add(0, stack);
            Direction direction = state.get(FACING);
            assert world != null;
            world.setBlockState(pos, state.getBlock().getDefaultState().with(FACING, direction).with(IS_PLATED, true), 3);
            return  ActionResult.SUCCESS;
        } else if (stack.isIn(ModTags.CABELS)) {
            if (inventory.get(1).getCount()<10 & inventory.get(0).getCount()==1){
                add(1, stack);
            }
            return  ActionResult.SUCCESS;
        } else if (stack.getItem() == ModItems.CAPACITOR & inventory.get(2).getCount()<1 & inventory.get(0).getCount()==1) {
            add(2, stack);
            return  ActionResult.SUCCESS;
        } else {return  ActionResult.PASS;}
    }

    public ActionResult removeStack(BlockState state) {
        if (inventory.get(2).getItem() != Items.AIR) {
            remove(2);
            return  ActionResult.SUCCESS;
        } else if (inventory.get(1).getItem() != Items.AIR) {
            remove(1);
            return  ActionResult.SUCCESS;
        } else if (inventory.get(0).getItem() != Items.AIR) {
            remove(0);
            Direction direction = state.get(FACING);
            assert world != null;
            world.setBlockState(pos, state.getBlock().getDefaultState().with(FACING, direction).with(IS_PLATED, false), 3);
            return  ActionResult.SUCCESS;
        } else {return  ActionResult.PASS;}
    }

    void add (int i, ItemStack stack){
        if (inventory.get(i).getItem() == stack.getItem() | inventory.get(i).getItem() == Items.AIR){
            if (inventory.get(i)== ItemStack.EMPTY) {
                inventory.set(i, stack.getItem().getDefaultStack());
            } else {inventory.get(i).increment(1);}
            stack.decrement(1);
        }
    }

    void remove (int i){
        ItemStack itemStack = inventory.get(i);
        assert world != null;
        dropStack(world, pos.add(0,1,0), itemStack.getItem().getDefaultStack());
        inventory.get(i).decrement(1);
    }

    public void building(BlockState state){
        Item plate = inventory.get(0).getItem();
        if(plate == ModItems.FUEL_GENERATOR_PLATE){
            ItemStack cable = ModBlocks.LVCABLE.asItem().getDefaultStack().copyWithCount(10);
            ItemStack capacitor = ModItems.CAPACITOR.getDefaultStack().copyWithCount(1);
            if(ItemStack.areEqual(cable, inventory.get(1)) & ItemStack.areEqual(capacitor, inventory.get(2))){
                assert world != null;
                world.setBlockState(pos, ModBlocks.FUEL_GENERATOR.getDefaultState().with(FACING, state.get(FACING)), 3);
            }
        } else if(plate == ModItems.SMES_PLATE){
            ItemStack cable = ModBlocks.HVCABLE.asItem().getDefaultStack().copyWithCount(10);
            ItemStack capacitor = ModItems.CAPACITOR.getDefaultStack().copyWithCount(1);
            if(ItemStack.areEqual(cable, inventory.get(1)) & ItemStack.areEqual(capacitor, inventory.get(2))){
                assert world != null;
                world.setBlockState(pos, ModBlocks.SMES.getDefaultState().with(FACING, state.get(FACING)), 3);
            }
        }

    }
}

