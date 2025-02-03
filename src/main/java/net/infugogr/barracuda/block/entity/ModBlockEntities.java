package net.infugogr.barracuda.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<FishingNetBlockEntity> GEM_POLISHING_STATION_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Barracuda.MOD_ID, "fishing_net"),
                    FabricBlockEntityTypeBuilder.create(FishingNetBlockEntity::new,
                            ModBlocks.FISHING_NET).build());

    public static void registerBlockEntities() {
        Barracuda.LOGGER.info("Registering Block Entities for " + Barracuda.MOD_ID);
    }
}