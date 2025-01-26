package net.infugogr.barracuda.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.infugogr.barracuda.Barracuda;
import net.infugogr.barracuda.entity.custom.PorcupineEntity;
import net.infugogr.barracuda.entity.custom.BassFishEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<PorcupineEntity> PORCUPINE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Barracuda.MOD_ID, "porcupine"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, PorcupineEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1f)).build());
    public static final EntityType< BassFishEntity> BASS_FISH = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Barracuda.MOD_ID, "bass_fish"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BassFishEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1f)).build());

}
