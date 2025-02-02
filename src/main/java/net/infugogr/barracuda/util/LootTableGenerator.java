package net.infugogr.barracuda.util;

import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import java.util.List;
import java.util.Random;

public class LootTableGenerator {
    public static LootTable generateLootTable(List<Item> items) {
        LootPool.Builder lootPool = LootPool.builder().rolls(ConstantLootNumberProvider.create(1));
        // For each item name provided, get the item object, create it as an entry to the loot table, and bosch
        items.forEach(item -> lootPool.with(ItemEntry.builder(item)));

        return LootTable.builder()
                .pool(lootPool.build())
                .type(LootContextTypes.BLOCK)
                .build();
    }

    public static Random random = new Random();
}
