package net.infugogr.barracuda.data.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.infugogr.barracuda.block.ModBlocks;
import net.infugogr.barracuda.item.ModItems;

public class BarracudaBlockLootTableProvider extends FabricBlockLootTableProvider {

    public BarracudaBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.AQUATIC_SAND, ModItems.AQUATIC_DUST);
        addDrop(ModBlocks.CHARGED_REDSTONEIUM_BLOCK);
        addDrop(ModBlocks.CURSED_GOLD_BLOCK);
        addDrop(ModBlocks.DISCHARGED_URANIUM_BLOCK);
        addDrop(ModBlocks.DEEPSLATE_URANIUM_ORE);
        addDrop(ModBlocks.FUEL_GENERATOR);
        addDrop(ModBlocks.MINERAL_BLOCK);
        addDrop(ModBlocks.MINERAL_CLUSTER);
        addDrop(ModBlocks.REDSTONEIUM_BLOCK);
        addDrop(ModBlocks.URANIUM_BLOCK);
        addDrop(ModBlocks.URANIUM_ORE);

    }
}

