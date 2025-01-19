package net.infugogr.barracuda.screenhandler;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.infugogr.barracuda.Barracuda;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlerType {
    public static final ScreenHandlerType<FuelGeneratorScreenHandler> FUEL_GENERATOR =
            register("fuel_generator", FuelGeneratorScreenHandler::new);

    public static final ScreenHandlerType<SMESScreenHandler> SMES =
            register("smes", SMESScreenHandler::new);

    public static final ScreenHandlerType<TeleporterScreenHandler> TELEPORTER =
            register("teleporter", TeleporterScreenHandler::new);

    public static <T extends ScreenHandler> ExtendedScreenHandlerType<T>
    register(String name,
             ExtendedScreenHandlerType.ExtendedFactory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, Barracuda.id(name), new ExtendedScreenHandlerType<>(factory));
    }

    public static void registerModScreenHandlerType() {
        Barracuda.LOGGER.info("Registering Mod Screen Handler Types for " + Barracuda.MOD_ID);
    }
}
