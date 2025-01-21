package net.infugogr.barracuda;

import net.fabricmc.api.ClientModInitializer;
import net.infugogr.barracuda.screenhandler.FuelGeneratorScreen;
import net.infugogr.barracuda.screenhandler.ModScreenHandlerType;
import net.infugogr.barracuda.screenhandler.SMESScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class BarracudaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        // Registering Screens
        HandledScreens.register(ModScreenHandlerType.FUEL_GENERATOR, FuelGeneratorScreen::new);
        HandledScreens.register(ModScreenHandlerType.SMES, SMESScreen::new);


        //BlockEntityRendererFactories.register(ModBlockEntityType.TELEPORTER, TeleporterRenderer::new);

    }
}
