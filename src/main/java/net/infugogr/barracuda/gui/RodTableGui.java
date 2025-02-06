//package net.infugogr.barracuda.gui;
//
//import net.infugogr.barracuda.Barracuda;
//import net.infugogr.barracuda.ReelItems;
//import net.infugogr.barracuda.item.UIItem;
//import net.infugogr.barracuda.item.ModularFishingRodItem;
//import eu.pb4.sgui.api.gui.SimpleGui;
//import net.minecraft.inventory.Inventory;
//import net.minecraft.inventory.InventoryChangedListener;
//import net.minecraft.inventory.SimpleInventory;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NbtCompound;
//import net.minecraft.screen.ScreenHandlerType;
//import net.minecraft.screen.slot.Slot;
//import net.minecraft.server.network.ServerPlayerEntity;
//import net.minecraft.text.Style;
//import net.minecraft.text.Text;
//import net.minecraft.util.Formatting;
//import net.minecraft.util.Identifier;
//
//public class RodTableGui extends SimpleGui implements InventoryChangedListener {
//    public static final int INPUT_SLOT_INDEX = 10;
//    public static final int ROD_SLOT_INDEX = 12;
//    public static final int REEL_SLOT_INDEX = 13;
//    public static final int HOOK_SLOT_INDEX = 14;
//    public static final int BUTTON_SLOT_INDEX = 16;
//
//    public final SimpleInventory inventory;
//
//    public RodTableGui(ServerPlayerEntity player) {
//        super(ScreenHandlerType.GENERIC_9X3, player, false);
//        inventory = new SimpleInventory(4);
//        inventory.addListener(this);
//        setSlotRedirect(INPUT_SLOT_INDEX, new Slot(inventory, 0, 0, 0) {
//            @Override
//            public boolean canInsert(ItemStack stack) {
//                return stack.isOf(ReelItems.MODULAR_FISHING_ROD_ITEM);
//            }
//        });
//        setSlotRedirect(ROD_SLOT_INDEX, new Slot(inventory, 1, 0, 0) {
//            @Override
//            public boolean canInsert(ItemStack stack) {
//                return Barracuda.ROD_IDS_TO_ITEMS.containsValue(stack.getItem());
//            }
//
//            @Override
//            public int getMaxItemCount() {
//                return 1;
//            }
//        });
//        setSlotRedirect(REEL_SLOT_INDEX, new Slot(inventory, 2, 0, 0) {
//            @Override
//            public boolean canInsert(ItemStack stack) {
//                return Barracuda.REEL_IDS_TO_ITEMS.containsValue(stack.getItem());
//            }
//
//            @Override
//            public int getMaxItemCount() {
//                return 1;
//            }
//        });
//        setSlotRedirect(HOOK_SLOT_INDEX, new Slot(inventory, 3, 0, 0) {
//            @Override
//            public boolean canInsert(ItemStack stack) {
//                return Barracuda.HOOK_IDS_TO_ITEMS.containsValue(stack.getItem());
//            }
//
//            @Override
//            public int getMaxItemCount() {
//                return 1;
//            }
//        });
//        setTitle(Text.literal("ab").setStyle(Style.EMPTY.withColor(Formatting.WHITE).withFont(Barracuda.id("rod_table_gui"))));
//    }
//
//    public void assembleRod() {
//        if (!hasAllComponents() || hasRod())
//            return;
//        ItemStack rodStack = new ItemStack(ReelItems.MODULAR_FISHING_ROD_ITEM);
//        // Copy components to rod NBT
//        NbtCompound nbt = rodStack.getOrCreateNbt();
//        nbt.putString(ModularFishingRodItem.ROD_KEY, Barracuda.getRodFromItem(getSlotRedirect(ROD_SLOT_INDEX).getStack().getItem()).toString());
//        nbt.putString(ModularFishingRodItem.REEL_KEY, Barracuda.getReelFromItem(getSlotRedirect(REEL_SLOT_INDEX).getStack().getItem()).toString());
//        nbt.putString(ModularFishingRodItem.HOOK_KEY, Barracuda.getHookFromItem(getSlotRedirect(HOOK_SLOT_INDEX).getStack().getItem()).toString());
//        nbt.putString(ModularFishingRodItem.CREATOR_KEY, player.getName().getString());
//        getSlotRedirect(INPUT_SLOT_INDEX).setStack(rodStack);
//        // Clear all component slots
//        getSlotRedirect(ROD_SLOT_INDEX).setStack(ItemStack.EMPTY);
//        getSlotRedirect(REEL_SLOT_INDEX).setStack(ItemStack.EMPTY);
//        getSlotRedirect(HOOK_SLOT_INDEX).setStack(ItemStack.EMPTY);
//    }
//
//    public void disassembleRod() {
//        if (hasAnyComponents() || !hasRod())
//            return;
//        ItemStack rodStack = getSlotRedirect(INPUT_SLOT_INDEX).getStack();
//        NbtCompound nbt = rodStack.getOrCreateNbt();
//        // Copy rod NBT to component stacks
//        ItemStack rodComponentStack = new ItemStack(Barracuda.getItemFromRod(new Identifier(nbt.getString(ModularFishingRodItem.ROD_KEY))));
//        ItemStack reelComponentStack = new ItemStack(Barracuda.getItemFromReel(new Identifier(nbt.getString(ModularFishingRodItem.REEL_KEY))));
//        ItemStack hookComponentStack = new ItemStack(Barracuda.getItemFromHook(new Identifier(nbt.getString(ModularFishingRodItem.HOOK_KEY))));
//        getSlotRedirect(ROD_SLOT_INDEX).setStack(rodComponentStack);
//        getSlotRedirect(REEL_SLOT_INDEX).setStack(reelComponentStack);
//        getSlotRedirect(HOOK_SLOT_INDEX).setStack(hookComponentStack);
//        // Clear input stack
//        getSlotRedirect(INPUT_SLOT_INDEX).setStack(ItemStack.EMPTY);
//    }
//
//    @Override
//    public void onInventoryChanged(Inventory changed) {
//        boolean hasRod = hasRod();
//        if (hasRod && !hasAnyComponents()) {
//            setSlot(BUTTON_SLOT_INDEX, UIItem.getButton("disassemble")
//                    .setName(Text.translatable("gui.reel.rod_table.disassemble"))
//                    .setCallback(this::disassembleRod));
//        } else if (hasAllComponents() && !hasRod) {
//            setSlot(BUTTON_SLOT_INDEX, UIItem.getButton("assemble")
//                    .setName(Text.translatable("gui.reel.rod_table.assemble"))
//                    .setCallback(this::assembleRod));
//        } else {
//            setSlot(BUTTON_SLOT_INDEX, ItemStack.EMPTY);
//        }
//    }
//
//    @Override
//    public void onClose() {
//        for (ItemStack itemStack : inventory.getHeldStacks()) {
//            player.getInventory().offerOrDrop(itemStack);
//        }
//    }
//
//    public boolean hasRod() {
//        return !getSlotRedirect(INPUT_SLOT_INDEX).getStack().isEmpty();
//    }
//
//    public boolean hasAnyComponents() {
//        return !getSlotRedirect(ROD_SLOT_INDEX).getStack().isEmpty()
//                || !getSlotRedirect(REEL_SLOT_INDEX).getStack().isEmpty()
//                || !getSlotRedirect(HOOK_SLOT_INDEX).getStack().isEmpty();
//    }
//
//    public boolean hasAllComponents() {
//        return !getSlotRedirect(ROD_SLOT_INDEX).getStack().isEmpty()
//                && !getSlotRedirect(REEL_SLOT_INDEX).getStack().isEmpty()
//                && !getSlotRedirect(HOOK_SLOT_INDEX).getStack().isEmpty();
//    }
//}
