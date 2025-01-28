package net.infugogr.barracuda;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.infugogr.barracuda.entity.ModEntities;
import net.infugogr.barracuda.entity.client.ModModelLayers;
import net.infugogr.barracuda.entity.client.PorcupineModel;
import net.infugogr.barracuda.entity.client.PorcupineRenderer;
import net.infugogr.barracuda.entity.client.AzureSerpentModel;
import net.infugogr.barracuda.entity.client.AzureSerpentRenderer;
import net.infugogr.barracuda.entity.client.BassFishModel;
import net.infugogr.barracuda.entity.client.BassFishRenderer;
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

        EntityRendererRegistry.register(ModEntities.PORCUPINE, PorcupineRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PORCUPINE, PorcupineModel::getTexturedModelData);
        
        EntityRendererRegistry.register(ModEntities.BASS_FISH, BassFishRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BASS_FISH, BassFishModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.AZURE_SERPENT, AzureSerpentRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.AZURE_SERPENT, AzureSerpentModel::getTexturedModelData);

        //BlockEntityRendererFactories.register(ModBlockEntityType.TELEPORTER, TeleporterRenderer::new);
    }
}
