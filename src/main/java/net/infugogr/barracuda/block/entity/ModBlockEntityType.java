package net.infugogr.barracuda.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.block.ModBlocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntityType {
    public static final BlockEntityType<FuelGeneratorBlockEntity> FUEL_GENERATOR = register("fuel_generator",
            FabricBlockEntityTypeBuilder.create(FuelGeneratorBlockEntity::new, ModBlocks.FUEL_GENERATOR)
                    .build());

    public static final BlockEntityType<LVcableBlockEntity> LVCABLE = register("lvcable",
            FabricBlockEntityTypeBuilder.create(LVcableBlockEntity::new, ModBlocks.LVCABLE)
                    .build());

    public static final BlockEntityType<HVcableBlockEntity> HVCABLE = register("hvcable",
            FabricBlockEntityTypeBuilder.create(HVcableBlockEntity::new, ModBlocks.HVCABLE)
                    .build());


    public static final BlockEntityType<MachineFrameBlockEntity> MACHINE_FRAME = register("machine_frame",
            FabricBlockEntityTypeBuilder.create(MachineFrameBlockEntity::new, ModBlocks.MACHINE_FRAME)
                    .build());

    public static final BlockEntityType<SMESblockEntity> SMES = register("smes",
            FabricBlockEntityTypeBuilder.create(SMESblockEntity::new, ModBlocks.SMES)
                    .build());

    public static final BlockEntityType<TeleporterBlockEntity> TELEPORTER = register("teleporter",
            FabricBlockEntityTypeBuilder.create(TeleporterBlockEntity::new, ModBlocks.TELEPORTER)
                    .build());

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Barracuda.id(name), type);
    }

    public static void registerModBlockEntityType() {
        Barracuda.LOGGER.info("Registering Mod Block Entity Types for " + Barracuda.MOD_ID);
    }
}

