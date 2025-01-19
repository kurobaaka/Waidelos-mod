package net.infugogr.barracuda;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.infugogr.barracuda.block.*;
import net.infugogr.barracuda.block.entity.*;
import net.infugogr.barracuda.entity.effect.ModStatusEffects;
import net.infugogr.barracuda.item.ModItemGroups;
import net.infugogr.barracuda.item.ModItems;
import net.infugogr.barracuda.screenhandler.ModScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import team.reborn.energy.api.EnergyStorage;

public class Barracuda implements ModInitializer {
	public static final String MOD_ID = "barracuda";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Text containerTitle(String name) {
		return Text.translatable("container." + MOD_ID + "." + name);
	}


	public void onInitialize() {
		LOGGER.info("Loading...");

		// Load registry classes
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModStatusEffects.registerEffects();
		ModScreenHandlerType.registerModScreenHandlerType();
		ModBlockEntityType.registerModBlockEntityType();

		// Item Lookup
		EnergyStorage.SIDED.registerForBlockEntity(FuelGeneratorBlockEntity::getEnergyProvider, ModBlockEntityType.FUEL_GENERATOR);
		ItemStorage.SIDED.registerForBlockEntity(FuelGeneratorBlockEntity::getInventoryProvider, ModBlockEntityType.FUEL_GENERATOR);

		EnergyStorage.SIDED.registerForBlockEntity(SMESblockEntity::getEnergyProvider, ModBlockEntityType.SMES);
		ItemStorage.SIDED.registerForBlockEntity(SMESblockEntity::getInventoryProvider, ModBlockEntityType.SMES);

		EnergyStorage.SIDED.registerForBlockEntity(TeleporterBlockEntity::getEnergyProvider, ModBlockEntityType.TELEPORTER);
		ItemStorage.SIDED.registerForBlockEntity(TeleporterBlockEntity::getInventoryProvider, ModBlockEntityType.TELEPORTER);

		EnergyStorage.SIDED.registerForBlockEntity(LVcableBlockEntity::getEnergyProvider, ModBlockEntityType.LVCABLE);

		EnergyStorage.SIDED.registerForBlockEntity(HVcableBlockEntity::getEnergyProvider, ModBlockEntityType.HVCABLE);

		


		LOGGER.info("Loaded!");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}