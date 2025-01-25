package net.infugogr.barracuda.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    // Регистрация сущности (вашей рыбы)
    public static final EntityType<CrappieFish> CUSTOM_FISH = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("barracuda", "crappie_fish"), // Замените "yourmodid" на ID вашего мода
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_AMBIENT, CrappieFish::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.3f)) // Размеры моба
                    .build()
    );

    // Метод для регистрации всех сущностей
    public static void register() {
        // Этот метод можно вызвать в главном классе мода
        System.out.println("Регистрация сущностей для YourMod...");
    }
}