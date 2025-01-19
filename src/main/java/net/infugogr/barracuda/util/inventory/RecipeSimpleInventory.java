package net.infugogr.barracuda.util.inventory;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeInputProvider;

public class RecipeSimpleInventory extends SimpleInventory implements RecipeInputProvider {
    public RecipeSimpleInventory(int size) {
        super(size);
    }

    public RecipeSimpleInventory(ItemStack... stacks) {
        super(stacks);
    }

    @Override
    public ItemStack getStack(int slot) {return super.getStack(slot);}
}