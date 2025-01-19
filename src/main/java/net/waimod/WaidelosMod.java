package net.waimod;

import net.fabricmc.api.ModInitializer;
import net.waimod.items.ModItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaidelosMod implements ModInitializer {
	public static final String MOD_ID = "waidelos-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		
		ModItems.registredModItems();
	}
}