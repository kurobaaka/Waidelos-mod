package net.infugogr.barracuda.util.inventory;

import net.infugogr.barracuda.util.UpdatableBlockEntity;
import net.minecraft.item.ItemStack;

public class OutputSimpleInventory extends PredicateSimpleInventory {
    public OutputSimpleInventory(UpdatableBlockEntity blockEntity, int size) {
        super(blockEntity, size, (slot, stack) -> false);
    }

    public OutputSimpleInventory(UpdatableBlockEntity blockEntity, ItemStack... stacks) {
        super(blockEntity, (slot, stack) -> false, stacks);
    }
}
