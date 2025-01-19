package net.infugogr.barracuda;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.infugogr.barracuda.data.BarracudaWorldGen;
import net.infugogr.barracuda.data.provider.BarracudaBlockLootTableProvider;
import net.infugogr.barracuda.data.provider.BarracudaEngLangProvider;
import net.infugogr.barracuda.data.provider.BarracudaTagProvider;
import net.infugogr.barracuda.world.dimension.ModDimensions;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class BarracudaDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(BarracudaEngLangProvider::new);
		pack.addProvider(BarracudaBlockLootTableProvider::new);
		pack.addProvider(BarracudaTagProvider::new);
		pack.addProvider(BarracudaWorldGen::new);
	}

	/*@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.DIMENSION_TYPE, ModDimensions::bootstrapType);
	}*/
}
