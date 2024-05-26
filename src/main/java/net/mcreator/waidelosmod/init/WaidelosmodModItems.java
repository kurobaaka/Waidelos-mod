/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.waidelosmod.init;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;

import net.mcreator.waidelosmod.item.MasterSwordItem;
import net.mcreator.waidelosmod.WaidelosmodMod;

public class WaidelosmodModItems {
	public static Item MASTER_SWORD;

	public static void load() {
		MASTER_SWORD = register("master_sword", new MasterSwordItem());
	}

	public static void clientLoad() {
	}

	private static Item register(String registryName, Item item) {
		return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(WaidelosmodMod.MODID, registryName), item);
	}

	private static void registerBlockingProperty(Item item) {
		ItemProperties.register(item, new ResourceLocation("blocking"), (ClampedItemPropertyFunction) ItemProperties.getProperty(Items.SHIELD, new ResourceLocation("blocking")));
	}
}
