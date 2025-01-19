package net.waimod.items;

import net.waimod.WaidelosMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item Ruby = registerItem("Ruby", new Item(new FabricItemSettings()));

    private static void addItemsIngridientTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(Ruby);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(WaidelosMod.MOD_ID, name), item);
    }

    public static void registredModItems() {
        WaidelosMod.LOGGER.info("registering mod items for" + WaidelosMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsIngridientTabItemGroup);
    }
}
// https://youtu.be/5ms6RiR4SQ4?si=heeFsGLDwqXKmxDN 9:18